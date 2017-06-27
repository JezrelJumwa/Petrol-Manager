package com.sstgroup.xabaapp.models.errors;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorRegisterWorker {

    @SerializedName("status")
    private String status;
    @SerializedName("errors")
    private Error errors;

    public ErrorRegisterWorker(String status, Error error) {
        this.status = status;
        this.errors = error;
    }

    public String getStatus() {
        return status;
    }

    public Error getError() {
        return errors;
    }

    public static class Error {
        @SerializedName("national_idn")
        private List<String> nationalIdErrors;
        @SerializedName("agent_id")
        private List<String> referralCodeErrors;

        public Error(List<String> nationalIdErrors, List<String> referralCodeErrors) {
            this.nationalIdErrors = nationalIdErrors;
            this.referralCodeErrors = referralCodeErrors;
        }

        public List<String> getNationalIdErrors() {
            return nationalIdErrors;
        }

        public List<String> getReferralCodeErrors() {
            return referralCodeErrors;
        }
    }
}
