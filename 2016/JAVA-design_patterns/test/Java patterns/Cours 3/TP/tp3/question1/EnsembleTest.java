package question1;

public class EnsembleTest extends junit.framework.TestCase{
    // Definissez ici les variables d'instance necessaires a vos engagements;
    // Vous pouvez egalement les saisir automatiquement du presentoir
    // a l'aide du menu contextuel "Presentoir --> Engagements".
    // Notez cependant que ce dernier ne peut saisir les objets primitifs
    // du presentoir (les objets sans constructeur, comme int, float, etc.).

    /**
     * Il ne vous reste plus qu'a definir une ou plusieurs methodes de test.
     * Ces methodes doivent verifier les resultats attendus a l'aide d'assertions
     * assertTrue(<boolean>).
     * Par convention, leurs noms devraient debuter par "test".
     * Vous pouvez ebaucher le corps grâce au menu contextuel "Enregistrer une methode de test".
     */

    public void testAdd(){
        // fail( "testAdd() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        question1.Ensemble<Integer> e1;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(3));
    }
       
    public void testUnion(){
        // fail( "testUnion() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite

        question1.Ensemble<Integer> e1,e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));  assertEquals(true, e1.add(3));
        
        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));  assertEquals(true, e2.add(4));
     
        question1.Ensemble<Integer> union = e1.union(e2);
        assertEquals(3, union.size());
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertTrue(union.contains(4));
    }
    
    public void testInter(){
        // fail( "testInter() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        question1.Ensemble<Integer> e1,e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));  assertEquals(true, e1.add(3));
        
        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));  assertEquals(true, e2.add(4));
        
        question1.Ensemble<Integer> inter = e1.inter(e2);
        assertEquals(1, inter.size());
        assertTrue(inter.contains(3));
    }
    
    public void testDiff(){
        // fail( "testDiff() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        question1.Ensemble<Integer> e1,e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(1));assertEquals(true, e1.add(2)); assertEquals(true, e1.add(3));assertEquals(true, e1.add(5));
        
        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(2)); assertEquals(true, e2.add(3));  assertEquals(true, e2.add(4));
        
        question1.Ensemble<Integer> diff = e1.diff(e2);
        assertEquals(2, diff.size());
        assertTrue(diff.contains(1));
        assertTrue(diff.contains(5));
    }
    
    public void testDiffSym(){
        // fail( "testDiffSym() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        question1.Ensemble<Integer> e1,e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(1));assertEquals(true, e1.add(2)); assertEquals(true, e1.add(3));assertEquals(true, e1.add(5));
        
        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(2)); assertEquals(true, e2.add(3));  assertEquals(true, e2.add(4));assertEquals(true, e2.add(7));assertEquals(true, e2.add(8));
        
        question1.Ensemble<Integer> diffSym = e1.diffSym(e2);
        assertEquals(5, diffSym.size());
        assertTrue(diffSym.contains(1));
        assertTrue(diffSym.contains(5));
        assertTrue(diffSym.contains(4));
        assertTrue(diffSym.contains(7));
        assertTrue(diffSym.contains(8));
    }
 
    /**
     * Met en place les engagements.
     *
     * Methode appelee avant chaque appel de methode de test.
     */
    protected void setUp() // throws java.lang.Exception
    {
        // Initialisez ici vos engagements
    }

    /**
     * Supprime les engagements
     *
     * Methode appelee après chaque appel de methode de test.
     */
    protected void tearDown() // throws java.lang.Exception
    {
        //Liberez ici les ressources engagees par setUp()
    }

}
