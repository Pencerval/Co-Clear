/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.sessionbeans;

import com.coclear.entitys.User;
import com.coclear.entitys.UserTask;
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
    @PersistenceContext(unitName = "coclear")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTaskFacade() {
        super(UserTask.class);
    }
    
    

    
}
