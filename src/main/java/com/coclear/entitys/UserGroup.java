/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pencerval
 */
@Entity
@Table(name = "user_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u"),
    @NamedQuery(name = "UserGroup.findByIdUserGroup", query = "SELECT u FROM UserGroup u WHERE u.idUserGroup = :idUserGroup"),
    @NamedQuery(name = "UserGroup.findByName", query = "SELECT u FROM UserGroup u WHERE u.name = :name"),
    @NamedQuery(name = "UserGroup.findByIsDefault", query = "SELECT u FROM UserGroup u WHERE u.isDefault = :isDefault")})
public class UserGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_group")
    private Integer idUserGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_default")
    private boolean isDefault;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "userGroup", fetch = FetchType.LAZY)
    private List<UserGroupMap> userGroupMapList;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "userGroup", fetch = FetchType.LAZY)
    private List<DefaultGroupTask> defaultGroupTaskList;

    public UserGroup() {
    }

    public UserGroup(Integer idUserGroup) {
        this.idUserGroup = idUserGroup;
    }

    public UserGroup(Integer idUserGroup, String name, boolean isDefault) {
        this.idUserGroup = idUserGroup;
        this.name = name;
        this.isDefault = isDefault;
    }

    public Integer getIdUserGroup() {
        return idUserGroup;
    }

    public void setIdUserGroup(Integer idUserGroup) {
        this.idUserGroup = idUserGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @XmlTransient
    public List<UserGroupMap> getUserGroupMapList() {
        return userGroupMapList;
    }

    public void setUserGroupMapList(List<UserGroupMap> userGroupMapList) {
        this.userGroupMapList = userGroupMapList;
    }

    @XmlTransient
    public List<DefaultGroupTask> getDefaultGroupTaskList() {
        return defaultGroupTaskList;
    }

    public void setDefaultGroupTaskList(List<DefaultGroupTask> defaultGroupTaskList) {
        this.defaultGroupTaskList = defaultGroupTaskList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserGroup != null ? idUserGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.idUserGroup == null && other.idUserGroup != null) || (this.idUserGroup != null && !this.idUserGroup.equals(other.idUserGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.UserGroup[ idUserGroup=" + idUserGroup + " ]";
    }
    
}
