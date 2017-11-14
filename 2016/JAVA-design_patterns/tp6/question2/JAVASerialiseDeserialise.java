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
public class JAVASerialiseDeserialise implements java.io.Serializable
{
    public static void serialjava(IProgr p, String nomDuFichier) throws Exception{
        try{
            FileOutputStream fout = new FileOutputStream(nomDuFichier);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(p);
            oos.close();
        }
        catch(Exception e){

        }
    }

    public static IProgr deserialjava(String nomDuFichier) throws Exception{
        try{
            FileInputStream fout = new FileInputStream(nomDuFichier);
            ObjectInputStream ois = new ObjectInputStream(fout);
            return (IProgr) ois.readObject();
        }
        catch(Exception e){
            return null;
        }
    }    
}
