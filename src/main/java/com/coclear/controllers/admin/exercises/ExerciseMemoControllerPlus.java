/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.exercises;

import com.coclear.entitys.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * <!-- <f:metadata> <f:event type="preRenderView"
 * listener="#{taskControllerPlus.init()}"/> </f:metadata> -->
 *
 * @author Pencerval
 */
@ManagedBean(name = "exerciseMemoControllerPlus")
@SessionScoped
public class ExerciseMemoControllerPlus implements Serializable{

    private static final long serialVersionUID = 1L;
    @EJB
    private com.coclear.sessionbeans.ExerciseFacade exerciseFacade;
    @EJB
    private com.coclear.sessionbeans.StimulusFacade stimulusFacade;
    @EJB
    private com.coclear.sessionbeans.TaskFacade taskFacade;
    @EJB
    private com.coclear.sessionbeans.TaskExerciseFacade taskExerciseFacade;
    @EJB
    private com.coclear.sessionbeans.ExerciseStimulusMapFacade exerciseStimulusMapFacade;
    private List<Exercise> exerciseList;
    private Exercise exercise = new Exercise();
    private List<Stimulus> stimulusImageList = null;
    private List<Stimulus> imagesAvalibles;
    private List<Stimulus> imagesAdded = new LinkedList<Stimulus>();
    private Stimulus[] imagesAvaliblesSelected;
    private Stimulus[] imagesAddedSelected;

    public ExerciseMemoControllerPlus() {
    }

    //@PostConstruct
    public void preEdit(Exercise exercise) {
        imagesAdded = new LinkedList<Stimulus>();
        if (exercise != null) {
            this.exercise = exercise;
            for (ExerciseStimulusMap stimulusMap : exercise.getExerciseStimulusMapList()) {
                imagesAdded.add(stimulusMap.getStimulus());
            }
        } else {
            this.exercise = new Exercise();
        }
        imagesAvalibles = null;
        getImagesAvalibles();
        imagesAvaliblesSelected = null;
        imagesAddedSelected = null;
    }

