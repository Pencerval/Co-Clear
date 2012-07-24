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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pencerval
 */
@Entity
@Table(name = "task_exercise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaskExercise.findAll", query = "SELECT t FROM TaskExercise t"),
    @NamedQuery(name = "TaskExercise.findByIdTaskExercise", query = "SELECT t FROM TaskExercise t WHERE t.idTaskExercise = :idTaskExercise"),
    @NamedQuery(name = "TaskExercise.findByExerciseOrder", query = "SELECT t FROM TaskExercise t WHERE t.exerciseOrder = :exerciseOrder")})
public class TaskExercise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_task_exercise")
    private Integer idTaskExercise;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exercise_order")
    private int exerciseOrder;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "taskExercise", fetch = FetchType.LAZY)
    private List<Result> resultList;
    @JoinColumn(name = "id_task", referencedColumnName = "id_task")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Task task;
    @JoinColumn(name = "id_excersice", referencedColumnName = "id_exercise")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Exercise exercise;

    public TaskExercise() {
    }

    public TaskExercise(Integer idTaskExercise) {
        this.idTaskExercise = idTaskExercise;
    }

    public TaskExercise(Integer idTaskExercise, int order) {
        this.idTaskExercise = idTaskExercise;
        this.exerciseOrder = order;
    }

    public Integer getIdTaskExercise() {
        return idTaskExercise;
    }

    public void setIdTaskExercise(Integer idTaskExercise) {
        this.idTaskExercise = idTaskExercise;
    }

    public int getExerciseOrder() {
        return exerciseOrder;
    }

    public void setExerciseOrder(int order) {
        this.exerciseOrder = order;
    }

    @XmlTransient
    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaskExercise != null ? idTaskExercise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskExercise)) {
            return false;
        }
        TaskExercise other = (TaskExercise) object;
        if ((this.idTaskExercise == null && other.idTaskExercise != null) || (this.idTaskExercise != null && !this.idTaskExercise.equals(other.idTaskExercise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.TaskExercise[ idTaskExercise=" + idTaskExercise + " ]";
    }
    
}
