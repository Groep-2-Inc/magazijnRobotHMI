package frames;
//door Jason Joshua van der Kolk
//styling bijgewerkt door Daan
//bpp gedaan door Joëlle

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import database.Database;
import panels.PanelBins;
import panels.PanelPositie;
import classes.Order;

public class FrameVerwerken extends FrameHeader implements ActionListener {
    private int i_orderID = 1; //int voor order id
    private Date d_date;
    private String s_customerName; // string voor de customer name
    private int i_customerID = 1; //int voor de customer id
    private int i_totaalAantalProducten = 1; //int voor totaal aantal producten
    private int i_productenVerzameld = 1; //int voor het aantal producten dat al verzameld is
    private JButton jb_annuleer = new JButton("Annuleer"); //button voor het annuleeren
    private JButton jb_go = new JButton("GO!"); //button voor het starten van het pakken van de orders
    private JButton jb_pakbonnenMaken = new JButton("Pakbonnen maken"); //button voor het aanmaken van de pakbonnen
    private static Order o_order;


    public FrameVerwerken(Order order){
        //initializeer alle nodige variabelen.
        closeProgram();
        o_order = order;
        this.i_orderID = order.getOrderID();
        this.i_customerID = order.getCustomer().getCustomerID();
        this.i_totaalAantalProducten = order.getProductCount();
        this.s_customerName = order.getCustomer().getCustomerName();
        this.d_date = order.getDate();

        //nieuwe pannel aanmaken voor alle informatie
        JPanel f = new JPanel();
        f.setPreferredSize(new Dimension(getScreenWidth(100f), getScreenHeight(100f)));

        //voeg de subheader toe aan het paneel
        JPanel p = GetHeaderPanel();
        f.add(p);

        //maak het lijntje om de delen van het scherm van elkaar te houden
        p = new JPanel();
        p.setPreferredSize(new Dimension(getScreenWidth(97f), 1));
        p.setBackground(Color.BLACK);
        f.add(p);

        //maak een niew paneel aan voor de splitsing in het midde
        JPanel p2 = new JPanel(new GridLayout(1, 2));
        p2.setPreferredSize(new Dimension(getScreenWidth(88.5f), getScreenHeight(74f)));
        //maak een nieuw paneel aan voor de linker kant
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(getScreenWidth(88.5f)/2, getScreenHeight(74f)));

        //voeg het positie paneel toe aan het linker paneel
        p = new PanelPositie();
        p.setBorder(null);
        p.setPreferredSize(new Dimension(getScreenWidth(51f), getScreenHeight(46.3f)));
        p3.add(p);

        //voeg het bpp paneel toe aan het linker paneel
        p3.add(GetBPPPanel());
        //voeg het linker paneel toe aan de linker kant van het subpaneel
        p2.add(p3);
        //voeg aan de rechter kant het productpaneel toe
        p2.add(GetProductPanel());

        //voeg het subpaneel toe aan het hoofdframe
        f.add(p2);

        //maak een nieuw lijntje
        p = new JPanel();
        p.setPreferredSize(new Dimension(getScreenWidth(97f), 1));
        p.setBackground(Color.BLACK);
        f.add(p);

        //maak een footer aan
        p = new JPanel();
        p.setPreferredSize(new Dimension(getScreenWidth(975f), getScreenHeight(4.6f)));

