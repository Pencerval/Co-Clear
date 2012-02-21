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
@Table(name = "default_group_task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DefaultGroupTask.findAll", query = "SELECT d FROM DefaultGroupTask d"),
    @NamedQuery(name = "DefaultGroupTask.findByIdDefaultGroupTask", query = "SELECT d FROM DefaultGroupTask d WHERE d.idDefaultGroupTask = :idDefaultGroupTask")})
public class DefaultGroupTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_default_group_task")
    private Integer idDefaultGroupTask;
    @JoinColumn(name = "id_user_group", referencedColumnName = "id_user_group")
    @ManyToOne(optional = false)
    private UserGroup idUserGroup;
    @JoinColumn(name = "id_task", referencedColumnName = "id_task")
    @ManyToOne(optional = false)
    private Task idTask;

    public DefaultGroupTask() {
    }

    public DefaultGroupTask(Integer idDefaultGroupTask) {
        this.idDefaultGroupTask = idDefaultGroupTask;
    }

    public Integer getIdDefaultGroupTask() {
        return idDefaultGroupTask;
    }

    public void setIdDefaultGroupTask(Integer idDefaultGroupTask) {
        this.idDefaultGroupTask = idDefaultGroupTask;
    }

    public UserGroup getIdUserGroup() {
        return idUserGroup;
    }

    public void setIdUserGroup(UserGroup idUserGroup) {
        this.idUserGroup = idUserGroup;
    }

    public Task getIdTask() {
        return idTask;
    }

    public void setIdTask(Task idTask) {
        this.idTask = idTask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDefaultGroupTask != null ? idDefaultGroupTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DefaultGroupTask)) {
            return false;
        }
        DefaultGroupTask other = (DefaultGroupTask) object;
        if ((this.idDefaultGroupTask == null && other.idDefaultGroupTask != null) || (this.idDefaultGroupTask != null && !this.idDefaultGroupTask.equals(other.idDefaultGroupTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.stimulusbriefing.entitys.DefaultGroupTask[ idDefaultGroupTask=" + idDefaultGroupTask + " ]";
    }
    
}
