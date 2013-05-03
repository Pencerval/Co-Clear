/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
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
    @NamedQuery(name = "Stimulus.findByType", query = "SELECT s FROM Stimulus s WHERE s.type = :type")})
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
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "stimulus", fetch = FetchType.LAZY)
    private List<ExerciseStimulusMap> exerciseStimulusMapList;
    @JoinColumn(name = "id_stimulus_group", referencedColumnName = "id_stimulus_group")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StimulusGroup stimulusGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStimulus")
    private List<TagGroupStimulusMap> tagGroupStimulusMapList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStimulus")
    private List<TagStimulusMap> tagStimulusMapList;

    public Stimulus() {
    }

    public Stimulus(Integer idStimulus) {
        this.idStimulus = idStimulus;
    }

    public Stimulus(Integer idStimulus, String name, String type) {
        this.idStimulus = idStimulus;
        this.name = name;
        this.type = type;
    }
    
    public Stimulus(String name, String type,StimulusGroup stimulusGroup) {
        this.name = name;
        this.type = type;
        this.stimulusGroup=stimulusGroup;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<ExerciseStimulusMap> getExerciseStimulusMapList() {
        return exerciseStimulusMapList;
    }

    public void setExerciseStimulusMapList(List<ExerciseStimulusMap> exerciseStimulusMapList) {
        this.exerciseStimulusMapList = exerciseStimulusMapList;
    }

    public StimulusGroup getStimulusGroup() {
        return stimulusGroup;
    }

    public void setStimulusGroup(StimulusGroup stimulusGroup) {
        this.stimulusGroup = stimulusGroup;
    }
    
    @XmlTransient
    public List<TagGroupStimulusMap> getTagGroupStimulusMapList() {
        return tagGroupStimulusMapList;
    }

    public void setTagGroupStimulusMapList(List<TagGroupStimulusMap> tagGroupStimulusMapList) {
        this.tagGroupStimulusMapList = tagGroupStimulusMapList;
    }

    @XmlTransient
    public List<TagStimulusMap> getTagStimulusMapList() {
        return tagStimulusMapList;
    }

    public void setTagStimulusMapList(List<TagStimulusMap> tagStimulusMapList) {
        this.tagStimulusMapList = tagStimulusMapList;
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
