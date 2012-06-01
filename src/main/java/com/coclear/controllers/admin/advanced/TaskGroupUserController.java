/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.entitys.Task;
import com.coclear.entitys.User;
import com.coclear.entitys.UserTask;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "taskGroupUserController")
@ViewScoped
public class TaskGroupUserController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private com.coclear.sessionbeans.TaskFacade ejbTaskFacade;
    @EJB
    private com.coclear.sessionbeans.UserFacade ejbUserFacade;
    @EJB
    private com.coclear.sessionbeans.UserGroupFacade ejbUserGroupFacade;
    @EJB
    private com.coclear.sessionbeans.UserTaskFacade ejbUserTaskFacade;
    List<User> users;
    User selectedUser;
    private DualListModel<Task> task;
    private List<Task> source;
    private List<Task> target = new ArrayList<Task>();

    public TaskGroupUserController() {
    }

    public List<Task> getSource() {
        if (source == null) {
            source = ejbTaskFacade.findAll();
        }
        return source;
    }

    public void setSource(List<Task> source) {
        this.source = source;
    }

    public List<Task> getTarget() {
        return target;
    }

    public void setTarget(List<Task> target) {
        this.target = target;
    }

    public DualListModel<Task> getTask() {
        if (task == null) {
            task = new DualListModel<Task>(getSource(), getTarget());
        }
        return task;
    }

    public void setTask(DualListModel<Task> task) {
        this.task = task;
    }

    public List<User> getUsers() {
        if (users == null) {
            users = ejbUserFacade.getUserbyAdmin(false);
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void saveTaskUser(ActionEvent event) {
        try {
            if (selectedUser == null) {
                FacesMessage msg = new FacesMessage("Warning", "Necesita seleccionar un usuario");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                for (Task task : getTask().getTarget()) {
                    UserTask userTask = new UserTask();
                    userTask.setTask(task);
                    userTask.setUser(selectedUser);
                    userTask.setComplete(false);
                    ejbUserTaskFacade.create(userTask);
                }
                FacesMessage msg = new FacesMessage("Succesful", "Tareas asignadas correctamente al usuario "+selectedUser.getLogin());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @FacesConverter( value="taskUserConverter")
    public static class taskGroupUserControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TaskGroupUserController controller = (TaskGroupUserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "taskGroupUserController");
            return controller.ejbTaskFacade.find(getKey(value));
       }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }
        
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Task) {
                Task o = (Task) object;
                return getStringKey(o.getIdTask());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TaskGroupUserController.class.getName());
            }
        }
    }
}
