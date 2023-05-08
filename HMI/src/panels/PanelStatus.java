package panels;
//Door Jason Joshua & Martijn

import comms.Communication;
import database.Database;

import frames.FrameHeader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelStatus extends JPanel implements ActionListener {
    // Static door Martijn
    private static JButton jb_robotVerbinding = new JButton("Robot verbinding"); //melding voor robot verbinding
    private static JButton jb_databaseVerbinding = new JButton("Database verbinding"); //melding voor database verbinding
    private static JButton jb_rust = new JButton("Rust"); //melding voor rust
    private static JButton jb_productOphalen = new JButton("Product Ophalen"); //melding voor product ophalen
    private static JButton jb_inBeweging = new JButton("In Beweging"); //melding voor in beweging
    private static JButton jb_nood = new JButton("NOOD"); //melding voor nood
    private static JButton jb_productAfgeven = new JButton("Product Afgeven"); //melding voor product afgeven
    private static JButton jb_productTerugzetten = new JButton("Product Terugzetten"); //melding voor terugzetten
    private static JButton jb_handmatige = new JButton("Handmatig"); //melding voor handmatig besturing

    public PanelStatus(){
        //initialiseer het hoofd paneel
        setPreferredSize(new Dimension(960,540));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //voeg kleiner paneel voor de meldingen
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 3, 20, 20));
        p.setMaximumSize(new Dimension(900,480));
        p.setMinimumSize(new Dimension(900,480));
        p.setPreferredSize(new Dimension(900,480));

        Font Arial20 = new Font("Arial", Font.PLAIN, 20);
        //zet het font voor alle meldingen

        jb_robotVerbinding.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_productTerugzetten.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_rust.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_productOphalen.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_inBeweging.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_nood.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_productAfgeven.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_handmatige.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_databaseVerbinding.setFont(new Font("Arial", Font.PLAIN, 27));

        //Update de status van de robot
        PanelStatus.updateStatus();

        //voeg alle meldingen toe
        p.add(jb_robotVerbinding);
        p.add(jb_databaseVerbinding);
        p.add(jb_rust);
        p.add(jb_productOphalen);
        p.add(jb_inBeweging);
        p.add(jb_nood);
        p.add(jb_productAfgeven);
        p.add(jb_handmatige);
        p.add(jb_productTerugzetten);

        //voeg nieuwe box toe om het paneel in het midde te laten zitten
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(p);
        box.add(Box.createVerticalGlue());

        jb_robotVerbinding.addActionListener(this);
        jb_databaseVerbinding.addActionListener(this);
        jb_nood.addActionListener(this);

        //voeg deze box toe.
        add(box);
    }

    // Update de status van de robot
    // Door Martijn
    public static void updateStatus(){
        // Als er Serial verbinding is
        if(Communication.hasComms()){
            // Maak de knop groen
            jb_robotVerbinding.setBackground(Color.green);
        } else{
            // Anders maak hem rood
            jb_robotVerbinding.setBackground(Color.red);
        }

        // Als er database verbinding is
        if(Database.hasDbConnection()){
            // Maak de knop groen
            jb_databaseVerbinding.setBackground(Color.green);
        }else{
            // Anders maak hem rood
            jb_databaseVerbinding.setBackground(Color.red);
        }

        //zet de background voor alle meldingen
        jb_rust.setBackground(Color.lightGray);
        jb_productOphalen.setBackground(Color.lightGray);
        jb_inBeweging.setBackground(Color.lightGray);
        jb_nood.setBackground(Color.lightGray);
        jb_productAfgeven.setBackground(Color.lightGray);
        jb_handmatige.setBackground(Color.lightGray);
        jb_productTerugzetten.setBackground(Color.lightGray);
    }

    // Handelt knop acties af
    // Door Martijn
    public void actionPerformed(ActionEvent e) {
        // Als het om de verbonden knop gaat
        if(e.getSource() == jb_robotVerbinding){
            // Als hij nog geen communicatie heeft
            if(!Communication.hasComms()) {
                // Begin de communicatie
                Communication.openComms();
                // Stuur status 200 naar de Arduino
                Communication.sendComms(200);
            }

            // Update de status op het home scherm
            PanelStatus.updateStatus();
        }

        // Als test!
        if(e.getSource() == jb_nood){
            if(!Communication.hasComms()){
                Communication.sendComms(500);

                // Werkt niet, panel logica moet in nieuwe methode komen!
                if(Communication.readComms() == 500){
                    jb_nood.setBackground(Color.red);
                }

                PanelStatus.updateStatus();
            }
        }
        if(e.getSource() == jb_databaseVerbinding) {
            if(!Database.hasDbConnection()) {
                Database.connectToDatase();
            }
        }
    }
}
