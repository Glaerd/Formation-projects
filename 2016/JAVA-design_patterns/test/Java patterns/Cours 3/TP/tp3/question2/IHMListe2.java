package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class IHMListe2 extends JPanel implements ActionListener, ItemListener
{
  private JPanel          cmd = new JPanel();
  private JLabel          afficheur = new JLabel();
  private JTextField      saisie = new JTextField();
  
  private JPanel          panelBoutons = new JPanel();
  private JButton         boutonRechercher = new JButton("rechercher");
  private JButton         boutonRetirer = new JButton("retirer");
    
  private CheckboxGroup  mode = new CheckboxGroup();
  private Checkbox       ordreCroissant = new Checkbox("croissant", mode, false);
  private Checkbox       ordreDecroissant = new Checkbox("decroissant", mode, false);

  private JButton         boutonOccurrences = new JButton("occurrence");
  private JButton         boutonAnnuler= new JButton("annuler");

  private JTextArea       texte = new JTextArea();
  private JScrollPane     textePane = new JScrollPane( texte );

  private List<String>   liste;
  private Map<String,Integer>   occurrences;
  private GestionAnnulation cancelmanager;

  public IHMListe2(List<String> liste, Map<String,Integer> occurrences){
    this.liste = liste;
    this.occurrences = occurrences;
    this.cancelmanager = new GestionAnnulation();
    
    cmd.setLayout(new GridLayout(3,1));

    cmd.add(afficheur);cmd.add(saisie);
    
    panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
    panelBoutons.add(boutonRechercher);panelBoutons.add(boutonRetirer);
    panelBoutons.add(new JLabel("tri du texte :"));
    panelBoutons.add(ordreCroissant);panelBoutons.add(ordreDecroissant);
    panelBoutons.add(boutonOccurrences);
    panelBoutons.add(boutonAnnuler);
    cmd.add(panelBoutons);
    
    afficheur.setText(liste.getClass().getName() + " et " + occurrences.getClass().getName());
    texte.setLineWrap( true );
    texte.setWrapStyleWord( true );
    texte.setText(liste.toString());

    setLayout(new BorderLayout());

    add(cmd,"North");
    add(textePane,"Center");
    
    boutonRechercher.addActionListener(this);
    // boutons a completer;
    saisie.addActionListener(this); // On ajoute un actionListener au JtextField pour détecter l'appui de la touche entrer 
    //du clavier (natif au JtextField)
    boutonRechercher.addActionListener(this);
    boutonRetirer.addActionListener(this);
    boutonOccurrences.addActionListener(this);
    ordreCroissant.addItemListener(this);
    ordreDecroissant.addItemListener(this);
    boutonAnnuler.addActionListener(cancelmanager);
  }
  
 
  private class GestionAnnulation implements ActionListener
  {
      private Stack<List> sauvegarde;
      public GestionAnnulation()
      {
          sauvegarde = new Stack<List>();
      } // GestionAnnulation()
      
      public void sauvegarder()
      {
          sauvegarde.push(new LinkedList(liste));
      } // sauvegarder()
      
      public void actionPerformed( ActionEvent ae )
      {
         if(!sauvegarde.empty()){
            liste = sauvegarde.pop(); 
            occurrences.clear();
              //Maj de la liste
              for(String s : liste){
                  Integer occur = occurrences.get(s);
                  if (occur == null) {
                      occur = 1;
                   }else{occur++; }
                  occurrences.put(s, occur);
              }
         }
         texte.setText(liste.toString());

      } // actionPerformed()
      
  } // GestionAnnulation
  
  
  public void actionPerformed(ActionEvent ae){
    try{
            boolean res=false;
      if (ae.getSource() == boutonRechercher || ae.getSource() == saisie){
          res = liste.contains(saisie.getText());
          Integer occur = occurrences.get(saisie.getText());
        afficheur.setText("resultat de la recherche de : " + saisie.getText() + " -->  "+ res);
      } else if (ae.getSource() == boutonRetirer){
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
        afficheur.setText("resultat du retrait de tous les elements commençant par -->  " + saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences){
                Integer occur = occurrences.get(saisie.getText());
        if(occur!=null)  
          afficheur.setText(" -->  " + occur + " occurrence(s)");
        else
          afficheur.setText(" -->  ??? ");
            }
          texte.setText(liste.toString());
      
     }catch(Exception e){
          afficheur.setText( e.toString());
     }
  }

  
  public void itemStateChanged(ItemEvent ie){
        this.cancelmanager.sauvegarder();
        if ( ie.getSource() == ordreCroissant )
            Collections.sort(liste);
        else if ( ie.getSource() == ordreDecroissant ){
            Collections.sort(liste, new Comparator<String>(){
                public int compare(String o1, String o2) {
                    return o2.compareTo(o1);
                }
            });
            // Et même plus simple : 
            // Collections.sort(liste, Collections.reverseOrder());
        }
        texte.setText( liste.toString() );
  }
  

  private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe){
      boolean resultat = false;
        if(occurrences.get(prefixe)>0){ // Si n'existe pas ou déjà retiré on ne fait rien
             this.cancelmanager.sauvegarder(); // on savegarde seulement si on supprime le mot
             while(!resultat){ // Tant qu'on a une occurrence du mot
                Integer res = occurrences.get(prefixe);
                if(res > 0){    // S'il reste des occurences, on supprime la suivante en actualisant la valeur de la hashMap
                    occurrences.put(prefixe,res-1);
                    liste.remove(prefixe);
                }
                else    resultat = true;    // Sinon on valide la suppression du mot
             }
            }
        return resultat;
  }
}