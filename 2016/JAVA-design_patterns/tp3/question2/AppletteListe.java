package question2;

import javax.swing.JApplet;
import javax.swing.JRootPane;
import java.util.List;
import java.util.Map;

/**
 * Classe AppletteListe - decrivez la classe ici
 * 
 * @author:  (votre nom)
 * @version: (un numero de version ou une date)
 */
public class AppletteListe extends JApplet
{
	public void init()
	{
		// Il y a un conflit de securite avec les navigateurs courants (incluant
		// Netscape & Internet Explorer) qui interdisent l'acces a la queue 
		// d'evenements d'AWT --ce dont les JApplets ont besoin au demarrage.
		JRootPane rootPane = this.getRootPane();	
		rootPane.putClientProperty( "defeatSystemEventQueueCheck", Boolean.TRUE) ;

		List<String> liste = Chapitre2CoreJava2.listeDesMots();
		Map<String,Integer> table = Chapitre2CoreJava2.occurrencesDesMots( liste );
        IHMListe ihmListe = new IHMListe( liste, table );
	    this.add( ihmListe );
	}
}
