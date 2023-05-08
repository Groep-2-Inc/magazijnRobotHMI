package frames;

import panels.PanelLogboek;
import panels.PanelOrderStatus;
import panels.PanelPositie;
import panels.PanelStatus;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

//door Jason Joshua van der Kolk
public class FrameHome extends FrameHeader{

    public FrameHome(){
        //initializeer het hoofd paneel
        JPanel f = new JPanel();
        setPreferredSize(new Dimension(getScreenWidth(100f), getScreenHeight(100f)));
        f.setLayout(new GridLayout(2,2));

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
