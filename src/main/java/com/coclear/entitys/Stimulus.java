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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pencerval
 */
@Entity
@Table(name = "stimulus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stimulus.findAll", query = "SELECT s FROM Stimulus s"),
    @NamedQuery(name = "Stimulus.findByIdStimulus", query = "SELECT s FROM Stimulus s WHERE s.idStimulus = :idStimulus"),
    @NamedQuery(name = "Stimulus.findByName", query = "SELECT s FROM Stimulus s WHERE s.name = :name"),
    @NamedQuery(name = "Stimulus.findByPath", query = "SELECT s FROM Stimulus s WHERE s.path = :path")})
public class Stimulus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stimulus")
    private Integer idStimulus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "path")
    private String path;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStimulus")
    private Collection<ExerciseStimulusMap> exerciseStimulusMapCollection;
    @JoinColumn(name = "id_stimulus_group", referencedColumnName = "id_stimulus_group")
    @ManyToOne(optional = false)
    private StimulusGroup idStimulusGroup;

    public Stimulus() {
    }

    public Stimulus(Integer idStimulus) {
        this.idStimulus = idStimulus;
    }

    public Stimulus(Integer idStimulus, String name, String path) {
        this.idStimulus = idStimulus;
        this.name = name;
        this.path = path;
    }
    
    public Stimulus(String name, String path,StimulusGroup stimulusGroup) {
        this.name = name;
        this.path = path;
        this.idStimulusGroup=stimulusGroup;
    }

    public Integer getIdStimulus() {
        return idStimulus;
    }

    public void setIdStimulus(Integer idStimulus) {
        this.idStimulus = idStimulus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @XmlTransient
    public Collection<ExerciseStimulusMap> getExerciseStimulusMapCollection() {
        return exerciseStimulusMapCollection;
    }

    public void setExerciseStimulusMapCollection(Collection<ExerciseStimulusMap> exerciseStimulusMapCollection) {
        this.exerciseStimulusMapCollection = exerciseStimulusMapCollection;
    }

    public StimulusGroup getIdStimulusGroup() {
        return idStimulusGroup;
    }

    public void setIdStimulusGroup(StimulusGroup idStimulusGroup) {
        this.idStimulusGroup = idStimulusGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStimulus != null ? idStimulus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stimulus)) {
            return false;
        }
        Stimulus other = (Stimulus) object;
        if ((this.idStimulus == null && other.idStimulus != null) || (this.idStimulus != null && !this.idStimulus.equals(other.idStimulus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.Stimulus[ idStimulus=" + idStimulus + " ]";
    }
    
}
