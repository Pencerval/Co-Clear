/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pencerval
 */
@Entity
@Table(name = "user_group_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroupMap.findAll", query = "SELECT u FROM UserGroupMap u"),
    @NamedQuery(name = "UserGroupMap.findByIdUserGroupMap", query = "SELECT u FROM UserGroupMap u WHERE u.idUserGroupMap = :idUserGroupMap")})
public class UserGroupMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_group_map")
    private Integer idUserGroupMap;
    @JoinColumn(name = "id_user_group", referencedColumnName = "id_user_group")
    @ManyToOne(optional = false)
    private UserGroup idUserGroup;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User id_User;

    public UserGroupMap() {
    }

    public UserGroupMap(Integer idUserGroupMap) {
        this.idUserGroupMap = idUserGroupMap;
    }

    public Integer getIdUserGroupMap() {
        return idUserGroupMap;
    }

    public void setIdUserGroupMap(Integer idUserGroupMap) {
        this.idUserGroupMap = idUserGroupMap;
    }

    public UserGroup getIdUserGroup() {
        return idUserGroup;
    }

    public void setIdUserGroup(UserGroup idUserGroup) {
        this.idUserGroup = idUserGroup;
    }

    public User getIdUser() {
        return id_User;
    }

    public void setIdUser(User idUser) {
        this.id_User = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserGroupMap != null ? idUserGroupMap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroupMap)) {
            return false;
        }
        UserGroupMap other = (UserGroupMap) object;
        if ((this.idUserGroupMap == null && other.idUserGroupMap != null) || (this.idUserGroupMap != null && !this.idUserGroupMap.equals(other.idUserGroupMap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.stimulusbriefing.entitys.UserGroupMap[ idUserGroupMap=" + idUserGroupMap + " ]";
    }
    
}
