package question2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRootPane;

/**
 * Classe AppletteQuestion2 - decrivez la classe ici
 * 
 * @author:  (votre nom)
 * @version: (un numero de version ou une date)
 */
public class AppletteQuestion2 extends JApplet
{

    private JButton boutonA= new JButton( "A" );
    private JButton boutonB= new JButton( "B" );
    private JButton boutonC= new JButton( "C" );
    private boolean testSouris= false;

    private TextArea contenu= new TextArea( 60, 80 );

    /**
     * Appelee par le navigateur ou le visualiseur afin de signaler a l'Applet
     * qu'il est maintenant pris en charge par le systeme.
     * Il est garanti que ceci precedera le premier appel de la methode start.
     */
    public void init()
    {
        // Il y a un conflit de securite avec les navigateurs courants (incluant
        // Netscape & Internet Explorer) qui interdisent l'acces a la queue 
        // d'evenements d'AWT --ce dont les JApplets ont besoin au demarrage.
        JRootPane rootPane= this.getRootPane();
        rootPane.putClientProperty( "defeatSystemEventQueueCheck", Boolean.TRUE );
        try {
            testSouris= getParameter( "mouse" ).equals( "yes" ); // A NE PAS OUBLIER !
        }
        catch ( Exception e ) {
            // contenu.setText( "Pas de parametre mouse : " + e ); // A COMMENTER POUR JNEWS
        }
        JPanel enHaut= new JPanel();
        enHaut.add( boutonA );
        enHaut.add( boutonB );
        enHaut.add( boutonC );
        setLayout( new BorderLayout( 5, 5 ) );
        add( "North", enHaut );
        add( "Center", contenu );
        if ( testSouris )
            enHaut.setBackground( Color.magenta );
        else
            enHaut.setBackground( Color.blue );
        // a completer
        
        JButtonObserver jbo1 = new JButtonObserver("jbo1",contenu);
        JButtonObserver jbo2 = new JButtonObserver("jbo2",contenu);
        JButtonObserver jbo3 = new JButtonObserver("jbo3",contenu);
        
        JMouseObserver jmo1 = new JMouseObserver("jmo1",contenu);
        JMouseObserver jmo2 = new JMouseObserver("jmo2",contenu);
        JMouseObserver jmo3 = new JMouseObserver("jmo3",contenu);
        
        // le bouton A a 3 observateurs jbo1 et jbo2 et jbo3
        boutonA.addActionListener(jbo1);
        boutonA.addActionListener(jbo2);
        boutonA.addActionListener(jbo3);
        
        // le bouton B a 2 observateurs jbo1 et jbo2
        boutonB.addActionListener(jbo1);
        boutonB.addActionListener(jbo2);
        // le bouton C a 1 observateur  jbo1
        boutonC.addActionListener(jbo1);
        
        if ( testSouris ) {
            boutonA.addMouseListener(jmo1);
            boutonB.addMouseListener(jmo2);
            boutonC.addMouseListener(jmo3);
        }
    } // init()

} // AppletteQuestion2
