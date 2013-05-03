package com.coclear.entitys;

import com.coclear.entitys.DefaultGroupTask;
import com.coclear.entitys.TaskExercise;
import com.coclear.entitys.UserTask;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-04-24T22:24:57")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Integer> idTask;
    public static volatile SingularAttribute<Task, String> help;
    public static volatile SingularAttribute<Task, Boolean> isUserDefault;
    public static volatile ListAttribute<Task, DefaultGroupTask> defaultGroupTaskList;
    public static volatile ListAttribute<Task, UserTask> userTaskList;
    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, String> name;
    public static volatile SingularAttribute<Task, Boolean> repeatable;
    public static volatile SingularAttribute<Task, Integer> type;
    public static volatile ListAttribute<Task, TaskExercise> taskExerciseList;

}