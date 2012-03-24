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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "TaskExercise.findByNumber", query = "SELECT t FROM TaskExercise t WHERE t.number = :number")})
public class TaskExercise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_task_exercise")
    private Integer idTaskExercise;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private int number;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTaskExercise")
    private Collection<Result> resultCollection;
    @JoinColumn(name = "id_task", referencedColumnName = "id_task")
    @ManyToOne(optional = false)
    private Task idTask;
    @JoinColumn(name = "id_excersice", referencedColumnName = "id_excersice")
    @ManyToOne(optional = false)
    private Excersice idExcersice;

    public TaskExercise() {
    }

    public TaskExercise(Integer idTaskExercise) {
        this.idTaskExercise = idTaskExercise;
    }

    public TaskExercise(Integer idTaskExercise, int number) {
        this.idTaskExercise = idTaskExercise;
        this.number = number;
    }

    public Integer getIdTaskExercise() {
        return idTaskExercise;
    }

    public void setIdTaskExercise(Integer idTaskExercise) {
        this.idTaskExercise = idTaskExercise;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @XmlTransient
    public Collection<Result> getResultCollection() {
        return resultCollection;
    }

    public void setResultCollection(Collection<Result> resultCollection) {
        this.resultCollection = resultCollection;
    }

    public Task getIdTask() {
        return idTask;
    }

    public void setIdTask(Task idTask) {
        this.idTask = idTask;
    }

    public Excersice getIdExcersice() {
        return idExcersice;
    }

    public void setIdExcersice(Excersice idExcersice) {
        this.idExcersice = idExcersice;
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
