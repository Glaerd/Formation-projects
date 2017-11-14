/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiee.mbdaihm;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

/**
 *
 * @author magnanc
 */
public class MouseWheelEventClass implements MouseWheelListener {
    
    private JPanel myPanel;
    public static double scaleFactor = 1;
    public static int mousePos[] = {0,0};
    public MouseWheelEventClass(JPanel panel) {
        //where initialization occurs:
        //Register for mouse-wheel events on the text area.
        myPanel = panel; 
        panel.addMouseWheelListener(this);
    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        int notches = e.getWheelRotation();
        mousePos[0] = e.getX() - myPanel.getWidth()/2;
        mousePos[1] = e.getY() - myPanel.getHeight()/2;
        
        // Add it to the image position...
        LOGGER.info("X : "+ mousePos[0] +" - Y : " + mousePos[1]);
        if(scaleFactor > 0.2 && notches < 0 || scaleFactor < 10 && notches > 0) scaleFactor += notches*0.1;
        myPanel.repaint();
    }
}