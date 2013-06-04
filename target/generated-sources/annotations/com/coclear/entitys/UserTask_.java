package com.coclear.entitys;

import com.coclear.entitys.Result;
import com.coclear.entitys.Task;
import com.coclear.entitys.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-23T10:13:31")
@StaticMetamodel(UserTask.class)
public class UserTask_ { 

    public static volatile SingularAttribute<UserTask, Boolean> complete;
    public static volatile SingularAttribute<UserTask, Task> task;
    public static volatile ListAttribute<UserTask, Result> resultList;
    public static volatile SingularAttribute<UserTask, User> user;
    public static volatile SingularAttribute<UserTask, Integer> idUserTask;

}