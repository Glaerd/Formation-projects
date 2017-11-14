package question1;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Vector;

public class Ensemble<T> extends AbstractSet<T>
{
    private Vector<T> aTable;

    public Ensemble() { this.aTable = new Vector<T>(); }

    @Override public int size() { return this.aTable.size(); }

    @Override public Iterator<T> iterator() { return this.aTable.iterator(); }

    @Override public boolean add( final T t )
    {  
        if(!this.aTable.contains(t))
            return this.aTable.add(t);
        else return false;
    } // add()

    public Ensemble<T> union( final Ensemble<? extends T> e )
    { 
        Ensemble<T> union = new Ensemble<T>();
        union.addAll(this.aTable);
        union.addAll(e.aTable);
        return union;
    } // union()

    public Ensemble<T> inter( final Ensemble<? extends T> e )
    {
        Ensemble<T> inter = new Ensemble<T>();
        inter.addAll(this.aTable);
        inter.retainAll(e.aTable);
        return inter;
    } // inter()

    public Ensemble<T> diff( final Ensemble<? extends T> e )
    {
        Ensemble<T> diff = new Ensemble<T>();
        diff.addAll(this.aTable);
        diff.removeAll(e.aTable);
        return diff;
    } // diff()

    Ensemble<T> diffSym( final Ensemble<? extends T> e )
    {
        return this.union(e).diff(this.inter(e));
    } // diffSym()
} // Ensemble
