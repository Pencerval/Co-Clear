package com.stimulusbriefing.entitys;

import com.stimulusbriefing.entitys.Task;
import com.stimulusbriefing.entitys.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-02-21T14:28:40")
@StaticMetamodel(UserTask.class)
public class UserTask_ { 

    public static volatile SingularAttribute<UserTask, Task> idTask;
    public static volatile SingularAttribute<UserTask, Integer> complete;
    public static volatile SingularAttribute<UserTask, Integer> idUserTask;
    public static volatile SingularAttribute<UserTask, User> id_User;

}