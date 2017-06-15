package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.sstgroup.xabaapp.R;

/**
 * Created by rosenstoyanov on 6/15/17.
 */

public class CommissionLogsFragment extends BaseFragment {

    public static CommissionLogsFragment newInstance() {

        Bundle args = new Bundle();

        CommissionLogsFragment fragment = new CommissionLogsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commission_logs;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }
}
