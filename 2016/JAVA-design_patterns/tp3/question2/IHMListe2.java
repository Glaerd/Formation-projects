package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
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

    private CheckboxGroup   mode = new CheckboxGroup();
    private Checkbox        ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox        ordreDecroissant = new Checkbox("decroissant", mode, false);

    private JButton         boutonOccurrences = new JButton("occurrence");
    private JButton         boutonAnnuler= new JButton("annuler");

    private JTextArea       texte = new JTextArea();
    private JScrollPane     textePane = new JScrollPane( texte );

    private List<String>    liste;
    private Map<String,Integer>   occurrences;
    private GestionAnnulation gestAnnul;

    public IHMListe2(List<String> liste, Map<String,Integer> occurrences)
    {
        this.liste = liste;
        this.occurrences = occurrences;
        this.gestAnnul = new GestionAnnulation();

        cmd.setLayout(new GridLayout(3,1));

        cmd.add(afficheur);cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
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
        boutonOccurrences.addActionListener( this );
        boutonRetirer.addActionListener( this );
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);
        boutonAnnuler.addActionListener(gestAnnul);

    } // IHMListe2(..)

    private class GestionAnnulation implements ActionListener
    {
        // a completer
        private Stack<List<String>> pile;
        public GestionAnnulation()
        {
            pile = new Stack<List<String>>();
        } // GestionAnnulation()

        public void sauvegarder()
        {
            pile.push(new LinkedList<String>(liste));
        } // sauvegarder()

        public void actionPerformed( ActionEvent ae )
        {
            if(ae.getSource() == boutonAnnuler)
            {        
                if(!pile.empty()){
                    liste = pile.pop();
                    occurrences.clear();
                    for(String str : liste){
                        Integer occur = occurrences.get(str);
                        if(occur == null) occur = 1;
                        else occur++;
                        occurrences.put(str, occur);
                    }
                }
            }
            texte.setText(liste.toString());
        } // actionPerformed(.)

    } // GestionAnnulation

    public void actionPerformed(ActionEvent ae)
    {
        try {
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
        }
        catch(Exception e) {
            afficheur.setText( e.toString());
        }
    } // actionPerformed(.)

    public void itemStateChanged(ItemEvent ie)
    {
        this.gestAnnul.sauvegarder();
        if ( ie.getSource() == ordreCroissant )
            Collections.sort(liste);
        else if ( ie.getSource() == ordreDecroissant )
        {
            Collections.sort(liste,new Comparator<String>()
            {
                @Override
                public int compare(String a, String b) {
                    return b.compareTo(a);
                }
            });
        }
        texte.setText( liste.toString() );
    } // itemStateChanged(.)

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe)
    {
        boolean resultat = false;
        Iterator<String> it = liste.iterator();
        while(it.hasNext()){
            String s = it.next();
            if(s.startsWith(prefixe)){
                if(!resultat) gestAnnul.sauvegarder();
                resultat = true;
                occurrences.put(s,occurrences.get(s)-1);
                it.remove();
            }
        }
        return resultat;
    } // retirerDeLaListeTousLesElementsCommencantPar(.)
} // IHMListe2