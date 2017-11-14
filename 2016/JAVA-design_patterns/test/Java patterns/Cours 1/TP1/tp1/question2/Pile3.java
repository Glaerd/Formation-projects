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
    if(taille <=0){
        v = new Vector<Object>(CAPACITE_PAR_DEFAUT);
    }
    else{
       v = new Vector<Object>(taille);
    }
  }
  
  public void empiler(Object o) throws PilePleineException{
      if(estPleine()){
          throw new PilePleineException();
      }
      v.add(o);
  }
  
  public Object depiler() throws PileVideException{
    if(estVide()){
          throw new PileVideException();
    }
    return v.remove(v.size() -1);
  }
  
  public Object sommet() throws PileVideException{
    if(estVide()){
              throw new PileVideException();
        }
    return v.get(v.size() -1);
  }
  public int taille(){
    return v.size();
  }
  
  public int capacite(){
    return v.capacity();
  }
  
  public boolean estVide(){
    return v.isEmpty();
  }
  
  public boolean estPleine(){
    return (v.size() >= this.capacite());
  }
  
  public String toString(){
      String s ="[";
      try{
      Pile3 clone;
      clone = (Pile3) this.clone();
      while(clone.estVide() != true){
          s+= clone.depiler();
          if(!clone.estVide()){
              s+=", ";
          }
      }
    }
    catch(Exception exc){
        System.out.println(exc.toString());
    }
      return s + "]";
  }
  
  public boolean equals(Object o){
    if(this == o){return true;}
    if(o == null){ return false;}
    if(!(o instanceof Pile3)){return false;}
    Pile3 p = (Pile3) o;
    return v.equals(p.v);
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
