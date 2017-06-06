package com.sstgroup.xabaapp.models;


import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Encryption;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class RegisterWorkerRequestModel {

    private String national_idn;
    private String pin;
    private String phone;
    private String preferred_language;
    private Long country_id;
    private Long county_id;
    private Long subcounty_id;
    private List<Long> professions;
    private Long agent_id;
    private String client;
    private String token;

    public RegisterWorkerRequestModel(String national_idn, String pin, String phone, String preferred_language, Long country_id, Long county_id, Long subcounty_id, List<Long> professions, Long agent_id, String client, String token) {
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
        this.token = token;
    }

    public String generateStringForRequest() {

        String requestBody = "";

        String professionsString = "";

        for (Long professionId : professions) {
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
                "&" + Constants.AGENT_APP_NAME + "=" + client +
                professionsString;

        if (agent_id != null) {
            requestBody += "&" + Constants.AGENT_ID + "=" + agent_id;
        }

        if (token != null && !token.isEmpty()) {
            requestBody += "&" + Constants.TOKEN + "=" + token;
        }

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
