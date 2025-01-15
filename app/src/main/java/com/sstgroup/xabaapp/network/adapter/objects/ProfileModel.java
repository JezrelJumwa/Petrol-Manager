package com.sstgroup.xabaapp.network.adapter.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by JezrelJumwa on 15/01/25.
 */

public class ProfileModel implements NetworkModelObject {
    private enum Gender {Male, Female}

    private long userId;

    private String firstName;
    private String surname;
    private String lastName;

    private Gender gender;
    private String country;
    private String county;
    private String subCounty;

    private String phone;
    private String email;

    private ArrayList<ProfessionModel> professions = new ArrayList<ProfessionModel>();

    private Date createdAt;
    private Date updatedAt;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ProfessionModel> getProfessions() {
        return professions;
    }

    public void setProfessions(ArrayList<ProfessionModel> professions) {
        this.professions = professions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
