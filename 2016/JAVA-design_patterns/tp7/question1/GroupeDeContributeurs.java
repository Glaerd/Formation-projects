package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>
{
    private List<Cotisant> aListe;

    public GroupeDeContributeurs( final String pNomDuGroupe )
    {
        super( pNomDuGroupe );
        aListe = new ArrayList<>();
    } // GroupeDeContributeurs(.)

    public void ajouter( final Cotisant pCotisant )
    {
        aListe.add(pCotisant);
        pCotisant.setParent(this);
    } // ajouter(.)

    public int nombreDeCotisants()
    {
        int vNombre = 0;
        for(Cotisant c:aListe){
            vNombre += c.nombreDeCotisants();
        }
        return vNombre; // pourquoi n'est-ce pas this.aListe.size() ?
    } // nombreDeCotisants()

    @Override public String toString()
    {
        String vStr = "{";
        for(Cotisant c:aListe){
            vStr += c.toString() + " - ";
        }
        vStr = vStr.substring(0, vStr.length()-1);
        vStr += "}";
        return vStr;
    } // toString()

    public List<Cotisant> getChildren()
    {   
        List<Cotisant> l = new ArrayList(aListe);
        return l; // a completer
        // pourquoi n'est-ce pas return this.aListe; ?
    } // getChildren()

    public void debit( final int pSomme ) throws SoldeDebiteurException
    {
        try{
            Iterator<Cotisant> it = aListe.iterator();
            while(it.hasNext()){
              Cotisant c = it.next();
              c.debit(pSomme);
            }
        }   
        catch(SoldeDebiteurException sde){
            throw sde;
        }
    } // debit(.)

    public void credit( final int pSomme )
    {
        Iterator<Cotisant> it = aListe.iterator();
        while(it.hasNext()){
            Cotisant c = it.next();
            c.credit(pSomme);
        }
    } // credit(.)

    public int solde()
    {
        int vSolde = 0;
        for(Cotisant c:aListe){
            vSolde += c.solde();
        }
        return vSolde;
    } // solde()

    // méthodes fournies

    public Iterator<Cotisant> iterator()
    {
        return new GroupeIterator( this.aListe.iterator() );
    } // iterator()

    private class GroupeIterator implements Iterator<Cotisant>
    {
        private Stack<Iterator<Cotisant>> aStk;

        public GroupeIterator( final Iterator<Cotisant> pIterator )
        {
            this.aStk = new Stack<Iterator<Cotisant>>();
            this.aStk.push( pIterator );
        } // GroupeIterator(.)

        @Override public boolean hasNext()
        {
            if ( this.aStk.empty() )
                return false;

            Iterator<Cotisant> vIterator = this.aStk.peek();
            if( ! vIterator.hasNext() ) {
                this.aStk.pop();
                return this.hasNext();
            } else {
                return true;
            }
        } // hasNext()

       @Override public Cotisant next()
       {
            if ( this.hasNext() ) {
                Iterator<Cotisant> vIterator = this.aStk.peek();
                Cotisant vCpt = vIterator.next();
                if ( vCpt instanceof GroupeDeContributeurs ) {
                    GroupeDeContributeurs vGr = (GroupeDeContributeurs)vCpt;
                    this.aStk.push( vGr.aListe.iterator() );
                }
                return vCpt;
            } else {
                throw new NoSuchElementException();
            }
       } // next()

        @Override public void remove()
        {
            throw new UnsupportedOperationException();
        } // remove()
    } // GroupeIterator

    @Override public <T> T accepter( final Visiteur<T> pVisiteur )
    {
        return pVisiteur.visite( this );
    }
} // GroupeDeContributeurs