        //voeg een knop toe aan de footer
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(getScreenWidth(10f), getScreenHeight(3f)));
        p2.setLayout(new GridLayout(1,1));
        jb_annuleer.addActionListener(this);
        jb_annuleer.setFont(new Font("Arial", Font.PLAIN, 17));
        p2.add(jb_annuleer);
        p.add(p2);

        //voeg een maregin toe aan de footer
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(getScreenWidth(5.2f), getScreenHeight(4.6f)));
        p.add(p2);

        //voeg een knop toe aan de footer
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(getScreenWidth(10f), getScreenHeight(3f)));
        p2.setLayout(new GridLayout(1,1));
        jb_go.addActionListener(this);
        jb_go.setFont(new Font("Arial", Font.PLAIN, 17));
        p2.add(jb_go);
        p.add(p2);

        //voeg een maregin toe aan de footer
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(getScreenWidth(5.2f), getScreenHeight(4.6f)));
        p.add(p2);

        //voeg een knop toe aan de footer
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(getScreenWidth(10f), getScreenHeight(3f)));
        p2.setLayout(new GridLayout(1,1));
        jb_pakbonnenMaken.addActionListener(this);
        jb_pakbonnenMaken.setFont(new Font("Arial", Font.PLAIN, 17));
        p2.add(jb_pakbonnenMaken);
        p.add(p2);

        //voeg de footer toe aan de frame
        f.add(p);

        //voeg het frame toe aan het scherm
        add(f);
    }

    public JPanel GetHeaderPanel(){
        //maak een nieuw subpaneel aan
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(4.6f)));

        //voeg de tekst voor de order toe
        JPanel p2 = new JPanel();
        Dimension d = new Dimension(getScreenWidth(20.8f), getScreenHeight(3.8f));

        p2.setPreferredSize(d);
        JLabel l = new JLabel("Order " + i_orderID);
        l.setFont(new Font("Arial", Font.BOLD, 25));
        p2.add(l);
        p.add(p2);

        //voeg de datum toe
        p2 = new JPanel();
        p2.setPreferredSize(d);
        JLabel jlDate = new JLabel("Datum: ");
        jlDate.setFont(new Font("Arial", Font.BOLD, 25));
        l = new JLabel("" + d_date.getDate() + "-" + (d_date.getMonth() + 1) + "-" + (d_date.getYear()+1900));
        l.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(jlDate);
        p2.add(l);
        p.add(p2);

        //voeg een leeg maneel toe voor de margin
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(getScreenWidth(11.82f), getScreenHeight(2.8f)));
        p2.setBackground(new Color(236,236,236));
        p.add(p2);

        //voeg de tekst klant toe
        l = new JLabel("Klant: ");
        l.setFont(new Font("Arial", Font.BOLD, 30));
        p.add(l);

        d = new Dimension(getScreenWidth(15f), getScreenHeight(3.8f));

        //voeg een vakje voor de klantennaam toe
        p2 = new JPanel();
        p2.setBounds(getScreenWidth(30f), getScreenHeight(3.8f), getScreenWidth(20f), getScreenHeight(3.8f));
        l = new JLabel(s_customerName + ", " + i_customerID);
        l.setFont(new Font("Arial", Font.PLAIN, 25));
        l.setBounds(getScreenWidth(10f), getScreenHeight(2.8f), getScreenWidth(20f), getScreenHeight(2.8f));
        p2.add(l);
        p.add(p2);

        //voeg een vakje voor de customer id toe
        p2 = new JPanel();
        p2.setPreferredSize(d);
        l = new JLabel();
        l.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(l);
        p.add(p2);

        //return het paneel
        return p;
    }

    public JPanel GetProductPanel(){
        //maak een nieuw subpaneel aan
        JPanel p = new JPanel();

        //maak nog een paneel aan voor de informatie
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(getScreenWidth(31.25f), getScreenHeight(69.4f)));
        p2.setBorder(new LineBorder(Color.black, 1));

        //maak nog een paneel aan voor het aantal producten en voeg deze toe aan de informatiepaneel
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(getScreenWidth(26f), getScreenHeight(2.8f)));
        JLabel jlTotalProducts = new JLabel("          Totaal aantal producten: " + o_order.getProducts().size() + "          ");
        jlTotalProducts.setFont(new Font("Arial", Font.BOLD, 16));
        p3.add(jlTotalProducts);
