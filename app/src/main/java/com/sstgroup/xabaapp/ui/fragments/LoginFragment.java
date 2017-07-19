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
import com.sstgroup.xabaapp.models.errors.ErrorMapListString;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.activities.EditPinActivity;
import com.sstgroup.xabaapp.ui.activities.ForgottenPinActivity;
import com.sstgroup.xabaapp.ui.activities.HomeActivity;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Encryption;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.NavigationUtils;
import com.sstgroup.xabaapp.utils.Preferences;
import com.sstgroup.xabaapp.utils.Utils;
import com.sstgroup.xabaapp.utils.Validator;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        if (BuildConfig.DEBUG) {
            mEditTextNationalId.setText("22330828");
            mEditTextPinCode.setText("1234");
        }

//        user with notification data
//        if (BuildConfig.DEBUG) {
//            mEditTextNationalId.setText("235720441");
//            mEditTextPinCode.setText("1234");
//        }
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

    @OnClick(R.id.txt_visit_url)
    public void openUrl() {
        Utils.openUrl(Constants.VISIT_XABA_URL, activity);
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

        if (Validator.isNotCorrectNationalIdSize(nationalId)) {
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

        if (Validator.isNotCorrectPinCodeSize(pinCode)) {
            ToastInterval.showToast(activity, getString(R.string.your_pin_code_is_wrong));
            return;
        }

        nationalId = Encryption.encryptionRSA(nationalId);

        Call<UserResponse> call = RestClient.getService().login(XabaApplication.getInstance().getLanguageCode(),
                Constants.AGENT_APP_VALUE, nationalId,
                pinCode);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getUser();
                    if (user.getId() > 0) {
                        Preferences.setLoggedUserId(activity, user.getId());
                    }
                    if (!user.getIsPhoneVerified()) {
                        activity.openFragment(RegisterConfirmFragment.newInstance(user.getId(), true), true);
                    } else if (user.getIsDefaultPin()) {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.TOKEN, user.getTokenFromWS().getValue());
                        bundle.putBoolean(Constants.REGISTER_CONFIRM_STARTED_FROM_LOGIN, true);
                        NavigationUtils.startActivityWithExtra(activity, bundle, EditPinActivity.class);
                    } else {
                        XabaApplication.getInstance().setToken(user.getTokenFromWS());
                        xabaDbHelper.insertLoggedUser(activity, user);
                        NavigationUtils.startSingleActivity(activity, HomeActivity.class);
                    }
                } else {
                    //TODO: init correct way of parsing errors because server returns different models of errors and could not be correctly parsed
                    ErrorMapListString errorMapListString = ErrorUtils.parseLoginError(response);
                    if (errorMapListString.getStatus().equals(Constants.ERROR_STATUS_UNEXPECTED)) {
                        ToastInterval.showToast(activity, getString(R.string.something_is_wrong));
                    } else {
                        if (errorMapListString.getError().containsKey("national_idn")){
                            ToastInterval.showToast(activity, getString(R.string.invalid_user));
                        } else if (errorMapListString.getError().containsKey("pin")) {
                            ToastInterval.showToast(activity, getString(R.string.incorrect_pin));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Utils.onFailureUtils(activity, t);
            }
        });
    }
}
