/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "fastTestController")
@SessionScoped
public class FastTestController implements Serializable {

    private static final long serialVersionUID = 1L;

    public FastTestController() {
    }

    public String createFastUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(true);
        HttpSession httpSession = (HttpSession) session;
        httpSession.setAttribute("fast", true);
        return "/admin/userGroup/EditPlus";
    }
    
    public String createNormalUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(true);
        HttpSession httpSession = (HttpSession) session;
        httpSession.setAttribute("fast", false);
        return "/admin/userGroup/ListPlus";
    }
    
    

    
    /*
    public String begin() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(true);
        HttpSession httpSession = (HttpSession) session;
        User user = (User) httpSession.getAttribute("fastuser");
        List<Task> tasks = taskFacade.getTaskByAutoAsign();
        for (Task task : tasks) {
            UserTask userTask = new UserTask();
            userTask.setTask(task);
            userTask.setUser(user);
            userTask.setComplete(false);
            userTaskFacade.create(userTask);
        }
        return "";
    }
    */
    
}
