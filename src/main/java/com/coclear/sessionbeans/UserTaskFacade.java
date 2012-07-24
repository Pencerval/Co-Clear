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
import javax.persistence.Query;

/**
 *
 * @author Pencerval
 */
@Stateless
public class UserTaskFacade extends AbstractFacade<UserTask> {
    @PersistenceContext(unitName = "coclearPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTaskFacade() {
        super(UserTask.class);
    }
    
    public List<UserTask> findAllByUserIDAndComplete(User user,boolean complete){
        Query query = em.createNamedQuery("UserTask.findByIdUserTaskAndComplete");
        query.setParameter("user", user);
        query.setParameter("complete", complete);
        return query.getResultList();
        
    }
    
}
