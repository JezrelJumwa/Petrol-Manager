package com.sstgroup.xabaapp.ui.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.ActivationCodeResponse;
import com.sstgroup.xabaapp.models.SendNewActivationCodeResponse;
import com.sstgroup.xabaapp.models.errors.ErrorLogin;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.Validator;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RegisterConfirmFragment extends BaseFragment {
    private Long userId;
    private Boolean startedFromLogin;

    @BindView(R.id.activation_code)
    EditText mEditTextActivationCode;

    public static RegisterConfirmFragment newInstance(long userId, boolean startedFromLogin) {

        Bundle args = new Bundle();
        args.putLong(Constants.WORKER_ID, userId);
        args.putBoolean(Constants.REGISTER_CONFIRM_STARTED_FROM_LOGIN, startedFromLogin);
        RegisterConfirmFragment fragment = new RegisterConfirmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_confirm;
    }

    @Override
    protected void initFields() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getLong(Constants.WORKER_ID, -1);
            startedFromLogin = bundle.getBoolean(Constants.REGISTER_CONFIRM_STARTED_FROM_LOGIN);
        }
    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.verify, R.id.resend_pin_sms})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.verify:
                verify();
                break;
            case R.id.resend_pin_sms:
                resendPinSms();
                break;
        }
    }

    private void verify() {

        String activationCode = mEditTextActivationCode.getText().toString();

        if (Validator.isEmpty(activationCode)) {
            Toast.makeText(activity, getString(R.string.enter_activation_code), Toast.LENGTH_SHORT).show();
            return;
        }

        Call<ActivationCodeResponse> call = RestClient.getService().sendActivationCode(Constants.AGENT_APP_VALUE, activationCode);
        call.enqueue(new Callback<ActivationCodeResponse>() {
            @Override
            public void onResponse(Call<ActivationCodeResponse> call, Response<ActivationCodeResponse> response) {
                if (response.isSuccessful()) {
                    response.body().getStatus();

                    if (startedFromLogin){
                        activity.onBackPressed();
                    } else {
                        activity.openFragment(RegisterCompleteFragment.newInstance(userId), true);
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
            public void onFailure(Call<ActivationCodeResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }

    private void resendPinSms() {
        Call<SendNewActivationCodeResponse> call = RestClient.getService().sendSmsWithNewActivationCode(Constants.AGENT_APP_VALUE, userId);
        call.enqueue(new Callback<SendNewActivationCodeResponse>() {
            @Override
            public void onResponse(Call<SendNewActivationCodeResponse> call, Response<SendNewActivationCodeResponse> response) {
                if (response.isSuccessful()) {
                    response.body().getStatus();
                    Toast.makeText(activity, getString(R.string.new_activation_code_is_sent_via_sms), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SendNewActivationCodeResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}
