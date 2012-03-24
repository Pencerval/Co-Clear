/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.DefaultGroupTask;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class DefaultGroupTaskFacade extends AbstractFacade<DefaultGroupTask> {
    @PersistenceContext(unitName = "coclear")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public DefaultGroupTaskFacade() {
        super(DefaultGroupTask.class);
    }
    
}
