package com.sstgroup.xabaapp.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import com.sstgroup.xabaapp.db.DatabaseHelper;
import com.sstgroup.xabaapp.models.Profession;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Utils;
import com.sstgroup.xabaapp.utils.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private User loggedUser;
    private Context context;
    private int professionsSize;
    private Callbacks callbacks;
    private DatabaseHelper xabaDbHelper;
    private int size;

    public MyProfileAdapter(User loggedUser, Callbacks callbacks) {
        this.loggedUser = loggedUser;
        this.context = XabaApplication.getInstance().getApplicationContext();
        this.professionsSize = loggedUser.getProfessions().size();
        this.callbacks = callbacks;
        this.xabaDbHelper = DatabaseHelper.getInstance(XabaApplication.getInstance());
        if (loggedUser.getGender() != null)
            this.size = 5;
        else
            this.size = 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.row_profile_header:
                return new RowHeader(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_profile_header, parent, false));
            case R.layout.row_profile_item:
                return new RowItem(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_profile_item, parent, false));
            case R.layout.row_profile_profesion:
                return new RowProfession(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_profile_profesion, parent, false));
            case R.layout.row_profile_footer:
                return new RowFooter(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_profile_footer, parent, false));
        }
        throw new IllegalStateException("Invalid view type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RowHeader) {
            ((RowHeader) holder).bind();
        } else if (holder instanceof RowItem) {
            ((RowItem) holder).bind(position);
        } else if (holder instanceof RowProfession) {
            ((RowProfession) holder).bind(position);
        } else if (holder instanceof RowFooter) {
            ((RowFooter) holder).bind();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (size == 5){
            if (position > 4){
                if (professionsSize + 4 >= position){
                    return R.layout.row_profile_profesion;
                }

                return R.layout.row_profile_footer;
            }
            switch (position) {
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
            }
        } else {
            if (position > 3){
                if (professionsSize + 3 >= position){
                    return R.layout.row_profile_profesion;
                }

                return R.layout.row_profile_footer;
            }
            switch (position) {
                case 0:
                    return R.layout.row_profile_header;
                case 1:
                    return R.layout.row_profile_item;
                case 2:
                    return R.layout.row_profile_item;
                case 3:
                    return R.layout.row_profile_item;
            }
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Override
    public int getItemCount() {
        return size + professionsSize + 1;
    }

    public void updateUser(User loggedUser) {
        this.professionsSize = loggedUser.getProfessions().size();
        this.loggedUser = loggedUser;
        notifyDataSetChanged();
    }

    class RowHeader extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name)
        TextView txtName;

        RowHeader(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            if (Validator.isEmpty(loggedUser.getFirstName()) && Validator.isEmpty(loggedUser.getLastName())){
                txtName.setText(XabaApplication.getInstance().getString(R.string.pending_validation));
            } else {
                txtName.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());
            }
        }
    }

    class RowItem extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_profile_key)
        TextView txtProfileKey;
        @BindView(R.id.txt_profile_detail)
        TextView txtProfileDetail;
        @BindView(R.id.iv_left_icon)
        ImageView ivProfileLeftIcon;
        @BindView(R.id.grp_container)
        LinearLayout grpContainer;

        RowItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            switch (position){
                case 1:
                    txtProfileKey.setText(context.getText(R.string.phone));
                    txtProfileDetail.setText(loggedUser.getPhone());
                    ivProfileLeftIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_phone));
                    break;
                case 2:
                    if (loggedUser.getGender() != null){
                        txtProfileKey.setText(context.getText(R.string.gender));
                        txtProfileDetail.setText(loggedUser.getGender());
                        if (loggedUser.getGender().equalsIgnoreCase(Constants.MALE)){
                            ivProfileLeftIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_genre_male));
                        } else {
                            ivProfileLeftIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_genre_female));
                        }
                        break;
                    }
                case 3:
                    txtProfileKey.setText(context.getText(R.string.county_profile));
                    txtProfileDetail.setText(xabaDbHelper.getCounty(Long.valueOf(loggedUser.getCountyId())).getName());
                    ivProfileLeftIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_county));
                    if ((size == 4 & position == 2) || size == 5){
                        break;
                    }
                case 4:
                    txtProfileKey.setText(context.getText(R.string.sub_county_profile));
                    txtProfileDetail.setText(xabaDbHelper.getSubCounty(Long.valueOf(loggedUser.getSubcountyId())).getName());
                    ivProfileLeftIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_county));
                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) grpContainer.getLayoutParams();
                    params.setMargins(Utils.dpToPx(15f, context), Utils.dpToPx(8f, context), Utils.dpToPx(15f, context), Utils.dpToPx(16f, context));
                    grpContainer.setLayoutParams(params);
                    break;
            }

        }
    }

    class RowProfession extends RecyclerView.ViewHolder {
//        @BindView(R.id.txt_industry)
//        TextView txtIndustry;
//        @BindView(R.id.txt_category)
//        TextView txtCategory;
        @BindView(R.id.txt_profession)
        TextView txtProfession;

        RowProfession(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            Profession profession = loggedUser.getProfessions().get(position - size);
            
            if(profession != null && !TextUtils.isEmpty(profession.getName())){
                txtProfession.setText(profession.getName());
            } else {
                txtProfession.setText("");
            }
        }
    }

    class RowFooter extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_referral_id)
        TextView txtReferralId;
        @BindView(R.id.top_green_view)
        View topGreenView;

        RowFooter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            if (loggedUser.getProfessions().isEmpty())
                topGreenView.setVisibility(View.GONE);
            else
                topGreenView.setVisibility(View.VISIBLE);

            txtReferralId.setText(String.valueOf(loggedUser.getId()));
        }

        @OnClick({R.id.txt_change_pin, R.id.btn_edit_profile, R.id.txt_delete_account})
        void onClick(View view){
            callbacks.onItemClick(view.getId());
        }
    }

    public interface Callbacks {
        void onItemClick(int id);
    }
}
