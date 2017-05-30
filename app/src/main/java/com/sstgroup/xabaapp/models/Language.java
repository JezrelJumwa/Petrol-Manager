package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Language {

    @Id
    private Long id;

    @SerializedName("language_code")
    private String languageCode;
    @SerializedName("name")
    private String name;
    @SerializedName("is_right_to_left")
    private String isRightToLeft;

    @Generated(hash = 1762613855)
    public Language(Long id, String languageCode, String name,
            String isRightToLeft) {
        this.id = id;
        this.languageCode = languageCode;
        this.name = name;
        this.isRightToLeft = isRightToLeft;
    }
    @Generated(hash = 1478671802)
    public Language() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLanguageCode() {
        return this.languageCode;
    }
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIsRightToLeft() {
        return this.isRightToLeft;
    }
    public void setIsRightToLeft(String isRightToLeft) {
        this.isRightToLeft = isRightToLeft;
    }
}
