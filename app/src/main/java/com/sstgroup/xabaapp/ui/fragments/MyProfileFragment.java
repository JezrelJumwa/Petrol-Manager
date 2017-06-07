package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.ui.activities.EditProfileActivity;
import com.sstgroup.xabaapp.ui.adapters.MyProfileAdapter;
import com.sstgroup.xabaapp.utils.NavigationUtils;

import butterknife.BindView;

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

    @Override
    public void onItemClick(int id) {
        switch (id){
            case R.id.txt_change_pin:
                break;
            case R.id.btn_edit_profile:
                NavigationUtils.startActivity(activity, EditProfileActivity.class);
                break;
            case R.id.txt_delete_account:
                break;
        }
    }
}
