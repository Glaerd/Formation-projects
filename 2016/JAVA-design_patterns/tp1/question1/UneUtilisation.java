package question1;

public class UneUtilisation {

  public static void main(String[] args) throws Exception {
    Pile p1 = new Pile(6);
    Pile p2 = new Pile(10);
    // p1 est ici une pile de polygones reguliers PolygoneRegulier.java
    
    p1.empiler(new PolygoneRegulier(5,100));
    p1.empiler("polygone");
    p1.empiler(new Integer(100));
    System.out.println(" la pile p1 = " + p1); // Quel est le resultat ?
                                          

    try {
      p1.empiler(new PolygoneRegulier(5,100));
      // ....
      Boolean b = (Boolean)p1.depiler(); // verifiez qu'une exception se produit
    } catch (Exception e ) {
      e.printStackTrace();
    } // catch
  } // main()
} // UneUtilisation
