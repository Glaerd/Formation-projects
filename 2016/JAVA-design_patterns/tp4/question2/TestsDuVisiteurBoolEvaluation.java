package question2;

import question1.*;

public class TestsDuVisiteurBoolEvaluation extends junit.framework.TestCase {

    protected Contexte m;
    protected VisiteurExpressionBooleenne<Boolean> veb;

    public void setUp() {
        m = new Memoire();
        veb = new VisiteurBoolEvaluation(new VisiteurEvaluation(m));
    }

    public void testVisiteurBoolEvaluation() {
        assertTrue(new Vrai().accepter(veb));
        assertFalse(new Faux().accepter(veb));
        assertTrue(new Sup(new Constante(5), new Constante(3)).accepter(veb));
        assertFalse(new Non(new Vrai()).accepter(veb));
        assertTrue(new Ou(new Vrai(), new Faux()).accepter(veb));
        assertFalse(new Et(new Vrai(), new Faux()).accepter(veb));
        assertTrue(new Inf(new Constante(7), new Constante(10)).accepter(veb));
        assertFalse(new Egal(new Constante(5), new Constante(3)).accepter(veb));
        assertTrue(new Egal(new Constante(5), new Constante(5)).accepter(veb));
    }
    
}
