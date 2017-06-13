package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;

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
    SwipeRefreshLayout srlNotifications;

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

    @Override
    protected void initViews(View rootView) {
        srlNotifications.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }
}
