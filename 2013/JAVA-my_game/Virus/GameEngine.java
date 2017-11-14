import java.util.Scanner;

/**
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */
public class GameEngine
{
    private Parser parser;
    private UserInterface gui;
    private Player player;
    private int level, i;
    

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        parser = new Parser();
        createRooms();
        level = 1;
        i = 0;
    }

    /**
     * Initialise the interface of the game
     * @param userInterface Define the interface
     */
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        gui.showImage(player.getCurrentRoom().getImageName());
        printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */

    public void printWelcome()
    {
     gui.print("\n");
     gui.println("Welcome to Virus!");
     gui.println("Virus is a new, incredibly amazing adventure game.");
     gui.println("Click on \"Help!\" if you need help.");
     gui.print("\n");
     gui.println(player.getCurrentRoom().getLongDescription());
     changeGui(player.getCurrentRoom());
        }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room computer, downloads, mailbox;
        Item cumbersomeVirus, superVirus, Virus, antiVirus, antiSpyware, noConnection, sendMessage;
        
        
        cumbersomeVirus = new Item("cumbersomeVirus", "The cumbersomeVirus saves you from one attack", 10);
        Virus = new Item("Virus", "The Virus saves you from one attack!", 5);
        superVirus = new Item("superVirus", "The superVirus saves you from two attacks!", 5);
        antiVirus = new Item("antiVirus", "The antiVirus destroys all the Virus!", 0);
        antiSpyware = new Item("antiSpyware", "Kills you if you have no Virus", 0);
        noConnection = new Item("noConnection", "Your connection is so low that you can't even send an E-mail !", 0);
        sendMessage = new Item("sendMessage", "Brings you to the next level", 0);
        
        // create the rooms
        computer = new Room("computer", "images/Computer.PNG");
        downloads = new Room("downloads", "images/Downloads.PNG");
        mailbox = new Room("mailbox", "images/Mailbox.PNG");
        
        player = new Player("Skynet", 15, computer, this);
        
        
        
        // initialise room exits
        computer.setExit("downloads", downloads);
        computer.setExit("mailbox", mailbox);
        downloads.setExit("computer", computer); 
        mailbox.setExit("computer", computer);
        
        downloads.addItem("Virus", Virus);
        downloads.addItem("cumbersomeVirus", cumbersomeVirus);
        downloads.addItem("superVirus", superVirus);
        downloads.addItem("antiVirus", antiVirus);

        mailbox.addItem("antiSpyware", antiSpyware);
        mailbox.addItem("noConnection", noConnection);
        mailbox.addItem("gmail", sendMessage);
        
        
        
    }


    
    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     * @param commandLine Define the text that has been written by the player
     */
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);
        String aSecondWord = command.getSecondWord();
        String room = player.getCurrentRoomName();
        
        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        
        else if (commandWord.equals("go")) 
        {
            player.goRoom(command);
            gui.println("");
            gui.println("");
            gui.println("");
            gui.println("");
            room = player.getCurrentRoomName();
             if(room.equals("downloads"))
             {
                 gui.enableTake2(false);

             }
             if(room.equals("mailbox"))
             {
                 gui.enableTake1(false);
             }
             if(room.equals("computer"))
             {
                 gui.enableTake1(true);
                 gui.enableTake2(true);
             }  

        }
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                quit();
            }
        else if (commandWord.equals("back"))
            {
                player.back();
                gui.enableTake1(true);
                gui.enableTake2(true);
            }
        else if (commandWord.equals("take"))  
        {
           
           if(room.equals("downloads")) {
            if(i < level){
               
             int random = (int)(Math.random() * 4 + 1);
             
                 if(random == 1) {
                 player.take("cumbersomeVirus");
               } 
                 if(random == 2) {
                 player.take("Virus");
               }
                 if(random == 3) {
                 player.take("superVirus");
               }
                 if(random == 4) {
                 player.take("antiVirus");
                 player.removeAllVirus();
               }
               i = i+1;
            }
            else {
                gui.println("You can't take anything more!");
            }
            
            
          }
          
           if(room.equals("mailbox")) {
               
             int random2 = (int)(Math.random() * 10 + 1);
             
                 if(random2 <= 3) {
                 player.take("antiSpyware");
                 player.antiSpyware();
                 player.removeOneItem("antiSpyware");
               } 
                 else if(random2 <= 8) {
                 player.take("gmail");
                 player.removeOneItem("gmail");
                 changeLevel();
               }
                 else if(random2 <= 10) {
                 player.take("noConnection");
                 player.noConnection();
               }
  
            }
            
           if(room.equals("computer"))
            {
              gui.println("There is nothing to take in this folder");
            }

            }
            
        else if (commandWord.equals("test"))
           {
                if(command.hasSecondWord())
                    test(aSecondWord);
                else
                    gui.println("test what ?");
            }  
        else if (commandWord.equals("replay"))
           {
               String name = "go computer";
               commandLine = name;
               command = parser.getCommand(commandLine);
               player.goRoom(command);
               level = 1;
               i = 0;
               setGUI(gui);
               player.removeAllVirus();
               gui.enableTake1(true);
               gui.enableTake2(true);
               gui.enable(true);
               
            }
    }


    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        gui.println("You need to infest all the network!");
        gui.println("You can get help by recruiting other virus");
        gui.println("in the downloaded files, or try to infest");
        gui.println("the other computer by sending you from the mailbox");
        gui.print("Your command words are: " + parser.showCommands());
        
        gui.println("");
        printLocationInfo();
    }
    
    /**
     * return the interface
     */
    public UserInterface getGUI()
    {
        return gui;
    }

    /**
     * Test a list of commands in a .txt file
     * @param pSecondWord Define the name of the .txt file
     */
    public void test(String pSecondWord)
        {
            Scanner sc = new Scanner( this.getClass().getClassLoader().getResourceAsStream(pSecondWord));

            while ( sc.hasNextLine() ) 
            {
                String ligne = sc.nextLine();
                interpretCommand(ligne);
            }
    
        }
    
    /**
     * print the description of the currentroom
     */
        public void printLocationInfo()
    {
        gui.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Give an end message for the player and disable the buttonPanel and the entryField in the interface
     */
    public void endGame()
    {
        gui.println("The antiSpyware destroyed you!");
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }

    /**
     * print the list of the items of the room
     */
    public void retourListeItem()
    {
        gui.println(player.getCurrentRoom().getItemList());
    }
    
    /**
     * change the upper image of the interface
     * @param room Define the room where the image is stored
     */
    public void changeGui(Room room)
    {
        gui.showImage(room.getImageName());
    }
    
    /**
     * method that gives you the opportunity to display a message from a method of the Class Player for example
     * @param pText Define a text to display
     */
    public void display(String pText)
    {
        gui.println(pText);
    }
    

    
    /**
     * Gives the message at the end of the level and disable the buttonPanel and the entryField in the interface
     */
    public void changeLevel()
    {
     level = level + 1;
     gui.println ("You reached the level " + level);
      if (level < 10)
      {
           int levelLeft = 10 - level;
           gui.println ("You win in " + levelLeft + " levels");
           interpretCommand("go computer");
           
      }
      else
      {
           gui.println("Congratulation");
           gui.println("You infested all the network");
           gui.println("You ruined the A3P project of ten innocent ESIEE students by destroying their computer");
           gui.println("Nice job !");
           gui.println("Press the X button to end the game or the replay button to start over!");
           gui.enable(false);
      }
    
    }
    
    /**
     * quit the Game
     */
    public void quit()
    {
        gui.println("You can now quit the game!");
        gui.println("See you soon !");
        gui.enable(false);
    }
}