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
@Table(name = "answer_stimulus_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnswerStimulusMap.findAll", query = "SELECT a FROM AnswerStimulusMap a"),
    @NamedQuery(name = "AnswerStimulusMap.findByIdAnswerStimulusMap", query = "SELECT a FROM AnswerStimulusMap a WHERE a.idAnswerStimulusMap = :idAnswerStimulusMap")})
public class AnswerStimulusMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_answer_stimulus_map")
    private Integer idAnswerStimulusMap;

    public AnswerStimulusMap() {
    }

    public AnswerStimulusMap(Integer idAnswerStimulusMap) {
        this.idAnswerStimulusMap = idAnswerStimulusMap;
    }

    public Integer getIdAnswerStimulusMap() {
        return idAnswerStimulusMap;
    }

    public void setIdAnswerStimulusMap(Integer idAnswerStimulusMap) {
        this.idAnswerStimulusMap = idAnswerStimulusMap;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnswerStimulusMap != null ? idAnswerStimulusMap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnswerStimulusMap)) {
            return false;
        }
        AnswerStimulusMap other = (AnswerStimulusMap) object;
        if ((this.idAnswerStimulusMap == null && other.idAnswerStimulusMap != null) || (this.idAnswerStimulusMap != null && !this.idAnswerStimulusMap.equals(other.idAnswerStimulusMap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.AnswerStimulusMap[ idAnswerStimulusMap=" + idAnswerStimulusMap + " ]";
    }
    
}
