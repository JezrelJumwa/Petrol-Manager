package com.sstgroup.xabaapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.fragments.LoginFragment;
import com.sstgroup.xabaapp.ui.fragments.RegisterConfirmFragment;
import com.sstgroup.xabaapp.utils.Constants;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        processIntent(getIntent());
    }

    @Override
    protected void init() {
        openFragment(LoginFragment.newInstance(), false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        Long userId = intent.getLongExtra(Constants.USER_ID_KEY, 0);
        String activationCode = intent.getStringExtra(Constants.ACTIVATION_CODE_KEY);
        if (userId > 0 && activationCode != null && activationCode.length() > 0) {
            openFragment(RegisterConfirmFragment.newInstance(userId, false, activationCode), true);
        }
    }
}
