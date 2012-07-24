package com.coclear.controllers.exercises;

import com.coclear.controllers.admin.UserTaskController;
import com.coclear.entitys.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "playExerciseDiscriminationController")
@ViewScoped
public class ExerciseDiscriminationController implements Serializable {

    private static final long serialVersionUID = 1L;
    //Necesario para el hack
    @EJB
    private com.coclear.sessionbeans.AnswerFacade answerFacade;
    List<Answer> answers = new LinkedList<Answer>();
    //Fin necesario hack
    @EJB
    private com.coclear.sessionbeans.ResultFacade ejbResultFacade;
    @EJB
    private com.coclear.sessionbeans.UserTaskFacade ejbUserTaskFacade;
    private List<Exercise> exercises = new LinkedList<Exercise>();
    private List<TaskExercise> taskExercises = new LinkedList<TaskExercise>();
    private Exercise currentExercise;
    private TaskExercise currentTaskExercise;
    private Stimulus currentStimulusSound1;
    private Stimulus currentStimulusSound2;
    private Stimulus currentStimulusImage1;
    private Stimulus currentStimulusImage2;
    private int position = 0;
    private boolean end = false;
    private Task task;
    private UserTask userTask;
    private int selectedIdStimulus = -1;
    private List<TaskDone> taskDones = new LinkedList<TaskDone>();
    private boolean played = true;
    private Integer progress;
    private boolean disabled = true;
    private Boolean correct = null;

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
        UserTaskController.UserTaskControllerConverter userTaskControllerConverter = new UserTaskController.UserTaskControllerConverter();
        userTask = (UserTask) userTaskControllerConverter.getAsObject(FacesContext.getCurrentInstance(), null, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("task"));
        task = userTask.getTask();
        //Randomizamos
        setTaskExercises(new LinkedList<TaskExercise>(task.getTaskExerciseList()));
        Collections.shuffle(taskExercises);
        for (TaskExercise taskExercise : getTaskExercises()) {
            exercises.add(taskExercise.getExercise());
        }
        setCurrentExercise(exercises.get(0));
        setCurrentTaskExercise(taskExercises.get(0));

