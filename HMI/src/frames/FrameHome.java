package frames;

import comms.Communication;
import panels.PanelLogboek;
import panels.PanelOrderStatus;
import panels.PanelPositie;
import panels.PanelStatus;

import javax.swing.*;
import java.awt.*;

//door Jason Joshua van der Kolk
public class FrameHome extends FrameHeader{
    public FrameHome(Communication comms){
        //initializeer het hoofd paneel
        JPanel f = new JPanel();
        setPreferredSize(new Dimension(1920, 1080));
        f.setLayout(new GridLayout(2,2));

        //voeg de panelen toe
        f.add(new PanelPositie());
        f.add(new PanelStatus(comms));
        f.add(new PanelOrderStatus());
        f.add(new PanelLogboek());

        //voeg het hoofd paneel toe
        add(f);
//        setVisible(true);
    }
}
