package question1;

/**
 * Decrivez votre classe Main ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Main2
{
  public static void main( final String[] pArgs ) throws Exception
  {
    HTTPSensor.setHttpProxy( "cache.esiee.fr", 3128 );
    HTTPSensor ds2438 = null;
    if ( pArgs.length == 0 )
      ds2438 = new HTTPSensor();
    else
      ds2438 = new HTTPSensor( pArgs[0] );

    // a completer :
    CyclicAcquisition vAcquisition = new CyclicAcquisition(ds2438,ds2438.minimalPeriod(),new ConsoleCommand<Float>());
    vAcquisition.start();
    Thread.sleep(4000);
    vAcquisition.stop();
  } // main(.)
} // Main2
