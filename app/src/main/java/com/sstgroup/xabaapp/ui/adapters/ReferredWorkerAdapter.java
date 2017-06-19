package com.sstgroup.xabaapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.ReferredWorker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferredWorkerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int VIEW_PROGRESS = 1;

    private List<ReferredWorker> referredWorkers;

    public ReferredWorkerAdapter(List<ReferredWorker> referredWorkers) {
        this.referredWorkers = referredWorkers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            return new ReferredWorkerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_referred_worker, parent, false));
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

    public void replaceAllReferredWorkers(List<ReferredWorker> referredWorkers) {
        this.referredWorkers = referredWorkers;
        notifyDataSetChanged();
    }

    public void addMoreReferredWorkers(List<ReferredWorker> referredWorkers) {
        int size = referredWorkers.size();
        this.referredWorkers.addAll(referredWorkers);
        notifyItemRangeInserted(size, referredWorkers.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ReferredWorkerHolder) {
            ((ReferredWorkerHolder) holder).bind(getItemAt(position));
        }
    }

    @Override
    public int getItemCount() {
        return referredWorkers != null ? referredWorkers.size() : 0;
    }

    public void loadMoreStarted() {
        referredWorkers.add(null);
        notifyItemChanged(referredWorkers.size() - 1);
    }

    public void loadMoreFinished() {
        int position = referredWorkers.size();
        referredWorkers.remove(position - 1);
        notifyItemChanged(position - 1);
    }

    private ReferredWorker getItemAt(int position) {
        return referredWorkers.get(position);
    }

    class BottomProgressHolder extends RecyclerView.ViewHolder {

        BottomProgressHolder(View itemView) {
            super(itemView);
        }
    }

    class ReferredWorkerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_text)
        TextView txtText;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.txt_is_valid)
        TextView txtIsValid;

        ReferredWorkerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ReferredWorker referredWorker) {

            txtText.setText("123");
            txtText.setText("456");
            txtIsValid.setText("678");

         /*   if (notification.getType().equals(Constants.NOTIFICATION_PAYOUT)) {
                ivNotification.setImageResource(R.drawable.ic_wallet);
                txtText.setText(notification.getText());
                txtTypeText.setText(notification.getSubtext());
                txtTypeText.setTextColor(ContextCompat.getColor(txtTypeText.getContext(), R.color.text_green));
                txtDate.setText(Utils.dateFromat(notification.getDate(), Constants.DATE_FORMAT_DASHES));
            } else if (notification.getType().equals(Constants.NOTIFICATION_REFERRAL_VALIDATION)) {
                ivNotification.setImageResource(R.drawable.ic_valid_account);
                //TODO: init HTML parsing for getText
                txtText.setText(notification.getText());
                txtTypeText.setText(notification.getSubtext());
                txtTypeText.setTextColor(ContextCompat.getColor(txtTypeText.getContext(), R.color.text_blue));
                txtDate.setText(Utils.dateFromat(notification.getDate(), Constants.DATE_FORMAT_DASHES));
            }*/
        }
    }
}
