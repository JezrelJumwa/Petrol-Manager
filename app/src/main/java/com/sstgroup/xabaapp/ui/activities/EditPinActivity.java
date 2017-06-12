package com.sstgroup.xabaapp.ui.activities;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.PinResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Utils;
import com.sstgroup.xabaapp.utils.Validator;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class EditPinActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_old_pin)
    EditText etOldPin;
    @BindView(R.id.et_new_pin)
    EditText etNewPin;
    @BindView(R.id.et_retype_pin)
    EditText etConfirmPin;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_pin;
    }

    @Override
    protected void init() {
        setupToolbar(toolbar, R.drawable.arrow_back);
        hasBackArrow = true;
    }

    @OnClick(R.id.txt_visit_url)
    public void openUrl(){
        Utils.openUrl(Constants.VISIT_XABA_URL, this);
    }

    @OnClick(R.id.btn_change_pin)
    public void saveNewPin(){
        showLoader();
        String oldPin = etOldPin.getText().toString();
        String newPin = etNewPin.getText().toString();
        String retypePin = etConfirmPin.getText().toString();

        if (Validator.isEmpty(oldPin) || Validator.isEmpty(newPin) || Validator.isEmpty(retypePin)){
            ToastInterval.showToast(this, getString(R.string.fields_not_empty));
            hideLoader();
            return;
        }

        if (!newPin.equals(retypePin)){
            ToastInterval.showToast(this, getString(R.string.new_pint_retype_should_match));
            hideLoader();
            return;
        }

        Call<PinResponse> call = RestClient.getService().changePin(Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue(), oldPin, newPin);
        call.enqueue(new Callback<PinResponse>() {
            @Override
            public void onResponse(Call<PinResponse> call, Response<PinResponse> response) {
                if (response.isSuccessful()) {
                    response.body().getStatus();
                } else {

                }

                hideLoader();
            }

            @Override
            public void onFailure(Call<PinResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());

                hideLoader();
            }
        });




    }
}
