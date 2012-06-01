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
@Table(name = "possible_solution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PossibleSolution.findAll", query = "SELECT p FROM PossibleSolution p"),
    @NamedQuery(name = "PossibleSolution.findByIdPossibleSolution", query = "SELECT p FROM PossibleSolution p WHERE p.idPossibleSolution = :idPossibleSolution"),
    @NamedQuery(name = "PossibleSolution.findByCorrect", query = "SELECT p FROM PossibleSolution p WHERE p.correct = :correct"),
    @NamedQuery(name = "PossibleSolution.findByAnswerOrder", query = "SELECT p FROM PossibleSolution p WHERE p.answerOrder = :answerOrder")})
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
    private boolean correct;
    @Column(name = "answer_order")
    private Integer answerOrder;
    @JoinColumn(name = "id_excersice", referencedColumnName = "id_exercise")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Exercise exercise;
    @JoinColumn(name = "id_answer", referencedColumnName = "id_answer")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Answer answer;

    public PossibleSolution() {
    }

    public PossibleSolution(Integer idPossibleSolution) {
        this.idPossibleSolution = idPossibleSolution;
    }

    public PossibleSolution(Integer idPossibleSolution, boolean correct) {
        this.idPossibleSolution = idPossibleSolution;
        this.correct = correct;
    }

    public Integer getIdPossibleSolution() {
        return idPossibleSolution;
    }

    public void setIdPossibleSolution(Integer idPossibleSolution) {
        this.idPossibleSolution = idPossibleSolution;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Integer getAnswerOrder() {
        return answerOrder;
    }

    public void setAnswerOrder(Integer order) {
        this.answerOrder = order;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
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
