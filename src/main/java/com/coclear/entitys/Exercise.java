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
@Table(name = "exercise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exercise.findAll", query = "SELECT e FROM Exercise e"),
    @NamedQuery(name = "Exercise.findByIdExercise", query = "SELECT e FROM Exercise e WHERE e.idExercise = :idExercise"),
    @NamedQuery(name = "Exercise.findByName", query = "SELECT e FROM Exercise e WHERE e.name = :name"),
    @NamedQuery(name = "Exercise.findByType", query = "SELECT e FROM Exercise e WHERE e.type = :type")})
public class Exercise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_exercise")
    private Integer idExercise;
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "exercise", fetch = FetchType.LAZY)
    private List<TaskExercise> taskExerciseList;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "exercise", fetch = FetchType.LAZY)
    private List<ExerciseStimulusMap> exerciseStimulusMapList;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "exercise", fetch = FetchType.LAZY)
    private List<PossibleSolution> possibleSolutionList;

    public Exercise() {
    }

    public Exercise(Integer idExercise) {
        this.idExercise = idExercise;
    }

    public Exercise(Integer idExercise, String name, int type) {
        this.idExercise = idExercise;
        this.name = name;
        this.type = type;
    }

    public Integer getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Integer idExercise) {
        this.idExercise = idExercise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @XmlTransient
    public List<TaskExercise> getTaskExerciseList() {
        return taskExerciseList;
    }

    public void setTaskExerciseList(List<TaskExercise> taskExerciseList) {
        this.taskExerciseList = taskExerciseList;
    }

    @XmlTransient
    public List<ExerciseStimulusMap> getExerciseStimulusMapList() {
        return exerciseStimulusMapList;
    }

    public void setExerciseStimulusMapList(List<ExerciseStimulusMap> exerciseStimulusMapList) {
        this.exerciseStimulusMapList = exerciseStimulusMapList;
    }

    @XmlTransient
    public List<PossibleSolution> getPossibleSolutionList() {
        return possibleSolutionList;
    }

    public void setPossibleSolutionList(List<PossibleSolution> possibleSolutionList) {
        this.possibleSolutionList = possibleSolutionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExercise != null ? idExercise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        if ((this.idExercise == null && other.idExercise != null) || (this.idExercise != null && !this.idExercise.equals(other.idExercise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.Exercise[ idExercise=" + idExercise + " ]";
    }
    
}
