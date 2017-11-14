package question2;

import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;

public class AST_Mult1 extends AST_Progr implements java.io.Serializable
{
/* mod�le "java Style" dont l'AST "WhileL style"  est � construire ci dessous
  a = n1;
  b = n2;
  produit = 0; 
  while (b > 0) {
     produit = produit + a;
     b = b-1;
  }
  afficher(produit);
*/
  private Contexte m = new Memoire();

  public AST_Mult1(int n1 , int n2)
  {
    m.ecrire("a" , n1);
    m.ecrire("b" , n2);
  }
                	    
  private Variable  a = new Variable(m,"a");
  private Variable  b = new Variable(m,"b");
  private Constante zero = new Constante(0);
  private Variable  produit = new Variable( m, "produit", 0 );
  private Constante un = new Constante(1);
  
  private Instruction ast= new TantQue(new Sup(b, zero), new Sequence(new Affectation(produit, new Addition(produit,a)),new Affectation(b,new Soustraction(b,un))));
             
  public Contexte getMem()
  {
    return m;
  }
            
  public Instruction getAST()
  {
    return ast;
  }
} // AST_Mult1 
