package question2;

/**
 * Decrivez votre classe MaxHandler ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
import java.util.Calendar;
import java.text.DateFormat;

public class MaxHandler extends Handler<Float>
{

    public MaxHandler( final Handler<Float> pSuccessor )
    {
        super( pSuccessor );
    } // MaxHandler(.)

    public boolean handleRequest( final Float pValue )
    {
        // a completer
        if(pValue >= 35 && pValue < 100) {
            System.out.println( "max : " + pValue );
            return false; // a modifier
        }
        return super.handleRequest(pValue);//this.getSuccessor().handleRequest(pValue);
    } // handleRequest(.)
} // MaxHandler
