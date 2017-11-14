using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HuffmanBibli;
using Huffman;
using System.Reflection;

namespace HuffmanBibliTest
{
    class Program
    {
        static void Main(string[] args)
        {
            FileStream fs = new FileStream("C:/Users/magna/Desktop/HuffmanBibliFinitoOuPresque/HuffmanBibli/test_file", FileMode.Open);
            byte[] bytes = new byte[fs.Length];
            try
            {
                fs.Read(bytes, 0, Convert.ToInt32(fs.Length));
                fs.Dispose();
            }
            finally
            {
                fs.Dispose();
            }

            foreach (byte b in bytes)
            {
                Console.Write("{0}", (char) b);
            }
            Console.WriteLine();

            foreach (Assembly a in AppDomain.CurrentDomain.GetAssemblies())
            {
                foreach (Type t in a.GetTypes())
                {
                    if (t.GetInterface("HuffmanBibli.IPlugin2") != null)
                    {
                        try
                        {
                            IPlugin2 pluginclass = Activator.CreateInstance(t) as IPlugin2;
                            HuffmanData huff = new HuffmanData
                            {
                                uncompressedData = bytes
                            };
                            if (pluginclass.Compress(ref huff))
                            {
                                foreach (byte b in huff.compressedData)
                                {
                                    Console.Write("{0}", b);
                                }
                                Console.WriteLine();
                                if (pluginclass.Decompress(ref huff))
                                {
                                    foreach (byte b in huff.uncompressedData)
                                    {
                                        Console.Write("{0}", (char) b);
                                    }
                                }
                            }
                        }
                        catch
                        {
                        }
                    }
                }
            }
            Console.ReadLine();
        }
    }
}
