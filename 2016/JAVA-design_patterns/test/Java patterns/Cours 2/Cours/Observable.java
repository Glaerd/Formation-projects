import java.util.*;
/**
 * Write a description of class Observable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Observable
{
    private List<Observateur> observateurs;
    public Observable(){
        this.observateurs = new ArrayList<Observateur>();
    }
    public void ajouter(Observateur obs){
        observateurs.add(obs);
    }
    public void retirer(Observateur obs){
        observateurs.remove(obs);
    }
    public void notifier(){
        for(Observateur o : observateurs){
            o.miseAJour();
        }
    }
}
