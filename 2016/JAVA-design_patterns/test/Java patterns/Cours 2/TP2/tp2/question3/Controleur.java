package question3;

import question3.tp_pile.PileI;
import question3.tp_pile.PilePleineException;
import question3.tp_pile.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * La classe Controleur écoute PileModele<T> et provoque les modifications d'affichage sur la Vue
 * 
 * @author SILVA
 * @version 22/09/2015
 */
public class Controleur extends JPanel
{
  private JButton push, add, sub, mul, div, clear;
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
    donnee.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
        
        }
        });
    JPanel boutons= new JPanel();
    boutons.setLayout( new FlowLayout() );
    
    // PUSH
    boutons.add( push );
    push.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            try{
            pile.empiler(operande());
        }
        catch(NumberFormatException nfe){    

        }
        catch(PilePleineException ppe){

        }
         actualiserInterface();  // Actualiser les boutons de l'interface après l'action
        }
        });
        
        // ADD
    boutons.add( add );
    add.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            try{
           pile.empiler(pile.depiler()+pile.depiler());
        }
        catch(Exception e){

        }
        actualiserInterface();
        }
        });
        
        // SUB
    boutons.add( sub );
    sub.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            try{
               int val1=pile.depiler();
               int val2=pile.depiler();
               pile.empiler(val2-val1);
        }
        catch(Exception e){
        }
        actualiserInterface();  // Actualiser les boutons de l'interface après l'action
        }
        });
        
        // MUL 
    boutons.add( mul );
    mul.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            try{
               pile.empiler(pile.depiler()*pile.depiler());
        }
        catch(Exception e){

        }
        actualiserInterface();
        }
        });
        // DIV 
    boutons.add( div );
    div.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            try{
               int val1=pile.depiler();
               int val2=pile.depiler();
               if(val1 != 0){   // Si division possible on la réalise
               pile.empiler(val2/val1);
            }
            else{   // Sinon on restaure la pile
                pile.empiler(val2);
                pile.empiler(val1);
            }
        }
        catch(Exception e){

        }
        actualiserInterface();      // Actualiser les boutons de l'interface après l'action
        }
        });
        // CLEAR  
    boutons.add( clear );
    clear.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
           try{
            while(!pile.estVide()){
                pile.depiler();  
            }
        }
        catch(Exception e){

        }
        actualiserInterface();
        }
        });         
    add( boutons );
    boutons.setBackground( Color.red );
    actualiserInterface();  
  } // Controleur()

  /*
   * Permet de vérifier l'état de la pile afin de rendre actif ou non un bouton au niveau de l'affichage
   * Si pile avec un élément les boutons seront désactivés
   */
  public void actualiserInterface()
  {
      if(pile.taille() < 2){
            sub.setEnabled(false);
            add.setEnabled(false);
            div.setEnabled(false);
            mul.setEnabled(false);
            if(pile.taille() == 0){
                clear.setEnabled(false);
            }
            else{
                clear.setEnabled(true);
            }
        }
        else{
          sub.setEnabled(true);
          add.setEnabled(true);
          div.setEnabled(true);
          mul.setEnabled(true);
          clear.setEnabled(true);
        }
  } // actualiserInterface()

  private Integer operande() throws NumberFormatException
  {
    return Integer.parseInt( donnee.getText() );
  } // operande()

  // a completer
  // en cas d'exception comme division par zero, mauvais format de nombre
  // la pile doit rester en l'etat (intacte)

} // Controleur
