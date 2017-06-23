package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
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
import com.sstgroup.xabaapp.models.errors.ErrorCodeAndMessage;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.adapters.NotificationAdapter;
import com.sstgroup.xabaapp.ui.dialogs.NotificationsFilterDialog;
import com.sstgroup.xabaapp.ui.widgets.EndlessScrollListener;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends BaseFragment implements NotificationsFilterDialog.Clicks {

    @BindView(R.id.rv_notifications)
    RecyclerView rvNotifications;
    @BindView(R.id.txt_notification_types)
    TextView tvNotificationTypes;
    @BindView(R.id.srl_notifications)
    SwipeRefreshLayout refreshLayout;
    private EndlessScrollListener endlessScrollListener;
    private NotificationAdapter notificationAdapter;
    private boolean loadMoreTriggered = false;
    private boolean isLoading = false;


    private String selectedFilter;
    private Integer fromId;
    private boolean moreItems;

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

    private void loadNotifications() {

        if (fromId == null && endlessScrollListener != null) {
            endlessScrollListener.resetState();
        }

        Call<XabaResponse<NotificationResponse>> call = RestClient.getService().loadNotifications(
                XabaApplication.getInstance().getLanguageCode(),
                Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue(),
                selectedFilter, fromId);
        call.enqueue(new Callback<XabaResponse<NotificationResponse>>() {
            @Override
            public void onResponse(Call<XabaResponse<NotificationResponse>> call, Response<XabaResponse<NotificationResponse>> response) {
                if (notificationAdapter != null && refreshLayout != null
                        && xabaDbHelper != null && activity != null) {
                    if (response.isSuccessful()) {

                        if (response.body().getBody().getNextPageParams() != null) {
                            fromId = response.body().getBody().getNextPageParams().getFromId();
                        } else {
                            fromId = null;
                        }

                        ArrayList<Notification> notifications = response.body().getBody().getItems();

                        if (loadMoreTriggered) {
                            loadMoreTriggered = false;
                            notificationAdapter.loadMoreFinished();
                            refreshLayout.setEnabled(true);

                            if (moreItems){
                                notificationAdapter.addMoreNotifications(notifications);
                            }

                            if (response.body().getBody().getMoreItems() != null) {
                                moreItems = response.body().getBody().getMoreItems();
                            }
                        } else {
                            hideSwipeLoading();
                            notificationAdapter.replaceAllNotification(notifications);

                            if (response.body().getBody().getMoreItems() != null) {
                                moreItems = response.body().getBody().getMoreItems();
                            }
                        }

                        xabaDbHelper.insertOrReplaceNotifications(notifications);
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
                        notificationAdapter.loadMoreFinished();
                        loadNotificationsFromDb();
                    }

                    isLoading = false;
                }

            }

            @Override
            public void onFailure(Call<XabaResponse<NotificationResponse>> call, Throwable t) {
                hideSwipeLoading();
                loadMoreTriggered = false;
                isLoading = false;
                notificationAdapter.loadMoreFinished();
                refreshLayout.setEnabled(true);

                loadNotificationsFromDb();
                Utils.onFailureUtils(activity, t);
            }
        });
    }

    @Override
    protected void initViews(View rootView) {
        showSwipeLoading();

        selectedFilter = "";
        fromId = null;
        moreItems = true;

        notificationAdapter = new NotificationAdapter(xabaDbHelper.getAllNotifications());
        rvNotifications.setAdapter(notificationAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (!isLoading) {
                    isLoading = true;
                    loadMoreTriggered = true;
                    refreshLayout.setEnabled(false);
                    notificationAdapter.loadMoreStarted();
                    loadNotifications();
                }
            }
        };

        rvNotifications.setLayoutManager(linearLayoutManager);
        rvNotifications.addOnScrollListener(endlessScrollListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO: disable bottom loader
                if (!isLoading) {
                    isLoading = true;
                    loadMoreTriggered = false;
                    fromId = null;

                    if (isAdded()) {
                        loadNotifications();
                    } else {
                        hideSwipeLoading();
                    }

                } else {
                    hideSwipeLoading();
                }
            }
        });

        loadNotifications();
    }

    private void loadNotificationsFromDb() {
        fromId = null;

        if (selectedFilter.equals("")) {
            notificationAdapter.replaceAllNotification(xabaDbHelper.getAllNotifications());
        } else if (selectedFilter.equals(Constants.NOTIFICATION_PAYOUT)) {
            notificationAdapter.replaceAllNotification(xabaDbHelper.getNotificationsByType(Constants.NOTIFICATION_PAYOUT));
        } else if (selectedFilter.equals(Constants.NOTIFICATION_REFERRAL_VALIDATION)) {
            notificationAdapter.replaceAllNotification(xabaDbHelper.getNotificationsByType(Constants.NOTIFICATION_REFERRAL_VALIDATION));
        }
    }

    @Override
    public void showAll(String selectedText) {
        tvNotificationTypes.setText(selectedText);
        selectedFilter = "";
        showSwipeLoading();
        loadNotifications();
    }

    @Override
    public void showPayouts(String selectedText) {
        tvNotificationTypes.setText(selectedText);
        selectedFilter = Constants.NOTIFICATION_PAYOUT;
        showSwipeLoading();
        loadNotifications();
    }

    @Override
    public void showValidated(String selectedText) {
        tvNotificationTypes.setText(selectedText);
        selectedFilter = Constants.NOTIFICATION_REFERRAL_VALIDATION;
        showSwipeLoading();
        loadNotifications();
    }
}
