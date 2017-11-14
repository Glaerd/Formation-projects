import java.util.Stack; 

    public class Player
    {
        private String name;
        private int weightMax;
        private int currentWeight;
        private int i;
        private Room currentRoom;
        private ItemList inventory;
        private GameEngine game;
        private Stack<Room> liste_rooms;
        
        public Player(final String pName,final int pWeightMax, Room pCurrentRoom, GameEngine game)
        {
            this.name = pName;
            this.weightMax = pWeightMax;
            this.currentRoom =pCurrentRoom;
            inventory =new ItemList(); 
            currentWeight = 0;
            this.game = game;
            liste_rooms = new Stack<Room>();
            i = 1;

        }
        
        /**
        * return the name of the player
        */ 
        public String getName()
        {
            return name;
        }
        
     
        /**
        * return if an item is in the inventory
        * @param Define the name of the item
        */ 
        public boolean itemPresence(String name)
        {
            return inventory.exist(name);
        }
        
        /**
        * return true if the player has enough memory to take an item
        * @param room Define the currentroom
        * @param name Define the name of the item
        */ 
        public boolean canTake(Room room, String name)
        {
            return (inventory.getPlayerWeight() + room.getItem(name).getWeight()) <= weightMax;
        }
        
        /**
        * add an item to the inventory
        */ 
        public void takeItem(Room room, String name)
        {

            inventory.setItem(name, room.getItem(name));
           
        }
        
        /**
        * remove one item
        * @param name name of the item
        */ 
        public void removeOneItem(String name)
        {
            inventory.removeItem(name);
        }
        
        /**
        * remove all items
        */ 
        public void removeAllVirus()
        {
            inventory.removeItem("cumbersomeVirus");
            inventory.removeItem("Virus");
            inventory.removeItem("superVirus");
            inventory.removeItem("antiVirus");
        }
        
        /**
        * Gives an instruction if the player finds an antiSpyware
        */ 
        public void antiSpyware()
        {
            if(itemPresence("cumbersomeVirus"))
            {
                inventory.removeItem("cumbersomeVirus");
            }
            else if(itemPresence("Virus"))
            {
                inventory.removeItem("Virus");
            }
            else if(itemPresence("superVirus"))
            {
                if(i < 3)
                {
                    i = i + 1;
                }
                if(i == 3)
                {
                    i = 1;
                    inventory.removeItem("superVirus");
                }
            }
            else {
                game.endGame();
            }
        }
        
        /**
        * Gives an instruction if the player finds a noConnection
        */ 
        public void noConnection()
        {

            inventory.removeItem("noConnection");
        }
        

        
        /**
        * Display a message and takes an item everytime the method is called
        * @param item Define the name of the item
        */ 
        public void take(String item)
        {
            
            if(!(currentRoom.itemPresence(item))) {
                game.display("There is no file like this in this folder!");
                return;
            }
            
            if(canTake(currentRoom, item))
            {
                takeItem(currentRoom, item);
                game.display("You took one file");
                game.display(inventory.getItemsDescription());
                game.display("");
                game.display("");
                game.display("");
                game.display("");
            }
            else
            {
                game.display("Not enough memory!");
            }
        }
        
        /**
        * change the currentroom to the room of the command
        * @param command Define the command to apply
        */ 
        public void goRoom(Command command) 
        {
         if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            game.display("Go where?");
            return;
         }

         String direction = command.getSecondWord();
         Room nextRoom = currentRoom.getExit(direction);
         
          if (nextRoom == null)
            game.display("You can't go here or you're in the same folder!");
          else {
             
              changeRoom(nextRoom);
       
          }

        }
       
        /**
         * Takes the player to the previous room
         */
        public void back()
        {
             if(!(liste_rooms.empty())) {
                Room backuproom = liste_rooms.pop();
                game.display("You're back in the previous folder!");
                changeRoom(backuproom);
                liste_rooms.pop();
                game.display("");
                game.display("");
                game.display("");
                game.display("");
             }
             else
                game.display("You can't go back any further");
        }
        
        /**
         * Accessor to to the player's Weight
         * @return The max weight that player can carry
         */
        public int getWeightMax()
        {
            return weightMax;
        }
    
        /**
         * Return the String of all the items in the inventory
         * @return A String representing player's inventory
         */
        public String returnInventoryString()
        {
            return "Your items are : "+ inventory.getItemsDescription();
        }
        
            /**
         * Accessor to to the player's current Weight
         * @return The current weight carry by the player
         */
        public int getCurrentWeight()
        {
            return currentWeight;
        }
        
        /**
         * Accessor to get the player current Room 
         * @return The current room of the player
         */
        public Room getCurrentRoom()
        {
            return currentRoom;
        }
        
        /**
         * return the name of the current room
         */
        public String getCurrentRoomName()
        {
            return currentRoom.getDescription();
        }
        
        /**
         * Accessor to player's inventory 
         * @return Player's inventory
         */
        public ItemList getInventory()
        {
            return inventory;
        }
        
        /**
         * Modificator which actualise currentWeight when you take an Item
         * @param pWeight The weight to add to current weight
         */
        public void addCurrentWeight(int pWeight)
        {
            currentWeight = currentWeight+pWeight;
        }
        
         /**
         * Modificator which actualise currentWeight when you drop an Item
         * @param pWeight The weight to substract to current weight
         */
        public void subCurrentWeight(int pWeight)       //fusion avec addWeight ï¿½ rï¿½aliser
        {
            currentWeight = currentWeight-pWeight;
        }
        
        /**
         * Method which return a String that indicate current free Weight in your inventory
         * @return Spaces free in Player's inventory
         */
        public String getInventoryWeight()
        {
            return "You have "+currentWeight+"/"+weightMax+" Mo used";
        }
        
        
         /**
         * Modificator to change the Max weight which can be take by player
         * @param A value that invrease or decrease player's max inventory
         */
        public void newMaxWeight(int pWeight)
        {
            weightMax = weightMax+pWeight;
        }
        

        
        /**
         * Goes to the room of the command
         * @param room Define the command to apply
         */    
        public void changeRoom(Room room)
        {
            liste_rooms.push(currentRoom);
            currentRoom = room;
            game.printLocationInfo();
            game.changeGui(currentRoom);
            
            game.display("");
            game.display("");
            game.display("");
            game.display("");
            
        }
        

    }
 