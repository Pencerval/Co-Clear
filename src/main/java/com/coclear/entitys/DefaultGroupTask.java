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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserGroup userGroup;
    @JoinColumn(name = "id_task", referencedColumnName = "id_task")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Task task;

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

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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
        return "com.coclear.entitys.DefaultGroupTask[ idDefaultGroupTask=" + idDefaultGroupTask + " ]";
    }
    
}
