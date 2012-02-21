package com.stimulusbriefing.entitys;

import com.stimulusbriefing.entitys.DefaultGroupTask;
import com.stimulusbriefing.entitys.TaskExercise;
import com.stimulusbriefing.entitys.UserTask;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-02-21T14:28:40")
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