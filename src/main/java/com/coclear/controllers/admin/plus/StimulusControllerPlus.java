/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.plus;

import com.coclear.entitys.Stimulus;
import com.coclear.entitys.StimulusGroup;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "stimulusGroupControllerPlus")
@ViewScoped
public class StimulusControllerPlus {

    private static final long serialVersionUID = 1L;
    @EJB
    private com.coclear.sessionbeans.StimulusFacade stimulusFacade;
    @EJB
    private com.coclear.sessionbeans.StimulusGroupFacade stimulusGroupFacade;
    private List<StimulusGroup> stimulusGroups = null;
    private List<Stimulus> stimulusList=null;
    private StimulusGroup stimulusGroup;
    
    public StimulusControllerPlus() {
    }

    public List<StimulusGroup> getStimulusGroups() {
        if(stimulusGroups==null){
            stimulusGroups=stimulusGroupFacade.findAll();
        }
        return stimulusGroups;
    }

    public void setStimulusGroups(List<StimulusGroup> stimulusGroups) {
        this.stimulusGroups = stimulusGroups;
    }

    public List<Stimulus> getStimulusList() {
        if(stimulusList==null){
            stimulusList = stimulusFacade.findAll();
        }
        return stimulusList;
    }

    public void setStimulusList(List<Stimulus> stimulusList) {
        this.stimulusList = stimulusList; 
    }

    public void deleteStimulus(Stimulus stimulus) {
        stimulusFacade.remove(stimulus);
        setStimulusList(stimulusFacade.findAll());
    }
}
