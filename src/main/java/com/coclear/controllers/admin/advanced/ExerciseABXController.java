/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import com.coclear.entitys.Exercise;
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
    private com.coclear.sessionbeans.ExerciseFacade ejbExercise;
    @EJB
    private com.coclear.sessionbeans.StimulusGroupFacade ejbStimulusGroup;
    @EJB
    private com.coclear.sessionbeans.StimulusFacade ejbStimulus;
    
    private Exercise exercise=new Exercise();
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
            ejbExercise.create(exercise);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stimulusGroupASelectedChanged(){
        if(stimulusGroupA==null && ejbStimulusGroup.count()>0){
            stimulusGroupA=ejbStimulusGroup.findAll().get(0);
        }
        stimulusAList=(List<Stimulus>) stimulusGroupA.getStimulusList();
    }
    public void stimulusGroupBSelectedChanged(){
        if(stimulusGroupB==null && ejbStimulusGroup.count()>0){
            stimulusGroupB=ejbStimulusGroup.findAll().get(0);
        }
        stimulusBList=(List<Stimulus>) stimulusGroupB.getStimulusList();
    }
    public void stimulusGroupXSelectedChanged(){
        if(stimulusGroupX==null && ejbStimulusGroup.count()>0){
            stimulusGroupX=ejbStimulusGroup.findAll().get(0);
        }
        stimulusXList=(List<Stimulus>) stimulusGroupX.getStimulusList();
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
        if(stimulusGroupA.getStimulusList() == null ){
            stimulusGroupA=ejbStimulusGroup.findAll().get(0);
        }
        stimulusAList=stimulusGroupA.getStimulusList();
        return stimulusAList;
    }

    public void setStimulusAList(List<Stimulus> stimulusAList) {
        this.stimulusAList = stimulusAList;
    }

    public List<Stimulus> getStimulusBList() {
        if(stimulusGroupB.getStimulusList() == null ){
            stimulusGroupB=ejbStimulusGroup.findAll().get(0);
        }
        stimulusBList=(List<Stimulus>) stimulusGroupB.getStimulusList();
        return stimulusBList;
    }

    public void setStimulusBList(List<Stimulus> stimulusBList) {
        this.stimulusBList = stimulusBList;
    }

    public List<Stimulus> getStimulusXList() {
        if(stimulusGroupX.getStimulusList() == null ){
            stimulusGroupX=ejbStimulusGroup.findAll().get(0);
        }
        stimulusXList=(List<Stimulus>) stimulusGroupX.getStimulusList();
        return stimulusXList;
    }

    public void setStimulusXList(List<Stimulus> stimulusXList) {
        this.stimulusXList = stimulusXList;
    }

    public Exercise getExersice() {
        return exercise;
    }

    public void setExersice(Exercise exercise) {
        this.exercise = exercise;
    }
    
    
    
}
