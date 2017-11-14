package question3;

import question3.tp_pile.PileI;
import question3.tp_pile.PilePleineException;
import question3.tp_pile.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Decrivez votre classe Controleur ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Controleur extends JPanel implements ActionListener
{
    public JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur( PileModele<Integer> pile )
    {
        super();
        this.pile= pile;
        this.donnee= new JTextField( 8 );
        this.push= new JButton( "push" );
        this.add= new JButton( "+" );
        this.sub= new JButton( "-" );
        this.mul= new JButton( "*" );
        this.div= new JButton( "/" );
        this.clear= new JButton( "[]" );

        setLayout( new GridLayout( 2, 1 ) );
        add( donnee );
        donnee.addActionListener( this /* null est a remplacer */);
        JPanel boutons= new JPanel();
        boutons.setLayout( new FlowLayout() );
        boutons.add( push );
        push.addActionListener( this /* null est a remplacer */);
        boutons.add( add );
        add.addActionListener( this /* null est a remplacer */);
        boutons.add( sub );
        sub.addActionListener( this /* null est a remplacer */);
        boutons.add( mul );
        mul.addActionListener( this /* null est a remplacer */);
        boutons.add( div );
        div.addActionListener( this /* null est a remplacer */);
        boutons.add( clear );
        clear.addActionListener( this /* null est a remplacer */);
        add( boutons );
        boutons.setBackground( Color.red );
        actualiserInterface();
    } // Controleur()

    public void actualiserInterface()
    {
        // a completer

    } // actualiserInterface()

    private Integer operande() throws NumberFormatException
    {
        return Integer.parseInt( donnee.getText() );
    } // operande()

    // a completer
    public void actionPerformed(ActionEvent ae){
        Object buttonPressed = ae.getSource();
        try{
            if(buttonPressed == push){
                pile.empiler(operande());
            }
            else if(buttonPressed == add){
                if(pile.taille() > 1) pile.empiler(pile.depiler() + pile.depiler());
            }
            else if(buttonPressed == sub){
                if(pile.taille() > 1) pile.empiler(-pile.depiler() + pile.depiler());
            }
            else if(buttonPressed == mul){
                if(pile.taille() > 1) pile.empiler(pile.depiler()*pile.depiler());
            }
            else if(buttonPressed == div){
                if(pile.taille() > 1) {
                    Integer p1 = pile.depiler();
                    Integer p2 = pile.depiler();
                    if(p1 != 0) pile.empiler(p2/p1);
                    else{
                        pile.empiler(p2);
                        pile.empiler(p1);
                    }
                }
            }
            else if(buttonPressed == clear){
                int j = pile.taille();
                for(int i = 0; i < j; i++){
                    pile.depiler();
                }
            }
            else{
                throw new RuntimeException("Bouton inconnu");
            }
        }
        catch(Exception e){
            
        }
    }
    // en cas d'exception comme division par zero, mauvais format de nombre
    // la pile doit rester en l'etat (intacte)

} // Controleur
