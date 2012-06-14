package com.coclear.entitys;

import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.StimulusGroup;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-06-12T19:24:56")
@StaticMetamodel(Stimulus.class)
public class Stimulus_ { 

    public static volatile SingularAttribute<Stimulus, StimulusGroup> stimulusGroup;
    public static volatile SingularAttribute<Stimulus, String> name;
    public static volatile SingularAttribute<Stimulus, Integer> idStimulus;
    public static volatile ListAttribute<Stimulus, ExerciseStimulusMap> exerciseStimulusMapList;
    public static volatile SingularAttribute<Stimulus, String> type;

}