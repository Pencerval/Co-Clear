/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.Stimulus;
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
public class StimulusFacade extends AbstractFacade<Stimulus> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StimulusFacade() {
        super(Stimulus.class);
    }
    
    public List<Stimulus> getByType (String type){
        Query query = em.createNamedQuery("Stimulus.findByType");
        query.setParameter("type", type);
        return query.getResultList();
    }
    
}
