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
@Table(name = "tag_group_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TagGroupMap.findAll", query = "SELECT t FROM TagGroupMap t"),
    @NamedQuery(name = "TagGroupMap.findByIdTagGroupMap", query = "SELECT t FROM TagGroupMap t WHERE t.idTagGroupMap = :idTagGroupMap")})
public class TagGroupMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tag_group_map")
    private Integer idTagGroupMap;
    @JoinColumn(name = "id_tag_group", referencedColumnName = "id_tag_group")
    @ManyToOne(optional = false)
    private TagGroup idTagGroup;
    @JoinColumn(name = "id_tag", referencedColumnName = "id_tag")
    @ManyToOne(optional = false)
    private Tag idTag;

    public TagGroupMap() {
    }

    public TagGroupMap(Integer idTagGroupMap) {
        this.idTagGroupMap = idTagGroupMap;
    }

    public Integer getIdTagGroupMap() {
        return idTagGroupMap;
    }

    public void setIdTagGroupMap(Integer idTagGroupMap) {
        this.idTagGroupMap = idTagGroupMap;
    }

    public TagGroup getIdTagGroup() {
        return idTagGroup;
    }

    public void setIdTagGroup(TagGroup idTagGroup) {
        this.idTagGroup = idTagGroup;
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
        hash += (idTagGroupMap != null ? idTagGroupMap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagGroupMap)) {
            return false;
        }
        TagGroupMap other = (TagGroupMap) object;
        if ((this.idTagGroupMap == null && other.idTagGroupMap != null) || (this.idTagGroupMap != null && !this.idTagGroupMap.equals(other.idTagGroupMap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.TagGroupMap[ idTagGroupMap=" + idTagGroupMap + " ]";
    }
    
}
