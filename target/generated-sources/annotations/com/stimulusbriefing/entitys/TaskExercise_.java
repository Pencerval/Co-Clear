package com.stimulusbriefing.entitys;

import com.stimulusbriefing.entitys.Excersice;
import com.stimulusbriefing.entitys.Result;
import com.stimulusbriefing.entitys.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-02-21T14:28:40")
@StaticMetamodel(TaskExercise.class)
public class TaskExercise_ { 

    public static volatile SingularAttribute<TaskExercise, Task> idTask;
    public static volatile CollectionAttribute<TaskExercise, Result> resultCollection;
    public static volatile SingularAttribute<TaskExercise, Excersice> idExcersice;
    public static volatile SingularAttribute<TaskExercise, Integer> number;
    public static volatile SingularAttribute<TaskExercise, Integer> idTaskExercise;

}