package com.sstgroup.xabaapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.BuildConfig;
import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.UserResponse;
import com.sstgroup.xabaapp.models.errors.ErrorLogin;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.activities.ForgottenPinActivity;
import com.sstgroup.xabaapp.ui.activities.HomeActivity;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Encryption;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.NavigationUtils;
import com.sstgroup.xabaapp.utils.Validator;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rosenstoyanov on 6/7/17.
 */

public class LoginFragment extends BaseFragment {
    @BindView(R.id.national_id)
    EditText mEditTextNationalId;
    @BindView(R.id.pin_code)
    EditText mEditTextPinCode;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initFields() {
        if (BuildConfig.DEBUG){
            mEditTextNationalId.setText("1234569870");
            mEditTextPinCode.setText("1234");
        }
    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.log_in, R.id.forgot_pin})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.log_in:
                login();
                break;
            case R.id.forgot_pin:
                Intent intent = new Intent(activity, ForgottenPinActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {

        String nationalId = mEditTextNationalId.getText().toString().trim();
        String pinCode = mEditTextPinCode.getText().toString().trim();

        if (Validator.isEmpty(nationalId)) {
            ToastInterval.showToast(activity, getString(R.string.enter_national_id));
            return;
        }

        if (Validator.isNotNumber(nationalId)) {
            ToastInterval.showToast(activity, getString(R.string.national_id_should_be_a_number));
            return;
        }
        //TODO: uncomment this
        if (nationalId.length() != 10) {
            ToastInterval.showToast(activity, getString(R.string.your_national_id_is_wrong));
            return;
        }

        if (Validator.isEmpty(pinCode)) {
            ToastInterval.showToast(activity, getString(R.string.enter_pin_code));
            return;
        }

        if (Validator.isNotNumber(pinCode)) {
            ToastInterval.showToast(activity, getString(R.string.pin_code_should_be_a_number));
            return;
        }

        if (pinCode.length() != 4) {
            ToastInterval.showToast(activity, getString(R.string.your_pin_code_is_wrong));
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
                    if (!user.getIsPhoneVerified()){
                        activity.openFragment(RegisterConfirmFragment.newInstance(user.getId(), true), true);
                    }
//                    else if (!user.getIsDefaultPin()){
//
//                    }
                    else {
                        XabaApplication.getInstance().setToken(user.getTokenFromWS());
                        xabaDbHelper.insertLoggedUser(activity, user);
                        NavigationUtils.startSingleActivity(activity, HomeActivity.class);
                    }
                } else {
                    ErrorLogin errorLogin = ErrorUtils.parseLoginError(response);
                    if (errorLogin.getClass().equals(Constants.ERROR_STATUS_UNEXPECTED)){
                        ToastInterval.showToast(activity, getString(R.string.something_is_wrong));
                    } else {
                        ToastInterval.showToast(activity, errorLogin.getError());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                if (t instanceof IOException){
                    //Add your code for displaying no network connection error
                    ToastInterval.showToast(activity, getString(R.string.check_your_internet_connection));
                } else {
                    ToastInterval.showToast(activity, getString(R.string.something_is_wrong));
                }
            }
        });
    }
}
