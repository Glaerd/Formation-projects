import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.BorderLayout;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener 
{
    private GameEngine engine;
    private JFrame myFrame, myFrame2;
    private JTextField entryField ;
    private JTextArea log;
    private JLabel image, logo;
    private JButton button1, button2, button3, button4, button5, button6, button7, button8;
    private JPanel buttonPanel;
    private int i;
    

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        i = 1;
        createGUI();
    }
    


    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }
   
    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        URL logoURL = this.getClass().getClassLoader().getResource("images/Logo.png");
        if(imageURL == null)
            println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }
            
        if(logoURL == null)
            println("logo not found");
        else {
            ImageIcon icon2 = new ImageIcon(logoURL);
            logo.setIcon(icon2);
            myFrame.pack();
        }
    
    }

    /**
     * Enable or disable input in the input field and some buttons of the buttonPanel.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button5.setEnabled(true);
            button7.setEnabled(true);
            button8.setEnabled(true);
        if(!on) {
            entryField.getCaret().setBlinkRate(0);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);
        }
    }
    
    /**
     * enable or disable the button7
     */    
    public void enableTake1(boolean on)
    {
        button7.setEnabled(true);
        if(!on) {
            button7.setEnabled(false);
        }
    }
    
    /**
     * enable or disable the button8
     */    
    public void enableTake2(boolean on)
    {
        button8.setEnabled(true);
        if(!on) {
            button8.setEnabled(false);
        }
    }
          
    

    /**
     * Set up graphical user interface.
     */
    public void createGUI()
    {       
        myFrame = new JFrame("Virus");
        entryField = new JTextField(34);
        
        
        
        log = new JTextArea();
        log.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        
        image = new JLabel();
        logo = new JLabel();
       
        
        JPanel buttonPanel = new JPanel();
        JPanel infoGamePanel = new JPanel();
        JPanel buttonAndLogoPanel = new JPanel();
        
        buttonPanel.setLayout(new GridLayout(5,3));
        infoGamePanel.setLayout(new BorderLayout());
        
        buttonPanel.add(new JLabel(""));
        
        button1 = new JButton("Downloads");
        button1.addActionListener(this);
        buttonPanel.add(button1);
        
        button7 = new JButton("Try to take a friendly Virus !");
        button7.addActionListener(this);
        buttonPanel.add(button7);
        
        button4 = new JButton("Computer");
        button4.addActionListener(this);
        buttonPanel.add(button4);
        
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        
        button2 = new JButton("Mailbox");
        button2.addActionListener(this);
        buttonPanel.add(button2);
        
        button8 = new JButton("Try to send you by the Mailbox");
        button8.addActionListener(this);
        buttonPanel.add(button8);
        
        button5 = new JButton("Back");
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(button5);
        button5.addActionListener(this);
        button3 = new JButton("Help!");
        button3.addActionListener(this);
        buttonPanel.add(button3);
        button6 = new JButton("Replay");
        button6.addActionListener(this);
        buttonPanel.add(button6);
        
        buttonAndLogoPanel.setLayout(new BorderLayout());
        buttonAndLogoPanel.add(buttonPanel, BorderLayout.EAST);
        buttonAndLogoPanel.add(logo, BorderLayout.WEST);
        
        
        panel.setLayout(new BorderLayout());
        panel.add(infoGamePanel, BorderLayout.NORTH);
        panel.add(buttonAndLogoPanel, BorderLayout.CENTER);
        panel.add(entryField, BorderLayout.SOUTH);
        
        
        panel.setBackground(Color.BLACK);   
       
        infoGamePanel.add(image, BorderLayout.WEST);
        infoGamePanel.add(listScroller, BorderLayout.CENTER);

        

        myFrame.getContentPane().add(panel, BorderLayout.CENTER);
        
        
        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        entryField.addActionListener(this);

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e) 
    {
       
       
       if(e.getSource().equals(button6))
       engine.interpretCommand("replay");
      
       if(e.getSource().equals(button1))
       engine.interpretCommand("go downloads");
       
       if(e.getSource().equals(button7))
       engine.interpretCommand("take");
       if(e.getSource().equals(button8))
       engine.interpretCommand("take");
       
       if(e.getSource().equals(button2)) {
       engine.interpretCommand("go mailbox");
        if(i<2) {
            // code pour afficher une fenêtre repris de la 274ème ligne du code de l'élève de l'année 2010/2011 (~blondiar) dans la classe UserInterface
           JOptionPane.showMessageDialog(myFrame,"Caution : you can be destroyed by the antiSpyware in the mailbox if you don't have any Virus next to you","Caution",JOptionPane.INFORMATION_MESSAGE);
           i=i+1;
        }
        
       }
       
       if(e.getSource() == entryField ){processCommand();}
       
       if(e.getSource().equals(button5))
       engine.interpretCommand("back");
       
       if(e.getSource().equals(button4))
       engine.interpretCommand("go computer");
       
       if(e.getSource().equals(button3))
       engine.interpretCommand("help");
       

      
    }

    
    
    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        boolean finished = false;
        String input = entryField.getText();
        entryField.setText("");

        engine.interpretCommand(input);
    }
}