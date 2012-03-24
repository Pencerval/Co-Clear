/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers;

import com.coclear.controllers.admin.TaskController;
import com.coclear.controllers.admin.TaskController.TaskControllerConverter;
import com.coclear.entitys.Task;
import com.coclear.entitys.User;
import com.coclear.entitys.UserTask;
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
    private com.coclear.sessionbeans.UserFacade ejbUserFacade;
    @EJB
    private com.coclear.sessionbeans.UserTaskFacade ejbUserTaskFacade;
    @EJB
    private com.coclear.sessionbeans.TaskFacade ejbTaskFacade;
    
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
