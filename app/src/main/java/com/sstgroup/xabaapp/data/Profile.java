package com.sstgroup.xabaapp.data;


import com.google.gson.annotations.SerializedName;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by julianlubenov on 5/10/17.
 */
@Entity
public class Profile {

    @Id
    @SerializedName("uuid")
    private Long uuid;

    @SerializedName("firstName")
    private String firstName;
    @SerializedName("sirName")
    private String sirName;
    @SerializedName("otherName")
    private String otherName;



    @Generated(hash = 1413825713)
    public Profile(Long uuid, String firstName, String sirName, String otherName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.sirName = sirName;
        this.otherName = otherName;
    }

    @Generated(hash = 782787822)
    public Profile() {
    }

    public Long getUuid() {
        return this.uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSirName() {
        return this.sirName;
    }

    public void setSirName(String sirName) {
        this.sirName = sirName;
    }

    public String getOtherName() {
        return this.otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
}