        setCurrentStimulusSound1(exercises.get(0).getExerciseStimulusMapList().get(0).getStimulus());
        setCurrentStimulusSound2(exercises.get(0).getExerciseStimulusMapList().get(1).getStimulus());
        setCurrentStimulusImage1(exercises.get(0).getExerciseStimulusMapList().get(2).getStimulus());
        setCurrentStimulusImage2(exercises.get(0).getExerciseStimulusMapList().get(3).getStimulus());

    }

    public ExerciseDiscriminationController() {
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Exercise getCurrentExercise() {
        if (currentExercise == null) {
            init();
        }
        return currentExercise;
    }

    public void setCurrentExercise(Exercise currentExercise) {
        this.currentExercise = currentExercise;
    }

    public Stimulus getCurrentStimulusImage1() {
        return currentStimulusImage1;
    }

    public void setCurrentStimulusImage1(Stimulus currentStimulusImage1) {
        this.currentStimulusImage1 = currentStimulusImage1;
    }

    public Stimulus getCurrentStimulusImage2() {
        return currentStimulusImage2;
    }

    public void setCurrentStimulusImage2(Stimulus currentStimulusImage2) {
        this.currentStimulusImage2 = currentStimulusImage2;
    }

    public Stimulus getCurrentStimulusSound1() {
        if (currentStimulusSound1 == null) {
            init();
        }
        return currentStimulusSound1;
    }

    public void setCurrentStimulusSound1(Stimulus currentStimulusSound1) {
        this.currentStimulusSound1 = currentStimulusSound1;
    }

    public Stimulus getCurrentStimulusSound2() {
        return currentStimulusSound2;
    }

    public void setCurrentStimulusSound2(Stimulus currentStimulusSound2) {
        this.currentStimulusSound2 = currentStimulusSound2;
    }

    public TaskExercise getCurrentTaskExercise() {
        return currentTaskExercise;
    }

    public void setCurrentTaskExercise(TaskExercise currentTaskExercise) {
        this.currentTaskExercise = currentTaskExercise;
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

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getSelectedIdStimulus() {
        return selectedIdStimulus;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
    
    

    public void setSelectedIdStimulus(int selectedIdAnswers) {
        if(isPlayed()){
            this.selectedIdStimulus = selectedIdAnswers;
            TaskDone taskDoneActual = new TaskDone();
            Answer answer;
            if(getSelectedIdStimulus()==currentExercise.getExerciseStimulusMapList().get(2).getStimulus().getIdStimulus()){
                answer=answerFacade.getSameAnswer();
            }else{
                answer=answerFacade.getDifferentAnswer();
            }
            taskDoneActual.setAnswer(answer);
            taskDoneActual.setExercise(currentExercise);
            taskDoneActual.setTaskExercise(getCurrentTaskExercise());
            taskDones.add(taskDoneActual);
            for (PossibleSolution possibleSolution : currentExercise.getPossibleSolutionList()) {
                if (possibleSolution.getCorrect()){
                    if(possibleSolution.getAnswer().getIdAnswer() == answerFacade.getSameAnswer().getIdAnswer() && getSelectedIdStimulus() == currentExercise.getExerciseStimulusMapList().get(2).getStimulus().getIdStimulus() || possibleSolution.getAnswer().getIdAnswer() == answerFacade.getDifferentAnswer().getIdAnswer() && getSelectedIdStimulus() == currentExercise.getExerciseStimulusMapList().get(3).getStimulus().getIdStimulus()){
                        //FacesMessage msg = new FacesMessage("Respuesta", "Correcta");
                        //FacesContext.getCurrentInstance().addMessage(null, msg);
                        setCorrect(true);
                        break;
                    }else{
                        //FacesMessage msg = new FacesMessage("Respuesta", "Incorrecta");
                        //FacesContext.getCurrentInstance().addMessage(null, msg);
                        setCorrect(false);
                        break;
                    }
                }
            }
            setPlayed(false);
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
        int internalProgress;
        internalProgress = (position * 100) / getExercises().size();
        if (internalProgress > 100) {
            internalProgress = 100;
        }
        return internalProgress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String doNext() {
        this.selectedIdStimulus = -1;
        if (exercises.size() > position + 1) {
            position++;
            setCurrentExercise(exercises.get(position));
            setCurrentTaskExercise(getTaskExercises().get(position));
            setCurrentStimulusSound1(exercises.get(position).getExerciseStimulusMapList().get(0).getStimulus());
            setCurrentStimulusSound2(exercises.get(position).getExerciseStimulusMapList().get(1).getStimulus());
            setCurrentStimulusImage1(exercises.get(position).getExerciseStimulusMapList().get(2).getStimulus());
            setCurrentStimulusImage2(exercises.get(position).getExerciseStimulusMapList().get(3).getStimulus());

            if (exercises.size() < position) {
                end = true;
            }
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("form1");
            setCorrect(null);
            setPlayed(true);
        } else {
            try {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                User user = (User) session.getAttribute("user");
                for (TaskDone taskDone : taskDones) {
                    Result result = new Result();
                    result.setUserTask(userTask);
                    result.setTaskExercise(taskDone.getTaskExercise());
                    result.setAnswer(taskDone.getAnswer());
                    ejbResultFacade.create(result);
                }
                List<UserTask> userTasks = new LinkedList<UserTask>(user.getUserTaskList());
                for (UserTask userTask : userTasks) {
                    if (userTask.getTask().getIdTask() == task.getIdTask() && !userTask.getComplete()) {
                        userTask.setComplete(true);
                        ejbUserTaskFacade.edit(userTask);
                        break;
                    }
                }
                FacesContext.getCurrentInstance().getExternalContext().redirect("../public/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ExerciseDiscriminationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;

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

        private Exercise exercise;
        private Answer answer;
        private TaskExercise taskExercise;

        public Answer getAnswer() {
            return answer;
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public Exercise getExercise() {
            return exercise;
        }

        public void setExercise(Exercise exercise) {
            this.exercise = exercise;
        }

        public TaskExercise getTaskExercise() {
            return taskExercise;
        }

        public void setTaskExercise(TaskExercise taskExercise) {
            this.taskExercise = taskExercise;
        }
    }
}
