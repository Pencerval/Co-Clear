/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.controllers.biefrings;

import au.com.bytecode.opencsv.CSVWriter;
import com.stimulusbriefing.entitys.Task;
import com.stimulusbriefing.entitys.User;
import com.stimulusbriefing.entitys.UserTask;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "userBriefingsController")
@ViewScoped
public class UserBriefings implements Serializable {

    @EJB
    private com.stimulusbriefing.sessionbeans.UserFacade ejbUserFacade;
    List<User> users;
    User selectedUser;
    List<Task> tasks;
    Task selectedTask;

    public UserBriefings() {
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<User> getUsers() {
        if (users == null) {
            users = ejbUserFacade.getUserbyAdmin(0);
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
    public void onUserSelect(){
        List<UserTask> userTasks=(List<UserTask>) getSelectedUser().getUserTaskCollection();
        List<Task> tasks=new LinkedList<Task>();
        for(UserTask userTask:userTasks){
            if(userTask.getComplete()==1){
                tasks.add(userTask.getIdTask());
            }
        }
        setTasks(tasks);
    }
    
    public void getCsv(){
        try {
            CSVWriter cSVWriter=new CSVWriter(new FileWriter("UserBriefing.csv"));
            List<String[]> filas=new LinkedList<String[]>();
            List<String> linea=new LinkedList<String>();
            //Cabecera
            //linea.
            
            
        } catch (IOException ex) {
            Logger.getLogger(UserBriefings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
