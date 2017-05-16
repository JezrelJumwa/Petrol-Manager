package com.sstgroup.xabaapp.ui.activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.national_id)
    EditText mEditTextNationalId;
    @BindView(R.id.pin_code)
    EditText mEditTextPinCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
    }

    @OnClick({R.id.log_in, R.id.forgot_pin})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.log_in:
                //
                break;
            case R.id.forgot_pin:
                Intent intent = new Intent(this, ForgottenPinActivity.class);
                startActivity(intent);
                break;
        }
    }
}
