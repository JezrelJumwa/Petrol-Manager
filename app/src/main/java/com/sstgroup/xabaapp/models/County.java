package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity
public class County {

    @Id
    private Long id;

    @SerializedName("location_id")
    private Long locationId;
    @SerializedName("name")
    private String name;
    @SerializedName("toponym")
    private String toponym;
    @SerializedName("subcounties")
    @ToMany(referencedJoinProperty = "countyId")
    private List<SubCounty> subCounties;
    private long countryId;
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

    public void setSubCounties(List<SubCounty> subCounties) {
        this.subCounties = subCounties;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1199581902)
    private transient CountyDao myDao;

    @Generated(hash = 366915973)
    public County(Long id, Long locationId, String name, String toponym, long countryId) {
        this.id = id;
        this.locationId = locationId;
        this.name = name;
        this.toponym = toponym;
        this.countryId = countryId;
    }

    @Generated(hash = 1991272252)
    public County() {
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 932039343)
    public List<SubCounty> getSubCounties() {
        if (subCounties == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SubCountyDao targetDao = daoSession.getSubCountyDao();
            List<SubCounty> subCountiesNew = targetDao._queryCounty_SubCounties(id);
            synchronized (this) {
                if (subCounties == null) {
                    subCounties = subCountiesNew;
                }
            }
        }
        return subCounties;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 703774556)
    public synchronized void resetSubCounties() {
        subCounties = null;
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
    @Generated(hash = 1951788226)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCountyDao() : null;
    }
}
