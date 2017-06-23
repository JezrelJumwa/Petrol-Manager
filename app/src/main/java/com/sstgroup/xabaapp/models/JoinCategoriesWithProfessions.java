package com.sstgroup.xabaapp.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JoinCategoriesWithProfessions {
    @Id
    private Long id;
    private Long categoryId;
    private Long professionsId;
    
    @Generated(hash = 2032384591)
    public JoinCategoriesWithProfessions(Long id, Long categoryId,
            Long professionsId) {
        this.id = id;
        this.categoryId = categoryId;
        this.professionsId = professionsId;
    }
    @Generated(hash = 515722175)
    public JoinCategoriesWithProfessions() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCategoryId() {
        return this.categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getProfessionsId() {
        return this.professionsId;
    }
    public void setProfessionsId(Long professionsId) {
        this.professionsId = professionsId;
    }
}
