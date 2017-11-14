package question2;

import question1.VisiteurExpression;

public class VisiteurBoolEvaluation extends VisiteurExpressionBooleenne<Boolean> {

    private VisiteurExpression<Integer> ve;

    public VisiteurBoolEvaluation(VisiteurExpression<Integer> ve) {
        this.ve = ve;
    }

    public Boolean visite(Vrai v) {
        return true;
    }

    public Boolean visite(Faux f) {
        return false;
    }

    public Boolean visite(Non n) {
		return !n.bop().accepter(this) ;
    }

    public Boolean visite(Ou ou) {
        boolean i1 = ou.bop1().accepter(this);
        boolean i2 = ou.bop2().accepter(this);
        return i1 || i2;
    }

    public Boolean visite(Et et) {
        boolean i1 = et.bop1().accepter(this);
        boolean i2 = et.bop2.accepter(this);
        return i1 && i2;
    }

    public Boolean visite(Sup sup) {
        Integer i1 = sup.op1().accepter(this.ve);
        Integer i2 = sup.op2().accepter(this.ve);
        return i1 > i2;
    }

    public Boolean visite(Egal eg) {
        Integer i1 = eg.op1().accepter(this.ve);
        Integer i2 = eg.op2().accepter(this.ve);
        return i1 == i2;
    }

    public Boolean visite(Inf inf) {
        Integer i1 = inf.op1().accepter(this.ve);
        Integer i2 = inf.op2().accepter(this.ve);
        return i1 < i2;
    }

}
