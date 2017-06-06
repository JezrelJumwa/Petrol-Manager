package com.sstgroup.xabaapp.ui.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.ActivationCodeResponse;
import com.sstgroup.xabaapp.models.SendNewActivationCodeResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Validator;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RegisterConfirmFragment extends BaseFragment {
    String userId = "";

    @BindView(R.id.activation_code)
    EditText mEditTextActivationCode;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_confirm;
    }

    @Override
    protected void initFields() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getString(Constants.WORKER_ID);
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

                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.WORKER_ID, userId);

                    RegisterCompleteFragment registerCompleteFragment = new RegisterCompleteFragment();
                    registerCompleteFragment.setArguments(bundle);
                    activity.openFragment(registerCompleteFragment, true);
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
