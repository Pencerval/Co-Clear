package com.coclear.entitys;

import com.coclear.entitys.AnswerGroup;
import com.coclear.entitys.PossibleSolution;
import com.coclear.entitys.Result;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, String> valueName;
    public static volatile CollectionAttribute<Answer, Result> resultCollection;
    public static volatile SingularAttribute<Answer, Integer> idAnswer;
    public static volatile SingularAttribute<Answer, AnswerGroup> idAnswerGroup;
    public static volatile CollectionAttribute<Answer, PossibleSolution> possibleSolutionCollection;
    public static volatile SingularAttribute<Answer, String> name;
    public static volatile SingularAttribute<Answer, String> example;

}