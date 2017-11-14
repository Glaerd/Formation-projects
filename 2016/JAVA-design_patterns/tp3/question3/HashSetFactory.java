package question3;

import java.util.Set;
import java.util.HashSet;


public class HashSetFactory<E extends Comparable<E>>/* a completer */ implements Factory<Set<E>>/* a completer */
{
    public Set<E> create(){
        return new HashSet<E>();
    }
} // HashSetFactory