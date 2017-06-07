package com.sstgroup.xabaapp.ui.activities;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.fragments.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        openFragment(LoginFragment.newInstance(), false);
    }
}
