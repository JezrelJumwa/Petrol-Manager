package com.sstgroup.xabaapp.network.adapter.objects;

import java.util.ArrayList;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class ProfessionCategoryModel implements NetworkModelObject {
    private long id;
    private String name;
    private ArrayList<ProfessionModel> professionModels = new ArrayList<ProfessionModel>();

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

    public ArrayList<ProfessionModel> getProfessionModels() {
        return professionModels;
    }

    public void setProfessionModels(ArrayList<ProfessionModel> professionModels) {
        this.professionModels = professionModels;
    }
}
