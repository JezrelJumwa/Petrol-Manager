package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.Notification;
import com.sstgroup.xabaapp.ui.adapters.NotificationAdapter;
import com.sstgroup.xabaapp.ui.dialogs.NotificationsFilterDialog;
import com.sstgroup.xabaapp.ui.widgets.EndlessScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rosenstoyanov on 6/12/17.
 */

public class NotificationsFragment extends BaseFragment implements NotificationsFilterDialog.Clicks {

    @BindView(R.id.rv_notifications)
    RecyclerView rvNotifications;
    @BindView(R.id.txt_notification_types)
    TextView tvNotificationTypes;
    @BindView(R.id.srl_notifications)
    SwipeRefreshLayout refreshLayout;
    private EndlessScrollListener endlessScrollListener;
    private NotificationAdapter notificationAdapter;
    private boolean canLoadMore = true;

    public static NotificationsFragment newInstance() {

        Bundle args = new Bundle();

        NotificationsFragment fragment = new NotificationsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notifications;
    }

    @OnClick(R.id.grp_notification_types)
    public void onClick() {
        NotificationsFilterDialog notificationsFilterDialog
                = new NotificationsFilterDialog(activity, this,
                tvNotificationTypes.getText().toString());
        notificationsFilterDialog.show();
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

        ArrayList<Notification> notifications = new ArrayList<>();
        for (int i = 0; i < 30; i++){
//            notifications.add(new Notification(1, "Tekst", "Name", new Date()));
//            notifications.add(new Notification(2, "Tekst", "Name", new Date()));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSwipeLoading();
            }
        }, 2000);

        notificationAdapter = new NotificationAdapter(notifications);
        rvNotifications.setAdapter(notificationAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (canLoadMore) {
                    canLoadMore = false;
                    rvNotifications.post(new Runnable() {
                        @Override
                        public void run() {
                            notificationAdapter.loadMoreStarted();
                        }
                    });

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notificationAdapter.loadMoreFinished();
                            canLoadMore = true;
                        }
                    }, 3000);
                }
            }
        };
        rvNotifications.setLayoutManager(linearLayoutManager);
        rvNotifications.addOnScrollListener(endlessScrollListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hideSwipeLoading();
            }
        });
    }

    @Override
    public void showAll(String selectedText) {
        tvNotificationTypes.setText(selectedText);
    }

    @Override
    public void showPayouts(String selectedText) {
        tvNotificationTypes.setText(selectedText);
    }

    @Override
    public void showValidated(String selectedText) {
        tvNotificationTypes.setText(selectedText);
    }
}
