package com.sstgroup.xabaapp.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.db.DatabaseHelper;
import com.sstgroup.xabaapp.models.Category;
import com.sstgroup.xabaapp.models.Industry;
import com.sstgroup.xabaapp.models.Profession;
import com.sstgroup.xabaapp.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by rosenstoyanov on 6/2/17.
 */

public class MyProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private User loggedUser;
    private Context context;
    private int professionsSize;
    private DatabaseHelper databaseHelper;

    public MyProfileAdapter(User loggedUser) {
        this.loggedUser = loggedUser;
        this.context = XabaApplication.getInstance().getApplicationContext();
        this.professionsSize = loggedUser.getProfessions().size();
        this.databaseHelper = DatabaseHelper.getInstance(XabaApplication.getInstance());
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

        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Override
    public int getItemCount() {
        return 5 + professionsSize + 1;
    }

    class RowHeader extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name)
        TextView txtName;

        public RowHeader(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            txtName.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());
        }
    }

    class RowItem extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_profile_key)
        TextView txtProfileKey;
        @BindView(R.id.txt_profile_detail)
        TextView txtProfileDetail;
        @BindView(R.id.iv_left_icon)
        ImageView ivProfileLeftIcon;


        public RowItem(View itemView) {
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
                    txtProfileKey.setText(context.getText(R.string.genre));
                    txtProfileDetail.setText(loggedUser.getGender());
                    ivProfileLeftIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_genre));
                    break;
                case 3:
                    txtProfileKey.setText(context.getText(R.string.county_profile));
                    txtProfileDetail.setText("1234");
                    ivProfileLeftIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_county));
                    break;
                case 4:
                    txtProfileKey.setText(context.getText(R.string.sub_county_profile));
                    txtProfileDetail.setText("1234");
                    ivProfileLeftIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_round_county));
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

        public RowProfession(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
//            Timber.d("meh");
//            loggedUser.refresh();
//            Profession professionRel = loggedUser.getProfessions().get(position - 6);
//            Long id = professionRel.getProfessionId() != null ? professionRel.getProfessionId() : professionRel.getLoggedUserProfessionId();
//            Profession profession = DatabaseHelper
//                    .getInstance(XabaApplication.getInstance())
//                    .getProfession(id);

            Profession profession = loggedUser.getProfessions().get(position - 5);

//            Category category = databaseHelper.getCategory(profession.getCategoryId());
//            Industry industry = databaseHelper.getIndustry(category.getIndustryId());
//            txtIndustry.setText(profession.getName());
//            txtCategory.setText(category.getName());
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

        public RowFooter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind() {
            txtReferralId.setText(loggedUser.getId().toString());
        }
    }
}
