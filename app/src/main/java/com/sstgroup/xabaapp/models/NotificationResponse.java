package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rosenstoyanov on 6/15/17.
 */

public class NotificationResponse {
    private ArrayList<Notification> items;
    @SerializedName("next_page_params")
    private NextPageParams nextPageParams;
    @SerializedName("per_page")
    private Integer perPage;

    public ArrayList<Notification> getItems() {
        return items;
    }

    public NextPageParams getNextPageParams() {
        return nextPageParams;
    }

    public Integer getPerPage() {
        return perPage;
    }
}
