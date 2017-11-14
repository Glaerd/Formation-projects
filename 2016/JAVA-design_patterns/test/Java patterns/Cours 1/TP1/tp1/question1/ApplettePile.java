package question1;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * A remplacer par votre classe ApplettePile .
 * 
 * @author:  (votre nom)
 * @version: (un numero de version ou une date)
 */
public class ApplettePile extends JApplet implements ActionListener
{
        // variables d'instance - remplacez cet exemple par le v�tre
      private JTextField donnee = new JTextField(6);
      private JTextField sommet = new JTextField(6);
      private JLabel     contenu = new JLabel("[]");
      
      private Pile p;

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
            JRootPane rootPane = this.getRootPane();    
            rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
    
            JButton    boutonEmpiler = new JButton("empiler");
        JButton    boutonDepiler = new JButton("depiler");
    
        JPanel enHaut = new JPanel();
        enHaut.add(donnee);
        enHaut.add(boutonEmpiler);
        enHaut.add(boutonDepiler);
        enHaut.add(sommet);
        setLayout(new BorderLayout(5,5));
        add("North",enHaut);
        add("Center",contenu);
        enHaut.setBackground(Color.red);
        boutonEmpiler.addActionListener(this);
        boutonDepiler.addActionListener(this);
        
        try{
          p = new Pile(Integer.parseInt(getParameter("TAILLE")));
        }catch(Exception e){
          p = new Pile();
        }
    }
        
        
     public void actionPerformed(ActionEvent ae){
        String depileString;
         try{
             if(ae.getActionCommand().equals("empiler")){
                p.empiler(donnee.getText());
            }
            else{
                depileString = (String) p.depiler();
                sommet.setText(depileString);
          }   
          contenu.setText(p.toString());
        }
        
        catch (PilePleineException ppe){
          contenu.setText(p.toString()+"C'est plein");
        }
        catch (PileVideException pve){
            contenu.setText(p.toString()+"[] Est Vide !");
        }
    }
}
