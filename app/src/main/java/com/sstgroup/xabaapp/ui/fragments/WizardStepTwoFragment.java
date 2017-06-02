package com.sstgroup.xabaapp.ui.fragments;


import android.content.Intent;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.ui.activities.RegisterActivity;

import butterknife.OnClick;

public class WizardStepTwoFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wizard_step_two;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.back, R.id.register, R.id.next, R.id.log_in})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                activity.onBackPressed();
                break;
            case R.id.register:
                Intent intentToRegisterActivity = new Intent(activity, RegisterActivity.class);
                startActivity(intentToRegisterActivity);
                break;
            case R.id.next:
                WizardStepThreeFragment wizardStepThreeFragment = new WizardStepThreeFragment();
                activity.openFragment(wizardStepThreeFragment, true);
                break;
            case R.id.log_in:
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
