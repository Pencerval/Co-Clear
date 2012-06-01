/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
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
    @NamedQuery(name = "Result.findByIdResult", query = "SELECT r FROM Result r WHERE r.idResult = :idResult"),
    @NamedQuery(name = "Result.findByStart", query = "SELECT r FROM Result r WHERE r.start = :start"),
    @NamedQuery(name = "Result.findByEnd", query = "SELECT r FROM Result r WHERE r.end = :end")})
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_result")
    private Integer idResult;
    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Column(name = "end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;
    @JoinColumn(name = "id_task_exercise", referencedColumnName = "id_task_exercise")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TaskExercise taskExercise;
    @JoinColumn(name = "id_answer", referencedColumnName = "id_answer")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Answer answer;
    @JoinColumn(name = "id_user_task", referencedColumnName = "id_user_task")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserTask userTask;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public TaskExercise getTaskExercise() {
        return taskExercise;
    }

    public void setTaskExercise(TaskExercise idTaskExercise) {
        this.taskExercise = idTaskExercise;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer idAnswer) {
        this.answer = idAnswer;
    }

    public UserTask getUserTask() {
        return userTask;
    }

    public void setUserTask(UserTask idUserTask) {
        this.userTask = idUserTask;
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
        return "entitys.Result[ idResult=" + idResult + " ]";
    }
    
}
