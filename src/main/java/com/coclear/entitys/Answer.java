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
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "answer", fetch = FetchType.LAZY)
    private List<Result> resultList;
    @JoinColumn(name = "id_answer_group", referencedColumnName = "id_answer_group")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AnswerGroup answerGroup;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "answer", fetch = FetchType.LAZY)
    private List<PossibleSolution> possibleSolutionList;

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
    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public AnswerGroup getAnswerGroup() {
        return answerGroup;
    }

    public void setAnswerGroup(AnswerGroup answerGroup) {
        this.answerGroup = answerGroup;
    }

    @XmlTransient
    public List<PossibleSolution> getPossibleSolutionList() {
        return possibleSolutionList;
    }

    public void setPossibleSolutionList(List<PossibleSolution> possibleSolutionList) {
        this.possibleSolutionList = possibleSolutionList;
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
