package com.coclear.entitys;

import com.coclear.entitys.AnswerGroup;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.Result;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-23T10:13:31")
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