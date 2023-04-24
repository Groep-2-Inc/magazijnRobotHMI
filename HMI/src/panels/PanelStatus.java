package panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

//door Jason Joshua van der Kolk
public class PanelStatus extends JPanel {
    private JButton jb_verbonden = new JButton("Verbonden"); //melding voor verbonden
    private JButton jb_rust = new JButton("Rust"); //melding voor rust
    private JButton jb_productOphalen = new JButton("Product Ophalen"); //melding voor product ophalen
    private JButton jb_inBeweging = new JButton("In Beweging"); //melding voor in beweging
    private JButton jb_nood = new JButton("NOOD"); //melding voor nood
    private JButton jb_productAfgeven = new JButton("Product Afgeven"); //melding voor product afgeven
    private JButton jb_productTerugzetten = new JButton("Product Terugzetten"); //melding voor terugzetten
    private JButton jb_handmatige = new JButton("Handmatig"); //melding voor handmatig besturing
    private JButton jb_empty2 = new JButton(""); //voor nu lege melding

    public PanelStatus(){
        //initialiseer het hoofd paneel
        setPreferredSize(new Dimension(960,540));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //voeg kleiner paneel voor de meldingen
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 3, 20, 20));
        p.setMaximumSize(new Dimension(900,480));
        p.setMinimumSize(new Dimension(900,480));
        p.setPreferredSize(new Dimension(900,480));

        //zet het font voor alle meldingen
        jb_verbonden.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_rust.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_productOphalen.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_inBeweging.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_nood.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_productAfgeven.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_handmatige.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_empty2.setFont(new Font("Arial", Font.PLAIN, 27));
        jb_productTerugzetten.setFont(new Font("Arial", Font.PLAIN, 27));

        //zet de background voor alle meldingen
        jb_verbonden.setBackground(Color.lightGray);
        jb_rust.setBackground(Color.lightGray);
        jb_productOphalen.setBackground(Color.lightGray);
        jb_inBeweging.setBackground(Color.lightGray);
        jb_nood.setBackground(Color.lightGray);
        jb_productAfgeven.setBackground(Color.lightGray);
        jb_handmatige.setBackground(Color.lightGray);
        jb_empty2.setBackground(Color.lightGray);
        jb_productTerugzetten.setBackground(Color.lightGray);

        //voeg alle meldingen toe
        p.add(jb_verbonden);
        p.add(jb_rust);
        p.add(jb_productOphalen);
        p.add(jb_inBeweging);
        p.add(jb_nood);
        p.add(jb_productAfgeven);
        p.add(jb_handmatige);
        p.add(jb_empty2);
        p.add(jb_productTerugzetten);

        //voeg nieuwe box toe om het paneel in het midde te laten zitten
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(p);
        box.add(Box.createVerticalGlue());

        //voeg deze box toe.
        add(box);


    }
}
