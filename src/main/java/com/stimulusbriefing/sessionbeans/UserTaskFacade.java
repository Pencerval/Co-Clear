/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.sessionbeans;

import com.stimulusbriefing.entitys.User;
import com.stimulusbriefing.entitys.UserTask;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pencerval
 */
@Stateless
public class UserTaskFacade extends AbstractFacade<UserTask> {
    @PersistenceContext(unitName = "com.stimulusbriefing_StimulusBriefing2_war_1.0PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTaskFacade() {
        super(UserTask.class);
    }
    
    

    
}
