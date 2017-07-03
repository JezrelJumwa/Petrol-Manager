package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by julianlubenov on 6/30/17.
 */

@Entity
public class Program {

    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_INACTIVE = "inactive";
    public static final String STATUS_DELETED = "deleted";

    @Id
    @SerializedName("program_id")
    Long programId;

    @Transient
    @SerializedName("id")
    private Long loggedUserProgramId;

    @SerializedName("name")
    String name;

    @SerializedName("status")
    String status;

    @Generated(hash = 515163722)
    public Program(Long programId, String name, String status) {
        this.programId = programId;
        this.name = name;
        this.status = status;
    }

    @Generated(hash = 775603163)
    public Program() {
    }

    public Long getLoggedUserProgramId() {
        return loggedUserProgramId;
    }

    public void setLoggedUserProgramId(Long loggedUserProgramId) {
        this.loggedUserProgramId = loggedUserProgramId;
    }

    public Long getProgramId() {
        return this.programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
