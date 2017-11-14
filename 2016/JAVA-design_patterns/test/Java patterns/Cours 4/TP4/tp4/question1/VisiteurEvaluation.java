package question1;

public class VisiteurEvaluation extends VisiteurParDefaut<Integer> {

    private Contexte c;

    public VisiteurEvaluation(Contexte c) {
        this.c = c;
    }

    // On lit la variable stockee dans la mémoire
    public Integer visite(Variable v) {
		return c.lire(v.nom());
	}
	
    public Integer visite(Constante c) {
		return c.valeur();
	}

	public Integer visite(Division d) {
		Integer i1 = d.op1().accepter(this) ;
		Integer i2 = d.op2().accepter(this);
		if(i2 == 0){
		    throw new ArithmeticException();
		  }
	    return i1/i2;
	}

	public Integer visite(Addition a) {
		Integer i1 = a.op1().accepter(this) ;
		Integer i2 = a.op2().accepter(this);
	    return i1+i2;
	}

	public Integer visite(Multiplication m) {
		Integer i1 = m.op1().accepter(this) ;
		Integer i2 = m.op2().accepter(this);
	    return i1*i2;
	}

	public Integer visite(Soustraction s) {
		Integer i1 = s.op1().accepter(this) ;
		Integer i2 = s.op2().accepter(this);
	    return i1-i2;
	}
    // à compléter
    // aucun "warning, de type unsafe à la compilation ne doit apparaître

    public Contexte contexte() {
        return c;
    }

}
