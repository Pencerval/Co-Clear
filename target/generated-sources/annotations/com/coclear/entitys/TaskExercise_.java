package com.coclear.entitys;

import com.coclear.entitys.Exercise;
import com.coclear.entitys.Result;
import com.coclear.entitys.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-23T10:13:31")
@StaticMetamodel(TaskExercise.class)
public class TaskExercise_ { 

    public static volatile SingularAttribute<TaskExercise, Task> task;
    public static volatile ListAttribute<TaskExercise, Result> resultList;
    public static volatile SingularAttribute<TaskExercise, Exercise> exercise;
    public static volatile SingularAttribute<TaskExercise, Integer> idTaskExercise;
    public static volatile SingularAttribute<TaskExercise, Integer> exerciseOrder;

}