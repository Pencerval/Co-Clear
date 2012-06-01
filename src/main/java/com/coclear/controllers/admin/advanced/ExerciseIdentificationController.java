/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * <!-- <h:panelGrid columns="2" > 
                            <h:outputLabel value="Grupo del estimulo: " />
                            <p:selectOneMenu  value="#{exerciseIdentificationController.stimulusGroup}" effect="fade">  
                                <p:ajax listener="#{exerciseIdentificationController.stimulusGroupSelectedChanged}" update="exercise"/>
                                <f:selectItems value="#{exerciseIdentificationController.stimulusGroupList}" var="stimulusGroup" itemLabel="#{stimulusGroup.name}" itemValue="#{stimulusGroup}"/>  
                            </p:selectOneMenu>  
                            <h:outputLabel value="Estimulo: " />  
                            <p:selectOneMenu id="exercise" value="#{exerciseIdentificationController.stimulus}" effect="fade" var="stimulus">  
                                <f:selectItems value="#{exerciseIdentificationController.stimulusList}" var="stimulus" itemLabel="#{stimulus.name}" itemValue="#{stimulus}"/>  
                            --><!--<p:column>  
                                <p:graphicImage value="/resources/images/button-play.png" width="20" height="20" onclick="document.getElementById('sound').play();"/>
                            </p:column>  -->
                            <!--<p:column>  
                            #{stimulus.name}  
                        </p:column>  
                    </p:selectOneMenu>  
                            
                            <p:selectOneListbox id="exercise" value="#{exerciseIdentificationController.stimulus}" style="height:100px">  
                                <f:selectItems value="#{exerciseIdentificationController.stimulusList}" var="stimulus" itemLabel="#{stimulus.name}" itemValue="#{stimulus}" />
                            </p:selectOneListbox>  
                        </h:panelGrid>
                            -->
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.entitys.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "exerciseIdentificationController")
@ViewScoped
public class ExerciseIdentificationController implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private com.coclear.sessionbeans.ExerciseFacade ejbExercise;
    @EJB
    private com.coclear.sessionbeans.StimulusFacade ejbStimulus;
    @EJB
    private com.coclear.sessionbeans.AnswerFacade ejbAnswers;
    @EJB
    private com.coclear.sessionbeans.ExerciseStimulusMapFacade ejbExerciseStimulusMap;
    @EJB
    private com.coclear.sessionbeans.PossibleSolutionFacade ejbPosiPossibleSolutionFacade;
    
    
    private Exercise exercise=new Exercise();
    private Stimulus stimulus=new Stimulus();
    
    private List<Stimulus> stimulusList;
    
    private DualListModel<Answer> answers;
    
    private List<Answer> source;
    
    private List<Answer> target = new ArrayList<Answer>();
    
    private List<Answer> correctAnswerList;
    
    private Answer correctAnswer;

    public Answer getCorrectAnswer() {
        if(correctAnswer==null){
            correctAnswer=ejbAnswers.findAll().get(0);
        }
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Answer> getCorrectAnswerList() {
        if(correctAnswerList==null){
            correctAnswerList=ejbAnswers.findAll();
        }
        return correctAnswerList;
    }

    public void setCorrectAnswerList(List<Answer> correctAnswerList) {
        this.correctAnswerList = correctAnswerList;
    }
    
    

    public List<Answer> getSource() {
        if(source==null){
            source=ejbAnswers.findAll();
        }
        return source;
    }

    public void setSource(List<Answer> source) {
        this.source = source;
    }

    public List<Answer> getTarget() {
        return target;
    }

    public void setTarget(List<Answer> target) {
        this.target = target;
    }

		
    public DualListModel<Answer> getAnswers() {
        if(answers==null){
           answers=new DualListModel<Answer>(getSource(),getTarget());
        }
        return answers;
    }

    public void setAnswers(DualListModel<Answer> answers) {
        this.answers = answers;
    }
    
    
    public ExerciseIdentificationController() {
    }

    public void saveExercise() {
        try {
            if(stimulus==null){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Necesita seleccionar un estimulo de la tabla.");  
                FacesContext.getCurrentInstance().addMessage(null, msg); 
                return;
            }
            exercise.setExerciseStimulusMapList(null);
            exercise.setPossibleSolutionList(null);
            if(exercise.getName()==null || exercise.getName().isEmpty()){
                exercise.setName(getStimulus().getName()+"-"+getCorrectAnswer().getName());
            }
            ejbExercise.create(exercise);
            ExerciseStimulusMap exerciseStimulusMap=new ExerciseStimulusMap();
            exerciseStimulusMap.setStimulus(stimulus);
            exerciseStimulusMap.setExercise(exercise);
            ejbExerciseStimulusMap.create(exerciseStimulusMap);
            List<ExerciseStimulusMap> exerciseStimulusMaps=new ArrayList<ExerciseStimulusMap>();
            exerciseStimulusMaps.add(exerciseStimulusMap);
            List<PossibleSolution> possibleSolutions=new ArrayList<PossibleSolution>();
            PossibleSolution possibleSolution=new PossibleSolution();
            possibleSolution.setCorrect(true);
            possibleSolution.setExercise(exercise);
            possibleSolution.setAnswer(correctAnswer);
            possibleSolution.setAnswerOrder(0);
            ejbPosiPossibleSolutionFacade.create(possibleSolution);
            possibleSolutions.add(possibleSolution);
            for(Answer answer:answers.getTarget()){
                if(answer.getIdAnswer()!=correctAnswer.getIdAnswer()){
                    PossibleSolution possibleSolutionFail=new PossibleSolution();
                    possibleSolutionFail.setCorrect(false);
                    possibleSolutionFail.setExercise(exercise);
                    possibleSolutionFail.setAnswer(answer);
                    possibleSolutionFail.setAnswerOrder(0);
                    ejbPosiPossibleSolutionFacade.create(possibleSolutionFail);
                    possibleSolutions.add(possibleSolutionFail);
                }
            }
            exercise.setExerciseStimulusMapList(exerciseStimulusMaps);
            exercise.setPossibleSolutionList(possibleSolutions);
            ejbExercise.edit(exercise);
            FacesMessage msg = new FacesMessage("Succesful", "Ejercicio "+exercise.getName()+" creado correctamente");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
            exercise=new Exercise();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Stimulus getStimulus() {
        return stimulus;
    }

    public void setStimulus(Stimulus stimulus) {
        this.stimulus = stimulus;
    }
  

    public List<Stimulus> getStimulusList() {
        if(stimulusList==null){
            stimulusList=ejbStimulus.findAll();
        }
        return stimulusList;
    }

    public void setStimulusList(List<Stimulus> stimulusList) {
        this.stimulusList = stimulusList;
    }

   

    public Exercise getExercise() {
        return exercise;
    }

    public void setExersice(Exercise exercise) {
        this.exercise = exercise;
    }
    
    
    @FacesConverter( value="answerConverter")
    public static class AnswerControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ExerciseIdentificationController controller = (ExerciseIdentificationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "exerciseIdentificationController");
            return controller.ejbAnswers.find(getKey(value));
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
            if (object instanceof Answer) {
                Answer o = (Answer) object;
                return getStringKey(o.getIdAnswer());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AnswersController.class.getName());
            }
        }
    }
}
