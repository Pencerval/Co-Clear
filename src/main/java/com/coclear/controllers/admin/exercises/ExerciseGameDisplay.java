/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.exercises;

import com.coclear.entitys.Exercise;
import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.Stimulus;
import com.coclear.entitys.TaskExercise;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * <!-- <f:metadata> <f:event type="preRenderView"
 * listener="#{taskControllerPlus.init()}"/> </f:metadata> -->
 *
 * @author Pencerval
 */
@ManagedBean(name = "exerciseGameDisplay")
@SessionScoped
public class ExerciseGameDisplay implements Serializable{

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
    private List<Stimulus> images;
    private List<Stimulus> sounds;
    private Stimulus imageSelected;
    private Stimulus soundSelected;
    
    private List<ImageSoundMap> imageSoundMap;

    public ExerciseGameDisplay() {
    }

    //@PostConstruct
    public void preEdit(Exercise exercise) {
        images = new LinkedList<Stimulus>();
        sounds = new LinkedList<Stimulus>();
        imageSoundMap=new LinkedList<ImageSoundMap>();
        if (exercise != null) {
            this.exercise = exercise;
            ImageSoundMap imageSound=new ImageSoundMap();
            for (ExerciseStimulusMap stimulusMap : exercise.getExerciseStimulusMapList()) {
                if(stimulusMap.getStimulus().getType()!=null && stimulusMap.getStimulus().getType().equals("image")){
                    imageSound.setImage(stimulusMap.getStimulus());
                }else{
                    imageSound.setSound(stimulusMap.getStimulus());
                    imageSoundMap.add(imageSound);
                }
            }
        } else {
            this.exercise = new Exercise();
        }
        images = null;
        getImages();
        sounds=null;
        imageSelected = null;
        soundSelected = null;
    }

    public List<ImageSoundMap> getImageSoundMap() {
        return imageSoundMap;
    }

    public void setImageSoundMap(List<ImageSoundMap> imageSoundMap) {
        this.imageSoundMap = imageSoundMap;
    }
    
    public List<Exercise> getExerciseList() {
        exerciseList = exerciseFacade.findAllByType(4);
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void delete(Exercise exercise) {
        for(TaskExercise taskExercise:exercise.getTaskExerciseList()){
            taskFacade.remove(taskExercise.getTask());
        }
        exerciseFacade.remove(exercise);
        setExerciseList(getExerciseList());
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List<Stimulus> getImages() {
        if (images == null) {
            images = stimulusFacade.getByType("image");
        }
        return images;
    }

    public void setImages(List<Stimulus> stimulusList) {
        this.images = stimulusList;
    }
    
    public List<Stimulus> getSounds() {
        if (sounds == null) {
            sounds = stimulusFacade.getByType("audio");
        }
        return sounds;
    }

    public void setSounds(List<Stimulus> stimulusList) {
        this.sounds = stimulusList;
    }

    public Stimulus getImageSelected() {
        return imageSelected;
    }

    public void setImageSelected(Stimulus imageSelected) {
        this.imageSelected = imageSelected;
    }

    public Stimulus getSoundSelected() {
        return soundSelected;
    }

    public void setSoundSelected(Stimulus soundSelected) {
        this.soundSelected = soundSelected;
    }
    
    public void addMap(){
        getImageSoundMap().add(new ImageSoundMap(getImageSelected(), getSoundSelected()));
    }
    
    public void save() {
        /*if (getExercise().getName() == null || getExercise().getName().isEmpty()) {
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
        }*/
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/admin/exerciseGameDisplay/ListPlus.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ExerciseGameDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class ImageSoundMap implements Serializable{
        private Stimulus image;
        private Stimulus sound;

        public ImageSoundMap() {
        }
        
        public ImageSoundMap(Stimulus image, Stimulus sound) {
            this.image = image;
            this.sound = sound;
        }
        
        public Stimulus getImage() {
            return image;
        }

        public void setImage(Stimulus image) {
            this.image = image;
        }

        public Stimulus getSound() {
            return sound;
        }

        public void setSound(Stimulus sound) {
            this.sound = sound;
        }
    }
}
