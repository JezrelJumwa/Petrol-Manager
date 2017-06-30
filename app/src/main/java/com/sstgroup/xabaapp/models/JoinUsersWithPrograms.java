package com.sstgroup.xabaapp.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by julianlubenov on 6/30/17.
 */

@Entity
public class JoinUsersWithPrograms {
    @Id
    private Long id;

    private Long userId;
    private Long programId;
    @Generated(hash = 7494312)
    public JoinUsersWithPrograms(Long id, Long userId, Long programId) {
        this.id = id;
        this.userId = userId;
        this.programId = programId;
    }
    @Generated(hash = 686649866)
    public JoinUsersWithPrograms() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getProgramId() {
        return this.programId;
    }
    public void setProgramId(Long programId) {
        this.programId = programId;
    }
}
