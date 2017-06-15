package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.sstgroup.xabaapp.R;

/**
 * Created by rosenstoyanov on 6/15/17.
 */

public class ReferredWorkersFragment extends BaseFragment {

    public static ReferredWorkersFragment newInstance() {

        Bundle args = new Bundle();

        ReferredWorkersFragment fragment = new ReferredWorkersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_referred_workers;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }
}
