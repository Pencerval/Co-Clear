/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.Tag;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class TagFacade extends AbstractFacade<Tag> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }
    

}
