import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelOrderStatus extends JPanel {
    public PanelOrderStatus(){
        setPreferredSize(new Dimension(960,540));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));

        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(620, 40));
        p3.setBorder(new LineBorder(Color.BLACK));
        p3.add(new Label("Order Status"));
        add(p3);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        for(int i = 0; i<20; i++){
            JPanel p2 = new JPanel();
            p2.setLayout(new GridLayout(1, 1));
            p2.setPreferredSize(new Dimension(600, 20));
            p2.setBorder(new LineBorder(Color.BLACK));
            p2.add(new Label("tekst"));
            p.add(p2);
        }

        JScrollPane s = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        s.setPreferredSize(new Dimension(600, 380));
        add(s);
    }
}
