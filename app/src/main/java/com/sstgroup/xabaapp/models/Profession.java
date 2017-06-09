package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class Profession {

    @Id
    @SerializedName("profession_id")
    private Long professionId;
    @SerializedName("name")
    private String name;
    @Transient
    @SerializedName("id")
    private Long loggedUserProfessionId;
    @Transient
    @Expose
    private Industry industry;
    @Transient
    @Expose
    private Category category;
    @Transient
    @Expose
    private boolean isNew;

    @Generated(hash = 1426892760)
    public Profession(Long professionId, String name) {
        this.professionId = professionId;
        this.name = name;
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

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Industry getIndustry() {
        return industry;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
