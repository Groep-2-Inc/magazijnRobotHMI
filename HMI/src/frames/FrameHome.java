package frames;
//Door Jason Joshua

import panels.PanelLogbook;
import panels.PanelOrderStatus;
import panels.PanelPositie;
import panels.PanelStatus;

import javax.swing.*;
import java.awt.*;

public class FrameHome extends FrameHeader{
    private PanelLogbook panelLogboek = new PanelLogbook();

    public FrameHome(){
        //initializeer het hoofd paneel
        JPanel f = new JPanel();
        setPreferredSize(new Dimension(getScreenWidth(100f), getScreenHeight(20f)));
        f.setLayout(new GridLayout(2,2));
        closeProgram();

        //voeg de panelen toe
        f.add(new PanelPositie());
        f.add(new PanelStatus());
        f.add(new PanelOrderStatus());
        f.add(panelLogboek);

        //voeg het hoofd paneel toe
        add(f);
    }

    public void updateLogbookPanel(){
        panelLogboek.updatePanel();
    }
}
