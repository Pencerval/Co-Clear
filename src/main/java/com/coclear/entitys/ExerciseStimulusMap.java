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
@Table(name = "exercise_stimulus_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExerciseStimulusMap.findAll", query = "SELECT e FROM ExerciseStimulusMap e"),
    @NamedQuery(name = "ExerciseStimulusMap.findByIdExerciseStimulusMap", query = "SELECT e FROM ExerciseStimulusMap e WHERE e.idExerciseStimulusMap = :idExerciseStimulusMap")})
public class ExerciseStimulusMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_exercise_stimulus_map")
    private Integer idExerciseStimulusMap;
    @JoinColumn(name = "id_stimulus", referencedColumnName = "id_stimulus")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Stimulus stimulus;
    @JoinColumn(name = "id_excersice", referencedColumnName = "id_exercise")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Exercise exercise;

    public ExerciseStimulusMap() {
    }

    public ExerciseStimulusMap(Integer idExerciseStimulusMap) {
        this.idExerciseStimulusMap = idExerciseStimulusMap;
    }

    public Integer getIdExerciseStimulusMap() {
        return idExerciseStimulusMap;
    }

    public void setIdExerciseStimulusMap(Integer idExerciseStimulusMap) {
        this.idExerciseStimulusMap = idExerciseStimulusMap;
    }

    public Stimulus getStimulus() {
        return stimulus;
    }

    public void setStimulus(Stimulus stimulus) {
        this.stimulus = stimulus;
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
        hash += (idExerciseStimulusMap != null ? idExerciseStimulusMap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExerciseStimulusMap)) {
            return false;
        }
        ExerciseStimulusMap other = (ExerciseStimulusMap) object;
        if ((this.idExerciseStimulusMap == null && other.idExerciseStimulusMap != null) || (this.idExerciseStimulusMap != null && !this.idExerciseStimulusMap.equals(other.idExerciseStimulusMap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.ExerciseStimulusMap[ idExerciseStimulusMap=" + idExerciseStimulusMap + " ]";
    }
    
}
