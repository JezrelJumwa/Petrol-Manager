package com.sstgroup.xabaapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.County;
import com.sstgroup.xabaapp.models.Profession;
import com.sstgroup.xabaapp.models.SubCounty;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rosenstoyanov on 6/8/17.
 */

public class EditProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private County selectedCounty;
    private SubCounty selectedSubCounty;
    private ArrayList<Profession> professions;
    private ClickCallbacks clickCallbacks;

    public EditProfileAdapter(ClickCallbacks clickCallbacks, ArrayList<Profession> professions, County county, SubCounty subCounty) {
        this.context = XabaApplication.getInstance().getApplicationContext();
        this.clickCallbacks = clickCallbacks;
        this.professions = professions;
        this.selectedCounty = county;
        this.selectedSubCounty = subCounty;
    }

    public ArrayList<Profession> getProfessions() {
        return professions;
    }

    public County getSelectedCounty() {
        return selectedCounty;
    }

    public SubCounty getSelectedSubCounty() {
        return selectedSubCounty;
    }

    public void setSelectedCounty(County selectedCounty) {
        this.selectedCounty = selectedCounty;
        notifyItemChanged(0);
        this.selectedSubCounty = null;
        notifyItemChanged(1);
    }

    public void setSelectedSubCounty(SubCounty selectedSubCounty) {
        this.selectedSubCounty = selectedSubCounty;
        notifyItemChanged(1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.row_edit_profile_country_sub:
                return new RowCountySub(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_profile_country_sub, parent, false));
            case R.layout.row_edit_profile_profession:
                return new RowEditProfession(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_profile_profession, parent, false));
            case R.layout.row_edit_profile_footer:
                return new RowFooter(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_profile_footer, parent, false));
        }

        throw new IllegalStateException("Unknown item type: " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RowCountySub) {
            ((RowCountySub) holder).bind(position);
        } else if (holder instanceof RowEditProfession) {
            ((RowEditProfession) holder).bind(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 2) {
            return R.layout.row_edit_profile_country_sub;
        }
        if (position > 1 && position <= professions.size() + 1) {
            return R.layout.row_edit_profile_profession;
        }
        return R.layout.row_edit_profile_footer;
    }

    @Override
    public int getItemCount() {
        return 3 + professions.size();
    }

    class RowCountySub extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_selector_title)
        TextView txtTitle;
        @BindView(R.id.txt_county_selection)
        TextView txtSelectedItem;
        @BindView(R.id.view_bottom)
        View view;

        RowCountySub(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            if (position == 0) {
                view.setVisibility(View.GONE);

                txtTitle.setText(context.getString(R.string.county));
                if (selectedCounty == null)
                    txtSelectedItem.setText(context.getString(R.string.select_county));
                else
                    txtSelectedItem.setText(selectedCounty.getName());
            } else if (position == 1) {
                view.setVisibility(View.VISIBLE);

                txtTitle.setText(context.getString(R.string.sub_county));
                if (selectedSubCounty == null)
                    txtSelectedItem.setText(context.getString(R.string.select_sub_county));
                else
                    txtSelectedItem.setText(selectedSubCounty.getName());
            }
        }

        @OnClick(R.id.grp_county)
        public void onClick(View view) {
            clickCallbacks.onItemClick(view, getAdapterPosition());
        }
    }

    public void addProfession() {
        int professionsSize = professions.size();
        if (professionsSize < 3) {
            professions.add(new Profession());
            notifyItemInserted(2 + professionsSize + 1);
        } else {
            ToastInterval.showToast(context, "Can not add more than 3 professions.");
        }
    }

    public void removeProfessionAt(int position) {
        professions.remove(position - 2);
        notifyItemRemoved(position);
    }

    class RowEditProfession extends RecyclerView.ViewHolder {
        @BindView(R.id.grp_industry_wrap)
        LinearLayout grpIndustry;
        @BindView(R.id.grp_category_wrap)
        LinearLayout grpCategory;
        @BindView(R.id.iv_profession_down_arrow)
        ImageView ivProfessionArrow;
        @BindView(R.id.txt_profession_selection)
        TextView txtProfessionSelection;

        public RowEditProfession(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            Profession profession = professions.get(position - 2);

            if (TextUtils.isEmpty(profession.getName())) {
                txtProfessionSelection.setText(context.getString(R.string.select_profession));
                grpIndustry.setVisibility(View.VISIBLE);
                grpCategory.setVisibility(View.VISIBLE);
                ivProfessionArrow.setVisibility(View.VISIBLE);
            } else {
                txtProfessionSelection.setText(profession.getName());
                grpIndustry.setVisibility(View.GONE);
                grpCategory.setVisibility(View.GONE);
                ivProfessionArrow.setVisibility(View.GONE);
            }
        }

        @OnClick({R.id.remove_profession_one, R.id.grp_industry, R.id.grp_category})
        public void onClick(View view) {
            clickCallbacks.onItemClick(view, getAdapterPosition());
        }
    }

    class RowFooter extends RecyclerView.ViewHolder {

        public RowFooter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.add_another_profession, R.id.btn_save})
        public void onClick(View view) {
            clickCallbacks.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ClickCallbacks {
        void onItemClick(View view, int position);
    }
}
