package com.sstgroup.xabaapp.network.adapter.objects;

import java.util.ArrayList;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class CountyModel implements NetworkModelObject {
    private long id;
    private String name;
    private ArrayList<SubCountyModel> subCounties = new ArrayList<SubCountyModel>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubCountyModel> getSubCounties() {
        return subCounties;
    }

    public void setSubCounties(ArrayList<SubCountyModel> subCounties) {
        this.subCounties = subCounties;
    }
}
