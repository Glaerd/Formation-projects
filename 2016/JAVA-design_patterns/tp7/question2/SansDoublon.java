package question2;

import java.util.List;
import java.util.ArrayList;
import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class SansDoublon implements Visiteur<Boolean>
{ 
    @Override public Boolean visite( final Contributeur pC )
    {
        return true;
    } // visite(.)

    @Override public Boolean visite( final GroupeDeContributeurs pG )
    {
        Boolean vRes = true;
        List<Cotisant> l = new ArrayList();
        for(Cotisant c:pG.getChildren()){
            l.add(c);
            for(Cotisant d:l){
                if(d.nom() == c.nom()){
                    vRes = false;
                }
            }
        }
        return vRes;
    } // visite(.)
    /*
    public List<Cotisant> parcours(Cotisant c){
        List<Cotisant> l = new ArrayList();
        if(c.getChildren() == 0) return c;
        else{
            for(Cotisant d:c.getChildren()){
                l.addAll(parcours(d));
            }
            return l;
        }
    }*/
} // SansDoublon