package com.sstgroup.xabaapp.ui.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.MessageResponse;
import com.sstgroup.xabaapp.models.errors.ErrorCodeAndMessage;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.Utils;
import com.sstgroup.xabaapp.utils.Validator;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactFragment extends BaseFragment {

    @BindView(R.id.email)
    EditText mEditTextEmail;
    @BindView(R.id.message)
    EditText mEditTextMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    public static ContactFragment newInstance() {

        Bundle args = new Bundle();

        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.send})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                sendMessageToSystem();
                break;
        }
    }

    private void sendMessageToSystem() {

        String email = mEditTextEmail.getText().toString().trim();
        String message = mEditTextMessage.getText().toString().trim();

        if (Validator.isEmpty(email)) {
            ToastInterval.showToast(activity, getString(R.string.enter_email));
            return;
        }

        if (!Validator.isEmailAddress(email)) {
            ToastInterval.showToast(activity, getString(R.string.enter_correct_email));
            return;
        }

        if (Validator.isEmpty(message)) {
            ToastInterval.showToast(activity, getString(R.string.enter_message));
            return;
        }

        activity.showLoader();

        Call<MessageResponse> call = RestClient.getService().sendMessageToSystem(XabaApplication.getInstance().getLanguageCode(),
                Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue(), email, message);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("OK")) {
                        ToastInterval.showToast(activity, activity.getString(R.string.message_is_sent));
                    }
                }  else {
                    ErrorCodeAndMessage error = ErrorUtils.parseErrorCodeMessage(response);
                    ToastInterval.showToast(activity, error.getErrors().getMessage());
                }

                activity.hideLoader();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Utils.onFailureUtils(activity, t);
                activity.hideLoader();
            }
        });
    }
}
