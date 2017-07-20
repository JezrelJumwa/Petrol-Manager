package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

public class UserCommissions {
    @SerializedName("current_balance")
    private Float currentBalance;
    @SerializedName("payout_threshold")
    private Float payoutThreshold;
    @SerializedName("total_referrals")
    private Integer totalReferrals;
    @SerializedName("per_worker")
    private Float perWorker;
    @SerializedName("currency_id")
    private Long currencyId;

    public Float getCurrentBalance() {
        return currentBalance;
    }

    public Float getPayoutThreshold() {
        return payoutThreshold;
    }

    public Integer getTotalReferrals() {
        return totalReferrals;
    }

    public Float getPerWorker() {
        return perWorker;
    }

    public Long getCurrencyId() {
        return currencyId;
    }
}
