package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

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
//    @ToOne(joinProperty = "countryId")
//    private Country country;
    @SerializedName("county_id")
    private Long countyId;
//    @ToOne(joinProperty = "countyId")
//    private County county;
    @SerializedName("subcounty_id")
    private Long subcountyId;
//    @ToOne(joinProperty = "subcountyId")
//    private SubCounty subCounty;
    @Transient
    @SerializedName("commissions")
    private UserCommissions userCommissions;
    private Float currentBalance;
    private Float payoutThreshold;
    private Integer totalReferrals;
    private Float perWorker;
    private Long currencyId;
    @Transient
    @Expose
    private Currency currency;

    @ToMany(referencedJoinProperty = "userId")
    private List<JoinUsersWithProfessionsAndIndustries> professions;

    @ToMany
    @JoinEntity(
            entity = JoinUsersWithPrograms.class,
            sourceProperty = "userId",
            targetProperty = "programId"
    )
    private List<Program> programs;

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

    @Generated(hash = 586692638)
    public User() {
    }

    @Generated(hash = 2044412495)
    public User(Long id, String status, String agentId, String firstName, String lastName,
            String otherName, String phone, String gender, String birthdate, Long countryId,
            Long countyId, Long subcountyId, Float currentBalance, Float payoutThreshold,
            Integer totalReferrals, Float perWorker, Long currencyId, Long tokenId,
            Boolean isPhoneVerified, Boolean isDefaultPin) {
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
        this.currentBalance = currentBalance;
        this.payoutThreshold = payoutThreshold;
        this.totalReferrals = totalReferrals;
        this.perWorker = perWorker;
        this.currencyId = currencyId;
        this.tokenId = tokenId;
        this.isPhoneVerified = isPhoneVerified;
        this.isDefaultPin = isDefaultPin;
    }

    @Generated(hash = 2059307683)
    private transient Long token__resolvedKey;
    public Token getTokenFromWS(){
        return this.token;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
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
    @Generated(hash = 596417267)
    public List<JoinUsersWithProfessionsAndIndustries> getProfessions() {
        if (professions == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            JoinUsersWithProfessionsAndIndustriesDao targetDao = daoSession
                    .getJoinUsersWithProfessionsAndIndustriesDao();
            List<JoinUsersWithProfessionsAndIndustries> professionsNew = targetDao
                    ._queryUser_Professions(id);
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

    public Float getCurrentBalance() {
        return this.currentBalance;
    }

    public void setCurrentBalance(Float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Float getPayoutThreshold() {
        return this.payoutThreshold;
    }

    public void setPayoutThreshold(Float payoutThreshold) {
        this.payoutThreshold = payoutThreshold;
    }

    public Integer getTotalReferrals() {
        return this.totalReferrals;
    }

    public void setTotalReferrals(Integer totalReferrals) {
        this.totalReferrals = totalReferrals;
    }

    public Float getPerWorker() {
        return this.perWorker;
    }

    public void setPerWorker(Float perWorker) {
        this.perWorker = perWorker;
    }

    public Long getCurrencyId() {
        return this.currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public UserCommissions getUserCommissions() {
        return userCommissions;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1243580497)
    public List<Program> getPrograms() {
        if (programs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProgramDao targetDao = daoSession.getProgramDao();
            List<Program> programsNew = targetDao._queryUser_Programs(id);
            synchronized (this) {
                if (programs == null) {
                    programs = programsNew;
                }
            }
        }
        return programs;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1664380762)
    public synchronized void resetPrograms() {
        programs = null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}
