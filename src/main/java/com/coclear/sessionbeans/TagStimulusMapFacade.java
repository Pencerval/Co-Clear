/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.Stimulus;
import com.coclear.entitys.Tag;
import com.coclear.entitys.TagStimulusMap;
import java.util.LinkedList;
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
public class TagStimulusMapFacade extends AbstractFacade<TagStimulusMap> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagStimulusMapFacade() {
        super(TagStimulusMap.class);
    }
    
    
}
