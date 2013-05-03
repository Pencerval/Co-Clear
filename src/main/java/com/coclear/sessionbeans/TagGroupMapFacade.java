/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.TagGroupMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class TagGroupMapFacade extends AbstractFacade<TagGroupMap> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagGroupMapFacade() {
        super(TagGroupMap.class);
    }
    
}
