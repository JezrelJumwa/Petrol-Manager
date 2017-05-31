package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SubCounty {

    @Id
    private Long id;

    @SerializedName("location_id")
    private Long locationId;
    @SerializedName("name")
    private String name;
    @SerializedName("toponym")
    private String toponym;
    private long countyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToponym() {
        return toponym;
    }

    public void setToponym(String toponym) {
        this.toponym = toponym;
    }

    public long getCountyId() {
        return this.countyId;
    }

    public void setCountyId(long countyId) {
        this.countyId = countyId;
    }

    @Generated(hash = 2111989345)
    public SubCounty(Long id, Long locationId, String name, String toponym,
                     long countyId) {
        this.id = id;
        this.locationId = locationId;
        this.name = name;
        this.toponym = toponym;
        this.countyId = countyId;
    }

    @Generated(hash = 508343104)
    public SubCounty() {
    }
}
