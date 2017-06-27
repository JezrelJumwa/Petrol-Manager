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
        @SerializedName("code")
        private Integer code;
        @SerializedName("message")
        private String message;
        @SerializedName("national_idn")
        private List<String> nationalIdErrors;
        @SerializedName("agent_id")
        private List<String> agentIdErrors;

        public Error(Integer code, String message, List<String> nationalIdErrors, List<String> agentIdErrors) {
            this.code = code;
            this.message = message;
            this.nationalIdErrors = nationalIdErrors;
            this.agentIdErrors = agentIdErrors;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getNationalIdErrors() {
            return nationalIdErrors;
        }

        public List<String> getAgentIdErrors() {
            return agentIdErrors;
        }
    }
}
