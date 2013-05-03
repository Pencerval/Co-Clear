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
@Table(name = "tag_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TagGroup.findAll", query = "SELECT t FROM TagGroup t"),
    @NamedQuery(name = "TagGroup.findByIdTagGroup", query = "SELECT t FROM TagGroup t WHERE t.idTagGroup = :idTagGroup"),
    @NamedQuery(name = "TagGroup.findByName", query = "SELECT t FROM TagGroup t WHERE t.name = :name"),
    @NamedQuery(name = "TagGroup.findByDescription", query = "SELECT t FROM TagGroup t WHERE t.description = :description")})
public class TagGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tag_group")
    private Integer idTagGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTagGroup")
    private List<TagGroupMap> tagGroupMapList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTagGroup")
    private List<TagGroupStimulusMap> tagGroupStimulusMapList;

    public TagGroup() {
    }

    public TagGroup(Integer idTagGroup) {
        this.idTagGroup = idTagGroup;
    }

    public TagGroup(Integer idTagGroup, String name) {
        this.idTagGroup = idTagGroup;
        this.name = name;
    }

    public Integer getIdTagGroup() {
        return idTagGroup;
    }

    public void setIdTagGroup(Integer idTagGroup) {
        this.idTagGroup = idTagGroup;
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
    public List<TagGroupMap> getTagGroupMapList() {
        return tagGroupMapList;
    }

    public void setTagGroupMapList(List<TagGroupMap> tagGroupMapList) {
        this.tagGroupMapList = tagGroupMapList;
    }

    @XmlTransient
    public List<TagGroupStimulusMap> getTagGroupStimulusMapList() {
        return tagGroupStimulusMapList;
    }

    public void setTagGroupStimulusMapList(List<TagGroupStimulusMap> tagGroupStimulusMapList) {
        this.tagGroupStimulusMapList = tagGroupStimulusMapList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTagGroup != null ? idTagGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagGroup)) {
            return false;
        }
        TagGroup other = (TagGroup) object;
        if ((this.idTagGroup == null && other.idTagGroup != null) || (this.idTagGroup != null && !this.idTagGroup.equals(other.idTagGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.TagGroup[ idTagGroup=" + idTagGroup + " ]";
    }
    
}
