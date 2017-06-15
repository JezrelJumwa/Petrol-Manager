package com.sstgroup.xabaapp.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.db.DatabaseHelper;
import com.sstgroup.xabaapp.ui.activities.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity activity;
    private Unbinder unbinder;
    protected DatabaseHelper xabaDbHelper;

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
        xabaDbHelper = DatabaseHelper.getInstance(XabaApplication.getInstance());
    }

    public void openFragment(Fragment toOpen, boolean addToBackStack) {
        if (activity != null) {
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.grp_container, toOpen);
            if (addToBackStack) {
                ft.addToBackStack(null);
            }
            ft.commit();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
        activity = null;
    }
}
