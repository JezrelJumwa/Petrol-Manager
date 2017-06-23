package com.sstgroup.xabaapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionLogFilterDialog extends Dialog {
    @BindView(R.id.grp_radio_buttons_type)
    RadioGroup grpRadioTypes;
    @BindView(R.id.grp_radio_buttons_date)
    RadioGroup grpRadioDate;

    private ClickCallbacks clickCallbacks;
    private int selectedBtnTypeId;
    private int selectedBtnDateId;

    public CommissionLogFilterDialog(@NonNull Context context, ClickCallbacks clickCallbacks, int selectedPeriod, String type) {
        super(context);
        this.clickCallbacks = clickCallbacks;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_commission_log_filter);
        ButterKnife.bind(this);

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
//                switch (selectedButtonId) {
//                    case R.id.rb_all_notification:
//                        if (clicks != null)
//                            clicks.showAll(getContext().getString(R.string.all_notifications));
//                        dismiss();
//                        break;
//                    case R.id.rb_payout_done:
//                        if (clicks != null)
//                            clicks.showPayouts(getContext().getString(R.string.payout_done));
//                        dismiss();
//                        break;
//                    case R.id.rb_validated_registration:
//                        if (clicks != null)
//                            clicks.showValidated(getContext().getString(R.string.validated_registration));
//                        dismiss();
//                        break;
//                    default:
//                        dismiss();
//                        break;
//                }
                dismiss();
                break;
        }
    }


    public interface ClickCallbacks {
        void onApplyFilter(int period, String type);
    }
}
