package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rosenstoyanov on 6/15/17.
 */

public class ReferredWorkersResponse {

    @SerializedName("referrals")
    private ArrayList<ReferredWorker> items;
    @SerializedName("more_items")
    private String moreItems;
    @SerializedName("next_page_params")
    private String nextPageParams;
    @SerializedName("per_page")
    private Integer perPage;

    public ReferredWorkersResponse(ArrayList<ReferredWorker> items, String moreItems, String nextPageParams, Integer perPage) {
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

    public String getMoreItems() {
        return moreItems;
    }

    public void setMoreItems(String moreItems) {
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
