package com.coclear.entitys;

import com.coclear.entitys.TagGroupMap;
import com.coclear.entitys.TagGroupStimulusMap;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-04-24T22:24:57")
@StaticMetamodel(TagGroup.class)
public class TagGroup_ { 

    public static volatile SingularAttribute<TagGroup, String> description;
    public static volatile SingularAttribute<TagGroup, String> name;
    public static volatile ListAttribute<TagGroup, TagGroupStimulusMap> tagGroupStimulusMapList;
    public static volatile ListAttribute<TagGroup, TagGroupMap> tagGroupMapList;
    public static volatile SingularAttribute<TagGroup, Integer> idTagGroup;

}