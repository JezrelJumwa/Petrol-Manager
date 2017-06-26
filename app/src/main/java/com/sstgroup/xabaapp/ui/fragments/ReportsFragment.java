package com.sstgroup.xabaapp.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.adapters.CustomViewPageAdapter;

import butterknife.BindView;

public class ReportsFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tab_bar)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

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
//        TabLayout.Tab commissionTab = tabLayout.newTab().setText(getString(R.string.commission_logs));
//        TabLayout.Tab referredWorkers = tabLayout.newTab().setText(R.string.referred_workers);
//
//        tabLayout.addTab(commissionTab);
//        tabLayout.addTab(referredWorkers);
        tabLayout.addOnTabSelectedListener(this);
        CustomViewPageAdapter adapter = new CustomViewPageAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(CommissionLogsFragment.newInstance(), getString(R.string.commission_logs));
        adapter.addFragment(ReferredWorkersFragment.newInstance(), getString(R.string.referred_workers));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).select();
    }

    @Override
    protected void initViews(View rootView) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        TextView text = (TextView) tab.getCustomView();
//        if (text != null)
//            text.setTypeface(null, Typeface.BOLD);
        viewPager.setCurrentItem(tab.getPosition());
//        int position = tab.getPosition();
//
//        if (position == 0) {
//            openFragment(CommissionLogsFragment.newInstance(), false);
//        } else {
//            openFragment(ReferredWorkersFragment.newInstance(), false);
//        }
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
