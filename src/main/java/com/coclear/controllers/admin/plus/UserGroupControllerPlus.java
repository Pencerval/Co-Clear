/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.plus;

import com.coclear.controllers.admin.exercises.ExerciseDiscriminationOneStimulusControllerPlus;
import com.coclear.entitys.Task;
import com.coclear.entitys.User;
import com.coclear.entitys.UserTask;
import com.coclear.sessionbeans.TaskFacade;
import com.coclear.sessionbeans.UserFacade;
import com.coclear.sessionbeans.UserTaskFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * <!-- <f:metadata> <f:event type="preRenderView"
 * listener="#{taskControllerPlus.init()}"/> </f:metadata> -->
 *
 * @author Pencerval
 */
@ManagedBean(name = "userGroupControllerPlus")
@SessionScoped
public class UserGroupControllerPlus implements Serializable {

    @EJB
    private UserFacade userFacade;
    @EJB
    private UserTaskFacade userTaskFacade;
    @EJB
    private TaskFacade taskFacade;
    private List<User> userList;
    private User user;
    private Boolean isImplanted;
    private SelectItem[] rolOptions=null;

    public UserGroupControllerPlus() {
    }

    //@PostConstruct
    public void preEdit(User user) {
        if (user != null) {
            this.user = user;
        } else {
            this.user = new User();

        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        if (userList == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            User user = (User) externalContext.getSessionMap().get("user");
            if (user.getIsAdmin() == 1) {
                userList = userFacade.findAll();
            } else {
                userList = userFacade.getUserbyAdmin(0);
            }
        }
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void delete(User user) {
        userFacade.remove(user);
        setUserList(userFacade.findAll());
    }

    public String saveUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Boolean fast = (Boolean) externalContext.getSessionMap().get("fast");
        if (fast != null && fast) {
            getUser().setLogin("FastUser");
            getUser().setPassword("FastUser");
            userFacade.create(user);
            getUser().setLogin("FastUser-" + user.getIdUser());
            getUser().setPassword("FastUser-" + user.getIdUser());
            userFacade.edit(user);
            //externalContext.getSessionMap().put("fastuser", user);
            List<Task> tasks = taskFacade.getTaskByAutoAsign();
            for (Task task : tasks) {
                UserTask userTask = new UserTask();
                userTask.setTask(task);
                userTask.setUser(user);
                userTask.setComplete(false);
                userTaskFacade.create(userTask);
            }
            externalContext.getSessionMap().put("user", user);
            return "/public/index";
        } else {
            if (getUser().getLogin() == null || "".equals(user.getLogin())) {
                FacesMessage msg = new FacesMessage("Warning", "Login incorrecto");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (getUser().getPassword() == null || "".equals(user.getPassword())) {
                FacesMessage msg = new FacesMessage("Warning", "Password incorrecto");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                if (user.getIdUser() != null) {
                    userFacade.edit(user);
                    FacesMessage msg = new FacesMessage("Succesful", "Usuario " + user.getLogin() + " editado correctamente.");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    userFacade.create(user);
                    FacesMessage msg = new FacesMessage("Succesful", "Usuario " + user.getLogin() + " creado correctamente.");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                setUserList(userFacade.findAll());
            }
            return "";
        }
    }

    public Boolean getIsImplanted() {
        return isImplanted;
    }

    public void setIsImplanted(Boolean isImplanted) {
        this.isImplanted = isImplanted;
    }
    
    public SelectItem[] getRolOptions() {  
        if(rolOptions==null){
            rolOptions=new SelectItem[]{new SelectItem("","Todos"),new SelectItem(0,"User"),new SelectItem(1,"Admin"),new SelectItem(2,"Manager")};
        }
        return rolOptions;  
    }  
    
    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/admin/userGroup/ListPlus.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ExerciseDiscriminationOneStimulusControllerPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
