/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.controllers.admin.ExcersiceController;
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
    private com.coclear.sessionbeans.ExcersiceFacade ejbExcersiceFacade;
    @EJB
    private com.coclear.sessionbeans.TaskExerciseFacade ejbTaskExerciseFacade;
    @EJB
    private com.coclear.sessionbeans.StimulusGroupFacade ejbStimulusGroupFacade;
    
    
    
    private Task task=new Task();
    
    
    private List<StimulusGroup> stimulusGroupList;
    private StimulusGroup stimulusGroupSelected;
    
    private DualListModel<Excersice> exercises;
    private List<Excersice> source;
    private List<Excersice> target = new ArrayList<Excersice>();
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
    
    public void stimulusGroupSelectedChanged(){
        List<Excersice> excersices=new LinkedList<Excersice>();
        if(getStimulusGroupSelected().getIdStimulusGroup()!=0){
            for(Excersice excersice:ejbExcersiceFacade.findAll()){
                for(ExerciseStimulusMap exerciseStimulusMap:excersice.getExerciseStimulusMapCollection()){
                    if(exerciseStimulusMap.getIdStimulus().getIdStimulusGroup().getIdStimulusGroup()==getStimulusGroupSelected().getIdStimulusGroup()){
                        excersices.add(excersice);
                        break;
                    }
                }
            }
        }else{
            excersices=ejbExcersiceFacade.findAll();
        }
        source=excersices;
        setExercises(new DualListModel<Excersice>(getSource(), getTarget()));
        
    }
    
    
    public String getStimulusName(Excersice excersice){
        if(excersice.getExerciseStimulusMapCollection().iterator().hasNext()){
            return excersice.getExerciseStimulusMapCollection().iterator().next().getIdStimulus().getName();
        }else{
            return "";
        }
        
    }    
    
    public void stimulusFromExersice(Excersice excersice){
        setIdStimulus(excersice.getExerciseStimulusMapCollection().iterator().next().getIdStimulus().getIdStimulus());
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
