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
@Table(name = "answer_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnswerGroup.findAll", query = "SELECT a FROM AnswerGroup a"),
    @NamedQuery(name = "AnswerGroup.findByIdAnswerGroup", query = "SELECT a FROM AnswerGroup a WHERE a.idAnswerGroup = :idAnswerGroup"),
    @NamedQuery(name = "AnswerGroup.findByName", query = "SELECT a FROM AnswerGroup a WHERE a.name = :name")})
public class AnswerGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_answer_group")
    private Integer idAnswerGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "answerGroup", fetch = FetchType.LAZY)
    private List<Answer> answerList;

    public AnswerGroup() {
    }

    public AnswerGroup(Integer idAnswerGroup) {
        this.idAnswerGroup = idAnswerGroup;
    }

    public AnswerGroup(Integer idAnswerGroup, String name) {
        this.idAnswerGroup = idAnswerGroup;
        this.name = name;
    }

    public Integer getIdAnswerGroup() {
        return idAnswerGroup;
    }

    public void setIdAnswerGroup(Integer idAnswerGroup) {
        this.idAnswerGroup = idAnswerGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnswerGroup != null ? idAnswerGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnswerGroup)) {
            return false;
        }
        AnswerGroup other = (AnswerGroup) object;
        if ((this.idAnswerGroup == null && other.idAnswerGroup != null) || (this.idAnswerGroup != null && !this.idAnswerGroup.equals(other.idAnswerGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.AnswerGroup[ idAnswerGroup=" + idAnswerGroup + " ]";
    }
    
}
