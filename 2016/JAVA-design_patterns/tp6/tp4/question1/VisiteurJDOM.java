package tp4.question1;

import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

public class VisiteurJDOM extends VisiteurParDefaut<Element>{   
    private  Contexte c;
    public VisiteurJDOM(Contexte c){
        this.c =c;
    }

    public  Element visite(Constante c){
        Element cste=new Element("Constante");
        cste.addContent(Integer.toString(c.valeur()));
        return cste;
    }

    public Element visite(Variable v){
        Element var=new Element("Variable");
        var.addContent(v.nom());
        return var;
    }

    public Element visite(Division d){
        Element div=new Element("Division");
        div.addContent(d.op1().accepter(this));
        div.addContent(d.op2().accepter(this));
        return div;
    }

    public Element visite(Addition a){
        Element add=new Element("Addition");
        add.addContent(a.op1().accepter(this));
        add.addContent(a.op2().accepter(this));
        return add;
    }

    public Element visite(Multiplication m){
        Element mul=new Element("Multiplication");
        mul.addContent(m.op1().accepter(this));
        mul.addContent(m.op2().accepter(this));
        return mul;
    }

    public Element visite(Soustraction s){
        Element sous=new Element("Soustraction");
        sous.addContent(s.op1().accepter(this));
        sous.addContent(s.op2().accepter(this));
        return sous;
    }

    public Contexte contexte(){return c;}
}
