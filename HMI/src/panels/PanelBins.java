package panels;

import classes.Bin;
import frames.FrameHeader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelBins extends JPanel {
    public PanelBins(){
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(21.8f), FrameHeader.getScreenHeight(27.8f)));
        setBackground(Color.blue);
//        setBorder(new LineBorder(Color.black, 1));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.drawRect(FrameHeader.getScreenWidth(0.5f), FrameHeader.getScreenHeight(0.5f),FrameHeader.getScreenWidth(9.9f), FrameHeader.getScreenHeight(23.15f));
        g.drawString("Doos 1",FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(24.65f) );
        g.drawRect(FrameHeader.getScreenWidth(11.4f), FrameHeader.getScreenHeight(0.5f),FrameHeader.getScreenWidth(9.9f), FrameHeader.getScreenHeight(23.15f));
        g.drawString("Doos 2",FrameHeader.getScreenWidth(11.9f), FrameHeader.getScreenHeight(24.65f) );
//        int yPos = 0;
//        for(int j = 0; j< Bin.getBins("First Fit").get(0).producten.size(); j++){
//            if(j != 1){
//                g.setColor(Color.yellow);
//            }else{
//                g.setColor(Color.lightGray);
//            }
//            g.fillRect(0,yPos, FrameHeader.getScreenWidth(9.9f), FrameHeader.getScreenHeight(2.315f * Bin.getBins("First Fit").get(0).producten.get(j).getWeight() ));
//            yPos += FrameHeader.getScreenHeight(2.315f * Bin.getBins("First Fit").get(0).producten.get(j).getWeight());
//        }


    }

}
