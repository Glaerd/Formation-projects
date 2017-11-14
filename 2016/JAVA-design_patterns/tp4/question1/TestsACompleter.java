package question1;


public class TestsACompleter extends junit.framework.TestCase{
	private Memoire m;
	private Variable i,j;
	private VisiteurExpression<Integer> ve;
	private VisiteurExpression<String>  vp,vi;
	
	
	public void setUp(){
	  m  = new Memoire();
	  i  = new Variable(m,"i",3);
	  j = new Variable(m,"j",5);
	  ve = new VisiteurEvaluation( m);
	  vi = new VisiteurInfixe( m);
	  vp = new VisiteurPostfixe( m);
	  assertNotNull(i);assertNotNull(j);
	  assertNotNull(ve);assertNotNull(vp);assertNotNull(vi);
	}
	
	
	public void testUneAddition(){
		  Expression expr = new Addition(new Constante(3), new Constante(2));
	    assertEquals(" 3+2 != 5 ?, curieux ",5,expr.accepter(ve).intValue());
	}
	
	public void testMultiplication(){
	   assertEquals(new Multiplication(i,j).accepter(ve).intValue(),15);	
	   assertEquals(new Multiplication(new Constante(4), new Constante(2)).accepter(ve).intValue(), 8);
	}

	public void testSoustraction(){
	  Expression ex = new Soustraction(i,j);
	  assertEquals(-2, ex.accepter(ve).intValue());
	  assertEquals(7, new Soustraction(new Constante(10), new Constante(3)).accepter(ve).intValue());
	}
	
	public void testDivision(){
	  assertEquals(new Division(new Multiplication(i,j),i).accepter(ve).intValue(),5);
	  	  try{
	    new Division(i,new Constante(0)).accepter(ve);
	    fail("division par zéro ? possible ");
	   }catch(ArithmeticException ae){
	   }
	 }
	 
	  public void testVisiteurInfixe(){
	   Expression expr = new Addition(new Constante(3), new Constante(5));
	   assertEquals("(3 + 5) est attendu ","(3 + 5)",expr.accepter(vi));
	   expr = new Addition(expr, new Constante(2));
	   assertEquals("((3 + 5) + 2) est attendu ", "((3 + 5) + 2)", expr.accepter(vi));
	   assertEquals("{i=3, j=5}",m.toString());
	  
	 }
	
	 public void testVisiteurPostfixe(){
	   Expression expr = new Addition(new Constante(3), new Constante(2));
	   assertEquals("(3,2)+",expr.accepter(vp));
	   expr = new Addition(expr, new Constante(2));
	   assertEquals("((3,2)+,2)+",expr.accepter(vp));
	   assertEquals("{i=3, j=5}", m.toString());
	   expr = new Soustraction(expr, expr);
	   assertEquals("(((3,2)+,2)+,((3,2)+,2)+)-", expr.accepter(vp));
	 }
	 
	 

}
