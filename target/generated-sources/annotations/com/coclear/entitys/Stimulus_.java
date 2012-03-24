package com.coclear.entitys;

import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.StimulusGroup;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(Stimulus.class)
public class Stimulus_ { 

    public static volatile CollectionAttribute<Stimulus, ExerciseStimulusMap> exerciseStimulusMapCollection;
    public static volatile SingularAttribute<Stimulus, String> name;
    public static volatile SingularAttribute<Stimulus, String> path;
    public static volatile SingularAttribute<Stimulus, Integer> idStimulus;
    public static volatile SingularAttribute<Stimulus, StimulusGroup> idStimulusGroup;

}