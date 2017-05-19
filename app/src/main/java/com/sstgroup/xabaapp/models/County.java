package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class County {

    @SerializedName("location_id")
    private Long locationId;
    @SerializedName("name")
    private String name;
    @SerializedName("toponym")
    private String toponym;
    @SerializedName("subcounties")
    public ArrayList<SubCounty> subCounties;

    public Long getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public String getToponym() {
        return toponym;
    }

    public ArrayList<SubCounty> getSubCounties() {
        return subCounties;
    }
}
