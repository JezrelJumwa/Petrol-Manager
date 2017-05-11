package com.sstgroup.xabaapp.network.adapter.objects;

import java.util.ArrayList;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class CountryModel implements NetworkModelObject {
    private long id;
    private String name;
    private ArrayList<CountyModel> counties = new ArrayList<CountyModel>();

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

    public ArrayList<CountyModel> getCounties() {
        return counties;
    }

    public void setCounties(ArrayList<CountyModel> counties) {
        this.counties = counties;
    }
}
