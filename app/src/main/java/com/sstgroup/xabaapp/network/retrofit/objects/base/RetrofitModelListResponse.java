package com.sstgroup.xabaapp.network.retrofit.objects.base;


import java.util.ArrayList;

/**
 * Created by julianlubenov on 5/11/17.
 */

public interface RetrofitModelListResponse<T> {
    public ArrayList<T> getModelList();
}
