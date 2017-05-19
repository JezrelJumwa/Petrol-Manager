package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

class User {

    @SerializedName("id")
    private Long id;
    @SerializedName("status")
    private String status;
    @SerializedName("agent_id")
    private String agentId;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("other_name")
    private String otherName;
    @SerializedName("phone")
    private String phone;
    @SerializedName("gender")
    private String gender;
    @SerializedName("birthdate")
    private String birthdate;
    @SerializedName("country_id")
    private String countryId;
    @SerializedName("county_id")
    private String countyId;
    @SerializedName("subcounty_id")
    private String subcountyId;

    @SerializedName("hash")
    public String hash;
}
