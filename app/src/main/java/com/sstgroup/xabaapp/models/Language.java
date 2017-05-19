package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("language_code")
    private String languageCode;
    @SerializedName("name")
    private String name;
    @SerializedName("is_right_to_left")
    private String isRightToLeft;

    public String getLanguageCode() {
        return languageCode;
    }

    public String getName() {
        return name;
    }

    public String getIsRightToLeft() {
        return isRightToLeft;
    }
}
