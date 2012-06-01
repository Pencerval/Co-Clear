/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.controllers.admin.advanced;

import au.com.bytecode.opencsv.CSVWriter;
import com.coclear.entitys.*;
import java.io.BufferedWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Pencerval
 */
@ManagedBean(name = "briefingsController")
@ViewScoped
public class BriefingsController implements Serializable {

    @EJB
    private com.coclear.sessionbeans.UserFacade userFacade;
    @EJB
    private com.coclear.sessionbeans.TaskFacade taskFacade;
    @EJB
    private com.coclear.sessionbeans.AnswerFacade answerFacade;
    List<User> users;
    User selectedUser;
    List<UserTask> userTasks;
    //UserTask selectedUserTask;
    List<Task> tasks;
    //Task selectedTask;
    StreamedContent csv;

    public BriefingsController() {
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        userTasks = new ArrayList<UserTask>();
        if (this.selectedUser != null) {
            for (UserTask userTask : selectedUser.getUserTaskList()) {
                if (userTask.getComplete()) {
                    userTasks.add(userTask);
                }
            }
        }
    }

    public List<User> getUsers() {
        if (users == null) {
            List<User> userWithEndedTask = new ArrayList<User>();
            for (User user : userFacade.getUserbyAdmin(false)) {
                for (UserTask userTask : user.getUserTaskList()) {
                    if (userTask.getComplete()) {
                        userWithEndedTask.add(user);
                        break;
                    }
                }
            }
            users = userWithEndedTask;
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Task> getTasks() {
        if (tasks == null) {
            tasks = new ArrayList<Task>();
            for (Task task : taskFacade.findAll()) {
                for (UserTask userTask : task.getUserTaskList()) {
                    if (userTask.getComplete()) {
                        tasks.add(task);
                        break;
                    }
                }
            }
        }
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<UserTask> getUserTasks() {
        if (userTasks == null) {
            userTasks = new ArrayList<UserTask>();
        }
        return userTasks;
    }

    public void setUserTasks(List<UserTask> userTasks) {
        this.userTasks = userTasks;
    }

    public StreamedContent getCsv() {
        return csv;
    }

    public void setCsv(StreamedContent csv) {
        this.csv = csv;
    }

    public void getCsvFromUserTask(UserTask userTask) {
        try {
            //csv=new DefaultStreamedContent();
            //Writer out = new BufferedWriter(csv.getStream());
            //List<Result> results=new LinkedList<Result>();
            //for(Result result:userTask.getUser().getResultList()){
            //    if(result.get){
            //        
            //    }
            //}

            StringWriter stringWriter = new StringWriter();
            CSVWriter cSVWriter = new CSVWriter(new BufferedWriter(stringWriter));
            List<String[]> filas = new LinkedList<String[]>();
            if (userTask.getTask().getType() == 0) {
                filas.add(new String[]{"Resultados"});

                //User data
                filas.add(new String[]{"Usuario", userTask.getUser().getLogin()});
                filas.add(new String[]{"Nombre", userTask.getUser().getName()});
                filas.add(new String[]{"Email", userTask.getUser().getEmail()});
                filas.add(new String[]{"Fecha de nacimiento", "" + userTask.getUser().getBirthdate()});
                filas.add(new String[]{"Telefono", "" + userTask.getUser().getPhone()});

                filas.add(new String[]{"Fecha del informe", new Date().toString()});

                filas.add(new String[]{"Tarea", userTask.getTask().getName()});
                int numberExercise = userTask.getTask().getTaskExerciseList().size();
                filas.add(new String[]{"Numero de ejercicios", "" + numberExercise});
                int numberSuccess = getNumberSuccess(userTask);
                int numberFail = getNumberFail(userTask);
                filas.add(new String[]{"Numero de aciertos", "" + numberSuccess});
                filas.add(new String[]{"Numero de errores", "" + numberFail});

                filas.add(new String[]{"% de aciertos", "" + ((numberSuccess * 100) / numberExercise) + "%"});
                filas.add(new String[]{"% de errores", "" + ((numberFail * 100) / numberExercise) + "%"});

                filas.add(new String[]{""});
                filas.add(new String[]{"Matriz de confusión"});
                filas.add(new String[]{"", "Respuesta Correcta", "b", "k", "ch", "d", "f", "g/gu", "j", "l", "ll", "m", "n", "ñ", "p", "r", "s", "t", "z"});
                filas.add(new String[]{"Respuesta seleccionada"});
                filas.add(new String[]{"b", "", "" + getNumberAnswer("b", "b", userTask), "" + getNumberAnswer("b", "k", userTask), "" + getNumberAnswer("b", "ch", userTask), "" + getNumberAnswer("b", "d", userTask), "" + getNumberAnswer("b", "f", userTask), "" + getNumberAnswer("b", "g/gu", userTask), "" + getNumberAnswer("b", "j", userTask), "" + getNumberAnswer("b", "l", userTask), "" + getNumberAnswer("b", "ll", userTask), "" + getNumberAnswer("b", "m", userTask), "" + getNumberAnswer("b", "n", userTask), "" + getNumberAnswer("b", "ñ", userTask), "" + getNumberAnswer("b", "p", userTask), "" + getNumberAnswer("b", "r", userTask), "" + getNumberAnswer("b", "s", userTask), "" + getNumberAnswer("b", "t", userTask), "" + getNumberAnswer("b", "z", userTask)});
                filas.add(new String[]{"k", "", "" + getNumberAnswer("k", "b", userTask), "" + getNumberAnswer("k", "k", userTask), "" + getNumberAnswer("k", "ch", userTask), "" + getNumberAnswer("k", "d", userTask), "" + getNumberAnswer("k", "f", userTask), "" + getNumberAnswer("k", "g/gu", userTask), "" + getNumberAnswer("k", "j", userTask), "" + getNumberAnswer("k", "l", userTask), "" + getNumberAnswer("k", "ll", userTask), "" + getNumberAnswer("k", "m", userTask), "" + getNumberAnswer("k", "n", userTask), "" + getNumberAnswer("k", "ñ", userTask), "" + getNumberAnswer("k", "p", userTask), "" + getNumberAnswer("k", "r", userTask), "" + getNumberAnswer("k", "s", userTask), "" + getNumberAnswer("k", "t", userTask), "" + getNumberAnswer("k", "z", userTask)});
                filas.add(new String[]{"ch", "", "" + getNumberAnswer("ch", "b", userTask), "" + getNumberAnswer("ch", "k", userTask), "" + getNumberAnswer("ch", "ch", userTask), "" + getNumberAnswer("ch", "d", userTask), "" + getNumberAnswer("ch", "f", userTask), "" + getNumberAnswer("ch", "g/gu", userTask), "" + getNumberAnswer("ch", "j", userTask), "" + getNumberAnswer("ch", "l", userTask), "" + getNumberAnswer("ch", "ll", userTask), "" + getNumberAnswer("ch", "m", userTask), "" + getNumberAnswer("ch", "n", userTask), "" + getNumberAnswer("ch", "ñ", userTask), "" + getNumberAnswer("ch", "p", userTask), "" + getNumberAnswer("ch", "r", userTask), "" + getNumberAnswer("ch", "s", userTask), "" + getNumberAnswer("ch", "t", userTask), "" + getNumberAnswer("ch", "z", userTask)});
                filas.add(new String[]{"d", "", "" + getNumberAnswer("d", "b", userTask), "" + getNumberAnswer("d", "k", userTask), "" + getNumberAnswer("d", "ch", userTask), "" + getNumberAnswer("d", "d", userTask), "" + getNumberAnswer("d", "f", userTask), "" + getNumberAnswer("d", "g/gu", userTask), "" + getNumberAnswer("d", "j", userTask), "" + getNumberAnswer("d", "l", userTask), "" + getNumberAnswer("d", "ll", userTask), "" + getNumberAnswer("d", "m", userTask), "" + getNumberAnswer("d", "n", userTask), "" + getNumberAnswer("d", "ñ", userTask), "" + getNumberAnswer("d", "p", userTask), "" + getNumberAnswer("d", "r", userTask), "" + getNumberAnswer("d", "s", userTask), "" + getNumberAnswer("d", "t", userTask), "" + getNumberAnswer("d", "z", userTask)});
                filas.add(new String[]{"f", "", "" + getNumberAnswer("f", "b", userTask), "" + getNumberAnswer("f", "k", userTask), "" + getNumberAnswer("f", "ch", userTask), "" + getNumberAnswer("f", "d", userTask), "" + getNumberAnswer("f", "f", userTask), "" + getNumberAnswer("f", "g/gu", userTask), "" + getNumberAnswer("f", "j", userTask), "" + getNumberAnswer("f", "l", userTask), "" + getNumberAnswer("f", "ll", userTask), "" + getNumberAnswer("f", "m", userTask), "" + getNumberAnswer("f", "n", userTask), "" + getNumberAnswer("f", "ñ", userTask), "" + getNumberAnswer("f", "p", userTask), "" + getNumberAnswer("f", "r", userTask), "" + getNumberAnswer("f", "s", userTask), "" + getNumberAnswer("f", "t", userTask), "" + getNumberAnswer("f", "z", userTask)});
                filas.add(new String[]{"g/gu", "", "" + getNumberAnswer("g/gu", "b", userTask), "" + getNumberAnswer("g/gu", "k", userTask), "" + getNumberAnswer("g/gu", "ch", userTask), "" + getNumberAnswer("g/gu", "d", userTask), "" + getNumberAnswer("g/gu", "f", userTask), "" + getNumberAnswer("g/gu", "g/gu", userTask), "" + getNumberAnswer("g/gu", "j", userTask), "" + getNumberAnswer("g/gu", "l", userTask), "" + getNumberAnswer("g/gu", "ll", userTask), "" + getNumberAnswer("g/gu", "m", userTask), "" + getNumberAnswer("g/gu", "n", userTask), "" + getNumberAnswer("g/gu", "ñ", userTask), "" + getNumberAnswer("g/gu", "p", userTask), "" + getNumberAnswer("g/gu", "r", userTask), "" + getNumberAnswer("g/gu", "s", userTask), "" + getNumberAnswer("g/gu", "t", userTask), "" + getNumberAnswer("g/gu", "z", userTask)});
                filas.add(new String[]{"j", "", "" + getNumberAnswer("j", "b", userTask), "" + getNumberAnswer("j", "k", userTask), "" + getNumberAnswer("j", "ch", userTask), "" + getNumberAnswer("j", "d", userTask), "" + getNumberAnswer("j", "f", userTask), "" + getNumberAnswer("j", "g/gu", userTask), "" + getNumberAnswer("j", "j", userTask), "" + getNumberAnswer("j", "l", userTask), "" + getNumberAnswer("j", "ll", userTask), "" + getNumberAnswer("j", "m", userTask), "" + getNumberAnswer("j", "n", userTask), "" + getNumberAnswer("j", "ñ", userTask), "" + getNumberAnswer("j", "p", userTask), "" + getNumberAnswer("j", "r", userTask), "" + getNumberAnswer("j", "s", userTask), "" + getNumberAnswer("j", "t", userTask), "" + getNumberAnswer("j", "z", userTask)});
                filas.add(new String[]{"l", "", "" + getNumberAnswer("l", "b", userTask), "" + getNumberAnswer("l", "k", userTask), "" + getNumberAnswer("l", "ch", userTask), "" + getNumberAnswer("l", "d", userTask), "" + getNumberAnswer("l", "f", userTask), "" + getNumberAnswer("l", "g/gu", userTask), "" + getNumberAnswer("l", "j", userTask), "" + getNumberAnswer("l", "l", userTask), "" + getNumberAnswer("l", "ll", userTask), "" + getNumberAnswer("l", "m", userTask), "" + getNumberAnswer("l", "n", userTask), "" + getNumberAnswer("l", "ñ", userTask), "" + getNumberAnswer("l", "p", userTask), "" + getNumberAnswer("l", "r", userTask), "" + getNumberAnswer("l", "s", userTask), "" + getNumberAnswer("l", "t", userTask), "" + getNumberAnswer("l", "z", userTask)});
                filas.add(new String[]{"ll", "", "" + getNumberAnswer("ll", "b", userTask), "" + getNumberAnswer("ll", "k", userTask), "" + getNumberAnswer("ll", "ch", userTask), "" + getNumberAnswer("ll", "d", userTask), "" + getNumberAnswer("ll", "f", userTask), "" + getNumberAnswer("ll", "g/gu", userTask), "" + getNumberAnswer("ll", "j", userTask), "" + getNumberAnswer("ll", "l", userTask), "" + getNumberAnswer("ll", "ll", userTask), "" + getNumberAnswer("ll", "m", userTask), "" + getNumberAnswer("ll", "n", userTask), "" + getNumberAnswer("ll", "ñ", userTask), "" + getNumberAnswer("ll", "p", userTask), "" + getNumberAnswer("ll", "r", userTask), "" + getNumberAnswer("ll", "s", userTask), "" + getNumberAnswer("ll", "t", userTask), "" + getNumberAnswer("ll", "z", userTask)});
                filas.add(new String[]{"m", "", "" + getNumberAnswer("m", "b", userTask), "" + getNumberAnswer("m", "k", userTask), "" + getNumberAnswer("m", "ch", userTask), "" + getNumberAnswer("m", "d", userTask), "" + getNumberAnswer("m", "f", userTask), "" + getNumberAnswer("m", "g/gu", userTask), "" + getNumberAnswer("m", "j", userTask), "" + getNumberAnswer("m", "l", userTask), "" + getNumberAnswer("m", "ll", userTask), "" + getNumberAnswer("m", "m", userTask), "" + getNumberAnswer("m", "n", userTask), "" + getNumberAnswer("m", "ñ", userTask), "" + getNumberAnswer("m", "p", userTask), "" + getNumberAnswer("m", "r", userTask), "" + getNumberAnswer("m", "s", userTask), "" + getNumberAnswer("m", "t", userTask), "" + getNumberAnswer("m", "z", userTask)});
                filas.add(new String[]{"n", "", "" + getNumberAnswer("n", "b", userTask), "" + getNumberAnswer("n", "k", userTask), "" + getNumberAnswer("n", "ch", userTask), "" + getNumberAnswer("n", "d", userTask), "" + getNumberAnswer("n", "f", userTask), "" + getNumberAnswer("n", "g/gu", userTask), "" + getNumberAnswer("n", "j", userTask), "" + getNumberAnswer("n", "l", userTask), "" + getNumberAnswer("n", "ll", userTask), "" + getNumberAnswer("n", "m", userTask), "" + getNumberAnswer("n", "n", userTask), "" + getNumberAnswer("n", "ñ", userTask), "" + getNumberAnswer("n", "p", userTask), "" + getNumberAnswer("n", "r", userTask), "" + getNumberAnswer("n", "s", userTask), "" + getNumberAnswer("n", "t", userTask), "" + getNumberAnswer("n", "z", userTask)});
                filas.add(new String[]{"ñ", "", "" + getNumberAnswer("ñ", "b", userTask), "" + getNumberAnswer("ñ", "k", userTask), "" + getNumberAnswer("ñ", "ch", userTask), "" + getNumberAnswer("ñ", "d", userTask), "" + getNumberAnswer("ñ", "f", userTask), "" + getNumberAnswer("ñ", "g/gu", userTask), "" + getNumberAnswer("ñ", "j", userTask), "" + getNumberAnswer("ñ", "l", userTask), "" + getNumberAnswer("ñ", "ll", userTask), "" + getNumberAnswer("ñ", "m", userTask), "" + getNumberAnswer("ñ", "n", userTask), "" + getNumberAnswer("ñ", "ñ", userTask), "" + getNumberAnswer("ñ", "p", userTask), "" + getNumberAnswer("ñ", "r", userTask), "" + getNumberAnswer("ñ", "s", userTask), "" + getNumberAnswer("ñ", "t", userTask), "" + getNumberAnswer("ñ", "z", userTask)});
                filas.add(new String[]{"p", "", "" + getNumberAnswer("p", "b", userTask), "" + getNumberAnswer("p", "k", userTask), "" + getNumberAnswer("p", "ch", userTask), "" + getNumberAnswer("p", "d", userTask), "" + getNumberAnswer("p", "f", userTask), "" + getNumberAnswer("p", "g/gu", userTask), "" + getNumberAnswer("p", "j", userTask), "" + getNumberAnswer("p", "l", userTask), "" + getNumberAnswer("p", "ll", userTask), "" + getNumberAnswer("p", "m", userTask), "" + getNumberAnswer("p", "n", userTask), "" + getNumberAnswer("p", "ñ", userTask), "" + getNumberAnswer("p", "p", userTask), "" + getNumberAnswer("p", "r", userTask), "" + getNumberAnswer("p", "s", userTask), "" + getNumberAnswer("p", "t", userTask), "" + getNumberAnswer("p", "z", userTask)});
                filas.add(new String[]{"r", "", "" + getNumberAnswer("r", "b", userTask), "" + getNumberAnswer("r", "k", userTask), "" + getNumberAnswer("r", "ch", userTask), "" + getNumberAnswer("r", "d", userTask), "" + getNumberAnswer("r", "f", userTask), "" + getNumberAnswer("r", "g/gu", userTask), "" + getNumberAnswer("r", "j", userTask), "" + getNumberAnswer("r", "l", userTask), "" + getNumberAnswer("r", "ll", userTask), "" + getNumberAnswer("r", "m", userTask), "" + getNumberAnswer("r", "n", userTask), "" + getNumberAnswer("r", "ñ", userTask), "" + getNumberAnswer("r", "p", userTask), "" + getNumberAnswer("r", "r", userTask), "" + getNumberAnswer("r", "s", userTask), "" + getNumberAnswer("r", "t", userTask), "" + getNumberAnswer("r", "z", userTask)});
                filas.add(new String[]{"s", "", "" + getNumberAnswer("s", "b", userTask), "" + getNumberAnswer("s", "k", userTask), "" + getNumberAnswer("s", "ch", userTask), "" + getNumberAnswer("s", "d", userTask), "" + getNumberAnswer("s", "f", userTask), "" + getNumberAnswer("s", "g/gu", userTask), "" + getNumberAnswer("s", "j", userTask), "" + getNumberAnswer("s", "l", userTask), "" + getNumberAnswer("s", "ll", userTask), "" + getNumberAnswer("s", "m", userTask), "" + getNumberAnswer("s", "n", userTask), "" + getNumberAnswer("s", "ñ", userTask), "" + getNumberAnswer("s", "p", userTask), "" + getNumberAnswer("s", "r", userTask), "" + getNumberAnswer("s", "s", userTask), "" + getNumberAnswer("s", "t", userTask), "" + getNumberAnswer("s", "z", userTask)});
                filas.add(new String[]{"t", "", "" + getNumberAnswer("t", "b", userTask), "" + getNumberAnswer("t", "k", userTask), "" + getNumberAnswer("t", "ch", userTask), "" + getNumberAnswer("t", "d", userTask), "" + getNumberAnswer("t", "f", userTask), "" + getNumberAnswer("t", "g/gu", userTask), "" + getNumberAnswer("t", "j", userTask), "" + getNumberAnswer("t", "l", userTask), "" + getNumberAnswer("t", "ll", userTask), "" + getNumberAnswer("t", "m", userTask), "" + getNumberAnswer("t", "n", userTask), "" + getNumberAnswer("t", "ñ", userTask), "" + getNumberAnswer("t", "p", userTask), "" + getNumberAnswer("t", "r", userTask), "" + getNumberAnswer("t", "s", userTask), "" + getNumberAnswer("t", "t", userTask), "" + getNumberAnswer("t", "z", userTask)});
                filas.add(new String[]{"z", "", "" + getNumberAnswer("z", "b", userTask), "" + getNumberAnswer("z", "k", userTask), "" + getNumberAnswer("z", "ch", userTask), "" + getNumberAnswer("z", "d", userTask), "" + getNumberAnswer("z", "f", userTask), "" + getNumberAnswer("z", "g/gu", userTask), "" + getNumberAnswer("z", "j", userTask), "" + getNumberAnswer("z", "l", userTask), "" + getNumberAnswer("z", "ll", userTask), "" + getNumberAnswer("z", "m", userTask), "" + getNumberAnswer("z", "n", userTask), "" + getNumberAnswer("z", "ñ", userTask), "" + getNumberAnswer("z", "p", userTask), "" + getNumberAnswer("z", "r", userTask), "" + getNumberAnswer("z", "s", userTask), "" + getNumberAnswer("z", "t", userTask), "" + getNumberAnswer("z", "z", userTask)});

                filas.add(new String[]{""});
                filas.add(new String[]{"Matriz de confusión porcentaje"});
                filas.add(new String[]{"", "Respuesta Correcta", "b", "k", "ch", "d", "f", "g/gu", "j", "l", "ll", "m", "n", "ñ", "p", "r", "s", "t", "z"});
                filas.add(new String[]{"Respuesta seleccionada"});
                filas.add(new String[]{"b", "", "" + getNumberAnswerPercent("b", "b", userTask) + "%", "" + getNumberAnswerPercent("b", "k", userTask) + "%", "" + getNumberAnswerPercent("b", "ch", userTask) + "%", "" + getNumberAnswerPercent("b", "d", userTask) + "%", "" + getNumberAnswerPercent("b", "f", userTask) + "%", "" + getNumberAnswerPercent("b", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("b", "j", userTask) + "%", "" + getNumberAnswerPercent("b", "l", userTask) + "%", "" + getNumberAnswerPercent("b", "ll", userTask) + "%", "" + getNumberAnswerPercent("b", "m", userTask) + "%", "" + getNumberAnswerPercent("b", "n", userTask) + "%", "" + getNumberAnswerPercent("b", "ñ", userTask) + "%", "" + getNumberAnswerPercent("b", "p", userTask) + "%", "" + getNumberAnswerPercent("b", "r", userTask) + "%", "" + getNumberAnswerPercent("b", "s", userTask) + "%", "" + getNumberAnswerPercent("b", "t", userTask) + "%", "" + getNumberAnswerPercent("b", "z", userTask) + "%"});
                filas.add(new String[]{"k", "", "" + getNumberAnswerPercent("k", "b", userTask) + "%", "" + getNumberAnswerPercent("k", "k", userTask) + "%", "" + getNumberAnswerPercent("k", "ch", userTask) + "%", "" + getNumberAnswerPercent("k", "d", userTask) + "%", "" + getNumberAnswerPercent("k", "f", userTask) + "%", "" + getNumberAnswerPercent("k", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("k", "j", userTask) + "%", "" + getNumberAnswerPercent("k", "l", userTask) + "%", "" + getNumberAnswerPercent("k", "ll", userTask) + "%", "" + getNumberAnswerPercent("k", "m", userTask) + "%", "" + getNumberAnswerPercent("k", "n", userTask) + "%", "" + getNumberAnswerPercent("k", "ñ", userTask) + "%", "" + getNumberAnswerPercent("k", "p", userTask) + "%", "" + getNumberAnswerPercent("k", "r", userTask) + "%", "" + getNumberAnswerPercent("k", "s", userTask) + "%", "" + getNumberAnswerPercent("k", "t", userTask) + "%", "" + getNumberAnswerPercent("k", "z", userTask) + "%"});
                filas.add(new String[]{"ch", "", "" + getNumberAnswerPercent("ch", "b", userTask) + "%", "" + getNumberAnswerPercent("ch", "k", userTask) + "%", "" + getNumberAnswerPercent("ch", "ch", userTask) + "%", "" + getNumberAnswerPercent("ch", "d", userTask) + "%", "" + getNumberAnswerPercent("ch", "f", userTask) + "%", "" + getNumberAnswerPercent("ch", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("ch", "j", userTask) + "%", "" + getNumberAnswerPercent("ch", "l", userTask) + "%", "" + getNumberAnswerPercent("ch", "ll", userTask) + "%", "" + getNumberAnswerPercent("ch", "m", userTask) + "%", "" + getNumberAnswerPercent("ch", "n", userTask) + "%", "" + getNumberAnswerPercent("ch", "ñ", userTask) + "%", "" + getNumberAnswerPercent("ch", "p", userTask) + "%", "" + getNumberAnswerPercent("ch", "r", userTask) + "%", "" + getNumberAnswerPercent("ch", "s", userTask) + "%", "" + getNumberAnswerPercent("ch", "t", userTask) + "%", "" + getNumberAnswerPercent("ch", "z", userTask) + "%"});
                filas.add(new String[]{"d", "", "" + getNumberAnswerPercent("d", "b", userTask) + "%", "" + getNumberAnswerPercent("d", "k", userTask) + "%", "" + getNumberAnswerPercent("d", "ch", userTask) + "%", "" + getNumberAnswerPercent("d", "d", userTask) + "%", "" + getNumberAnswerPercent("d", "f", userTask) + "%", "" + getNumberAnswerPercent("d", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("d", "j", userTask) + "%", "" + getNumberAnswerPercent("d", "l", userTask) + "%", "" + getNumberAnswerPercent("d", "ll", userTask) + "%", "" + getNumberAnswerPercent("d", "m", userTask) + "%", "" + getNumberAnswerPercent("d", "n", userTask) + "%", "" + getNumberAnswerPercent("d", "ñ", userTask) + "%", "" + getNumberAnswerPercent("d", "p", userTask) + "%", "" + getNumberAnswerPercent("d", "r", userTask) + "%", "" + getNumberAnswerPercent("d", "s", userTask) + "%", "" + getNumberAnswerPercent("d", "t", userTask) + "%", "" + getNumberAnswerPercent("d", "z", userTask) + "%"});
                filas.add(new String[]{"f", "", "" + getNumberAnswerPercent("f", "b", userTask) + "%", "" + getNumberAnswerPercent("f", "k", userTask) + "%", "" + getNumberAnswerPercent("f", "ch", userTask) + "%", "" + getNumberAnswerPercent("f", "d", userTask) + "%", "" + getNumberAnswerPercent("f", "f", userTask) + "%", "" + getNumberAnswerPercent("f", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("f", "j", userTask) + "%", "" + getNumberAnswerPercent("f", "l", userTask) + "%", "" + getNumberAnswerPercent("f", "ll", userTask) + "%", "" + getNumberAnswerPercent("f", "m", userTask) + "%", "" + getNumberAnswerPercent("f", "n", userTask) + "%", "" + getNumberAnswerPercent("f", "ñ", userTask) + "%", "" + getNumberAnswerPercent("f", "p", userTask) + "%", "" + getNumberAnswerPercent("f", "r", userTask) + "%", "" + getNumberAnswerPercent("f", "s", userTask) + "%", "" + getNumberAnswerPercent("f", "t", userTask) + "%", "" + getNumberAnswerPercent("f", "z", userTask) + "%"});
                filas.add(new String[]{"g/gu", "", "" + getNumberAnswerPercent("g/gu", "b", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "k", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "ch", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "d", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "f", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "j", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "l", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "ll", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "m", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "n", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "ñ", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "p", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "r", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "s", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "t", userTask) + "%", "" + getNumberAnswerPercent("g/gu", "z", userTask) + "%"});
                filas.add(new String[]{"j", "", "" + getNumberAnswerPercent("j", "b", userTask) + "%", "" + getNumberAnswerPercent("j", "k", userTask) + "%", "" + getNumberAnswerPercent("j", "ch", userTask) + "%", "" + getNumberAnswerPercent("j", "d", userTask) + "%", "" + getNumberAnswerPercent("j", "f", userTask) + "%", "" + getNumberAnswerPercent("j", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("j", "j", userTask) + "%", "" + getNumberAnswerPercent("j", "l", userTask) + "%", "" + getNumberAnswerPercent("j", "ll", userTask) + "%", "" + getNumberAnswerPercent("j", "m", userTask) + "%", "" + getNumberAnswerPercent("j", "n", userTask) + "%", "" + getNumberAnswerPercent("j", "ñ", userTask) + "%", "" + getNumberAnswerPercent("j", "p", userTask) + "%", "" + getNumberAnswerPercent("j", "r", userTask) + "%", "" + getNumberAnswerPercent("j", "s", userTask) + "%", "" + getNumberAnswerPercent("j", "t", userTask) + "%", "" + getNumberAnswerPercent("j", "z", userTask) + "%"});
                filas.add(new String[]{"l", "", "" + getNumberAnswerPercent("l", "b", userTask) + "%", "" + getNumberAnswerPercent("l", "k", userTask) + "%", "" + getNumberAnswerPercent("l", "ch", userTask) + "%", "" + getNumberAnswerPercent("l", "d", userTask) + "%", "" + getNumberAnswerPercent("l", "f", userTask) + "%", "" + getNumberAnswerPercent("l", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("l", "j", userTask) + "%", "" + getNumberAnswerPercent("l", "l", userTask) + "%", "" + getNumberAnswerPercent("l", "ll", userTask) + "%", "" + getNumberAnswerPercent("l", "m", userTask) + "%", "" + getNumberAnswerPercent("l", "n", userTask) + "%", "" + getNumberAnswerPercent("l", "ñ", userTask) + "%", "" + getNumberAnswerPercent("l", "p", userTask) + "%", "" + getNumberAnswerPercent("l", "r", userTask) + "%", "" + getNumberAnswerPercent("l", "s", userTask) + "%", "" + getNumberAnswerPercent("l", "t", userTask) + "%", "" + getNumberAnswerPercent("l", "z", userTask) + "%"});
                filas.add(new String[]{"ll", "", "" + getNumberAnswerPercent("ll", "b", userTask) + "%", "" + getNumberAnswerPercent("ll", "k", userTask) + "%", "" + getNumberAnswerPercent("ll", "ch", userTask) + "%", "" + getNumberAnswerPercent("ll", "d", userTask) + "%", "" + getNumberAnswerPercent("ll", "f", userTask) + "%", "" + getNumberAnswerPercent("ll", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("ll", "j", userTask) + "%", "" + getNumberAnswerPercent("ll", "l", userTask) + "%", "" + getNumberAnswerPercent("ll", "ll", userTask) + "%", "" + getNumberAnswerPercent("ll", "m", userTask) + "%", "" + getNumberAnswerPercent("ll", "n", userTask) + "%", "" + getNumberAnswerPercent("ll", "ñ", userTask) + "%", "" + getNumberAnswerPercent("ll", "p", userTask) + "%", "" + getNumberAnswerPercent("ll", "r", userTask) + "%", "" + getNumberAnswerPercent("ll", "s", userTask) + "%", "" + getNumberAnswerPercent("ll", "t", userTask) + "%", "" + getNumberAnswerPercent("ll", "z", userTask) + "%"});
                filas.add(new String[]{"m", "", "" + getNumberAnswerPercent("m", "b", userTask) + "%", "" + getNumberAnswerPercent("m", "k", userTask) + "%", "" + getNumberAnswerPercent("m", "ch", userTask) + "%", "" + getNumberAnswerPercent("m", "d", userTask) + "%", "" + getNumberAnswerPercent("m", "f", userTask) + "%", "" + getNumberAnswerPercent("m", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("m", "j", userTask) + "%", "" + getNumberAnswerPercent("m", "l", userTask) + "%", "" + getNumberAnswerPercent("m", "ll", userTask) + "%", "" + getNumberAnswerPercent("m", "m", userTask) + "%", "" + getNumberAnswerPercent("m", "n", userTask) + "%", "" + getNumberAnswerPercent("m", "ñ", userTask) + "%", "" + getNumberAnswerPercent("m", "p", userTask) + "%", "" + getNumberAnswerPercent("m", "r", userTask) + "%", "" + getNumberAnswerPercent("m", "s", userTask) + "%", "" + getNumberAnswerPercent("m", "t", userTask) + "%", "" + getNumberAnswerPercent("m", "z", userTask) + "%"});
                filas.add(new String[]{"n", "", "" + getNumberAnswerPercent("n", "b", userTask) + "%", "" + getNumberAnswerPercent("n", "k", userTask) + "%", "" + getNumberAnswerPercent("n", "ch", userTask) + "%", "" + getNumberAnswerPercent("n", "d", userTask) + "%", "" + getNumberAnswerPercent("n", "f", userTask) + "%", "" + getNumberAnswerPercent("n", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("n", "j", userTask) + "%", "" + getNumberAnswerPercent("n", "l", userTask) + "%", "" + getNumberAnswerPercent("n", "ll", userTask) + "%", "" + getNumberAnswerPercent("n", "m", userTask) + "%", "" + getNumberAnswerPercent("n", "n", userTask) + "%", "" + getNumberAnswerPercent("n", "ñ", userTask) + "%", "" + getNumberAnswerPercent("n", "p", userTask) + "%", "" + getNumberAnswerPercent("n", "r", userTask) + "%", "" + getNumberAnswerPercent("n", "s", userTask) + "%", "" + getNumberAnswerPercent("n", "t", userTask) + "%", "" + getNumberAnswerPercent("n", "z", userTask) + "%"});
                filas.add(new String[]{"ñ", "", "" + getNumberAnswerPercent("ñ", "b", userTask) + "%", "" + getNumberAnswerPercent("ñ", "k", userTask) + "%", "" + getNumberAnswerPercent("ñ", "ch", userTask) + "%", "" + getNumberAnswerPercent("ñ", "d", userTask) + "%", "" + getNumberAnswerPercent("ñ", "f", userTask) + "%", "" + getNumberAnswerPercent("ñ", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("ñ", "j", userTask) + "%", "" + getNumberAnswerPercent("ñ", "l", userTask) + "%", "" + getNumberAnswerPercent("ñ", "ll", userTask) + "%", "" + getNumberAnswerPercent("ñ", "m", userTask) + "%", "" + getNumberAnswerPercent("ñ", "n", userTask) + "%", "" + getNumberAnswerPercent("ñ", "ñ", userTask) + "%", "" + getNumberAnswerPercent("ñ", "p", userTask) + "%", "" + getNumberAnswerPercent("ñ", "r", userTask) + "%", "" + getNumberAnswerPercent("ñ", "s", userTask) + "%", "" + getNumberAnswerPercent("ñ", "t", userTask) + "%", "" + getNumberAnswerPercent("ñ", "z", userTask) + "%"});
                filas.add(new String[]{"p", "", "" + getNumberAnswerPercent("p", "b", userTask) + "%", "" + getNumberAnswerPercent("p", "k", userTask) + "%", "" + getNumberAnswerPercent("p", "ch", userTask) + "%", "" + getNumberAnswerPercent("p", "d", userTask) + "%", "" + getNumberAnswerPercent("p", "f", userTask) + "%", "" + getNumberAnswerPercent("p", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("p", "j", userTask) + "%", "" + getNumberAnswerPercent("p", "l", userTask) + "%", "" + getNumberAnswerPercent("p", "ll", userTask) + "%", "" + getNumberAnswerPercent("p", "m", userTask) + "%", "" + getNumberAnswerPercent("p", "n", userTask) + "%", "" + getNumberAnswerPercent("p", "ñ", userTask) + "%", "" + getNumberAnswerPercent("p", "p", userTask) + "%", "" + getNumberAnswerPercent("p", "r", userTask) + "%", "" + getNumberAnswerPercent("p", "s", userTask) + "%", "" + getNumberAnswerPercent("p", "t", userTask) + "%", "" + getNumberAnswerPercent("p", "z", userTask) + "%"});
                filas.add(new String[]{"r", "", "" + getNumberAnswerPercent("r", "b", userTask) + "%", "" + getNumberAnswerPercent("r", "k", userTask) + "%", "" + getNumberAnswerPercent("r", "ch", userTask) + "%", "" + getNumberAnswerPercent("r", "d", userTask) + "%", "" + getNumberAnswerPercent("r", "f", userTask) + "%", "" + getNumberAnswerPercent("r", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("r", "j", userTask) + "%", "" + getNumberAnswerPercent("r", "l", userTask) + "%", "" + getNumberAnswerPercent("r", "ll", userTask) + "%", "" + getNumberAnswerPercent("r", "m", userTask) + "%", "" + getNumberAnswerPercent("r", "n", userTask) + "%", "" + getNumberAnswerPercent("r", "ñ", userTask) + "%", "" + getNumberAnswerPercent("r", "p", userTask) + "%", "" + getNumberAnswerPercent("r", "r", userTask) + "%", "" + getNumberAnswerPercent("r", "s", userTask) + "%", "" + getNumberAnswerPercent("r", "t", userTask) + "%", "" + getNumberAnswerPercent("r", "z", userTask) + "%"});
                filas.add(new String[]{"s", "", "" + getNumberAnswerPercent("s", "b", userTask) + "%", "" + getNumberAnswerPercent("s", "k", userTask) + "%", "" + getNumberAnswerPercent("s", "ch", userTask) + "%", "" + getNumberAnswerPercent("s", "d", userTask) + "%", "" + getNumberAnswerPercent("s", "f", userTask) + "%", "" + getNumberAnswerPercent("s", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("s", "j", userTask) + "%", "" + getNumberAnswerPercent("s", "l", userTask) + "%", "" + getNumberAnswerPercent("s", "ll", userTask) + "%", "" + getNumberAnswerPercent("s", "m", userTask) + "%", "" + getNumberAnswerPercent("s", "n", userTask) + "%", "" + getNumberAnswerPercent("s", "ñ", userTask) + "%", "" + getNumberAnswerPercent("s", "p", userTask) + "%", "" + getNumberAnswerPercent("s", "r", userTask) + "%", "" + getNumberAnswerPercent("s", "s", userTask) + "%", "" + getNumberAnswerPercent("s", "t", userTask) + "%", "" + getNumberAnswerPercent("s", "z", userTask) + "%"});
                filas.add(new String[]{"t", "", "" + getNumberAnswerPercent("t", "b", userTask) + "%", "" + getNumberAnswerPercent("t", "k", userTask) + "%", "" + getNumberAnswerPercent("t", "ch", userTask) + "%", "" + getNumberAnswerPercent("t", "d", userTask) + "%", "" + getNumberAnswerPercent("t", "f", userTask) + "%", "" + getNumberAnswerPercent("t", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("t", "j", userTask) + "%", "" + getNumberAnswerPercent("t", "l", userTask) + "%", "" + getNumberAnswerPercent("t", "ll", userTask) + "%", "" + getNumberAnswerPercent("t", "m", userTask) + "%", "" + getNumberAnswerPercent("t", "n", userTask) + "%", "" + getNumberAnswerPercent("t", "ñ", userTask) + "%", "" + getNumberAnswerPercent("t", "p", userTask) + "%", "" + getNumberAnswerPercent("t", "r", userTask) + "%", "" + getNumberAnswerPercent("t", "s", userTask) + "%", "" + getNumberAnswerPercent("t", "t", userTask) + "%", "" + getNumberAnswerPercent("t", "z", userTask) + "%"});
                filas.add(new String[]{"z", "", "" + getNumberAnswerPercent("z", "b", userTask) + "%", "" + getNumberAnswerPercent("z", "k", userTask) + "%", "" + getNumberAnswerPercent("z", "ch", userTask) + "%", "" + getNumberAnswerPercent("z", "d", userTask) + "%", "" + getNumberAnswerPercent("z", "f", userTask) + "%", "" + getNumberAnswerPercent("z", "g/gu", userTask) + "%", "" + getNumberAnswerPercent("z", "j", userTask) + "%", "" + getNumberAnswerPercent("z", "l", userTask) + "%", "" + getNumberAnswerPercent("z", "ll", userTask) + "%", "" + getNumberAnswerPercent("z", "m", userTask) + "%", "" + getNumberAnswerPercent("z", "n", userTask) + "%", "" + getNumberAnswerPercent("z", "ñ", userTask) + "%", "" + getNumberAnswerPercent("z", "p", userTask) + "%", "" + getNumberAnswerPercent("z", "r", userTask) + "%", "" + getNumberAnswerPercent("z", "s", userTask) + "%", "" + getNumberAnswerPercent("z", "t", userTask) + "%", "" + getNumberAnswerPercent("z", "z", userTask) + "%"});

                filas.add(new String[]{""});
                filas.add(new String[]{"Ejercicios:"});
                filas.add(new String[]{"Id", "Nombre", "Respuesta correcta", "Respuesta seleccionada"});
                Exercise exercise;
                PossibleSolution possibleSolutionCorrect = null;
                Result resultSelected = null;
                Stimulus stimulus = null;
                for (TaskExercise taskExercise : userTask.getTask().getTaskExerciseList()) {
                    exercise = taskExercise.getExercise();
                    for (PossibleSolution possibleSolution : exercise.getPossibleSolutionList()) {
                        if (possibleSolution.getCorrect()) {
                            possibleSolutionCorrect = possibleSolution;
                            break;
                        }
                    }
                    for (Result result : userTask.getResultList()) {
                        if (result.getTaskExercise().getIdTaskExercise() == taskExercise.getIdTaskExercise()) {
                            resultSelected = result;
                            break;
                        }
                    }
                    filas.add(new String[]{"" + exercise.getIdExercise(), exercise.getName(), possibleSolutionCorrect.getAnswer().getName(), resultSelected.getAnswer().getName()});
                    stimulus = exercise.getExerciseStimulusMapList().get(0).getStimulus();
                    filas.add(new String[]{"", "Estimulo:", "" + stimulus.getIdStimulus(), stimulus.getName()});
                }



            } else if (userTask.getTask().getType() == 1) {

                filas.add(new String[]{"Resultados"});
                //User data
                filas.add(new String[]{"Usuario", userTask.getUser().getLogin()});
                filas.add(new String[]{"Nombre", userTask.getUser().getName()});
                filas.add(new String[]{"Email", userTask.getUser().getEmail()});
                filas.add(new String[]{"Fecha de nacimiento", "" + userTask.getUser().getBirthdate()});
                filas.add(new String[]{"Telefono", "" + userTask.getUser().getPhone()});

                filas.add(new String[]{"Fecha del informe", new Date().toString()});

                filas.add(new String[]{"Tarea", userTask.getTask().getName()});
                int numberExercise = userTask.getTask().getTaskExerciseList().size();
                filas.add(new String[]{"Numero de ejercicios", "" + numberExercise});
                int numberSuccess = getNumberSuccess(userTask);
                int numberFail = getNumberFail(userTask);
                filas.add(new String[]{"Numero de aciertos", "" + numberSuccess});
                filas.add(new String[]{"Numero de errores", "" + numberFail});

                filas.add(new String[]{"% de aciertos", "" + ((numberSuccess * 100) / numberExercise) + "%"});
                filas.add(new String[]{"% de errores", "" + ((numberFail * 100) / numberExercise) + "%"});

                filas.add(new String[]{""});
                filas.add(new String[]{"Matriz de confusión"});
                filas.add(new String[]{"", "Respuesta Correcta", "Iguales", "Diferentes"});
                filas.add(new String[]{"Respuesta seleccionada"});
                filas.add(new String[]{"Iguales", "", getSameDifferent(true, true, userTask), getSameDifferent(false, true, userTask)});
                filas.add(new String[]{"Diferentes", "", getSameDifferent(true, false, userTask), getSameDifferent(false, false, userTask)});

                filas.add(new String[]{""});
                filas.add(new String[]{"Matriz de confusión porcentaje"});
                filas.add(new String[]{"", "Respuesta Correcta", "Iguales", "Diferentes"});
                filas.add(new String[]{"Respuesta seleccionada"});
                filas.add(new String[]{"Iguales", "", getSameDifferentByPercent(true, true, userTask), getSameDifferentByPercent(false, true, userTask)});
                filas.add(new String[]{"Diferentes", "", getSameDifferentByPercent(true, false, userTask), getSameDifferentByPercent(false, false, userTask)});

                filas.add(new String[]{""});
                filas.add(new String[]{"Ejercicios:"});
                filas.add(new String[]{"Id", "Nombre", "Respuesta correcta", "Respuesta seleccionada"});
                Exercise exercise;
                PossibleSolution possibleSolutionCorrect = null;
                Result resultSelected = null;
                Stimulus stimulus = null;
                for (TaskExercise taskExercise : userTask.getTask().getTaskExerciseList()) {
                    exercise = taskExercise.getExercise();
                    for (PossibleSolution possibleSolution : exercise.getPossibleSolutionList()) {
                        if (possibleSolution.getCorrect()) {
                            possibleSolutionCorrect = possibleSolution;
                            break;
                        }
                    }
                    for (Result result : userTask.getResultList()) {
                        if (result.getTaskExercise().getIdTaskExercise() == taskExercise.getIdTaskExercise()) {
                            resultSelected = result;
                            break;
                        }
                    }
                    filas.add(new String[]{"" + exercise.getIdExercise(), exercise.getName(), possibleSolutionCorrect.getAnswer().getName(), resultSelected.getAnswer().getName()});
                    stimulus = exercise.getExerciseStimulusMapList().get(0).getStimulus();
                    filas.add(new String[]{"", "Estimulo:", "" + stimulus.getIdStimulus(), stimulus.getName()});
                }



            }
            cSVWriter.writeAll(filas);
            cSVWriter.close();
            //Cabecera
            //linea.
            csv = new DefaultStreamedContent(IOUtils.toInputStream(stringWriter.getBuffer().toString()), "text/csv", userTask.getUser().getLogin() + "-" + userTask.getTask().getName() + ".csv");
        } catch (Exception ex) {
            Logger.getLogger(BriefingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /*
     * private String ""+getNumberAnswer(String insert, String correct){ int
     * responses=0; for(Result result:this.userTask.getResultList()){
     * if(result.getAnswer().getValueName().equals(insert)){ for(TaskExercise
     * taskExercise:this.userTask.getTask().getTaskExerciseList()){
     * for(PossibleSolution
     * possibleSolution:taskExercise.getExercise().getPossibleSolutionList()){
     * if(possibleSolution.getCorrect() &&
     * possibleSolution.getAnswer().getValueName().equals(correct)){
     * responses++; } } } } } return ""+responses/5; }
     *
     */

    private int getNumberAnswer(String insert, String correct, UserTask userTask) {
        int responses = 0;
        for (Result result : userTask.getResultList()) {
            if (result.getAnswer().getValueName().equals(insert)) {
                for (PossibleSolution possibleSolution : result.getTaskExercise().getExercise().getPossibleSolutionList()) {
                    if (possibleSolution.getCorrect() && possibleSolution.getAnswer().getValueName().equals(correct)) {
                        responses = responses + 1;
                    }
                }
            }
        }

        return responses;
    }

    private int getNumberAnswerPercent(String insert, String correct, UserTask userTask) {
        int responses = 0;
        for (Result result : userTask.getResultList()) {
            if (result.getAnswer().getValueName().equals(insert)) {
                for (PossibleSolution possibleSolution : result.getTaskExercise().getExercise().getPossibleSolutionList()) {
                    if (possibleSolution.getCorrect() && possibleSolution.getAnswer().getValueName().equals(correct)) {
                        responses = responses + 1;
                    }
                }
            }
        }
        return (responses * 100) / userTask.getTask().getTaskExerciseList().size();
    }

    private int getNumberSuccess(UserTask userTask) {
        int success = 0;
        for (Result result : userTask.getResultList()) {
            for (PossibleSolution possibleSolution : result.getTaskExercise().getExercise().getPossibleSolutionList()) {
                if (possibleSolution.getCorrect() && result.getAnswer().getIdAnswer() == possibleSolution.getAnswer().getIdAnswer()) {
                    success = success + 1;
                }
            }
        }
        return success;
    }

    private int getNumberSuccessByTask(Task task) {
        int success = 0;
        for (UserTask userTask : task.getUserTaskList()) {
            if (userTask.getComplete()) {
                success = success + getNumberSuccess(userTask);
            }

        }
        return success;
    }

    private int getNumberFail(UserTask userTask) {
        int fail = 0;
        for (Result result : userTask.getResultList()) {
            for (PossibleSolution possibleSolution : result.getTaskExercise().getExercise().getPossibleSolutionList()) {
                if (possibleSolution.getCorrect() && result.getAnswer().getIdAnswer() != possibleSolution.getAnswer().getIdAnswer()) {
                    fail = fail + 1;
                }
            }
        }
        return fail;
    }

    private int getNumberFailByTask(Task task) {
        int fail = 0;
        for (UserTask userTask : task.getUserTaskList()) {
            if (userTask.getComplete()) {
                fail = fail + getNumberFail(userTask);
            }
        }
        return fail;
    }

    private String getSameDifferent(boolean isSame, boolean doSame, UserTask userTask) {
        int sum = 0;
        for (Result result : userTask.getResultList()) {
            if (doSame && isSame) {
                if (result.getAnswer().getIdAnswer() == answerFacade.getSameAnswer().getIdAnswer() && result.getTaskExercise().getExercise().getPossibleSolutionList().get(0).getCorrect()) {
                    sum = sum + 1;
                }
            } else if (!doSame && isSame) {
                if (result.getAnswer().getIdAnswer() == answerFacade.getDifferentAnswer().getIdAnswer() && result.getTaskExercise().getExercise().getPossibleSolutionList().get(0).getCorrect()) {
                    sum = sum + 1;
                }
            } else if (doSame && !isSame) {
                if (result.getAnswer().getIdAnswer() == answerFacade.getSameAnswer().getIdAnswer() && result.getTaskExercise().getExercise().getPossibleSolutionList().get(1).getCorrect()) {
                    sum = sum + 1;
                }
            } else {
                if (result.getAnswer().getIdAnswer() == answerFacade.getDifferentAnswer().getIdAnswer() && result.getTaskExercise().getExercise().getPossibleSolutionList().get(1).getCorrect()) {
                    sum = sum + 1;
                }
            }
        }
        return ""+sum;
    }
    private String getSameDifferentByPercent(boolean isSame, boolean doSame, UserTask userTask) {
        int sum = 0;
        for (Result result : userTask.getResultList()) {
            if (doSame && isSame) {
                if (result.getAnswer().getIdAnswer() == answerFacade.getSameAnswer().getIdAnswer() && result.getTaskExercise().getExercise().getPossibleSolutionList().get(0).getCorrect()) {
                    sum = sum + 1;
                }
            } else if (!doSame && isSame) {
                if (result.getAnswer().getIdAnswer() == answerFacade.getDifferentAnswer().getIdAnswer() && result.getTaskExercise().getExercise().getPossibleSolutionList().get(0).getCorrect()) {
                    sum = sum + 1;
                }
            } else if (doSame && !isSame) {
                if (result.getAnswer().getIdAnswer() == answerFacade.getSameAnswer().getIdAnswer() && result.getTaskExercise().getExercise().getPossibleSolutionList().get(1).getCorrect()) {
                    sum = sum + 1;
                }
            } else {
                if (result.getAnswer().getIdAnswer() == answerFacade.getDifferentAnswer().getIdAnswer() && result.getTaskExercise().getExercise().getPossibleSolutionList().get(1).getCorrect()) {
                    sum = sum + 1;
                }
            }
        }
        return ""+(sum * 100) / userTask.getTask().getTaskExerciseList().size();
    }

    public void getCsvFromTask(Task task) {
        try {
            StringWriter stringWriter = new StringWriter();
            CSVWriter cSVWriter = new CSVWriter(new BufferedWriter(stringWriter));
            List<String[]> filas = new LinkedList<String[]>();
            filas.add(new String[]{"Resultados"});

            filas.add(new String[]{"Tarea", task.getName()});
            filas.add(new String[]{"Fecha", new Date().toString()});

            int numberExercise = getNumberExersiceDone(task);
            filas.add(new String[]{"Numero de ejercicios", "" + numberExercise});
            int numberSuccess = getNumberSuccessByTask(task);
            int numberFail = getNumberFailByTask(task);
            filas.add(new String[]{"Numero de aciertos totales", "" + numberSuccess});
            filas.add(new String[]{"Numero de errores totales", "" + numberFail});

            filas.add(new String[]{"% de aciertos", "" + ((numberSuccess * 100) / numberExercise) + "%"});
            filas.add(new String[]{"% de errores", "" + ((numberFail * 100) / numberExercise) + "%"});

            filas.add(new String[]{""});
            filas.add(new String[]{"Matriz de confusión"});
            filas.add(new String[]{"", "Respuesta Correcta", "b", "k", "ch", "d", "f", "g/gu", "j", "l", "ll", "m", "n", "ñ", "p", "r", "s", "t", "z"});
            filas.add(new String[]{"Respuesta seleccionada"});
            filas.add(new String[]{"b", "", "" + getNumberAnswerByTask("b", "b", task), "" + getNumberAnswerByTask("b", "k", task), "" + getNumberAnswerByTask("b", "ch", task), "" + getNumberAnswerByTask("b", "d", task), "" + getNumberAnswerByTask("b", "f", task), "" + getNumberAnswerByTask("b", "g/gu", task), "" + getNumberAnswerByTask("b", "j", task), "" + getNumberAnswerByTask("b", "l", task), "" + getNumberAnswerByTask("b", "ll", task), "" + getNumberAnswerByTask("b", "m", task), "" + getNumberAnswerByTask("b", "n", task), "" + getNumberAnswerByTask("b", "ñ", task), "" + getNumberAnswerByTask("b", "p", task), "" + getNumberAnswerByTask("b", "r", task), "" + getNumberAnswerByTask("b", "s", task), "" + getNumberAnswerByTask("b", "t", task), "" + getNumberAnswerByTask("b", "z", task)});
            filas.add(new String[]{"k", "", "" + getNumberAnswerByTask("k", "b", task), "" + getNumberAnswerByTask("k", "k", task), "" + getNumberAnswerByTask("k", "ch", task), "" + getNumberAnswerByTask("k", "d", task), "" + getNumberAnswerByTask("k", "f", task), "" + getNumberAnswerByTask("k", "g/gu", task), "" + getNumberAnswerByTask("k", "j", task), "" + getNumberAnswerByTask("k", "l", task), "" + getNumberAnswerByTask("k", "ll", task), "" + getNumberAnswerByTask("k", "m", task), "" + getNumberAnswerByTask("k", "n", task), "" + getNumberAnswerByTask("k", "ñ", task), "" + getNumberAnswerByTask("k", "p", task), "" + getNumberAnswerByTask("k", "r", task), "" + getNumberAnswerByTask("k", "s", task), "" + getNumberAnswerByTask("k", "t", task), "" + getNumberAnswerByTask("k", "z", task)});
            filas.add(new String[]{"ch", "", "" + getNumberAnswerByTask("ch", "b", task), "" + getNumberAnswerByTask("ch", "k", task), "" + getNumberAnswerByTask("ch", "ch", task), "" + getNumberAnswerByTask("ch", "d", task), "" + getNumberAnswerByTask("ch", "f", task), "" + getNumberAnswerByTask("ch", "g/gu", task), "" + getNumberAnswerByTask("ch", "j", task), "" + getNumberAnswerByTask("ch", "l", task), "" + getNumberAnswerByTask("ch", "ll", task), "" + getNumberAnswerByTask("ch", "m", task), "" + getNumberAnswerByTask("ch", "n", task), "" + getNumberAnswerByTask("ch", "ñ", task), "" + getNumberAnswerByTask("ch", "p", task), "" + getNumberAnswerByTask("ch", "r", task), "" + getNumberAnswerByTask("ch", "s", task), "" + getNumberAnswerByTask("ch", "t", task), "" + getNumberAnswerByTask("ch", "z", task)});
            filas.add(new String[]{"d", "", "" + getNumberAnswerByTask("d", "b", task), "" + getNumberAnswerByTask("d", "k", task), "" + getNumberAnswerByTask("d", "ch", task), "" + getNumberAnswerByTask("d", "d", task), "" + getNumberAnswerByTask("d", "f", task), "" + getNumberAnswerByTask("d", "g/gu", task), "" + getNumberAnswerByTask("d", "j", task), "" + getNumberAnswerByTask("d", "l", task), "" + getNumberAnswerByTask("d", "ll", task), "" + getNumberAnswerByTask("d", "m", task), "" + getNumberAnswerByTask("d", "n", task), "" + getNumberAnswerByTask("d", "ñ", task), "" + getNumberAnswerByTask("d", "p", task), "" + getNumberAnswerByTask("d", "r", task), "" + getNumberAnswerByTask("d", "s", task), "" + getNumberAnswerByTask("d", "t", task), "" + getNumberAnswerByTask("d", "z", task)});
            filas.add(new String[]{"f", "", "" + getNumberAnswerByTask("f", "b", task), "" + getNumberAnswerByTask("f", "k", task), "" + getNumberAnswerByTask("f", "ch", task), "" + getNumberAnswerByTask("f", "d", task), "" + getNumberAnswerByTask("f", "f", task), "" + getNumberAnswerByTask("f", "g/gu", task), "" + getNumberAnswerByTask("f", "j", task), "" + getNumberAnswerByTask("f", "l", task), "" + getNumberAnswerByTask("f", "ll", task), "" + getNumberAnswerByTask("f", "m", task), "" + getNumberAnswerByTask("f", "n", task), "" + getNumberAnswerByTask("f", "ñ", task), "" + getNumberAnswerByTask("f", "p", task), "" + getNumberAnswerByTask("f", "r", task), "" + getNumberAnswerByTask("f", "s", task), "" + getNumberAnswerByTask("f", "t", task), "" + getNumberAnswerByTask("f", "z", task)});
            filas.add(new String[]{"g/gu", "", "" + getNumberAnswerByTask("g/gu", "b", task), "" + getNumberAnswerByTask("g/gu", "k", task), "" + getNumberAnswerByTask("g/gu", "ch", task), "" + getNumberAnswerByTask("g/gu", "d", task), "" + getNumberAnswerByTask("g/gu", "f", task), "" + getNumberAnswerByTask("g/gu", "g/gu", task), "" + getNumberAnswerByTask("g/gu", "j", task), "" + getNumberAnswerByTask("g/gu", "l", task), "" + getNumberAnswerByTask("g/gu", "ll", task), "" + getNumberAnswerByTask("g/gu", "m", task), "" + getNumberAnswerByTask("g/gu", "n", task), "" + getNumberAnswerByTask("g/gu", "ñ", task), "" + getNumberAnswerByTask("g/gu", "p", task), "" + getNumberAnswerByTask("g/gu", "r", task), "" + getNumberAnswerByTask("g/gu", "s", task), "" + getNumberAnswerByTask("g/gu", "t", task), "" + getNumberAnswerByTask("g/gu", "z", task)});
            filas.add(new String[]{"j", "", "" + getNumberAnswerByTask("j", "b", task), "" + getNumberAnswerByTask("j", "k", task), "" + getNumberAnswerByTask("j", "ch", task), "" + getNumberAnswerByTask("j", "d", task), "" + getNumberAnswerByTask("j", "f", task), "" + getNumberAnswerByTask("j", "g/gu", task), "" + getNumberAnswerByTask("j", "j", task), "" + getNumberAnswerByTask("j", "l", task), "" + getNumberAnswerByTask("j", "ll", task), "" + getNumberAnswerByTask("j", "m", task), "" + getNumberAnswerByTask("j", "n", task), "" + getNumberAnswerByTask("j", "ñ", task), "" + getNumberAnswerByTask("j", "p", task), "" + getNumberAnswerByTask("j", "r", task), "" + getNumberAnswerByTask("j", "s", task), "" + getNumberAnswerByTask("j", "t", task), "" + getNumberAnswerByTask("j", "z", task)});
            filas.add(new String[]{"l", "", "" + getNumberAnswerByTask("l", "b", task), "" + getNumberAnswerByTask("l", "k", task), "" + getNumberAnswerByTask("l", "ch", task), "" + getNumberAnswerByTask("l", "d", task), "" + getNumberAnswerByTask("l", "f", task), "" + getNumberAnswerByTask("l", "g/gu", task), "" + getNumberAnswerByTask("l", "j", task), "" + getNumberAnswerByTask("l", "l", task), "" + getNumberAnswerByTask("l", "ll", task), "" + getNumberAnswerByTask("l", "m", task), "" + getNumberAnswerByTask("l", "n", task), "" + getNumberAnswerByTask("l", "ñ", task), "" + getNumberAnswerByTask("l", "p", task), "" + getNumberAnswerByTask("l", "r", task), "" + getNumberAnswerByTask("l", "s", task), "" + getNumberAnswerByTask("l", "t", task), "" + getNumberAnswerByTask("l", "z", task)});
            filas.add(new String[]{"ll", "", "" + getNumberAnswerByTask("ll", "b", task), "" + getNumberAnswerByTask("ll", "k", task), "" + getNumberAnswerByTask("ll", "ch", task), "" + getNumberAnswerByTask("ll", "d", task), "" + getNumberAnswerByTask("ll", "f", task), "" + getNumberAnswerByTask("ll", "g/gu", task), "" + getNumberAnswerByTask("ll", "j", task), "" + getNumberAnswerByTask("ll", "l", task), "" + getNumberAnswerByTask("ll", "ll", task), "" + getNumberAnswerByTask("ll", "m", task), "" + getNumberAnswerByTask("ll", "n", task), "" + getNumberAnswerByTask("ll", "ñ", task), "" + getNumberAnswerByTask("ll", "p", task), "" + getNumberAnswerByTask("ll", "r", task), "" + getNumberAnswerByTask("ll", "s", task), "" + getNumberAnswerByTask("ll", "t", task), "" + getNumberAnswerByTask("ll", "z", task)});
            filas.add(new String[]{"m", "", "" + getNumberAnswerByTask("m", "b", task), "" + getNumberAnswerByTask("m", "k", task), "" + getNumberAnswerByTask("m", "ch", task), "" + getNumberAnswerByTask("m", "d", task), "" + getNumberAnswerByTask("m", "f", task), "" + getNumberAnswerByTask("m", "g/gu", task), "" + getNumberAnswerByTask("m", "j", task), "" + getNumberAnswerByTask("m", "l", task), "" + getNumberAnswerByTask("m", "ll", task), "" + getNumberAnswerByTask("m", "m", task), "" + getNumberAnswerByTask("m", "n", task), "" + getNumberAnswerByTask("m", "ñ", task), "" + getNumberAnswerByTask("m", "p", task), "" + getNumberAnswerByTask("m", "r", task), "" + getNumberAnswerByTask("m", "s", task), "" + getNumberAnswerByTask("m", "t", task), "" + getNumberAnswerByTask("m", "z", task)});
            filas.add(new String[]{"n", "", "" + getNumberAnswerByTask("n", "b", task), "" + getNumberAnswerByTask("n", "k", task), "" + getNumberAnswerByTask("n", "ch", task), "" + getNumberAnswerByTask("n", "d", task), "" + getNumberAnswerByTask("n", "f", task), "" + getNumberAnswerByTask("n", "g/gu", task), "" + getNumberAnswerByTask("n", "j", task), "" + getNumberAnswerByTask("n", "l", task), "" + getNumberAnswerByTask("n", "ll", task), "" + getNumberAnswerByTask("n", "m", task), "" + getNumberAnswerByTask("n", "n", task), "" + getNumberAnswerByTask("n", "ñ", task), "" + getNumberAnswerByTask("n", "p", task), "" + getNumberAnswerByTask("n", "r", task), "" + getNumberAnswerByTask("n", "s", task), "" + getNumberAnswerByTask("n", "t", task), "" + getNumberAnswerByTask("n", "z", task)});
            filas.add(new String[]{"ñ", "", "" + getNumberAnswerByTask("ñ", "b", task), "" + getNumberAnswerByTask("ñ", "k", task), "" + getNumberAnswerByTask("ñ", "ch", task), "" + getNumberAnswerByTask("ñ", "d", task), "" + getNumberAnswerByTask("ñ", "f", task), "" + getNumberAnswerByTask("ñ", "g/gu", task), "" + getNumberAnswerByTask("ñ", "j", task), "" + getNumberAnswerByTask("ñ", "l", task), "" + getNumberAnswerByTask("ñ", "ll", task), "" + getNumberAnswerByTask("ñ", "m", task), "" + getNumberAnswerByTask("ñ", "n", task), "" + getNumberAnswerByTask("ñ", "ñ", task), "" + getNumberAnswerByTask("ñ", "p", task), "" + getNumberAnswerByTask("ñ", "r", task), "" + getNumberAnswerByTask("ñ", "s", task), "" + getNumberAnswerByTask("ñ", "t", task), "" + getNumberAnswerByTask("ñ", "z", task)});
            filas.add(new String[]{"p", "", "" + getNumberAnswerByTask("p", "b", task), "" + getNumberAnswerByTask("p", "k", task), "" + getNumberAnswerByTask("p", "ch", task), "" + getNumberAnswerByTask("p", "d", task), "" + getNumberAnswerByTask("p", "f", task), "" + getNumberAnswerByTask("p", "g/gu", task), "" + getNumberAnswerByTask("p", "j", task), "" + getNumberAnswerByTask("p", "l", task), "" + getNumberAnswerByTask("p", "ll", task), "" + getNumberAnswerByTask("p", "m", task), "" + getNumberAnswerByTask("p", "n", task), "" + getNumberAnswerByTask("p", "ñ", task), "" + getNumberAnswerByTask("p", "p", task), "" + getNumberAnswerByTask("p", "r", task), "" + getNumberAnswerByTask("p", "s", task), "" + getNumberAnswerByTask("p", "t", task), "" + getNumberAnswerByTask("p", "z", task)});
            filas.add(new String[]{"r", "", "" + getNumberAnswerByTask("r", "b", task), "" + getNumberAnswerByTask("r", "k", task), "" + getNumberAnswerByTask("r", "ch", task), "" + getNumberAnswerByTask("r", "d", task), "" + getNumberAnswerByTask("r", "f", task), "" + getNumberAnswerByTask("r", "g/gu", task), "" + getNumberAnswerByTask("r", "j", task), "" + getNumberAnswerByTask("r", "l", task), "" + getNumberAnswerByTask("r", "ll", task), "" + getNumberAnswerByTask("r", "m", task), "" + getNumberAnswerByTask("r", "n", task), "" + getNumberAnswerByTask("r", "ñ", task), "" + getNumberAnswerByTask("r", "p", task), "" + getNumberAnswerByTask("r", "r", task), "" + getNumberAnswerByTask("r", "s", task), "" + getNumberAnswerByTask("r", "t", task), "" + getNumberAnswerByTask("r", "z", task)});
            filas.add(new String[]{"s", "", "" + getNumberAnswerByTask("s", "b", task), "" + getNumberAnswerByTask("s", "k", task), "" + getNumberAnswerByTask("s", "ch", task), "" + getNumberAnswerByTask("s", "d", task), "" + getNumberAnswerByTask("s", "f", task), "" + getNumberAnswerByTask("s", "g/gu", task), "" + getNumberAnswerByTask("s", "j", task), "" + getNumberAnswerByTask("s", "l", task), "" + getNumberAnswerByTask("s", "ll", task), "" + getNumberAnswerByTask("s", "m", task), "" + getNumberAnswerByTask("s", "n", task), "" + getNumberAnswerByTask("s", "ñ", task), "" + getNumberAnswerByTask("s", "p", task), "" + getNumberAnswerByTask("s", "r", task), "" + getNumberAnswerByTask("s", "s", task), "" + getNumberAnswerByTask("s", "t", task), "" + getNumberAnswerByTask("s", "z", task)});
            filas.add(new String[]{"t", "", "" + getNumberAnswerByTask("t", "b", task), "" + getNumberAnswerByTask("t", "k", task), "" + getNumberAnswerByTask("t", "ch", task), "" + getNumberAnswerByTask("t", "d", task), "" + getNumberAnswerByTask("t", "f", task), "" + getNumberAnswerByTask("t", "g/gu", task), "" + getNumberAnswerByTask("t", "j", task), "" + getNumberAnswerByTask("t", "l", task), "" + getNumberAnswerByTask("t", "ll", task), "" + getNumberAnswerByTask("t", "m", task), "" + getNumberAnswerByTask("t", "n", task), "" + getNumberAnswerByTask("t", "ñ", task), "" + getNumberAnswerByTask("t", "p", task), "" + getNumberAnswerByTask("t", "r", task), "" + getNumberAnswerByTask("t", "s", task), "" + getNumberAnswerByTask("t", "t", task), "" + getNumberAnswerByTask("t", "z", task)});
            filas.add(new String[]{"z", "", "" + getNumberAnswerByTask("z", "b", task), "" + getNumberAnswerByTask("z", "k", task), "" + getNumberAnswerByTask("z", "ch", task), "" + getNumberAnswerByTask("z", "d", task), "" + getNumberAnswerByTask("z", "f", task), "" + getNumberAnswerByTask("z", "g/gu", task), "" + getNumberAnswerByTask("z", "j", task), "" + getNumberAnswerByTask("z", "l", task), "" + getNumberAnswerByTask("z", "ll", task), "" + getNumberAnswerByTask("z", "m", task), "" + getNumberAnswerByTask("z", "n", task), "" + getNumberAnswerByTask("z", "ñ", task), "" + getNumberAnswerByTask("z", "p", task), "" + getNumberAnswerByTask("z", "r", task), "" + getNumberAnswerByTask("z", "s", task), "" + getNumberAnswerByTask("z", "t", task), "" + getNumberAnswerByTask("z", "z", task)});

            filas.add(new String[]{""});
            filas.add(new String[]{"Matriz de confusión porcentaje"});
            filas.add(new String[]{"", "Respuesta Correcta", "b", "k", "ch", "d", "f", "g/gu", "j", "l", "ll", "m", "n", "ñ", "p", "r", "s", "t", "z"});
            filas.add(new String[]{"Respuesta seleccionada"});
            filas.add(new String[]{"b", "", "" + getNumberAnswerPercentByTask("b", "b", task) + "%", "" + getNumberAnswerPercentByTask("b", "k", task) + "%", "" + getNumberAnswerPercentByTask("b", "ch", task) + "%", "" + getNumberAnswerPercentByTask("b", "d", task) + "%", "" + getNumberAnswerPercentByTask("b", "f", task) + "%", "" + getNumberAnswerPercentByTask("b", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("b", "j", task) + "%", "" + getNumberAnswerPercentByTask("b", "l", task) + "%", "" + getNumberAnswerPercentByTask("b", "ll", task) + "%", "" + getNumberAnswerPercentByTask("b", "m", task) + "%", "" + getNumberAnswerPercentByTask("b", "n", task) + "%", "" + getNumberAnswerPercentByTask("b", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("b", "p", task) + "%", "" + getNumberAnswerPercentByTask("b", "r", task) + "%", "" + getNumberAnswerPercentByTask("b", "s", task) + "%", "" + getNumberAnswerPercentByTask("b", "t", task) + "%", "" + getNumberAnswerPercentByTask("b", "z", task) + "%"});
            filas.add(new String[]{"k", "", "" + getNumberAnswerPercentByTask("k", "b", task) + "%", "" + getNumberAnswerPercentByTask("k", "k", task) + "%", "" + getNumberAnswerPercentByTask("k", "ch", task) + "%", "" + getNumberAnswerPercentByTask("k", "d", task) + "%", "" + getNumberAnswerPercentByTask("k", "f", task) + "%", "" + getNumberAnswerPercentByTask("k", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("k", "j", task) + "%", "" + getNumberAnswerPercentByTask("k", "l", task) + "%", "" + getNumberAnswerPercentByTask("k", "ll", task) + "%", "" + getNumberAnswerPercentByTask("k", "m", task) + "%", "" + getNumberAnswerPercentByTask("k", "n", task) + "%", "" + getNumberAnswerPercentByTask("k", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("k", "p", task) + "%", "" + getNumberAnswerPercentByTask("k", "r", task) + "%", "" + getNumberAnswerPercentByTask("k", "s", task) + "%", "" + getNumberAnswerPercentByTask("k", "t", task) + "%", "" + getNumberAnswerPercentByTask("k", "z", task) + "%"});
            filas.add(new String[]{"ch", "", "" + getNumberAnswerPercentByTask("ch", "b", task) + "%", "" + getNumberAnswerPercentByTask("ch", "k", task) + "%", "" + getNumberAnswerPercentByTask("ch", "ch", task) + "%", "" + getNumberAnswerPercentByTask("ch", "d", task) + "%", "" + getNumberAnswerPercentByTask("ch", "f", task) + "%", "" + getNumberAnswerPercentByTask("ch", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("ch", "j", task) + "%", "" + getNumberAnswerPercentByTask("ch", "l", task) + "%", "" + getNumberAnswerPercentByTask("ch", "ll", task) + "%", "" + getNumberAnswerPercentByTask("ch", "m", task) + "%", "" + getNumberAnswerPercentByTask("ch", "n", task) + "%", "" + getNumberAnswerPercentByTask("ch", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("ch", "p", task) + "%", "" + getNumberAnswerPercentByTask("ch", "r", task) + "%", "" + getNumberAnswerPercentByTask("ch", "s", task) + "%", "" + getNumberAnswerPercentByTask("ch", "t", task) + "%", "" + getNumberAnswerPercentByTask("ch", "z", task) + "%"});
            filas.add(new String[]{"d", "", "" + getNumberAnswerPercentByTask("d", "b", task) + "%", "" + getNumberAnswerPercentByTask("d", "k", task) + "%", "" + getNumberAnswerPercentByTask("d", "ch", task) + "%", "" + getNumberAnswerPercentByTask("d", "d", task) + "%", "" + getNumberAnswerPercentByTask("d", "f", task) + "%", "" + getNumberAnswerPercentByTask("d", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("d", "j", task) + "%", "" + getNumberAnswerPercentByTask("d", "l", task) + "%", "" + getNumberAnswerPercentByTask("d", "ll", task) + "%", "" + getNumberAnswerPercentByTask("d", "m", task) + "%", "" + getNumberAnswerPercentByTask("d", "n", task) + "%", "" + getNumberAnswerPercentByTask("d", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("d", "p", task) + "%", "" + getNumberAnswerPercentByTask("d", "r", task) + "%", "" + getNumberAnswerPercentByTask("d", "s", task) + "%", "" + getNumberAnswerPercentByTask("d", "t", task) + "%", "" + getNumberAnswerPercentByTask("d", "z", task) + "%"});
            filas.add(new String[]{"f", "", "" + getNumberAnswerPercentByTask("f", "b", task) + "%", "" + getNumberAnswerPercentByTask("f", "k", task) + "%", "" + getNumberAnswerPercentByTask("f", "ch", task) + "%", "" + getNumberAnswerPercentByTask("f", "d", task) + "%", "" + getNumberAnswerPercentByTask("f", "f", task) + "%", "" + getNumberAnswerPercentByTask("f", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("f", "j", task) + "%", "" + getNumberAnswerPercentByTask("f", "l", task) + "%", "" + getNumberAnswerPercentByTask("f", "ll", task) + "%", "" + getNumberAnswerPercentByTask("f", "m", task) + "%", "" + getNumberAnswerPercentByTask("f", "n", task) + "%", "" + getNumberAnswerPercentByTask("f", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("f", "p", task) + "%", "" + getNumberAnswerPercentByTask("f", "r", task) + "%", "" + getNumberAnswerPercentByTask("f", "s", task) + "%", "" + getNumberAnswerPercentByTask("f", "t", task) + "%", "" + getNumberAnswerPercentByTask("f", "z", task) + "%"});
            filas.add(new String[]{"g/gu", "", "" + getNumberAnswerPercentByTask("g/gu", "b", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "k", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "ch", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "d", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "f", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "j", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "l", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "ll", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "m", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "n", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "p", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "r", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "s", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "t", task) + "%", "" + getNumberAnswerPercentByTask("g/gu", "z", task) + "%"});
            filas.add(new String[]{"j", "", "" + getNumberAnswerPercentByTask("j", "b", task) + "%", "" + getNumberAnswerPercentByTask("j", "k", task) + "%", "" + getNumberAnswerPercentByTask("j", "ch", task) + "%", "" + getNumberAnswerPercentByTask("j", "d", task) + "%", "" + getNumberAnswerPercentByTask("j", "f", task) + "%", "" + getNumberAnswerPercentByTask("j", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("j", "j", task) + "%", "" + getNumberAnswerPercentByTask("j", "l", task) + "%", "" + getNumberAnswerPercentByTask("j", "ll", task) + "%", "" + getNumberAnswerPercentByTask("j", "m", task) + "%", "" + getNumberAnswerPercentByTask("j", "n", task) + "%", "" + getNumberAnswerPercentByTask("j", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("j", "p", task) + "%", "" + getNumberAnswerPercentByTask("j", "r", task) + "%", "" + getNumberAnswerPercentByTask("j", "s", task) + "%", "" + getNumberAnswerPercentByTask("j", "t", task) + "%", "" + getNumberAnswerPercentByTask("j", "z", task) + "%"});
            filas.add(new String[]{"l", "", "" + getNumberAnswerPercentByTask("l", "b", task) + "%", "" + getNumberAnswerPercentByTask("l", "k", task) + "%", "" + getNumberAnswerPercentByTask("l", "ch", task) + "%", "" + getNumberAnswerPercentByTask("l", "d", task) + "%", "" + getNumberAnswerPercentByTask("l", "f", task) + "%", "" + getNumberAnswerPercentByTask("l", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("l", "j", task) + "%", "" + getNumberAnswerPercentByTask("l", "l", task) + "%", "" + getNumberAnswerPercentByTask("l", "ll", task) + "%", "" + getNumberAnswerPercentByTask("l", "m", task) + "%", "" + getNumberAnswerPercentByTask("l", "n", task) + "%", "" + getNumberAnswerPercentByTask("l", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("l", "p", task) + "%", "" + getNumberAnswerPercentByTask("l", "r", task) + "%", "" + getNumberAnswerPercentByTask("l", "s", task) + "%", "" + getNumberAnswerPercentByTask("l", "t", task) + "%", "" + getNumberAnswerPercentByTask("l", "z", task) + "%"});
            filas.add(new String[]{"ll", "", "" + getNumberAnswerPercentByTask("ll", "b", task) + "%", "" + getNumberAnswerPercentByTask("ll", "k", task) + "%", "" + getNumberAnswerPercentByTask("ll", "ch", task) + "%", "" + getNumberAnswerPercentByTask("ll", "d", task) + "%", "" + getNumberAnswerPercentByTask("ll", "f", task) + "%", "" + getNumberAnswerPercentByTask("ll", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("ll", "j", task) + "%", "" + getNumberAnswerPercentByTask("ll", "l", task) + "%", "" + getNumberAnswerPercentByTask("ll", "ll", task) + "%", "" + getNumberAnswerPercentByTask("ll", "m", task) + "%", "" + getNumberAnswerPercentByTask("ll", "n", task) + "%", "" + getNumberAnswerPercentByTask("ll", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("ll", "p", task) + "%", "" + getNumberAnswerPercentByTask("ll", "r", task) + "%", "" + getNumberAnswerPercentByTask("ll", "s", task) + "%", "" + getNumberAnswerPercentByTask("ll", "t", task) + "%", "" + getNumberAnswerPercentByTask("ll", "z", task) + "%"});
            filas.add(new String[]{"m", "", "" + getNumberAnswerPercentByTask("m", "b", task) + "%", "" + getNumberAnswerPercentByTask("m", "k", task) + "%", "" + getNumberAnswerPercentByTask("m", "ch", task) + "%", "" + getNumberAnswerPercentByTask("m", "d", task) + "%", "" + getNumberAnswerPercentByTask("m", "f", task) + "%", "" + getNumberAnswerPercentByTask("m", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("m", "j", task) + "%", "" + getNumberAnswerPercentByTask("m", "l", task) + "%", "" + getNumberAnswerPercentByTask("m", "ll", task) + "%", "" + getNumberAnswerPercentByTask("m", "m", task) + "%", "" + getNumberAnswerPercentByTask("m", "n", task) + "%", "" + getNumberAnswerPercentByTask("m", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("m", "p", task) + "%", "" + getNumberAnswerPercentByTask("m", "r", task) + "%", "" + getNumberAnswerPercentByTask("m", "s", task) + "%", "" + getNumberAnswerPercentByTask("m", "t", task) + "%", "" + getNumberAnswerPercentByTask("m", "z", task) + "%"});
            filas.add(new String[]{"n", "", "" + getNumberAnswerPercentByTask("n", "b", task) + "%", "" + getNumberAnswerPercentByTask("n", "k", task) + "%", "" + getNumberAnswerPercentByTask("n", "ch", task) + "%", "" + getNumberAnswerPercentByTask("n", "d", task) + "%", "" + getNumberAnswerPercentByTask("n", "f", task) + "%", "" + getNumberAnswerPercentByTask("n", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("n", "j", task) + "%", "" + getNumberAnswerPercentByTask("n", "l", task) + "%", "" + getNumberAnswerPercentByTask("n", "ll", task) + "%", "" + getNumberAnswerPercentByTask("n", "m", task) + "%", "" + getNumberAnswerPercentByTask("n", "n", task) + "%", "" + getNumberAnswerPercentByTask("n", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("n", "p", task) + "%", "" + getNumberAnswerPercentByTask("n", "r", task) + "%", "" + getNumberAnswerPercentByTask("n", "s", task) + "%", "" + getNumberAnswerPercentByTask("n", "t", task) + "%", "" + getNumberAnswerPercentByTask("n", "z", task) + "%"});
            filas.add(new String[]{"ñ", "", "" + getNumberAnswerPercentByTask("ñ", "b", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "k", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "ch", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "d", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "f", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "j", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "l", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "ll", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "m", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "n", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "p", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "r", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "s", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "t", task) + "%", "" + getNumberAnswerPercentByTask("ñ", "z", task) + "%"});
            filas.add(new String[]{"p", "", "" + getNumberAnswerPercentByTask("p", "b", task) + "%", "" + getNumberAnswerPercentByTask("p", "k", task) + "%", "" + getNumberAnswerPercentByTask("p", "ch", task) + "%", "" + getNumberAnswerPercentByTask("p", "d", task) + "%", "" + getNumberAnswerPercentByTask("p", "f", task) + "%", "" + getNumberAnswerPercentByTask("p", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("p", "j", task) + "%", "" + getNumberAnswerPercentByTask("p", "l", task) + "%", "" + getNumberAnswerPercentByTask("p", "ll", task) + "%", "" + getNumberAnswerPercentByTask("p", "m", task) + "%", "" + getNumberAnswerPercentByTask("p", "n", task) + "%", "" + getNumberAnswerPercentByTask("p", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("p", "p", task) + "%", "" + getNumberAnswerPercentByTask("p", "r", task) + "%", "" + getNumberAnswerPercentByTask("p", "s", task) + "%", "" + getNumberAnswerPercentByTask("p", "t", task) + "%", "" + getNumberAnswerPercentByTask("p", "z", task) + "%"});
            filas.add(new String[]{"r", "", "" + getNumberAnswerPercentByTask("r", "b", task) + "%", "" + getNumberAnswerPercentByTask("r", "k", task) + "%", "" + getNumberAnswerPercentByTask("r", "ch", task) + "%", "" + getNumberAnswerPercentByTask("r", "d", task) + "%", "" + getNumberAnswerPercentByTask("r", "f", task) + "%", "" + getNumberAnswerPercentByTask("r", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("r", "j", task) + "%", "" + getNumberAnswerPercentByTask("r", "l", task) + "%", "" + getNumberAnswerPercentByTask("r", "ll", task) + "%", "" + getNumberAnswerPercentByTask("r", "m", task) + "%", "" + getNumberAnswerPercentByTask("r", "n", task) + "%", "" + getNumberAnswerPercentByTask("r", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("r", "p", task) + "%", "" + getNumberAnswerPercentByTask("r", "r", task) + "%", "" + getNumberAnswerPercentByTask("r", "s", task) + "%", "" + getNumberAnswerPercentByTask("r", "t", task) + "%", "" + getNumberAnswerPercentByTask("r", "z", task) + "%"});
            filas.add(new String[]{"s", "", "" + getNumberAnswerPercentByTask("s", "b", task) + "%", "" + getNumberAnswerPercentByTask("s", "k", task) + "%", "" + getNumberAnswerPercentByTask("s", "ch", task) + "%", "" + getNumberAnswerPercentByTask("s", "d", task) + "%", "" + getNumberAnswerPercentByTask("s", "f", task) + "%", "" + getNumberAnswerPercentByTask("s", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("s", "j", task) + "%", "" + getNumberAnswerPercentByTask("s", "l", task) + "%", "" + getNumberAnswerPercentByTask("s", "ll", task) + "%", "" + getNumberAnswerPercentByTask("s", "m", task) + "%", "" + getNumberAnswerPercentByTask("s", "n", task) + "%", "" + getNumberAnswerPercentByTask("s", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("s", "p", task) + "%", "" + getNumberAnswerPercentByTask("s", "r", task) + "%", "" + getNumberAnswerPercentByTask("s", "s", task) + "%", "" + getNumberAnswerPercentByTask("s", "t", task) + "%", "" + getNumberAnswerPercentByTask("s", "z", task) + "%"});
            filas.add(new String[]{"t", "", "" + getNumberAnswerPercentByTask("t", "b", task) + "%", "" + getNumberAnswerPercentByTask("t", "k", task) + "%", "" + getNumberAnswerPercentByTask("t", "ch", task) + "%", "" + getNumberAnswerPercentByTask("t", "d", task) + "%", "" + getNumberAnswerPercentByTask("t", "f", task) + "%", "" + getNumberAnswerPercentByTask("t", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("t", "j", task) + "%", "" + getNumberAnswerPercentByTask("t", "l", task) + "%", "" + getNumberAnswerPercentByTask("t", "ll", task) + "%", "" + getNumberAnswerPercentByTask("t", "m", task) + "%", "" + getNumberAnswerPercentByTask("t", "n", task) + "%", "" + getNumberAnswerPercentByTask("t", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("t", "p", task) + "%", "" + getNumberAnswerPercentByTask("t", "r", task) + "%", "" + getNumberAnswerPercentByTask("t", "s", task) + "%", "" + getNumberAnswerPercentByTask("t", "t", task) + "%", "" + getNumberAnswerPercentByTask("t", "z", task) + "%"});
            filas.add(new String[]{"z", "", "" + getNumberAnswerPercentByTask("z", "b", task) + "%", "" + getNumberAnswerPercentByTask("z", "k", task) + "%", "" + getNumberAnswerPercentByTask("z", "ch", task) + "%", "" + getNumberAnswerPercentByTask("z", "d", task) + "%", "" + getNumberAnswerPercentByTask("z", "f", task) + "%", "" + getNumberAnswerPercentByTask("z", "g/gu", task) + "%", "" + getNumberAnswerPercentByTask("z", "j", task) + "%", "" + getNumberAnswerPercentByTask("z", "l", task) + "%", "" + getNumberAnswerPercentByTask("z", "ll", task) + "%", "" + getNumberAnswerPercentByTask("z", "m", task) + "%", "" + getNumberAnswerPercentByTask("z", "n", task) + "%", "" + getNumberAnswerPercentByTask("z", "ñ", task) + "%", "" + getNumberAnswerPercentByTask("z", "p", task) + "%", "" + getNumberAnswerPercentByTask("z", "r", task) + "%", "" + getNumberAnswerPercentByTask("z", "s", task) + "%", "" + getNumberAnswerPercentByTask("z", "t", task) + "%", "" + getNumberAnswerPercentByTask("z", "z", task) + "%"});

            /*
             * filas.add(new String[]{""}); filas.add(new
             * String[]{"Ejercicios:"}); filas.add(new String[]{"Id", "Nombre",
             * "Respuesta correcta", "Respuesta seleccionada"}); Exercise
             * exercise; PossibleSolution possibleSolutionCorrect = null; Result
             * resultSelected = null; Stimulus stimulus = null; for (UserTask
             * userTask : task.getUserTaskList()) { if (userTask.getComplete())
             * { for (TaskExercise taskExercise :
             * userTask.getTask().getTaskExerciseList()) { exercise =
             * taskExercise.getExercise(); for (PossibleSolution
             * possibleSolution : exercise.getPossibleSolutionList()) { if
             * (possibleSolution.getCorrect()) { possibleSolutionCorrect =
             * possibleSolution; break; } } for (Result result :
             * userTask.getResultList()) { if
             * (result.getTaskExercise().getIdTaskExercise() ==
             * taskExercise.getIdTaskExercise()) { resultSelected = result;
             * break; } } filas.add(new String[]{"" + exercise.getIdExercise(),
             * exercise.getName(),
             * possibleSolutionCorrect.getAnswer().getName(),
             * resultSelected.getAnswer().getName()}); stimulus =
             * exercise.getExerciseStimulusMapList().get(0).getStimulus();
             * filas.add(new String[]{"", "Estimulo:", "" +
             * stimulus.getIdStimulus(), stimulus.getName()}); } } }
             *
             */


            cSVWriter.writeAll(filas);
            cSVWriter.close();
            //Cabecera
            //linea.
            csv = new DefaultStreamedContent(IOUtils.toInputStream(stringWriter.getBuffer().toString()), "text/csv", task.getName() + ".csv");
        } catch (Exception ex) {
            Logger.getLogger(BriefingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int getNumberExersiceDone(Task task) {
        int number = 0;
        for (UserTask userTask : task.getUserTaskList()) {
            if (userTask.getComplete()) {
                number = number + userTask.getResultList().size();
            }
        }
        return number;
    }

    private int getNumberAnswerByTask(String b, String b0, Task task) {
        int responses = 0;
        for (UserTask userTask : task.getUserTaskList()) {
            if (userTask.getComplete()) {
                responses = responses + getNumberAnswer(b, b0, userTask);
            }
        }
        return responses;
    }

    private int getNumberAnswerPercentByTask(String b, String b0, Task task) {
        int responses = 0;
        int cont = 0;
        for (UserTask userTask : task.getUserTaskList()) {
            if (userTask.getComplete()) {
                responses = responses + getNumberAnswerPercent(b, b0, userTask);
                cont = cont + 1;
            }
        }
        return responses / cont;
    }
}
