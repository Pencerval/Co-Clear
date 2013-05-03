/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.TagGroupStimulusMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class TagGroupStimulusMapFacade extends AbstractFacade<TagGroupStimulusMap> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagGroupStimulusMapFacade() {
        super(TagGroupStimulusMap.class);
    }
    
}
