package com.coclear.entitys;

import com.coclear.entitys.DefaultGroupTask;
import com.coclear.entitys.UserGroupMap;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(UserGroup.class)
public class UserGroup_ { 

    public static volatile CollectionAttribute<UserGroup, UserGroupMap> userGroupMapCollection;
    public static volatile CollectionAttribute<UserGroup, DefaultGroupTask> defaultGroupTaskCollection;
    public static volatile SingularAttribute<UserGroup, Integer> idUserGroup;
    public static volatile SingularAttribute<UserGroup, Integer> isDefault;
    public static volatile SingularAttribute<UserGroup, String> name;

}