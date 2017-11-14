package question1;
import java.io.*;

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
    HTTPSensor vDS2438 = null;
    if ( pArgs.length == 0 )
      vDS2438 = new HTTPSensor();
    else
      vDS2438 = new HTTPSensor( pArgs[0] );

    
    CyclicAcquisition vAcquisition = new CyclicAcquisition(vDS2438,vDS2438.minimalPeriod(), new ConsoleCommand<Float>() );
    vAcquisition.start();
    Thread.sleep(2000);
    vAcquisition.stop();
  } // main(.)
} // Main2
