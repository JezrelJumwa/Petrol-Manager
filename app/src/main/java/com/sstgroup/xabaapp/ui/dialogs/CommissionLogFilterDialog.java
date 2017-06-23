package com.sstgroup.xabaapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

public class CommissionLogFilterDialog extends Dialog {

    public CommissionLogFilterDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public interface ClickCallbacks {
        void onApplyFilter(int period, int type);
    }
}
