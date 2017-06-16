package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.Notification;
import com.sstgroup.xabaapp.models.NotificationResponse;
import com.sstgroup.xabaapp.models.XabaResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.adapters.NotificationAdapter;
import com.sstgroup.xabaapp.ui.dialogs.NotificationsFilterDialog;
import com.sstgroup.xabaapp.ui.widgets.EndlessScrollListener;
import com.sstgroup.xabaapp.utils.Constants;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private void loadNotifications(String filter, Integer lastId){
        Call<XabaResponse<NotificationResponse>> call = RestClient.getService().loadNotifications(
                Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue(),
                filter, lastId);
        call.enqueue(new Callback<XabaResponse<NotificationResponse>>() {
            @Override
            public void onResponse(Call<XabaResponse<NotificationResponse>> call, Response<XabaResponse<NotificationResponse>> response) {
                if (response.isSuccessful()){
                    //TODO: save db
                } else {
                    //TODO: request db
                }
                hideSwipeLoading();
            }

            @Override
            public void onFailure(Call<XabaResponse<NotificationResponse>> call, Throwable t) {
                hideSwipeLoading();
                //TODO: check exeption for no internet request db
            }
        });
    }

    @Override
    protected void initViews(View rootView) {
        showSwipeLoading();

        ArrayList<Notification> notifications = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0){
                notifications.add(new Notification(i,
                        Constants.NOTIFICATION_PAYOUT,
                        "You were transferred 502.200 KES", "Payout done", new Date()));
            } else {
                notifications.add(new Notification(i,
                        Constants.NOTIFICATION_REFERRAL_VALIDATION,
                        "John Doe has registered.", "Validated account", new Date()));
            }
        }

//        xabaDbHelper.insertOrReplaceNotifications(notifications);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSwipeLoading();
            }
        }, 2000);

//        notificationAdapter = new NotificationAdapter(xabaDbHelper.getAllNotifications());
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
//        notificationAdapter.replaceAllNotification(xabaDbHelper.getAllNotifications());
    }

    @Override
    public void showPayouts(String selectedText) {
        tvNotificationTypes.setText(selectedText);
//        notificationAdapter.replaceAllNotification(xabaDbHelper.getNotificationsByType(Constants.NOTIFICATION_PAYOUT));
    }

    @Override
    public void showValidated(String selectedText) {
        tvNotificationTypes.setText(selectedText);
//        notificationAdapter.replaceAllNotification(xabaDbHelper.getNotificationsByType(Constants.NOTIFICATION_REFERRAL_VALIDATION));
    }
}
