package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import static com.sstgroup.xabaapp.utils.Constants.BODY;
import static com.sstgroup.xabaapp.utils.Constants.STATUS;


public class LocationResponse {

    @SerializedName(STATUS)
    private String status;

    @SerializedName(BODY)
    private LocationStructure locationStructure;

    public String getStatus() {
        return status;
    }

    public LocationStructure getLocationStructure() {
        return locationStructure;
    }
}



