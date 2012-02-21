package com.stimulusbriefing.entitys;

import com.stimulusbriefing.entitys.Answer;
import com.stimulusbriefing.entitys.Excersice;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-02-21T14:28:40")
@StaticMetamodel(PossibleSolution.class)
public class PossibleSolution_ { 

    public static volatile SingularAttribute<PossibleSolution, Integer> idPossibleSolution;
    public static volatile SingularAttribute<PossibleSolution, Integer> correct;
    public static volatile SingularAttribute<PossibleSolution, Answer> idAnswer;
    public static volatile SingularAttribute<PossibleSolution, Excersice> idExcersice;

}