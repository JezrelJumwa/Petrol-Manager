package com.sstgroup.xabaapp.models;


import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Encryption;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SignupRequestModel {

    private String national_idn;
    private String pin;
    private String phone;
    private String preferred_language;
    private Integer country_id;
    private Integer county_id;
    private Integer subcounty_id;
    private ArrayList<Integer> professions;
    private Integer agent_id;
    private String client;


    public SignupRequestModel(String national_idn, String pin, String phone, String preferred_language, Integer country_id, Integer county_id, Integer subcounty_id, ArrayList<Integer> professions, Integer agent_id, String client) {
        this.national_idn = national_idn;
        this.pin = pin;
        this.phone = phone;
        this.preferred_language = preferred_language;
        this.country_id = country_id;
        this.county_id = county_id;
        this.subcounty_id = subcounty_id;
        this.professions = professions;
        this.agent_id = agent_id;
        this.client = client;
    }

    public String generateStringForRequest() {

        String requestBody = "";

        String professionsString = "";

        for (Integer professionId : professions) {
            professionsString += "&" + Constants.PROFESSIONS + "=" + professionId;
        }

        requestBody = Constants.AGENT_APP_NAME + "=" + client +
                "&" + Constants.NATIONAL_ID + "=" + getEncryptedAndURLEncodedNationalIdn() +
                "&" + Constants.PIN + "=" + pin +
                "&" + Constants.PHONE + "=" + phone +
                "&" + Constants.PREFERRED_LANGUAGE + "=" + preferred_language +
                "&" + Constants.COUNTRY_ID + "=" + country_id +
                "&" + Constants.COUNTY_ID + "=" + county_id +
                "&" + Constants.SUBCOUNTY_ID + "=" + subcounty_id +
                "&" + Constants.AGENT_ID + "=" + agent_id +
                "&" + Constants.AGENT_APP_NAME + "=" + client +
                professionsString;

        return requestBody;
    }

    private String getEncryptedAndURLEncodedNationalIdn() {

        String text = Encryption.encryptionRSA(national_idn);
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return text;
    }
}
