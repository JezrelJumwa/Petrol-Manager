package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReferredWorkersResponse {

    @SerializedName("referrals")
    private ArrayList<ReferredWorker> items;
    @SerializedName("more_items")
    private Boolean moreItems;
    @SerializedName("next_page_params")
    private String nextPageParams;
    @SerializedName("per_page")
    private Integer perPage;

    public ReferredWorkersResponse(ArrayList<ReferredWorker> items, Boolean moreItems, String nextPageParams, Integer perPage) {
        this.items = items;
        this.moreItems = moreItems;
        this.nextPageParams = nextPageParams;
        this.perPage = perPage;
    }

    public ArrayList<ReferredWorker> getItems() {
        return items;
    }

    public void setItems(ArrayList<ReferredWorker> items) {
        this.items = items;
    }

    public Boolean getMoreItems() {
        return moreItems;
    }

    public void setMoreItems(Boolean moreItems) {
        this.moreItems = moreItems;
    }

    public String getNextPageParams() {
        return nextPageParams;
    }

    public void setNextPageParams(String nextPageParams) {
        this.nextPageParams = nextPageParams;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
}
