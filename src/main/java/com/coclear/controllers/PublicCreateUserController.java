/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.sos
 */
package com.coclear.controllers;

import com.coclear.entitys.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
@ManagedBean(name = "publicCreateUserController")
@ViewScoped
public class PublicCreateUserController implements Serializable {

    private static final long serialVersionUID = 1L;
    public User user=new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @EJB
    private com.coclear.sessionbeans.UserFacade ejbFacade;

    public PublicCreateUserController() {
    }

    public String create() {
        try{
            ejbFacade.create(user);
            FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getLogin());  
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            Object session = externalContext.getSession(true);
            HttpSession httpSession = (HttpSession) session;
            httpSession.setAttribute("user", user);
        }catch(Exception e){
            e.printStackTrace();
            FacesMessage msg = new FacesMessage("Error", "Cant create :" + user.getLogin());  
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "";
        }
        return "public/index";
    }

     
     public String getBoolean(int value){
         if(value == 1){
             return "Yes";
         }else{
             return "No";
         }
     }
     
     public String getBooleanGender(int value){
         if(value == 1){
             return "Male";
         }else{
             return "Female";
         }
     }
     
     
    
    
}
