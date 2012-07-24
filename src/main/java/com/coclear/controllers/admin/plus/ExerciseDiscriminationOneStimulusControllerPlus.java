/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.plus;

import com.coclear.entitys.Exercise;
import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.Stimulus;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
@ManagedBean(name = "exerciseDiscriminationOneStimulusControllerPlus")
@SessionScoped
public class ExerciseDiscriminationOneStimulusControllerPlus {

    private static final long serialVersionUID = 1L;
    @EJB
    private com.coclear.sessionbeans.ExerciseFacade exerciseFacade;
    @EJB
    private com.coclear.sessionbeans.StimulusFacade stimulusFacade;
    @EJB
    private com.coclear.sessionbeans.AnswerFacade answerFacade;
    @EJB
    private com.coclear.sessionbeans.PossibleSolutionFacade possibleSolutionFacade;
    @EJB
    private com.coclear.sessionbeans.ExerciseStimulusMapFacade exerciseStimulusMapFacade;
    private List<Exercise> exerciseList;
    private Exercise[] exerciseSelected;
    private Exercise exercise = new Exercise();
    private List<Stimulus> stimulusImageList = null;
    private List<Stimulus> stimulusAudioList = null;
    private Stimulus stimulusSound = null;
    private Stimulus stimulusImage1 = null;
    private Stimulus stimulusImage2 = null;
    private boolean iguales=true;
    

    public ExerciseDiscriminationOneStimulusControllerPlus() {
    }

    //@PostConstruct
    public void preEdit(Exercise exercise) {
        if (exercise != null) {
            this.exercise = exercise;
            stimulusSound = exercise.getExerciseStimulusMapList().get(0).getStimulus();
            stimulusImage1 = exercise.getExerciseStimulusMapList().get(1).getStimulus();
            stimulusImage2 = exercise.getExerciseStimulusMapList().get(2).getStimulus();
            iguales=exercise.getPossibleSolutionList().get(0).getCorrect();
        } else {
            this.exercise = new Exercise();
            stimulusSound = null;
            stimulusImage1 = null;
            stimulusImage2 = null;
            iguales=true;
        }
    }

