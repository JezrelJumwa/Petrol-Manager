package com.sstgroup.xabaapp.ui.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.RegisterWorkerRequestModel;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Preferences;
import com.sstgroup.xabaapp.utils.Validator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.sstgroup.xabaapp.utils.Validator.isNotNumber;

public class RegisterWorkerByAgentFragment extends BaseFragment {

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

    @BindView(R.id.add_another_profession)
    Button mButtonAddAnotherProfession;

    private String languageCode;
    private Long countryId;
    private Long countyId;
    private Long subCountyId;
    private Long userId;

    List<String> counties = new ArrayList<>();
    List<String> subCounties = new ArrayList<>();
    private String selectedCounty = "";
    private String selectedSubCounty = "";

    List<String> industries = new ArrayList<>();

    List<String> categories = new ArrayList<>();
    List<String> professions = new ArrayList<>();
    private String selectedIndustry = "";
    private String selectedCategory = "";
    private String selectedProfession = "";

    List<String> categoriesTwo = new ArrayList<>();
    List<String> professionsTwo = new ArrayList<>();
    private String selectedIndustryTwo = "";
    private String selectedCategoryTwo = "";
    private String selectedProfessionTwo = "";

    List<String> categoriesThree = new ArrayList<>();
    List<String> professionsThree = new ArrayList<>();
    private String selectedIndustryThree = "";
    private String selectedCategoryThree = "";
    private String selectedProfessionThree = "";

    public static RegisterWorkerByAgentFragment newInstance() {

        Bundle args = new Bundle();
        RegisterWorkerByAgentFragment fragment = new RegisterWorkerByAgentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_worker_by_agent;
    }

    @Override
    protected void initFields() {
        countryId = xabaDbHelper.getCountry(Preferences.getSelectedCountry(activity)).getCountryId();
        languageCode = xabaDbHelper.getLanguage(Preferences.getSelectedLanguage(activity)).getLanguageCode();
        counties = xabaDbHelper.getCounties();
        industries = xabaDbHelper.getIndustries();

//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            userId = Long.valueOf(bundle.getString(Constants.WORKER_ID));
//        }

        userId = xabaDbHelper.getLoggedUser(activity).getId();
    }

    @Override
    protected void initViews(View rootView) {
    }

