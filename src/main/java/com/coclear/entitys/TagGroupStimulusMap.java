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
@Table(name = "tag_group_stimulus_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TagGroupStimulusMap.findAll", query = "SELECT t FROM TagGroupStimulusMap t"),
    @NamedQuery(name = "TagGroupStimulusMap.findByIdTagGroupStimulusMap", query = "SELECT t FROM TagGroupStimulusMap t WHERE t.idTagGroupStimulusMap = :idTagGroupStimulusMap")})
public class TagGroupStimulusMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tag_group_stimulus_map")
    private Integer idTagGroupStimulusMap;
    @JoinColumn(name = "id_stimulus", referencedColumnName = "id_stimulus")
    @ManyToOne(optional = false)
    private Stimulus idStimulus;
    @JoinColumn(name = "id_tag_group", referencedColumnName = "id_tag_group")
    @ManyToOne(optional = false)
    private TagGroup idTagGroup;

    public TagGroupStimulusMap() {
    }

    public TagGroupStimulusMap(Integer idTagGroupStimulusMap) {
        this.idTagGroupStimulusMap = idTagGroupStimulusMap;
    }

    public Integer getIdTagGroupStimulusMap() {
        return idTagGroupStimulusMap;
    }

    public void setIdTagGroupStimulusMap(Integer idTagGroupStimulusMap) {
        this.idTagGroupStimulusMap = idTagGroupStimulusMap;
    }

    public Stimulus getIdStimulus() {
        return idStimulus;
    }

    public void setIdStimulus(Stimulus idStimulus) {
        this.idStimulus = idStimulus;
    }

    public TagGroup getIdTagGroup() {
        return idTagGroup;
    }

    public void setIdTagGroup(TagGroup idTagGroup) {
        this.idTagGroup = idTagGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTagGroupStimulusMap != null ? idTagGroupStimulusMap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagGroupStimulusMap)) {
            return false;
        }
        TagGroupStimulusMap other = (TagGroupStimulusMap) object;
        if ((this.idTagGroupStimulusMap == null && other.idTagGroupStimulusMap != null) || (this.idTagGroupStimulusMap != null && !this.idTagGroupStimulusMap.equals(other.idTagGroupStimulusMap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.TagGroupStimulusMap[ idTagGroupStimulusMap=" + idTagGroupStimulusMap + " ]";
    }
    
}
