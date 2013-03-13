package com.coclear.entitys;

import com.coclear.entitys.Answer;
import com.coclear.entitys.Exercise;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2013-02-20T15:07:53")
@StaticMetamodel(PossibleSolution.class)
public class PossibleSolution_ { 

    public static volatile SingularAttribute<PossibleSolution, Integer> idPossibleSolution;
    public static volatile SingularAttribute<PossibleSolution, Boolean> correct;
    public static volatile SingularAttribute<PossibleSolution, Answer> answer;
    public static volatile SingularAttribute<PossibleSolution, Exercise> exercise;
    public static volatile SingularAttribute<PossibleSolution, Integer> answerOrder;

}