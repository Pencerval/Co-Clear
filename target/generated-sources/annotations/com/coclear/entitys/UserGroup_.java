package com.coclear.entitys;

import com.coclear.entitys.DefaultGroupTask;
import com.coclear.entitys.UserGroupMap;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-02-20T15:07:53")
@StaticMetamodel(UserGroup.class)
public class UserGroup_ { 

    public static volatile SingularAttribute<UserGroup, Integer> idUserGroup;
    public static volatile SingularAttribute<UserGroup, Boolean> isDefault;
    public static volatile ListAttribute<UserGroup, DefaultGroupTask> defaultGroupTaskList;
    public static volatile SingularAttribute<UserGroup, String> name;
    public static volatile ListAttribute<UserGroup, UserGroupMap> userGroupMapList;

}