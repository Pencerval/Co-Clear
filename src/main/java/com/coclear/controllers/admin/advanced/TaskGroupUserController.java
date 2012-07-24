/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.entitys.*;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "userTaskControllerPlus")
@SessionScoped
public class TaskGroupUserController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private com.coclear.sessionbeans.TaskFacade taskFacade;
    @EJB
    private com.coclear.sessionbeans.UserFacade userFacade;
    @EJB
    private com.coclear.sessionbeans.UserTaskFacade userTaskFacade;
    @EJB
    private com.coclear.sessionbeans.UserGroupFacade userGroupFacade;
    private List<User> users;
    private User userSelected;
    private List<UserGroup> userGroups;
    private UserGroup userGroupSelected;
    private List<Task> taskAvalibles;
    private List<Task> taskAdded;
    private Task[] taskAvaliblesSelected;
    private Task[] taskAddedSelected;

    public TaskGroupUserController() {
    }

    public void preEdit() {
        userSelected = null;
        userGroupSelected = null;
        taskAvalibles = null;
        taskAdded = null;
        taskAddedSelected = null;
        taskAvaliblesSelected = null;

    }

    public List<Task> getTaskAdded() {
        if (taskAdded == null) {
            taskAdded = Collections.synchronizedList(new LinkedList<Task>());
        }
        return taskAdded;
    }

    public void setTaskAdded(List<Task> taskAdded) {
        this.taskAdded = taskAdded;
    }

    public Task[] getTaskAddedSelected() {
        return taskAddedSelected;
    }

    public void setTaskAddedSelected(Task[] taskAddedSelected) {
        this.taskAddedSelected = taskAddedSelected;
    }

    public Task[] getTaskAvaliblesSelected() {
        return taskAvaliblesSelected;
    }

    public void setTaskAvaliblesSelected(Task[] taskAvaliblesSelected) {
        this.taskAvaliblesSelected = taskAvaliblesSelected;
    }

    public List<Task> getTaskAvalibles() {
        if (taskAvalibles == null) {
            taskAvalibles = Collections.synchronizedList(new LinkedList<Task>());
        }
        return taskAvalibles;
    }

    public void setTaskAvalibles(List<Task> tasksAvalibles) {
        this.taskAvalibles = tasksAvalibles;
    }

    public List<UserGroup> getUserGroups() {
        if (userGroups == null) {
            userGroups = userGroupFacade.findAll();
        }
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<User> getUsers() {
        if (users == null) {
            users = userFacade.getUserbyAdmin(false);
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(User selectedUser) {
        if (selectedUser != null && selectedUser!=userSelected) {
            this.userSelected = selectedUser;
            taskAvalibles = Collections.synchronizedList(new LinkedList<Task>());
            taskAdded = Collections.synchronizedList(new LinkedList<Task>());
            List<UserTask> userTasks = userTaskFacade.findAllByUserIDAndComplete(userSelected, false);
            for (UserTask userTask : userTasks) {
                taskAdded.add(userTask.getTask());
            }
            taskAvalibles = taskFacade.findAll();
            taskAvalibles.removeAll(getTaskAdded());
        }
        
    }

    public UserGroup getUserGroupSelected() {
        return userGroupSelected;
    }

    public void setUserGroupSelected(UserGroup userGroupSelected) {
        if (userGroupSelected != null && this.userGroupSelected!=userGroupSelected) {
            this.userGroupSelected = userGroupSelected;
            taskAvalibles = Collections.synchronizedList(new LinkedList<Task>());
            taskAdded = Collections.synchronizedList(new LinkedList<Task>());
            for (UserGroupMap userGroupMap : userGroupSelected.getUserGroupMapList()) {
                Map<Task, Integer> userTaskNumber = new ConcurrentHashMap<Task, Integer>();
                for (UserTask userTask : userGroupMap.getUser().getUserTaskList()) {
                    if (userTaskNumber.containsKey(userTask.getTask())) {
                        userTaskNumber.put(userTask.getTask(), userTaskNumber.get(userTask.getTask()) + 1);
                    } else {
                        userTaskNumber.put(userTask.getTask(), 1);
                    }
                }
                for (Map.Entry<Task, Integer> entry : userTaskNumber.entrySet()) {
                    if (entry.getValue() == userGroupMap.getUserGroup().getUserGroupMapList().size()) {
                        taskAdded.add(entry.getKey());
                    }
                }
            }
            taskAvalibles = taskFacade.findAll();
            taskAvalibles.removeAll(getTaskAdded());
        }
    }

    public void addSelected(Task task) {
        getTaskAdded().add(task);
        setTaskAdded(getTaskAdded());
        getTaskAvalibles().remove(task);
        setTaskAvalibles(getTaskAvalibles());
    }

    public void addAllSelected() {
        getTaskAdded().addAll(Arrays.asList(getTaskAvaliblesSelected()));
        setTaskAdded(getTaskAdded());
        getTaskAvalibles().removeAll(Arrays.asList(getTaskAvaliblesSelected()));
        setTaskAvalibles(getTaskAvalibles());
    }

    public void removeSelected(Task task) {
        getTaskAdded().remove(task);
        setTaskAdded(getTaskAdded());
        getTaskAvalibles().add(task);
        setTaskAvalibles(getTaskAvalibles());
    }

    public void removeAllSelected() {
        getTaskAdded().removeAll(Arrays.asList(getTaskAddedSelected()));
        setTaskAdded(getTaskAdded());
        getTaskAvalibles().addAll(Arrays.asList(getTaskAddedSelected()));
        setTaskAvalibles(getTaskAvalibles());
    }

    public void saveUserTask() {

        try {
            if (userSelected == null && userGroupSelected == null) {
                FacesMessage msg = new FacesMessage("Warning", "Necesita seleccionar un usuario o un grupo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                if (userSelected != null) {
                    List<UserTask> userTasks=userTaskFacade.findAllByUserIDAndComplete(userSelected, false);
                    for(UserTask userTask:userTasks){
                        userTaskFacade.remove(userTask);
                    }
                    for (Task task : getTaskAdded()) {
                        UserTask userTask = new UserTask();
                        userTask.setTask(task);
                        userTask.setUser(userSelected);
                        userTask.setComplete(false);
                        userTaskFacade.create(userTask);
                    }
                    FacesMessage msg = new FacesMessage("Succesful", "Tareas asignadas correctamente al usuario " + userSelected.getLogin());
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else if (userGroupSelected != null) {
                    //TODO
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
