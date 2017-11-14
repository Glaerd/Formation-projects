package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;
import java.util.Iterator;

public class Pile2 implements PileI, Cloneable{
  /** par delegation : utilisation de la class Stack */
  private Stack<Object> stk;

  /** la capacite de la pile */
  private int capacite;
  
  /** Creation d'une pile.
   * @param taille la taille de la pile, la taille doit etre > 0
   */
  public Pile2(int taille){
    if(taille <=0){
        capacite = CAPACITE_PAR_DEFAUT;
    }
    else{
        capacite = taille;
    }
    stk = new Stack<>();
  }

  // constructeur fourni
  public Pile2(){
    this(0);
  }
  

  public void empiler(Object o) throws PilePleineException{
    // On envoi l'exception de pile pleine
      if(estPleine())
    {
        throw new PilePleineException(); 
    }
    stk.push(o);
   }
  
  
  public Object depiler() throws PileVideException{
      if(estVide())
    {
        throw new PileVideException(); 
    }
    return stk.pop();
  }


  public Object sommet() throws PileVideException{
      if(estVide())
    {
        throw new PileVideException(); 
    }
      return stk.peek();
  }
  
  /** Effectue un test de l'etat de la pile.
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide(){ 
    return stk.empty();
  }

  /** Effectue un test de l'etat de la pile.
   * @return vrai si la pile est pleine, faux autrement
   */   
  public boolean estPleine(){
     return (stk.size() >= capacite);
  }
  
  /** Retourne une representation en String d'une pile, 
   * contenant la representation en String de chaque element.
   * @return une representation en String d'une pile
   */ 
  public String toString() {
      // utiliser clone et pop pour récupérer les valeurs     
    String s ="["; 
      try{
        Pile2 clone;  
        clone = (Pile2) this.clone();
        while(estVide() != true){
            s = s + clone.depiler() ;
            if(!clone.estVide()){s +=", ";}
        }
    }
    catch(Exception cnse){
        System.out.println(cnse.toString());
    }
    return s + "]";
  }
  
  /**
   * Comparer les éléments des 2 piles avec cette fonction
   */
  public boolean equals(Object o){
      if(this == o){
          return true;
        }
      if(o == null){
          return false;
        }
      if(!(o instanceof Pile2)){
         return false;  
        }
      Pile2 p = (Pile2) o;
      return stk.equals(p.stk);
    }
  
  // fonction fournie
  public int hashCode(){
    return toString().hashCode();
  }
  
  // fonction fournie
  public Object clone() throws CloneNotSupportedException{
    Pile2 p = new Pile2(this.capacite());
    p.stk = (Stack<Object>)stk.clone();
    return p;
  }

  /** Retourne le nombre d'element d'une pile.
   * @return le nombre d'element present
   */ 
  public int taille(){
    return stk.size();
  }
  
  /** Retourne la capacite de cette pile.
   * @return le nombre d'element possible
   */ 
  public int capacite(){
    return capacite;
  }
  
} // Pile2.java
