package panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

//door Jason Joshua van der Kolk
public class PanelPositie extends JPanel {
    public PanelPositie(){
        //initializeer het hoofd paneel
        setPreferredSize(new Dimension(960,540));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));

        //nieuw paneel voor alle informatie
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(500, 500));
        p.setLayout(new GridLayout(6, 6));

        //voeg alle dingen toe aan het paneel
        for (int i = 0; i <= 35; i++){
            switch (i){
                case 0:
                    p.add(new JLabel("E"));
                    break;
                case 6:
                    p.add(new JLabel("D"));
                    break;
                case 12:
                    p.add(new JLabel("C"));
                    break;
                case 18:
                    p.add(new JLabel("B"));
                    break;
                case 24:
                    p.add(new JLabel("A"));
                    break;
                case 30:
                    p.add(new JLabel(""));
                    break;
                case 31, 32, 33, 34, 35:
                    p.add(new JLabel("" +(i - 30)));
                    break;
                default:
                    JPanel p2 = new JPanel();
                    p2.setBackground(Color.lightGray);
                    p2.setBorder(new LineBorder(Color.BLACK, 1));
                    p.add(p2);
            }
        }
        //voeg paneel toe aan het hoofdscherm
        add(p);
    }
}
