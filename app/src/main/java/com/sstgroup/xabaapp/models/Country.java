package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity
public class Country {

    @Id
    @SerializedName("country_id")
    private Long countryId;
    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("currency_id")
    private Long currencyId;
    @SerializedName("timezone_id")
    private Long timezoneId;
    @SerializedName("counties")
    @ToMany(referencedJoinProperty = "countryId")
    private List<County> counties;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(Long timezoneId) {
        this.timezoneId = timezoneId;
    }

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 753128027)
    private transient CountryDao myDao;

    @Generated(hash = 563658970)
    public Country(Long countryId, String name, String code, Long currencyId,
                   Long timezoneId) {
        this.countryId = countryId;
        this.name = name;
        this.code = code;
        this.currencyId = currencyId;
        this.timezoneId = timezoneId;
    }

    @Generated(hash = 668024697)
    public Country() {
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2044699499)
    public List<County> getCounties() {
        if (counties == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CountyDao targetDao = daoSession.getCountyDao();
            List<County> countiesNew = targetDao._queryCountry_Counties(countryId);
            synchronized (this) {
                if (counties == null) {
                    counties = countiesNew;
                }
            }
        }
        return counties;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 89625997)
    public synchronized void resetCounties() {
        counties = null;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1445920194)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCountryDao() : null;
    }
}
