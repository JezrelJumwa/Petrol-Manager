package com.sstgroup.xabaapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.County;
import com.sstgroup.xabaapp.models.Profession;
import com.sstgroup.xabaapp.models.SubCounty;
import com.sstgroup.xabaapp.utils.Validator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private County selectedCounty;
    private SubCounty selectedSubCounty;
    private ArrayList<Profession> professions;
    private ClickCallbacks clickCallbacks;
    private boolean removeAddProfessionButton;

    private String selectedPrograms = "";

    private String phoneNumber;

    public EditProfileAdapter(ClickCallbacks clickCallbacks, ArrayList<Profession> professions,
                              County county, SubCounty subCounty, String phoneNumber, String selectedPrograms) {
        this.context = XabaApplication.getInstance().getApplicationContext();
        this.clickCallbacks = clickCallbacks;
        this.professions = professions;
        this.selectedCounty = county;
        this.selectedSubCounty = subCounty;
        this.phoneNumber = phoneNumber;
        this.selectedPrograms = selectedPrograms;

        if (professions.size() >= 3) {
            removeAddProfessionButton = true;
        }
    }

    public Profession getProfessionAtPosition(int position) {
        return professions.get(position - 4);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setSelectedCounty(County selectedCounty) {
        if (selectedCounty.getCountyId() != this.selectedSubCounty.getCountyId()) {
            this.selectedCounty = selectedCounty;
            notifyItemChanged(1);
            this.selectedSubCounty = null;
            notifyItemChanged(2);
        }
    }

    public void setSelectedSubCounty(SubCounty selectedSubCounty) {
        this.selectedSubCounty = selectedSubCounty;
        notifyItemChanged(2);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.row_edit_phone:
                return new RowPhone(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_phone, parent, false));
            case R.layout.row_edit_profile_country_sub:
                return new RowCountySub(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_profile_country_sub, parent, false));
            case R.layout.row_edit_profile_profession:
                return new RowEditProfession(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_profile_profession, parent, false));
            case R.layout.row_edit_profile_footer:
                return new RowFooter(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_profile_footer, parent, false));
            case R.layout.row_edit_program:
                return new RowPrograms(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_edit_program, parent, false));
        }

        throw new IllegalStateException("Unknown item type: " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RowPhone) {
            ((RowPhone) holder).bind(position);
        } else if (holder instanceof RowCountySub) {
            ((RowCountySub) holder).bind(position);
        } else if (holder instanceof RowEditProfession) {
            ((RowEditProfession) holder).bind(position);
        } else if (holder instanceof RowFooter) {
            ((RowFooter) holder).bind();
        } else if (holder instanceof RowPrograms) {
            ((RowPrograms) holder).bind();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.row_edit_phone;
        } else if (position < 3) {
            return R.layout.row_edit_profile_country_sub;
        } else if (position == 3) {
            return R.layout.row_edit_program;
        } else if (position <= professions.size() + 3) {
            return R.layout.row_edit_profile_profession;
        }
        return R.layout.row_edit_profile_footer;
    }

    @Override
    public int getItemCount() {
        return 5 + professions.size();
    }

    public void updateProfession(int position, Profession profession) {
        profession.setNew(true);
        professions.set(position - 4, profession);
        notifyItemChanged(position);
    }

    public void updateProgram(int position, String selectedItems) {
        selectedPrograms = selectedItems;
        notifyItemChanged(position);
    }

    class RowPhone extends RecyclerView.ViewHolder {
        @BindView(R.id.phone_title)
        TextView phoneTitle;

        @BindView(R.id.phone_number)
        EditText phoneNumber;

        public RowPhone(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            phoneNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    EditProfileAdapter.this.phoneNumber = RowPhone.this.phoneNumber.getText().toString();
                }
            });
        }

        void bind(int position) {
            phoneNumber.setText(EditProfileAdapter.this.phoneNumber);
        }

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
            if (position == 1) {
                view.setVisibility(View.GONE);

                txtTitle.setText(context.getString(R.string.county));
                if (selectedCounty == null)
                    txtSelectedItem.setText(context.getString(R.string.select_county));
                else
                    txtSelectedItem.setText(selectedCounty.getName());
            } else if (position == 2) {
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
        Profession profession = new Profession();
        profession.setNew(true);
        professions.add(profession);
        notifyItemInserted(4 + professionsSize + 1);

        if (professions.size() >= 3) {
            removeAddProfessionButton = true;
            notifyItemChanged(getItemCount());
        }
    }

    public void removeProfessionAt(int position) {
        int size = professions.size();

        professions.remove(position - 4);
        notifyItemRemoved(position);

        if (size >= 3) {
            removeAddProfessionButton = false;
            notifyItemChanged(getItemCount() - 1);
        }
    }

    class RowPrograms extends RecyclerView.ViewHolder {
        @BindView(R.id.row_profile_frame)
        FrameLayout rowProfileFrame;

        @BindView(R.id.txt_program)
        TextView txtProgram;

        public RowPrograms(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            rowProfileFrame.setPadding(0, 0, 0, 20 * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
//            rowTitle.setText(context.getResources().getString(R.string.programs_dot));
            if (selectedPrograms.isEmpty()){
                txtProgram.setText(context.getString(R.string.select_program));
            } else {
                txtProgram.setText(selectedPrograms);
            }
        }

        @OnClick(R.id.row_profile_frame)
        public void onClick(View view) {
            clickCallbacks.onItemClick(view, getAdapterPosition());
        }
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
        @BindView(R.id.txt_category_selection)
        TextView txtCategorySelection;
        @BindView(R.id.txt_industry_selection)
        TextView txtIndustrySelection;

        RowEditProfession(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            Profession profession = professions.get(position - 4);

            if (profession.isNew()) {
                if (profession.getIndustry() == null) {
                    txtIndustrySelection.setText(context.getString(R.string.select_industry));
                } else {
                    txtIndustrySelection.setText(profession.getIndustry().getName());
                }

                if (profession.getCategory() == null) {
                    txtCategorySelection.setText(context.getString(R.string.select_category));
                } else {
                    txtCategorySelection.setText(profession.getCategory().getName());
                }

                if (Validator.isEmpty(profession.getName())) {
                    txtProfessionSelection.setText(context.getString(R.string.select_profession));
                } else {
                    txtProfessionSelection.setText(profession.getName());
                }

                grpIndustry.setVisibility(View.VISIBLE);
                grpCategory.setVisibility(View.GONE);
                ivProfessionArrow.setVisibility(View.VISIBLE);
            } else {
                txtProfessionSelection.setText(profession.getName());
                grpIndustry.setVisibility(View.GONE);
                grpCategory.setVisibility(View.GONE);
                ivProfessionArrow.setVisibility(View.GONE);
            }
        }

        @OnClick({R.id.remove_profession_one, R.id.grp_industry, R.id.grp_category, R.id.grp_profession})
        public void onClick(View view) {
            if (view.getId() == R.id.grp_profession && ivProfessionArrow.getVisibility() == View.GONE)
                return;

            clickCallbacks.onItemClick(view, getAdapterPosition());
        }
    }

    class RowFooter extends RecyclerView.ViewHolder {
        @BindView(R.id.add_another_profession)
        Button btnAddProfession;

        public RowFooter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            if (removeAddProfessionButton) {
                btnAddProfession.setVisibility(View.GONE);
            } else {
                btnAddProfession.setVisibility(View.VISIBLE);
            }
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
