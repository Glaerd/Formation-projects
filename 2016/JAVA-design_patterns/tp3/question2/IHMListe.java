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

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class IHMListe extends JPanel implements ActionListener, ItemListener
{
    private JPanel          cmd = new JPanel();
    private JLabel          afficheur = new JLabel();
    private JTextField      saisie = new JTextField();

    private JPanel          panelBoutons = new JPanel();
    private JButton         boutonRechercher = new JButton( "rechercher" );
    private JButton         boutonRetirer = new JButton( "retirer" );

    private CheckboxGroup  mode = new CheckboxGroup();
    private Checkbox       ordreCroissant = new Checkbox( "croissant", mode, false );
    private Checkbox       ordreDecroissant = new Checkbox( "decroissant", mode, false );

    private JButton         boutonOccurrences = new JButton( "occurrence" );

    private JTextArea       texte = new JTextArea();
    private JScrollPane     textePane = new JScrollPane( texte );

    private List<String>   liste;
    private Map<String,Integer>   occurrences;

    public IHMListe( List<String> liste, Map<String,Integer> occurrences )
    {
        this.liste = liste;
        this.occurrences = occurrences;

        cmd.setLayout( new GridLayout(3,1) );

        cmd.add( afficheur ); cmd.add( saisie );

        panelBoutons.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        panelBoutons.add( boutonRechercher ); panelBoutons.add( boutonRetirer );
        panelBoutons.add( new JLabel( "tri du texte :" ) );
        panelBoutons.add( ordreCroissant ); panelBoutons.add( ordreDecroissant );
        panelBoutons.add( boutonOccurrences );
        cmd.add( panelBoutons );

        afficheur.setText( liste.getClass().getName() + " et " + occurrences.getClass().getName() );
        texte.setLineWrap( true );
        texte.setWrapStyleWord( true );
        texte.setText( liste.toString() );

        setLayout( new BorderLayout() );

        add( cmd, "North" );
        add( textePane, "Center" );

        boutonRechercher.addActionListener( this );
        boutonOccurrences.addActionListener( this );
        boutonRetirer.addActionListener( this );
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);

    }

    /** ne pas modifier les affichages, les classes de tests en ont besoin ... */
    public void actionPerformed( ActionEvent ae )
    {
        try {
            boolean res=false;
            if ( ae.getSource() == boutonRechercher || ae.getSource() == saisie ) {
                res = liste.contains( saisie.getText() );
                Integer occur = occurrences.get( saisie.getText() );
                afficheur.setText( "resultat de la recherche de : " + saisie.getText() + " -->  "+ res );
            } else if ( ae.getSource() == boutonRetirer ) {
                res = retirerDeLaListeTousLesElementsCommencantPar( saisie.getText() );
                afficheur.setText( "resultat du retrait de tous les elements commenšant par -->  " + saisie.getText() + " : " + res );
            } else if ( ae.getSource() == boutonOccurrences ) {
                Integer occur = occurrences.get( saisie.getText() );
                if ( occur != null )
                    afficheur.setText( " -->  " + occur + " occurrence(s)" );
                else
                    afficheur.setText( " -->  ??? " );
            }
            texte.setText( liste.toString() );
        } catch( Exception e ) {
            afficheur.setText( e.toString() );
        }
    }

  
    public void itemStateChanged( ItemEvent ie )
    {
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
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar( String prefixe )
    {
        boolean resultat = false;
        Iterator<String> it = liste.iterator();
        while(it.hasNext()){
            String s = it.next();
            if(s.startsWith(prefixe)){
                occurrences.put(s,occurrences.get(s)-1);
                it.remove();
                resultat = true;
            }
        }
        return resultat;
    }
}