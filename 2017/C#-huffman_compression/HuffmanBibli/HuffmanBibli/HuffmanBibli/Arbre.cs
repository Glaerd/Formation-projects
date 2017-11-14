using Huffman;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HuffmanBibli
{
    class Arbre
    {
        public List<Noeud> listNoeud;
        public Noeud racine;
        public List<KeyValuePair<byte, int>> Occurence;
        public Dictionary<byte, List<bool>> code;
        public Dictionary<List<bool>, byte> codeInverse;

        public Arbre()
        {
            listNoeud = new List<Noeud>();
            racine = new Noeud();
            Occurence = new List<KeyValuePair<byte, int>>();
            code = new Dictionary<byte, List<bool>>();
            codeInverse = new Dictionary<List<bool>, byte>();
        }

        //remplissage du dictionnaire d'occurence
        public List<KeyValuePair<byte, int>> initOccurence(ref HuffmanData data)
        {
            if (data.uncompressedData.Length == 0) return null;

            Dictionary<byte, int> dico = new Dictionary<byte, int>();
            byte[] bytes = data.uncompressedData;
            for (int i = 0; i < bytes.Length; i++)
            {
                if (dico.ContainsKey(bytes[i])) dico[bytes[i]] += 1;
                else dico.Add(bytes[i], 1);
            }
            Occurence = dico.ToList<KeyValuePair<byte, int>>();
            return Occurence;
        }

        //creation de la liste de feuille
        public void initListArbre()
        {
            foreach (KeyValuePair<byte, int> pair in Occurence)
            {
                byte b = Convert.ToByte(pair.Key);
                List<byte> clef = new List<byte>();
                clef.Add(b);
                Noeud node = new Noeud(clef, pair.Value);
                listNoeud.Add(node);
            }
            listNoeud = listNoeud.OrderBy(x => x.poids).ToList<Noeud>();
        }

        //creation de l'arbre
        public void createArbre()
        {
            initListArbre();
            //tant qu'il y a plus d'un arbre dans la liste
            while (listNoeud.Count() > 1)
            {
                //on prend les arbres de poids minimun
                Noeud min = listNoeud[0];
                Noeud min2 = listNoeud[1];
                List<byte> key = new List<byte>();
                foreach (byte b in min.clef)
                {
                    key.Add(b);
                }
                foreach (byte b in min2.clef)
                {
                    key.Add(b);
                }
                int val = min.poids + min2.poids;
                //on cree un nouveau noeud parent des deux noeuds precedents
                Noeud node = new Noeud(key, val, min, min2);
                //on retire les noeuds fils de la liste
                listNoeud.RemoveAt(0);
                listNoeud.RemoveAt(0);
                //on ajoute le noeud parent dans la liste
                listNoeud.Add(node);
                //on trie la liste pour avoir les noeuds de poids minimum au debut
                listNoeud = listNoeud.OrderBy(x => x.poids).ToList<Noeud>();
            }
            //on defini le noeud final comme noeud racine
            racine = listNoeud[0];
            createCode();
        }

        public void createCode()
        {

            foreach (KeyValuePair<byte, int> pair in Occurence)
            {

                List<bool> temp = new List<bool>();
                Noeud nodeTp = racine;
                byte b = Convert.ToByte(pair.Key);
                while (nodeTp.gauche != null)
                {
                    if (nodeTp.gauche.clef.Contains(b))
                    {
                        temp.Add(false);
                        nodeTp = nodeTp.gauche;
                    }
                    else if (nodeTp.droite.clef.Contains(b))
                    {
                        temp.Add(true);
                        nodeTp = nodeTp.droite;
                    }
                }
                if(temp.Count != 0)
                {
                    code.Add(b, temp);
                    codeInverse.Add(temp, b);
                }
                else
                {
                    code.Add(b, new List<bool> { false });
                    codeInverse.Add(new List<bool> { false }, b);
                }

                //verfi code
                /*
                char[] val = new char[temp.Count()];
                int i = 0;
                foreach (byte b in temp)
                {
                    int j = Convert.ToInt32(b);
                    Console.WriteLine(j);
                    val[i] = Convert.ToChar(j);
                    i++;
                }
                string k = new string(val);
                string str = pair.Key.ToString() + ":" + k;
                Console.Write(str);
                Console.WriteLine("");*/
            }
            codeInverse = codeInverse.OrderBy(key => key.Value).ToDictionary(x => x.Key, x => x.Value);
        }

        //fonction d'affichage d'un arbre
        override
        public string ToString()
        {
            string str = "";
            str += racine.ToString();
            return str;
        }
    }
}
