/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.entitys.Answer;
import com.coclear.entitys.AnswerGroup;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "answersController")
@SessionScoped
public class AnswersController implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private com.coclear.sessionbeans.AnswerGroupFacade ejbFacadeAnswerGroup;
    @EJB
    private com.coclear.sessionbeans.AnswerFacade ejbFacadeAnswer;
    private AnswerGroup answerGroupInput=new AnswerGroup();
    private AnswerGroup answerGroupSelected;
    private Answer answer=new Answer();

    
    private List<AnswerGroup> answerGroupList;

   
    public AnswersController() {
    }

     public AnswerGroup getAnswerGroupInput() {
        return answerGroupInput;
    }

    public void setStimulusGroupInput(AnswerGroup answerGroupInput) {
        this.answerGroupInput = answerGroupInput;
    }

    public AnswerGroup getAnswerGroupSelected() {
        return answerGroupSelected;
    }

    public void setAnswerGroupSelected(AnswerGroup answerGroupSelected) {
        this.answerGroupSelected = answerGroupSelected;
    }
    
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
    
    public List<AnswerGroup> getAnswerGroupList() {
        answerGroupList=ejbFacadeAnswerGroup.findAll();
        return answerGroupList;
    }

    public void setAnswerGroupList(List<AnswerGroup> answerGroupList) {
        this.answerGroupList = answerGroupList;
    }
    
    public void saveAnswerGroup(ActionEvent event) {
        try {
            answerGroupSelected=answerGroupInput;
            ejbFacadeAnswerGroup.create(answerGroupInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveAnswer(ActionEvent event) {
        try {
            if(answerGroupSelected==null && getAnswerGroupList().size()>0){
                answerGroupSelected=getAnswerGroupList().get(0);
            }
            answer.setAnswerGroup(answerGroupSelected);
            ejbFacadeAnswer.create(answer);
            FacesMessage msg = new FacesMessage("Succesful", "Respuesta "+answer.getName()+ " añadida al grupo "+answerGroupSelected.getName()+" correctamente.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void answerSelectedChanged(){
        
    }
}
