package question3;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2<T> implements PileI<T>{
  /** par délégation : utilisation de la class Stack */
  private Stack<T> stk;
  /** la capacité de la pile */
  private int capacite;
  
  /** Création d'une pile.
   * @param capacite la "taille maximale" de la pile, doit être > 0
   */
  public Pile2(int capacite){
    // à compléter
  }

  public Pile2(){
    // à compléter
  }

  public void empiler(T o) throws PilePleineException{
    // à compléter
  }
  
  public T depiler() throws PileVideException{
    // à compléter
    return null;
  }

  public T sommet() throws PileVideException{
    // à compléter
    return null;
  }
  
  
  /** Effectue un test de l'état de la pile.
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide(){
    return stk.empty();
  }
  
  public boolean estPleine(){
    return capacite == stk.size();
  }
  
  /** Retourne une représentation en String d'une pile, 
   * contenant la représentation en String de chaque élément.
   * @return une représentation en String d'une pile
   */ 
  public String toString(){
    String s = "[";
    for(int i = taille()-1; i>=0;i--){
      s = s + stk.elementAt(i);
      if(i>0) s = s+ ", ";
    }
    return s + "]";
  }
  
  /** Retourne le nombre d'élément d'une pile.
   * @return le nombre d'élément présent
   */ 
  public int taille(){
    return stk.size();
  }
  
  /** Retourne le nombre d'élément d'une pile.
   * @return le nombre d'élément possible
   */ 
  public int capacite(){
    return this.capacite;
  }
} // Pile2