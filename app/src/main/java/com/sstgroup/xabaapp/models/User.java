package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

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
    @SerializedName("professions")
    private ArrayList<Profession> professions;
    @SerializedName("token")
    private Token token;

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCountyId() {
        return countyId;
    }

    public String getSubcountyId() {
        return subcountyId;
    }

    public ArrayList<Profession> getProfessions() {
        return professions;
    }

    public Token getToken() {
        return token;
    }
}
