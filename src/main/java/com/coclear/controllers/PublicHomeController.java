/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers;

import com.coclear.controllers.admin.UserTaskController;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.Result;
import com.coclear.entitys.User;
import com.coclear.entitys.UserTask;
import com.coclear.sessionbeans.UserFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "publicHomeController")
@ViewScoped
public class PublicHomeController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private UserFacade userFacade;
    private PieChartModel pieTotalModel;
    private PieChartModel pieTotalDiscrimination;
    private PieChartModel pieTotalEntonation;
    private PieChartModel pieTotalIdentification;
    private CartesianChartModel barDiscrimination;
    private CartesianChartModel barEntonation;
    private CartesianChartModel barIdentification;

    public PublicHomeController() {
    }

    public List<UserTask> getIncompleteTask() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user = (User) session.getAttribute("user");
        //user=ejbUserFacade.getUserByname(user.getLogin());
        user = userFacade.find(user.getIdUser());
        //user.getUserTaskCollection().size();
        user.getUserTaskList();
        List<UserTask> userTasks = new ArrayList<UserTask>(user.getUserTaskList());
        List<UserTask> incompleteUserTasktasks = new LinkedList<UserTask>();
        for (UserTask userTask : userTasks) {
            if (!userTask.getComplete()) {
                incompleteUserTasktasks.add(userTask);
            }
        }
        return incompleteUserTasktasks;
    }

    public String exerciseUrl() {
        UserTaskController.UserTaskControllerConverter userTaskControllerConverter = new UserTaskController.UserTaskControllerConverter();
        UserTask task = (UserTask) userTaskControllerConverter.getAsObject(FacesContext.getCurrentInstance(), null, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("task"));
        if (task.getTask().getType() == 0) {
            return "/public/exercises/exerciseIdentification";
        } else if (task.getTask().getType() == 1) {
            return "/public/exercises/exerciseDiscrimination";
        } else if (task.getTask().getType() == 2) {
            return "/public/exercises/exerciseDiscriminationOneStimulus";
        } else if (task.getTask().getType() == 3) {
            return "/public/exercises/exerciseMemo";
        } else {
            return "";
        }
    }

    public PieChartModel getPieTotalModel() {
        if (pieTotalModel == null) {
            pieTotalModel = new PieChartModel();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User user = (User) session.getAttribute("user");
            //user=ejbUserFacade.getUserByname(user.getLogin());
            user = userFacade.find(user.getIdUser());
            //user.getUserTaskCollection().size();
            user.getUserTaskList();
            List<UserTask> userTasks = new ArrayList<UserTask>(user.getUserTaskList());
            int taskdone = 0;
            int totalSucces = 0;
            for (UserTask userTask : userTasks) {
                if (userTask.getComplete()) {
                    taskdone++;
                    totalSucces = totalSucces + getNumberSuccessPercent(userTask);
                }
            }
            pieTotalModel.set("% Acierto", totalSucces / taskdone);
            pieTotalModel.set("% Error", 100 - (totalSucces / taskdone));
        }
        return pieTotalModel;
    }

    private int getNumberSuccessPercent(UserTask userTask) {
        int success = 0;
        for (Result result : userTask.getResultList()) {
            for (PossibleSolution possibleSolution : result.getTaskExercise().getExercise().getPossibleSolutionList()) {
                if (possibleSolution.getCorrect() && result.getAnswer().getIdAnswer() == possibleSolution.getAnswer().getIdAnswer()) {
                    success = success + 1;
                }
            }
        }
        return (int)(((float)success / (float)userTask.getTask().getTaskExerciseList().size()) * 100);
    }

    public PieChartModel getPieTotalDiscrimination() {
        if (pieTotalDiscrimination == null) {
            pieTotalDiscrimination = new PieChartModel();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User user = (User) session.getAttribute("user");
            //user=ejbUserFacade.getUserByname(user.getLogin());
            user = userFacade.find(user.getIdUser());
            //user.getUserTaskCollection().size();
            int percent = getPercentCompletedByTaskType(user.getUserTaskList(), 1);
            if (percent != 0) {
                pieTotalDiscrimination.set("% Acierto", percent);
                pieTotalDiscrimination.set("% Error", 100 - percent);
            }else{
                pieTotalDiscrimination.set("No realizado", 100);
            }

        }
        return pieTotalDiscrimination;
    }

    public PieChartModel getPieTotalEntonation() {
        if (pieTotalEntonation == null) {
            pieTotalEntonation = new PieChartModel();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User user = (User) session.getAttribute("user");
            //user=ejbUserFacade.getUserByname(user.getLogin());
            user = userFacade.find(user.getIdUser());
            //user.getUserTaskCollection().size();
            int percent = getPercentCompletedByTaskType(user.getUserTaskList(), 2);
            if (percent != 0) {
                pieTotalEntonation.set("% Acierto", percent);
                pieTotalEntonation.set("% Error", 100 - percent);
            }else{
                pieTotalEntonation.set("No realizado", 100);
            }
        }
        return pieTotalEntonation;
    }

    public PieChartModel getPieTotalIdentification() {
        if (pieTotalIdentification == null) {
            pieTotalIdentification = new PieChartModel();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User user = (User) session.getAttribute("user");
            //user=ejbUserFacade.getUserByname(user.getLogin());
            user = userFacade.find(user.getIdUser());
            //user.getUserTaskCollection().size();
            int percent = getPercentCompletedByTaskType(user.getUserTaskList(), 0);
            if (percent != 0) {
                pieTotalIdentification.set("% Acierto", percent);
                pieTotalIdentification.set("% Error", 100 - percent);
            }else{
                pieTotalIdentification.set("No realizado", 100);
            }
        }
        return pieTotalIdentification;
    }

    private int getPercentCompletedByTaskType(List<UserTask> userTasks, int type) {
        int taskdone = 0;
        int totalSucces = 0;
        for (UserTask userTask : userTasks) {
            if (userTask.getComplete() && userTask.getTask().getType() == type) {
                taskdone=taskdone+1;
                totalSucces = totalSucces + getNumberSuccessPercent(userTask);
            }
        }
        if(taskdone==0){
            return 0;
        }
        return totalSucces / taskdone;
    }

    public CartesianChartModel getBarDiscrimination() {
        if (barDiscrimination == null) {
            barDiscrimination = new CartesianChartModel();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User user = (User) session.getAttribute("user");
            //user=ejbUserFacade.getUserByname(user.getLogin());
            user = userFacade.find(user.getIdUser());
            //user.getUserTaskCollection().size();
            ChartSeries exercises = fillChartByTaskAndType(user.getUserTaskList(), 1);;
            exercises.setLabel("% Acierto");
            barDiscrimination.addSeries(exercises);
        }
        return barDiscrimination;
    }

    public CartesianChartModel getBarEntonation() {
        if (barEntonation == null) {
            barEntonation = new CartesianChartModel();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User user = (User) session.getAttribute("user");
            //user=ejbUserFacade.getUserByname(user.getLogin());
            user = userFacade.find(user.getIdUser());
            //user.getUserTaskCollection().size();
            ChartSeries exercises = fillChartByTaskAndType(user.getUserTaskList(), 2);;
            exercises.setLabel("% Acierto");
            barEntonation.addSeries(exercises);
        }
        return barEntonation;
    }

    public CartesianChartModel getBarIdentification() {
        if (barIdentification == null) {
            barIdentification = new CartesianChartModel();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User user = (User) session.getAttribute("user");
            //user=ejbUserFacade.getUserByname(user.getLogin());
            user = userFacade.find(user.getIdUser());
            //user.getUserTaskCollection().size();
            ChartSeries exercises = fillChartByTaskAndType(user.getUserTaskList(), 0);;
            exercises.setLabel("% Acierto");
            barIdentification.addSeries(exercises);
        }
        return barIdentification;
    }

    private ChartSeries fillChartByTaskAndType(List<UserTask> userTasks, int type) {
        ChartSeries exercises = new ChartSeries();
        for (UserTask userTask : userTasks) {
            if (userTask.getComplete() && userTask.getTask().getType() == type) {
                exercises.set(userTask.getTask().getName(), getNumberSuccessPercent(userTask));
            }
        }
        return exercises;
    }
}
