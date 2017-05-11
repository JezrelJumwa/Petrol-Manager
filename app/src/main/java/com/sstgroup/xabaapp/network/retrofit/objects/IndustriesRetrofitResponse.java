package com.sstgroup.xabaapp.network.retrofit.objects;

import com.google.gson.annotations.SerializedName;
import com.sstgroup.xabaapp.network.retrofit.objects.base.RetrofitListResponse;
import com.sstgroup.xabaapp.network.retrofit.objects.base.RetrofitModelObject;

import java.util.ArrayList;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class IndustriesRetrofitResponse extends RetrofitListResponse<IndustriesRetrofitResponse.Industry> {

    public class Industry implements RetrofitModelObject {

    }

    public class Body {
        @SerializedName("industries")
        private ArrayList<Industry> industries;
    }

    @SerializedName("body")
    private Body body;

    @Override
    public ArrayList<Industry> getModelList() {
        if (body != null) {
            return body.industries;
        }
        return null;
    }
}
