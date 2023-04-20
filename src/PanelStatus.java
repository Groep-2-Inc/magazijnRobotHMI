import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelStatus extends JPanel {
    private JButton jb_verbonden = new JButton("Verbonden");
    private JButton jb_rust = new JButton("Rust");
    private JButton jb_productOphalen = new JButton("Product Ophalen");
    private JButton jb_inBeweging = new JButton("In Beweging");
    private JButton jb_nood = new JButton("NOOD");
    private JButton jb_productAfgeven = new JButton("Product Afgeven");
    private JButton jb_productTerugzetten = new JButton("Product Terugzetten");
    private JButton jb_empty1 = new JButton("");
    private JButton jb_empty2 = new JButton("");
    public PanelStatus(){
        setPreferredSize(new Dimension(960,540));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 3, 20, 20));
        p.setMaximumSize(new Dimension(900,480));
        p.setMinimumSize(new Dimension(900,480));
        p.setPreferredSize(new Dimension(900,480));

        jb_verbonden.setFont(new Font("Arial", Font.PLAIN, 30));
        jb_rust.setFont(new Font("Arial", Font.PLAIN, 30));
        jb_productOphalen.setFont(new Font("Arial", Font.PLAIN, 30));
        jb_inBeweging.setFont(new Font("Arial", Font.PLAIN, 30));
        jb_nood.setFont(new Font("Arial", Font.PLAIN, 30));
        jb_productAfgeven.setFont(new Font("Arial", Font.PLAIN, 30));
        jb_empty1.setFont(new Font("Arial", Font.PLAIN, 30));
        jb_empty2.setFont(new Font("Arial", Font.PLAIN, 30));
        jb_productTerugzetten.setFont(new Font("Arial", Font.PLAIN, 30));

        jb_verbonden.setBackground(Color.lightGray);
        jb_rust.setBackground(Color.lightGray);
        jb_productOphalen.setBackground(Color.lightGray);
        jb_inBeweging.setBackground(Color.lightGray);
        jb_nood.setBackground(Color.lightGray);
        jb_productAfgeven.setBackground(Color.lightGray);
        jb_empty1.setBackground(Color.lightGray);
        jb_empty2.setBackground(Color.lightGray);
        jb_productTerugzetten.setBackground(Color.lightGray);

        p.add(jb_verbonden);
        p.add(jb_rust);
        p.add(jb_productOphalen);
        p.add(jb_inBeweging);
        p.add(jb_nood);
        p.add(jb_productAfgeven);
        p.add(jb_empty1);
        p.add(jb_empty2);
        p.add(jb_productTerugzetten);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(p);
        box.add(Box.createVerticalGlue());

        add(box);


    }
}
