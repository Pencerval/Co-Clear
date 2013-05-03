/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers;

import com.coclear.controllers.util.JsfUtil;
import com.coclear.entitys.Exercise;
import com.coclear.entitys.User;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {
    
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final long serialVersionUID = 1L;
    public String username;
    public String pass;
    @EJB
    private com.coclear.sessionbeans.UserFacade ejbFacade;
    @EJB
    private com.coclear.sessionbeans.ExerciseFacade exerciseFacade;

    public LoginController() {
    }

    public String login() {
        User user = new User();
        user.setLogin(username);
        user.setPassword(pass);
        try {
            user = ejbFacade.getUserByname(username);
            //user = ejbFacade.find(user);
        } catch (Exception e) {
            logger.error("SQL problem ",e);
            JsfUtil.addErrorMessage("Login Failed");
            return "";
        }
        if(user==null || !user.getPassword().equals(pass)){
            JsfUtil.addErrorMessage("Login Failed");
            return "";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(true);
        HttpSession httpSession = (HttpSession) session;
        httpSession.setAttribute("user", user);
        if (user.getIsAdmin()>=1) {
            return "/admin/index";
        } else {
            return "/public/index";
        }
    }

    public String anonymous() {
        User user = new User();
        user.setLogin("Guest-" + new Date().getTime());
        user.setPassword("1234");
        
        ejbFacade.create(user);
        user.setLogin("Guest-" + user.getIdUser());
        ejbFacade.edit(user);
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(true);
        HttpSession httpSession = (HttpSession) session;
        httpSession.setAttribute("user", user);
        return "public/index";
    }

    public String create() {
        return "public/createuser";
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }
}
