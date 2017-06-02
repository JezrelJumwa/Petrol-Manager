package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.sstgroup.xabaapp.R;

/**
 * Created by rosenstoyanov on 6/2/17.
 */

public class MyProfileFragment extends BaseFragment {

    public static final String TAG = MyProfileFragment.class.getSimpleName();

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

    }

    @Override
    protected void initViews(View rootView) {

    }
}
