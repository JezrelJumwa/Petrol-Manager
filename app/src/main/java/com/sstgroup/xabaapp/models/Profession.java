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
    private Long categoryId;

    @Generated(hash = 1923483507)
    public Profession(Long id, Long professionId, String name, Long categoryId) {
        this.id = id;
        this.professionId = professionId;
        this.name = name;
        this.categoryId = categoryId;
    }

    @Generated(hash = 900874100)
    public Profession() {
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
