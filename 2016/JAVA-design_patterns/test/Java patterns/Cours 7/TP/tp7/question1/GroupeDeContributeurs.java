package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
    liste = new ArrayList<>();
  }
  
  public void ajouter(Cotisant cotisant){
      liste.add(cotisant);
  }
  
  
  public int nombreDeCotisants(){
    int nombre = 0;
    for(Cotisant c:liste){
        c.nombreDeCotisants();
    }
    return nombre;
    // Membres du groupe de cotisants
  }
  
  public String toString(){
    String str = new String();
    for(int i = 0; liste.get(i).nom() != null;i++){
        str = str + liste.get(i).nom(); 
    }
    return str;
  }
  
  public List<Cotisant> getChildren(){
      return liste;
  }
  
  public void debit(int somme) throws SoldeDebiteurException{
      try{
          Iterator<Cotisant> it = new liste.iterator();
          while(it.hasNext()){
              Cotisant c = it.next();
              c.debit(somme);
          }
      }
      catch(SoldeDebiteurException sde){
          
      }
  }
  
  public void credit(int somme){
    // a completer
  }
  
  public int solde(){
    int solde = 0;
    // a completer
    return solde;
  }
  
  // méthodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  

}
