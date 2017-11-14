package question3;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2<T> implements PileI<T>{
  /** par d�l�gation : utilisation de la class Stack */
  private Stack<T> stk;
  /** la capacit� de la pile */
  private int capacite;
  
  /** Cr�ation d'une pile.
   * @param capacite la "taille maximale" de la pile, doit �tre > 0
   */
  public Pile2(int capacite){
    // � compl�ter
  }

  public Pile2(){
    // � compl�ter
  }

  public void empiler(T o) throws PilePleineException{
    // � compl�ter
  }
  
  public T depiler() throws PileVideException{
    // � compl�ter
    return null;
  }

  public T sommet() throws PileVideException{
    // � compl�ter
    return null;
  }
  
  
  /** Effectue un test de l'�tat de la pile.
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide(){
    return stk.empty();
  }
  
  public boolean estPleine(){
    return capacite == stk.size();
  }
  
  /** Retourne une repr�sentation en String d'une pile, 
   * contenant la repr�sentation en String de chaque �l�ment.
   * @return une repr�sentation en String d'une pile
   */ 
  public String toString(){
    String s = "[";
    for(int i = taille()-1; i>=0;i--){
      s = s + stk.elementAt(i);
      if(i>0) s = s+ ", ";
    }
    return s + "]";
  }
  
  /** Retourne le nombre d'�l�ment d'une pile.
   * @return le nombre d'�l�ment pr�sent
   */ 
  public int taille(){
    return stk.size();
  }
  
  /** Retourne le nombre d'�l�ment d'une pile.
   * @return le nombre d'�l�ment possible
   */ 
  public int capacite(){
    return this.capacite;
  }
} // Pile2