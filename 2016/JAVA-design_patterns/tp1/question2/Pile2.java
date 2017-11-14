package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;
import java.util.Iterator;

public class Pile2 implements PileI, Cloneable{
    /** par delegation : utilisation de la class Stack */
    private Stack<Object> stk;

    /** la capacite de la pile */
    private int   capacite;

    /** Creation d'une pile.
     * @param taille la taille de la pile, la taille doit etre > 0
     */
    public Pile2(int taille){
        if(taille > 0) {
            this.capacite = taille;
            this.stk = new Stack<Object>();
        }
        else System.out.println("Erreur taille");
    }

    // constructeur fourni
    public Pile2(){
        this(0);
    }

    public void empiler(Object o) throws PilePleineException{
        try{
            if(this.estPleine()) throw new PilePleineException();
            else stk.push(o);
        }
        catch (PilePleineException e) {
            System.out.println("pile pleine");
        }
    }

    public Object depiler() throws PileVideException{
        try {
            if(this.estVide()) throw new PileVideException();
            else return stk.pop();
        }
        catch (PileVideException e) {
            System.out.println("pile vide");
            return null;
        }
    }

    public Object sommet() throws PileVideException{
        try {
            if(this.estVide()) throw new PileVideException();
            else return stk.peek();
        }
        catch (PileVideException e) {
            System.out.println("pile vide");
            return null;
        }
    }

    /** Effectue un test de l'etat de la pile.
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide(){
        return stk.empty();
    }

    /** Effectue un test de l'etat de la pile.
     * @return vrai si la pile est pleine, faux autrement
     */   
    public boolean estPleine(){
        if(this.taille() >= this.capacite()) return true;
        else return false;
    }

    /** Retourne une representation en String d'une pile, 
     * contenant la representation en String de chaque element.
     * @return une representation en String d'une pile
     */ 
    public String toString(){
        String s = "[";
        Iterator iterator = stk.iterator();
        while(iterator.hasNext()){
            s = s + iterator.next().toString();
            s = s + ",";
        }
        return s + "]";
    }

    public boolean equals(Object o){
        if(this == o) return true;
        else if(o == null) return false;
        else if(o.getClass() != this.getClass()) return false;
        else if(o.getClass() == this.getClass()) {
            Pile2 p2 = (Pile2) o;
            if(p2.taille() != this.taille()) return false;
        }
        Iterator iterator = stk.iterator();
        Pile2 p2 = (Pile2) o;
        Iterator it = p2.getStack().iterator();
        while(iterator.hasNext()){
            if(it.next() != iterator.next()) return false;
        }
        return true;
    }

    public Stack<Object> getStack() {
        return this.stk;
    }
    
    // fonction fournie
    public int hashCode(){
        return toString().hashCode();
    }

    // fonction fournie
    public Object clone() throws CloneNotSupportedException{
        Pile2 p = new Pile2(this.capacite());
        p.stk = (Stack<Object>)stk.clone();
        return p;
    }

    /** Retourne le nombre d'element d'une pile.
     * @return le nombre d'element present
     */ 
    public int taille(){
        return stk.size();
    }

    /** Retourne la capacite de cette pile.
     * @return le nombre d'element possible
     */ 
    public int capacite(){
        return this.capacite;
    }

} // Pile2.java
