package com.sstgroup.xabaapp.ui.activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgottenPinActivity extends BaseActivity {

    @BindView(R.id.national_id)
    EditText mEditTextNationalId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgotten_pin;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.send_new_pin, R.id.do_you_remember_it})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.send_new_pin:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.do_you_remember_it:
                Intent intent2 = new Intent(this, LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
