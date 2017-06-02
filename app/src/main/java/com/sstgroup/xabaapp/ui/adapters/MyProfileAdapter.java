package com.sstgroup.xabaapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sstgroup.xabaapp.R;

/**
 * Created by rosenstoyanov on 6/2/17.
 */

public class MyProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case R.layout.row_profile_header:
                return new Header(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_profile_header, parent, false));
            case R.layout.row_profile_item:
                return new RowItem(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_profile_item, parent, false));
            case R.layout.row_profile_profesion:
                return new RowProfession(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_profile_footer, parent, false));
            case R.layout.row_profile_footer:
                return new Footer(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_profile_footer, parent, false));
        }
        throw new IllegalStateException("Invalid view type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return R.layout.row_profile_header;
            case 1:
                return R.layout.row_profile_item;
            case 2:
                return R.layout.row_profile_item;
            case 3:
                return R.layout.row_profile_item;
            case 4:
                return R.layout.row_profile_item;
            case 5:
                return R.layout.row_profile_item;
            case 6:
                return R.layout.row_profile_profesion;
            case 7:
                return R.layout.row_profile_profesion;
            case 8:
                return R.layout.row_profile_profesion;
            case 9:
                return R.layout.row_profile_footer;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class Header extends RecyclerView.ViewHolder {

        public Header(View itemView) {
            super(itemView);
        }

        void bind(){

        }
    }

    class RowItem extends RecyclerView.ViewHolder {

        public RowItem(View itemView) {
            super(itemView);
        }

        void bind(int position){

        }
    }

    class RowProfession extends RecyclerView.ViewHolder {

        public RowProfession(View itemView) {
            super(itemView);
        }

        void bind(int position){

        }
    }

    class Footer extends RecyclerView.ViewHolder {

        public Footer(View itemView) {
            super(itemView);
        }

        void bind(){

        }
    }
}
