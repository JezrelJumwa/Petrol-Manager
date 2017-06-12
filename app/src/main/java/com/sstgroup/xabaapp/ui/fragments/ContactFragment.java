package com.sstgroup.xabaapp.ui.fragments;


import android.os.Bundle;
import android.view.View;

import com.sstgroup.xabaapp.R;

public class ContactFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    public static ContactFragment newInstance() {

        Bundle args = new Bundle();

        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
