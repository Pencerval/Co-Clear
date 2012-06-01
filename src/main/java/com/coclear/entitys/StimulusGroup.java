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
@Table(name = "stimulus_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StimulusGroup.findAll", query = "SELECT s FROM StimulusGroup s"),
    @NamedQuery(name = "StimulusGroup.findByIdStimulusGroup", query = "SELECT s FROM StimulusGroup s WHERE s.idStimulusGroup = :idStimulusGroup"),
    @NamedQuery(name = "StimulusGroup.findByName", query = "SELECT s FROM StimulusGroup s WHERE s.name = :name"),
    @NamedQuery(name = "StimulusGroup.findByDescription", query = "SELECT s FROM StimulusGroup s WHERE s.description = :description")})
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
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stimulusGroup", fetch = FetchType.LAZY)
    private List<Stimulus> stimulusList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Stimulus> getStimulusList() {
        return stimulusList;
    }

    public void setStimulusList(List<Stimulus> stimulusList) {
        this.stimulusList = stimulusList;
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
        return "com.coclear.entitys.StimulusGroup[ idStimulusGroup=" + idStimulusGroup + " ]";
    }
    
}
