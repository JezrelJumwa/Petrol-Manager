package com.sstgroup.xabaapp.ui.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.PinResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Encryption;
import com.sstgroup.xabaapp.utils.Validator;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

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
                resetPinAndSendSMS();
                break;
            case R.id.do_you_remember_it:
                finish();
                break;
        }
    }

    private void resetPinAndSendSMS() {

        String nationalId = mEditTextNationalId.getText().toString();

        if (Validator.isEmpty(nationalId)) {
            Toast.makeText(this, getResources().getString(R.string.enter_national_id), Toast.LENGTH_SHORT).show();
            return;
        }

        if (Validator.isNotNumber(nationalId)) {
            Toast.makeText(this, getResources().getString(R.string.national_id_should_be_a_number), Toast.LENGTH_SHORT).show();
            return;
        }

        if (nationalId.length() != 10) {
            Toast.makeText(this, getResources().getString(R.string.your_national_id_is_wrong), Toast.LENGTH_SHORT).show();
            return;
        }

        nationalId = Encryption.encryptionRSA(nationalId);

        Call<PinResponse> call = RestClient.getService().resetPin(Constants.AGENT_APP_VALUE, nationalId);
        call.enqueue(new Callback<PinResponse>() {
            @Override
            public void onResponse(Call<PinResponse> call, Response<PinResponse> response) {
                if (response.isSuccessful()) {
                    response.body().getStatus();
                    Toast.makeText(ForgottenPinActivity.this, getResources().getString(R.string.pin_is_reset), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PinResponse> call, Throwable t) {
                Toast.makeText(ForgottenPinActivity.this, getResources().getString(R.string.something_is_wrong), Toast.LENGTH_SHORT).show();
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}
