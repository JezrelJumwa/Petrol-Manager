package com.sstgroup.xabaapp.ui.fragments;


import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.RegisterWorkerRequestModel;
import com.sstgroup.xabaapp.models.UserResponse;
import com.sstgroup.xabaapp.models.errors.ErrorRegisterWorker;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.Preferences;
import com.sstgroup.xabaapp.utils.Utils;
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
    @BindView(R.id.remove_two)
    TextView txtRemoveTwo;

    @BindView(R.id.layout_profession_three)
    LinearLayout mLinearLayoutProfessionThree;
    @BindView(R.id.txt_industry_selection_three)
    TextView txtIndustrySelectionThree;
    @BindView(R.id.txt_category_selection_three)
    TextView txtCategorySelectionThree;
    @BindView(R.id.txt_profession_selection_three)
    TextView txtProfessionSelectionThree;
    @BindView(R.id.remove_three)
    TextView txtRemoveThree;

    @BindView(R.id.add_another_profession)
    Button mButtonAddAnotherProfession;

    @BindView(R.id.pin_code)
    EditText mEditTextPinCode;
    @BindView(R.id.confirm_pin_code)
    EditText mEditTextConfirmPinCode;
    @BindView(R.id.referral_code)
    EditText mEditTextReferralCode;

    @BindView(R.id.txt_program)
    TextView txtProgram;

    private String languageCode;
    private Long countryId;
    private Long countyId;
    private Long subCountyId;

    List<String> counties = new ArrayList<>();
    List<String> subCounties = new ArrayList<>();
    private String selectedCounty = "";
    private String selectedSubCounty = "";

    List<String> industries = new ArrayList<>();
    List<String> programs = new ArrayList<>();
    private List<String> selectedPrograms = new ArrayList<>();

    List<String> categories = new ArrayList<>();
    List<String> professions = new ArrayList<>();
    private String selectedIndustry = "";
    private String selectedCategory = "";
    private String selectedProfession = "";
    private Long selectedProfessionId = 0L;

    List<String> categoriesTwo = new ArrayList<>();
    List<String> professionsTwo = new ArrayList<>();
    private String selectedIndustryTwo = "";
    private String selectedCategoryTwo = "";
    private String selectedProfessionTwo = "";
    private Long selectedProfessionTwoId = 0L;

    List<String> categoriesThree = new ArrayList<>();
    List<String> professionsThree = new ArrayList<>();
    private String selectedIndustryThree = "";
    private String selectedCategoryThree = "";
    private String selectedProfessionThree = "";
    private Long selectedProfessionThreeId = 0L;

    private RegistrationInfo info;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_worker_agent;
    }

    @Override
    protected void initFields() {
        countryId = xabaDbHelper.getCountry(Preferences.getSelectedCountry(activity)).getCountryId();
        languageCode = xabaDbHelper.getLanguage(Preferences.getSelectedLanguage(activity)).getLanguageCode();
        counties = xabaDbHelper.getCounties();
        industries = xabaDbHelper.getIndustries();
        programs = xabaDbHelper.getActivePrograms();

        String defaultProgram = xabaDbHelper.getProgramById(Constants.DEFAULT_PROGRAM_ID);
        if (programs.contains(defaultProgram)){
            selectedPrograms.add(defaultProgram);
            txtProgram.setText(defaultProgram);
        }
    }

    @Override
    protected void initViews(View rootView) {
        if(Preferences.getSelectedCountry(activity).equals("Kenya")){
            mEditTextPhoneNumber.setText("+254");
        }

        mEditTextReferralCode.setText("15");
    }

    @OnClick({R.id.back, R.id.grp_county, R.id.grp_sub_county, R.id.grp_industry, R.id.grp_category, R.id.grp_profession, R.id.grp_industry_two, R.id.grp_category_two, R.id.grp_profession_two, R.id.grp_industry_three, R.id.grp_category_three, R.id.grp_profession_three, R.id.remove_two, R.id.remove_three, R.id.add_another_profession, R.id.grp_program, R.id.register})
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
            case R.id.remove_two:
                removeProfessionTwo();
                break;
            case R.id.remove_three:
                removeProfessionThree();
                break;
            case R.id.add_another_profession:
                addAnotherProfession();
                break;
            case R.id.grp_program:
                showProgramsDialog();
                break;
            case R.id.register:
                register();
                break;
        }
    }

    private void showProgramsDialog() {


        CustomChooserDialog dialog = new CustomChooserDialog(activity, programs, false, new ArrayList<>(selectedPrograms),
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        if (selectedItems.size() > 0) {
                            selectedPrograms = new ArrayList<>(selectedItems);
                            txtProgram.setText(CustomChooserDialog.getSelectedPrograms(selectedPrograms));
                        } else {
                            txtProgram.setText(getString(R.string.select_program));
                        }
                    }
                });
        dialog.show();
    }

    private void showCountiesDialog() {
        CustomChooserDialog dialog = new CustomChooserDialog(activity, counties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {

                        if (!selectedCounty.equals(selectedItems.get(0))) {
                            selectedSubCounty = "";
                            txtSubCountySelection.setText(getString(R.string.select_sub_county));
                        }

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
            ToastInterval.showToast(activity, getString(R.string.choose_county_first));
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

                                if (!selectedIndustry.equals(selectedItems.get(0))) {
                     /*               selectedCategory = "";
                                    txtCategorySelection.setText(getString(R.string.select_category));*/
                                    selectedProfession = "";
                                    txtProfessionSelection.setText(getString(R.string.select_profession));
                                }

                                selectedIndustry = selectedItems.get(0);
                                txtIndustrySelection.setText(selectedIndustry);

                                professions = xabaDbHelper.getProfessions(selectedIndustry);
                                break;
                            case 2:

                                if (!selectedIndustryTwo.equals(selectedItems.get(0))) {
                              /*      selectedCategoryTwo = "";
                                    txtCategorySelectionTwo.setText(getString(R.string.select_category));*/
                                    selectedProfessionTwo = "";
                                    txtProfessionSelectionTwo.setText(getString(R.string.select_profession));
                                }

                                selectedIndustryTwo = selectedItems.get(0);
                                txtIndustrySelectionTwo.setText(selectedIndustryTwo);
                                professionsTwo = xabaDbHelper.getProfessions(selectedIndustryTwo);
                                break;
                            case 3:

                                if (!selectedIndustryThree.equals(selectedItems.get(0))) {
                                 /*   selectedCategoryThree = "";
                                    txtCategorySelectionThree.setText(getString(R.string.select_category));*/
                                    selectedProfessionThree = "";
                                    txtProfessionSelectionThree.setText(getString(R.string.select_profession));
                                }

                                selectedIndustryThree = selectedItems.get(0);
                                txtIndustrySelectionThree.setText(selectedIndustryThree);
                                professionsThree = xabaDbHelper.getProfessions(selectedIndustryThree);
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
                    ToastInterval.showToast(activity, getString(R.string.choose_industry_first));
                    isSuccessValidation = false;
                }
                break;
            case 2:
                if (Validator.isEmpty(selectedIndustryTwo)) {
                    ToastInterval.showToast(activity, getString(R.string.choose_industry_first));
                    isSuccessValidation = false;
                }
                break;
            case 3:
                if (Validator.isEmpty(selectedIndustryThree)) {
                    ToastInterval.showToast(activity, getString(R.string.choose_industry_first));
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

                                if (!selectedCategory.equals(selectedItems.get(0))) {
                                    selectedProfession = "";
                                    txtProfessionSelection.setText(getString(R.string.select_profession));
                                }

                                selectedCategory = selectedItems.get(0);
                                txtCategorySelection.setText(selectedCategory);
                                professions = xabaDbHelper.getProfessions(selectedCategory, selectedIndustry);
                                break;
                            case 2:

                                if (!selectedCategoryTwo.equals(selectedItems.get(0))) {
                                    selectedProfessionTwo = "";
                                    txtProfessionSelectionTwo.setText(getString(R.string.select_profession));
                                }

                                selectedCategoryTwo = selectedItems.get(0);
                                txtCategorySelectionTwo.setText(selectedCategoryTwo);
                                professionsTwo = xabaDbHelper.getProfessions(selectedCategoryTwo, selectedIndustryTwo);
                                break;
                            case 3:

                                if (!selectedCategoryThree.equals(selectedItems.get(0))) {
                                    selectedProfessionThree = "";
                                    txtProfessionSelectionThree.setText(getString(R.string.select_profession));
                                }

                                selectedCategoryThree = selectedItems.get(0);
                                txtCategorySelectionThree.setText(selectedCategoryThree);
                                professionsThree = xabaDbHelper.getProfessions(selectedCategoryThree, selectedIndustryThree);
                                break;
                        }

                    }
                });
        dialog.show();
    }

    private void showProfessionsDialog(final int professionRow, List<String> options) {

        boolean isSuccessValidation = true;

        switch (professionRow) {
         /*  case 1:
                if (Validator.isEmpty(selectedCategory)) {
                    ToastInterval.showToast(activity, getString(R.string.choose_category_first));
                    isSuccessValidation = false;
                }
                break;
            case 2:
                if (Validator.isEmpty(selectedCategoryTwo)) {
                    ToastInterval.showToast(activity, getString(R.string.choose_category_first));
                    isSuccessValidation = false;
                }
                break;
            case 3:
                if (Validator.isEmpty(selectedCategoryThree)) {
                    ToastInterval.showToast(activity, getString(R.string.choose_category_first));
                    isSuccessValidation = false;
                }
                break;*/

            case 1:
                if (Validator.isEmpty(selectedIndustry)) {
                    ToastInterval.showToast(activity, getString(R.string.choose_industry_first));
                    isSuccessValidation = false;
                }
                break;
            case 2:
                if (Validator.isEmpty(selectedIndustryTwo)) {
                    ToastInterval.showToast(activity, getString(R.string.choose_industry_first));
                    isSuccessValidation = false;
                }
                break;
            case 3:
                if (Validator.isEmpty(selectedIndustryThree)) {
                    ToastInterval.showToast(activity, getString(R.string.choose_industry_first));
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

    private void removeProfessionTwo() {
        mLinearLayoutProfessionTwo.setVisibility(View.GONE);
        mButtonAddAnotherProfession.setVisibility(View.VISIBLE);
    }

    private void removeProfessionThree() {
        mLinearLayoutProfessionThree.setVisibility(View.GONE);
        mButtonAddAnotherProfession.setVisibility(View.VISIBLE);
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

        RegisterWorkerRequestModel registerWorkerRequestModel = getRegisterWorkerRequestModel();
        if (registerWorkerRequestModel == null) return;

        requestRegister(registerWorkerRequestModel);
    }

    @Nullable
    private RegisterWorkerRequestModel getRegisterWorkerRequestModel() {
        String nationalId = mEditTextNationalId.getText().toString().trim();
        String confirmNationalId = mEditTextConfirmNationalId.getText().toString().trim();
        String phoneNumber = mEditTextPhoneNumber.getText().toString().replaceAll("\\s+", "");

        String pinCode = mEditTextPinCode.getText().toString().trim();
        String confirmPinCode = mEditTextConfirmPinCode.getText().toString().trim();
        String referralCode = mEditTextReferralCode.getText().toString().trim();

        ArrayList<String> selectedIndustries = new ArrayList<>();
        if (selectedIndustry != null && selectedIndustry.length() > 0) {
            selectedIndustries.add(selectedIndustry);
        }
        if (selectedIndustryTwo != null && selectedIndustryTwo.length() > 0) {
            selectedIndustries.add(selectedIndustryTwo);
        }
        if (selectedIndustryThree != null && selectedIndustryThree.length() > 0) {
            selectedIndustries.add(selectedIndustryThree);
        }
        List<Long> industryIds = xabaDbHelper.getIndustryIds(selectedIndustries);

        ArrayList<Long> selectedProfessions = new ArrayList<>();
        if (selectedProfession != null && selectedProfession.length() > 0) {
            selectedProfessions.add(xabaDbHelper.getProfessionId(selectedIndustry, selectedProfession));
        }
        if (selectedProfessionTwo != null && selectedProfessionTwo.length() > 0) {
            selectedProfessions.add(xabaDbHelper.getProfessionId(selectedIndustryTwo, selectedProfessionTwo));
        }
        if (selectedProfessionThree != null && selectedProfessionThree.length() > 0) {
            selectedProfessions.add(xabaDbHelper.getProfessionId(selectedIndustryThree, selectedProfessionThree));
        }


        ArrayList<String> programs = new ArrayList<String>(selectedPrograms);
        List<Long> programIds = xabaDbHelper.getProgramIds(programs);

        info = new RegistrationInfo(nationalId,
                confirmNationalId,
                phoneNumber,
                pinCode,
                confirmPinCode,
                referralCode,
                industryIds,
                selectedProfessions,
                programIds);

        if (validateRegisterInfo(info)) return null;

        return getRegisterWorkerRequestModelWithInfo(info);
    }

    @Nullable
    public RegisterWorkerRequestModel getRegisterWorkerRequestModelWithInfo(RegistrationInfo registrationInfo) {
        RegisterWorkerRequestModel registerWorkerRequestModel = new RegisterWorkerRequestModel(registrationInfo.getNationalId(), registrationInfo.getPinCode(), registrationInfo.getPhoneNumber(), languageCode, countryId, countyId, subCountyId, registrationInfo.professionIds, registrationInfo.getIndustryIds(), registrationInfo.getReferralCodeAsLong(), Constants.AGENT_APP_VALUE, null, registrationInfo.programIds);
        return registerWorkerRequestModel;
    }

    private boolean validateRegisterInfo(RegistrationInfo registrationInfo) {
        // validations for National ID
        if (Validator.isEmpty(registrationInfo.getNationalId())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.enter_national_id));
            return true;
        }

        if (Validator.isNotNumber(registrationInfo.getNationalId())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.national_id_should_be_a_number));
            return true;
        }

        if (Validator.isNotCorrectNationalIdSize(registrationInfo.getNationalId())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.your_national_id_is_wrong));
            return true;
        }

        // validations for Confirm National ID
        if (Validator.isEmpty(registrationInfo.getConfirmNationalId())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.enter_confirm_national_id));
            return true;
        }

        if (!registrationInfo.getNationalId().equals(registrationInfo.getConfirmNationalId())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.national_id_and_confirm_national_id_should_be_the_same));
            return true;
        }

        // validations for Phone Number
        if (Validator.isEmpty(registrationInfo.getPhoneNumber())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.enter_phone_number));
            return true;
        }

        if (!Validator.isCorrectPhoneNumber(registrationInfo.getPhoneNumber())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.your_phone_number_should_look_like));
            return true;
        }

        //  validations for Locations
        if (Validator.isEmpty(selectedCounty)) {
            ToastInterval.showToast(activity, getResources().getString(R.string.choose_county));
            return true;
        }

        if (Validator.isEmpty(selectedSubCounty)) {
            ToastInterval.showToast(activity, getResources().getString(R.string.choose_sub_county));
            return true;
        }

        //  validations for Professions
        if (Validator.isEmpty(selectedIndustry)) {
            ToastInterval.showToast(activity, getResources().getString(R.string.choose_industry));
            return true;
        }

    /*    if (Validator.isEmpty(selectedCategory)) {
            ToastInterval.showToast(activity, getResources().getString(R.string.choose_category));
            return true;
        }*/

        if (Validator.isEmpty(selectedProfession)) {
            ToastInterval.showToast(activity, getResources().getString(R.string.choose_profession));
            return true;
        }

        // validations for PIN code
        if (Validator.isEmpty(registrationInfo.getPinCode())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.enter_pin_code));
            return true;
        }

        if (Validator.isNotCorrectPinCodeSize(registrationInfo.getPinCode())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.your_pin_code_is_wrong));
            return true;
        }

        // validations for Confirm PIN code
        if (Validator.isEmpty(registrationInfo.getConfirmPinCode())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.enter_confirm_pin_code));
            return true;
        }

        if (!Validator.isMatch(registrationInfo.getPinCode(), registrationInfo.getConfirmPinCode())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.pin_code_and_confirm_pin_code_should_be_the_same));
            return true;
        }

        // validations for Referral code
      /*  if (Validator.isEmpty(referralCode)) {
            ToastInterval.showToast(activity, getResources().getString(R.string.enter_referral_code));
            return;
        }*/

        if (Validator.isNotNumber(registrationInfo.getReferralCode())) {
            ToastInterval.showToast(activity, getResources().getString(R.string.referral_code_should_be_a_number));
            return true;
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
        return false;
    }

    private void requestRegister(RegisterWorkerRequestModel registerWorkerRequestModel) {
        activity.showLoader();
        RequestBody body = RequestBody.create(MediaType.parse("text"), registerWorkerRequestModel.generateRegisterWorkerAgentRequest());
        Call<UserResponse> call = RestClient.getService().register(XabaApplication.getInstance().getLanguageCode(), body);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Preferences.setLoggedUserId(activity, response.body().getUser().getId());
                    activity.openFragment(RegisterConfirmFragment.newInstance(response.body().getUser().getId(), false), true);
                } else {
                    ErrorRegisterWorker errorRegisterWorker = ErrorUtils.parseRegisterWorkerError(response);
                    if (errorRegisterWorker.getStatus().equals(Constants.ERROR_STATUS_UNEXPECTED)) {
                        ToastInterval.showToast(activity, getString(R.string.something_is_wrong));
                    } else {
                        if (errorRegisterWorker.getError().getNationalIdErrors() != null) {
                            ToastInterval.showToast(activity, errorRegisterWorker.getError().getNationalIdErrors().get(0));
                        } else if (errorRegisterWorker.getError().getAgentIdErrors() != null) {
                            ToastInterval.showToast(activity, errorRegisterWorker.getError().getAgentIdErrors().get(0));
                        }
                    }
                }
                activity.hideLoader();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
                activity.hideLoader();
                Utils.onFailureUtils(activity, t);
            }
        });
    }

    public static class RegistrationInfo {
        private String nationalId;
        private String confirmNationalId;
        private String phoneNumber;
        private String pinCode;
        private String confirmPinCode;
        private String referralCode;
        private List<Long> professionIds;
        private List<Long> industryIds;
        private List<Long> programIds;

        public RegistrationInfo(String nationalId, String confirmNationalId, String phoneNumber, String pinCode, String confirmPinCode, String referralCode, List<Long> industryIds, List<Long> professionIds, List<Long> programIds) {
            this.nationalId = nationalId;
            this.confirmNationalId = confirmNationalId;
            this.phoneNumber = phoneNumber;
            this.pinCode = pinCode;
            this.confirmPinCode = confirmPinCode;
            this.referralCode = referralCode;
            this.industryIds = industryIds;
            this.professionIds = professionIds;

            this.programIds = programIds;
        }

        public void setNationalId(String nationalId) {
            this.nationalId = nationalId;
        }

        public void setConfirmNationalId(String confirmNationalId) {
            this.confirmNationalId = confirmNationalId;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public void setConfirmPinCode(String confirmPinCode) {
            this.confirmPinCode = confirmPinCode;
        }

        public void setReferralCode(String referralCode) {
            this.referralCode = referralCode;
        }

        public void setProfessionIds(List<Long> professionIds) {
            this.professionIds = professionIds;
        }

        public String getNationalId() {
            return nationalId;
        }

        public String getConfirmNationalId() {
            return confirmNationalId;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getPinCode() {
            return pinCode;
        }

        public String getConfirmPinCode() {
            return confirmPinCode;
        }

        public String getReferralCode() {
            return referralCode;
        }

        public Long getReferralCodeAsLong() {
            if (referralCode == null || referralCode.length() == 0) {
                return 0L;
            }
            try {
                Long result = Long.valueOf(referralCode);
                return result;
            } catch (Exception e) {
                return 0L;
            }
        }

        public List<Long> getIndustryIds() {
            return industryIds;
        }

        public List<Long> getProfessionIds() {
            return professionIds;
        }

        public List<Long> getProgramIds() {
            return programIds;
        }
    }
}
