package question3;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;
import question2.*;
import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

/**
 * Décrivez votre classe XML2AST ici.
 * 
 * @author (votre nom) 
 * @version (un numéro de version ou une date)
 */
public class XML2AST
{

      public static Instruction  xmlInst2ast(Contexte m, Element element) throws Exception{
         
         String nomInstruction=element.getName();
         if(nomInstruction=="Affectation"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             Variable var=new Variable(m ,el1.getText());
             Expression exp=xmlExp2ast(m,el2);
             return new Affectation(var , exp);
            }
         if(nomInstruction=="Sequence"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             Instruction i1=xmlInst2ast(m,el1);
             Instruction i2=xmlInst2ast(m,el2);
              return new Sequence(i1 , i2);
            }
         if(nomInstruction=="Selection"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             ExpressionBooleenne b1=xmlExpBool2ast(m,el1);
             Instruction i1=xmlInst2ast(m,el2);
             if(element.getChildren().size()==3){
                 Element el3 = (Element)element.getChildren().get(2);
                 Instruction i2=xmlInst2ast(m,el3);
                 return new Selection(b1 , i1 , i2);
                }
              return new Selection(b1 , i1);
            }
         if(nomInstruction=="TantQue"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             ExpressionBooleenne b1=xmlExpBool2ast(m,el1);
             Instruction i1=xmlInst2ast(m,el2);
              return new TantQue(b1 , i1);
            }

         if(nomInstruction=="Pour"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             Element el3 = (Element)element.getChildren().get(2);
             Element el4 = (Element)element.getChildren().get(3);
             ExpressionBooleenne b1=xmlExpBool2ast(m,el2);
             Instruction init=xmlInst2ast(m,el1);
             Instruction i1=xmlInst2ast(m,el3);
             Instruction inc=xmlInst2ast(m,el4);
              return new Pour(init , b1 , i1 , inc);
            }
         if(nomInstruction=="Afficher"){
             Element el1 = (Element)element.getChildren().get(0);
             Expression e1=xmlExp2ast(m,el1);
             return new Afficher(e1);
            }

            throw new RuntimeException("Erreur dans instruction ...");
        }
        
         public static Expression  xmlExp2ast(Contexte m, Element element) throws Exception{
            String nomExpr=element.getName();
            if(nomExpr=="Constante"){
                 Integer el1 = Integer.parseInt(element.getText());
                 return new Constante(el1);
            }
            
            if(nomExpr == "Variable"){
                 String el1 = element.getText();
                 return new Variable(m, el1);
            }
            
            if(nomExpr == "Addition"){
                 Expression op1 = xmlExp2ast(m,(Element)element.getChildren().get(0));
                 Expression op2 = xmlExp2ast(m,(Element)element.getChildren().get(1));
                 return new Addition(op1 , op2);
            }
  
            if(nomExpr == "Soustraction"){
                 Expression op1 = xmlExp2ast(m,(Element)element.getChildren().get(0));
                 Expression op2 = xmlExp2ast(m,(Element)element.getChildren().get(1));
                 return new Soustraction(op1 , op2);
            }
            
            if(nomExpr == "Division"){
                 Expression op1 = xmlExp2ast(m,(Element)element.getChildren().get(0));
                 Expression op2 = xmlExp2ast(m,(Element)element.getChildren().get(1));
                 return new Division(op1 , op2);
            }
            
            if(nomExpr == "Multiplication"){
                 Expression op1 = xmlExp2ast(m,(Element)element.getChildren().get(0));
                 Expression op2 = xmlExp2ast(m,(Element)element.getChildren().get(1));
                 return new Multiplication(op1 , op2);
            }
            throw new RuntimeException("Erreur dans expression ...");
           
                }
                
                
         public static ExpressionBooleenne  xmlExpBool2ast(Contexte m,Element element) throws Exception{
                String nomExprBool=element.getName();
                if(nomExprBool == "Vrai"){
                    return new Vrai();
                    }
                if(nomExprBool == "Faux"){
                    return new Faux();
                    }
                if(nomExprBool == "Non"){
                    ExpressionBooleenne el1 = xmlExpBool2ast(m,(Element)element.getChildren().get(0));
                    return new Non(el1);
                    }     
                if(nomExprBool == "Ou"){
                    ExpressionBooleenne el1 = xmlExpBool2ast(m,(Element)element.getChildren().get(0));
                    ExpressionBooleenne el2 = xmlExpBool2ast(m,(Element)element.getChildren().get(1));
                    return new Ou(el1,el2);
                    }  
                if(nomExprBool == "Et"){
                    ExpressionBooleenne el1 = xmlExpBool2ast(m,(Element)element.getChildren().get(0));
                    ExpressionBooleenne el2 = xmlExpBool2ast(m,(Element)element.getChildren().get(1));
                    return new Et(el1,el2);
                    }
                if(nomExprBool == "Inf"){
                    Expression el1 = xmlExp2ast(m,(Element)element.getChildren().get(0));
                    Expression el2 = xmlExp2ast(m,(Element)element.getChildren().get(1));
                    return new Inf(el1,el2);
                    }  
                if(nomExprBool == "Egal"){
                    Expression el1 = xmlExp2ast(m,(Element)element.getChildren().get(0));
                    Expression el2 = xmlExp2ast(m,(Element)element.getChildren().get(1));
                    return new Egal(el1,el2);
                    }  
                if(nomExprBool == "Sup"){
                    Expression el1 = xmlExp2ast(m,(Element)element.getChildren().get(0));
                    Expression el2 = xmlExp2ast(m,(Element)element.getChildren().get(1));
                    return new Sup(el1,el2);
                    }      
                throw new RuntimeException("Erreur dans expression booleenne...");

                }
}

