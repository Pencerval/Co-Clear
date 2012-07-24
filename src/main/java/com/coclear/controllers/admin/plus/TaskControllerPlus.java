/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.plus;

import com.coclear.entitys.Exercise;
import com.coclear.entitys.Task;
import com.coclear.entitys.TaskExercise;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * <!-- <f:metadata> <f:event type="preRenderView"
 * listener="#{taskControllerPlus.init()}"/> </f:metadata> -->
 *
 * @author Pencerval
 */
@ManagedBean(name = "taskControllerPlus")
@SessionScoped
public class TaskControllerPlus {

    private static final long serialVersionUID = 1L;
    @EJB
    private com.coclear.sessionbeans.TaskFacade taskFacade;
    @EJB
    private com.coclear.sessionbeans.ExerciseFacade exerciseFacade;
    @EJB
    private com.coclear.sessionbeans.TaskExerciseFacade taskExerciseFacade;
    private List<Task> taskList;
    private Task[] taskSelected;
    private List<Exercise> exercisesAvalibles;
    private List<Exercise> exercisesAdded = Collections.synchronizedList(new LinkedList<Exercise>());
    private int filterType = 0;
    private Exercise[] exerciseAvaliblesSelected;
    private Exercise[] exerciseAddedSelected;
    private Task task = new Task();

    public TaskControllerPlus() {
    }

    //@PostConstruct
    public void preEdit(Task task) {
        exercisesAdded.removeAll(exercisesAdded);
        if (task != null) {
            this.task = task;
            List<TaskExercise> taskExercises = task.getTaskExerciseList();
            for (TaskExercise taskExercise : taskExercises) {
                exercisesAdded.add(taskExercise.getExercise());
            }
            if (taskExercises != null && taskExercises.size() > 0) {
                setFilterType(taskExercises.get(0).getExercise().getType());
            }
        } else {
            this.task = new Task();

        }
        exercisesAvalibles = null;
        getExercisesAvalibles();
        exerciseAvaliblesSelected = null;
        exerciseAddedSelected = null;
    }

    public List<Task> getTaskList() {
        taskList = taskFacade.findAll();
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task[] getTaskSelected() {
        return taskSelected;
    }

    public void setTaskSelected(Task[] taskSelected) {
        this.taskSelected = taskSelected;
    }

    public void delete(Task stimulus) {
        taskFacade.remove(stimulus);
        setTaskList(taskFacade.findAll());
    }

    public List<Exercise> getExercisesAdded() {
        return exercisesAdded;
    }

    public void setExercisesAdded(List<Exercise> exercisesAdded) {
        this.exercisesAdded = exercisesAdded;
    }

    public List<Exercise> getExercisesAvalibles() {
        //exercisesAvalibles=exerciseFacade.findAll();
        if (exercisesAvalibles == null) {
            exercisesAvalibles = Collections.synchronizedList(new ArrayList<Exercise>());
            exercisesAvalibles.addAll(exerciseFacade.findAllByType(filterType));
        }
        exercisesAvalibles.removeAll(exercisesAdded);
        return exercisesAvalibles;
    }

    public Exercise[] getExerciseAvaliblesSelected() {
        return exerciseAvaliblesSelected;
    }

    public void setExerciseAvaliblesSelected(Exercise[] exerciseAvaliblesSelected) {
        this.exerciseAvaliblesSelected = exerciseAvaliblesSelected;
    }

    public void setExercisesAvalibles(List<Exercise> exercisesAvalibles) {
        this.exercisesAvalibles = exercisesAvalibles;
    }

    public Exercise[] getExerciseAddedSelected() {
        return exerciseAddedSelected;
    }

    public void setExerciseAddedSelected(Exercise[] exerciseAddedSelected) {
        this.exerciseAddedSelected = exerciseAddedSelected;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public void addSelected(Exercise exercise) {
        getExercisesAdded().add(exercise);
        setExercisesAdded(getExercisesAdded());
        getExercisesAvalibles().remove(exercise);
        setExercisesAvalibles(getExercisesAvalibles());
    }

    public void addAllSelected() {
        getExercisesAdded().addAll(Arrays.asList(getExerciseAvaliblesSelected()));
        setExercisesAdded(getExercisesAdded());
        getExercisesAvalibles().removeAll(Arrays.asList(getExerciseAvaliblesSelected()));
        setExercisesAvalibles(getExercisesAvalibles());
    }

    public void removeSelected(Exercise exercise) {
        getExercisesAdded().remove(exercise);
        setExercisesAdded(getExercisesAdded());
        getExercisesAvalibles().add(exercise);
        setExercisesAvalibles(getExercisesAvalibles());
    }

    public void removeAllSelected() {
        getExercisesAdded().removeAll(Arrays.asList(getExerciseAddedSelected()));
        setExercisesAdded(getExercisesAdded());
        getExercisesAvalibles().addAll(Arrays.asList(getExerciseAddedSelected()));
        setExercisesAvalibles(getExercisesAvalibles());
    }

    public void saveTask() {
        if (getTask().getName() == null || "".equals(getTask().getName())) {
            if (RequestContext.getCurrentInstance() != null) {
                RequestContext.getCurrentInstance().scrollTo("top");
            }
        }
        if (getExercisesAdded() != null) {
            for (Exercise exercise : getExercisesAdded()) {
                if (exercise.getType() == 1) {
                    task.setType(1);
                    break;
                } else if (exercise.getType() == 2) {
                    task.setType(2);
                    break;
                }
            }
        }
        if (task.getIdTask() == null) {
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
            Logger.getLogger(TaskControllerPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void filterTypeChange() {
        exerciseAvaliblesSelected = null;
        exerciseAddedSelected = null;
        exercisesAvalibles = null;
        getExercisesAvalibles();
    }

    public int exercisesAddedSize() {
        return getExercisesAdded().size();
    }

    public  synchronized  void goTop() {
        if (getTask().getName() == null || "".equals(getTask().getName())) {
            if (RequestContext.getCurrentInstance() != null) {
                RequestContext.getCurrentInstance().scrollTo("top");
            }
        }
    }
}
