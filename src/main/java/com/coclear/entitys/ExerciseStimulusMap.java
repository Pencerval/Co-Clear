/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

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
    @ManyToOne(optional = false)
    private Stimulus idStimulus;
    @JoinColumn(name = "id_excersice", referencedColumnName = "id_excersice")
    @ManyToOne(optional = false)
    private Excersice idExcersice;

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

    public Stimulus getIdStimulus() {
        return idStimulus;
    }

    public void setIdStimulus(Stimulus idStimulus) {
        this.idStimulus = idStimulus;
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
