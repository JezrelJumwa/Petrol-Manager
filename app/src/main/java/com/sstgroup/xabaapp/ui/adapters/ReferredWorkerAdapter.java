package com.sstgroup.xabaapp.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.ReferredWorker;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferredWorkerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ReferredWorker> referredWorkers;
    private Context context;

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROGRESS = 1;

    private final String STATUS_ACTIVE = "active";
    private final String STATUS_PENDING = "pending";
    private final String STATUS_INACTIVE = "inactive";

    public ReferredWorkerAdapter(List<ReferredWorker> referredWorkers, Context context) {
        this.referredWorkers = referredWorkers;
        this.context = context;
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
        if (position > 1 && referredWorkers.get(position - 1) == null) {
            referredWorkers.remove(position - 1);
            notifyItemRemoved(position - 1);
        }
    }

    private ReferredWorker getItemAt(int position) {
        return referredWorkers.get(position);
    }

    private class BottomProgressHolder extends RecyclerView.ViewHolder {

        BottomProgressHolder(View itemView) {
            super(itemView);
        }
    }

    class ReferredWorkerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_status)
        ImageView imageViewStatus;
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

            String text = "";
            String textDate = Utils.dateFromat(referredWorker.getCreatedAt(), Constants.DATE_FORMAT_REFERRED_WORKERS);
            String textIsValid = "";

            if (referredWorker.getStatus().equals(STATUS_ACTIVE)) {
                imageViewStatus.setBackgroundColor(ContextCompat.getColor(imageViewStatus.getContext(), R.color.referred_worker_green));
                text = context.getString(R.string.verified) + " - " + referredWorker.getFirstName() + " " + referredWorker.getLastName();
            } else if (referredWorker.getStatus().equals(STATUS_PENDING)) {
                imageViewStatus.setBackgroundColor(ContextCompat.getColor(imageViewStatus.getContext(), R.color.referred_worker_blue));
                text = context.getString(R.string.pending) + " - " + referredWorker.getFirstName() + " " + referredWorker.getLastName();
            } else if (referredWorker.getStatus().equals(STATUS_INACTIVE)) {
                imageViewStatus.setBackgroundColor(ContextCompat.getColor(imageViewStatus.getContext(), R.color.referred_worker_red));
                text = context.getString(R.string.rejected) + " - " + referredWorker.getFirstName() + " " + referredWorker.getLastName();
                textDate += " - ";
                textIsValid = context.getString(R.string.invalid_data);
            }

            txtText.setText(text);
            txtDate.setText(textDate);
            txtIsValid.setText(textIsValid);
        }
    }
}
