package com.stimulusbriefing.entitys;

import com.stimulusbriefing.entitys.Answer;
import com.stimulusbriefing.entitys.TaskExercise;
import com.stimulusbriefing.entitys.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-02-21T14:28:40")
@StaticMetamodel(Result.class)
public class Result_ { 

    public static volatile SingularAttribute<Result, Answer> idAnswer;
    public static volatile SingularAttribute<Result, TaskExercise> idTaskExercise;
    public static volatile SingularAttribute<Result, Integer> idResult;
    public static volatile SingularAttribute<Result, User> id_User;

}