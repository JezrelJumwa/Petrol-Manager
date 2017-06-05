package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Profession {

    @Id
    @SerializedName("profession_id")
    private Long professionId;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private Long loggedUserProfessionId;
    @Generated(hash = 1568584522)
    public Profession(Long professionId, String name, Long loggedUserProfessionId) {
        this.professionId = professionId;
        this.name = name;
        this.loggedUserProfessionId = loggedUserProfessionId;
    }
    @Generated(hash = 900874100)
    public Profession() {
    }
    public Long getProfessionId() {
        return this.professionId;
    }
    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getLoggedUserProfessionId() {
        return this.loggedUserProfessionId;
    }
    public void setLoggedUserProfessionId(Long loggedUserProfessionId) {
        this.loggedUserProfessionId = loggedUserProfessionId;
    }

}
