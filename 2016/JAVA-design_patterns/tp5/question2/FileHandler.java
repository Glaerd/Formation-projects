package question2;

import java.io.File;
// import des classes de gestion des fichiers en ecriture
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.ObjectOutputStream;
// import des classes de gestion des fichiers en lecture
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
// exceptions susceptibles d'etre engendrees lors d'une operation de lecture ou d'ecriture
import java.io.IOException;
import java.io.FileNotFoundException;
// exception liee a la serialisation
import java.lang.ClassCastException;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
/* a completer */
/* a completer */
import java.util.Set;

import java.util.Calendar;
import java.util.Locale;
import java.util.Iterator;
import java.util.Date;

import java.text.DateFormat;

// emploi de getResource (voir java.lang.Class)
import java.net.URL;

public class FileHandler extends Handler<Float> // a completer
{
    private static final int MAX= 5;
    private String aFileName;
    private int aCounter;
    private Date date;
    private SortedMap<Date, Float> aTable;

    public FileHandler( final String pFileName, final Handler<Float> pSuccessor )
    {
        super(pSuccessor);
        this.aFileName = pFileName;
        this.aCounter  = 0;
        this.aTable = new TreeMap<Date,Float>(); /* a modifier */
    } // FileHandler(..)

    public boolean handleRequest( final Float pValue )
    {
        date = new Date();
        aTable.put(date, pValue);
        try{writeHTML();}
        catch(IOException e){}
        return super.handleRequest( pValue ); /* a modifier */
    } // handleRequest(.)

    /** Cette methode genere un fichier HTML .
     */
    private void writeHTML() throws IOException
    {
        BufferedWriter vBW = new BufferedWriter( new PrintWriter(
                    new FileWriter( this.aFileName + ".html" ) ) );
        vBW.write( "<html><head><title>mesures du taux d'humidité relative</title>" );
        vBW.write( "<meta http-equiv=\"refresh\" content=\"30\">" );
        vBW.write( "</head><body><br>\r\n<table border=\"2\" bgcolor=\"#CBFEEA\">" );
        
        for (final Iterator iter = aTable.keySet().iterator(); iter.hasNext();) {
            final Date date = (Date) iter.next();
            final Float key = aTable.get(date);
            vBW.write("<tr><td>" + date + "</td>\n<td>" + key + "</td></tr>\n");
        }

        vBW.write( "</table>\r\n</body></html>" );
        
        vBW.close();
    } // writeHTML()

} // FileHandler
