package com.sstgroup.xabaapp.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sstgroup.xabaapp.ui.activities.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity activity;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initFields();
        initViews(rootView);

        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initFields();

    protected abstract void initViews(View rootView);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
        activity = null;
    }
}
