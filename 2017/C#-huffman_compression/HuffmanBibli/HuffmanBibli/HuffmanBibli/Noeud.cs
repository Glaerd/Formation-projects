using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HuffmanBibli
{
    class Noeud
    {
        public List<byte> clef;
        public int poids;
        public Noeud droite;
        public Noeud gauche;

        public Noeud(List<byte> k, int p)
        {
            clef = k;
            poids = p;
            droite = null;
            gauche = null;
        }

        public Noeud(List<byte> k, int p, Noeud g, Noeud d)
        {
            clef = k;
            poids = p;
            droite = d;
            gauche = g;
        }

        public Noeud()
        {
            clef = null;
            poids = 0;
            droite = null;
            gauche = null;
        }

        override
        public string ToString()
        {
            string str = "";
            if (this.gauche != null)
            {
                str += this.gauche.ToString();
                str += this.droite.ToString();
            }
            char[] key = new char[this.clef.Count()];
            int i = 0;
            foreach (byte b in this.clef)
            {
                key[i] = Convert.ToChar(b);
                i++;
            }
            string k = new string(key);
            str += k + ":" + this.poids + " ";
            return str;

        }

    }
}
