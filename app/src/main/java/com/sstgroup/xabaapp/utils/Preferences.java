package com.sstgroup.xabaapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String PREFERENCES_NAME = "XabaPreferences";
    private static final String LOCATION_HASH = "location_hash";
    private static final String PROFESSION_HASH = "profession_hash";
    private static final String SELECTED_COUNTRY = "selected_country";
    private static final String SELECTED_LANGUAGE = "selected_language";
    private static final String LOGGED_USER_ID = "logged_user_id";

    private static Long mLogedUserId = null;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditPreference(Context context){
        return getPreferences(context).edit();
    }

    public static void setLocationHash(Context context, String locationHash) {
        getPreferences(context).edit().putString(LOCATION_HASH, locationHash).apply();
    }

    public static String getLocationHash(Context context) {
        return getPreferences(context).getString(LOCATION_HASH, "");
    }

    public static void setProfessionHash(Context context, String locationHash) {
        getPreferences(context).edit().putString(PROFESSION_HASH, locationHash).apply();
    }

    public static String getProfessionHash(Context context) {
        return getPreferences(context).getString(PROFESSION_HASH, "");
    }

    public static void setSelectedCountry(Context context, String country) {
        getPreferences(context).edit().putString(SELECTED_COUNTRY, country).apply();
    }

    public static String getSelectedCountry(Context context) {
        return getPreferences(context).getString(SELECTED_COUNTRY, "");
    }

    public static void setSelectedLanguage(Context context, String language) {
        getPreferences(context).edit().putString(SELECTED_LANGUAGE, language).apply();
    }

    public static String getSelectedLanguage(Context context) {
        return getPreferences(context).getString(SELECTED_LANGUAGE, "");
    }

    public static void setLoggedUserId(Context context, Long loggedUserId) {
        getEditPreference(context).putLong(LOGGED_USER_ID, loggedUserId).apply();
        mLogedUserId = loggedUserId;
    }

    //TODO: implement update loggedUserID if needed
    public static Long getLoggedUserId(Context context){
        if (mLogedUserId != null){
            return mLogedUserId;
        }

        Long loggedUserId = getPreferences(context).getLong(LOGGED_USER_ID, -1);
        mLogedUserId = loggedUserId;
        return  loggedUserId;
    }
}
