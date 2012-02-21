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
@Table(name = "stimulus_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StimulusGroup.findAll", query = "SELECT s FROM StimulusGroup s"),
    @NamedQuery(name = "StimulusGroup.findByIdStimulusGroup", query = "SELECT s FROM StimulusGroup s WHERE s.idStimulusGroup = :idStimulusGroup"),
    @NamedQuery(name = "StimulusGroup.findByName", query = "SELECT s FROM StimulusGroup s WHERE s.name = :name")})
public class StimulusGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stimulus_group")
    private Integer idStimulusGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStimulusGroup")
    private Collection<Stimulus> stimulusCollection;

    public StimulusGroup() {
    }

    public StimulusGroup(Integer idStimulusGroup) {
        this.idStimulusGroup = idStimulusGroup;
    }

    public StimulusGroup(Integer idStimulusGroup, String name) {
        this.idStimulusGroup = idStimulusGroup;
        this.name = name;
    }

    public Integer getIdStimulusGroup() {
        return idStimulusGroup;
    }

    public void setIdStimulusGroup(Integer idStimulusGroup) {
        this.idStimulusGroup = idStimulusGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Stimulus> getStimulusCollection() {
        return stimulusCollection;
    }

    public void setStimulusCollection(Collection<Stimulus> stimulusCollection) {
        this.stimulusCollection = stimulusCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStimulusGroup != null ? idStimulusGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StimulusGroup)) {
            return false;
        }
        StimulusGroup other = (StimulusGroup) object;
        if ((this.idStimulusGroup == null && other.idStimulusGroup != null) || (this.idStimulusGroup != null && !this.idStimulusGroup.equals(other.idStimulusGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.stimulusbriefing.entitys.StimulusGroup[ idStimulusGroup=" + idStimulusGroup + " ]";
    }
    
}
