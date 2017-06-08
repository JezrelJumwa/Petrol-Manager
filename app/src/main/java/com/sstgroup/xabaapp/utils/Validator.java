package com.sstgroup.xabaapp.utils;


import android.text.TextUtils;


public class Validator {

    public static boolean isEmpty(String field) {
        return TextUtils.isEmpty(field);
    }

    public static boolean isNotNumber(String field) {
        return !TextUtils.isDigitsOnly(field);
    }

}
