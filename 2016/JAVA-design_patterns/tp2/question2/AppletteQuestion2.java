package question2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.*;
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
        ActionListener jbo1 = (new JButtonObserver("jbo1", contenu));
        ActionListener jbo2 = (new JButtonObserver("jbo2", contenu));
        ActionListener jbo3 = (new JButtonObserver("jbo3", contenu));
        MouseListener jmo1 = new JMouseObserver("jmo1", contenu);
        MouseListener jmo2 = new JMouseObserver("jmo2", contenu);
        MouseListener jmo3 = new JMouseObserver("jmo3", contenu);
        
        boutonA.addActionListener(jbo1);
        boutonA.addActionListener(jbo2);
        boutonA.addActionListener(jbo3);
        
        // le bouton A a 3 observateurs jbo1, jbo2 et jbo3
        boutonB.addActionListener(jbo1);
        boutonB.addActionListener(jbo2);
        
        // le bouton B a 2 observateurs jbo1 et jbo2
        boutonC.addActionListener(jbo1);
        
        // le bouton C a 1 observateur  jbo1

        if ( testSouris ) {
            // le bouton A a 1 observateur  jmo1
            boutonA.addMouseListener(jmo1);
            // le bouton B a 1 observateur  jmo2
            boutonB.addMouseListener(jmo2);
            // le bouton C a 1 observateur  jmo3
            boutonC.addMouseListener(jmo3);
        }
    } // init()

} // AppletteQuestion2
