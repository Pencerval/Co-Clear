/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.sessionbeans;

import com.stimulusbriefing.entitys.User;
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
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "com.stimulusbriefing_StimulusBriefing2_war_1.0PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User getUserByname(String login){
        Query query = em.createNamedQuery("User.findByLogin");
        query.setParameter("login", login);
        List resultList = query.getResultList();
        return (User) resultList.get(0);
    }
    
     public List<User> getUserbyAdmin (int admin){
        Query query = em.createNamedQuery("User.findByIsAdmin");
        query.setParameter("isAdmin", admin);
        return query.getResultList();
    }
    
}
