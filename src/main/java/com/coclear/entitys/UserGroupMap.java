/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import javax.persistence.*;
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserGroup userGroup;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

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

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "com.coclear.entitys.UserGroupMap[ idUserGroupMap=" + idUserGroupMap + " ]";
    }
    
}
