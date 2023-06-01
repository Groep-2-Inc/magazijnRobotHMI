package panels;

import classes.Robot;
import classes.Verwerken;
import frames.FrameHeader;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import TSP.*;
import frames.FrameHome;

//door Jason Joshua van der Kolk
public class PanelPositie extends JPanel {
    public PanelPositie(){
        //initializeer het hoofd paneel
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(50f),FrameHeader.getScreenHeight(50f)));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));
    }

    //voegt paneel toe aan het hoofdscherm
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Achtergrond
        g.setColor(Color.black);
        g.fillRect(FrameHeader.getScreenWidth(14.5f), FrameHeader.getScreenHeight(2.8f), FrameHeader.getScreenWidth(21.8f), FrameHeader.getScreenHeight(40.25f));
        int xPos = FrameHeader.getScreenWidth(14.65f);
        int yPos = FrameHeader.getScreenHeight(3f);

        // Zet huidige positie
        // Door Martijn
        int currXPoss = 0;
        int currYPoss = 0;
        // Als de huidige robot positie niet 0 is.
        if(Robot.getRobotPosisiton() != 0){
            // Leest de positie uit en haalt er 400 vanaf
            // Doe ik zodat de waardes makkelijk te gebruiken zijn in de for loop
            String numString = String.valueOf(Robot.getRobotPosisiton() - 400);
            // Als de lengte van numString 1 is zet er een 0 voor
            // Zodat als de status 405 -> 05 is is de waarde van numString ook echt 05 is
            // Zonder dit gaat hij hem trimmen; 05 zou 5 worden.
            if(numString.length() == 1){numString = "0" + numString;}
            // Pakt de X coördinaat uit de String; eerste char
            currXPoss = Character.getNumericValue(numString.charAt(0)) +1;
            // Pakt de Y coördinaat uit de String; tweede char
            currYPoss = Character.getNumericValue(numString.charAt(1)) -1;
        }

        // Eerste for Y-as
        for (int i = 5; i > 0; i--){
            // Tweede voor X-as
            for (int j = 0; j < 5; j++){
                int i2 = 0;
                g.setColor(Color.lightGray);
                g.fillRect(xPos, yPos, FrameHeader.getScreenWidth(4.2f), FrameHeader.getScreenHeight(7.8f));


                // Als de kolom overeen komt met huidige robot positie
                if(i == currXPoss && j == currYPoss){
                    // Maak het vakje groen
                    g.setColor(Color.green);
                    g.fillRect(xPos, yPos, FrameHeader.getScreenWidth(4.2f), FrameHeader.getScreenHeight(7.8f));
                }else if(Verwerken.IsVerwerken()){
                    for (int posFromTSP : Verwerken.getTSPRoute()) {
                        if (posFromTSP == (400 + 10 * (i - 1) + (j + 1)) && i2 != 0) {
                            System.out.println(posFromTSP);
                            g.setColor(Color.orange);
                            g.fillRect(xPos, yPos, FrameHeader.getScreenWidth(4.2f), FrameHeader.getScreenHeight(7.8f));

                            g.setColor(Color.black);
                            g.setFont(new Font("Arial", Font.PLAIN, 20));
                            g.drawString(String.valueOf(i2), xPos + FrameHeader.getScreenWidth(1.8f), yPos + FrameHeader.getScreenWidth(2f));

                        }
                        i2++;
                    }
                }

                xPos += FrameHeader.getScreenWidth(4.35f);


            }
            yPos += FrameHeader.getScreenHeight(8f);
            xPos = FrameHeader.getScreenWidth(14.65f);
        }

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 22));
        Character[] alphabet = {'E', 'D', 'C', 'B', 'A'};

        yPos = FrameHeader.getScreenHeight(7.5f);
        xPos = FrameHeader.getScreenWidth(16f);

        for (int i = 0; i < 5; i++){
            g.drawString(String.valueOf(alphabet[i]), FrameHeader.getScreenWidth(13.21f), yPos);
            yPos += FrameHeader.getScreenHeight(8f);
        }
        for (int i = 0; i < 5; i++){
            g.drawString(String.valueOf(i + 1), xPos, FrameHeader.getScreenHeight(45f));
            xPos += FrameHeader.getScreenHeight(8f);
        }
    }

    public void updatePanel(){
        repaint();
    }
}
