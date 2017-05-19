package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Category {

    @SerializedName("category_id")
    private Long categoryId;
    @SerializedName("name")
    private String name;
    @SerializedName("professions")
    public ArrayList<Profession> professions;

    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Profession> getProfessions() {
        return professions;
    }
}
