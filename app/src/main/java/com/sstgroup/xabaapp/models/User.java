package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {

    @Id
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
    //TODO: do one to many relation
    @Transient
    @SerializedName("professions")
    private ArrayList<Profession> professions;
    //TODO: do one to one relation
    @Transient
    @SerializedName("token")
    private Token token;

    @Generated(hash = 1223420695)
    public User(Long id, String status, String agentId, String firstName,
            String lastName, String otherName, String phone, String gender,
            String birthdate, String countryId, String countyId,
            String subcountyId) {
        this.id = id;
        this.status = status;
        this.agentId = agentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.countryId = countryId;
        this.countyId = countyId;
        this.subcountyId = subcountyId;
    }

    @Generated(hash = 586692638)
    public User() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public void setSubcountyId(String subcountyId) {
        this.subcountyId = subcountyId;
    }
}
