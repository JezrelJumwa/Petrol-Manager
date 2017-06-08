package com.sstgroup.xabaapp.ui.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.County;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.ui.adapters.EditProfileAdapter;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditProfileActivity extends BaseActivity implements EditProfileAdapter.ClickCallbacks {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_edit_profile)
    RecyclerView mRvEditProfile;

    private List<String> counties;
    private List<String> subCounties;
    private List<String> industries;
    private List<String> categories;
    private List<String> professions;
    private EditProfileAdapter editProfileAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void init() {
        setupToolbar(mToolbar, R.drawable.arrow_back);
        hasBackArrow = true;
        counties = new ArrayList<>();
        subCounties = new ArrayList<>();
        counties = xabaDbHelper.getCounties();
        industries = xabaDbHelper.getIndustries();
        categories = new ArrayList<>();
        professions = new ArrayList<>();
        User user = xabaDbHelper.getLoggedUser(this);
        user.refresh();
        editProfileAdapter = new EditProfileAdapter(this, new ArrayList<>(user.getProfessions()), xabaDbHelper.getCounty(user.getCountyId()), xabaDbHelper.getSubCounty(user.getSubcountyId()));
        mRvEditProfile.setLayoutManager(new LinearLayoutManager(this));
        mRvEditProfile.setAdapter(editProfileAdapter);
    }

    private void showCountiesDialog() {
        CustomChooserDialog dialog = new CustomChooserDialog(this, counties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        String selectedCounty = selectedItems.get(0);
                        editProfileAdapter.setSelectedCounty(xabaDbHelper.getCounty(selectedCounty));
                        subCounties = xabaDbHelper.getSubCounties(selectedCounty);

                    }
                });
        dialog.show();
    }

    private void showSubCountiesDialog() {
        County selectedCounty = editProfileAdapter.getSelectedCounty();

        if (selectedCounty == null) {
            ToastInterval.showToast(this, getString(R.string.choose_county_first));
            return;
        }

        CustomChooserDialog dialog = new CustomChooserDialog(this, subCounties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        String selectedSubCounty = selectedItems.get(0);
                        editProfileAdapter.setSelectedSubCounty(xabaDbHelper.getSubCounty(selectedSubCounty));
                    }
                });
        dialog.show();
    }

    @Override
    public void onItemClick(View view, int position) {
        int id = view.getId();
        switch (id) {
            case R.id.remove_profession_one:
                editProfileAdapter.removeProfessionAt(position);
                break;
            case R.id.add_another_profession:
                editProfileAdapter.addProfession();
                break;
            case R.id.grp_county:
                if (position == 0) {
                    showCountiesDialog();
                } else if (position == 1) {
                    showSubCountiesDialog();
                }
                break;
            case R.id.grp_industry:
//                showIndustriesDialog(position, industries);
                break;
            case R.id.grp_category:
//                showCategoriesDialog(position, categories);
                break;
            case R.id.grp_profession:
//                showProfessionsDialog(position, professions);
                break;
        }
    }

