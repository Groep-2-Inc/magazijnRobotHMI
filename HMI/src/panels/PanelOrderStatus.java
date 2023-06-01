package panels;

import classes.*;
import frames.FrameHeader;
import frames.FrameVerwerken;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.lang.reflect.Array;

//door Jason Joshua van der Kolk
public class PanelOrderStatus extends JPanel {
    private Font arial14B = new Font("Arial", Font.BOLD, 14);
    private static boolean picking = false;
    private JPanel p = new JPanel();
    private JLabel noOrder = new JLabel("geen informatie om te tonen");
    private static Order order;
    JPanel panel;

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
        p.add(noOrder);
        p.setLayout(new FlowLayout());
        p.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 580)); //TODO hoogte schalen met de hoeveelheid dingen die we erin stoppen

        //maar het scrollpane en voeg uiteindelijk het scrollpane toe.
        JScrollPane s = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        s.getVerticalScrollBar().setUnitIncrement(14);
        s.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(1080, 358))));
        add(s);
    }

    // Wanneer op 'go' of 'annuleer' knop wordt gedrukt in FrameVerwerken zet hij variabelen en repaint de paintComponent (Sarah)
    public void setOrder(boolean p, Order o){
        order = o;
        picking = p;
        repaint();
    }

    // paintComponent die de producten 'tekent' in de panel (Sarah)
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Als 'go' knop in FrameVerwerken is ingedrukt (dus picking), haalt hij foutmelding weg en toont alle producten vanuit FrameVerwerken (Sarah)
        if(picking){
            //voeg de informatie toe
            noOrder.setVisible(false);
            for(int i = 0; i<order.getProductCount(); i++) {
                panel = FrameVerwerken.setProductsInfo(i);
                p.add(panel);
            }
        } else{

            // Als hij geen order moet picken, geeft hij foutmelding en verwijdert alle JPanel components(dus producten) uit de panel (Sarah)
            noOrder.setVisible(true);
            Component[] componentsPanel = p.getComponents();
            for(Component c : componentsPanel){
                System.out.println(c);
                if(c instanceof JPanel){
                    p.remove(c);
                }
            }
        }
    }
}
