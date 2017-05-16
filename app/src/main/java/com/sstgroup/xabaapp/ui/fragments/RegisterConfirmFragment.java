package com.sstgroup.xabaapp.ui.fragments;


import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterConfirmFragment extends BaseFragment {

    @BindView(R.id.activation_code)
    EditText mEditTextActivationCode;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_confirm;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.verify, R.id.resend_pin_sms})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.verify:
                RegisterCompleteFragment registerCompleteFragment = new RegisterCompleteFragment();
                activity.openFragment(registerCompleteFragment, true);
                break;
            case R.id.resend_pin_sms:
                break;
        }
    }
}
