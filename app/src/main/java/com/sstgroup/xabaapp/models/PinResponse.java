package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import static com.sstgroup.xabaapp.utils.Constants.BODY;
import static com.sstgroup.xabaapp.utils.Constants.STATUS;

public class PinResponse {

    @SerializedName(STATUS)
    private String status;
    @SerializedName(BODY)
    private Worker worker;

    public String getStatus() {
        return status;
    }

    public Worker getWorker() {
        return worker;
    }

    public class Worker {
        private Integer id;
        private String token;

        public Integer getId() {
            return id;
        }

        public String getToken() {
            return token;
        }
    }
}
