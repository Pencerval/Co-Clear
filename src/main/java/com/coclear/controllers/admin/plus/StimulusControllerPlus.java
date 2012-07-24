/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.plus;

import com.coclear.entitys.Stimulus;
import com.coclear.entitys.StimulusGroup;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

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
    private List<Stimulus> stimulusList = null;
    private StimulusGroup stimulusGroupSelected;
    private StimulusGroup stimulusGroupCreate;
    //private Stimulus stimulusCreate;

    public StimulusControllerPlus() {
    }

    public StimulusGroup getStimulusGroupSelected() {
        return stimulusGroupSelected;
    }

    public void setStimulusGroupSelected(StimulusGroup stimulusGroupSelected) {
        this.stimulusGroupSelected = stimulusGroupSelected;
        if (this.stimulusGroupSelected != null) {
            setStimulusList(stimulusGroupSelected.getStimulusList());
        }
    }

    public List<StimulusGroup> getStimulusGroups() {
        if (stimulusGroups == null) {
            stimulusGroups = stimulusGroupFacade.findAll();
        }
        return stimulusGroups;
    }

    public void setStimulusGroups(List<StimulusGroup> stimulusGroups) {
        this.stimulusGroups = stimulusGroups;
    }

    public List<Stimulus> getStimulusList() {
        return stimulusList;
    }

    public void setStimulusList(List<Stimulus> stimulusList) {
        this.stimulusList = stimulusList;
    }

    public StimulusGroup getStimulusGroupCreate() {
        if (stimulusGroupCreate == null) {
            stimulusGroupCreate = new StimulusGroup();
        }
        return stimulusGroupCreate;
    }

    public void setStimulusGroupCreate(StimulusGroup stimulusGroupCreate) {
        this.stimulusGroupCreate = stimulusGroupCreate;
    }

    public void editGroup(StimulusGroup stimulusGroup) {
        stimulusGroupFacade.edit(stimulusGroup);
        setStimulusGroups(stimulusGroupFacade.findAll());
    }

    public void deleteGroup(StimulusGroup stimulusGroup) {
        stimulusGroupFacade.remove(stimulusGroup);
        setStimulusGroups(stimulusGroupFacade.findAll());
        stimulusList=null;
        stimulusGroupSelected=null;
    }

    public void deleteStimulus(Stimulus stimulus) {
        stimulusFacade.remove(stimulus);
        stimulusGroupSelected=stimulusGroupFacade.find(stimulusGroupSelected.getIdStimulusGroup());
        setStimulusGroupSelected(stimulusGroupSelected);
        setStimulusGroups(stimulusGroupFacade.findAll());
        
    }

    public void createGroup(ActionEvent actionEvent) {
        stimulusGroupFacade.create(getStimulusGroupCreate());
        setStimulusGroups(stimulusGroupFacade.findAll());
        setStimulusGroupSelected(getStimulusGroupCreate());
    }
    
    public void uploadStimulus(FileUploadEvent event) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Stimulus stimulus=null;
        File file=null;
        if (FilenameUtils.getExtension(event.getFile().getFileName()).toLowerCase().equals("png")) {
            stimulus = new Stimulus(event.getFile().getFileName(), "image", stimulusGroupSelected);
            stimulusFacade.create(stimulus);
            file = new File(context.getInitParameter("stimulusstore") + "image" + File.separator + stimulus.getIdStimulus() + "." + FilenameUtils.getExtension(event.getFile().getFileName()));
        } else {
            stimulus = new Stimulus(event.getFile().getFileName(), "audio", stimulusGroupSelected);
            stimulusFacade.create(stimulus);
            file = new File(context.getInitParameter("stimulusstore") + "audio" + File.separator + stimulus.getIdStimulus() + "." + FilenameUtils.getExtension(event.getFile().getFileName()));
        }
        try {
            FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(event.getFile().getInputstream()));
            FacesMessage msg = new FacesMessage("Succesful", "Archivo " + event.getFile().getFileName() + " subido correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException ex) {
            Logger.getLogger(StimulusControllerPlus.class.getName()).log(Level.WARNING, null, ex);
            FacesMessage msg = new FacesMessage("Error", "Archivo " + event.getFile().getFileName() + " subido incorrectamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        stimulusGroupSelected=stimulusGroupFacade.find(stimulusGroupSelected.getIdStimulusGroup());
        setStimulusGroupSelected(stimulusGroupSelected);
        setStimulusGroups(stimulusGroupFacade.findAll());
        //setStimulusList(stimulusGroupSelected.getStimulusList());
        
    }
}
