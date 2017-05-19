package com.sstgroup.xabaapp.ui.activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                login();
                break;
            case R.id.forgot_pin:
                Intent intent = new Intent(this, ForgottenPinActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {

        String nationalId = mEditTextNationalId.getText().toString().trim();
        String pinCode = mEditTextPinCode.getText().toString().trim();

        Call<Object> call = RestClient.getService().login(Constants.AGENT_APP_VALUE, nationalId,
                pinCode);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    response.toString();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
            }
        });
    }
}
