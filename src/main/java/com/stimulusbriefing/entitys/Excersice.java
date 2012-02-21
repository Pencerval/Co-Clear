/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.entitys;

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
@Table(name = "excersice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Excersice.findAll", query = "SELECT e FROM Excersice e"),
    @NamedQuery(name = "Excersice.findByIdExcersice", query = "SELECT e FROM Excersice e WHERE e.idExcersice = :idEcxcersice"),
    @NamedQuery(name = "Excersice.findByName", query = "SELECT e FROM Excersice e WHERE e.name = :name"),
    @NamedQuery(name = "Excersice.findByDescription", query = "SELECT e FROM Excersice e WHERE e.description = :description"),
    @NamedQuery(name = "Excersice.findByType", query = "SELECT e FROM Excersice e WHERE e.type = :type")})
public class Excersice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_excersice")
    private Integer idExcersice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private Integer description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExcersice")
    private Collection<TaskExercise> taskExerciseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExcersice")
    private Collection<ExerciseStimulusMap> exerciseStimulusMapCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExcersice")
    private Collection<PossibleSolution> possibleSolutionCollection;

    public Excersice() {
    }

    public Excersice(Integer idExcersice) {
        this.idExcersice = idExcersice;
    }

    public Excersice(Integer idExcersice, String name, int type) {
        this.idExcersice = idExcersice;
        this.name = name;
        this.type = type;
    }

    public Integer getIdExcersice() {
        return idExcersice;
    }

    public void setIdExcersice(Integer idExcersice) {
        this.idExcersice = idExcersice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
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
    public Collection<ExerciseStimulusMap> getExerciseStimulusMapCollection() {
        return exerciseStimulusMapCollection;
    }

    public void setExerciseStimulusMapCollection(Collection<ExerciseStimulusMap> exerciseStimulusMapCollection) {
        this.exerciseStimulusMapCollection = exerciseStimulusMapCollection;
    }

    @XmlTransient
    public Collection<PossibleSolution> getPossibleSolutionCollection() {
        return possibleSolutionCollection;
    }

    public void setPossibleSolutionCollection(Collection<PossibleSolution> possibleSolutionCollection) {
        this.possibleSolutionCollection = possibleSolutionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExcersice != null ? idExcersice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Excersice)) {
            return false;
        }
        Excersice other = (Excersice) object;
        if ((this.idExcersice == null && other.idExcersice != null) || (this.idExcersice != null && !this.idExcersice.equals(other.idExcersice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.stimulusbriefing.entitys.Excersice[ idExcersice=" + idExcersice + " ]";
    }
    
}
