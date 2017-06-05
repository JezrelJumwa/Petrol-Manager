package com.sstgroup.xabaapp.ui.fragments;


import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;


public class RegisterWorkerAgentFragment extends BaseFragment {

    @BindView(R.id.national_id)
    EditText mEditTextNationalId;
    @BindView(R.id.confirm_national_id)
    EditText mEditTextConfirmNationalId;
    @BindView(R.id.phone_number)
    EditText mEditTextPhoneNumber;

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

    @BindView(R.id.layout_profession_two)
    LinearLayout mLinearLayoutProfessionTwo;
    @BindView(R.id.txt_industry_selection_two)
    TextView txtIndustrySelectionTwo;
    @BindView(R.id.txt_category_selection_two)
    TextView txtCategorySelectionTwo;
    @BindView(R.id.txt_profession_selection_two)
    TextView txtProfessionSelectionTwo;

    @BindView(R.id.layout_profession_three)
    LinearLayout mLinearLayoutProfessionThree;
    @BindView(R.id.txt_industry_selection_three)
    TextView txtIndustrySelectionThree;
    @BindView(R.id.txt_category_selection_three)
    TextView txtCategorySelectionThree;
    @BindView(R.id.txt_profession_selection_three)
    TextView txtProfessionSelectionThree;

    @BindView(R.id.pin_code)
    EditText mEditTextPinCode;
    @BindView(R.id.confirm_pin_code)
    EditText mEditTextConfirmPinCode;
    @BindView(R.id.referral_code)
    EditText mEditTextReferralCode;

    List<String> counties = new ArrayList<>();
    List<String> subCounties = new ArrayList<>();

    List<String> industries = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    List<String> professions = new ArrayList<>();

    private String selectedCounty = "";
    private String selectedSubCounty = "";

    private String selectedIndustry = "";
    private String selectedCategory = "";
    private String selectedProfession = "";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_worker_agent;
    }

    @Override
    protected void initFields() {
        counties = xabaDbHelper.getCounties();
        industries = xabaDbHelper.getIndustries();
    }

    @Override
    protected void initViews(View rootView) {

    }

    @OnClick({R.id.grp_county, R.id.grp_sub_county, R.id.grp_industry, R.id.grp_category, R.id.grp_profession, R.id.add_another_profession, R.id.register})
    public void onButtonClick(View view) {
        switch (view.getId()) {
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
            case R.id.add_another_profession:
                addAnotherProfession();
                break;
            case R.id.register:
                RegisterConfirmFragment registerConfirmFragment = new RegisterConfirmFragment();
                activity.openFragment(registerConfirmFragment, true);
                break;
        }
    }

    private void showCountiesDialog() {

        CustomChooserDialog dialog = new CustomChooserDialog(activity, counties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedCounty = selectedItems.get(0);
                        txtCountySelection.setText(selectedItems.get(0));
                        subCounties = xabaDbHelper.getSubCounties(selectedItems.get(0));
                    }
                });
        dialog.show();
    }

    private void showSubCountiesDialog() {
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
        CustomChooserDialog dialog = new CustomChooserDialog(activity, industries, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedIndustry = selectedItems.get(0);
                        txtIndustrySelection.setText(selectedItems.get(0));
                        categories = xabaDbHelper.getCategories(selectedItems.get(0));
                    }
                });
        dialog.show();
    }

    private void showCategoriesDialog() {
        CustomChooserDialog dialog = new CustomChooserDialog(activity, categories, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedCategory = selectedItems.get(0);
                        txtCategorySelection.setText(selectedItems.get(0));
                        professions = xabaDbHelper.getProfessions(selectedItems.get(0));
                    }
                });
        dialog.show();
    }

    private void showProfessionsDialog() {

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

    private void addAnotherProfession() {
        if (mLinearLayoutProfessionTwo.getVisibility() == View.GONE) {
            mLinearLayoutProfessionTwo.setVisibility(View.VISIBLE);
        } else if (mLinearLayoutProfessionThree.getVisibility() == View.GONE) {
            mLinearLayoutProfessionThree.setVisibility(View.VISIBLE);
        }
    }
}
