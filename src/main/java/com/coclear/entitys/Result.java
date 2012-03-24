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
@Table(name = "result")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Result.findAll", query = "SELECT r FROM Result r"),
    @NamedQuery(name = "Result.findByIdResult", query = "SELECT r FROM Result r WHERE r.idResult = :idResult")})
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_result")
    private Integer idResult;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User id_User;
    @JoinColumn(name = "id_task_exercise", referencedColumnName = "id_task_exercise")
    @ManyToOne(optional = false)
    private TaskExercise idTaskExercise;
    @JoinColumn(name = "id_answer", referencedColumnName = "id_answer")
    @ManyToOne(optional = false)
    private Answer idAnswer;

    public Result() {
    }

    public Result(Integer idResult) {
        this.idResult = idResult;
    }

    public Integer getIdResult() {
        return idResult;
    }

    public void setIdResult(Integer idResult) {
        this.idResult = idResult;
    }

    public User getIdUser() {
        return id_User;
    }

    public void setIdUser(User idUser) {
        this.id_User = idUser;
    }

    public TaskExercise getIdTaskExercise() {
        return idTaskExercise;
    }

    public void setIdTaskExercise(TaskExercise idTaskExercise) {
        this.idTaskExercise = idTaskExercise;
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
        hash += (idResult != null ? idResult.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Result)) {
            return false;
        }
        Result other = (Result) object;
        if ((this.idResult == null && other.idResult != null) || (this.idResult != null && !this.idResult.equals(other.idResult))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.Result[ idResult=" + idResult + " ]";
    }
    
}
