import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    private String description;
    
    private HashMap<String, Room> exits;
    private String imageName;
    private ItemList liste_items;

    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String pDescription, String pImageName)
    {
        description = pDescription;
        imageName = pImageName;
        exits = new HashMap<String, Room>();
        liste_items = new ItemList();
    }

    /**
    * return a text of the list of the exits
    */
    public String getExitString()
    {
        String returnString = "Folders :";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " +exit;
        }
        return returnString;
    }

    /**
    * add an item to the room
    */
    public void addItem(String name, Item item) 
    {
        liste_items.setItem(name, item);
    }
    
    /**
    * return an item of a room
    * @param name Define the name of the item
    */
    public Item getItem(String name)
    {
        return liste_items.getItem(name);
    }
    
    /**
    * remove an item of the inventory
    * @param pItem Define the name of the Item to remove
    */
    public void removeItem(String pItem)
    {
        liste_items.removeItem(pItem);
    }
    

    /**
    * return true if the name of the item exist in the room
    * @param name of the item
    */
    public boolean itemPresence(String name)
    {
        return liste_items.exist(name);
    }
    
    /**
    * set an exit of a room
    */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    /**
    * return the exit of a room
    */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
    * return a list of items that exist in a room with their description and weight
    */
    public String getItemList()
    {
        return liste_items.getItemList("the folder");
    }
    
    /**
    * return the description of the room, the exits and the list of the items in the room
    */
    public String getLongDescription()
    {
        return "You are in the " + description + ".\n" + getExitString() + ".\n" + getItemList();
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
    * return the name of the image
    */
    public String getImageName()
    {
        return imageName;
    }
}
