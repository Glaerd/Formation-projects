package question3;

import question3.tp_pile.PileI;
import question3.tp_pile.PileVideException;
import question3.tp_pile.PilePleineException;

import java.util.Observable;
public class PileModele<T> extends Observable implements PileI<T>
{
    private PileI<T> pile;

    /* a completer */

    public PileModele( PileI<T> pile )
    {
        this.pile= pile;
    } // PileModele()

    public void empiler( T o ) throws PilePleineException
    {
        pile.empiler(o);
        setChanged();
        notifyObservers();
    } // empiler()

    public T depiler() throws PileVideException
    {
        T t = pile.depiler();
        setChanged();
        notifyObservers();
        return t;
    } // depiler()

    public T sommet() throws PileVideException
    {
        T t = null;
        if(!pile.estVide()) t = pile.sommet();
        return t;
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

