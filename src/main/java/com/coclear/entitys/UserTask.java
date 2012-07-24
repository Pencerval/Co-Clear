/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
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
    @NamedQuery(name = "UserTask.findByComplete", query = "SELECT u FROM UserTask u WHERE u.complete = :complete"),
    @NamedQuery(name = "UserTask.findByIdUserTaskAndComplete", query = "SELECT u FROM UserTask u WHERE u.complete = :complete and u.user = :user")})
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
    private boolean complete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTask", fetch = FetchType.LAZY)
    private List<Result> resultList;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "id_task", referencedColumnName = "id_task")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Task task;

    public UserTask() {
    }

    public UserTask(Integer idUserTask) {
        this.idUserTask = idUserTask;
    }

    public UserTask(Integer idUserTask, boolean complete) {
        this.idUserTask = idUserTask;
        this.complete = complete;
    }

    public Integer getIdUserTask() {
        return idUserTask;
    }

    public void setIdUserTask(Integer idUserTask) {
        this.idUserTask = idUserTask;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
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
        return "com.coclear.entitys.UserTask[ idUserTask=" + idUserTask + " ]";
    }
    
}
