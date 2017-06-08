package com.sstgroup.xabaapp.ui.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.County;
import com.sstgroup.xabaapp.models.Profession;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.ui.adapters.EditProfileAdapter;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Validator;

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
        User user = xabaDbHelper.getLoggedUser(this);
        editProfileAdapter = new EditProfileAdapter(this, (ArrayList<Profession>) user.getProfessions(), xabaDbHelper.getCounty(user.getCountyId()), xabaDbHelper.getSubCounty(user.getSubcountyId()));
        mRvEditProfile.setLayoutManager(new LinearLayoutManager(this));
        mRvEditProfile.setAdapter(editProfileAdapter);
    }

    private void showCountiesDialog() {
        CustomChooserDialog dialog = new CustomChooserDialog(this, counties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
//                        selectedCounty = selectedItems.get(0);
//                        txtCountySelection.setText(selectedCounty);
//                        subCounties = xabaDbHelper.getSubCounties(selectedCounty);
                    }
                });
        dialog.show();
    }

    private void showSubCountiesDialog() {
        County selectedCounty = editProfileAdapter.getSelectedCounty();

        if (Validator.isEmpty(selectedCounty.getName())) {
            ToastInterval.showToast(this, getString(R.string.choose_county_first));
            return;
        }

        CustomChooserDialog dialog = new CustomChooserDialog(this, subCounties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
//                        selectedSubCounty = selectedItems.get(0);
//                        txtSubCountySelection.setText(selectedSubCounty);
                    }
                });
        dialog.show();
    }

    @Override
    public void onItemClick(View view, int position) {
        if (view != null){
            int id = view.getId();
            switch (id){
                case R.id.remove_profession_one:
                    editProfileAdapter.removeProfessionAt(position);
                    break;
                case R.id.add_another_profession:
                    editProfileAdapter.addProfession();
                    break;
            }
            return;
        }

        if (position == 0){
            showCountiesDialog();
        } else if (position == 1){
            showSubCountiesDialog();
        }
    }
}
