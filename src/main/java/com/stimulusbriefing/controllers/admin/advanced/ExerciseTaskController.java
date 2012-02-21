/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.controllers.admin.advanced;

import com.stimulusbriefing.controllers.admin.ExcersiceController;
import com.stimulusbriefing.entitys.Excersice;
import com.stimulusbriefing.entitys.Task;
import com.stimulusbriefing.entitys.TaskExercise;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@ManagedBean(name = "exerciseTaskController")
@SessionScoped
public class ExerciseTaskController implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private com.stimulusbriefing.sessionbeans.TaskFacade ejbTaskFacade;
    @EJB
    private com.stimulusbriefing.sessionbeans.ExcersiceFacade ejbExcersiceFacade;
    @EJB
    private com.stimulusbriefing.sessionbeans.TaskExerciseFacade ejbTaskExerciseFacade;
    
    
    private Task task=new Task();
    
    private DualListModel<Excersice> exercises;
    private List<Excersice> source;
    private List<Excersice> target = new ArrayList<Excersice>();
    

   
    public ExerciseTaskController() {
    }

    public DualListModel<Excersice> getExercises() {
        if(exercises==null){
           exercises=new DualListModel<Excersice>(getSource(),getTarget());
        }
        return exercises;
    }

    public void setExercises(DualListModel<Excersice> exercises) {
        this.exercises = exercises;
    }

    public List<Excersice> getSource() {
        if(source==null){
            source=ejbExcersiceFacade.findAll();
        }
        return source;
    }

    public void setSource(List<Excersice> source) {
        this.source = source;
    }

    public List<Excersice> getTarget() {
        return target;
    }

    public void setTarget(List<Excersice> target) {
        this.target = target;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void saveTask(ActionEvent event) {
        try {
            ejbTaskFacade.create(task);
            List<TaskExercise> taskExercises=new ArrayList<TaskExercise>();
            for(Excersice exercise:getExercises().getTarget()){
                int cont=0;
                TaskExercise taskExercise=new TaskExercise();
                taskExercise.setIdTask(task);
                taskExercise.setIdExcersice(exercise);
                taskExercise.setNumber(cont);
                taskExercises.add(taskExercise);
                cont++;
                ejbTaskExerciseFacade.create(taskExercise);
            }
            task.setTaskExerciseCollection(taskExercises);
            ejbTaskFacade.edit(task);
            FacesMessage msg = new FacesMessage("Succesful", "Tarea "+task.getName()+" creada correctamente.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    
    
    @FacesConverter( value="exerciseConverter")
    public static class exerciseTaskControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ExerciseTaskController controller = (ExerciseTaskController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "exerciseTaskController");
            return controller.ejbExcersiceFacade.find(getKey(value));
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
            if (object instanceof Excersice) {
                Excersice o = (Excersice) object;
                return getStringKey(o.getIdExcersice());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ExcersiceController.class.getName());
            }
        }
    }
}
