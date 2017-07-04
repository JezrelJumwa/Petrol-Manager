package com.sstgroup.xabaapp.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JoinUsersWithProfessions {
    @Id
    private Long id;
    private Long userId;
    private Long professionsId;

    @Generated(hash = 1003950575)
    public JoinUsersWithProfessions(Long id, Long userId, Long professionsId) {
        this.id = id;
        this.userId = userId;
        this.professionsId = professionsId;
    }

    @Generated(hash = 866433188)
    public JoinUsersWithProfessions() {
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

    public Long getProfessionsId() {
        return this.professionsId;
    }

    public void setProfessionsId(Long professionsId) {
        this.professionsId = professionsId;
    }

}
