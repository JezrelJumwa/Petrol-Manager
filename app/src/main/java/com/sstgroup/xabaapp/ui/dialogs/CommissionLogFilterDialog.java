package com.sstgroup.xabaapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionLogFilterDialog extends Dialog {
    @BindView(R.id.grp_radio_buttons_type)
    RadioGroup grpRadioTypes;
    @BindView(R.id.grp_radio_buttons_date)
    RadioGroup grpRadioDate;

    @BindView(R.id.rb_period_all)
    RadioButton rbPeriodAll;
    @BindView(R.id.rb_period_today)
    RadioButton rbPeriodToday;
    @BindView(R.id.rb_period_last_30_days)
    RadioButton rbPeriod30Days;
    @BindView(R.id.rb_period_last_week)
    RadioButton rbPeriodLastWeek;


    @BindView(R.id.rb_types_all)
    RadioButton rbTypesAll;
    @BindView(R.id.rb_types_payouts)
    RadioButton rbTypesPayouts;
    @BindView(R.id.rb_validated_registration)
    RadioButton rbTypesValidatedRegistration;

    private ClickCallbacks clickCallbacks;
    private int selectedBtnTypeId;
    private int selectedBtnDateId;
    private Integer selectedPeriod;
    private String selectedType;

    public CommissionLogFilterDialog(@NonNull Context context, ClickCallbacks clickCallbacks, Integer selectedPeriod, String selectedType) {
        super(context);
        this.clickCallbacks = clickCallbacks;
        this.selectedPeriod = selectedPeriod;
        this.selectedType = selectedType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_commission_log_filter);
        ButterKnife.bind(this);

        if (selectedPeriod == null) {
            rbPeriodAll.setChecked(true);
        } else {
            switch (selectedPeriod) {
                case 1:
                    rbPeriodToday.setChecked(true);
                    break;
                case 7:
                    rbPeriodLastWeek.setChecked(true);
                    break;
                case 30:
                    rbPeriod30Days.setChecked(true);
                    break;
            }
        }

        if (selectedType.equals(Constants.COMMISSIONS_TYPE_ALL)) {
            rbTypesAll.setChecked(true);
        } else if (selectedType.equals(Constants.COMMISSIONS_TYPE_PAYOUT)) {
            rbTypesPayouts.setChecked(true);
        } else if (selectedType.equals(Constants.COMMISSIONS_TYPE_REFERRAL_VALIDATION)) {
            rbTypesValidatedRegistration.setChecked(true);
        }

        grpRadioTypes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                selectedBtnTypeId = checkedId;
            }
        });

        grpRadioDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                selectedBtnDateId = checkedId;
            }
        });
    }

    @OnClick({R.id.btn_cancel, R.id.btn_apply})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_apply:
                switch (selectedBtnDateId) {
                    case R.id.rb_period_all:
                        selectedPeriod = Constants.COMMISSIONS_PERIOD_ALL;
                        break;
                    case R.id.rb_period_today:
                        selectedPeriod = Constants.COMMISSIONS_PERIOD_TODAY;
                        break;
                    case R.id.rb_period_last_week:
                        selectedPeriod = Constants.COMMISSIONS_PERIOD_LAST_WEEK;
                        break;
                    case R.id.rb_period_last_30_days:
                        selectedPeriod = Constants.COMMISSIONS_PERIOD_LAST_30_DAYS;
                        break;
                }

                switch (selectedBtnTypeId){
                    case R.id.rb_types_all:
                        selectedType = Constants.COMMISSIONS_TYPE_ALL;
                        break;
                    case R.id.rb_types_payouts:
                        selectedType = Constants.COMMISSIONS_TYPE_PAYOUT;
                        break;
                    case R.id.rb_validated_registration:
                        selectedType = Constants.COMMISSIONS_TYPE_REFERRAL_VALIDATION;
                        break;
                }

                if (clickCallbacks != null)
                    clickCallbacks.onApplyFilter(selectedPeriod, selectedType);
                dismiss();
                break;
        }
    }


    public interface ClickCallbacks {
        void onApplyFilter(Integer period, String type);
    }
}
