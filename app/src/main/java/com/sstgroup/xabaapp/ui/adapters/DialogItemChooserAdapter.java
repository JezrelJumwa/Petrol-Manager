package com.sstgroup.xabaapp.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;


public class DialogItemChooserAdapter extends RecyclerView.Adapter<DialogItemChooserAdapter.DialogItemViewHolder> {
    private final List<String> userSelectedItems;
    private Context context;
    private List<String> selectedItems;
    private List<String> dialogItems;
    private final PublishSubject<String> onClickSubject = PublishSubject.create();

    private final boolean isSingleChoice;


    public static class DialogItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.grp_item_dialog)
        LinearLayout grpItemDialog;
        @BindView(R.id.img_tick)
        ImageView imgTick;
        @BindView(R.id.txt_item_name)
        TextView txtItemName;
        @BindView(R.id.tv_registered)
        TextView txtRegistered;

        public DialogItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public DialogItemChooserAdapter(Context context, List<String> dialogItems, List<String> selectedItems, boolean isSingleChoice, List<String> userSelectedItems) {
        this.dialogItems = dialogItems;
        this.context = context;
        this.selectedItems = selectedItems;
        this.isSingleChoice = isSingleChoice;
        this.userSelectedItems = userSelectedItems;
    }

    public DialogItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog, parent, false);
        return new DialogItemViewHolder(itemView);
    }

    public void onBindViewHolder(final DialogItemViewHolder holder, int position) {
        final String dialogItem = dialogItems.get(position);

        holder.txtItemName.setText(dialogItem);

        if (!isSingleChoice) {
            holder.imgTick.setImageResource(R.drawable.check);
        }

        if (selectedItems.contains(dialogItem)) {
            selectDialogItem(holder);
        }

        if (userSelectedItems.contains(dialogItem)){
            bindUserSelectedItems(holder);
        }

        holder.grpItemDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.txtRegistered.getVisibility() == View.GONE){
                    selectDialogItem(holder);
                    onClickSubject.onNext(dialogItem);
                }
            }
        });
    }

    private void bindUserSelectedItems(DialogItemViewHolder holder) {
        holder.imgTick.setVisibility(View.INVISIBLE);
        holder.txtRegistered.setVisibility(View.VISIBLE);
        int bgGraySelected = ContextCompat.getColor(context, R.color.white);
        holder.grpItemDialog.setBackgroundColor(bgGraySelected);
    }

    private void selectDialogItem(DialogItemViewHolder holder) {
        holder.txtRegistered.setVisibility(View.GONE);

        if (holder.imgTick.getVisibility() == View.INVISIBLE) {
            holder.imgTick.setVisibility(View.VISIBLE);
            int bgGraySelected = ContextCompat.getColor(context, R.color.white);
            holder.grpItemDialog.setBackgroundColor(bgGraySelected);
        } else {
            holder.imgTick.setVisibility(View.INVISIBLE);
            int white = ContextCompat.getColor(context, R.color.white);
            holder.grpItemDialog.setBackgroundColor(white);
        }
    }

    public int getItemCount() {
        return dialogItems.size();
    }

    public Observable<String> getPositionClicks() {
        return onClickSubject.asObservable();
    }
}
