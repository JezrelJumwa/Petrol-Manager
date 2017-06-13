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

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    private ArrayList<Notification> notifications;

    public NotificationAdapter(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotificationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        holder.bind(getItemAt(position));
    }

    @Override
    public int getItemCount() {
        return notifications != null ? notifications.size() : 0;
    }

    private Notification getItemAt(int position){
        return notifications.get(position);
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

        void bind(Notification notification){
            if (notification.getType() == Notification.PAY_TYPE){
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
