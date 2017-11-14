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

  @Override public boolean add( T t )
  {  
    // a completer pour la question1-1

    if (!this.contains(t)){
        this.add(t);   
        return true;
    }
    else{
        return false;
    }
  } // add()
  
  public Ensemble<T> union( Ensemble<? extends T> e )
  { 
    Ensemble<T> temp = new Ensemble<T>();
    temp.addAll(this);
    temp.addAll(e);
    
    return temp;
  } // union()
  
  public Ensemble<T> inter( Ensemble<? extends T> e )
  {
   Ensemble<T> temp = new Ensemble<T>();
    temp.addAll(this);
    temp.retainAll(e);
    return temp;
  } // inter()
  
  public Ensemble<T> diff( Ensemble<? extends T> e )
  {
    // a completer pour la question1-2
    Ensemble<T> temp = new Ensemble<T>();
    temp.addAll(this);
    temp.removeAll(e);
    return temp;
  } // diff()
  
  Ensemble<T> diffSym( Ensemble<? extends T> e )
  {
    // a completer pour la question1-2
    Ensemble<T> temp = union(e);
    Ensemble<T> inter = inter(e);
    temp.removeAll(inter);
    return temp;

  } // diffSym()
} // Ensemble
