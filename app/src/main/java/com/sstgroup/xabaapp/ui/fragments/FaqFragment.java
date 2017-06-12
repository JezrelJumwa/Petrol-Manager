package com.sstgroup.xabaapp.ui.fragments;


import android.os.Bundle;
import android.view.View;

import com.sstgroup.xabaapp.R;

public class FaqFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_faq;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    public static FaqFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FaqFragment fragment = new FaqFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
