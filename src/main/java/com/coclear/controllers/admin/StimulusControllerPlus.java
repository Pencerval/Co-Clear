package com.coclear.controllers.admin;


import com.coclear.entitys.Stimulus;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "stimulusControllerPlus")
@ViewScoped
public class StimulusControllerPlus implements Serializable {

    
    @EJB
    private com.coclear.sessionbeans.StimulusFacade ejbStimulus;
    
    List<Stimulus> stimuluses;
    Stimulus selectedStimulus;
    
    public StimulusControllerPlus() {
    }
    
    public void delete(){
        System.out.println("HOLA");
    }

    public Stimulus getSelectedStimulus() {
        return selectedStimulus;
    }

    public void setSelectedStimulus(Stimulus selectedStimulus) {
        this.selectedStimulus = selectedStimulus;
    }

    public List<Stimulus> getStimuluses() {
        if(stimuluses==null){
            stimuluses=ejbStimulus.findAll();
        }
        return stimuluses;
    }

    public void setStimuluses(List<Stimulus> stimuluses) {
        this.stimuluses = stimuluses;
    }
    
    

  
}
