package question2;


import question1.PilePleineException;
import question1.PileVideException;

import java.util.Vector;
/**
 * Decrivez votre classe PileVector ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Pile3 implements PileI
{
 
  private Vector<Object> v;
  
  public Pile3(){
    this(0);
  }

  public Pile3(int taille){
    // traiter le cas <=0
    // a completer
  }
  
  public void empiler(Object o) throws PilePleineException{
    // a completer
  }
  
  public Object depiler() throws PileVideException{
    // a completer
    return null;
  }
  
  public Object sommet() throws PileVideException{
    // a completer
    return null;
  }
  public int taille(){
    // a completer
    return -1;
  }
  public int capacite(){
    // a completer
    return -1;
  }
  public boolean estVide(){
    // a completer
    return false;
  }
  public boolean estPleine(){
    // a completer
    return false;
  }
  public String toString(){
    // a completer
    return "";
  }
  
  public boolean equals(Object o){
    // a completer
    return false;
  }

  // fonction fournie
  public int hashCode(){
    return toString().hashCode();
  }

  // fonction fournie
  public Object clone() throws CloneNotSupportedException{
    Pile3 p = new Pile3(this.capacite());
    p.v = (Vector<Object>)v.clone();
    return p;
  }
}
