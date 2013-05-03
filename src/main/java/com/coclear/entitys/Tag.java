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
@Table(name = "tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findByIdTag", query = "SELECT t FROM Tag t WHERE t.idTag = :idTag"),
    @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = :name"),
    @NamedQuery(name = "Tag.findByDescription", query = "SELECT t FROM Tag t WHERE t.description = :description")})
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tag")
    private Integer idTag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTag")
    private List<TagGroupMap> tagGroupMapList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTag")
    private List<TagStimulusMap> tagStimulusMapList;

    public Tag() {
    }

    public Tag(Integer idTag) {
        this.idTag = idTag;
    }

    public Tag(Integer idTag, String name) {
        this.idTag = idTag;
        this.name = name;
    }

    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
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
    public List<TagStimulusMap> getTagStimulusMapList() {
        return tagStimulusMapList;
    }

    public void setTagStimulusMapList(List<TagStimulusMap> tagStimulusMapList) {
        this.tagStimulusMapList = tagStimulusMapList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTag != null ? idTag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.idTag == null && other.idTag != null) || (this.idTag != null && !this.idTag.equals(other.idTag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Tag[ idTag=" + idTag + " ]";
    }
    
}
