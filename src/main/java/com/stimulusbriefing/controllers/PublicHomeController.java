/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.controllers;

import com.stimulusbriefing.controllers.admin.TaskController;
import com.stimulusbriefing.controllers.admin.TaskController.TaskControllerConverter;
import com.stimulusbriefing.entitys.Task;
import com.stimulusbriefing.entitys.User;
import com.stimulusbriefing.entitys.UserTask;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "publicHomeController")
@ViewScoped
public class PublicHomeController implements Serializable {
    
    private static final long serialVersionUID = 1L;
   
    @EJB
    private com.stimulusbriefing.sessionbeans.UserFacade ejbUserFacade;
    @EJB
    private com.stimulusbriefing.sessionbeans.UserTaskFacade ejbUserTaskFacade;
    @EJB
    private com.stimulusbriefing.sessionbeans.TaskFacade ejbTaskFacade;
    
    public PublicHomeController() {
    
    }
    

        
    public List<Task> getIncompleteTask(){
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
        User user=(User) session.getAttribute("user");
        user=ejbUserFacade.getUserByname(user.getLogin());
        user.getUserTaskCollection().size();
        List<UserTask> userTasks=new ArrayList<UserTask>(user.getUserTaskCollection());
        List<Task> tasks=new LinkedList<Task>();
        for(UserTask userTask:userTasks){
            if(userTask.getComplete()==0){
                tasks.add(userTask.getIdTask());
            }
        }
        return tasks;
        
    }
    
    public String exerciseUrl(){
        TaskControllerConverter taskControllerConverter=new TaskController.TaskControllerConverter();
        Task task=(Task) taskControllerConverter.getAsObject(FacesContext.getCurrentInstance(), null, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("task"));
        if(task.getType()==0){
            return "exercises/exerciseIdentification?hola=1";
        }else{
            return "";
        }
        
    } 

}
