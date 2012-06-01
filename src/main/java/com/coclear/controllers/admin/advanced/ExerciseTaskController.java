/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.controllers.admin.ExerciseController;
import com.coclear.entitys.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
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
@ManagedBean(name = "exerciseTaskController")
@ViewScoped
public class ExerciseTaskController implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private com.coclear.sessionbeans.TaskFacade ejbTaskFacade;
    @EJB
    private com.coclear.sessionbeans.ExerciseFacade ejbExerciseFacade;
    @EJB
    private com.coclear.sessionbeans.TaskExerciseFacade ejbTaskExerciseFacade;
    @EJB
    private com.coclear.sessionbeans.StimulusGroupFacade ejbStimulusGroupFacade;
    
    
    
    private Task task=new Task();
    
    
    private List<StimulusGroup> stimulusGroupList;
    private StimulusGroup stimulusGroupSelected;
    
    private DualListModel<Exercise> exercises;
    private List<Exercise> source;
    private List<Exercise> target = new ArrayList<Exercise>();
    private Task selectedTask;
    private int idStimulus;

   
    public ExerciseTaskController() {
    }

    public List<StimulusGroup> getStimulusGroupList() {
        if(stimulusGroupList==null){
            stimulusGroupList=new LinkedList<StimulusGroup>();
            stimulusGroupList.add(new StimulusGroup(0, "Sin filtro"));
            stimulusGroupList.addAll(ejbStimulusGroupFacade.findAll());
        }
        return stimulusGroupList;
    }

    public void setStimulusGroupList(List<StimulusGroup> stimulusGroup) {
        this.stimulusGroupList = stimulusGroup;
    }

    public StimulusGroup getStimulusGroupSelected() {
        if(stimulusGroupSelected==null){
            stimulusGroupSelected=new StimulusGroup(0, "Sin filtro");
        }
        return stimulusGroupSelected;
    }

    public void setStimulusGroupSelected(StimulusGroup stimulusGroupSelected) {
        this.stimulusGroupSelected = stimulusGroupSelected;
    }
    
    public DualListModel<Exercise> getExercises() {
        if(exercises==null){
           exercises=new DualListModel<Exercise>(getSource(),getTarget());
        }
        return exercises;
    }

    public void setExercises(DualListModel<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Exercise> getSource() {
        if(source==null){
            source=ejbExerciseFacade.findAll();
        }
        return source;
    }

    public void setSource(List<Exercise> source) {
        this.source = source;
    }

    public List<Exercise> getTarget() {
        return target;
    }

    public void setTarget(List<Exercise> target) {
        this.target = target;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public int getIdStimulus() {
        return idStimulus;
    }

    public void setIdStimulus(int idStimulus) {
        this.idStimulus = idStimulus;
    }
    
    

    public void saveTask(ActionEvent event) {
        try {
            ejbTaskFacade.create(task);
            List<TaskExercise> taskExercises=new ArrayList<TaskExercise>();
            for(Exercise exercise:getExercises().getTarget()){
                int cont=0;
                TaskExercise taskExercise=new TaskExercise();
                taskExercise.setTask(task);
                taskExercise.setExercise(exercise);
                taskExercise.setExerciseOrder(cont);
                taskExercises.add(taskExercise);
                cont++;
                ejbTaskExerciseFacade.create(taskExercise);
            }
            task.setTaskExerciseList(taskExercises);
            ejbTaskFacade.edit(task);
            FacesMessage msg = new FacesMessage("Succesful", "Tarea "+task.getName()+" creada correctamente.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void stimulusGroupSelectedChanged(){
        List<Exercise> exercises=new LinkedList<Exercise>();
        if(getStimulusGroupSelected().getIdStimulusGroup()!=0){
            for(Exercise exercise:ejbExerciseFacade.findAll()){
                for(ExerciseStimulusMap exerciseStimulusMap:exercise.getExerciseStimulusMapList()){
                    if(exerciseStimulusMap.getStimulus().getStimulusGroup().getIdStimulusGroup()==getStimulusGroupSelected().getIdStimulusGroup()){
                        exercises.add(exercise);
                        break;
                    }
                }
            }
        }else{
            exercises=ejbExerciseFacade.findAll();
        }
        source=exercises;
        setExercises(new DualListModel<Exercise>(getSource(), getTarget()));
        
    }
    
    
    public String getStimulusName(Exercise exercise){
        if(exercise.getExerciseStimulusMapList().iterator().hasNext()){
            return exercise.getExerciseStimulusMapList().iterator().next().getStimulus().getName();
        }else{
            return "";
        }
        
    }    
    
    public void stimulusFromExersice(Exercise exercise){
        setIdStimulus(exercise.getExerciseStimulusMapList().iterator().next().getStimulus().getIdStimulus());
    }
    
    @FacesConverter( value="exerciseConverter")
    public static class exerciseTaskControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ExerciseTaskController controller = (ExerciseTaskController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "exerciseTaskController");
            return controller.ejbExerciseFacade.find(getKey(value));
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
            if (object instanceof Exercise) {
                Exercise o = (Exercise) object;
                return getStringKey(o.getIdExercise());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ExerciseController.class.getName());
            }
        }
    }
}
