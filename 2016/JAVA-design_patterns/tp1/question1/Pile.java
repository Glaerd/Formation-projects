package question1;

/**
 * classe Pile d'entiers
 * 
 * @author JMD/DB 
 * @version v2010
 */
public class Pile
{
  public final static int CAPACITE_PAR_DEFAUT = 6;
  
  private Object[] zone;
  private int ptr;
  

  public Pile(int taille)
  {
    if (taille<0) taille = CAPACITE_PAR_DEFAUT;
    this.zone = new Object[taille];
    this.ptr = 0;
  } // Pile(.)
  

  public Pile()
  {
    this(CAPACITE_PAR_DEFAUT);
  } // Pile()
  
  public void empiler(Object o) throws PilePleineException
  { 
    if (estPleine()) throw new PilePleineException();
    this.zone[this.ptr] = o;
    this.ptr++;
  } // empiler(.)

  public Object depiler() throws PileVideException
  {   
    if (estVide()) throw new PileVideException();
    this.ptr--;
    return zone[ptr];
  } // depiler()
 
  public boolean estVide()
  {
    return ptr==0;
  } // estVide()

  public boolean estPleine()
  {
    return ptr==zone.length;
  } // estPleine()

  public String toString()
  {
    StringBuffer sb = new StringBuffer("[");
    for (int i=ptr-1;i>=0;i--) {
      sb.append(zone[i].toString());
      if (i >0) sb.append(", ");
    } // for
    sb.append("]");
    return sb.toString();
  } // toString()
} // Pile
