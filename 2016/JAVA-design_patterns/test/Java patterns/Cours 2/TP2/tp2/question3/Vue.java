package question3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Observable;
import java.util.Observer;

/**
 * La classe Vue surveille la pile en attendnat une notification de changement
 * 
 * @author SILVA 
 * @version 22/09/2015
 */
public class Vue extends JPanel implements Observer
{
  private JLabel etatPile;
  private PileModele<Integer> pile;

  public Vue( PileModele<Integer> pile )
  {
    super();
    this.pile= pile;
    this.etatPile= new JLabel( "entrez des nombres entiers" );
    setLayout( new FlowLayout( FlowLayout.LEFT ) );
    add( etatPile );
    setBackground( Color.green );
    // inscription aupres du modele comme observateur
    pile.addObserver(this);
  } // Vue()

  public void update( Observable obs, Object arg )
  {
    etatPile.setText( pile.toString() ); // ou obs.toString()
  } // update()

} // Vue
