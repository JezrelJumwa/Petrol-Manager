package com.sstgroup.xabaapp.ui.fragments;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.Language;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.ui.activities.RegisterActivity;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Preferences;
import com.sstgroup.xabaapp.utils.Validator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


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

                        Language language = xabaDbHelper.getLanguage(selectedLanguage);
                        XabaApplication.getInstance().setLanguage(language);

                        Preferences.setSelectedLanguage(activity, selectedLanguage);
                    }
                });
        dialog.show();
    }
}
