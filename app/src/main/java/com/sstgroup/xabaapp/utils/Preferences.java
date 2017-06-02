package com.sstgroup.xabaapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String PREFERENCES_NAME = "XabaPreferences";
    public static final String LOCATION_HASH = "location_hash";
    public static final String PROFESSION_HASH = "profession_hash";
    public static final String SELECTED_COUNTRY = "selected_country";
    public static final String SELECTED_LANGUAGE = "selected_language";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
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
}
