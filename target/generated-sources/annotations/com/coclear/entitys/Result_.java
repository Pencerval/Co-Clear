package com.coclear.entitys;

import com.coclear.entitys.Answer;
import com.coclear.entitys.TaskExercise;
import com.coclear.entitys.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(Result.class)
public class Result_ { 

    public static volatile SingularAttribute<Result, Answer> idAnswer;
    public static volatile SingularAttribute<Result, TaskExercise> idTaskExercise;
    public static volatile SingularAttribute<Result, Integer> idResult;
    public static volatile SingularAttribute<Result, User> id_User;

}