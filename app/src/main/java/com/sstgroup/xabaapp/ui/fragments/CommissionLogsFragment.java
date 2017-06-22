package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.CommissionLog;
import com.sstgroup.xabaapp.models.CommissionLogsResponse;
import com.sstgroup.xabaapp.models.XabaResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.adapters.CommissionLogAdapter;
import com.sstgroup.xabaapp.ui.widgets.EndlessScrollListener;
import com.sstgroup.xabaapp.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommissionLogsFragment extends BaseFragment {

    @BindView(R.id.rv_commission_logs)
    RecyclerView rvCommissionLogs;
    @BindView(R.id.srl_commission_logs)
    SwipeRefreshLayout refreshLayout;
    private CommissionLogAdapter commissionLogAdapter;
    private EndlessScrollListener endlessScrollListener;
    private boolean canLoadMore = true;

    private List<CommissionLog> mCommissionLogs;

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

    @Override
    protected void initViews(View rootView) {
        showSwipeLoading();

        loadCommissionLogs();
//        loadDemoData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSwipeLoading();
            }
        }, 2000);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (canLoadMore) {
                    canLoadMore = false;
                    rvCommissionLogs.post(new Runnable() {
                        @Override
                        public void run() {
                            commissionLogAdapter.loadMoreStarted();
                        }
                    });

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            commissionLogAdapter.loadMoreFinished();
                            canLoadMore = true;
                        }
                    }, 3000);
                }
            }
        };

        rvCommissionLogs.setLayoutManager(linearLayoutManager);
        rvCommissionLogs.addOnScrollListener(endlessScrollListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hideSwipeLoading();
            }
        });
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

    private void loadCommissionLogs() {
        Call<XabaResponse<CommissionLogsResponse>> call = RestClient.getService().loadCommissionLogs(
                XabaApplication.getInstance().getLanguageCode(),
                Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue());
        call.enqueue(new Callback<XabaResponse<CommissionLogsResponse>>() {
            @Override
            public void onResponse(Call<XabaResponse<CommissionLogsResponse>> call, Response<XabaResponse<CommissionLogsResponse>> response) {
                if (response.isSuccessful()) {
                    mCommissionLogs = response.body().getBody().getItems();
                    commissionLogAdapter = new CommissionLogAdapter(mCommissionLogs, getContext());
                    rvCommissionLogs.setAdapter(commissionLogAdapter);
                    //TODO: save db
                } else {
                    //TODO: request db
                }
                hideSwipeLoading();
            }

            @Override
            public void onFailure(Call<XabaResponse<CommissionLogsResponse>> call, Throwable t) {
                hideSwipeLoading();
                //TODO: check exception for no internet request db
            }
        });
    }

    private void loadDemoData() {

        ArrayList<CommissionLog> commissionLogs = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 3 == 0) {
                commissionLogs.add(new CommissionLog(8, "15", "2", "payout", "payout", new Date(), "Paid to your account"));
            } else if (i % 3 == 1) {
                commissionLogs.add(new CommissionLog(8, "15", "2", "credit", "asd", new Date(), "Paid to your account"));
            } else if (i % 3 == 2) {
                commissionLogs.add(new CommissionLog(8, "15", "2", "credit", "payout", new Date(), "Paid to your account"));
            }
        }

        commissionLogAdapter = new CommissionLogAdapter(commissionLogs, getContext());
        rvCommissionLogs.setAdapter(commissionLogAdapter);
    }
}
