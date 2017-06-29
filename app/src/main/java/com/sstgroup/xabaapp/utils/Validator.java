package com.sstgroup.xabaapp.utils;


import android.text.TextUtils;
import android.util.Patterns;


public class Validator {

    public static boolean isEmpty(String field) {
        return TextUtils.isEmpty(field);
    }

    public static boolean isNotNumber(String field) {
        return !TextUtils.isDigitsOnly(field);
    }

    public static boolean isNotCorrectNationalIdSize(String field) {
        return field.length() > 10;
    }

    public static boolean isNotCorrectPinCodeSize(String field) {
        return field.length() != 4;
    }

    public static boolean isCorrectPhoneNumber(String field) {
        return !isEmpty(field) && field.length() >= 12 && field.length() <= 13 && (field.startsWith("2547") || field.startsWith("+2547"));
    }

    public static boolean isEmailAddress(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isMatch(String fieldOne, String fieldWwo) {
        return !isEmpty(fieldOne)
                && !isEmpty(fieldWwo)
                && fieldOne.equals(fieldWwo);
    }
}
