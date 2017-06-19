package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.ReferredWorker;
import com.sstgroup.xabaapp.models.ReferredWorkersResponse;
import com.sstgroup.xabaapp.models.XabaResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.adapters.ReferredWorkerAdapter;
import com.sstgroup.xabaapp.ui.widgets.EndlessScrollListener;
import com.sstgroup.xabaapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

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
    private boolean canLoadMore = true;

    private List<ReferredWorker> mReferredWorkers;

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

    @Override
    protected void initViews(View rootView) {
        showSwipeLoading();

//        loadReferredWorkers();
        loadDemoData();

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
                    rvReferredWorkers.post(new Runnable() {
                        @Override
                        public void run() {
                            referredWorkerAdapter.loadMoreStarted();
                        }
                    });

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            referredWorkerAdapter.loadMoreFinished();
                            canLoadMore = true;
                        }
                    }, 3000);
                }
            }
        };

        rvReferredWorkers.setLayoutManager(linearLayoutManager);
        rvReferredWorkers.addOnScrollListener(endlessScrollListener);
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

    private void loadReferredWorkers() {
        Call<XabaResponse<ReferredWorkersResponse>> call = RestClient.getService().loadReferredWorkers(
                Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue());
        call.enqueue(new Callback<XabaResponse<ReferredWorkersResponse>>() {
            @Override
            public void onResponse(Call<XabaResponse<ReferredWorkersResponse>> call, Response<XabaResponse<ReferredWorkersResponse>> response) {
                if (response.isSuccessful()) {
                    mReferredWorkers = response.body().getBody().getItems();
                    referredWorkerAdapter = new ReferredWorkerAdapter(mReferredWorkers, getContext());
                    rvReferredWorkers.setAdapter(referredWorkerAdapter);
                    //TODO: save db
                } else {
                    //TODO: request db
                }
                hideSwipeLoading();
            }

            @Override
            public void onFailure(Call<XabaResponse<ReferredWorkersResponse>> call, Throwable t) {
                hideSwipeLoading();
                //TODO: check exeption for no internet request db
            }
        });
    }

    private void loadDemoData() {

        ArrayList<ReferredWorker> notifications = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 3 == 0) {
                notifications.add(new ReferredWorker(2, "", "active", "Sasho", " Sasho2", "17-feb-2017", "17-feb-2017", ""));
            } else if (i % 3 == 1) {
                notifications.add(new ReferredWorker(1, "", "pending", "Pesho", "Pesho2", "17-feb-2017", "17-feb-2017", ""));
            } else if (i % 3 == 2) {
                notifications.add(new ReferredWorker(1, "", "inactive", "Atanas", "Atanas2", "17-feb-2017", "17-feb-2017", ""));
            }
        }

        referredWorkerAdapter = new ReferredWorkerAdapter(notifications, getContext());
        rvReferredWorkers.setAdapter(referredWorkerAdapter);
    }
}
