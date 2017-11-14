import java.util.HashMap;

public class Item
    {
        
        private String description;
        private int weight, strength;
        private String itemName;
        
        public Item(String pItemName, String description, int weight)
        {
            this.description = description;
            this.weight = weight;
            itemName = pItemName;
        }
    
        /**
         * Gives the description of an item
         * @return a String of the description
         */
        public String getDescription()
        {
            return description;
        }
        
        /**
        * return the name of the item
        */ 
        public String getItemName()
        {
          return itemName ;
        }
        
        /**
        * return the name of the item + a description + the weight
        */ 
        public String getItemLongDescription()
        {
         return getItemName()+": "+getDescription()+"   Weight :"+getWeight();   
        }

        /**
        * return the weight of the item
        */ 
        public int getWeight()
        {
            return weight;
        }
        
    }