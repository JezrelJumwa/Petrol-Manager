package com.sstgroup.xabaapp.ui.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.Notification;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rosenstoyanov on 6/13/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int VIEW_PROGRESS = 1;

    private List<Notification> notifications;

    public NotificationAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            return new NotificationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification, parent, false));
        }

        return new BottomProgressHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bottom_progress, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemAt(position) == null) {
            return VIEW_PROGRESS;
        } else {
            return VIEW_ITEM;
        }
    }

    public void replaceAllNotification(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    public void addMoreNotifications(List<Notification> notifications) {
        int size = notifications.size();
        this.notifications.addAll(notifications);
        notifyItemRangeInserted(size, notifications.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NotificationHolder) {
            ((NotificationHolder) holder).bind(getItemAt(position));
        }
    }

    @Override
    public int getItemCount() {
        return notifications != null ? notifications.size() : 0;
    }

    public void loadMoreStarted() {
        notifications.add(null);
        notifyItemInserted(notifications.size() - 1);
    }

    public void loadMoreFinished() {
        int position = notifications.size();
        if (position > 1 && notifications.get(position - 1) == null) {
            notifications.remove(position - 1);
            notifyItemRemoved(position - 1);
        }
    }

    private Notification getItemAt(int position) {
        return notifications.get(position);
    }

    class BottomProgressHolder extends RecyclerView.ViewHolder {

        BottomProgressHolder(View itemView) {
            super(itemView);
        }
    }

    class NotificationHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_notification_text)
        TextView txtText;
        @BindView(R.id.txt_notification_type_text)
        TextView txtTypeText;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.ic_notification)
        ImageView ivNotification;

        NotificationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Notification notification) {
            if (notification.getDescription().equals(Constants.NOTIFICATION_PAYOUT)) {
                ivNotification.setImageResource(R.drawable.ic_wallet);
                txtText.setText(notification.getText());
                txtTypeText.setText(notification.getSubtext());
                txtTypeText.setTextColor(ContextCompat.getColor(txtTypeText.getContext(), R.color.text_green));
                txtDate.setText(Utils.dateFromat(notification.getDate(), Constants.DATE_FORMAT_DASHES));
            } else if (notification.getDescription().equals(Constants.NOTIFICATION_REFERRAL_VALIDATION)) {
                ivNotification.setImageResource(R.drawable.ic_valid_account);
                //TODO: init HTML parsing for getText
                //<b>Owen Mburu</b> has registered
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    txtText.setText(Html.fromHtml(notification.getText(), Html.FROM_HTML_MODE_LEGACY));
                } else {
                    txtText.setText(Html.fromHtml(notification.getText()));
                }
//                txtText.setText(Html.fromHtml(notification.getText()));
                txtTypeText.setText(notification.getSubtext());
                txtTypeText.setTextColor(ContextCompat.getColor(txtTypeText.getContext(), R.color.text_blue));
                txtDate.setText(Utils.dateFromat(notification.getDate(), Constants.DATE_FORMAT_DASHES));
            }
        }
    }
}
