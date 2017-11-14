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
    return false; // a modifier
  } // handleRequest(.)
} // MaxHandler
