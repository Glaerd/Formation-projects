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
    HTTPSensor vDS2438 = null;
    if ( pArgs.length == 0 )
      vDS2438 = new HTTPSensor();
    else
      vDS2438 = new HTTPSensor( pArgs[0] );

    
    Handler<Float> vChain = new FileHandler("mesures2",new TraceHandler(null));//new TraceHandler(new MaxHandler(new MinHandler(null)));
    CyclicAcquisition vAcquisition = new CyclicAcquisition( vDS2438, 1000,
        new ChainCommand<Float>( vChain ) );
    vAcquisition.start();

    Thread.sleep( 1000 * 10 );
    vAcquisition.stop();
  } // main()
} // Main
