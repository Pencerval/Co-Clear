package com.coclear.controllers.exercises;

import com.coclear.controllers.admin.UserTaskController;
import com.coclear.entitys.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "playExerciseMemoController")
@ViewScoped
public class ExerciseMemoController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private com.coclear.sessionbeans.UserTaskFacade ejbUserTaskFacade;
    private Exercise exercise;
    private TaskExercise taskExercise;
    private List<Card> cardList;
    private Task task;
    private UserTask userTask;
    private Card cardVisible = null;
    boolean allPaired = false;
        

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
        UserTaskController.UserTaskControllerConverter userTaskControllerConverter = new UserTaskController.UserTaskControllerConverter();
        userTask = (UserTask) userTaskControllerConverter.getAsObject(FacesContext.getCurrentInstance(), null, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("task"));
        task = userTask.getTask();
        //Randomizamos
        setTaskExercise(task.getTaskExerciseList().iterator().next());
        setExercise(getTaskExercise().getExercise());
        for (ExerciseStimulusMap exerciseStimulusMap : getExercise().getExerciseStimulusMapList()) {
            getCardList().add(new Card(false, exerciseStimulusMap.getStimulus().getIdStimulus()));
            getCardList().add(new Card(true, exerciseStimulusMap.getStimulus().getIdStimulus()));
        }
        Collections.shuffle(getCardList());
    }

    public ExerciseMemoController() {
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Card> getCardList() {
        if (cardList == null) {
            cardList = new LinkedList<Card>();
            init();
        }
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
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

    public UserTask getUserTask() {
        return userTask;
    }

    public void setUserTask(UserTask userTask) {
        this.userTask = userTask;
    }

    public Card getCardVisible() {
        return cardVisible;
    }

    public void setCardVisible(Card cardVisible) {
        this.cardVisible = cardVisible;
    }

    public void cardClicked(String cardId) {
        int timeout=0;
        RequestContext context = RequestContext.getCurrentInstance();
        boolean paired = false;
        for (Card card : cardList) {
            if (card.getId().equals(cardId)) {
                card.setVisible(!card.isVisible());
                if (card.isVisible()) {
                    if (card.isVisible() && getCardVisible() != null) {
                        if (card.getStimulusId() == getCardVisible().getStimulusId()) {
                            card.setPaired(true);
                            getCardVisible().setPaired(true);
                            setCardVisible(null);
                            paired = true;
                        } else {
                            timeout=1000;
                            card.setVisible(false);
                            getCardVisible().setVisible(false);
                            context.execute("setTimeout(function(){$('#card"+card.getId()+"container').rotate3Di('toggle', 1000,{sideChange: function(front){$('#card"+card.getId()+"container button').css('background-image', 'url("+FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/resources/images/background.jpg)')}});},"+timeout+")");
                            context.execute("setTimeout(function(){$('#card"+getCardVisible().getId()+"container').rotate3Di('toggle', 1000,{sideChange: function(front){$('#card"+getCardVisible().getId()+"container button').css('background-image', 'url("+FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/resources/images/background.jpg)')}});},"+timeout+")");
                            setCardVisible(null);
                            timeout=timeout+1000;
                        }
                    } else {
                        card.setVisible(true);
                        setCardVisible(card);
                    }
                } else {
                    setCardVisible(null);
                }
            }
        }
        if (paired) {
            boolean isAllPaired=true;
            for (Card card : cardList) {
                if (!card.isPaired()) {
                    isAllPaired=false;
                    break;
                }
            }
            if(isAllPaired){
                allPaired=true;
            }
        }
        context.execute("setTimeout(function(){updateForm()},"+timeout+");");
     }

    public void done() {
        if (allPaired) {
            getUserTask().setComplete(true);
            ejbUserTaskFacade.edit(getUserTask());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("completedialog.show()");
        }
    }

    public class Card {

        private String id;
        private boolean copy;
        private int stimulusId;
        private boolean paired;
        private boolean visible;

        public Card(boolean copy, int stimulusId) {
            this.id = stimulusId + "" + copy;
            this.copy = copy;
            this.stimulusId = stimulusId;
            this.paired = false;
            this.visible = false;

        }

        public boolean isCopy() {
            return copy;
        }

        public void setCopy(boolean copy) {
            this.copy = copy;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStimulusId() {
            return stimulusId;
        }

        public void setStimulus(int stimulusId) {
            this.stimulusId = stimulusId;
        }

        public boolean isPaired() {
            return paired;
        }

        public void setPaired(boolean paired) {
            this.paired = paired;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }
    }
}
