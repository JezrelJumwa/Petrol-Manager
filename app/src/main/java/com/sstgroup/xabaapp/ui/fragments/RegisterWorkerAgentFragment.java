package com.sstgroup.xabaapp.ui.fragments;


import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterWorkerAgentFragment extends BaseFragment {

    @BindView(R.id.national_id)
    EditText mEditTextNationalId;
    @BindView(R.id.confirm_national_id)
    EditText mEditTextConfirmNationalId;
    @BindView(R.id.phone_number)
    EditText mEditTextPhoneNumber;
    @BindView(R.id.pin_code)
    EditText mEditTextPinCode;
    @BindView(R.id.confirm_pin_code)
    EditText mEditTextConfirmPinCode;
    @BindView(R.id.referral_code)
    EditText mEditTextReferralCode;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_worker_agent;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.register})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                RegisterConfirmFragment registerConfirmFragment = new RegisterConfirmFragment();
                activity.openFragment(registerConfirmFragment, true);
                break;
        }
    }
}
