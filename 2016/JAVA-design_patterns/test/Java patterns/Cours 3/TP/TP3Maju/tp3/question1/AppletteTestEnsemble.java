package question1;

import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class AppletteTestEnsemble extends JApplet
{
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JPanel panel1;
  private JLabel label1;
  private JTextField textField1;
  
  private JPanel panel2;
  private JLabel label2;
  private JTextField textField2;
  private JPanel panel3;
  
  private JLabel label4;
  private JPanel panel4;
  private JLabel label3;
  private JTextField textField3;
  
  private JButton button1;
  private JButton button2;
  private JButton button3;
  private JButton button4;
    
    public void init(){
        // Il y a un conflit de securite avec les navigateurs courants (incluant
        // Netscape & Internet Explorer) qui interdisent l'accès a la queue 
        // d'evenements d'AWT --ce dont les JApplets ont besoin au demarrage.
		JRootPane rootPane = this.getRootPane();	
		rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);

		initComponents ();
	} // init()

  private void initComponents ()
  { //GEN-BEGIN:initComponents
    setLayout (new GridLayout (4, 1, 2, 2));

    panel1 = new JPanel ();
    panel1.setLayout (new FlowLayout (0, 5, 5));
    panel1.setBackground (Color.lightGray);
    label1 = new JLabel ();
    label1.setBackground (Color.orange);
    label1.setText ("ensemble e1 :");
    panel1.add (label1);
    textField1 = new JTextField ();
    textField1.setColumns (52);
    textField1.setName ("saisieE1");
    panel1.add (textField1);
    add (panel1);

    panel2 = new JPanel ();
    panel2.setLayout (new FlowLayout (0, 5, 5));
    panel2.setBackground (Color.magenta);
    label2 = new JLabel ();
    label2.setText ("ensemble e2 :");
    panel2.add (label2);
    textField2 = new JTextField ();
    textField2.setColumns (52);
    panel2.add (textField2);
    add (panel2);

    panel3 = new JPanel ();
    panel3.setLayout (new FlowLayout (0, 5, 5));
    panel3.setBackground (Color.green);
    label4 = new JLabel ();
    label4.setText ("Operations e1 Op e2  :");
    label4.setName ("label4");
    panel3.add (label4);

    button1 = new JButton ("union");
    button1.setBackground (Color.red);
    button1.setName ("union");
    button1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          unionActionPerformed();
        }
    });
    

    panel3.add (button1);

    button2 = new JButton ("intersection");
    button2.setBackground (Color.yellow);
    button2.setName ("intersection");
    button2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          intersectionActionPerformed();
        }
    });

    panel3.add (button2);
    button3 = new JButton ("difference");
    button3.setBackground (Color.pink);
    button3.setActionCommand ("difference");
    button3.setName ("difference");
    button3.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          differenceActionPerformed();
        }
    });

    panel3.add (button3);
    button4 = new JButton ("diffSymetrique");
    button4.setBackground (Color.cyan);
    button4.setName ("diffSymetrique");
    button4.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          diffSymetriqueActionPerformed();
        }
    });

    panel3.add (button4);
    add (panel3);

    panel4 = new JPanel ();
    panel4.setLayout (new FlowLayout (0, 5, 5));
    label3 = new JLabel ();
    label3.setText ("Resultat");
    label3.setName ("Resultat");

    panel4.add (label3);

    textField3 = new JTextField ();
    textField3.setColumns (60);

    panel4.add (textField3);
    panel4.setBackground (Color.orange);

    add (panel4);

  }//GEN-END:initComponents
  
	private Ensemble<String> getSet(JTextField saisie){
	  Ensemble<String> e = new Ensemble<String>();
      StringTokenizer st = new StringTokenizer(saisie.getText()," ,.:/-;");
	  while(st.hasMoreTokens()){
	    e.add(st.nextToken());
	  }
	  return e;
  }

  // ne pas modifier les lignes suivantes :

  private void differenceActionPerformed () {//GEN-FIRST:event_differenceActionPerformed
  // Add your handling code here:
    Ensemble<String> e1 = getSet(textField1);
    Ensemble<String> e2 = getSet(textField2);
    textField3.setText(e1.diff(e2).toString());  
  }//GEN-LAST:event_differenceActionPerformed
  
  private void intersectionActionPerformed () {//GEN-FIRST:event_intersectionActionPerformed
  // Add your handling code here:
    Ensemble<String> e1 = getSet(textField1);
    Ensemble<String> e2 = getSet(textField2);
    textField3.setText((e1.inter(e2)).toString());
  }//GEN-LAST:event_intersectionActionPerformed
  
  private void unionActionPerformed () {//GEN-FIRST:event_unionActionPerformed
      Ensemble<String> e1 = getSet(textField1);
      Ensemble<String> e2 = getSet(textField2);
      textField3.setText(e1.union(e2).toString());
    }//GEN-LAST:event_unionActionPerformed
  
  private void diffSymetriqueActionPerformed () {//GEN-FIRST:event_button4ActionPerformed
  // Add your handling code here:
    Ensemble<String> e1 = getSet(textField1);
    Ensemble<String> e2 = getSet(textField2);
    textField3.setText(e1.diffSym(e2).toString());  
  }//GEN-LAST:event_button4ActionPerformed
}
