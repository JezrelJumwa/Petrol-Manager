package com.sstgroup.xabaapp.ui.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.Category;
import com.sstgroup.xabaapp.models.County;
import com.sstgroup.xabaapp.models.Industry;
import com.sstgroup.xabaapp.models.Profession;
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
                showIndustriesDialog(position, industries);
                break;
            case R.id.grp_category:
                showCategoriesDialog(position, categories);
                break;
            case R.id.grp_profession:
                showProfessionsDialog(position, professions);
                break;
        }
    }

    private void showIndustriesDialog(final int professionRow, List<String> options) {

        CustomChooserDialog dialog = new CustomChooserDialog(this, options, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        Profession profession = editProfileAdapter.getProfessionAtPosition(professionRow);
                        Industry industry = profession.getIndustry();

                        if (industry != null && industry.getName().equalsIgnoreCase(selectedItems.get(0))) {
                            return;
                        }

                        profession.setCategory(null);
                        profession.setName(null);
                        String selectedIndustry = selectedItems.get(0);
                        Industry industrySelected = xabaDbHelper.getIndustry(selectedIndustry);
                        profession.setIndustry(industrySelected);
                        categories = xabaDbHelper.getCategories(selectedIndustry);
                        editProfileAdapter.updateProfession(professionRow, profession);
                    }
                });
        dialog.show();
    }

    private void showCategoriesDialog(final int professionRow, List<String> options) {

        if (editProfileAdapter.getProfessionAtPosition(professionRow).getIndustry() == null) {
            ToastInterval.showToast(this, getString(R.string.choose_industry_first));
            return;
        }

        CustomChooserDialog dialog = new CustomChooserDialog(this, options, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        Profession profession = editProfileAdapter.getProfessionAtPosition(professionRow);
                        Category category = profession.getCategory();

                        if (category != null && category.getName().equalsIgnoreCase(selectedItems.get(0))) {
                            return;
                        }

                        profession.setName(null);
                        String selectedCategory = selectedItems.get(0);
                        Category categorySelected = xabaDbHelper.getCategory(selectedCategory);
                        profession.setCategory(categorySelected);
                        professions = xabaDbHelper.getProfessions(selectedCategory);
                        editProfileAdapter.updateProfession(professionRow, profession);
                    }
                });
        dialog.show();
    }

    private void showProfessionsDialog(final int professionRow, List<String> options) {
        final Profession profession = editProfileAdapter.getProfessionAtPosition(professionRow);
        Category category = profession.getCategory();

        if (category == null) {
            ToastInterval.showToast(this, getString(R.string.choose_category_first));
            return;
        }

        CustomChooserDialog dialog = new CustomChooserDialog(this, options, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        if (profession.getName() == null || !profession.getName().equalsIgnoreCase(selectedItems.get(0))) {
                            Profession professionSelected = xabaDbHelper.getProfession(selectedItems.get(0));
                            professionSelected.setCategory(profession.getCategory());
                            professionSelected.setIndustry(profession.getIndustry());

                            editProfileAdapter.updateProfession(professionRow, professionSelected);
                        }
                    }
                });
        dialog.show();
    }
}
