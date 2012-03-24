/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pencerval
 */
@Entity
@Table(name = "possible_solution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PossibleSolution.findAll", query = "SELECT p FROM PossibleSolution p"),
    @NamedQuery(name = "PossibleSolution.findByIdPossibleSolution", query = "SELECT p FROM PossibleSolution p WHERE p.idPossibleSolution = :idPossibleSolution"),
    @NamedQuery(name = "PossibleSolution.findByCorrect", query = "SELECT p FROM PossibleSolution p WHERE p.correct = :correct")})
public class PossibleSolution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_possible_solution")
    private Integer idPossibleSolution;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correct")
    private int correct;
    @JoinColumn(name = "id_excersice", referencedColumnName = "id_excersice")
    @ManyToOne(optional = false)
    private Excersice idExcersice;
    @JoinColumn(name = "id_answer", referencedColumnName = "id_answer")
    @ManyToOne(optional = false)
    private Answer idAnswer;

    public PossibleSolution() {
    }

    public PossibleSolution(Integer idPossibleSolution) {
        this.idPossibleSolution = idPossibleSolution;
    }

    public PossibleSolution(Integer idPossibleSolution, int correct) {
        this.idPossibleSolution = idPossibleSolution;
        this.correct = correct;
    }

    public Integer getIdPossibleSolution() {
        return idPossibleSolution;
    }

    public void setIdPossibleSolution(Integer idPossibleSolution) {
        this.idPossibleSolution = idPossibleSolution;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public Excersice getIdExcersice() {
        return idExcersice;
    }

    public void setIdExcersice(Excersice idExcersice) {
        this.idExcersice = idExcersice;
    }

    public Answer getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Answer idAnswer) {
        this.idAnswer = idAnswer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPossibleSolution != null ? idPossibleSolution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PossibleSolution)) {
            return false;
        }
        PossibleSolution other = (PossibleSolution) object;
        if ((this.idPossibleSolution == null && other.idPossibleSolution != null) || (this.idPossibleSolution != null && !this.idPossibleSolution.equals(other.idPossibleSolution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.PossibleSolution[ idPossibleSolution=" + idPossibleSolution + " ]";
    }
    
}
