package question3;

import question1.*;
import java.util.*;

public class Memento
{
    // Note : Un usage du patron Memento, 
    //        d’un premier visiteur pour la sauvegarde et 
    //        d’un second pour la restitution du composite, 
    //        representent une solution possible. 
    private Map<Cotisant, Integer> save;
    
    public Memento( final Cotisant pC )
    {
        save = new HashMap<Cotisant,Integer>();
        setMap(pC);
    } // Memento(.)

    public void setState( final Cotisant pC )
    {
        if( pC instanceof GroupeDeContributeurs ){
            GroupeDeContributeurs p = (GroupeDeContributeurs) pC;
            for(Cotisant c: p.getChildren()) setState(c);
        }
        else {
            Contributeur c = (Contributeur) pC;
            c.affecterSolde(save.get(pC));
        }
    } // setState(.)
    
    public void setMap( final Cotisant pC ){
        if( pC instanceof GroupeDeContributeurs ){
            GroupeDeContributeurs p = (GroupeDeContributeurs) pC;
            for(Cotisant c: p.getChildren()) setMap(c);
        }
        else save.put(pC, pC.solde());
    }
} // Memento