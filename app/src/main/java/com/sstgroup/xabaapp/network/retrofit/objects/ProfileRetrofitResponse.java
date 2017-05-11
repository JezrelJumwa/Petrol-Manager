package com.sstgroup.xabaapp.network.retrofit.objects;

import com.google.gson.annotations.SerializedName;
import com.sstgroup.xabaapp.network.retrofit.objects.base.RetrofitModelObject;
import com.sstgroup.xabaapp.network.retrofit.objects.base.RetrofitResponse;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class ProfileRetrofitResponse extends RetrofitResponse {

    public class Profile implements RetrofitModelObject {
        private long userId;

        private String firstName;
        private String surname;
        private String lastName;

        private String gender;
        private String country;
        private String county;
        private String subCounty;

        private String phone;
        private String email;

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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
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
    }


    public class Body {
        @SerializedName("profile")
        private Profile profile;
    }

    @SerializedName("body")
    private Body body;

    @Override
    public RetrofitModelObject getModelObject() {
        if (body != null) {
            return body.profile;
        }
        return null;
    }
}
