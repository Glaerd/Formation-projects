package question1;

/**
 * Decrivez votre classe Main ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Main
{

  public static void main( final String[] pArgs )
  {
    HTTPSensor.setHttpProxy( "cache.esiee.fr", 3128 );

    HTTPSensor ds2438;
    if ( pArgs.length == 0 )
      ds2438 = new HTTPSensor();
    else
      ds2438 = new HTTPSensor( pArgs[0] );

    try {
      // a completer pour 1.1 puis a modifier pour 1.2 :
      System.out.println( "requête auprès du ds2438 : "+ds2438.value());
      System.out.println( "requête auprès du ds2438 : "+ds2438.value());
      
    }
    catch ( final Exception pE ) {
      pE.printStackTrace();
    }
  } // main(.)
} // Main
