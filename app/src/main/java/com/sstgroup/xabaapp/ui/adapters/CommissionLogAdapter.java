package com.sstgroup.xabaapp.ui.adapters;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.CommissionLog;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Utils;

import java.util.ArrayList;
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
    private ClickCallbacks clickCallbacks;


    public CommissionLogAdapter(List<CommissionLog> commissionLogs, User loggedUser, ClickCallbacks clickCallbacks) {
        this.commissionLogs = new ArrayList<>(commissionLogs);
        this.loggedUser = loggedUser;
        this.clickCallbacks = clickCallbacks;
        if (this.commissionLogs.isEmpty()){
            this.commissionLogs.add(new CommissionLog());
        }
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

        if (getItemAt(position - 1) == null) {
            return VIEW_PROGRESS;
        } else {
            return VIEW_ITEM;
        }
    }

    public void replaceAllCommissionLogs(List<CommissionLog> commissionLogs) {
        this.commissionLogs = new ArrayList<>(commissionLogs);
        if (this.commissionLogs.isEmpty()){
            this.commissionLogs.add(new CommissionLog());
        }
        notifyDataSetChanged();
    }

    public void addMoreCommissionLogs(List<CommissionLog> commissionLogs) {
        if (!commissionLogs.isEmpty()) {
            int size = this.commissionLogs.size();
            this.commissionLogs.addAll(commissionLogs);
            notifyItemRangeInserted(size + 1, commissionLogs.size() + 1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommissionLogHolder) {
            ((CommissionLogHolder) holder).bind(position);
        } else if (holder instanceof HeaderHolder) {
            ((HeaderHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return commissionLogs != null ? commissionLogs.size() + 1 : 0;
    }

    public void loadMoreStarted() {
        commissionLogs.add(null);
        notifyItemChanged(commissionLogs.size());
    }

    public void loadMoreFinished() {
        int position = commissionLogs.size();
        commissionLogs.remove(position - 1);
        notifyItemChanged(position);
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
        @BindView(R.id.txt_filters)
        TextView txtFilters;
        @BindView(R.id.grp_container)
        LinearLayout grpContainer;

        CommissionLogHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            if (position == 1) {
                txtFilters.setVisibility(View.VISIBLE);
            } else {
                txtFilters.setVisibility(View.GONE);
            }

            CommissionLog commissionLog = getItemAt(position - 1);

            if (commissionLog.getId() == null) {
                grpContainer.setVisibility(View.GONE);
                return;
            } else {
                grpContainer.setVisibility(View.VISIBLE);
            }

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

        @OnClick(R.id.txt_filters)
        public void onClick() {
            if (clickCallbacks != null)
                clickCallbacks.onClick();
        }
    }

    public interface ClickCallbacks {
        void onClick();
    }
}
