package com.coclear.entitys;

import com.coclear.entitys.AnswerGroup;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.Result;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-04-20T10:21:38")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, String> valueName;
    public static volatile ListAttribute<Answer, PossibleSolution> possibleSolutionList;
    public static volatile SingularAttribute<Answer, Integer> idAnswer;
    public static volatile SingularAttribute<Answer, String> name;
    public static volatile ListAttribute<Answer, Result> resultList;
    public static volatile SingularAttribute<Answer, String> example;
    public static volatile SingularAttribute<Answer, AnswerGroup> answerGroup;

}