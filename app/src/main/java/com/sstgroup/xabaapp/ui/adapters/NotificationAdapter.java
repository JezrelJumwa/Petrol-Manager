package com.sstgroup.xabaapp.ui.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.Notification;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.SpanableUtils;
import com.sstgroup.xabaapp.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rosenstoyanov on 6/13/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int VIEW_PROGRESS = 1;

    private ArrayList<Notification> notifications;

    public NotificationAdapter(ArrayList<Notification> notifications) {
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
        notifyItemChanged(notifications.size() - 1);
    }

    public void loadMoreFinished() {
        int position = notifications.size();
        notifications.remove(position - 1);
        notifyItemChanged(position - 1);
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
            if (notification.getType() == Notification.PAY_TYPE) {
                ivNotification.setImageResource(R.drawable.ic_wallet);
                txtText.setText(notification.getText());
                txtTypeText.setText("PAYOUT DONE");
                txtTypeText.setTextColor(ContextCompat.getColor(txtTypeText.getContext(), R.color.text_green));
                txtDate.setText(Utils.dateFromat(notification.getDate(), Constants.DATE_FORMAT_DASHES));
            } else {
                ivNotification.setImageResource(R.drawable.ic_valid_account);
                txtText.setText(SpanableUtils.boldSpan(notification.getName() + notification.getText(), 0, notification.getName().length()));
                txtTypeText.setText("VALIDATED ACCOUNT");
                txtTypeText.setTextColor(ContextCompat.getColor(txtTypeText.getContext(), R.color.text_blue));
                txtDate.setText(Utils.dateFromat(notification.getDate(), Constants.DATE_FORMAT_DASHES));
            }
        }
    }
}
