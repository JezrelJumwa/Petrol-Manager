package com.sstgroup.xabaapp.network.adapter.objects;

import java.util.ArrayList;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class IndustryModel implements NetworkModelObject {
    private long id;
    private String name;
    private ArrayList<ProfessionCategoryModel> professionCategoryModels = new ArrayList<ProfessionCategoryModel>();

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

    public ArrayList<ProfessionCategoryModel> getProfessionCategoryModels() {
        return professionCategoryModels;
    }

    public void setProfessionCategoryModels(ArrayList<ProfessionCategoryModel> professionCategoryModels) {
        this.professionCategoryModels = professionCategoryModels;
    }
}
