package com.sstgroup.xabaapp.ui.activities;

import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.PinResponse;
import com.sstgroup.xabaapp.models.errors.ErrorStatusAndError;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Encryption;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.Utils;
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

    @OnClick({R.id.send_new_pin, R.id.do_you_remember_it, R.id.txt_visit_url})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.send_new_pin:
                resetPinAndSendSMS();
                break;
            case R.id.do_you_remember_it:
                finish();
                break;
            case R.id.txt_visit_url:
                Utils.openUrl(Constants.VISIT_XABA_URL, this);
                break;
        }
    }

    private void resetPinAndSendSMS() {

        String nationalId = mEditTextNationalId.getText().toString().trim();

        if (Validator.isEmpty(nationalId)) {
            ToastInterval.showToast(this, getResources().getString(R.string.enter_national_id));
            return;
        }

        if (Validator.isNotNumber(nationalId)) {
            ToastInterval.showToast(this, getResources().getString(R.string.national_id_should_be_a_number));
            return;
        }

        if (Validator.isNotCorrectNationalIdSize(nationalId)) {
            ToastInterval.showToast(this, getResources().getString(R.string.your_national_id_is_wrong));
            return;
        }

        nationalId = Encryption.encryptionRSA(nationalId);

        Call<PinResponse> call = RestClient.getService().resetPin(XabaApplication.getInstance().getLanguageCode(),
                Constants.AGENT_APP_VALUE, nationalId);
        call.enqueue(new Callback<PinResponse>() {
            @Override
            public void onResponse(Call<PinResponse> call, Response<PinResponse> response) {
                if (response.isSuccessful()) {
                    ToastInterval.showToast(ForgottenPinActivity.this, getResources().getString(R.string.pin_is_reset));
                    finish();
                } else {
                    ErrorStatusAndError errorStatusAndError = ErrorUtils.parseStatusAndError(response);
                    ToastInterval.showToast(ForgottenPinActivity.this, errorStatusAndError.getError());
                }
            }

            @Override
            public void onFailure(Call<PinResponse> call, Throwable t) {
                ToastInterval.showToast(ForgottenPinActivity.this, getResources().getString(R.string.something_is_wrong));
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}
