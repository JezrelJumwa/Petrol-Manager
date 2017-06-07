package com.sstgroup.xabaapp.ui.activities;

import android.support.v7.widget.Toolbar;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;

public class EditProfileActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void init() {
        setupToolbar(mToolbar, R.drawable.arrow_back);
        hasBackArrow = true;
    }
}
