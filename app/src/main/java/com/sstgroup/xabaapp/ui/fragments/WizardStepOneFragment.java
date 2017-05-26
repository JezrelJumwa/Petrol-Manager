package com.sstgroup.xabaapp.ui.fragments;


import android.content.Intent;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.PinResponse;
import com.sstgroup.xabaapp.models.SignupRequestModel;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.UserResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.utils.Constants;

import java.util.ArrayList;

import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class WizardStepOneFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wizard_step_one;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.next, R.id.log_in})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                WizardStepTwoFragment wizardStepTwoFragment = new WizardStepTwoFragment();
                activity.openFragment(wizardStepTwoFragment, true);
                break;
            case R.id.log_in:
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void register() {

        ArrayList<Integer> professions = new ArrayList<>();
        professions.add(77);
        professions.add(4);

        SignupRequestModel signupRequestModel = new SignupRequestModel("1234554781", "1234", "+254701234567", "sw-KE", 1, 42, 51, professions, 12, Constants.AGENT_APP_VALUE);

        RequestBody body = RequestBody.create(MediaType.parse("text"), signupRequestModel.generateStringForRequest());
        Call<Object> call = RestClient.getService().register(body);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }

    private void getWorkerData() {

        Call<UserResponse> call = RestClient.getService().getWorkerData(Constants.AGENT_APP_VALUE, "ade43d58783686752516ecff2e6393a480b657d5dbf58333c6e438e90cb0c00a212e876ec9e67f6197b4b658545d63158c5b4e5e5d2422ae29a05969f0fc65ef");
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getUser();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }

    private void changePIN() {

        Call<PinResponse> call = RestClient.getService().changePin(Constants.AGENT_APP_VALUE, "c6aa59e7de501f559089fcc31b2a57319816fc042035425e1a1fe29bfa8fd77b3204955ba9237a12fae168dc96850a29b34d435a1dce5665e3b6f58e731aac6f", "1234", "1234");
        call.enqueue(new Callback<PinResponse>() {
            @Override
            public void onResponse(Call<PinResponse> call, Response<PinResponse> response) {
                if (response.isSuccessful()) {
                    response.body().getStatus();
                }
            }

            @Override
            public void onFailure(Call<PinResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}
