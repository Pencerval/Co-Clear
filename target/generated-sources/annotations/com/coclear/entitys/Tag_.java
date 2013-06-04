package com.coclear.entitys;

import com.coclear.entitys.TagGroupMap;
import com.coclear.entitys.TagStimulusMap;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-23T10:13:31")
@StaticMetamodel(Tag.class)
public class Tag_ { 

    public static volatile SingularAttribute<Tag, String> description;
    public static volatile SingularAttribute<Tag, String> name;
    public static volatile ListAttribute<Tag, TagGroupMap> tagGroupMapList;
    public static volatile ListAttribute<Tag, TagStimulusMap> tagStimulusMapList;
    public static volatile SingularAttribute<Tag, Integer> idTag;

}