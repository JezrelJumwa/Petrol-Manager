
package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import static com.sstgroup.xabaapp.utils.Constants.BODY;
import static com.sstgroup.xabaapp.utils.Constants.STATUS;

public class UserResponse {

    @SerializedName(STATUS)
    private String status;
    @SerializedName(BODY)
    private Worker worker;

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return worker.getUser();
    }

    private class Worker {
        @SerializedName("worker")
        private User user;

        private User getUser() {
            return user;
        }
    }
}




