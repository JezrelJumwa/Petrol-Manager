package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rosenstoyanov on 6/21/17.
 */
public class UserCommissions {
    @SerializedName("current_balance")
    private Integer currentBalance;
    @SerializedName("payout_threshold")
    private Integer payoutThreshold;
    @SerializedName("total_referrals")
    private Integer totalReferrals;
    @SerializedName("per_worker")
    private Integer perWorker;
    @SerializedName("currency_id")
    private Long currencyId;

    public Integer getCurrentBalance() {
        return currentBalance;
    }

    public Integer getPayoutThreshold() {
        return payoutThreshold;
    }

    public Integer getTotalReferrals() {
        return totalReferrals;
    }

    public Integer getPerWorker() {
        return perWorker;
    }

    public Long getCurrencyId() {
        return currencyId;
    }
}
