package panels;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
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
//        p.setLayout(new GridLayout(7, 6));
//        for (int i = 0; i < 6; i++){
//            p.add(new JLabel(""));
//        }
//
//        //voeg alle dingen toe aan het paneel
////        for (int i = 0; i <= 35; i++){
////            switch (i){
////                case 0:
////                    p.add(new JLabel("E"));
////                    break;
////                case 6:
////                    p.add(new JLabel("D"));
////                    break;
////                case 12:
////                    p.add(new JLabel("C"));
////                    break;
////                case 18:
////                    p.add(new JLabel("B"));
////                    break;
////                case 24:
////                    p.add(new JLabel("A"));
////                    break;
////                case 30:
////                    p.add(new JLabel(""));
////                    break;
////                case 31, 32, 33, 34, 35:
////                    p.add(new JLabel("" +(i - 30)));
////                    break;
////                default:
////                    JPanel p2 = new JPanel();
////                    p2.setBackground(Color.lightGray);
////                    p2.setBorder(new LineBorder(Color.BLACK, 1));
////                    p.add(p2);
////            }
////        }
//
//        for (int i = 0; i <= 35; i++){
//            Font arial20 = new Font("Arial", Font.PLAIN, 20);
//            switch (i){
//                case 0:
//                    JLabel labelE = new JLabel("E ");
//                    labelE.setHorizontalAlignment(SwingConstants.RIGHT);
//                    labelE.setVerticalAlignment(SwingConstants.CENTER);
//                    labelE.setFont(arial20);
//                    p.add(labelE);
//                    break;
//                case 6:
//                    JLabel labelD = new JLabel("D ");
//                    labelD.setHorizontalAlignment(SwingConstants.RIGHT);
//                    labelD.setVerticalAlignment(SwingConstants.CENTER);
//                    labelD.setFont(arial20);
//                    p.add(labelD);
//                    break;
//                case 12:
//                    JLabel labelC = new JLabel("C ");
//                    labelC.setHorizontalAlignment(SwingConstants.RIGHT);
//                    labelC.setVerticalAlignment(SwingConstants.CENTER);
//                    labelC.setFont(arial20);
//                    p.add(labelC);
//                    break;
//                case 18:
//                    JLabel labelB = new JLabel("B ");
//                    labelB.setHorizontalAlignment(SwingConstants.RIGHT);
//                    labelB.setVerticalAlignment(SwingConstants.CENTER);
//                    labelB.setFont(arial20);
//                    p.add(labelB);
//                    break;
//                case 24:
//                    JLabel labelA = new JLabel("A ");
//                    labelA.setHorizontalAlignment(SwingConstants.RIGHT);
//                    labelA.setVerticalAlignment(SwingConstants.CENTER);
//                    labelA.setFont(arial20);
//                    p.add(labelA);
//                    break;
//                case 30:
//                    p.add(new JLabel(""));
//                    break;
//                case 31, 32, 33, 34, 35:
//                    JLabel labelNum = new JLabel("" + (i - 30));
//                    labelNum.setHorizontalAlignment(SwingConstants.CENTER);
//                    labelNum.setVerticalAlignment(SwingConstants.TOP);
//                    labelNum.setFont(arial20);
//                    p.add(labelNum);
//                    break;
//                default:
//                    JPanel p2 = new JPanel();
//                    p2.setBackground(Color.lightGray);
//                    p2.setBorder(new LineBorder(Color.BLACK, 1));
//                    p.add(p2);
//            }
//        }


//        p.paintComponent();



        //voeg paneel toe aan het hoofdscherm

    }

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
