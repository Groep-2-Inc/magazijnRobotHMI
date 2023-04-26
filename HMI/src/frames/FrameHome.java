package frames;
//Door Jason Joshua

import comms.Communication;
import panels.PanelLogboek;
import panels.PanelOrderStatus;
import panels.PanelPositie;
import panels.PanelStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameHome extends FrameHeader{
    public FrameHome(){
        //initializeer het hoofd paneel
        JPanel f = new JPanel();
        setPreferredSize(new Dimension(1920, 1080));
        f.setLayout(new GridLayout(2,2));

        // Handelt het sluiten van de applicatie beter af
        // Door Martijn
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sluit Serial verbinding
                Communication.closeComms();

                // Sluit de applicatie
                dispose();
            }
        });

        //voeg de panelen toe
        f.add(new PanelPositie());
        f.add(new PanelStatus());
        f.add(new PanelOrderStatus());
        f.add(new PanelLogboek());

        //voeg het hoofd paneel toe
        add(f);
//        setVisible(true);
    }
}
