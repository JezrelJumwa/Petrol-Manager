package com.sstgroup.xabaapp.ui.fragments;


import android.content.Intent;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.ui.activities.RegisterActivity;

import butterknife.OnClick;

public class WizardStepThreeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wizard_step_three;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.register, R.id.log_in})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                Intent intentToRegisterActivity = new Intent(activity, RegisterActivity.class);
                startActivity(intentToRegisterActivity);
                break;
            case R.id.log_in:
                Intent intentToLoginActivity = new Intent(activity, LoginActivity.class);
                startActivity(intentToLoginActivity);
                break;
        }
    }
}
