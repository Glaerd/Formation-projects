package question3;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetFactory<E extends Comparable<E>>/* a completer */ implements Factory<Set<E>>/* a completer */
{
    public Set<E> create(){
        return new TreeSet<E>();
    }
} // TreeSetFactory
