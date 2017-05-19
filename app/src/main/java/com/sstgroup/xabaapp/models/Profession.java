package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

public class Profession {

    @SerializedName("profession_id")
    private Long professionId;
    @SerializedName("name")
    private String name;

    public Long getProfessionId() {
        return professionId;
    }

    public String getName() {
        return name;
    }
}
