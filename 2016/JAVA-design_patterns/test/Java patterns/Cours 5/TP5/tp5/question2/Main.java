package question2;

import question1.HTTPSensor;
import question1.CyclicAcquisition;

/**
 * Decrivez votre classe Main ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Main
{

  public static void main( final String[] pArgs ) throws Exception
  {
    HTTPSensor.setHttpProxy( "cache.esiee.fr", 3128 );
    HTTPSensor ds2438 = null;
    if ( pArgs.length == 0 )
      ds2438 = new HTTPSensor();
    else
      ds2438 = new HTTPSensor( pArgs[0] );

    //a completer :
    Handler<Float> vChain = null;

    CyclicAcquisition vAcquisition = new CyclicAcquisition( ds2438, 1000,
        new ChainCommand<Float>( vChain ) );
    vAcquisition.start();

    Thread.sleep( 1000 * 10 );

    vAcquisition.stop();
  } // main()
} // Main
