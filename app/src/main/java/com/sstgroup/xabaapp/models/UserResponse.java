
package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import static com.sstgroup.xabaapp.utils.Constants.BODY;
import static com.sstgroup.xabaapp.utils.Constants.STATUS;

public class UserResponse {

    @SerializedName(STATUS)
    private String status;
    @SerializedName(BODY)
    private User user;
}




