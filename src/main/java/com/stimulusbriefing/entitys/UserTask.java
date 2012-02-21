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
@Table(name = "user_task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTask.findAll", query = "SELECT u FROM UserTask u"),
    @NamedQuery(name = "UserTask.findByIdUserTask", query = "SELECT u FROM UserTask u WHERE u.idUserTask = :idUserTask"),
    @NamedQuery(name = "UserTask.findByComplete", query = "SELECT u FROM UserTask u WHERE u.complete = :complete")})
public class UserTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_task")
    private Integer idUserTask;
    @Basic(optional = false)
    @NotNull
    @Column(name = "complete")
    private int complete;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User id_User;
    @JoinColumn(name = "id_task", referencedColumnName = "id_task")
    @ManyToOne(optional = false)
    private Task idTask;

    public UserTask() {
    }

    public UserTask(Integer idUserTask) {
        this.idUserTask = idUserTask;
    }

    public UserTask(Integer idUserTask, int complete) {
        this.idUserTask = idUserTask;
        this.complete = complete;
    }

    public Integer getIdUserTask() {
        return idUserTask;
    }

    public void setIdUserTask(Integer idUserTask) {
        this.idUserTask = idUserTask;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public User getIdUser() {
        return id_User;
    }

    public void setIdUser(User idUser) {
        this.id_User = idUser;
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
        hash += (idUserTask != null ? idUserTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTask)) {
            return false;
        }
        UserTask other = (UserTask) object;
        if ((this.idUserTask == null && other.idUserTask != null) || (this.idUserTask != null && !this.idUserTask.equals(other.idUserTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.stimulusbriefing.entitys.UserTask[ idUserTask=" + idUserTask + " ]";
    }
    
}
