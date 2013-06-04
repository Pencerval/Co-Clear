package com.coclear.entitys;

import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.TaskExercise;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-23T10:13:31")
@StaticMetamodel(Exercise.class)
public class Exercise_ { 

    public static volatile ListAttribute<Exercise, PossibleSolution> possibleSolutionList;
    public static volatile SingularAttribute<Exercise, Integer> idExercise;
    public static volatile SingularAttribute<Exercise, String> name;
    public static volatile ListAttribute<Exercise, ExerciseStimulusMap> exerciseStimulusMapList;
    public static volatile SingularAttribute<Exercise, Integer> type;
    public static volatile ListAttribute<Exercise, TaskExercise> taskExerciseList;

}