    public List<Exercise> getExerciseList() {
        exerciseList = exerciseFacade.findAllByType(3);
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void delete(Exercise exercise) {
        Task task = null;
        for(TaskExercise taskExercise:exercise.getTaskExerciseList()){
            task=taskExercise.getTask();
            break;
        }
        exerciseFacade.remove(exercise);
        taskFacade.remove(task);
        setExerciseList(getExerciseList());
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List<Stimulus> getStimulusImageList() {
        if (stimulusImageList == null) {
            stimulusImageList = stimulusFacade.getByType("image");
        }
        return stimulusImageList;
    }

    public void setStimulusImageList(List<Stimulus> stimulusList) {
        this.stimulusImageList = stimulusList;
    }

    public List<Stimulus> getImagesAdded() {
        return imagesAdded;
    }

    public void setImagesAdded(List<Stimulus> imagesAdded) {
        this.imagesAdded = imagesAdded;
    }

    public Stimulus[] getImagesAddedSelected() {
        return imagesAddedSelected;
    }

    public void setImagesAddedSelected(Stimulus[] imagesAddedSelected) {
        this.imagesAddedSelected = imagesAddedSelected;
    }

    public List<Stimulus> getImagesAvalibles() {
        if (imagesAvalibles == null) {
            imagesAvalibles = new ArrayList<Stimulus>();
            imagesAvalibles.addAll(stimulusFacade.getByType("image"));
        }
        imagesAvalibles.removeAll(getImagesAdded());
        return imagesAvalibles;
    }

    public void setImagesAvalibles(List<Stimulus> imagesAvalibles) {
        this.imagesAvalibles = imagesAvalibles;
    }

    public Stimulus[] getImagesAvaliblesSelected() {
        return imagesAvaliblesSelected;
    }

    public void setImagesAvaliblesSelected(Stimulus[] imagesAvaliblesSelected) {
        this.imagesAvaliblesSelected = imagesAvaliblesSelected;
    }

    public void addSelected(Stimulus stimulus) {
        getImagesAdded().add(stimulus);
        setImagesAdded(getImagesAdded());
        getImagesAvalibles().remove(stimulus);
        setImagesAvalibles(getImagesAvalibles());
    }

    public void addAllSelected() {
        getImagesAdded().addAll(Arrays.asList(getImagesAvaliblesSelected()));
        setImagesAdded(getImagesAdded());
        getImagesAvalibles().removeAll(Arrays.asList(getImagesAvaliblesSelected()));
        setImagesAvalibles(getImagesAvalibles());
    }

    public void removeSelected(Stimulus stimulus) {
        getImagesAdded().remove(stimulus);
        setImagesAdded(getImagesAdded());
        getImagesAvalibles().add(stimulus);
        setImagesAvalibles(getImagesAvalibles());
    }

    public void removeAllSelected() {
        getImagesAdded().removeAll(Arrays.asList(getImagesAddedSelected()));
        setImagesAdded(getImagesAdded());
        getImagesAvalibles().addAll(Arrays.asList(getImagesAddedSelected()));
        setImagesAvalibles(getImagesAvalibles());
    }

    public void save() {
        if (getExercise().getName() == null || getExercise().getName().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe indicar el nombre del ejercicio.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (getImagesAdded().isEmpty() || getImagesAdded().size() < 2) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe indicar 2 imagenes minimo.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            exercise.setType(3);
            if (exercise.getIdExercise() != null) {
                exerciseFacade.edit(exercise);
            } else {
                exerciseFacade.create(exercise);
            }
            ExerciseStimulusMap exerciseStimulusMap;
            List<ExerciseStimulusMap> exerciseStimulusMaps = new LinkedList<ExerciseStimulusMap>();
            for (Stimulus stimulus : getImagesAdded()) {
                exerciseStimulusMap = new ExerciseStimulusMap();
                exerciseStimulusMap.setExercise(exercise);
                exerciseStimulusMap.setStimulus(stimulus);
                exerciseStimulusMapFacade.create(exerciseStimulusMap);
                exerciseStimulusMaps.add(exerciseStimulusMap);
            }
            exercise.setExerciseStimulusMapList(exerciseStimulusMaps);
            exerciseFacade.edit(exercise);
            Task task = new Task();
            task.setType(3);
            task.setName(exercise.getName());
            taskFacade.create(task);
            TaskExercise taskExercise = new TaskExercise();
            taskExercise.setExercise(exercise);
            taskExercise.setTask(task);
            taskExerciseFacade.create(taskExercise);
            List<TaskExercise> taskExercises = new LinkedList<TaskExercise>();
            taskExercises.add(taskExercise);
            task.setTaskExerciseList(taskExercises);
            taskFacade.edit(task);
            FacesMessage msg = new FacesMessage("Succesful", "Ejercicio " + task.getName() + " creado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        /*
         * if (getStimulusSound() == null) { FacesMessage msg = new
         * FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe indicar 1
         * estimulos sonoros.");
         * FacesContext.getCurrentInstance().addMessage(null, msg); } else if
         * (getStimulusImage1() == null || getStimulusImage2() == null) {
         * FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
         * "Error", "Debe indicar 2 imagenes");
         * FacesContext.getCurrentInstance().addMessage(null, msg); } else { if
         * (exercise.getName() == null || exercise.getName().isEmpty()) {
         * exercise.setName(getStimulusSound().getName() + "-" + (isIguales() ?
         * "Mayor-Menor" : "Menor-Mayor")); } exercise.setType(2); if
         * (exercise.getIdExercise() == null) { exerciseFacade.create(exercise);
         * } else { for (ExerciseStimulusMap exerciseStimulusMap :
         * exercise.getExerciseStimulusMapList()) {
         * exerciseStimulusMapFacade.remove(exerciseStimulusMap); } for
         * (PossibleSolution possibleSolution :
         * exercise.getPossibleSolutionList()) {
         * possibleSolutionFacade.remove(possibleSolution); } }
         *
         * List<PossibleSolution> possibleSolutions = new
         * LinkedList<PossibleSolution>(); PossibleSolution
         * possibleSolutionIguales = new PossibleSolution();
         * possibleSolutionIguales.setAnswer(answerFacade.getSameAnswer());
         * possibleSolutionIguales.setAnswerOrder(0);
         * possibleSolutionIguales.setCorrect(isIguales());
         * possibleSolutionIguales.setExercise(exercise); PossibleSolution
         * possibleSolutionDiferentes = new PossibleSolution();
         * possibleSolutionDiferentes.setAnswer(answerFacade.getDifferentAnswer());
         * possibleSolutionDiferentes.setAnswerOrder(1);
         * possibleSolutionDiferentes.setCorrect(!isIguales());
         * possibleSolutionDiferentes.setExercise(exercise);
         * possibleSolutions.add(possibleSolutionIguales);
         * possibleSolutions.add(possibleSolutionDiferentes);
         * possibleSolutionFacade.create(possibleSolutionIguales);
         * possibleSolutionFacade.create(possibleSolutionDiferentes);
         * exercise.setPossibleSolutionList(possibleSolutions);
         *
         *
         * ExerciseStimulusMap exerciseStimulusMap1 = new ExerciseStimulusMap();
         * exerciseStimulusMap1.setExercise(exercise);
         * exerciseStimulusMap1.setStimulus(getStimulusSound());
         * ExerciseStimulusMap exerciseStimulusMap2 = new ExerciseStimulusMap();
         * exerciseStimulusMap2.setExercise(exercise);
         * exerciseStimulusMap2.setStimulus(getStimulusImage1());
         * ExerciseStimulusMap exerciseStimulusMap3 = new ExerciseStimulusMap();
         * exerciseStimulusMap3.setExercise(exercise);
         * exerciseStimulusMap3.setStimulus(getStimulusImage2());
         * List<ExerciseStimulusMap> exerciseStimulusMaps = new
         * LinkedList<ExerciseStimulusMap>();
         * exerciseStimulusMaps.add(exerciseStimulusMap1);
         * exerciseStimulusMaps.add(exerciseStimulusMap2);
         * exerciseStimulusMaps.add(exerciseStimulusMap3);
         * exerciseStimulusMapFacade.create(exerciseStimulusMap1);
         * exerciseStimulusMapFacade.create(exerciseStimulusMap2);
         * exerciseStimulusMapFacade.create(exerciseStimulusMap3);
         * exercise.setExerciseStimulusMapList(exerciseStimulusMaps);
         * exerciseFacade.edit(exercise); FacesMessage msg = new
         * FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ejercicio guardado
         * correctamente"); FacesContext.getCurrentInstance().addMessage(null,
         * msg); } /* if (task.getIdTask() == null) { taskFacade.create(task); }
         * else { taskFacade.edit(task); for (TaskExercise taskExercise :
         * task.getTaskExerciseList()) {
         * taskExerciseFacade.remove(taskExercise); } } if (getExercisesAdded()
         * != null) { for (Exercise exercise : getExercisesAdded()) {
         * TaskExercise taskExercise = new TaskExercise();
         * taskExercise.setExercise(exercise); taskExercise.setExerciseOrder(0);
         * taskExercise.setTask(task); taskExerciseFacade.create(taskExercise);
         * } } try { FacesMessage msg = new FacesMessage("Succesful", "Tarea " +
         * task.getName() + " creada correctamente");
         * FacesContext.getCurrentInstance().addMessage(null, msg);
         * FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
         * + "/admin/task/ListPlus.xhtml"); } catch (Exception ex) {
         * Logger.getLogger(ExerciseDiscriminationControllerPlus.class.getName()).log(Level.SEVERE,
         * null, ex); }
         */
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/admin/exerciseMemo/ListPlus.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ExerciseMemoControllerPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
