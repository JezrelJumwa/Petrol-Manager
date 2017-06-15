package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;


/**
 * Created by rosenstoyanov on 6/15/17.
 */

public class ReportsFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tab_bar)
    TabLayout tabLayout;

    public static ReportsFragment newInstance() {

        Bundle args = new Bundle();

        ReportsFragment fragment = new ReportsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reports;
    }

    @Override
    protected void initFields() {
        TabLayout.Tab commissionTab = tabLayout.newTab().setText(getString(R.string.commission_logs));
        TabLayout.Tab referredWorkers = tabLayout.newTab().setText(R.string.referred_workers);

        tabLayout.addTab(commissionTab);
        tabLayout.addTab(referredWorkers);
        tabLayout.addOnTabSelectedListener(this);

        commissionTab.select();
    }

    @Override
    protected void initViews(View rootView) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        TextView text = (TextView) tab.getCustomView();
//        if (text != null)
//            text.setTypeface(null, Typeface.BOLD);
        int position = tab.getPosition();

        if (position == 0) {
            openFragment(CommissionLogsFragment.newInstance(), false);
        } else {
            openFragment(ReferredWorkersFragment.newInstance(), false);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
//        TextView text = (TextView) tab.getCustomView();
//        if (text != null)
//            text.setTypeface(null, Typeface.NORMAL);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
