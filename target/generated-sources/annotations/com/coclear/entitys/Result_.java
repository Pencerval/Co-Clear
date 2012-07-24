package com.coclear.entitys;

import com.coclear.entitys.Answer;
import com.coclear.entitys.TaskExercise;
import com.coclear.entitys.UserTask;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-07-24T11:27:48")
@StaticMetamodel(Result.class)
public class Result_ { 

    public static volatile SingularAttribute<Result, Date> start;
    public static volatile SingularAttribute<Result, TaskExercise> taskExercise;
    public static volatile SingularAttribute<Result, Answer> answer;
    public static volatile SingularAttribute<Result, UserTask> userTask;
    public static volatile SingularAttribute<Result, Integer> idResult;
    public static volatile SingularAttribute<Result, Date> end;

}