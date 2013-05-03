package com.coclear.entitys;

import com.coclear.entitys.UserGroupMap;
import com.coclear.entitys.UserTask;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-04-24T22:24:57")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> phone;
    public static volatile SingularAttribute<User, String> placeYearsResidenceFather;
    public static volatile SingularAttribute<User, Integer> speechReadingYears;
    public static volatile ListAttribute<User, UserGroupMap> userGroupMapList;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> learningLenguagesList;
    public static volatile SingularAttribute<User, Integer> speechTherapyYears;
    public static volatile SingularAttribute<User, String> degreeDeafness;
    public static volatile SingularAttribute<User, String> languages;
    public static volatile SingularAttribute<User, String> cochlearImplantModel;
    public static volatile SingularAttribute<User, Date> hearingLossDate;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> causeDeafnes;
    public static volatile SingularAttribute<User, Date> birthdate;
    public static volatile SingularAttribute<User, Boolean> gender;
    public static volatile SingularAttribute<User, String> placeYearsResidence;
    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, Boolean> bilingual;
    public static volatile SingularAttribute<User, Boolean> speechTherapy;
    public static volatile SingularAttribute<User, Integer> educationalOrientationYears;
    public static volatile SingularAttribute<User, String> originDeafnes;
    public static volatile SingularAttribute<User, String> durationHearingLoss;
    public static volatile ListAttribute<User, UserTask> userTaskList;
    public static volatile SingularAttribute<User, String> homeLanguages;
    public static volatile SingularAttribute<User, String> cochlearImplantType;
    public static volatile SingularAttribute<User, Boolean> speechReading;
    public static volatile SingularAttribute<User, Date> implantationDate;
    public static volatile SingularAttribute<User, String> adminComments;
    public static volatile SingularAttribute<User, Integer> signLanguageYears;
    public static volatile SingularAttribute<User, Boolean> educationalOrientation;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Boolean> learningLenguages;
    public static volatile SingularAttribute<User, String> placeYearsResidenceMother;
    public static volatile SingularAttribute<User, Boolean> signLanguage;
    public static volatile SingularAttribute<User, Integer> idUser;
    public static volatile SingularAttribute<User, Integer> isAdmin;
    public static volatile SingularAttribute<User, String> comments;

}