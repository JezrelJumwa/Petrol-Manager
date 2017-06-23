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
import com.sstgroup.xabaapp.models.CommissionLog;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROGRESS = 1;
    private final int VIEW_HEADER = 2;
    private final String STATUS_PAYMENT = "payout";

    private List<CommissionLog> commissionLogs;
    private User loggedUser;


    public CommissionLogAdapter(List<CommissionLog> commissionLogs, User loggedUser) {
        this.commissionLogs = commissionLogs;
        this.loggedUser = loggedUser;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            return new CommissionLogHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_commission_log, parent, false));
        } else if (viewType == VIEW_HEADER) {
            return new HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_commission_log_header, parent, false));
        }
        return new BottomProgressHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bottom_progress, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        }

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
        } else if (holder instanceof HeaderHolder) {
            ((HeaderHolder) holder).bind();
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

    class HeaderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_commissions_balance)
        TextView txtCommissionBalance;

        HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            txtCommissionBalance.setText(loggedUser.getCurrentBalance() + " " + loggedUser.getCurrency().getCode());
        }

        @OnClick(R.id.txt_filters)
        public void onClick(View view) {
            ToastInterval.showToast(view.getContext(), "Filters clicked");
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

            if (commissionLog.getDescription().equals(STATUS_PAYMENT)) {
                imageViewStatus.setBackgroundColor(ContextCompat.getColor(imageViewStatus.getContext(), R.color.referred_worker_blue));
            } else {
                imageViewStatus.setBackgroundColor(ContextCompat.getColor(imageViewStatus.getContext(), R.color.referred_worker_green));
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                txtText.setText(Html.fromHtml(commissionLog.getText(), Html.FROM_HTML_MODE_LEGACY));
            } else {
                txtText.setText(Html.fromHtml(commissionLog.getText()));
            }

            txtDate.setText(Utils.dateFromat(commissionLog.getCreatedAt(), Constants.DATE_FORMAT_REFERRED_WORKERS));
            txtValue.setText(commissionLog.getAmount());
        }
    }
}
