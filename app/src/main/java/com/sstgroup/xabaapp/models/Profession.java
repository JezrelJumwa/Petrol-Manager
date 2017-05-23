package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

public class Profession {

    @SerializedName("profession_id")
    private Long professionId;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private Long id;

    public Long getProfessionId() {
        return professionId;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
