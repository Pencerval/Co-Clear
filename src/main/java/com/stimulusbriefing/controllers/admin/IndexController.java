/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stimulusbriefing.controllers.admin;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pencerval
 */

@ManagedBean(name = "indexController")
@SessionScoped
public class IndexController implements Serializable {


    public IndexController() {
    }

    public String toMasiveUpload(){
       return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/admin/advanced/stimulusupload"; 
    }

}
