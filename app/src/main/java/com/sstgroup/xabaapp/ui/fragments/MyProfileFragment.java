package com.sstgroup.xabaapp.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.ui.activities.EditProfileActivity;
import com.sstgroup.xabaapp.ui.adapters.MyProfileAdapter;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.NavigationUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rosenstoyanov on 6/2/17.
 */

public class MyProfileFragment extends BaseFragment implements MyProfileAdapter.Callbacks {

    public static final String TAG = MyProfileFragment.class.getSimpleName();

    @BindView(R.id.rv_profile)
    RecyclerView mRvMyProfile;

    public static MyProfileFragment newInstance() {

        Bundle args = new Bundle();

        MyProfileFragment fragment = new MyProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_profile;
    }

    @Override
    protected void initFields() {
        mRvMyProfile.setLayoutManager(new LinearLayoutManager(activity));
        User loggedUser = xabaDbHelper.getLoggedUser(activity);
        loggedUser.refresh();
        MyProfileAdapter adapter = new MyProfileAdapter(loggedUser, this);
        mRvMyProfile.setAdapter(adapter);
    }

    @Override
    protected void initViews(View rootView) {

    }

    private void showDeleteAccountDialog(){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_delete_account);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_white_rect);

        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnDelete = (Button) dialog.findViewById(R.id.btn_delete);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastInterval.showToast(activity, "Delete Clicked");
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public void onItemClick(int id) {
        switch (id){
            case R.id.txt_change_pin:
                break;
            case R.id.btn_edit_profile:
                NavigationUtils.startActivity(activity, EditProfileActivity.class);
                break;
            case R.id.txt_delete_account:
                showDeleteAccountDialog();
                break;
        }
    }
}
