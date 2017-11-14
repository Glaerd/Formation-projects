package question3;

import question3.tp_pile.PileI;
import question3.tp_pile.PileVideException;
import question3.tp_pile.PilePleineException;

import java.util.Observable;
public class PileModele<T> extends Observable implements PileI<T>
{
  private PileI<T> pile;


  public PileModele( PileI<T> pile )
  {
    this.pile= pile;
  } // PileModele()

  public void empiler( T o ) throws PilePleineException
  {
      if(estPleine()){
          throw new PilePleineException();
      }
      pile.empiler(o);
      // On prévient du changement de la pile
      setChanged(); 
      // On envoi une notification quelconque du changement au Observateurs
      notifyObservers("empiler");
  } // empiler()

  public T depiler() throws PileVideException
  {
       if(estVide()){
          throw new PileVideException();
      }
      // ici il faudra d'abords stocker la valeur renvoyée après avoir modifié la pile puis on la renvoi après les méthodes de notifications
      T res = pile.depiler();
      setChanged();
      notifyObservers("depiler");
      return res;
  } // depiler()

  public T sommet() throws PileVideException
  {
       if(estVide()){
          throw new PileVideException();
      }
      // On ne fait que consulter la pile donc pas de notification
      return pile.sommet();
  } // sommet()

  public int taille()
  {
    return pile.taille();
  } // taille()

  public int capacite()
  {
    return pile.capacite();
  } // capacite()

  public boolean estVide()
  {
    return pile.estVide();
  } // estVide()

  public boolean estPleine()
  {
    return pile.estPleine();
  } // estPleine()

  public String toString()
  {
    return pile.toString();
  } // toString()
  
} // PileModele

/** notez qu'un message d'alerte subsiste a la compilation (unsafe ...) 
 *  du a l'emploi de notifyObservers, incontournable et sans consequence ici
 */