//    private void showIndustriesDialog(final int professionRow, List<String> options) {
//        CustomChooserDialog dialog = new CustomChooserDialog(this, options, true,
//                new CustomChooserDialog.OnCustomChooserDialogClosed() {
//                    @Override
//                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
//                        switch (professionRow) {
//                            case 1:
//
//                                if (!selectedIndustry.equals(selectedItems.get(0))) {
//                                    selectedCategory = "";
//                                    txtCategorySelection.setText(getString(R.string.select_category));
//                                }
//
//                                selectedIndustry = selectedItems.get(0);
//                                txtIndustrySelection.setText(selectedIndustry);
//                                categories = xabaDbHelper.getCategories(selectedIndustry);
//                                break;
//                            case 2:
//
//                                if (!selectedIndustryTwo.equals(selectedItems.get(0))) {
//                                    selectedCategoryTwo = "";
//                                    txtCategorySelectionTwo.setText(getString(R.string.select_category));
//                                }
//
//                                selectedIndustryTwo = selectedItems.get(0);
//                                txtIndustrySelectionTwo.setText(selectedIndustryTwo);
//                                categoriesTwo = xabaDbHelper.getCategories(selectedIndustryTwo);
//                                break;
//                            case 3:
//
//                                if (!selectedIndustryThree.equals(selectedItems.get(0))) {
//                                    selectedCategoryThree = "";
//                                    txtCategorySelectionThree.setText(getString(R.string.select_category));
//                                }
//
//                                selectedIndustryThree = selectedItems.get(0);
//                                txtIndustrySelectionThree.setText(selectedIndustryThree);
//                                categoriesThree = xabaDbHelper.getCategories(selectedIndustryThree);
//                                break;
//                        }
//                    }
//                });
//        dialog.show();
//    }
//
//    private void showCategoriesDialog(final int professionRow, List<String> options) {
//
//        boolean isSuccessValidation = true;
//
//        switch (professionRow) {
//            case 1:
//                if (Validator.isEmpty(selectedIndustry)) {
//                    ToastInterval.showToast(this, getString(R.string.choose_industry_first));
//                    isSuccessValidation = false;
//                }
//                break;
//            case 2:
//                if (Validator.isEmpty(selectedIndustryTwo)) {
//                    ToastInterval.showToast(this, getString(R.string.choose_industry_first));
//                    isSuccessValidation = false;
//                }
//                break;
//            case 3:
//                if (Validator.isEmpty(selectedIndustryThree)) {
//                    ToastInterval.showToast(this, getString(R.string.choose_industry_first));
//                    isSuccessValidation = false;
//                }
//                break;
//        }
//
//        if (!isSuccessValidation) {
//            return;
//        }
//
//        CustomChooserDialog dialog = new CustomChooserDialog(this, options, true,
//                new CustomChooserDialog.OnCustomChooserDialogClosed() {
//                    @Override
//                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
//                        switch (professionRow) {
//                            case 1:
//
//                                if (!selectedCategory.equals(selectedItems.get(0))) {
//                                    selectedProfession = "";
//                                    txtProfessionSelection.setText(getString(R.string.select_profession));
//                                }
//
//                                selectedCategory = selectedItems.get(0);
//                                txtCategorySelection.setText(selectedCategory);
//                                professions = xabaDbHelper.getProfessions(selectedCategory);
//                                break;
//                            case 2:
//
//                                if (!selectedCategoryTwo.equals(selectedItems.get(0))) {
//                                    selectedProfessionTwo = "";
//                                    txtProfessionSelectionTwo.setText(getString(R.string.select_profession));
//                                }
//
//                                selectedCategoryTwo = selectedItems.get(0);
//                                txtCategorySelectionTwo.setText(selectedCategoryTwo);
//                                professionsTwo = xabaDbHelper.getProfessions(selectedCategoryTwo);
//                                break;
//                            case 3:
//
//                                if (!selectedCategoryThree.equals(selectedItems.get(0))) {
//                                    selectedProfessionThree = "";
//                                    txtProfessionSelectionThree.setText(getString(R.string.select_profession));
//                                }
//
//                                selectedCategoryThree = selectedItems.get(0);
//                                txtCategorySelectionThree.setText(selectedCategoryThree);
//                                professionsThree = xabaDbHelper.getProfessions(selectedCategoryThree);
//                                break;
//                        }
//
//                    }
//                });
//        dialog.show();
//    }
//
//    private void showProfessionsDialog(final int professionRow, List<String> options) {
//
//        boolean isSuccessValidation = true;
//
//        switch (professionRow) {
//            case 1:
//                if (Validator.isEmpty(selectedCategory)) {
//                    ToastInterval.showToast(this, getString(R.string.choose_category_first));
//                    isSuccessValidation = false;
//                }
//                break;
//            case 2:
//                if (Validator.isEmpty(selectedCategoryTwo)) {
//                    ToastInterval.showToast(this, getString(R.string.choose_category_first));
//                    isSuccessValidation = false;
//                }
//                break;
//            case 3:
//                if (Validator.isEmpty(selectedCategoryThree)) {
//                    ToastInterval.showToast(this, getString(R.string.choose_category_first));
//                    isSuccessValidation = false;
//                }
//                break;
//        }
//
//        if (!isSuccessValidation) {
//            return;
//        }
//
//        CustomChooserDialog dialog = new CustomChooserDialog(this, options, true,
//                new CustomChooserDialog.OnCustomChooserDialogClosed() {
//                    @Override
//                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
//                        switch (professionRow) {
//                            case 1:
//                                selectedProfession = selectedItems.get(0);
//                                txtProfessionSelection.setText(selectedProfession);
//                                break;
//                            case 2:
//                                selectedProfessionTwo = selectedItems.get(0);
//                                txtProfessionSelectionTwo.setText(selectedProfessionTwo);
//                                break;
//                            case 3:
//                                selectedProfessionThree = selectedItems.get(0);
//                                txtProfessionSelectionThree.setText(selectedProfessionThree);
//                                break;
//                        }
//                    }
//                });
//        dialog.show();
//    }
}
