package question3;

import question3.tp_pile.*;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

/**
 * Classe VueApplette - decrivez la classe ici
 * 
 * @author:  SILVA
 * @version: 22/09/2015
 */
public class AppletteCalculette extends JApplet
{

  public void init()
  {
    JRootPane rootPane= this.getRootPane();
    rootPane.putClientProperty( "defeatSystemEventQueueCheck", Boolean.TRUE );

    PileModele<Integer> modele= new PileModele<Integer>( new Pile2<Integer>( 5 ) );
    Controleur controle= new Controleur( modele );
    Vue vue= new Vue( modele );

    setLayout( new GridLayout( 2, 1 ) );
    add( vue );
    add( controle );
    setVisible( true );

  }

}
