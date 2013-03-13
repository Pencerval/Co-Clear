package com.coclear.entitys;

import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.TaskExercise;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-02-20T15:07:53")
@StaticMetamodel(Exercise.class)
public class Exercise_ { 

    public static volatile ListAttribute<Exercise, PossibleSolution> possibleSolutionList;
    public static volatile SingularAttribute<Exercise, Integer> idExercise;
    public static volatile SingularAttribute<Exercise, String> name;
    public static volatile ListAttribute<Exercise, ExerciseStimulusMap> exerciseStimulusMapList;
    public static volatile SingularAttribute<Exercise, Integer> type;
    public static volatile ListAttribute<Exercise, TaskExercise> taskExerciseList;

}