package question2;

import question1.Contributeur;
import question1.Cotisant;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class CompositeValide implements Visiteur<Boolean>
{
  // Le solde de chaque contributeur doit etre superieur ou egal a 0 
  // et il n’existe pas de groupe n’ayant pas de contributeurs.
  
  @Override public Boolean visite( final Contributeur pC )
  {
    return (pC.solde() >= 0); // a completer;
  } // visite(.)
  
  @Override public Boolean visite( final GroupeDeContributeurs pG )
  {
    boolean vRes = true; // a completer
    if(pG.nombreDeCotisants() != 0) {
        for(Cotisant c:pG.getChildren()){
            boolean vTemp = c.accepter(this);
            if(c.solde() < 0) vRes = vTemp;
        }
    }
    else 
        vRes = false;
    return vRes;
  } // visite(.)
} // CompositeValide