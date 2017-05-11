package com.sstgroup.xabaapp.network.retrofit.objects;

import com.google.gson.annotations.SerializedName;
import com.sstgroup.xabaapp.network.retrofit.objects.base.RetrofitListResponse;
import com.sstgroup.xabaapp.network.retrofit.objects.base.RetrofitModelListResponse;
import com.sstgroup.xabaapp.network.retrofit.objects.base.RetrofitModelObject;
import com.sstgroup.xabaapp.network.retrofit.objects.base.RetrofitResponse;

import java.util.ArrayList;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class CountriesRetrofitResponse extends RetrofitListResponse<CountriesRetrofitResponse.Countries> {

    public class Countries implements RetrofitModelObject {

    }

    public class Body {
        @SerializedName("countries")
        private ArrayList<Countries> countries;
    }

    @SerializedName("body")
    private Body body;

    @Override
    public ArrayList<Countries> getModelList() {
        if (this.body != null) {
            return this.body.countries;
        }
        return null;
    }
}