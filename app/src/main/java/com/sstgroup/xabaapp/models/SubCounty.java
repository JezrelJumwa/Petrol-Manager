package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

public class SubCounty {

    @SerializedName("location_id")
    private Long locationId;
    @SerializedName("name")
    private String name;
    @SerializedName("toponym")
    private String toponym;

    public Long getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public String getToponym() {
        return toponym;
    }
}
