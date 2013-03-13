/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.AnswerStimulusMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class AnswerStimulusMapFacade extends AbstractFacade<AnswerStimulusMap> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerStimulusMapFacade() {
        super(AnswerStimulusMap.class);
    }
    
}
