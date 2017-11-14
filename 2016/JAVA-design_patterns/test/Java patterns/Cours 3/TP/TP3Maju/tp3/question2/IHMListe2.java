package question2;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

import java.util.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class IHMListe2 extends JPanel implements ActionListener, ItemListener{
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
  private Map<String,Integer>  occurrences;
  private GestionAnnulation _annulationManager;

  public IHMListe2(List<String> liste, Map<String,Integer> occurrences){
    
    this.liste = liste;
    this.occurrences = occurrences;
    this._annulationManager = new GestionAnnulation();
     
    cmd.setLayout(new GridLayout(3,1));

    cmd.add(afficheur); cmd.add(saisie);
    
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
    
    boutonAnnuler.addActionListener(_annulationManager);
    boutonRechercher.addActionListener( this );
    boutonRetirer.addActionListener(this);
    ordreCroissant.addItemListener(this); // because it is a checkbox
    ordreDecroissant.addItemListener(this); 
    boutonOccurrences.addActionListener(this);
    saisie.addActionListener(this);
  }
  
  
  private class GestionAnnulation implements ActionListener{      
      private Stack<List> pile;
      
      public GestionAnnulation() {
          pile = new Stack<List>();
      } // GestionAnnulation()
      
      public void sauvegarder(){
          pile.push(new LinkedList(liste));        
      } // sauvegarder()
      
      public void actionPerformed(ActionEvent ae ){
         if(!pile.empty()){
              liste = pile.pop(); 
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
        afficheur.setText("resultat du retrait de tous les elements commencant par -->  " + saisie.getText() + " : " + res);
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

  
  public void itemStateChanged(ItemEvent ie ){
       
       this._annulationManager.sauvegarder();
       if ( ie.getSource() == ordreCroissant ){
            Map<String,Integer> sortedMap = new TreeMap<String,Integer>(occurrences);
            this.liste = sortList(sortedMap);
       }
       else if ( ie.getSource() == ordreDecroissant ){
            Map<String,Integer> sortedMap = new TreeMap<String,Integer>(occurrences);
            this.liste = sortList(sortedMap);
            Collections.reverse(this.liste);
       }
       texte.setText( liste.toString() );
  }
    
  public static List sortList(Map pMap) {     
        List tempList = new LinkedList(pMap.entrySet());
        List returnList = new LinkedList<String>();
        Collections.sort(tempList, new Comparator() {
            public int compare(Object o1, Object o2) {
                    return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
                }
        });    
          for(Iterator it = tempList.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            try{
                int temp = (int) entry.getValue();
                for(int i = 0; i < temp ; i++){
                    returnList.add(entry.getKey());
                }
            }catch(Exception pE){}      
        }
        return returnList;
  }   

  private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe){   
    boolean saved = false;
    boolean resultat = false;
    String tempString;
    Iterator<String> _it = liste.iterator();
    while(_it.hasNext()){
         tempString = _it.next();
         if(tempString.startsWith(prefixe)){
             if(occurrences.containsKey(tempString)){occurrences.remove(tempString); occurrences.put(tempString, 0); }
             if(!saved){ this._annulationManager.sauvegarder(); saved = true;}
             _it.remove();         
             resultat = true;
         }   
    }
    return resultat;     
  }    
}