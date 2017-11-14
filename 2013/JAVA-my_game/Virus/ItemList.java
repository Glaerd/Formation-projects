    import java.util.HashMap;
    import java.util.Set;
    import java.util.Iterator;

    
    public class ItemList
    {
        private HashMap<String,Item> liste_items;
        
        /**
         * Constructor for objects of class ItemList
         */
        public ItemList()
        {
            liste_items = new HashMap<String, Item>();
        }
    
        /**
         * Define an item 
         * @param pDescription Define Item's description
         * @param pWeight Define Item weight.
         */
        public void setItem(String pName,Item pItem) 
        {
            liste_items.put(pName,pItem);  
        }
        
        /**
         * Accessor to the HashMap
         * @return the HashMap
         * @param pItem Define Item name
         */
        public Item getItem(String pItem)
        {
            return liste_items.get(pItem);
        }

        /**
        * return if an Item exists
        * @param name Define Item name
        */ 
        public boolean exist(String name)
        {
            return liste_items.containsKey(name);
        }
        
        /**
        * return the current Weight of all the Items of the list
        */ 
        public int getPlayerWeight()
        {
            int weight = 0;
           
            for (Item item : liste_items.values()){
                weight = weight + item.getWeight();}
            return weight;
        }
        
        /**
         * Remove an Item in the HashMap
         * @param pitem The item to delete
         */
            public void removeItem(String pItem)
        {
            liste_items.remove(pItem);
        }
        

        /**
        * Gives a message with all the items of the list and their description
        * @param text Define a text to put in a area of the text
        */ 
        public String getItemList(String text)
        {
            StringBuilder list = new StringBuilder("Here are the files in " + text + " :\n");
            
            for (String name : liste_items.keySet()){
                list.append("- " + name + " (" + getItem(name).getWeight() + " Mo) : " + getItem(name).getDescription() + "\n");}
            list.append("(End of the list.)");
            return list.toString();
        }
        
        
        /**
         * Return the description of the items with their weight
         * @return The item(s) description
         */
        public String getItemsDescription()
        {
            Set<String> keys;
            keys = liste_items.keySet();
            String returnString = "Your inventory :";
            if (liste_items != null)
            {
                for(String item : keys)
                {
                    returnString = returnString+"\n"+ liste_items.get(item).getItemLongDescription();
                }
                }

        
            return returnString;
        
        }
       

    }
    
    
