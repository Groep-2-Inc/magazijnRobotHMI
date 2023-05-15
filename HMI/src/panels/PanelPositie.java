package panels;

import frames.FrameHeader;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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

        g.setColor(Color.black);
        g.fillRect(FrameHeader.getScreenWidth(14.5f), FrameHeader.getScreenHeight(2.8f), FrameHeader.getScreenWidth(21.8f), FrameHeader.getScreenHeight(40.25f));
        int xPos = FrameHeader.getScreenWidth(14.65f);
        int yPos = FrameHeader.getScreenHeight(3f);

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                g.setColor(Color.lightGray);
                g.fillRect(xPos, yPos, FrameHeader.getScreenWidth(4.2f), FrameHeader.getScreenHeight(7.8f));
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
}
