/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.sessionbeans;

import com.stimulusbriefing.entitys.DefaultGroupTask;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class DefaultGroupTaskFacade extends AbstractFacade<DefaultGroupTask> {
    @PersistenceContext(unitName = "com.stimulusbriefing_StimulusBriefing2_war_1.0PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public DefaultGroupTaskFacade() {
        super(DefaultGroupTask.class);
    }
    
}
