package com.sstgroup.xabaapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rosenstoyanov on 6/13/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {


    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_notification_text)
        TextView txtText;
        @BindView(R.id.txt_notification_type_text)
        TextView txtTypeText;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.ic_notification)
        ImageView ivNotification;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(){

        }
    }
}
