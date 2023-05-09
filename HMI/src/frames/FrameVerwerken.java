package frames;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import panels.PanelPositie;
import panels.PanelStatus;
import testClasses.Order;

//door Jason Joshua van der Kolk
public class FrameVerwerken extends FrameHeader implements ActionListener {
    private int i_orderID = 1; //int voor order id
    private Date d_date = new Date(2000, Calendar.JANUARY, 1); //date voor datum
    private String s_customerName = "customerName"; // string voor de customer name
    private int i_customerID = 1; //int voor de customer id
    private int i_totaalAantalProducten = 1; //int voor totaal aantal producten
    private int i_productenVerzameld = 1; //int voor het aantal producten dat al verzameld is
    private JButton jb_annuleer = new JButton("Annuleer"); //button voor het annuleeren
    private JButton jb_go = new JButton("GO!"); //button voor het starten van het pakken van de orders
    private JButton jb_pakbonnenMaken = new JButton("Pakbonnen maken"); //button voor het aanmaken van de pakbonnen
    private Order o_order;


    public FrameVerwerken(Order order){
        //initializeer alle nodige variabelen.
        o_order = order;
        this.i_orderID = order.getOrderID();
        this.i_customerID = order.getCustomer().getCustomerID();
        this.i_totaalAantalProducten = order.getProductAmount();
        this.s_customerName = order.getCustomer().getCustomername();
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
        p.setPreferredSize(new Dimension(getScreenWidth(41.66666666666f), getScreenHeight(46.3f)));
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
//        setVisible(true);
    }

    public JPanel GetHeaderPanel(){
        //maak een nieuw subpaneel aan
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(4.6f)));

        //voeg de tekst voor de order toe
        JPanel p2 = new JPanel();
        Dimension d = new Dimension(getScreenWidth(7.8f), getScreenHeight(3.8f));
        LineBorder b;

        p2.setPreferredSize(d);
        JLabel l = new JLabel("Order:");
        l.setFont(new Font("Arial", Font.BOLD, 25));
        p2.add(l);
        p.add(p2);

        //maak een paneel aan en definieer de dimension en border
        p2 = new JPanel();
        d = new Dimension(getScreenWidth(7.8f), getScreenHeight(3.8f));
        b = new LineBorder(Color.BLACK, 1);

        //voeg de order id toe aan het paneel en voeg vervolgens het poneel toe aan het subpaneel
        p2.setPreferredSize(d);
        l = new JLabel("" + i_orderID);
        l.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(l);
        p.add(p2);

        //voeg een dubbele punt toe aan het paneel
        p2 = new JPanel();
        p2.setPreferredSize(d);
        l = new JLabel(":");
        l.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(l);
        p.add(p2);

        //voeg de datum toe
        p2 = new JPanel();
        p2.setPreferredSize(d);
        l = new JLabel("" + d_date.getDate() + "-" + (d_date.getMonth() + 1) + "-" + (d_date.getYear()+1900));
        l.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(l);
//        p2.setBorder(b);
        p.add(p2);

        //voeg een leeg maneel toe voor de margin
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(getScreenWidth(29f), getScreenHeight(2.8f)));
        p.add(p2);

        //voeg de tekst klant toe
        l = new JLabel("Klant:");
        l.setFont(new Font("Arial", Font.BOLD, 30));
        p.add(l);

        d = new Dimension(getScreenWidth(15f), getScreenHeight(3.8f));

        //voeg een vakje voor de klantennaam toe
        p2 = new JPanel();
        p2.setPreferredSize(d);
        l = new JLabel(s_customerName + ", " + i_customerID);
        l.setFont(new Font("Arial", Font.PLAIN, 25));
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
        p3.add(new JLabel("          Totaal aantal producten: " + i_totaalAantalProducten + "          "));
        p2.add(p3);
        //maak een nieuw paneel aan voor de producten verzameld en voeg deze ook toe aan de informatiepaneel
        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(getScreenWidth(26f), getScreenHeight(2.8f)));
        p3.add(new JLabel("          Producten verzameld " + i_productenVerzameld + "          "));
        p2.add(p3);

        //voeg een lijntje toe
        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(getScreenWidth(31.25f), 1));
        p3.setBackground(Color.black);
        p2.add(p3);

        //voeg de informatie toe aan het paneel
        for(int i = 0; i<6; i++){
            //maak nieuwe paneeltjes aan
            JPanel p4 = new JPanel();
            JPanel p5 = new JPanel();

            //verander de border en size voor het hoofdpaneeltje van de informatie
            p4.setBorder(new LineBorder(Color.black));
            p4.setPreferredSize(new Dimension(getScreenWidth(31.25f), getScreenHeight(4.6f)));

            //voeg een paneeltje toe voor de afbeeldingen
            p5.setBorder(new LineBorder(Color.black));
            p5.setPreferredSize(new Dimension(getScreenWidth(2f), getScreenHeight(3.7f)));
            p4.add(p5);

            //voeg paneeltje toe voor de product naam en code
            p5 = new JPanel();
            p5.setLayout(new GridLayout(2,1));
            p5.setPreferredSize(new Dimension(getScreenWidth(15.6f), getScreenHeight(3.7f)));
            p5.add(new JLabel("[ProductNaam]"));
            p5.add(new JLabel("[ProductCode]"));
            p4.add(p5);

            //voeg een paneeltje toe voor de locatie
            p5 = new JPanel();
            p5.setLayout(new GridLayout(2,1));
            p5.setPreferredSize(new Dimension(getScreenWidth(5.2f), getScreenHeight(3.7f)));
            p5.add(new JLabel("Stelling:"));
            p5.add(new JLabel("[locatie]"));
            p4.add(p5);

            //voeg een paneeltje toe voor of het product gepicked is of niet
            p5 = new JPanel();
            p5.setBorder(new LineBorder(Color.black));
            p5.setPreferredSize(new Dimension(getScreenWidth(1f), getScreenHeight(2f)));
            p5.setBackground(Color.red);
            p4.add(p5);

            //voeg de panelen toe aan het informatiepaneel
            p2.add(p4);
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

        //voeg de boxjes toe
        for(int i = 1; i < 4; i++){
            //voeg een nieuw paneel toe
            JPanel p2 = new JPanel();
            p2.setPreferredSize(new Dimension(getScreenWidth(10.4f), getScreenHeight(27.8f)));

            //zet een boxje in het paneel
            JPanel p3 = new JPanel();
            p3.setPreferredSize(new Dimension(getScreenWidth(9.9f), getScreenHeight(23.15f)));
            p3.setBorder(new LineBorder(Color.black, 1));

            //voeg een label toe aan het paneel
            JLabel l = new JLabel("[Doos" + i + "]");

            //voeg vervolgens alles toe
            p2.add(p3);
            p2.add(l);
            //maak een nieuw paneel aan voor de maregin
            JPanel p4 = new JPanel();
            p4.setPreferredSize(new Dimension(getScreenWidth(5.2f), 1));
            //voeg de rest toe
            p2.add(p4);
            p.add(p2);
        }
        //return het subpaneel
        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        //ga naar het pakbonnenmakenscherm
        if(e.getSource() == jb_pakbonnenMaken){
            FrameController.setActiveFramePackingList(this, o_order);
        }

        if(e.getSource() == jb_annuleer){
            System.out.println("er is op de annuleerknop gedrukt");
        }

        if(e.getSource() == jb_go){
            System.out.println("er is op de go knop gedrukt");
        }
    }
}
