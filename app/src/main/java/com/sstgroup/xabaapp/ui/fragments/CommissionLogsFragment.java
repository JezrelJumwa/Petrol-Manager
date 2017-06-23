package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.CommissionLog;
import com.sstgroup.xabaapp.models.CommissionLogsResponse;
import com.sstgroup.xabaapp.models.XabaResponse;
import com.sstgroup.xabaapp.models.errors.ErrorCodeAndMessage;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.adapters.CommissionLogAdapter;
import com.sstgroup.xabaapp.ui.dialogs.CommissionLogFilterDialog;
import com.sstgroup.xabaapp.ui.widgets.EndlessScrollListener;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommissionLogsFragment extends BaseFragment implements CommissionLogAdapter.ClickCallbacks, CommissionLogFilterDialog.ClickCallbacks {

    @BindView(R.id.rv_commission_logs)
    RecyclerView rvCommissionLogs;
    @BindView(R.id.srl_commission_logs)
    SwipeRefreshLayout refreshLayout;
    private CommissionLogAdapter commissionLogAdapter;
    private EndlessScrollListener endlessScrollListener;
    private boolean loadMoreTriggered = false;
    private boolean isLoading = false;
    private Integer fromId;
    private Integer selectedPeriod;
    private String selectedType;
    private boolean moreItems;

    public static CommissionLogsFragment newInstance() {

        Bundle args = new Bundle();

        CommissionLogsFragment fragment = new CommissionLogsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commission_logs;
    }

    @Override
    protected void initFields() {

    }

    private void showSwipeLoading() {
        if (refreshLayout != null)
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(true);
                }
            });
    }

    private void hideSwipeLoading() {
        if (refreshLayout != null)
            refreshLayout.setRefreshing(false);
    }

    @Override
    protected void initViews(View rootView) {
        showSwipeLoading();

        selectedPeriod = null;
        selectedType = "";
        fromId = null;
        moreItems = true;

        commissionLogAdapter = new CommissionLogAdapter(xabaDbHelper.getAllCommissionLogs(),
                xabaDbHelper.getLoggedUser(activity), this);
        rvCommissionLogs.setAdapter(commissionLogAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!isLoading) {
                    isLoading = true;
                    loadMoreTriggered = true;
                    refreshLayout.setEnabled(false);
                    commissionLogAdapter.loadMoreStarted();
                    loadCommissionLogs();
                }
            }
        };

        rvCommissionLogs.setLayoutManager(linearLayoutManager);
        rvCommissionLogs.addOnScrollListener(endlessScrollListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    loadMoreTriggered = false;
                    fromId = null;

                    if (isAdded()) {
                        loadCommissionLogs();
                    } else {
                        hideSwipeLoading();
                    }

                } else {
                    hideSwipeLoading();
                }
            }
        });

        loadCommissionLogs();
    }


    private void loadCommissionLogs() {

        if (fromId == null && endlessScrollListener != null) {
            endlessScrollListener.resetState();
        }

        Call<XabaResponse<CommissionLogsResponse>> call = RestClient.getService().loadCommissionLogs(
                XabaApplication.getInstance().getLanguageCode(),
                Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue(),
                selectedType, selectedPeriod, fromId);
        call.enqueue(new Callback<XabaResponse<CommissionLogsResponse>>() {
            @Override
            public void onResponse(Call<XabaResponse<CommissionLogsResponse>> call, Response<XabaResponse<CommissionLogsResponse>> response) {
                if (commissionLogAdapter != null && refreshLayout != null
                        && xabaDbHelper != null && activity != null) {
                    if (response.isSuccessful()) {

                        if (response.body().getBody().getNextPageParams() != null) {
                            fromId = response.body().getBody().getNextPageParams().getFromId();
                        } else {
                            fromId = null;
                        }

                        ArrayList<CommissionLog> commissionLogs = response.body().getBody().getItems();

                        if (loadMoreTriggered) {
                            loadMoreTriggered = false;
                            commissionLogAdapter.loadMoreFinished();
                            refreshLayout.setEnabled(true);

                            if (moreItems){
                                commissionLogAdapter.addMoreCommissionLogs(commissionLogs);
                            }

                            if (response.body().getBody().getMoreItems() != null) {
                                moreItems = response.body().getBody().getMoreItems();
                            }

                        } else {
                            hideSwipeLoading();
                            commissionLogAdapter.replaceAllCommissionLogs(commissionLogs);

                            if (response.body().getBody().getMoreItems() != null) {
                                moreItems = response.body().getBody().getMoreItems();
                            }
                        }

                        xabaDbHelper.insertOrReplaceCommissionLogs(commissionLogs);
                    } else {
                        ErrorCodeAndMessage errorLogin = ErrorUtils.parseErrorCodeMessage(response);

                        if (errorLogin.getErrors().getMessage().equals(Constants.ERROR_UNAUTHORIZED)) {
                            XabaApplication.getInstance().logout();
                            //from this point we logout user
                            return;
                        }

                        if (errorLogin.getErrors().getMessage().equals(Constants.ERROR_STATUS_UNEXPECTED)) {
                            ToastInterval.showToast(activity, getString(R.string.something_is_wrong));
                        }

                        hideSwipeLoading();
                        commissionLogAdapter.loadMoreFinished();
                        loadCommissionLogsFromDb();
                    }

                    isLoading = false;
                }
            }

            @Override
            public void onFailure(Call<XabaResponse<CommissionLogsResponse>> call, Throwable t) {
                hideSwipeLoading();
                loadMoreTriggered = false;
                isLoading = false;
                commissionLogAdapter.loadMoreFinished();
                refreshLayout.setEnabled(true);

                loadCommissionLogsFromDb();
                Utils.onFailureUtils(activity, t);
            }
        });
    }

    private void loadCommissionLogsFromDb() {
        fromId = null;

//        if (selectedFilter.equals("")) {
//            commissionLogAdapter.replaceAllCommissionLogs(xabaDbHelper.getAllCommissionLogs());
//        } else if (selectedFilter.equals(Constants.NOTIFICATION_PAYOUT)) {
//            commissionLogAdapter.replaceAllCommissionLogs(xabaDbHelper.getAllCommissionLogsByType(Constants.NOTIFICATION_PAYOUT));
//        } else if (selectedFilter.equals(Constants.NOTIFICATION_REFERRAL_VALIDATION)) {
//            commissionLogAdapter.replaceAllCommissionLogs(xabaDbHelper.getAllCommissionLogsByType(Constants.NOTIFICATION_REFERRAL_VALIDATION));
//        }
    }

    @Override
    public void onApplyFilter(Integer period, String type) {
        selectedPeriod = period;
        selectedType = type;
        loadCommissionLogs();
    }

    @Override
    public void onClick() {
        CommissionLogFilterDialog commissionLogFilterDialog
                = new CommissionLogFilterDialog(activity, this, selectedPeriod, selectedType);
        commissionLogFilterDialog.show();
    }
}
