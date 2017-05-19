package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Industry {

    @SerializedName("industry_id")
    private Long industryId;
    @SerializedName("name")
    private String name;
    @SerializedName("categories")
    public ArrayList<Category> categories;

    public Long getIndustryId() {
        return industryId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
