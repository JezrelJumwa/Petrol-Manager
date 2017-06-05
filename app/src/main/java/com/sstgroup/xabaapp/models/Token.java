package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Token {

    @Id
    private Long id;

    @SerializedName("value")
    private String value;
    @SerializedName("expires")
    private String expires;

    @Generated(hash = 1780524627)
    public Token(Long id, String value, String expires) {
        this.id = id;
        this.value = value;
        this.expires = expires;
    }

    @Generated(hash = 79808889)
    public Token() {
    }

    public String getValue() {
        return value;
    }

    public String getExpires() {
        return expires;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}
