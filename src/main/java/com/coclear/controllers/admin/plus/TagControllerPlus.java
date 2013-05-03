/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.plus;

import com.coclear.entitys.Tag;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "tagControllerPlus")
@ViewScoped
public class TagControllerPlus implements Serializable{

    private static final long serialVersionUID = 1L;
    @EJB
    private com.coclear.sessionbeans.TagFacade tagFacade;
    private List<Tag> tagList;
    private Tag tag = new Tag();

    public TagControllerPlus() {
    }

    //@PostConstruct
    public void preEdit(Tag tag) {
       if (tag != null) {
            this.tag = tag;
        } else {
            this.tag = new Tag();

        }
    }

    public List<Tag> getTagList() {
        tagList = tagFacade.findAll();
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public void delete(Tag tag) {
        tagFacade.remove(tag);
        setTagList(getTagList());
    }

    public Tag getTag() {
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tag")!=null){
            tag=(Tag) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("tag");
        }
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void saveTag() {
        if (getTag().getName() == null || "".equals(getTag().getName())) {
            if (RequestContext.getCurrentInstance() != null) {
                RequestContext.getCurrentInstance().scrollTo("top");
            }
        }
        if (tag.getIdTag() == null) {
            tagFacade.create(tag);
        } else {
            tagFacade.edit(tag);
        }
        try {
            FacesMessage msg = new FacesMessage("Succesful", "Tag " + tag.getName() + " creado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/admin/tag/ListPlus.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(TagControllerPlus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
