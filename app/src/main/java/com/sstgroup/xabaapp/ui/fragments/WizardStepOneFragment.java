package com.sstgroup.xabaapp.ui.fragments;


import android.content.Intent;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;

import butterknife.OnClick;

public class WizardStepOneFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wizard_step_one;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.next, R.id.log_in})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                WizardStepTwoFragment wizardStepTwoFragment = new WizardStepTwoFragment();
                activity.openFragment(wizardStepTwoFragment, true);
                break;
            case R.id.log_in:
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