    public List<Exercise> getExerciseList() {
        exerciseList = exerciseFacade.findAllByType(2);
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public Exercise[] getExerciseSelected() {
        return exerciseSelected;
    }

    public void setExerciseSelected(Exercise[] exerciseSelected) {
        this.exerciseSelected = exerciseSelected;
    }

    public void delete(Exercise exercise) {
        exerciseFacade.remove(exercise);
        setExerciseList(getExerciseList());
    }

    
    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Stimulus getStimulusSound() {
        return stimulusSound;
    }

    public void setStimulusSound(Stimulus stimulus1) {
        this.stimulusSound = stimulus1;
    }

    public Stimulus getStimulusImage1() {
        return stimulusImage1;
    }

    public void setStimulusImage1(Stimulus stimulusImage1) {
        this.stimulusImage1 = stimulusImage1;
    }

    public Stimulus getStimulusImage2() {
        return stimulusImage2;
    }

    public void setStimulusImage2(Stimulus stimulusImage2) {
        this.stimulusImage2 = stimulusImage2;
    }
    
    

    public List<Stimulus> getStimulusImageList() {
        if(stimulusImageList==null){
           stimulusImageList=stimulusFacade.getByType("image");
        }
        return stimulusImageList;
    }

    public void setStimulusImageList(List<Stimulus> stimulusList) {
        this.stimulusImageList = stimulusList;
    }

    public List<Stimulus> getStimulusAudioList() {
        if(stimulusAudioList==null){
           stimulusAudioList=stimulusFacade.getByType("audio");
        }
        return stimulusAudioList;
    }

    public void setStimulusAudioList(List<Stimulus> stimulusAudioList) {
        this.stimulusAudioList = stimulusAudioList;
    }

    public boolean isIguales() {
        return iguales;
    }

    public void setIguales(boolean iguales) {
        this.iguales = iguales;
    }
    
    public void save() {
        if(getStimulusSound()==null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe indicar 1 estimulos sonoros.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else if(getStimulusImage1()==null || getStimulusImage2()==null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe indicar 2 imagenes");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            if(exercise.getName()==null || exercise.getName().isEmpty()){
                exercise.setName(getStimulusSound().getName()+"-"+(isIguales()?"Mayor-Menor":"Menor-Mayor"));
            }
            exercise.setType(2);
            if(exercise.getIdExercise()==null){
                exerciseFacade.create(exercise);
            }else{
                for(ExerciseStimulusMap exerciseStimulusMap:exercise.getExerciseStimulusMapList()){
                    exerciseStimulusMapFacade.remove(exerciseStimulusMap);
                }
                for(PossibleSolution possibleSolution:exercise.getPossibleSolutionList()){
                    possibleSolutionFacade.remove(possibleSolution);
                }
            }
            
            List<PossibleSolution> possibleSolutions=new LinkedList<PossibleSolution>();
            PossibleSolution possibleSolutionIguales=new PossibleSolution();
            possibleSolutionIguales.setAnswer(answerFacade.getSameAnswer());
            possibleSolutionIguales.setAnswerOrder(0);
            possibleSolutionIguales.setCorrect(isIguales());
            possibleSolutionIguales.setExercise(exercise);
            PossibleSolution possibleSolutionDiferentes=new PossibleSolution();
            possibleSolutionDiferentes.setAnswer(answerFacade.getDifferentAnswer());
            possibleSolutionDiferentes.setAnswerOrder(1);
            possibleSolutionDiferentes.setCorrect(!isIguales());
            possibleSolutionDiferentes.setExercise(exercise);            
            possibleSolutions.add(possibleSolutionIguales);
            possibleSolutions.add(possibleSolutionDiferentes);
            possibleSolutionFacade.create(possibleSolutionIguales);
            possibleSolutionFacade.create(possibleSolutionDiferentes);
            exercise.setPossibleSolutionList(possibleSolutions);
            
            
            ExerciseStimulusMap exerciseStimulusMap1=new ExerciseStimulusMap();
            exerciseStimulusMap1.setExercise(exercise);
            exerciseStimulusMap1.setStimulus(getStimulusSound());
            ExerciseStimulusMap exerciseStimulusMap2=new ExerciseStimulusMap();
            exerciseStimulusMap2.setExercise(exercise);
            exerciseStimulusMap2.setStimulus(getStimulusImage1());
            ExerciseStimulusMap exerciseStimulusMap3=new ExerciseStimulusMap();
            exerciseStimulusMap3.setExercise(exercise);
            exerciseStimulusMap3.setStimulus(getStimulusImage2());
            List<ExerciseStimulusMap> exerciseStimulusMaps=new LinkedList<ExerciseStimulusMap>();
            exerciseStimulusMaps.add(exerciseStimulusMap1);
            exerciseStimulusMaps.add(exerciseStimulusMap2);
            exerciseStimulusMaps.add(exerciseStimulusMap3);
            exerciseStimulusMapFacade.create(exerciseStimulusMap1);
            exerciseStimulusMapFacade.create(exerciseStimulusMap2);
            exerciseStimulusMapFacade.create(exerciseStimulusMap3);
            exercise.setExerciseStimulusMapList(exerciseStimulusMaps);
            exerciseFacade.edit(exercise);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Info", "Ejercicio guardado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        /*if (task.getIdTask() == null) {
            taskFacade.create(task);
        } else {
            taskFacade.edit(task);
            for (TaskExercise taskExercise : task.getTaskExerciseList()) {
                taskExerciseFacade.remove(taskExercise);
            }
        }
        if (getExercisesAdded() != null) {
            for (Exercise exercise : getExercisesAdded()) {
                TaskExercise taskExercise = new TaskExercise();
                taskExercise.setExercise(exercise);
                taskExercise.setExerciseOrder(0);
                taskExercise.setTask(task);
                taskExerciseFacade.create(taskExercise);
            }
        }
        try {
            FacesMessage msg = new FacesMessage("Succesful", "Tarea " + task.getName() + " creada correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/admin/task/ListPlus.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(ExerciseDiscriminationControllerPlus.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public void back(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/admin/exerciseDiscriminationOneStimulus/ListPlus.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ExerciseDiscriminationOneStimulusControllerPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
