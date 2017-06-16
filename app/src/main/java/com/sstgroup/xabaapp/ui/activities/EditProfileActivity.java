package com.sstgroup.xabaapp.ui.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.Category;
import com.sstgroup.xabaapp.models.County;
import com.sstgroup.xabaapp.models.Industry;
import com.sstgroup.xabaapp.models.Profession;
import com.sstgroup.xabaapp.models.SubCounty;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.UserResponse;
import com.sstgroup.xabaapp.models.errors.ErrorCodeAndMessage;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.adapters.EditProfileAdapter;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        County county = xabaDbHelper.getCounty(user.getCountyId());
        subCounties = xabaDbHelper.getSubCounties(county.getName());

        for (Profession profession : user.getProfessions()){
            profession.setNew(false);
        }

        editProfileAdapter = new EditProfileAdapter(this, new ArrayList<>(user.getProfessions()), county, xabaDbHelper.getSubCounty(user.getSubcountyId()));
        mRvEditProfile.setLayoutManager(new LinearLayoutManager(this));
        mRvEditProfile.setAdapter(editProfileAdapter);

        ((SimpleItemAnimator) mRvEditProfile.getItemAnimator()).setSupportsChangeAnimations(false);
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
            case R.id.btn_save:
                saveProfile();
                break;
        }
    }

    private void saveProfile() {
        showLoader();
        StringBuilder stringBuilder = new StringBuilder();

        County county = editProfileAdapter.getSelectedCounty();
        SubCounty subCounty = editProfileAdapter.getSelectedSubCounty();
        ArrayList<Profession> selectedProfessions = editProfileAdapter.getProfessions();

        if (county == null){
            hideLoader();
            ToastInterval.showToast(this, getString(R.string.select_county));
            return;
        }

        if (subCounty == null){
            hideLoader();
            ToastInterval.showToast(this, getString(R.string.select_sub_county));
            return;
        }

        if(selectedProfessions.isEmpty()){
            hideLoader();
            ToastInterval.showToast(this, getString(R.string.add_at_least_one_profession));
            return;
        }

        stringBuilder.append(Constants.AGENT_APP_KEY);
        stringBuilder.append("=");
        stringBuilder.append(Constants.AGENT_APP_VALUE);
        stringBuilder.append("&");
        stringBuilder.append(Constants.COUNTY_ID);
        stringBuilder.append("=");
        stringBuilder.append(editProfileAdapter.getSelectedCounty().getCountyId());
        stringBuilder.append("&");
        stringBuilder.append(Constants.SUBCOUNTY_ID);
        stringBuilder.append("=" );
        stringBuilder.append(editProfileAdapter.getSelectedSubCounty().getSubCountyId());
        stringBuilder.append("&");
        stringBuilder.append(Constants.TOKEN);
        stringBuilder.append("=");
        stringBuilder.append(XabaApplication.getInstance().getToken().getValue());


        for (Profession profession : selectedProfessions) {
            if(profession == null || profession.getProfessionId() == null){
                hideLoader();
                ToastInterval.showToast(this, getString(R.string.complete_or_remove_professions));
                return;
            }

            stringBuilder.append("&");
            stringBuilder.append(Constants.PROFESSIONS);
            stringBuilder.append("=");
            stringBuilder.append(profession.getProfessionId());
        }

        RequestBody body = RequestBody.create(MediaType.parse("text"), stringBuilder.toString());
        Call<UserResponse> call = RestClient.getService().updateWorker(body);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getUser();
                    xabaDbHelper.updateLoggedUser(user, XabaApplication.getInstance().getToken());
                    finish();
                } else {
                    ErrorCodeAndMessage errorLogin = ErrorUtils.parseErrorCodeMessage(response);

                    if (errorLogin.getErrors().getMessage().equals(Constants.ERROR_UNAUTHORIZED)) {
                        XabaApplication.getInstance().logout();
                        //from this point we logout user
                        return;
                    }

                    if (errorLogin.getErrors().getMessage().equals(Constants.ERROR_STATUS_UNEXPECTED)){
                        ToastInterval.showToast(EditProfileActivity.this, getString(R.string.something_is_wrong));
                    } else {
                        ToastInterval.showToast(EditProfileActivity.this, errorLogin.getErrors().getMessage());
                    }
                }
                hideLoader();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    ToastInterval.showToast(EditProfileActivity.this, getString(R.string.check_your_internet_connection));
                } else {
                    ToastInterval.showToast(EditProfileActivity.this, getString(R.string.something_is_wrong));
                }
                hideLoader();
            }
        });
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
