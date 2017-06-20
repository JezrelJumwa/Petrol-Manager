package com.sstgroup.xabaapp.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rosenstoyanov on 6/14/17.
 */

public class NotificationsFilterDialog extends Dialog {

    @BindView(R.id.grp_radio_buttons)
    RadioGroup grpRadio;

    @BindView(R.id.rb_all_notification)
    RadioButton rbAllSelected;
    @BindView(R.id.rb_payout_done)
    RadioButton rbPayoutDone;
    @BindView(R.id.rb_validated_registration)
    RadioButton rbValidatedRegistration;

    private int selectedButtonId;
    private String selectedText;
    private final Clicks clicks;

    public NotificationsFilterDialog(Activity activity,
                                     NotificationsFilterDialog.Clicks clicks, String selectedText) {
        super(activity);
        this.clicks = clicks;
        this.selectedText = selectedText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_notification_filter);
        ButterKnife.bind(this);

        if (selectedText.equals(getContext().getString(R.string.all_notifications))){
            rbAllSelected.setChecked(true);
        } else if (selectedText.equals(getContext().getString(R.string.payout_done))){
            rbPayoutDone.setChecked(true);
        } else if (selectedText.equals(getContext().getString(R.string.validated_registration))) {
            rbValidatedRegistration.setChecked(true);
        }

        grpRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                selectedButtonId = checkedId;
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
                switch (selectedButtonId) {
                    case R.id.rb_all_notification:
                        if (clicks != null)
                            clicks.showAll(getContext().getString(R.string.all_notifications));
                        dismiss();
                        break;
                    case R.id.rb_payout_done:
                        if (clicks != null)
                            clicks.showPayouts(getContext().getString(R.string.payout_done));
                        dismiss();
                        break;
                    case R.id.rb_validated_registration:
                        if (clicks != null)
                            clicks.showValidated(getContext().getString(R.string.validated_registration));
                        dismiss();
                        break;
                    default:
                        dismiss();
                        break;
                }
                break;
        }
    }

//    @OnCheckedChanged(R.id.grp_radio_buttons)
//    public void radioSelector(RadioGroup radioButton, boolean isChecked) {
//        if (isChecked)
//            selectedButtonId = radioButton.getId();
//    }

    public interface Clicks {
        void showAll(String selectedText);

        void showPayouts(String selectedText);

        void showValidated(String selectedText);
    }
}
