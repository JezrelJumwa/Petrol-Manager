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

    @Generated(hash = 1149211111)
    public Profile(Long uuid) {
        this.uuid = uuid;
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
}
