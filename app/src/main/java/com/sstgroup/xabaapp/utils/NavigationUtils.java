package com.sstgroup.xabaapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

public class NavigationUtils {
    public static void startSingleActivity(Context mContext, Class<?> cls) {
        Intent intent = new Intent().setClass(mContext, cls);
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
//                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }

    public static void startSingleActivityWithExtra(Context mContext, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent().setClass(mContext, cls);
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
//                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }

    public static void startSingleActivityNoAnimation(Context mContext, Class<?> cls) {
        Intent intent = new Intent().setClass(mContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mContext.startActivity(intent);
    }

    public static void startActivity(Context mContext, Class<?> cls) {
        Intent intent = new Intent().setClass(mContext, cls);
        mContext.startActivity(intent);
    }

    public static void startActivityForResult(Activity mContext, int code,  Class<?> cls) {
        Intent intent = new Intent().setClass(mContext, cls);
        mContext.startActivityForResult(intent, code);
    }

    public static void startActivityWithExtra(Context mContext,Bundle bundle, Class<?> cls) {
        Intent intent = new Intent().setClass(mContext, cls);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public static void startActivityWithCleanTopAndExtra(Context mContext,Bundle bundle, Class<?> cls) {
        Intent intent = new Intent().setClass(mContext, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }

    public static void startActivityWithExtraAndTransition(Activity activity, Bundle bundle, Class<?> cls, ActivityOptionsCompat options) {
        Intent intent = new Intent().setClass(activity, cls);
        intent.putExtras(bundle);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }


    public static boolean validateIntent(Intent intent, Activity activity){
        PackageManager packageManager = activity.getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            activity.startActivity(intent);
            return true;
        } else {
//            SnackbarInterval.showSnackbar(activity.findViewById(android.R.id.content), activity.getString(R.string.invalid_url));
            return false;
        }
    }
}
