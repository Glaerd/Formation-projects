package question3;

import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;
import question2.*;

import java.io.*;

import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.*;
/**
 * D�crivez votre classe SerialiseDeserialiseAST_XML ici.
 * 
 * @author (votre nom) 
 * @version (un num�ro de version ou une date)
 */
public class SerialiseDeserialiseAST_XML
{
    public static void serialAst2xml(IProgr p,String nomDuFichier){
        Element programme=new Element("programme");

        Contexte m=(Memoire)p.getMem();
        //programme.addContent((Element)p.xmlJDOM());  

        VisiteurExpression<Element> ve = new VisiteurJDOM(m);
        VisiteurExpressionBooleenne<Element> vb = new VisiteurBoolJDOM(ve);
        VisiteurInstruction<Element> vi = new VisiteurInstJDOM(ve,vb);

        Instruction ast=p.getAST();
        //Nouveau Document JDOM bas� sur la racine de l'AST
        programme.addContent((Element)ast.accepter(vi));
        org.jdom.Document document = new Document(programme);
        try{
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            //il suffit simplement de cr�er une instance de FileOutputStream
            //avec en argument le nom du fichier pour effectuer la s�rialisation.
            FileOutputStream fos = new FileOutputStream(nomDuFichier);
            sortie.output(document,fos);
            fos.flush();
            fos.close();
        }catch (java.io.IOException e){}
    }

    public static Element deserialXml(String nomDuFichier) throws Exception
    {
        //TestXML2AST x2a=new TestXML2AST();
        //On cr�e une instance de SAXBuilder
        Document document=null;
        Element racine=null;
        SAXBuilder sxb = new SAXBuilder();
        try{
            document = sxb.build(new File(nomDuFichier));
        }catch(Exception e){}      
        //On initialise un nouvel Element �l�ment racine avec l'�l�ment racine du document.
        racine = document.getRootElement();

        Element instruction = (Element)racine.getChildren().get(0);
        return instruction;

    }
}