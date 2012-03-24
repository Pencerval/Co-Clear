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
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByIdAnswer", query = "SELECT a FROM Answer a WHERE a.idAnswer = :idAnswer"),
    @NamedQuery(name = "Answer.findByName", query = "SELECT a FROM Answer a WHERE a.name = :name"),
    @NamedQuery(name = "Answer.findByValueName", query = "SELECT a FROM Answer a WHERE a.valueName = :valueName"),
    @NamedQuery(name = "Answer.findByExample", query = "SELECT a FROM Answer a WHERE a.example = :example")})
public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_answer")
    private Integer idAnswer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "value_name")
    private String valueName;
    @Size(max = 255)
    @Column(name = "example")
    private String example;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnswer")
    private Collection<Result> resultCollection;
    @JoinColumn(name = "id_answer_group", referencedColumnName = "id_answer_group")
    @ManyToOne(optional = false)
    private AnswerGroup idAnswerGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnswer")
    private Collection<PossibleSolution> possibleSolutionCollection;

    public Answer() {
    }

    public Answer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }

    public Answer(Integer idAnswer, String name, String valueName) {
        this.idAnswer = idAnswer;
        this.name = name;
        this.valueName = valueName;
    }

    public Integer getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @XmlTransient
    public Collection<Result> getResultCollection() {
        return resultCollection;
    }

    public void setResultCollection(Collection<Result> resultCollection) {
        this.resultCollection = resultCollection;
    }

    public AnswerGroup getIdAnswerGroup() {
        return idAnswerGroup;
    }

    public void setIdAnswerGroup(AnswerGroup idAnswerGroup) {
        this.idAnswerGroup = idAnswerGroup;
    }

    @XmlTransient
    public Collection<PossibleSolution> getPossibleSolutionCollection() {
        return possibleSolutionCollection;
    }

    public void setPossibleSolutionCollection(Collection<PossibleSolution> possibleSolutionCollection) {
        this.possibleSolutionCollection = possibleSolutionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnswer != null ? idAnswer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.idAnswer == null && other.idAnswer != null) || (this.idAnswer != null && !this.idAnswer.equals(other.idAnswer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.Answer[ idAnswer=" + idAnswer + " ]";
    }
    
}
