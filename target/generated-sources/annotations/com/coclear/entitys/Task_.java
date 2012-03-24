package com.coclear.entitys;

import com.coclear.entitys.DefaultGroupTask;
import com.coclear.entitys.TaskExercise;
import com.coclear.entitys.UserTask;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Integer> idTask;
    public static volatile CollectionAttribute<Task, TaskExercise> taskExerciseCollection;
    public static volatile CollectionAttribute<Task, DefaultGroupTask> defaultGroupTaskCollection;
    public static volatile SingularAttribute<Task, Integer> isUserDefault;
    public static volatile CollectionAttribute<Task, UserTask> userTaskCollection;
    public static volatile SingularAttribute<Task, String> name;
    public static volatile SingularAttribute<Task, Integer> repeatable;
    public static volatile SingularAttribute<Task, Integer> type;

}