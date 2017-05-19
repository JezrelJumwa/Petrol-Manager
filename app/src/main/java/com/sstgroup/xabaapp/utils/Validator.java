package com.sstgroup.xabaapp.utils;


import android.text.TextUtils;
import android.util.Patterns;

public class Validator {

    public static boolean isEmpty(String field) {
        return TextUtils.isEmpty(field);
    }

    public static boolean isPhoneValid(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean doPasswordsMatch(String newPassword, String confirmNewPassword) {
        return newPassword != null && !isEmpty(newPassword)
                && confirmNewPassword != null && !isEmpty(confirmNewPassword)
                && newPassword.equals(confirmNewPassword);
    }

    public static boolean isPasswordLengthCorrect(String password) {
        return password != null && !isEmpty(password) && !(password.length() < 8);
    }
}
