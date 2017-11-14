package question4;
import java.io.IOException;
import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question2.*;
import question3.*;
import static question2.Main.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.ByteArrayOutputStream;

public class IHM extends JFrame implements ActionListener
{
    private JTextArea  aTResultat = new JTextArea( "", 7, 60 );
    private JButton    aBDebiter  = new JButton( "débiter"   );
    private JButton    aBCrediter = new JButton( "créditer"  );
    private JTextField aTSomme    = new JTextField( 4 );

    private GroupeDeContributeurs g;

    public IHM()
    {
        this.setTitle( "Cotisant" );
        Container vContainer = this.getContentPane();
        this.aTSomme.setText( "40" );
        vContainer.setLayout( new BorderLayout() );

        vContainer.add( aTResultat, BorderLayout.NORTH );
        JPanel vP = new JPanel( new FlowLayout() );
        vP.add( this.aTSomme );
        vP.add( this.aBDebiter  );
        vP.add( this.aBCrediter );
        vContainer.add( vP, BorderLayout.SOUTH );

        this.g = new GroupeDeContributeurs( "g" );
        this.g.ajouter( new Contributeur( "g_a", 100 ) );
        this.g.ajouter( new Contributeur( "g_b",  50 ) );
        this.g.ajouter( new Contributeur( "g_c", 150 ) );
        GroupeDeContributeurs vG1 = new GroupeDeContributeurs( "g1" );
        vG1.ajouter( new Contributeur( "g1_a1",  70 ) );
        vG1.ajouter( new Contributeur( "g1_b1", 200 ) );
        this.g.ajouter( vG1 );

        try {
            this.aTResultat.setText( Main.arbreXML( this.g ) ); //actualiser();
        } catch( final Exception pE ) {
             System.err.println( pE );
        }
        
        this.aBDebiter.addActionListener( this );
        this.aBCrediter.addActionListener( this );

        this.pack();
        this.setVisible( true );
    } // IHM()

    public static void main() // ce n'est pas la veritable methode main !
    {
        new IHM();    
    } // main()
    
        public void actionPerformed(ActionEvent e) {
        if(e.getSource() == aBDebiter){
            int somme = Integer.parseInt(aTSomme.getText());
            AbstractTransaction transaction = new TransactionDebit(g);
            try{
                transaction.debit(somme);
                this.aTResultat.setText(Main.arbreXML(g));
            }
            catch(Exception sde){
                //nothing to do
            }
        }
        else if(e.getSource() == aBCrediter){
            int somme = Integer.parseInt(aTSomme.getText());
            g.credit(somme);
            try{
                this.aTResultat.setText(Main.arbreXML(g));
            }
            catch(Exception sde){
                //nothing to do
            }
        }
    }
} // IHM
