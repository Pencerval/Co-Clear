package com.coclear.entitys;

import com.coclear.entitys.Result;
import com.coclear.entitys.Task;
import com.coclear.entitys.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-04-20T10:21:38")
@StaticMetamodel(UserTask.class)
public class UserTask_ { 

    public static volatile SingularAttribute<UserTask, Boolean> complete;
    public static volatile SingularAttribute<UserTask, Task> task;
    public static volatile ListAttribute<UserTask, Result> resultList;
    public static volatile SingularAttribute<UserTask, User> user;
    public static volatile SingularAttribute<UserTask, Integer> idUserTask;

}