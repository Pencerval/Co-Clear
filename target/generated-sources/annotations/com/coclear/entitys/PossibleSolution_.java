package com.coclear.entitys;

import com.coclear.entitys.Answer;
import com.coclear.entitys.Excersice;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-03-23T15:10:01")
@StaticMetamodel(PossibleSolution.class)
public class PossibleSolution_ { 

    public static volatile SingularAttribute<PossibleSolution, Integer> idPossibleSolution;
    public static volatile SingularAttribute<PossibleSolution, Integer> correct;
    public static volatile SingularAttribute<PossibleSolution, Answer> idAnswer;
    public static volatile SingularAttribute<PossibleSolution, Excersice> idExcersice;

}