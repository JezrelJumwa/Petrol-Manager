package com.sstgroup.xabaapp.ui.activities;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.ui.dialogs.CustomChooserDialog;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Validator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EditProfileActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.txt_county_selection)
    TextView txtCountySelection;
    @BindView(R.id.txt_sub_county_selection)
    TextView txtSubCountySelection;

    private List<String> counties;
    private List<String> subCounties;
    private String selectedCounty;
    private String selectedSubCounty;
    private User user;

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
        user = xabaDbHelper.getLoggedUser(this);

        txtCountySelection.setText(xabaDbHelper.getCounty(user.getCountyId()).getName());
        txtSubCountySelection.setText(xabaDbHelper.getSubCounty(user.getSubcountyId()).getName());
    }

    @OnClick({R.id.grp_county, R.id.grp_sub_county})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.grp_county:
                showCountiesDialog();
                break;
            case R.id.grp_sub_county:
                showSubCountiesDialog();
                break;
        }
    }

    private void showCountiesDialog() {
        CustomChooserDialog dialog = new CustomChooserDialog(this, counties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedCounty = selectedItems.get(0);
                        txtCountySelection.setText(selectedCounty);
                        subCounties = xabaDbHelper.getSubCounties(selectedCounty);
                    }
                });
        dialog.show();
    }

    private void showSubCountiesDialog() {

        if (Validator.isEmpty(selectedCounty)) {
            ToastInterval.showToast(this, getString(R.string.choose_county_first));
            return;
        }

        CustomChooserDialog dialog = new CustomChooserDialog(this, subCounties, true,
                new CustomChooserDialog.OnCustomChooserDialogClosed() {
                    @Override
                    public void onCustomChooserDialogClosed(List<String> selectedItems) {
                        selectedSubCounty = selectedItems.get(0);
                        txtSubCountySelection.setText(selectedSubCounty);
                    }
                });
        dialog.show();
    }
}
