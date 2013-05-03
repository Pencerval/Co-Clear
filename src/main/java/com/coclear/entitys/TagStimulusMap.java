/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pencerval
 */
@Entity
@Table(name = "tag_stimulus_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TagStimulusMap.findAll", query = "SELECT t FROM TagStimulusMap t"),
    @NamedQuery(name = "TagStimulusMap.findByIdTagStimulusMap", query = "SELECT t FROM TagStimulusMap t WHERE t.idTagStimulusMap = :idTagStimulusMap")})
public class TagStimulusMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tag_stimulus_map")
    private Integer idTagStimulusMap;
    @JoinColumn(name = "id_stimulus", referencedColumnName = "id_stimulus")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Stimulus idStimulus;
    @JoinColumn(name = "id_tag", referencedColumnName = "id_tag")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tag idTag;

    public TagStimulusMap() {
    }

    public TagStimulusMap(Integer idTagStimulusMap) {
        this.idTagStimulusMap = idTagStimulusMap;
    }

    public Integer getIdTagStimulusMap() {
        return idTagStimulusMap;
    }

    public void setIdTagStimulusMap(Integer idTagStimulusMap) {
        this.idTagStimulusMap = idTagStimulusMap;
    }

    public Stimulus getIdStimulus() {
        return idStimulus;
    }

    public void setIdStimulus(Stimulus idStimulus) {
        this.idStimulus = idStimulus;
    }

    public Tag getIdTag() {
        return idTag;
    }

    public void setIdTag(Tag idTag) {
        this.idTag = idTag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTagStimulusMap != null ? idTagStimulusMap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagStimulusMap)) {
            return false;
        }
        TagStimulusMap other = (TagStimulusMap) object;
        if ((this.idTagStimulusMap == null && other.idTagStimulusMap != null) || (this.idTagStimulusMap != null && !this.idTagStimulusMap.equals(other.idTagStimulusMap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.TagStimulusMap[ idTagStimulusMap=" + idTagStimulusMap + " ]";
    }
    
}
