package panels;

import frames.FrameHeader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

//door Jason Joshua van der Kolk
public class PanelOrderStatus extends JPanel {
    private Font arial14B = new Font("Arial", Font.BOLD, 14);

    public PanelOrderStatus(){
        //initializeer het hoofd paneel
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(50f),FrameHeader.getScreenHeight(50f)));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));

        //voeg nieuw paneel toe voor de titel
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(1080, 40))));
        p3.setBorder(new LineBorder(Color.BLACK));
        p3.setBackground(Color.lightGray);
        p3.setFont(arial14B);
        p3.add(new Label("Order Status"));
        add(p3);

        //nieuw paneel voor alle informatie
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 580)); //TODO hoogte schalen met de hoeveelheid dingen die we erin stoppen

        //voeg de informatie toe
        for(int i = 0; i<10; i++){
            JPanel p2 = new JPanel();
            p2.setLayout(new GridLayout(1, 1));
            p2.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 58));
            p2.setBorder(new LineBorder(Color.BLACK));
            p2.add(new JLabel("tekst"));
            p.add(p2);
        }

        //maar het scrollpane en voeg uiteindelijk het scrollpane toe.
        JScrollPane s = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        s.getVerticalScrollBar().setUnitIncrement(14);
        s.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(1080, 358))));
        add(s);
    }
}
