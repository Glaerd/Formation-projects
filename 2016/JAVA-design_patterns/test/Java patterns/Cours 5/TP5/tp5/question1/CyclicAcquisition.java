package question1;

public class CyclicAcquisition implements Runnable
{

  private Thread aLocal;
  private HumiditySensor aSensor;
  private long aPeriod;
  private Command<Float> aCommand;

  public CyclicAcquisition( final HumiditySensor pSensor, final long pPeriod,
      final Command<Float> pCommand ) throws Exception
  {
    if ( pPeriod < pSensor.minimalPeriod() )
      throw new Exception( " respectez la periode minimale ( >= "
          + pSensor.minimalPeriod() + ") !!" );
    this.aSensor = pSensor;
    this.aPeriod = pPeriod;
    this.aCommand = pCommand;
    this.aLocal = new Thread( this );
  } // CyclicAcquisition(...)

  public void start()
  {
    /* demarrer le thread */
    aLocal.start();
  } // start()

  public void stop()
  {
    /* interrompre le thread */
    aLocal.interrupt();
  } // stop()

  public synchronized void setPeriod( final long pPeriod ) throws Exception
  {
    if ( pPeriod < aSensor.minimalPeriod() )
      throw new Exception( " respectez la periode minimale ( >= "
          + aSensor.minimalPeriod() + ") !!" );
    this.aPeriod = pPeriod;
  } // setPeriod(.)

  public void run()
  {
    try {
      if ( Thread.currentThread() != this.aLocal )
        throw new Exception( "Mauvais thread !!" );
      while ( !this.aLocal.isInterrupted() ) {
        synchronized ( this ) {
          try {
            /* lecture du capteur et transmission de la valeur par make() */
            aCommand.make(aSensor.value());
          }
          catch ( final Exception pE ) {
            /* transmission d'une valeur negative par make() */
            aCommand.make(-1F); // F pour indiquer un float
          }
          Thread.sleep( this.aPeriod );
        } // synchronized
      } // while
    }
    catch ( final InterruptedException pIE ) {
      // rien
    }
    catch ( final Exception pE ) {
      System.out.println( pE.toString() );
    } // catch
  } // run()
} // CyclicAcquisition
