using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Huffman;

namespace HuffmanBibli
{
    public class HuffmanBibli : MarshalByRefObject, IPlugin
    {
        Dictionary<byte, byte> trad_file = new Dictionary<byte, byte>();

        string IPlugin.PluginName => "plop";

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

            bool b = true;
            if (data.compressedData.Length < data.frequency.Count)
            {
                b = false;
            }
            return b;
        }

        public bool Decompress(ref HuffmanData data)
        {
            /*
             
             */
            bool b = true;
            if(data.uncompressedData.Length != data.sizeOfUncompressedData)
            {
                b = false;
            }
            return b;
        }

        bool IPlugin.Compress(ref HuffmanData data)
        {
            throw new NotImplementedException();
        }

        bool IPlugin.Decompress(ref HuffmanData data)
        {
            throw new NotImplementedException();
        }
    }
}
