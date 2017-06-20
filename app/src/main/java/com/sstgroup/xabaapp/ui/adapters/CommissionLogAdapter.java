package com.sstgroup.xabaapp.ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.CommissionLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommissionLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CommissionLog> commissionLogs;
    private Context context;

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROGRESS = 1;

    public CommissionLogAdapter(List<CommissionLog> commissionLogs, Context context) {
        this.commissionLogs = commissionLogs;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            return new CommissionLogHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_commission_log, parent, false));
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

    public void replaceAllCommissionLogs(List<CommissionLog> commissionLogs) {
        this.commissionLogs = commissionLogs;
        notifyDataSetChanged();
    }

    public void addMoreCommissionLogs(List<CommissionLog> commissionLogs) {
        int size = commissionLogs.size();
        this.commissionLogs.addAll(commissionLogs);
        notifyItemRangeInserted(size, commissionLogs.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommissionLogHolder) {
            ((CommissionLogHolder) holder).bind(getItemAt(position));
        }
    }

    @Override
    public int getItemCount() {
        return commissionLogs != null ? commissionLogs.size() : 0;
    }

    public void loadMoreStarted() {
        commissionLogs.add(null);
        notifyItemChanged(commissionLogs.size() - 1);
    }

    public void loadMoreFinished() {
        int position = commissionLogs.size();
        commissionLogs.remove(position - 1);
        notifyItemChanged(position - 1);
    }

    private CommissionLog getItemAt(int position) {
        return commissionLogs.get(position);
    }

    class BottomProgressHolder extends RecyclerView.ViewHolder {

        BottomProgressHolder(View itemView) {
            super(itemView);
        }
    }

    class CommissionLogHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_status)
        ImageView imageViewStatus;
        @BindView(R.id.txt_text)
        TextView txtText;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.txt_value)
        TextView txtValue;

        CommissionLogHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CommissionLog commissionLog) {

            txtText.setText(commissionLog.getText());
            txtDate.setText(commissionLog.getCreatedAt());
            txtValue.setText(commissionLog.getAmount());
        }
    }
}
