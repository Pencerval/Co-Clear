package com.coclear.entitys;

import com.coclear.entitys.TagGroupMap;
import com.coclear.entitys.TagStimulusMap;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-04-24T22:24:57")
@StaticMetamodel(Tag.class)
public class Tag_ { 

    public static volatile SingularAttribute<Tag, String> description;
    public static volatile SingularAttribute<Tag, String> name;
    public static volatile ListAttribute<Tag, TagGroupMap> tagGroupMapList;
    public static volatile ListAttribute<Tag, TagStimulusMap> tagStimulusMapList;
    public static volatile SingularAttribute<Tag, Integer> idTag;

}