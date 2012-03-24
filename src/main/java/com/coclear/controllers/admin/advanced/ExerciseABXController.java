/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.entitys.Excersice;
import com.coclear.entitys.Stimulus;
import com.coclear.entitys.StimulusGroup;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "exerciseABXController")
@SessionScoped
public class ExerciseABXController implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private com.coclear.sessionbeans.ExcersiceFacade ejbExercise;
    @EJB
    private com.coclear.sessionbeans.StimulusGroupFacade ejbStimulusGroup;
    @EJB
    private com.coclear.sessionbeans.StimulusFacade ejbStimulus;
    
    private Excersice exersice=new Excersice();
    private StimulusGroup stimulusGroupA=new StimulusGroup();
    private StimulusGroup stimulusGroupB=new StimulusGroup();
    private StimulusGroup stimulusGroupX=new StimulusGroup();
    private Stimulus stimulusA=new Stimulus();
    private Stimulus stimulusB=new Stimulus();
    private Stimulus stimulusX=new Stimulus();
    
    private List<StimulusGroup> stimulusAGroupList;
    private List<StimulusGroup> stimulusBGroupList;
    private List<StimulusGroup> stimulusXGroupList;
    
    private List<Stimulus> stimulusAList;
    private List<Stimulus> stimulusBList;
    private List<Stimulus> stimulusXList;
    
    
    public ExerciseABXController() {
    }

    public void saveExercise(ActionEvent event) {
        try {
            ejbExercise.create(exersice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stimulusGroupASelectedChanged(){
        if(stimulusGroupA==null && ejbStimulusGroup.count()>0){
            stimulusGroupA=ejbStimulusGroup.findAll().get(0);
        }
        stimulusAList=(List<Stimulus>) stimulusGroupA.getStimulusCollection();
    }
    public void stimulusGroupBSelectedChanged(){
        if(stimulusGroupB==null && ejbStimulusGroup.count()>0){
            stimulusGroupB=ejbStimulusGroup.findAll().get(0);
        }
        stimulusBList=(List<Stimulus>) stimulusGroupB.getStimulusCollection();
    }
    public void stimulusGroupXSelectedChanged(){
        if(stimulusGroupX==null && ejbStimulusGroup.count()>0){
            stimulusGroupX=ejbStimulusGroup.findAll().get(0);
        }
        stimulusXList=(List<Stimulus>) stimulusGroupX.getStimulusCollection();
    }
    
    
    
     

    public Stimulus getStimulusA() {
        return stimulusA;
    }

    public void setStimulusA(Stimulus stimulusA) {
        this.stimulusA = stimulusA;
    }

    public Stimulus getStimulusB() {
        return stimulusB;
    }

    public void setStimulusB(Stimulus stimulusB) {
        this.stimulusB = stimulusB;
    }

    public StimulusGroup getStimulusGroupA() {
        return stimulusGroupA;
    }

    public void setStimulusGroupA(StimulusGroup stimulusGroupA) {
        this.stimulusGroupA = stimulusGroupA;
    }

    public StimulusGroup getStimulusGroupB() {
        return stimulusGroupB;
    }

    public void setStimulusGroupB(StimulusGroup stimulusGroupB) {
        this.stimulusGroupB = stimulusGroupB;
    }

    public StimulusGroup getStimulusGroupX() {
        return stimulusGroupX;
    }

    public void setStimulusGroupX(StimulusGroup stimulusGroupX) {
        this.stimulusGroupX = stimulusGroupX;
    }

    public Stimulus getStimulusX() {
        return stimulusX;
    }

    public void setStimulusX(Stimulus stimulusX) {
        this.stimulusX = stimulusX;
    }

    public List<StimulusGroup> getStimulusAGroupList() {
        stimulusAGroupList=ejbStimulusGroup.findAll();
        return stimulusAGroupList;
    }

    public void setStimulusAGroupList(List<StimulusGroup> stimulusAGroupList) {
        this.stimulusAGroupList = stimulusAGroupList;
    }

    public List<StimulusGroup> getStimulusBGroupList() {
        stimulusBGroupList=ejbStimulusGroup.findAll();
        return stimulusBGroupList;
    }

    public void setStimulusBGroupList(List<StimulusGroup> stimulusBGroupList) {
        this.stimulusBGroupList = stimulusBGroupList;
    }

    public List<StimulusGroup> getStimulusXGroupList() {
        stimulusXGroupList=ejbStimulusGroup.findAll();
        return stimulusXGroupList;
    }

    public void setStimulusXGroupList(List<StimulusGroup> stimulusXGroupList) {
        this.stimulusXGroupList = stimulusXGroupList;
    }

    public List<Stimulus> getStimulusAList() {
        if(stimulusGroupA.getStimulusCollection() == null ){
            stimulusGroupA=ejbStimulusGroup.findAll().get(0);
        }
        stimulusAList=(List<Stimulus>) stimulusGroupA.getStimulusCollection();
        return stimulusAList;
    }

    public void setStimulusAList(List<Stimulus> stimulusAList) {
        this.stimulusAList = stimulusAList;
    }

    public List<Stimulus> getStimulusBList() {
        if(stimulusGroupB.getStimulusCollection() == null ){
            stimulusGroupB=ejbStimulusGroup.findAll().get(0);
        }
        stimulusBList=(List<Stimulus>) stimulusGroupB.getStimulusCollection();
        return stimulusBList;
    }

    public void setStimulusBList(List<Stimulus> stimulusBList) {
        this.stimulusBList = stimulusBList;
    }

    public List<Stimulus> getStimulusXList() {
        if(stimulusGroupX.getStimulusCollection() == null ){
            stimulusGroupX=ejbStimulusGroup.findAll().get(0);
        }
        stimulusXList=(List<Stimulus>) stimulusGroupX.getStimulusCollection();
        return stimulusXList;
    }

    public void setStimulusXList(List<Stimulus> stimulusXList) {
        this.stimulusXList = stimulusXList;
    }

    public Excersice getExersice() {
        return exersice;
    }

    public void setExersice(Excersice exersice) {
        this.exersice = exersice;
    }
    
    
    
}
