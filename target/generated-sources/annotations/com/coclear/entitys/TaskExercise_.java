package com.coclear.entitys;

import com.coclear.entitys.Exercise;
import com.coclear.entitys.Result;
import com.coclear.entitys.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-02-20T15:07:53")
@StaticMetamodel(TaskExercise.class)
public class TaskExercise_ { 

    public static volatile SingularAttribute<TaskExercise, Task> task;
    public static volatile ListAttribute<TaskExercise, Result> resultList;
    public static volatile SingularAttribute<TaskExercise, Exercise> exercise;
    public static volatile SingularAttribute<TaskExercise, Integer> idTaskExercise;
    public static volatile SingularAttribute<TaskExercise, Integer> exerciseOrder;

}