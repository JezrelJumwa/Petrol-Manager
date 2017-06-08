package com.sstgroup.xabaapp.ui.fragments;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.PinResponse;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.UserResponse;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.ui.activities.RegisterActivity;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Encryption;
import com.sstgroup.xabaapp.utils.Preferences;
import com.sstgroup.xabaapp.utils.Validator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class WizardStepOneFragment extends BaseFragment {

    @BindView(R.id.txt_country_selection)
    TextView txtCountrySelection;
    @BindView(R.id.txt_language_selection)
    TextView txtLanguageSelection;

    List<String> countries = new ArrayList<>();
    List<String> languages = new ArrayList<>();

    private String selectedCountry = "";
    private String selectedLanguage = "";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wizard_step_one;
    }

    @Override
    protected void initFields() {
        countries = xabaDbHelper.getCountries();
        languages = xabaDbHelper.getLanguages();

        selectedCountry = Preferences.getSelectedCountry(activity);
        selectedLanguage = Preferences.getSelectedLanguage(activity);
    }

    @Override
    protected void initViews(View rootView) {

        if (!Validator.isEmpty(selectedCountry)) {
            txtCountrySelection.setText(selectedCountry);
        }

        if (!Validator.isEmpty(selectedLanguage)) {
            txtLanguageSelection.setText(selectedLanguage);
        }
    }

    @OnClick({R.id.grp_country, R.id.grp_language, R.id.register, R.id.next, R.id.log_in})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.grp_country:
                showCountriesDialog();
                break;
            case R.id.grp_language:
                showLanguagesDialog();
                break;
            case R.id.register:
                if (isValidationSuccess()) {
                    Intent intentToRegisterActivity = new Intent(activity, RegisterActivity.class);
                    startActivity(intentToRegisterActivity);
                }
                break;
            case R.id.next:
                if (isValidationSuccess()) {
                    WizardStepTwoFragment wizardStepTwoFragment = new WizardStepTwoFragment();
                    activity.openFragment(wizardStepTwoFragment, true);
                }
                break;
            case R.id.log_in:
                if (isValidationSuccess()) {
                    Intent intent = new Intent(activity, LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean isValidationSuccess() {
        if (Validator.isEmpty(selectedCountry)) {
            ToastInterval.showToast(activity, getString(R.string.choose_country));
            return false;
        }

        if (Validator.isEmpty(selectedLanguage)) {
            ToastInterval.showToast(activity, getString(R.string.choose_language));
            return false;
        }

        return true;
    }

    private void showCountriesDialog() {
        CustomChooserDialog dialog = new CustomChooserDialog(activity, countries, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedCountry = selectedItems.get(0);
                        txtCountrySelection.setText(selectedCountry);

                        Preferences.setSelectedCountry(activity, selectedCountry);
                    }
                });
        dialog.show();
    }

    private void showLanguagesDialog() {
        CustomChooserDialog dialog = new CustomChooserDialog(activity, languages, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedLanguage = selectedItems.get(0);
                        txtLanguageSelection.setText(selectedLanguage);

                        Preferences.setSelectedLanguage(activity, selectedLanguage);
                    }
                });
        dialog.show();
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

    private void sendVerificationCodeForResetPIN() {

        String nationalId = "1234554781";
        nationalId = Encryption.encryptionRSA(nationalId);

        Call<PinResponse> call = RestClient.getService().sendVerificationCodeForResetPIN(Constants.AGENT_APP_VALUE, nationalId);
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
