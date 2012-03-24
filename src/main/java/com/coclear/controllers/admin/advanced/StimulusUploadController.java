/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.entitys.Stimulus;
import com.coclear.entitys.StimulusGroup;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
@ManagedBean(name = "stimulusUploadController")
@ViewScoped
public class StimulusUploadController implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private com.coclear.sessionbeans.StimulusGroupFacade ejbFacadeStimulusGroup;
    @EJB
    private com.coclear.sessionbeans.StimulusFacade ejbFacadeStimulus;
    private StimulusGroup stimulusGroupInput=new StimulusGroup();
    private StimulusGroup stimulusGroupSelected;
    private List<StimulusGroup> stimulusGroupList;

   
    public StimulusUploadController() {
    }

     public StimulusGroup getStimulusGroupInput() {
        return stimulusGroupInput;
    }

    public void setStimulusGroupInput(StimulusGroup stimulusGroupInput) {
        this.stimulusGroupInput = stimulusGroupInput;
    }

    public StimulusGroup getStimulusGroupSelected() {
        return stimulusGroupSelected;
    }

    public void setStimulusGroupSelected(StimulusGroup stimulusGroupSelected) {
        this.stimulusGroupSelected = stimulusGroupSelected;
    }
    
    public List<StimulusGroup> getStimulusGroupList() {
        stimulusGroupList=ejbFacadeStimulusGroup.findAll();
        return stimulusGroupList;
    }

    public void setStimulusGroupList(List<StimulusGroup> stimulusGroupList) {
        this.stimulusGroupList = stimulusGroupList;
    }
    
    public void saveStimulusGroup(ActionEvent event) {
        try {
            ejbFacadeStimulusGroup.create(stimulusGroupInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void uploadStimulus(FileUploadEvent event) {  
        if(stimulusGroupSelected==null && getStimulusGroupList().size()>0){
            stimulusGroupSelected=getStimulusGroupList().get(0);
        }
        ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
        Stimulus stimulus=new Stimulus(event.getFile().getFileName(), "audio",stimulusGroupSelected);
        ejbFacadeStimulus.create(stimulus);
        File file=new File(context.getInitParameter("stimulusstore")+"audio"+File.separator+stimulus.getIdStimulus()+"."+FilenameUtils.getExtension(event.getFile().getFileName()));
        try {
            FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(event.getFile().getInputstream()));
        } catch (IOException ex) {
            Logger.getLogger(StimulusUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesMessage msg = new FacesMessage("Succesful", "Archivo "+event.getFile().getFileName() + " subido correctamente.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    } 
    
    public void stimulusGroupSelectedChanged(){
    }
}
