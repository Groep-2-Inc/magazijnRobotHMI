package panels;

import classes.Product;
import classes.Order;
import frames.FrameHeader;
import frames.FrameVerwerken;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

//door Jason Joshua van der Kolk
public class PanelOrderStatus extends JPanel {
    private Font arial14B = new Font("Arial", Font.BOLD, 14);
    private ArrayList<Product> done = new ArrayList<>();
    private ArrayList<Product> doing = new ArrayList<>();
    private ArrayList<Product> waiting = new ArrayList<>();
    private static Order order;

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
        p.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 350)); //TODO hoogte schalen met de hoeveelheid dingen die we erin stoppen
        p.setBorder(new LineBorder(Color.black));
        add(p);

        if(order != null){
            for(Product product: order.getProducts()){
                switch (product.getStatus()) {
                    case "waiting" -> waiting.add(product);
                    case "doing" -> doing.add(product);
                    case "done" -> done.add(product);
                }
            }
        }

        //voeg de informatie toe
        for(int i = 0; i<waiting.size(); i++){
//            JPanel p2 = new JPanel();
//            p2.setLayout(new GridLayout(1, 1));
//            p2.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 58));
//            p2.setBackground(Color.red);
//            p2.setBorder(new LineBorder(Color.BLACK));
//            p2.add(new JLabel("tekst"));
//            p.add(p2);
            JPanel jpProductInfo = new JPanel();
            JLabel jlNameAndNumberText = new JLabel("Naam, artikelnr: ");
            JLabel jlNameAndNumber = new JLabel(waiting.get(i).getProductName() + ", " + waiting.get(i).getProductID());
            JLabel jlLocationText = new JLabel("Stelling: ");
            JLabel jlLocation = new JLabel(String.valueOf(waiting.get(i).getBinLocation()));
            JLabel jlWeightText = new JLabel("Gewicht: ");
            JLabel jlWeight = new JLabel(String.valueOf(waiting.get(i).getWeight()));

            jpProductInfo.setLayout(null);
            jpProductInfo.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(31.25f), FrameHeader.getScreenHeight(10f)));
            jpProductInfo.setBorder(new LineBorder(Color.black));
            jpProductInfo.setBackground(Color.pink);

            jlNameAndNumberText.setFont(new Font("Arial", Font.BOLD, 15));
            jlNameAndNumberText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(2f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlNameAndNumberText);

            jlNameAndNumber.setFont(new Font("Arial", Font.PLAIN, 14));
            jlNameAndNumber.setBounds(FrameHeader.getScreenWidth(7.5f), FrameHeader.getScreenHeight(2f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlNameAndNumber);

            jlLocationText.setFont(new Font("Arial", Font.BOLD, 15));
            jlLocationText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(4f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlLocationText);

            jlLocation.setFont(new Font("Arial", Font.PLAIN, 14));
            jlLocation.setBounds(FrameHeader.getScreenWidth(4.5f), FrameHeader.getScreenHeight(4f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlLocation);

            jlWeightText.setFont(new Font("Arial", Font.BOLD, 15));
            jlWeightText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(6f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlWeightText);

            jlWeight.setFont(new Font("Arial", Font.PLAIN, 15));
            jlWeight.setBounds(FrameHeader.getScreenWidth(4.8f), FrameHeader.getScreenHeight(6f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlWeight);

            p.add(jpProductInfo);
        }

        //voeg de informatie toe
        for(int i = 0; i<doing.size(); i++){
//            JPanel p2 = new JPanel();
//            p2.setLayout(new GridLayout(1, 1));
//            p2.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 58));
//            p2.setBackground(Color.orange);
//            p2.setBorder(new LineBorder(Color.BLACK));
//            p2.add(new JLabel("tekst"));
//            p.add(p2);
            JPanel jpProductInfo = new JPanel();
            JLabel jlNameAndNumberText = new JLabel("Naam, artikelnr: ");
            JLabel jlNameAndNumber = new JLabel(waiting.get(i).getProductName() + ", " + waiting.get(i).getProductID());
            JLabel jlLocationText = new JLabel("Stelling: ");
            JLabel jlLocation = new JLabel(String.valueOf(waiting.get(i).getBinLocation()));
            JLabel jlWeightText = new JLabel("Gewicht: ");
            JLabel jlWeight = new JLabel(String.valueOf(waiting.get(i).getWeight()));

            jpProductInfo.setLayout(null);
            jpProductInfo.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(31.25f), FrameHeader.getScreenHeight(10f)));
            jpProductInfo.setBorder(new LineBorder(Color.black));
            jpProductInfo.setBackground(new Color(245, 218, 130));

            jlNameAndNumberText.setFont(new Font("Arial", Font.BOLD, 15));
            jlNameAndNumberText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(2f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlNameAndNumberText);

            jlNameAndNumber.setFont(new Font("Arial", Font.PLAIN, 14));
            jlNameAndNumber.setBounds(FrameHeader.getScreenWidth(7.5f), FrameHeader.getScreenHeight(2f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlNameAndNumber);

            jlLocationText.setFont(new Font("Arial", Font.BOLD, 15));
            jlLocationText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(4f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlLocationText);

            jlLocation.setFont(new Font("Arial", Font.PLAIN, 14));
            jlLocation.setBounds(FrameHeader.getScreenWidth(4.5f), FrameHeader.getScreenHeight(4f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlLocation);

            jlWeightText.setFont(new Font("Arial", Font.BOLD, 15));
            jlWeightText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(6f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlWeightText);

            jlWeight.setFont(new Font("Arial", Font.PLAIN, 15));
            jlWeight.setBounds(FrameHeader.getScreenWidth(4.8f), FrameHeader.getScreenHeight(6f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlWeight);

            p.add(jpProductInfo);
        }

        //voeg de informatie toe
        for(int i = 0; i<done.size(); i++){
//            JPanel p2 = new JPanel();
//            p2.setLayout(new GridLayout(1, 1));
//            p2.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 58));
//            p2.setBackground(Color.green);
//            p2.setBorder(new LineBorder(Color.BLACK));
//            p2.add(new JLabel("tekst"));
//            p.add(p2);
            JPanel jpProductInfo = new JPanel();
            JLabel jlNameAndNumberText = new JLabel("Naam, artikelnr: ");
            JLabel jlNameAndNumber = new JLabel(waiting.get(i).getProductName() + ", " + waiting.get(i).getProductID());
            JLabel jlLocationText = new JLabel("Stelling: ");
            JLabel jlLocation = new JLabel(String.valueOf(waiting.get(i).getBinLocation()));
            JLabel jlWeightText = new JLabel("Gewicht: ");
            JLabel jlWeight = new JLabel(String.valueOf(waiting.get(i).getWeight()));

            jpProductInfo.setLayout(null);
            jpProductInfo.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(31.25f), FrameHeader.getScreenHeight(10f)));
            jpProductInfo.setBorder(new LineBorder(Color.black));
            jpProductInfo.setBackground(new Color(178, 245, 154));

            jlNameAndNumberText.setFont(new Font("Arial", Font.BOLD, 15));
            jlNameAndNumberText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(2f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlNameAndNumberText);

            jlNameAndNumber.setFont(new Font("Arial", Font.PLAIN, 14));
            jlNameAndNumber.setBounds(FrameHeader.getScreenWidth(7.5f), FrameHeader.getScreenHeight(2f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlNameAndNumber);

            jlLocationText.setFont(new Font("Arial", Font.BOLD, 15));
            jlLocationText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(4f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlLocationText);

            jlLocation.setFont(new Font("Arial", Font.PLAIN, 14));
            jlLocation.setBounds(FrameHeader.getScreenWidth(4.5f), FrameHeader.getScreenHeight(4f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlLocation);

            jlWeightText.setFont(new Font("Arial", Font.BOLD, 15));
            jlWeightText.setBounds(FrameHeader.getScreenWidth(1f), FrameHeader.getScreenHeight(6f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlWeightText);

            jlWeight.setFont(new Font("Arial", Font.PLAIN, 15));
            jlWeight.setBounds(FrameHeader.getScreenWidth(4.8f), FrameHeader.getScreenHeight(6f), FrameHeader.getScreenWidth(29f), FrameHeader.getScreenHeight(2f));
            jpProductInfo.add(jlWeight);

            p.add(jpProductInfo);

        }
    }

    public static void setOrder(Order o) {
        order = o;
    }
}
