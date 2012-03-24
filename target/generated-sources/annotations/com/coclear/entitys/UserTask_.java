package com.coclear.entitys;

import com.coclear.entitys.Task;
import com.coclear.entitys.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(UserTask.class)
public class UserTask_ { 

    public static volatile SingularAttribute<UserTask, Task> idTask;
    public static volatile SingularAttribute<UserTask, Integer> complete;
    public static volatile SingularAttribute<UserTask, Integer> idUserTask;
    public static volatile SingularAttribute<UserTask, User> id_User;

}