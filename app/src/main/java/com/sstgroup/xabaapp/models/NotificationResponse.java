package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
