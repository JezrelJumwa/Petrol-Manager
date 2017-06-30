package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by julianlubenov on 6/30/17.
 */

public class Program {

    public enum Status {
        @SerializedName("active")
        Active,
        @SerializedName("inactive")
        Inactive,
        @SerializedName("deleted")
        Deleted
    }

    @SerializedName("program_id")
    Long programId;

    @SerializedName("name")
    String name;

    @SerializedName("status")
    Status status;
}
