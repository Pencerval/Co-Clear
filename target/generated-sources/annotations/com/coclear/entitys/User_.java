package com.coclear.entitys;

import com.coclear.entitys.Result;
import com.coclear.entitys.UserGroupMap;
import com.coclear.entitys.UserTask;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> phone;
    public static volatile SingularAttribute<User, String> placeYearsResidenceFather;
    public static volatile SingularAttribute<User, Integer> speechReadingYears;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> learningLenguagesList;
    public static volatile SingularAttribute<User, Integer> speechTherapyYears;
    public static volatile SingularAttribute<User, String> degreeDeafness;
    public static volatile SingularAttribute<User, String> languages;
    public static volatile SingularAttribute<User, String> cochlearImplantModel;
    public static volatile SingularAttribute<User, Date> hearingLossDate;
    public static volatile CollectionAttribute<User, UserTask> userTaskCollection;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> causeDeafnes;
    public static volatile SingularAttribute<User, Date> birthdate;
    public static volatile SingularAttribute<User, Integer> gender;
    public static volatile SingularAttribute<User, String> placeYearsResidence;
    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, Integer> bilingual;
    public static volatile SingularAttribute<User, Integer> speechTherapy;
    public static volatile SingularAttribute<User, Integer> educationalOrientationYears;
    public static volatile SingularAttribute<User, String> durationHearingLoss;
    public static volatile SingularAttribute<User, String> homeLanguages;
    public static volatile SingularAttribute<User, String> cochlearImplantType;
    public static volatile SingularAttribute<User, Date> implantationDate;
    public static volatile SingularAttribute<User, Integer> speechReading;
    public static volatile SingularAttribute<User, String> adminComments;
    public static volatile SingularAttribute<User, Integer> signLanguageYears;
    public static volatile SingularAttribute<User, Integer> educationalOrientation;
    public static volatile CollectionAttribute<User, UserGroupMap> userGroupMapCollection;
    public static volatile CollectionAttribute<User, Result> resultCollection;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Integer> learningLenguages;
    public static volatile SingularAttribute<User, String> placeYearsResidenceMother;
    public static volatile SingularAttribute<User, Integer> signLanguage;
    public static volatile SingularAttribute<User, Integer> idUser;
    public static volatile SingularAttribute<User, Integer> isAdmin;
    public static volatile SingularAttribute<User, String> comments;

}