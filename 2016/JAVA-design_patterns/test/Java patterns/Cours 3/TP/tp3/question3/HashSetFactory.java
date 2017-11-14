package question3;

import java.util.Set;
import java.util.HashSet;

public class HashSetFactory<E extends Comparable<E>> implements Factory<Set<E>>
{
    public Set<E> create(){
        return new HashSet<E>();
    }
}