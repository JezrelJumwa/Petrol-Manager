package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfessionStructure {

    @SerializedName("industries")
    public ArrayList<Industry> industries;
    @SerializedName("hash")
    public String hash;
    @SerializedName("not_modified")
    public Boolean isNotModified;

    public ArrayList<Industry> getIndustries() {
        return industries;
    }

    public String getHash() {
        return hash;
    }

    public Boolean isNotModified() {
        return isNotModified;
    }
}
