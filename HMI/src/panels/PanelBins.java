package panels;

import classes.Bin;
import frames.FrameHeader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class PanelBins extends JPanel {
    public PanelBins(){
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(21.8f), FrameHeader.getScreenHeight(27.8f)));
//        setBackground(Color.blue);
//        setBorder(new LineBorder(Color.black, 1));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //tekenen van 2 dozen met tekst eronder(Joëlle)
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawRect(FrameHeader.getScreenWidth(0.5f), FrameHeader.getScreenHeight(0.5f),FrameHeader.getScreenWidth(9.9f), FrameHeader.getScreenHeight(23.15f));
        g.drawString("Doos 1",FrameHeader.getScreenWidth(0.5f), FrameHeader.getScreenHeight(25.65f) );
        g.drawRect(FrameHeader.getScreenWidth(11.4f), FrameHeader.getScreenHeight(0.5f),FrameHeader.getScreenWidth(9.9f), FrameHeader.getScreenHeight(23.15f));
        g.drawString("Doos 2",FrameHeader.getScreenWidth(11.4f), FrameHeader.getScreenHeight(25.65f) );

        //kopie makne van de arraylist van de dozen(kan eventueel ook anders)(Joëlle)
        ArrayList<Bin> copylistbins = Bin.getBins("First Fit");

        //variable voor x en y posities (Joëlle)
        int yPos = FrameHeader.getScreenHeight(23.65f);
        int xPos = FrameHeader.getScreenWidth(0.6f);
        int xPosText = FrameHeader.getScreenWidth(3f);

        //zelf kleuren toevoegen en array en arrayvariable(Joëlle)
        Color Color1 = new Color(202, 200, 255);
        Color Color2 = new Color(202, 225, 255);
        Color Color3 = new Color(189, 194, 234);
        Color[] colorList = new Color[] {Color1, Color2, Color3};
        int variableArray = 0;
        //eerst doorlopen van de 2 dozen (Joëlle)
        for(int i = 0; i< copylistbins.size(); i++){
            // van de dozen de producten doorlopen en per product dit tonen (Joëlle)
            for(int j = 0; j< copylistbins.get(i).producten.size(); j++){
                g.setColor(colorList[variableArray]); // de kleur uit kleurenarray halen (Joëlle)
                g.fillRect(xPos,yPos- FrameHeader.getScreenHeight(2.315f * copylistbins.get(i).producten.get(j).getWeight()), FrameHeader.getScreenWidth(9.8f), FrameHeader.getScreenHeight(2.315f * copylistbins.get(i).producten.get(j).getWeight() ));
                g.setColor(Color.black); // kleur voor tekst toevoegen (Joëlle)
                g.setFont(new Font("Arial", Font.PLAIN, 17));
                //een if/else constructie die ervoor zorgt dat de tekst altijd in het midden komt (Joëlle)
                if(copylistbins.get(i).producten.get(j).getWeight() == 4){
                    g.drawString("Artikelnr. "+ copylistbins.get(i).producten.get(j).getProductID(),xPosText, yPos-  FrameHeader.getScreenHeight(4f));
                }else if(copylistbins.get(i).producten.get(j).getWeight() == 3){
                    g.drawString("Artikelnr. "+ copylistbins.get(i).producten.get(j).getProductID(),xPosText, yPos-  FrameHeader.getScreenHeight(3f));
                }else{
                    g.drawString("Artikelnr. "+ copylistbins.get(i).producten.get(j).getProductID(),xPosText, yPos-  FrameHeader.getScreenHeight(5f));
                }
                //ophogen van de waarde (Joëlle)
                variableArray += 1;
                yPos -= FrameHeader.getScreenHeight(2.315f * copylistbins.get(i).producten.get(j).getWeight());
            }
            //ophogen van de waarde (Joëlle)
            variableArray = 2;
            xPos= FrameHeader.getScreenWidth(11.5f);
            yPos = FrameHeader.getScreenHeight(23.65f);
            xPosText += FrameHeader.getScreenHeight(20f);
        }


    }

}
