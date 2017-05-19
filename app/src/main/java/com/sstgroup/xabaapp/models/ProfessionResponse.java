package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import static com.sstgroup.xabaapp.utils.Constants.BODY;
import static com.sstgroup.xabaapp.utils.Constants.STATUS;

public class ProfessionResponse {

    @SerializedName(STATUS)
    private String status;

    @SerializedName(BODY)
    private ProfessionStructure professionStructure;

    public String getStatus() {
        return status;
    }

    public ProfessionStructure getProfessionStructure() {
        return professionStructure;
    }
}
