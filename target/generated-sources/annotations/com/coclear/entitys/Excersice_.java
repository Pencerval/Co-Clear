package com.coclear.entitys;

import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.TaskExercise;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(Excersice.class)
public class Excersice_ { 

    public static volatile CollectionAttribute<Excersice, TaskExercise> taskExerciseCollection;
    public static volatile CollectionAttribute<Excersice, ExerciseStimulusMap> exerciseStimulusMapCollection;
    public static volatile SingularAttribute<Excersice, Integer> idExcersice;
    public static volatile SingularAttribute<Excersice, Integer> description;
    public static volatile CollectionAttribute<Excersice, PossibleSolution> possibleSolutionCollection;
    public static volatile SingularAttribute<Excersice, String> name;
    public static volatile SingularAttribute<Excersice, Integer> type;

}