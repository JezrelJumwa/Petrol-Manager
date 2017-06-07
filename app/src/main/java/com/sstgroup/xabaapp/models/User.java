package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class User {

    @Id
    @SerializedName("id")
    private Long id;
    @SerializedName("status")
    private String status;
    @SerializedName("agent_id")
    private String agentId;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("other_name")
    private String otherName;
    @SerializedName("phone")
    private String phone;
    @SerializedName("gender")
    private String gender;
    @SerializedName("birthdate")
    private String birthdate;
    @SerializedName("country_id")
    private Long countryId;
    @ToOne(joinProperty = "countryId")
    private Country country;
    @SerializedName("county_id")
    private Long countyId;
    @ToOne(joinProperty = "countyId")
    private County county;
    @SerializedName("subcounty_id")
    private Long subcountyId;
    @ToOne(joinProperty = "subcountyId")
    private SubCounty subCounty;
    @SerializedName("professions")
    @ToMany
    @JoinEntity(
            entity = JoinUsersWithProfessions.class,
            sourceProperty = "userId",
            targetProperty = "professionsId"
    )
    private List<Profession> professions;
    @SerializedName("token")
    @ToOne(joinProperty = "tokenId")
    private Token token;
    private Long tokenId;
    @SerializedName("is_phone_verified")
    private Boolean isPhoneVerified;
    @SerializedName("is_default_pin")
    private Boolean isDefaultPin;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;

    @Generated(hash = 2026033923)
    public User(Long id, String status, String agentId, String firstName,
            String lastName, String otherName, String phone, String gender,
            String birthdate, Long countryId, Long countyId, Long subcountyId,
            Long tokenId, Boolean isPhoneVerified, Boolean isDefaultPin) {
        this.id = id;
        this.status = status;
        this.agentId = agentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.countryId = countryId;
        this.countyId = countyId;
        this.subcountyId = subcountyId;
        this.tokenId = tokenId;
        this.isPhoneVerified = isPhoneVerified;
        this.isDefaultPin = isDefaultPin;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    @Generated(hash = 1591299782)
    private transient Long country__resolvedKey;
    @Generated(hash = 9373241)
    private transient Long county__resolvedKey;
    @Generated(hash = 1556866286)
    private transient Long subCounty__resolvedKey;
    @Generated(hash = 2059307683)
    private transient Long token__resolvedKey;

    public Token getTokenFromWS(){
        return this.token;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgentId() {
        return this.agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return this.otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Long getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCountyId() {
        return this.countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public Long getSubcountyId() {
        return this.subcountyId;
    }

    public void setSubcountyId(Long subcountyId) {
        this.subcountyId = subcountyId;
    }

    public Long getTokenId() {
        return this.tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public Boolean getIsPhoneVerified() {
        return this.isPhoneVerified;
    }

    public void setIsPhoneVerified(Boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public Boolean getIsDefaultPin() {
        return this.isDefaultPin;
    }

    public void setIsDefaultPin(Boolean isDefaultPin) {
        this.isDefaultPin = isDefaultPin;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1271419885)
    public Country getCountry() {
        Long __key = this.countryId;
        if (country__resolvedKey == null || !country__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CountryDao targetDao = daoSession.getCountryDao();
            Country countryNew = targetDao.load(__key);
            synchronized (this) {
                country = countryNew;
                country__resolvedKey = __key;
            }
        }
        return country;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1795445608)
    public void setCountry(Country country) {
        synchronized (this) {
            this.country = country;
            countryId = country == null ? null : country.getCountryId();
            country__resolvedKey = countryId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 315446817)
    public County getCounty() {
        Long __key = this.countyId;
        if (county__resolvedKey == null || !county__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CountyDao targetDao = daoSession.getCountyDao();
            County countyNew = targetDao.load(__key);
            synchronized (this) {
                county = countyNew;
                county__resolvedKey = __key;
            }
        }
        return county;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 175798542)
    public void setCounty(County county) {
        synchronized (this) {
            this.county = county;
            countyId = county == null ? null : county.getCountyId();
            county__resolvedKey = countyId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 261222071)
    public SubCounty getSubCounty() {
        Long __key = this.subcountyId;
        if (subCounty__resolvedKey == null
                || !subCounty__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SubCountyDao targetDao = daoSession.getSubCountyDao();
            SubCounty subCountyNew = targetDao.load(__key);
            synchronized (this) {
                subCounty = subCountyNew;
                subCounty__resolvedKey = __key;
            }
        }
        return subCounty;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2107527471)
    public void setSubCounty(SubCounty subCounty) {
        synchronized (this) {
            this.subCounty = subCounty;
            subcountyId = subCounty == null ? null : subCounty.getSubCountyId();
            subCounty__resolvedKey = subcountyId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1690932140)
    public Token getToken() {
        Long __key = this.tokenId;
        if (token__resolvedKey == null || !token__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TokenDao targetDao = daoSession.getTokenDao();
            Token tokenNew = targetDao.load(__key);
            synchronized (this) {
                token = tokenNew;
                token__resolvedKey = __key;
            }
        }
        return token;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1839028877)
    public void setToken(Token token) {
        synchronized (this) {
            this.token = token;
            tokenId = token == null ? null : token.getId();
            token__resolvedKey = tokenId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 542178971)
    public List<Profession> getProfessions() {
        if (professions == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProfessionDao targetDao = daoSession.getProfessionDao();
            List<Profession> professionsNew = targetDao._queryUser_Professions(id);
            synchronized (this) {
                if (professions == null) {
                    professions = professionsNew;
                }
            }
        }
        return professions;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1823650265)
    public synchronized void resetProfessions() {
        professions = null;
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
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }

}
