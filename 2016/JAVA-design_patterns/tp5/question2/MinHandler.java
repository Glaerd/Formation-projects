package question2;

import java.util.Calendar;
import java.text.DateFormat;

/**
 * Decrivez votre classe MinHandler ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class MinHandler extends Handler<Float>
{

    public MinHandler( final Handler<Float> pSuccessor )
    {
        super( pSuccessor );
    } // MinHandler(.)

    public boolean handleRequest( final Float pValue )
    {
        // a completer
        if(pValue < 35 && pValue >= 0) {
            System.out.println( "min : " + pValue );
            return false;
        }
        return super.handleRequest(pValue); // a modifier//
        //}
        //else return this.getSuccessor().handleRequest(pValue);
    } // handleRequest(.)
} // MinHandler
