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

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rosenstoyanov on 6/12/17.
 */

public class NotificationsFragment extends BaseFragment {

    @BindView(R.id.rv_notifications)
    RecyclerView rvNotifications;
    @BindView(R.id.txt_notification_types)
    TextView tvNotificationTypes;
    @BindView(R.id.srl_notifications)
    SwipeRefreshLayout refreshLayout;

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

    @OnClick({R.id.grp_notification_types, R.id.iv_period})
    public void onClick(View view){
        int id = view.getId();

        switch (id){
            case R.id.grp_notification_types:
                break;
            case R.id.iv_period:
                break;

        }
    }

    @Override
    protected void initFields() {

    }

    private void showSwipeLoading(){
        if (refreshLayout != null)
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(true);
                }
            });
    }

    private void hideSwipeLoading(){
        if (refreshLayout != null)
            refreshLayout.setRefreshing(false);
    }

    @Override
    protected void initViews(View rootView) {
        showSwipeLoading();

        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification(1,"Tekst", "Name", new Date()));
        notifications.add(new Notification(2,"Tekst", "Name", new Date()));
        notifications.add(new Notification(1,"Tekst", "Name", new Date()));
        notifications.add(new Notification(2,"Tekst", "Name", new Date()));
        notifications.add(new Notification(1,"Tekst", "Name", new Date()));
        notifications.add(new Notification(2,"Tekst", "Name", new Date()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSwipeLoading();
            }
        }, 2000);

        NotificationAdapter adapter = new NotificationAdapter(notifications);
        rvNotifications.setAdapter(adapter);
        rvNotifications.setLayoutManager(new LinearLayoutManager(activity));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hideSwipeLoading();
            }
        });
    }
}
