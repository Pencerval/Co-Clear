/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers;

import com.coclear.controllers.admin.UserTaskController;
import com.coclear.entitys.User;
import com.coclear.entitys.UserTask;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "publicHomeController")
@ViewScoped
public class PublicHomeController implements Serializable {
    
    private static final long serialVersionUID = 1L;
   
    @EJB
    private com.coclear.sessionbeans.UserFacade ejbUserFacade;
    @EJB
    private com.coclear.sessionbeans.UserTaskFacade ejbUserTaskFacade;
    @EJB
    private com.coclear.sessionbeans.TaskFacade ejbTaskFacade;
    
    public PublicHomeController() {
    
    }
    

        
    public List<UserTask> getIncompleteTask(){
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
        User user=(User) session.getAttribute("user");
        //user=ejbUserFacade.getUserByname(user.getLogin());
        //user=ejbUserFacade.find(user.getIdUser());
        //user.getUserTaskCollection().size();
        user.getUserTaskList();
        List<UserTask> userTasks=new ArrayList<UserTask>(user.getUserTaskList());
        List<UserTask> incompleteUserTasktasks=new LinkedList<UserTask>();
        for(UserTask userTask:userTasks){
            if(!userTask.getComplete()){
                incompleteUserTasktasks.add(userTask);
            }
        }
        return incompleteUserTasktasks;
    }
    
    public String exerciseUrl(){
        UserTaskController.UserTaskControllerConverter userTaskControllerConverter=new UserTaskController.UserTaskControllerConverter();
        UserTask task=(UserTask) userTaskControllerConverter.getAsObject(FacesContext.getCurrentInstance(), null, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("task"));
        if(task.getTask().getType()==0){
            return "exercises/exerciseIdentification";
        }else if(task.getTask().getType()==1){
            return "exercises/exerciseDiscrimination";
        }else if(task.getTask().getType()==2){
            return "exercises/exerciseDiscriminationOneStimulus";
        }else{
            return "";
        }
    } 

}
