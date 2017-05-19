package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationStructure {

    @SerializedName("languages")
    public ArrayList<Language> languages;
    @SerializedName("currencies")
    public ArrayList<Currency> currencies;
    @SerializedName("countries")
    public ArrayList<Country> countries;
    @SerializedName("timezones")
    public ArrayList<Timezone> timezones;
    @SerializedName("hash")
    public String hash;

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public ArrayList<Timezone> getTimezones() {
        return timezones;
    }

    public String getHash() {
        return hash;
    }
}
