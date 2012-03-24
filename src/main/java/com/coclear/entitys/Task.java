/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pencerval
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByIdTask", query = "SELECT t FROM Task t WHERE t.idTask = :idTask"),
    @NamedQuery(name = "Task.findByName", query = "SELECT t FROM Task t WHERE t.name = :name"),
    @NamedQuery(name = "Task.findByRepeatable", query = "SELECT t FROM Task t WHERE t.repeatable = :repeatable"),
    @NamedQuery(name = "Task.findByIsUserDefault", query = "SELECT t FROM Task t WHERE t.isUserDefault = :isUserDefault"),
    @NamedQuery(name = "Task.findByType", query = "SELECT t FROM Task t WHERE t.type = :type")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_task")
    private Integer idTask;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "repeatable")
    private int repeatable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_user_default")
    private int isUserDefault;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTask")
    private Collection<TaskExercise> taskExerciseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTask")
    private Collection<DefaultGroupTask> defaultGroupTaskCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTask")
    private Collection<UserTask> userTaskCollection;

    public Task() {
    }

    public Task(Integer idTask) {
        this.idTask = idTask;
    }

    public Task(Integer idTask, String name, int repeatable, int isUserDefault, int type) {
        this.idTask = idTask;
        this.name = name;
        this.repeatable = repeatable;
        this.isUserDefault = isUserDefault;
        this.type = type;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepeatable() {
        return repeatable;
    }

    public void setRepeatable(int repeatable) {
        this.repeatable = repeatable;
    }

    public int getIsUserDefault() {
        return isUserDefault;
    }

    public void setIsUserDefault(int isUserDefault) {
        this.isUserDefault = isUserDefault;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<TaskExercise> getTaskExerciseCollection() {
        return taskExerciseCollection;
    }

    public void setTaskExerciseCollection(Collection<TaskExercise> taskExerciseCollection) {
        this.taskExerciseCollection = taskExerciseCollection;
    }

    @XmlTransient
    public Collection<DefaultGroupTask> getDefaultGroupTaskCollection() {
        return defaultGroupTaskCollection;
    }

    public void setDefaultGroupTaskCollection(Collection<DefaultGroupTask> defaultGroupTaskCollection) {
        this.defaultGroupTaskCollection = defaultGroupTaskCollection;
    }

    @XmlTransient
    public Collection<UserTask> getUserTaskCollection() {
        return userTaskCollection;
    }

    public void setUserTaskCollection(Collection<UserTask> userTaskCollection) {
        this.userTaskCollection = userTaskCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTask != null ? idTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.idTask == null && other.idTask != null) || (this.idTask != null && !this.idTask.equals(other.idTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.Task[ idTask=" + idTask + " ]";
    }
    
}
