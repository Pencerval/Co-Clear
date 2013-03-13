/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "formantEditController")
@ViewScoped
public class FormantEditController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    String newName;
    float f1=0;
    float f2=0;
    
    public FormantEditController() {
    }

    public float getF1() {
        return f1;
    }

    public void setF1(float f1) {
        this.f1 = f1;
    }

    public float getF2() {
        return f2;
    }

    public void setF2(float f2) {
        this.f2 = f2;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
    
    public void editStimulusByScript(){
        try {
            //Process proc = Runtime.getRuntime().exec("C:\\Users\\Pencerval\\Desktop\\praat\\praatcon.exe C:\\Users\\Pencerval\\Desktop\\praat\\basic_synthesis_alt_custom.praat F1_mama.wav 0.01 self"+f1+" self"+f2+" self self self self self self self self 1");
            //proc.waitFor();
            Runtime.getRuntime().exec("C:\\Users\\Pencerval\\Desktop\\praat\\praatcon.exe C:\\Users\\Pencerval\\Desktop\\praat\\basic_synthesis_alt_custom.praat F1_mama.wav 0.01 self+"+f1+" self+"+f2+" self self self self self self self self 1");
        //} catch (InterruptedException ex) {
        //    Logger.getLogger(FormantEditController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormantEditController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
    }
}
