package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.adapters.MyProfileAdapter;

import butterknife.BindView;

/**
 * Created by rosenstoyanov on 6/2/17.
 */

public class MyProfileFragment extends BaseFragment {

    public static final String TAG = MyProfileFragment.class.getSimpleName();

//    @BindView(R.id.rv_profile)
//    RecyclerView mRvMyProfile;

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
//        mRvMyProfile.setLayoutManager(new LinearLayoutManager(activity));
//        MyProfileAdapter adapter = new MyProfileAdapter();
//        mRvMyProfile.setAdapter(adapter);
    }

    @Override
    protected void initViews(View rootView) {

    }
}
