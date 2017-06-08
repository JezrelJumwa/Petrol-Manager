package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.sstgroup.xabaapp.R;

/**
 * Created by julianlubenov on 6/8/17.
 */

public class DashboardFragment extends BaseFragment {

    public static DashboardFragment newInstance() {

        Bundle args = new Bundle();

        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }
}