//        p3.setFont(new Font("Arial", Font.BOLD, 12));
        p2.add(p3);
        //maak een nieuw paneel aan voor de producten verzameld en voeg deze ook toe aan de informatiepaneel
        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(getScreenWidth(26f), getScreenHeight(2.8f)));
        JLabel jlTotalCollected = new JLabel("          Producten verzameld: 0 " +  "          ");
        jlTotalCollected.setFont(new Font("Arial", Font.PLAIN, 14));
        p3.add(jlTotalCollected);
        p2.add(p3);

        //voeg een lijntje toe
        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(getScreenWidth(31.25f), 1));
        p3.setBackground(Color.black);
        p2.add(p3);

        //voeg de informatie toe aan het paneel
        for(int i = 0; i<o_order.getProducts().size(); i++){
            JPanel jpProductInfo = new JPanel();
            JLabel jlNameAndNumberText = new JLabel("Naam, artikelnr: ");
            JLabel jlNameAndNumber = new JLabel(o_order.getProducts().get(i).getProductName() + ", " + o_order.getProducts().get(i).getProductID());
            JLabel jlLocationText = new JLabel("Stelling: ");
            JLabel jlLocation = new JLabel(String.valueOf(o_order.getProducts().get(i).getBinLocation()));
            JLabel jlWeightText = new JLabel("Gewicht: ");
            JLabel jlWeight = new JLabel(String.valueOf(o_order.getProducts().get(i).getWeight()));

            jpProductInfo.setLayout(null);
            jpProductInfo.setPreferredSize(new Dimension(getScreenWidth(31.25f), getScreenHeight(10f)));
            jpProductInfo.setBorder(new LineBorder(Color.black));
            jpProductInfo.setBackground(Color.pink);

            jlNameAndNumberText.setFont(new Font("Arial", Font.BOLD, 15));
            jlNameAndNumberText.setBounds(getScreenWidth(1f), getScreenHeight(2f), getScreenWidth(29f), getScreenHeight(2f));
            jpProductInfo.add(jlNameAndNumberText);

            jlNameAndNumber.setFont(new Font("Arial", Font.PLAIN, 14));
            jlNameAndNumber.setBounds(getScreenWidth(7.5f), getScreenHeight(2f), getScreenWidth(29f), getScreenHeight(2f));
            jpProductInfo.add(jlNameAndNumber);

            jlLocationText.setFont(new Font("Arial", Font.BOLD, 15));
            jlLocationText.setBounds(getScreenWidth(1f), getScreenHeight(4f), getScreenWidth(29f), getScreenHeight(2f));
            jpProductInfo.add(jlLocationText);

            jlLocation.setFont(new Font("Arial", Font.PLAIN, 14));
            jlLocation.setBounds(getScreenWidth(4.5f), getScreenHeight(4f), getScreenWidth(29f), getScreenHeight(2f));
            jpProductInfo.add(jlLocation);

            jlWeightText.setFont(new Font("Arial", Font.BOLD, 15));
            jlWeightText.setBounds(getScreenWidth(1f), getScreenHeight(6f), getScreenWidth(29f), getScreenHeight(2f));
            jpProductInfo.add(jlWeightText);

            jlWeight.setFont(new Font("Arial", Font.PLAIN, 15));
            jlWeight.setBounds(getScreenWidth(4.8f), getScreenHeight(6f), getScreenWidth(29f), getScreenHeight(2f));
            jpProductInfo.add(jlWeight);

            p2.add(jpProductInfo);
        }

        //voeg het informatiepaneel toe aan het subpaneel
        p.add(p2);
        //return het subpaneel
        return p;
    }

    public JPanel GetBPPPanel(){
        //maak een nieuwe paneel aan
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(getScreenWidth(41.7f), getScreenHeight(27.8f)));

        PanelBins panelBins = new PanelBins();
        p.add(panelBins);

//        JPanel p3 = new JPanel();
//        p3.setPreferredSize(new Dimension(getScreenWidth(21.8f), getScreenHeight(27.8f)));
//        p3.setBackground(Color.blue);
//        p.add(p3);

//        for(int i = 1; i < 3; i++){
//            PanelBins panelBins = new PanelBins();
//            p3.add(panelBins);
//        }
        //voeg de boxjes toe
//        for(int i = 1; i < 3; i++){
//            //voeg een nieuw paneel toe
////            JPanel p2 = new JPanel();
////            p2.setPreferredSize(new Dimension(getScreenWidth(10.4f), getScreenHeight(27.8f)));
////            p2.setBackground(Color.pink);
//
//            //zet een boxje in het paneel
//            PanelBins panelBins = new PanelBins();
////            p3.setPreferredSize(new Dimension(getScreenWidth(9.9f), getScreenHeight(23.15f)));
////            p3.setBorder(new LineBorder(Color.black, 1));
//
//            //voeg een label toe aan het paneel
//            JLabel l = new JLabel("Doos " + i);
//
//            //voeg vervolgens alles toe
//            p3.add(panelBins);
//            p3.add(l);
//            //maak een nieuw paneel aan voor de maregin
//            JPanel p4 = new JPanel();
//            p4.setPreferredSize(new Dimension(getScreenWidth(5.2f), 1));
//            //voeg de rest toe
//            p3.add(p4);
//            p.add(p3);
//        }
        //return het subpaneel
        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        //als er op de knop gedrukt wordt, dan wordt een actie toegevoegd aan de database
        if(e.getSource() == jb_go){
           Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "1", "Heeft op Go gedrukt!"}); // in het logboek wordt opgeslagen dat er op Go gedrukt is (Joëlle)
        }
        
        //ga naar het pakbonnenmakenschermUPDATE orders SET OrderCompleted = RAND(1)
        if(e.getSource() == jb_pakbonnenMaken){
            FrameController.setActiveFramePackingList(this, o_order);
            Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "1", "Pakbon is gemaakt van order " + o_order.getOrderID()}); // in het logboek wordt opgeslagen dat pakbon is gemaakt (Joëlle)
        }

        if(e.getSource() == jb_annuleer){
            System.out.println("er is op de annuleerknop gedrukt");
        }
    }

    public static Order getO_order() {
        return o_order;
    }
}
