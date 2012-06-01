/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.AnswerGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class AnswerGroupFacade extends AbstractFacade<AnswerGroup> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerGroupFacade() {
        super(AnswerGroup.class);
    }

    @Override
    public List<AnswerGroup> findAll() {
        List<AnswerGroup> answerGroups=super.findAll();
        AnswerGroup answerGroupDiscrimination=null;
        for(AnswerGroup answerGroup:answerGroups){
            if(answerGroup.getName().equals("xxxexercisediscriminationanswergroupxxx")){
                answerGroupDiscrimination=answerGroup;
                break;
            }
        }
        answerGroups.remove(answerGroupDiscrimination);
        return answerGroups;
    }
    
    
    
}
