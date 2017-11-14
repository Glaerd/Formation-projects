package question3;

import question1.PilePleineException;
import question1.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Classe ApplettePile - d�crivez la classe ici
 * 
 * @author:  (votre nom)
 * @version: (un num�ro de version ou une date)
 */
public class ApplettePile extends JApplet implements ActionListener{
	// variables d'instance - remplacez cet exemple par le v�tre
  private JTextField donnee = new JTextField(6);
  private JTextField sommet = new JTextField(6);
  private JLabel     contenu = new JLabel("[]");
  
  private PileI<String> p;

	 /**
	 * Appel�e par le navigateur ou le visualiseur afin de signaler � l'Applet
	 * qu'il est maintenant pris en charge par le syst�me.
	 * Il est garanti que ceci pr�c�dera le premier appel de la m�thode start.
	 */
	public void init()
	{
		// Il y a un conflit de s�curit� avec les navigateurs courants (incluant
		// Netscape & Internet Explorer) qui interdisent l'acc�s � la queue 
		// d'�v�nements d'AWT --ce dont les JApplets ont besoin au d�marrage.
		JRootPane rootPane = this.getRootPane();	
		rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);

		JButton    boutonEmpiler = new JButton("empiler");
    JButton    boutonDepiler = new JButton("d�piler");

    JPanel enHaut = new JPanel();
    enHaut.add(donnee);
    enHaut.add(boutonEmpiler);
    enHaut.add(boutonDepiler);
    enHaut.add(sommet);
    setLayout(new BorderLayout(5,5));
    add("North",enHaut);
    add("Center",contenu);
    enHaut.setBackground(Color.magenta);
    boutonEmpiler.addActionListener(this);
    boutonDepiler.addActionListener(this);
    
    try{
      p = new Pile2<String>(Integer.parseInt(getParameter("TAILLE")));
    }catch(Exception e){
      p = new Pile2(5);
    }
	}
	
	
 public void actionPerformed(ActionEvent ae){
    if(ae.getActionCommand().equals("empiler")){
      try{
        p.empiler(donnee.getText());
        contenu.setText(p.toString());
      }catch(PilePleineException e){
        contenu.setText(p.toString() + " estPleine !");
      }
    }else{
      try{
	      sommet.setText(p.depiler());
        contenu.setText(p.toString());	      
	    }catch(PileVideException e){
	      contenu.setText(p.toString() + " estVide !");
	    }
    }
  }
}


