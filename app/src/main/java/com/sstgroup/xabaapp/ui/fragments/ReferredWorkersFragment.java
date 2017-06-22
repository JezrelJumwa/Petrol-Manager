package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.ReferredWorker;
import com.sstgroup.xabaapp.models.ReferredWorkersResponse;
import com.sstgroup.xabaapp.models.XabaResponse;
import com.sstgroup.xabaapp.models.errors.ErrorCodeAndMessage;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.adapters.ReferredWorkerAdapter;
import com.sstgroup.xabaapp.ui.widgets.EndlessScrollListener;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.Utils;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReferredWorkersFragment extends BaseFragment {

    @BindView(R.id.rv_referred_workers)
    RecyclerView rvReferredWorkers;
    @BindView(R.id.srl_referred_workers)
    SwipeRefreshLayout refreshLayout;
    private ReferredWorkerAdapter referredWorkerAdapter;
    private EndlessScrollListener endlessScrollListener;
    private boolean loadMoreTriggered = false;
    private boolean isLoading = false;

    private Integer fromId;

    public static ReferredWorkersFragment newInstance() {
        Bundle args = new Bundle();

        ReferredWorkersFragment fragment = new ReferredWorkersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_referred_workers;
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

        fromId = null;

        referredWorkerAdapter = new ReferredWorkerAdapter(xabaDbHelper.getAllReferredWorkers(), getContext());
        rvReferredWorkers.setAdapter(referredWorkerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!isLoading) {
                    isLoading = true;
                    loadMoreTriggered = true;
                    refreshLayout.setEnabled(false);
                    referredWorkerAdapter.loadMoreStarted();
                    loadReferredWorkers();
                }
            }
        };

        rvReferredWorkers.setLayoutManager(linearLayoutManager);
        rvReferredWorkers.addOnScrollListener(endlessScrollListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    loadMoreTriggered = false;
                    fromId = null;

                    if (isAdded()) {
                        loadReferredWorkers();
                    } else {
                        hideSwipeLoading();
                    }

                } else {
                    hideSwipeLoading();
                }
            }
        });

        loadReferredWorkers();
    }

    private void loadReferredWorkers() {

        if (fromId == null && endlessScrollListener != null) {
            endlessScrollListener.resetState();
        }

        Call<XabaResponse<ReferredWorkersResponse>> call = RestClient.getService().loadReferredWorkers(
                XabaApplication.getInstance().getLanguageCode(),
                Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue());
        call.enqueue(new Callback<XabaResponse<ReferredWorkersResponse>>() {
            @Override
            public void onResponse(Call<XabaResponse<ReferredWorkersResponse>> call, Response<XabaResponse<ReferredWorkersResponse>> response) {
                if (response.isSuccessful()) {

                    if (response.body().getBody().getNextPageParams() != null) {
                        fromId = Integer.valueOf(response.body().getBody().getNextPageParams());
                    } else {
                        fromId = null;
                    }

                    ArrayList<ReferredWorker> referredWorkers = response.body().getBody().getItems();

                    if (loadMoreTriggered) {
                        referredWorkerAdapter.addMoreReferredWorkers(referredWorkers);
                        loadMoreTriggered = false;
                        refreshLayout.setEnabled(true);
                        referredWorkerAdapter.loadMoreFinished();
                    } else {
                        hideSwipeLoading();
                        referredWorkerAdapter.replaceAllReferredWorkers(referredWorkers);
                    }

                    xabaDbHelper.insertOrReplaceReferredWorkers(referredWorkers);
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
                    referredWorkerAdapter.loadMoreFinished();
                    loadReferredWorkersFromDb();
                }

                isLoading = false;
            }

            @Override
            public void onFailure(Call<XabaResponse<ReferredWorkersResponse>> call, Throwable t) {
                hideSwipeLoading();
                loadMoreTriggered = false;
                isLoading = false;
                referredWorkerAdapter.loadMoreFinished();
                refreshLayout.setEnabled(true);

                loadReferredWorkersFromDb();
                Utils.onFailureUtils(activity, t);
            }
        });
    }

    private void loadReferredWorkersFromDb() {
        fromId = null;
        referredWorkerAdapter.replaceAllReferredWorkers(xabaDbHelper.getAllReferredWorkers());
    }

    private void loadDemoData() {

        ArrayList<ReferredWorker> referredWorkers = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 3 == 0) {
                referredWorkers.add(new ReferredWorker(2l, "", "active", "Sasho", " Sasho2", new Date(), new Date(), ""));
            } else if (i % 3 == 1) {
                referredWorkers.add(new ReferredWorker(1l, "", "pending", "Pesho", "Pesho2", new Date(), new Date(), ""));
            } else if (i % 3 == 2) {
                referredWorkers.add(new ReferredWorker(1l, "", "inactive", "Atanas", "Atanas2", new Date(), new Date(), ""));
            }
        }
    }
}
