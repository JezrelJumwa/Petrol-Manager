package com.sstgroup.xabaapp.ui.activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.db.DatabaseHelper;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.UserResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Encryption;
import com.sstgroup.xabaapp.utils.NavigationUtils;
import com.sstgroup.xabaapp.utils.Preferences;
import com.sstgroup.xabaapp.utils.Validator;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

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

        if (Validator.isEmpty(nationalId)) {
            Toast.makeText(this, getResources().getString(R.string.enter_national_id), Toast.LENGTH_SHORT).show();
            return;
        }

        if (Validator.isNotNumber(nationalId)) {
            Toast.makeText(this, getResources().getString(R.string.national_id_should_be_a_number), Toast.LENGTH_SHORT).show();
            return;
        }

//        if (nationalId.length() != 10) {
//            Toast.makeText(this, getResources().getString(R.string.your_national_id_is_wrong), Toast.LENGTH_SHORT).show();
//            return;
//        }

        if (Validator.isEmpty(pinCode)) {
            Toast.makeText(this, getResources().getString(R.string.enter_pin_code), Toast.LENGTH_SHORT).show();
            return;
        }

        if (Validator.isNotNumber(pinCode)) {
            Toast.makeText(this, getResources().getString(R.string.pin_code_should_be_a_number), Toast.LENGTH_SHORT).show();
            return;
        }

        if (pinCode.length() != 4) {
            Toast.makeText(this, getResources().getString(R.string.your_pin_code_is_wrong), Toast.LENGTH_SHORT).show();
            return;
        }

        nationalId = Encryption.encryptionRSA(nationalId);

        Call<UserResponse> call = RestClient.getService().login(Constants.AGENT_APP_VALUE, nationalId,
                pinCode);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getUser();
                    Preferences.setLoggedUserId(LoginActivity.this, user.getId());
                    databaseHelper.insertOrReplaceUser(user);
                    NavigationUtils.startSingleActivity(LoginActivity.this, HomeActivity.class);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.something_is_wrong), Toast.LENGTH_SHORT).show();
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}