    @OnClick({R.id.back, R.id.grp_county, R.id.grp_sub_county, R.id.grp_industry, R.id.grp_category, R.id.grp_profession, R.id.grp_industry_two, R.id.grp_category_two, R.id.grp_profession_two, R.id.grp_industry_three, R.id.grp_category_three, R.id.grp_profession_three, R.id.add_another_profession, R.id.register})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                activity.onBackPressed();
                break;
            case R.id.grp_county:
                showCountiesDialog();
                break;
            case R.id.grp_sub_county:
                showSubCountiesDialog();
                break;
            case R.id.grp_industry:
                showIndustriesDialog(1, industries);
                break;
            case R.id.grp_category:
                showCategoriesDialog(1, categories);
                break;
            case R.id.grp_profession:
                showProfessionsDialog(1, professions);
                break;
            case R.id.grp_industry_two:
                showIndustriesDialog(2, industries);
                break;
            case R.id.grp_category_two:
                showCategoriesDialog(2, categoriesTwo);
                break;
            case R.id.grp_profession_two:
                showProfessionsDialog(2, professionsTwo);
                break;
            case R.id.grp_industry_three:
                showIndustriesDialog(3, industries);
                break;
            case R.id.grp_category_three:
                showCategoriesDialog(3, categoriesThree);
                break;
            case R.id.grp_profession_three:
                showProfessionsDialog(3, professionsThree);
                break;
            case R.id.add_another_profession:
                addAnotherProfession();
                break;
            case R.id.register:
                register();
                break;
        }
    }

    private void showCountiesDialog() {
        CustomChooserDialog dialog = new CustomChooserDialog(activity, counties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedCounty = selectedItems.get(0);
                        txtCountySelection.setText(selectedCounty);
                        subCounties = xabaDbHelper.getSubCounties(selectedCounty);
                        countyId = xabaDbHelper.getCounty(selectedCounty).getCountyId();
                    }
                });
        dialog.show();
    }

    private void showSubCountiesDialog() {

        if (Validator.isEmpty(selectedCounty)) {
            Toast.makeText(activity, getString(R.string.choose_county_first), Toast.LENGTH_SHORT).show();
            return;
        }

        CustomChooserDialog dialog = new CustomChooserDialog(activity, subCounties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedSubCounty = selectedItems.get(0);
                        txtSubCountySelection.setText(selectedSubCounty);
                        subCountyId = xabaDbHelper.getSubCounty(selectedSubCounty).getSubCountyId();
                    }
                });
        dialog.show();
    }

    private void showIndustriesDialog(final int professionRow, List<String> options) {
        final CustomChooserDialog dialog = new CustomChooserDialog(activity, options, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        switch (professionRow) {
                            case 1:
                                selectedIndustry = selectedItems.get(0);
                                txtIndustrySelection.setText(selectedIndustry);
                                categories = xabaDbHelper.getCategories(selectedIndustry);
                                break;
                            case 2:
                                selectedIndustryTwo = selectedItems.get(0);
                                txtIndustrySelectionTwo.setText(selectedIndustryTwo);
                                categoriesTwo = xabaDbHelper.getCategories(selectedIndustryTwo);
                                break;
                            case 3:
                                selectedIndustryThree = selectedItems.get(0);
                                txtIndustrySelectionThree.setText(selectedIndustryThree);
                                categoriesThree = xabaDbHelper.getCategories(selectedIndustryThree);
                                break;
                        }
                    }
                });
        dialog.show();
    }

    private void showCategoriesDialog(final int professionRow, List<String> options) {

        boolean isSuccessValidation = true;

        switch (professionRow) {
            case 1:
                if (Validator.isEmpty(selectedIndustry)) {
                    Toast.makeText(activity, getString(R.string.choose_industry_first), Toast.LENGTH_SHORT).show();
                    isSuccessValidation = false;
                }
                break;
            case 2:
                if (Validator.isEmpty(selectedIndustryTwo)) {
                    Toast.makeText(activity, getString(R.string.choose_industry_first), Toast.LENGTH_SHORT).show();
                    isSuccessValidation = false;
                }
                break;
            case 3:
                if (Validator.isEmpty(selectedIndustryThree)) {
                    Toast.makeText(activity, getString(R.string.choose_industry_first), Toast.LENGTH_SHORT).show();
                    isSuccessValidation = false;
                }
                break;
        }

        if (!isSuccessValidation) {
            return;
        }

        CustomChooserDialog dialog = new CustomChooserDialog(activity, options, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        switch (professionRow) {
                            case 1:
                                selectedCategory = selectedItems.get(0);
                                txtCategorySelection.setText(selectedCategory);
                                professions = xabaDbHelper.getProfessions(selectedCategory);
                                break;
                            case 2:
                                selectedCategoryTwo = selectedItems.get(0);
                                txtCategorySelectionTwo.setText(selectedCategoryTwo);
                                professionsTwo = xabaDbHelper.getProfessions(selectedCategoryTwo);
                                break;
                            case 3:
                                selectedCategoryThree = selectedItems.get(0);
                                txtCategorySelectionThree.setText(selectedCategoryThree);
                                professionsThree = xabaDbHelper.getProfessions(selectedCategoryThree);
                                break;
                        }

                    }
                });
        dialog.show();
    }

    private void showProfessionsDialog(final int professionRow, List<String> options) {

        boolean isSuccessValidation = true;

        switch (professionRow) {
            case 1:
                if (Validator.isEmpty(selectedCategory)) {
                    Toast.makeText(activity, getString(R.string.choose_category_first), Toast.LENGTH_SHORT).show();
                    isSuccessValidation = false;
                }
                break;
            case 2:
                if (Validator.isEmpty(selectedCategoryTwo)) {
                    Toast.makeText(activity, getString(R.string.choose_category_first), Toast.LENGTH_SHORT).show();
                    isSuccessValidation = false;
                }
                break;
            case 3:
                if (Validator.isEmpty(selectedCategoryThree)) {
                    Toast.makeText(activity, getString(R.string.choose_category_first), Toast.LENGTH_SHORT).show();
                    isSuccessValidation = false;
                }
                break;
        }

        if (!isSuccessValidation) {
            return;
        }

        CustomChooserDialog dialog = new CustomChooserDialog(activity, options, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        switch (professionRow) {
                            case 1:
                                selectedProfession = selectedItems.get(0);
                                txtProfessionSelection.setText(selectedProfession);
                                break;
                            case 2:
                                selectedProfessionTwo = selectedItems.get(0);
                                txtProfessionSelectionTwo.setText(selectedProfessionTwo);
                                break;
                            case 3:
                                selectedProfessionThree = selectedItems.get(0);
                                txtProfessionSelectionThree.setText(selectedProfessionThree);
                                break;
                        }
                    }
                });
        dialog.show();
    }

    private void addAnotherProfession() {
        if (mLinearLayoutProfessionTwo.getVisibility() == View.GONE) {
            mLinearLayoutProfessionTwo.setVisibility(View.VISIBLE);
        } else if (mLinearLayoutProfessionThree.getVisibility() == View.GONE) {
            mLinearLayoutProfessionThree.setVisibility(View.VISIBLE);
            mButtonAddAnotherProfession.setVisibility(View.GONE);
        }
    }

    private void register() {

        String nationalId = mEditTextNationalId.getText().toString().trim();
        String confirmNationalId = mEditTextConfirmNationalId.getText().toString().trim();
        String phoneNumber = mEditTextPhoneNumber.getText().toString().trim();


        // validations for National ID
        if (Validator.isEmpty(nationalId)) {
            Toast.makeText(activity, getResources().getString(R.string.enter_national_id), Toast.LENGTH_SHORT).show();
            return;
        }

        if (isNotNumber(nationalId)) {
            Toast.makeText(activity, getResources().getString(R.string.national_id_should_be_a_number), Toast.LENGTH_SHORT).show();
            return;
        }

        if (nationalId.length() != 10) {
            Toast.makeText(activity, getResources().getString(R.string.your_national_id_is_wrong), Toast.LENGTH_SHORT).show();
            return;
        }

        // validations for Confirm National ID
        if (Validator.isEmpty(confirmNationalId)) {
            Toast.makeText(activity, getResources().getString(R.string.enter_confirm_national_id), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!nationalId.equals(confirmNationalId)) {
            Toast.makeText(activity, getResources().getString(R.string.national_id_and_confirm_national_id_should_be_the_same), Toast.LENGTH_SHORT).show();
            return;
        }

        // validations for Phone Number
        if (Validator.isEmpty(phoneNumber)) {
            Toast.makeText(activity, getResources().getString(R.string.enter_phone_number), Toast.LENGTH_SHORT).show();
            return;
        }

        //  validations for Locations
        if (Validator.isEmpty(selectedCounty)) {
            Toast.makeText(activity, getResources().getString(R.string.choose_county), Toast.LENGTH_SHORT).show();
            return;
        }

        if (Validator.isEmpty(selectedSubCounty)) {
            Toast.makeText(activity, getResources().getString(R.string.choose_sub_county), Toast.LENGTH_SHORT).show();
            return;
        }

        //  validations for Professions
        if (Validator.isEmpty(selectedIndustry)) {
            Toast.makeText(activity, getResources().getString(R.string.choose_industry), Toast.LENGTH_SHORT).show();
            return;
        }

        if (Validator.isEmpty(selectedCategory)) {
            Toast.makeText(activity, getResources().getString(R.string.choose_category), Toast.LENGTH_SHORT).show();
            return;
        }

        if (Validator.isEmpty(selectedProfession)) {
            Toast.makeText(activity, getResources().getString(R.string.choose_profession), Toast.LENGTH_SHORT).show();
            return;
        }


        List<String> professions = new ArrayList<>();
        if (!Validator.isEmpty(selectedProfession)) {
            professions.add(selectedProfession);
        }
        if (!Validator.isEmpty(selectedProfessionTwo)) {
            professions.add(selectedProfessionTwo);
        }
        if (!Validator.isEmpty(selectedProfessionThree)) {
            professions.add(selectedProfessionThree);
        }

        List<Long> professionIds = xabaDbHelper.getProfessionIds(professions);

        RegisterWorkerRequestModel registerWorkerRequestModel = new RegisterWorkerRequestModel(nationalId, null, phoneNumber, languageCode, countryId, countyId, subCountyId, professionIds, userId, Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue());

        RequestBody body = RequestBody.create(MediaType.parse("text"), registerWorkerRequestModel.generateRegisterWorkerByAgentRequest());
        Call<Object> call = RestClient.getService().registerWorkerByAgent(body);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    ToastInterval.showToast(activity, getString(R.string.worker_is_registered));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}