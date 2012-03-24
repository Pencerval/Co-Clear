/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.Stimulus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class StimulusFacade extends AbstractFacade<Stimulus> {
    @PersistenceContext(unitName = "coclear")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public StimulusFacade() {
        super(Stimulus.class);
    }
    
}
