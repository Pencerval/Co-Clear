/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.controllers.exercises;

import com.stimulusbriefing.controllers.admin.AnswerController;
import com.stimulusbriefing.controllers.admin.TaskController;
import com.stimulusbriefing.controllers.admin.TaskController.TaskControllerConverter;
import com.stimulusbriefing.entitys.Answer;
import com.stimulusbriefing.entitys.Excersice;
import com.stimulusbriefing.entitys.ExerciseStimulusMap;
import com.stimulusbriefing.entitys.PossibleSolution;
import com.stimulusbriefing.entitys.Result;
import com.stimulusbriefing.entitys.Stimulus;
import com.stimulusbriefing.entitys.Task;
import com.stimulusbriefing.entitys.TaskExercise;
import com.stimulusbriefing.entitys.User;
import com.stimulusbriefing.entitys.UserTask;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "playExerciseIdentificationController")
@ViewScoped
public class ExerciseIdentificationController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //Necesario para el hack
    @EJB
    private com.stimulusbriefing.sessionbeans.AnswerFacade ejbAnswerFacade;
    List<Answer> answers = new LinkedList<Answer>();
    //Fin necesario hack
    
    @EJB
    private com.stimulusbriefing.sessionbeans.ResultFacade ejbResultFacade;
    @EJB
    private com.stimulusbriefing.sessionbeans.UserTaskFacade ejbUserTaskFacade;
    private List<Excersice> exersices = new LinkedList<Excersice>();
    private List<TaskExercise> taskExercises = new LinkedList<TaskExercise>();
    private Excersice currentExcersice;
    private TaskExercise currentTaskExcersice;
    private Stimulus currentStimulus;
    private List<Answer> currentAnswers;
    private int position = 0;
    private boolean end = false;
    private Task task;
    private int selectedIdAnswers = -1;
    private List<TaskDone> taskDones = new LinkedList<TaskDone>();
    private boolean played=false;
    private Integer progress;

    /*
     *
     *
     * action="#{playExerciseIdentificationController.next(answer.idAnswer)}"
     *
     * <!--<h:form binding="#{playExerciseIdentificationController.init}"> <!--
     * <f:viewParam name="answer" value="#{answer.idAnswer}"/>--> </h:form> -->
     * <f:event type="preRenderView"
     * listener="#{playExerciseIdentificationController.init}" />
     *
     * <!-- <c:forEach
     * items="#{playExerciseIdentificationController.currentAnswers}"
     * var="answer"> <p:commandButton update="exersice"
     * actionListener="#{playExerciseIdentificationController.next(answer.idAnswer)}"
     * value="#{answer.valueName}&#10;#{answer.example}" style="white-space:
     * pre; width: 100%; height: 100%;" /> </c:forEach> -->
     *
     */
    public void init() {
        end = false;
        TaskControllerConverter taskControllerConverter = new TaskController.TaskControllerConverter();
        task = (Task) taskControllerConverter.getAsObject(FacesContext.getCurrentInstance(), null, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("task"));
        setTaskExercises(new LinkedList<TaskExercise>(task.getTaskExerciseCollection()));
        for (TaskExercise taskExercise : getTaskExercises()) {
            exersices.add(taskExercise.getIdExcersice());
        }
        //randomizamos
        Collections.shuffle(exersices);
        setCurrentExcersice(exersices.get(0));
        setCurrentTaskExcersice(taskExercises.get(0));
        ExerciseStimulusMap exerciseStimulusMap = exersices.get(0).getExerciseStimulusMapCollection().iterator().next();
        setCurrentStimulus(exerciseStimulusMap.getIdStimulus());
        List<PossibleSolution> possibleSolutions = new LinkedList<PossibleSolution>(exersices.get(0).getPossibleSolutionCollection());
        //Hack orden respuestas
        /*
         * List<Answer> answers = new LinkedList<Answer>();
         * for (PossibleSolution possibleSolution : possibleSolutions) {
         * answers.add(possibleSolution.getIdAnswer());
         *  }
         */
        answers=ejbAnswerFacade.findAll();
        //fin hack

        setCurrentAnswers(answers);
    }

    public ExerciseIdentificationController() {
    }

    public Excersice getCurrentExcersice() {
        if (currentExcersice == null) {
            init();
        }
        return currentExcersice;
    }

    public void setCurrentExcersice(Excersice currentExcersice) {
        this.currentExcersice = currentExcersice;
    }

    public List<Answer> getCurrentAnswers() {
        if (currentExcersice == null) {
            init();
        }
        return currentAnswers;
    }

    public void setCurrentAnswers(List<Answer> currentAnswers) {
        this.currentAnswers = currentAnswers;
    }

    public Stimulus getCurrentStimulus() {
        return currentStimulus;
    }

    public void setCurrentStimulus(Stimulus currentStimulus) {
        this.currentStimulus = currentStimulus;
    }

    public TaskExercise getCurrentTaskExcersice() {
        return currentTaskExcersice;
    }

    public void setCurrentTaskExcersice(TaskExercise currentTaskExcersice) {
        this.currentTaskExcersice = currentTaskExcersice;
    }

    public List<TaskExercise> getTaskExercises() {
        return taskExercises;
    }

    public void setTaskExercises(List<TaskExercise> taskExercises) {
        this.taskExercises = taskExercises;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public List<Excersice> getExersices() {
        return exersices;
    }

    public void setExersices(List<Excersice> exersices) {
        this.exersices = exersices;
    }

    public int getSelectedIdAnswers() {
        return selectedIdAnswers;
    }

    public void setSelectedIdAnswers(int selectedIdAnswers) {
        if (this.selectedIdAnswers == selectedIdAnswers) {
            this.selectedIdAnswers = -1;
        } else {
            this.selectedIdAnswers = selectedIdAnswers;
        }
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public Integer getProgress() {  
        int progress=0;
        progress = (position*100)/getExersices().size();  
        if(progress > 100) { 
            progress = 100;  
        }  
        return progress; 
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
    
    

    public void next() {
        setPlayed(false);
        TaskDone taskDoneActual = new TaskDone();
        AnswerController.AnswerControllerConverter answerControllerConverter = new AnswerController.AnswerControllerConverter();
        Answer answer = (Answer) answerControllerConverter.getAsObject(FacesContext.getCurrentInstance(), null, "" + getSelectedIdAnswers());
        taskDoneActual.setAnswer(answer);
        taskDoneActual.setExcersice(currentExcersice);
        taskDoneActual.setTaskExercise(getCurrentTaskExcersice());
        taskDones.add(taskDoneActual);
        for (PossibleSolution possibleSolution : currentExcersice.getPossibleSolutionCollection()) {
            if (possibleSolution.getCorrect() == 1 && possibleSolution.getIdAnswer().getIdAnswer() == getSelectedIdAnswers()) {
                FacesMessage msg = new FacesMessage("Correcto");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                break;
            } else {
                FacesMessage msg = new FacesMessage("Incorrecto");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                break;
            }
        }
        setSelectedIdAnswers(-1);
        if (exersices.size() > position + 1) {
            position++;
            setCurrentExcersice(exersices.get(position));
            setCurrentTaskExcersice(getTaskExercises().get(position));
            ExerciseStimulusMap exerciseStimulusMap = exersices.get(position).getExerciseStimulusMapCollection().iterator().next();
            setCurrentStimulus(exerciseStimulusMap.getIdStimulus());
            List<PossibleSolution> possibleSolutions = new LinkedList<PossibleSolution>(exersices.get(position).getPossibleSolutionCollection());
            
           
            //Hack orden respuestas
            /*
             * List<Answer> answers = new LinkedList<Answer>();
             * for (PossibleSolution possibleSolution : possibleSolutions) {
            * answers.add(possibleSolution.getIdAnswer());
            *  }
            */
            //answers.addAll(answers);
            //fin hack


            setCurrentAnswers(answers);
            if (exersices.size() < position) {
                end = true;
            }
        } else {
            try {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                User user = (User) session.getAttribute("user");
                for (TaskDone taskDone : taskDones) {
                    Result result = new Result();
                    result.setIdUser(user);
                    result.setIdTaskExercise(taskDone.getTaskExercise());
                    result.setIdAnswer(taskDone.getAnswer());
                    ejbResultFacade.create(result);
                }
                List<UserTask> userTasks = new LinkedList<UserTask>(user.getUserTaskCollection());
                for (UserTask userTask : userTasks) {
                    if (userTask.getIdTask().getIdTask() == task.getIdTask() && userTask.getComplete() == 0) {
                        userTask.setComplete(1);
                        ejbUserTaskFacade.edit(userTask);
                        break;
                    }
                }
                FacesContext.getCurrentInstance().getExternalContext().redirect("../public/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ExerciseIdentificationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    
    
    

    
    
    /*
     * Result result = new Result(); HttpSession session = (HttpSession)
     * FacesContext.getCurrentInstance().getExternalContext().getSession(true);
     * User user = (User) session.getAttribute("user"); result.setIdUser(user);
     * result.setIdTaskExercise(getCurrentTaskExcersice());
     * AnswerController.AnswerControllerConverter answerControllerConverter =
     * new AnswerController.AnswerControllerConverter(); Answer answer =
     * (Answer)
     * answerControllerConverter.getAsObject(FacesContext.getCurrentInstance(),
     * null, "" + id); result.setIdAnswer(answer);
     * ejbResultFacade.create(result); if (exersices.size() > position + 1) {
     * position++; setCurrentExcersice(exersices.get(position));
     * setCurrentTaskExcersice(getTaskExercises().get(position));
     * ExerciseStimulusMap exerciseStimulusMap =
     * exersices.get(position).getExerciseStimulusMapCollection().iterator().next();
     * setCurrentStimulus(exerciseStimulusMap.getIdStimulus());
     * List<PossibleSolution> possibleSolutions = new
     * LinkedList<PossibleSolution>(exersices.get(position).getPossibleSolutionCollection());
     * List<Answer> answers = new LinkedList<Answer>(); for (PossibleSolution
     * possibleSolution : possibleSolutions) {
     * answers.add(possibleSolution.getIdAnswer()); }
     * setCurrentAnswers(answers); if (exersices.size() < position) { end =
     * true; } } else { try { List<UserTask> userTasks = new
     * LinkedList<UserTask>(user.getUserTaskCollection()); for (UserTask
     * userTask : userTasks) { if (userTask.getIdTask().getIdTask() ==
     * task.getIdTask() && userTask.getComplete() == 0) {
     * userTask.setComplete(1); ejbUserTaskFacade.edit(userTask); break; } }
     * FacesContext.getCurrentInstance().getExternalContext().redirect("../public/index.xhtml");
     * } catch (IOException ex) {
     * Logger.getLogger(ExerciseIdentificationController.class.getName()).log(Level.SEVERE,
     * null, ex); } } return null;
     */

    private class TaskDone {

        private Excersice excersice;
        private Answer answer;
        private TaskExercise taskExercise;

        public Answer getAnswer() {
            return answer;
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public Excersice getExcersice() {
            return excersice;
        }

        public void setExcersice(Excersice excersice) {
            this.excersice = excersice;
        }

        public TaskExercise getTaskExercise() {
            return taskExercise;
        }

        public void setTaskExercise(TaskExercise taskExercise) {
            this.taskExercise = taskExercise;
        }
    }
}
