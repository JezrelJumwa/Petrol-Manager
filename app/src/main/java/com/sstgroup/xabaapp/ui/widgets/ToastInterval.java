package com.sstgroup.xabaapp.ui.widgets;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rosenstoyanov on 6/6/17.
 */

public class ToastInterval {
    private static final long TOAST_INTERVAL_MILLIS = 1000 * 3; // 3 sec

    private static long sLastToastTime;

    /**
     * Shows Toast Message only if the time since the previous shown one is >= TOAST_INTERVAL_MILLIS
     * @param ctx
     * @param message
     */
    public static void showToast(Context ctx, CharSequence message) {

        showToast(ctx, message, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context ctx, CharSequence message, int toastDuration) {

        if (ctx != null &&
                ((System.currentTimeMillis() - sLastToastTime) >= TOAST_INTERVAL_MILLIS) ) {

            Toast.makeText(ctx, message, toastDuration).show();
            sLastToastTime = System.currentTimeMillis();
        }
    }

}
