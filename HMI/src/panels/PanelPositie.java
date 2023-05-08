package panels;

import frames.FrameHeader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

//door Jason Joshua van der Kolk
public class PanelPositie extends JPanel {
    public PanelPositie(){
        //initializeer het hoofd paneel
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(50f),FrameHeader.getScreenHeight(50f)));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));
//        setBorder(new MatteBorder(0,0,1,1,Color.black));
//        setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));


        //nieuw paneel voor alle informatie
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 500)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(1080, 500))));
        p.setLayout(new GridLayout(7, 6));
        for (int i = 0; i < 6; i++){
            p.add(new JLabel(""));
        }

        //voeg alle dingen toe aan het paneel
//        for (int i = 0; i <= 35; i++){
//            switch (i){
//                case 0:
//                    p.add(new JLabel("E"));
//                    break;
//                case 6:
//                    p.add(new JLabel("D"));
//                    break;
//                case 12:
//                    p.add(new JLabel("C"));
//                    break;
//                case 18:
//                    p.add(new JLabel("B"));
//                    break;
//                case 24:
//                    p.add(new JLabel("A"));
//                    break;
//                case 30:
//                    p.add(new JLabel(""));
//                    break;
//                case 31, 32, 33, 34, 35:
//                    p.add(new JLabel("" +(i - 30)));
//                    break;
//                default:
//                    JPanel p2 = new JPanel();
//                    p2.setBackground(Color.lightGray);
//                    p2.setBorder(new LineBorder(Color.BLACK, 1));
//                    p.add(p2);
//            }
//        }

        for (int i = 0; i <= 35; i++){
            Font arial20 = new Font("Arial", Font.PLAIN, 20);
            switch (i){
                case 0:
                    JLabel labelE = new JLabel("E ");
                    labelE.setHorizontalAlignment(SwingConstants.RIGHT);
                    labelE.setVerticalAlignment(SwingConstants.CENTER);
                    labelE.setFont(arial20);
                    p.add(labelE);
                    break;
                case 6:
                    JLabel labelD = new JLabel("D ");
                    labelD.setHorizontalAlignment(SwingConstants.RIGHT);
                    labelD.setVerticalAlignment(SwingConstants.CENTER);
                    labelD.setFont(arial20);
                    p.add(labelD);
                    break;
                case 12:
                    JLabel labelC = new JLabel("C ");
                    labelC.setHorizontalAlignment(SwingConstants.RIGHT);
                    labelC.setVerticalAlignment(SwingConstants.CENTER);
                    labelC.setFont(arial20);
                    p.add(labelC);
                    break;
                case 18:
                    JLabel labelB = new JLabel("B ");
                    labelB.setHorizontalAlignment(SwingConstants.RIGHT);
                    labelB.setVerticalAlignment(SwingConstants.CENTER);
                    labelB.setFont(arial20);
                    p.add(labelB);
                    break;
                case 24:
                    JLabel labelA = new JLabel("A ");
                    labelA.setHorizontalAlignment(SwingConstants.RIGHT);
                    labelA.setVerticalAlignment(SwingConstants.CENTER);
                    labelA.setFont(arial20);
                    p.add(labelA);
                    break;
                case 30:
                    p.add(new JLabel(""));
                    break;
                case 31, 32, 33, 34, 35:
                    JLabel labelNum = new JLabel("" + (i - 30));
                    labelNum.setHorizontalAlignment(SwingConstants.CENTER);
                    labelNum.setVerticalAlignment(SwingConstants.TOP);
                    labelNum.setFont(arial20);
                    p.add(labelNum);
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
