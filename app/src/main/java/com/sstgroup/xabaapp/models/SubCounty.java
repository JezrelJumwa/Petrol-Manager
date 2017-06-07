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
    private Long subCountyId;
    @SerializedName("name")
    private String name;
    @SerializedName("toponym")
    private String toponym;
    private long countyId;
    @Generated(hash = 1038651313)
    public SubCounty(Long id, Long subCountyId, String name, String toponym,
            long countyId) {
        this.id = id;
        this.subCountyId = subCountyId;
        this.name = name;
        this.toponym = toponym;
        this.countyId = countyId;
    }
    @Generated(hash = 508343104)
    public SubCounty() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSubCountyId() {
        return this.subCountyId;
    }
    public void setSubCountyId(Long subCountyId) {
        this.subCountyId = subCountyId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getToponym() {
        return this.toponym;
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
}
