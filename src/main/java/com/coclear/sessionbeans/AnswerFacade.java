/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.Answer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pencerval
 */
@Stateless
public class AnswerFacade extends AbstractFacade<Answer> {

    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }

    @Override
    public List<Answer> findAll() {
        List<Answer> answers = super.findAll();
        Answer answerDiscriminationSame = null;
        Answer answerDiscriminationDifferent = null;
        for (Answer answer : answers) {
            if (answer.getName().equals("xxxsamexxx")) {
                answerDiscriminationSame = answer;
            } else if (answer.getName().equals("xxxdifferentxxx")) {
                answerDiscriminationDifferent = answer;
            }
            if (answerDiscriminationSame != null && answerDiscriminationDifferent != null) {
                break;
            }
        }
        answers.remove(answerDiscriminationSame);
        answers.remove(answerDiscriminationDifferent);
        return answers;
    }

    public Answer getSameAnswer() {
        Query query = em.createNamedQuery("Answer.findByName");
        query.setParameter("name", "xxxsamexxx");
        List resultList = query.getResultList();
        return (Answer) resultList.get(0);
    }

    public Answer getDifferentAnswer() {
        Query query = em.createNamedQuery("Answer.findByName");
        query.setParameter("name", "xxxdifferentxxx");
        List resultList = query.getResultList();
        return (Answer) resultList.get(0);
    }
}
