package com.stimulusbriefing.entitys;

import com.stimulusbriefing.entitys.Stimulus;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-02-21T14:28:40")
@StaticMetamodel(StimulusGroup.class)
public class StimulusGroup_ { 

    public static volatile SingularAttribute<StimulusGroup, String> name;
    public static volatile CollectionAttribute<StimulusGroup, Stimulus> stimulusCollection;
    public static volatile SingularAttribute<StimulusGroup, Integer> idStimulusGroup;

}