package question1;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Vector;

public class Ensemble<T> extends AbstractSet<T>
{
  private Vector<T> table;

  public Ensemble() { table = new Vector<T>(); }
  
  @Override public int size() { return table.size(); }

  @Override public Iterator<T> iterator() { return table.iterator(); }
  
  /*
   * On vérifie si l'élément n'est pas déjà présent dans le vecteur et on l'y ajoute
   * Sinon on laisse tel quel.
   */
  @Override public boolean add( T t )
  {  
    if(!this.contains(t))
      return table.add(t);
    return false;    
  } // add()
  
  public Ensemble<T> union( Ensemble<? extends T> e )
  { 
      Ensemble<T> result = new Ensemble<T>();
      result.addAll(this);
      result.addAll(e);
      return result;
}
  
  public Ensemble<T> inter( Ensemble<? extends T> e )
  {
    Ensemble<T> result = new Ensemble<T>();
    Iterator it = this.iterator();
    Object check ;
    while(it.hasNext()){
        result.add((T)it.next());
    }
    result.retainAll(e);    
    return result;
  } // inter()
  
  public Ensemble<T> diff( Ensemble<? extends T> e )        // Confirmer fonctionnement sur applet avec doublons pour e1, Faut t'il le traiter ???
  {
    Ensemble<T> result = new Ensemble<T>();
    Iterator it = this.iterator();
    Object check ;
    while(it.hasNext()){
        if(!(e.contains(check = it.next()))){
            result.add( (T) check);
        }
    }
    
    return result;
  } // diff()
  
  Ensemble<T> diffSym( Ensemble<? extends T> e )
  {
    Ensemble<T> result = this.diff(e);
    Iterator it = e.iterator();
    Object check ;
    while(it.hasNext()){
        if(!(this.contains(check = it.next()))){
            result.add( (T) check);
        }
    }
    
    return result;
  } // diffSym()
} // Ensemble
