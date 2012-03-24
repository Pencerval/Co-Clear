/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.entitys.Answer;
import com.coclear.entitys.Excersice;
import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.Stimulus;
import com.coclear.entitys.StimulusGroup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@ManagedBean(name = "exerciseIdentificationController")
@SessionScoped
public class ExerciseIdentificationController implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private com.coclear.sessionbeans.ExcersiceFacade ejbExercise;
    @EJB
    private com.coclear.sessionbeans.StimulusGroupFacade ejbStimulusGroup;
    @EJB
    private com.coclear.sessionbeans.StimulusFacade ejbStimulus;
    @EJB
    private com.coclear.sessionbeans.AnswerFacade ejbAnswers;
    @EJB
    private com.coclear.sessionbeans.ExerciseStimulusMapFacade ejbExerciseStimulusMap;
    @EJB
    private com.coclear.sessionbeans.PossibleSolutionFacade ejbPosiPossibleSolutionFacade;
    
    
    private Excersice exersice=new Excersice();
    private StimulusGroup stimulusGroup=new StimulusGroup();
    private Stimulus stimulus=new Stimulus();
    
    private List<StimulusGroup> stimulusGroupList;
    
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

    public void saveExercise(ActionEvent event) {
        try {
            exersice.setExerciseStimulusMapCollection(null);
            exersice.setPossibleSolutionCollection(null);
            ejbExercise.create(exersice);
            ExerciseStimulusMap exerciseStimulusMap=new ExerciseStimulusMap();
            exerciseStimulusMap.setIdStimulus(stimulus);
            exerciseStimulusMap.setIdExcersice(exersice);
            ejbExerciseStimulusMap.create(exerciseStimulusMap);
            List<ExerciseStimulusMap> exerciseStimulusMaps=new ArrayList<ExerciseStimulusMap>();
            exerciseStimulusMaps.add(exerciseStimulusMap);
            List<PossibleSolution> possibleSolutions=new ArrayList<PossibleSolution>();
            PossibleSolution possibleSolution=new PossibleSolution();
            possibleSolution.setCorrect(1);
            possibleSolution.setIdExcersice(exersice);
            possibleSolution.setIdAnswer(correctAnswer);
            ejbPosiPossibleSolutionFacade.create(possibleSolution);
            possibleSolutions.add(possibleSolution);
            for(Answer answer:answers.getTarget()){
                if(answer.getIdAnswer()!=correctAnswer.getIdAnswer()){
                    PossibleSolution possibleSolutionFail=new PossibleSolution();
                    possibleSolutionFail.setCorrect(0);
                    possibleSolutionFail.setIdExcersice(exersice);
                    possibleSolutionFail.setIdAnswer(answer);
                    ejbPosiPossibleSolutionFacade.create(possibleSolutionFail);
                    possibleSolutions.add(possibleSolutionFail);
                }
            }

            exersice.setExerciseStimulusMapCollection(exerciseStimulusMaps);
            exersice.setPossibleSolutionCollection(possibleSolutions);
            ejbExercise.edit(exersice);
            FacesMessage msg = new FacesMessage("Succesful", "Ejercicio "+exersice.getName()+" creado correctamente");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stimulusGroupSelectedChanged(){
        if(stimulusGroup==null && ejbStimulusGroup.count()>0){
            stimulusGroup=ejbStimulusGroup.findAll().get(0);
        }
        stimulusList=(List<Stimulus>) stimulusGroup.getStimulusCollection();
    }
    
    public Stimulus getStimulus() {
        return stimulus;
    }

    public void setStimulus(Stimulus stimulus) {
        this.stimulus = stimulus;
    }

    

    public StimulusGroup getStimulusGroup() {
        return stimulusGroup;
    }

    public void setStimulusGroup(StimulusGroup stimulusGroup) {
        this.stimulusGroup = stimulusGroup;
    }



    public List<StimulusGroup> getStimulusGroupList() {
        stimulusGroupList=ejbStimulusGroup.findAll();
        return stimulusGroupList;
    }

    public void setStimulusGroupList(List<StimulusGroup> stimulusGroupList) {
        this.stimulusGroupList = stimulusGroupList;
    }

   

    public List<Stimulus> getStimulusList() {
        if(stimulusGroup==null || stimulusGroup.getName()==null || stimulusGroup.getName()=="" || stimulusGroup.getName().isEmpty()){
            stimulusGroup=ejbStimulusGroup.findAll().get(0);
        }
        stimulusList=(List<Stimulus>) stimulusGroup.getStimulusCollection();
        return stimulusList;
    }

    public void setStimulusList(List<Stimulus> stimulusList) {
        this.stimulusList = stimulusList;
    }

   

    public Excersice getExersice() {
        return exersice;
    }

    public void setExersice(Excersice exersice) {
        this.exersice = exersice;
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
