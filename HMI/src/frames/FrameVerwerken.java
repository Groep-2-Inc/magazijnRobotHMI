package frames;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import panels.PanelPositie;
import panels.PanelStatus;

//door Jason Joshua van der Kolk
public class FrameVerwerken extends FrameHeader{
    private int i_orderID = 1;
    private Date d_date = new Date(2000, Calendar.JANUARY, 1);
    private String s_customerName = "customerName";
    private int i_customerID = 1;
    private int i_totaalAantalProducten = 1;
    private int i_productenVerzameld = 1;
    private JButton jb_annuleer = new JButton("Annuleer");
    private JButton jb_go = new JButton("GO!");
    private JButton jb_pakbonnenMaken = new JButton("Pakbonnen maken");


    public FrameVerwerken(){
        JPanel f = new JPanel();
        f.setPreferredSize(new Dimension(1920, 1080));

        JPanel p = GetHeaderPanel();
        f.add(p);

        p = new JPanel();
        p.setPreferredSize(new Dimension(1700, 1));
        p.setBackground(Color.BLACK);
        f.add(p);

        JPanel p2 = new JPanel(new GridLayout(1, 2));
        p2.setPreferredSize(new Dimension(1700, 800));
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(850, 800));

        p = new PanelPositie();
        p.setBorder(null);
        p.setPreferredSize(new Dimension(800, 500));
        p3.add(p);
        p3.add(GetBPPPanel());
        p2.add(p3);
        p2.add(GetProductPanel());

        f.add(p2);

        p = new JPanel();
        p.setPreferredSize(new Dimension(1700, 1));
        p.setBackground(Color.BLACK);
        f.add(p);

        p = new JPanel();
        p.setPreferredSize(new Dimension(1700, 50));

        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(150, 40));
        p2.setLayout(new GridLayout(1,1));
        p2.add(jb_annuleer);
        p.add(p2);

        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(100, 50));
        p.add(p2);

        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(150, 40));
        p2.setLayout(new GridLayout(1,1));
        p2.add(jb_go);
        p.add(p2);

        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(100, 50));
        p.add(p2);

        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(150, 40));
        p2.setLayout(new GridLayout(1,1));
        p2.add(jb_pakbonnenMaken);
        p.add(p2);

        f.add(p);

        add(f);
        setVisible(true);
    }

    public JPanel GetHeaderPanel(){
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(1920, 50));

        JLabel l = new JLabel("Order");
        l.setFont(new Font("Arial", Font.BOLD, 30));
        p.add(l);

        JPanel p2 = new JPanel();
        Dimension d = new Dimension(150, 30);
        LineBorder b = new LineBorder(Color.BLACK, 1);

        p2.setPreferredSize(d);
        p2.add(new JLabel("" + i_orderID));
        p2.setBorder(b);
        p.add(p2);

        p2 = new JPanel();
        p2.setPreferredSize(d);
        p2.add(new JLabel(":"));
        p.add(p2);

        p2 = new JPanel();
        p2.setPreferredSize(d);
        p2.add(new JLabel("" + d_date.getDate() + "/" + (d_date.getMonth() + 1) + "/" + d_date.getYear()));
        p2.setBorder(b);
        p.add(p2);

        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(400, 30));
        p.add(p2);

        l = new JLabel("Klant:");
        l.setFont(new Font("Arial", Font.BOLD, 30));
        p.add(l);

        p2 = new JPanel();
        p2.setPreferredSize(d);
        p2.add(new JLabel(s_customerName));
        p2.setBorder(b);
        p.add(p2);

        p2 = new JPanel();
        p2.setPreferredSize(d);
        p2.add(new JLabel("" + i_customerID));
        p2.setBorder(b);
        p.add(p2);

        return p;
    }

    public JPanel GetProductPanel(){
        JPanel p = new JPanel();

        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(600, 750));
        p2.setBorder(new LineBorder(Color.black, 1));

        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(500, 30));
        p3.add(new JLabel("          totaal aantal producten: " + i_totaalAantalProducten + "          "));
        p2.add(p3);
        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(500, 30));
        p3.add(new JLabel("          Producten verzameld " + i_productenVerzameld + "          "));
        p2.add(p3);

        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(600, 1));
        p3.setBackground(Color.black);
        p2.add(p3);

        for(int i = 0; i<6; i++){
            JPanel p4 = new JPanel();
            JPanel p5 = new JPanel();

            p4.setBorder(new LineBorder(Color.black));
            p4.setPreferredSize(new Dimension(600, 50));

            p5.setBorder(new LineBorder(Color.black));
            p5.setPreferredSize(new Dimension(40, 40));
            p4.add(p5);

            p5 = new JPanel();
            p5.setLayout(new GridLayout(2,1));
            p5.setPreferredSize(new Dimension(300, 40));
            p5.add(new JLabel("[ProductNaam]"));
            p5.add(new JLabel("[ProductCode]"));
            p4.add(p5);

            p5 = new JPanel();
            p5.setLayout(new GridLayout(2,1));
            p5.setPreferredSize(new Dimension(100, 40));
            p5.add(new JLabel("Stelling:"));
            p5.add(new JLabel("[locatie]"));
            p4.add(p5);

            p5 = new JPanel();
            p5.setBorder(new LineBorder(Color.black));
            p5.setPreferredSize(new Dimension(20, 20));
            p5.setBackground(Color.red);
            p4.add(p5);

            p2.add(p4);
        }



        p.add(p2);
        return p;
    }

    public JPanel GetBPPPanel(){
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(800, 300));

        for(int i = 1; i < 4; i++){
            JPanel p2 = new JPanel();
            p2.setPreferredSize(new Dimension(200, 300));

            JPanel p3 = new JPanel();
            p3.setPreferredSize(new Dimension(190, 250));
            p3.setBorder(new LineBorder(Color.black, 1));

            JLabel l = new JLabel("[Doos" + i + "]");

            p2.add(p3);
            p2.add(l);
            JPanel p4 = new JPanel();
            p4.setPreferredSize(new Dimension(100, 1));
            p2.add(p4);
            p.add(p2);
        }

        return p;
    }
}
