package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by julianlubenov on 7/4/17.
 */

@Entity
public class JoinUsersWithProfessionsAndIndustries {
    @Id
    Long id;

    Long userId;
    @SerializedName("profession_id")
    Long professionsId;
    @SerializedName("industry_id")
    Long industryId;

    @ToOne(joinProperty = "professionsId")
    Profession profession;

    @ToOne(joinProperty = "industryId")
    Industry industry;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1373720862)
    private transient JoinUsersWithProfessionsAndIndustriesDao myDao;

    @Generated(hash = 479684797)
    public JoinUsersWithProfessionsAndIndustries(Long id, Long userId,
            Long professionsId, Long industryId) {
        this.id = id;
        this.userId = userId;
        this.professionsId = professionsId;
        this.industryId = industryId;
    }

    @Generated(hash = 1725523394)
    public JoinUsersWithProfessionsAndIndustries() {
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

    public Long getIndustryId() {
        return this.industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    @Generated(hash = 2049070167)
    private transient Long profession__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 508003316)
    public Profession getProfession() {
        Long __key = this.professionsId;
        if (profession__resolvedKey == null
                || !profession__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProfessionDao targetDao = daoSession.getProfessionDao();
            Profession professionNew = targetDao.load(__key);
            synchronized (this) {
                profession = professionNew;
                profession__resolvedKey = __key;
            }
        }
        return profession;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 957292769)
    public void setProfession(Profession profession) {
        synchronized (this) {
            this.profession = profession;
            professionsId = profession == null ? null
                    : profession.getProfessionId();
            profession__resolvedKey = professionsId;
        }
    }

    @Generated(hash = 857531134)
    private transient Long industry__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1329296507)
    public Industry getIndustry() {
        Long __key = this.industryId;
        if (industry__resolvedKey == null || !industry__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IndustryDao targetDao = daoSession.getIndustryDao();
            Industry industryNew = targetDao.load(__key);
            synchronized (this) {
                industry = industryNew;
                industry__resolvedKey = __key;
            }
        }
        return industry;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 659580085)
    public void setIndustry(Industry industry) {
        synchronized (this) {
            this.industry = industry;
            industryId = industry == null ? null : industry.getIndustryId();
            industry__resolvedKey = industryId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 739733744)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getJoinUsersWithProfessionsAndIndustriesDao()
                : null;
    }
}
