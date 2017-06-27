package com.sstgroup.xabaapp.models.errors;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ErrorRegisterWorkerError {
    @SerializedName("national_idn")
    private ArrayList<String> nationalIdErrors;
    @SerializedName("agent_id")
    private ArrayList<String> referralCodeErrors;

    public ErrorRegisterWorkerError(ArrayList<String> nationalIdErrors, ArrayList<String> referralCodeErrors) {
        this.nationalIdErrors = nationalIdErrors;
        this.referralCodeErrors = referralCodeErrors;
    }

    public ArrayList<String> getNationalIdErrors() {
        return nationalIdErrors;
    }

    public ArrayList<String> getReferralCodeErrors() {
        return referralCodeErrors;
    }
}
