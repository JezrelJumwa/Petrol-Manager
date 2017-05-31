package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Profession {

    @Id
    private Long id;

    @SerializedName("profession_id")
    private Long professionId;
    @SerializedName("name")
    private String name;
    private long categoryId;

    @Generated(hash = 91061013)
    public Profession(Long id, Long professionId, String name, long categoryId) {
        this.id = id;
        this.professionId = professionId;
        this.name = name;
        this.categoryId = categoryId;
    }

    @Generated(hash = 900874100)
    public Profession() {
    }

    public Long getProfessionId() {
        return professionId;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
