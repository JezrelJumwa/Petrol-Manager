package com.sstgroup.xabaapp.ui.fragments;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterWorkerFragment extends BaseFragment {

    @BindView(R.id.national_id)
    EditText mEditTextNationalId;
    @BindView(R.id.confirm_national_id)
    EditText mEditTextConfirmNationalId;
    @BindView(R.id.phone_number)
    EditText mEditTextPhoneNumber;
    @BindView(R.id.txt_language_selection)
    TextView txtLanguageSelection;
    @BindView(R.id.txt_county_selection)
    TextView txtCountySelection;
    @BindView(R.id.txt_sub_county_selection)
    TextView txtSubCountySelection;
    @BindView(R.id.txt_industry_selection)
    TextView txtIndustrySelection;
    @BindView(R.id.txt_category_selection)
    TextView txtCategorySelection;
    @BindView(R.id.txt_profession_selection)
    TextView txtProfessionSelection;

    private String selectedLanguage = "";
    private String selectedCounty = "";
    private String selectedSubCounty = "";

    private String selectedIndustry = "";
    private String selectedCategory = "";
    private String selectedProfession = "";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_worker;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.grp_language, R.id.grp_county, R.id.grp_sub_county, R.id.grp_industry, R.id.grp_category, R.id.grp_profession, R.id.register})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.grp_language:
                showLanguagesDialog();
                break;
            case R.id.grp_county:
                showCountiesDialog();
                break;
            case R.id.grp_sub_county:
                showSubCountiesDialog();
                break;
            case R.id.grp_industry:
                showIndustriesDialog();
                break;
            case R.id.grp_category:
                showCategoriesDialog();
                break;
            case R.id.grp_profession:
                showProfessionsDialog();
                break;
            case R.id.register:
                RegisterConfirmFragment registerConfirmFragment = new RegisterConfirmFragment();
                activity.openFragment(registerConfirmFragment, true);
                break;
        }
    }

    private void showLanguagesDialog() {

        final List<String> languages = new ArrayList<>();
        languages.add("Bulgarian");
        languages.add("German");
        languages.add("French");

        CustomChooserDialog dialog = new CustomChooserDialog(activity, languages, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedLanguage = selectedItems.get(0);
                        txtLanguageSelection.setText(selectedItems.get(0));
                    }
                });
        dialog.show();
    }

    private void showCountiesDialog() {

        final List<String> counties = new ArrayList<>();
        counties.add("Sofia");
        counties.add("Plovdiv");
        counties.add("Varna");

        CustomChooserDialog dialog = new CustomChooserDialog(activity, counties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedCounty = selectedItems.get(0);
                        txtCountySelection.setText(selectedItems.get(0));
                    }
                });
        dialog.show();
    }

    private void showSubCountiesDialog() {

        final List<String> subCounties = new ArrayList<>();
        subCounties.add("Ihtiman");
        subCounties.add("Pomorie");
        subCounties.add("Shabla");

        CustomChooserDialog dialog = new CustomChooserDialog(activity, subCounties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedSubCounty = selectedItems.get(0);
                        txtSubCountySelection.setText(selectedItems.get(0));
                    }
                });
        dialog.show();
    }

    private void showIndustriesDialog() {

        final List<String> industries = new ArrayList<>();
        industries.add("Pharmacy");
        industries.add("IT");
        industries.add("Tourism");

        CustomChooserDialog dialog = new CustomChooserDialog(activity, industries, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedIndustry = selectedItems.get(0);
                        txtIndustrySelection.setText(selectedItems.get(0));
                    }
                });
        dialog.show();
    }

    private void showCategoriesDialog() {

        final List<String> categories = new ArrayList<>();
        categories.add("Chief");
        categories.add("Worker");
        categories.add("Support");

        CustomChooserDialog dialog = new CustomChooserDialog(activity, categories, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedCategory = selectedItems.get(0);
                        txtCategorySelection.setText(selectedItems.get(0));
                    }
                });
        dialog.show();
    }

    private void showProfessionsDialog() {

        final List<String> professions = new ArrayList<>();
        professions.add("Policeman");
        professions.add("Teacher");
        professions.add("Barman");

        CustomChooserDialog dialog = new CustomChooserDialog(activity, professions, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedProfession = selectedItems.get(0);
                        txtProfessionSelection.setText(selectedItems.get(0));
                    }
                });
        dialog.show();
    }
}
