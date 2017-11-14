package question2;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;

import java.io.*;

/**
 * Décrivez votre classe JAVASerialiseDeserialise ici.
 * 
 * @author (votre nom) 
 * @version (un numéro de version ou une date)
 */
public class JAVASerialiseDeserialise
{
      public static void serialjava(IProgr p, String nomDuFichier) throws IOException, ClassNotFoundException{  
          try
          {
       FileOutputStream fileOut = new FileOutputStream(nomDuFichier);
       ObjectOutputStream oos = new ObjectOutputStream(fileOut);
       oos.writeObject(p);
       oos.close();
    }
        catch(Exception e){
        e.printStackTrace();
    }
    }

      public static IProgr deserialjava(String nomDuFichier) throws IOException{
        IProgr Object = null;
          try{
          FileInputStream fileIn = new FileInputStream(nomDuFichier);
          ObjectInputStream oi = new ObjectInputStream(fileIn);
          Object = (IProgr) oi.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return Object;
    }    
}
