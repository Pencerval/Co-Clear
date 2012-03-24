/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers;

import com.coclear.entitys.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "navigationController")
@SessionScoped
public class NavigationController implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean loged;
    private String name;

    public NavigationController() {
    }

    public boolean getLoged() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (session.getAttribute("user") != null) {
            return true;
        } else {
            return false;
        }
    }

    public void setLoged(boolean loged) {
        this.loged = loged;
    }

    public String getName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user=(User)session.getAttribute("user");
        if ( user != null) {
            return user.getLogin();
        } 
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index";
    }

    
}
