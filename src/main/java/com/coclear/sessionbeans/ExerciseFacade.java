/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.Exercise;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * 0 identificacion
 * 1 discriminacion
 * 2 identificacion entonacion
 * 3 Memo
 * @author Pencerval
 */
@Stateless
public class ExerciseFacade extends AbstractFacade<Exercise> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExerciseFacade() {
        super(Exercise.class);
    }
    
    
    public List<Exercise> findAllByType(int type){
        Query query = em.createNamedQuery("Exercise.findByType");
        query.setParameter("type", type);
        return query.getResultList();
    }
    
}
