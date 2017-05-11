package com.sstgroup.xabaapp.network.adapter.objects;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class SubCountyModel implements NetworkModelObject {
    private long id;
    private String name;

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
}
