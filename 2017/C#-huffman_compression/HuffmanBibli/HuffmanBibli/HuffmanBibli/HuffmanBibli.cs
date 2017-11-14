using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Huffman;
using System.Collections;

namespace HuffmanBibli
{
    public class HuffmanBibli : MarshalByRefObject, IPlugin
    {

        static Arbre a;

        public string PluginName => "HuffManPlugin";

        public bool Compress(ref HuffmanData data)
        {
            /*public byte[] compressedData;
            public byte[] uncompressedData;
            public int sizeOfUncompressedData;
            public List<KeyValuePair<byte, int>> frequency;*/

            /*
             faire frequency à partir data.uncompressedData
             faire arbre à partir frequency
             remplir dico à partir arbre
             trier dico
             remplir compressedData à partir dico et uncompressedData
             */
            if (data.uncompressedData.Length == 0) return false;

            a = new Arbre();
            data.frequency = a.initOccurence(ref data);
            data.sizeOfUncompressedData = data.uncompressedData.Length;
            a.createArbre();

            List<bool> dataCode = new List<bool>();
            for(int i =0; i< data.sizeOfUncompressedData; i++)
            {
                if (a.code.Count > 1) dataCode.AddRange(a.code[data.uncompressedData[i]]);
                else dataCode.Add(a.code.ElementAt(0).Value[0]);
            }

            int size = dataCode.Count / 8;
            if (dataCode.Count % 8 != 0) size++;
            byte[] dataComp = new byte[size];
            int index = 0, ind = 0;
            for(int i=0; i<dataCode.Count; i++)
            {
                if (dataCode[i])
                {
                    dataComp[ind] |= (byte)(((byte)1) << 7 - index);
                }
                index++;
                if(index == 8)
                {
                    index = 0;
                    ind++;
                }
            }
            data.compressedData = dataComp;
            return true;
        }

        public bool Decompress(ref HuffmanData data)
        {
            /*
             
             */
            if (data.compressedData.Length == 0) return false;
            if (data.frequency.Count == 0) return false;
            if (a == null) return false;

            int size = 0, it = 0, el_path_size = 0;
            foreach(KeyValuePair<byte,int> v in data.frequency)
            {
                el_path_size = a.code.ElementAt(it).Value.Count;
                size += v.Value * el_path_size;
                it++;
            }
            //a remplir

            List<byte> data_read = new List<byte>();
            Noeud curr_noeud = a.racine;
            for (int i = 0; i < size; i++)
            {
                if (curr_noeud.droite != null && curr_noeud.gauche != null)
                {
                    if ((data.compressedData[i / 8] & (1 << 7 - i%8)) == 0) curr_noeud = curr_noeud.gauche;
                    else curr_noeud = curr_noeud.droite;
                }
                if (curr_noeud.droite == null && curr_noeud.gauche == null)
                {
                    data_read.Add(curr_noeud.clef[0]);
                    curr_noeud = a.racine;
                }
            }
            data.uncompressedData = data_read.ToArray();
            return true;
        }
    }
}
