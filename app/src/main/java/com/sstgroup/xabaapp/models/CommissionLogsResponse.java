package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommissionLogsResponse {

    @SerializedName("items")
    private ArrayList<CommissionLog> items;
    @SerializedName("more_items")
    private String moreItems;
    @SerializedName("next_page_params")
    private NextPageParams nextPageParams;
    @SerializedName("per_page")
    private Integer perPage;

    public CommissionLogsResponse(ArrayList<CommissionLog> items, String moreItems, NextPageParams nextPageParams, Integer perPage) {
        this.items = items;
        this.moreItems = moreItems;
        this.nextPageParams = nextPageParams;
        this.perPage = perPage;
    }

    public ArrayList<CommissionLog> getItems() {
        return items;
    }

    public void setItems(ArrayList<CommissionLog> items) {
        this.items = items;
    }

    public String getMoreItems() {
        return moreItems;
    }

    public void setMoreItems(String moreItems) {
        this.moreItems = moreItems;
    }

    public NextPageParams getNextPageParams() {
        return nextPageParams;
    }

    public void setNextPageParams(NextPageParams nextPageParams) {
        this.nextPageParams = nextPageParams;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
}
