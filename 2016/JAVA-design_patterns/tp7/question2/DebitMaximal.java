package question2;

import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class DebitMaximal implements Visiteur<Integer>
{
    @Override public Integer visite( final Contributeur pC )
    {
        //faire accepter pour visiter les enfants des contributeurs
        return pC.solde(); // a completer;
    } // visite(.)

    @Override public Integer visite( final GroupeDeContributeurs pG )
    {
        int vRes = (int) Float.POSITIVE_INFINITY; // a completer
        for(Cotisant c:pG.getChildren()){
            int child_solde = c.accepter(this);//faire accepter pour visiter les enfants des contributeurs
            if(child_solde < vRes) vRes = child_solde;
        }
        return vRes;
    } // visite(.)
} // DebitMaximal