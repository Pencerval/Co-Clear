package com.coclear.entitys;

import com.coclear.entitys.ExerciseStimulusMap;
import com.coclear.entitys.StimulusGroup;
import com.coclear.entitys.TagGroupStimulusMap;
import com.coclear.entitys.TagStimulusMap;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-04-24T22:24:57")
@StaticMetamodel(Stimulus.class)
public class Stimulus_ { 

    public static volatile SingularAttribute<Stimulus, StimulusGroup> stimulusGroup;
    public static volatile SingularAttribute<Stimulus, String> name;
    public static volatile ListAttribute<Stimulus, TagGroupStimulusMap> tagGroupStimulusMapList;
    public static volatile SingularAttribute<Stimulus, Integer> idStimulus;
    public static volatile ListAttribute<Stimulus, ExerciseStimulusMap> exerciseStimulusMapList;
    public static volatile ListAttribute<Stimulus, TagStimulusMap> tagStimulusMapList;
    public static volatile SingularAttribute<Stimulus, String> type;

}