package panels;

import testClasses.*;

import javax.swing.*;
import java.awt.*;

public class ProductsInBoxPanel extends JPanel {
    private Order order; //Order waarvan hij de producten laat zien

    public ProductsInBoxPanel(Order order, int index) {
        this.order = order;

        //Informatie voor het hele panel (Sarah)
        setPreferredSize(new Dimension(1320, 75));
        setLayout(null);

        //ProductID opvragen en stylen (Sarah)
        JLabel jl_productID = new JLabel(String.valueOf(this.order.getProducts().get(index).getProductID()));
        jl_productID.setFont(new Font("Arial", Font.PLAIN, 17));
        Dimension sizeProductID = jl_productID.getPreferredSize();
        jl_productID.setBounds(50, 30, sizeProductID.width + 10, sizeProductID.height);
        add(jl_productID);

        //Productnaam opvragen en stylen (Sarah)
        JLabel jl_productname = new JLabel(this.order.getProducts().get(index).getProductname());
        jl_productname.setFont(new Font("Arial", Font.PLAIN, 17));
        Dimension sizeProductname = jl_productname.getPreferredSize();
        jl_productname.setBounds(200, 30, sizeProductname.width + 10, sizeProductname.height);
        add(jl_productname);

        //Producthoeveelheid opvragen en stylen (Sarah)
        JLabel jl_amount = new JLabel("Hoeveelheid in doos: " + order.getProducts().get(index).getStock());
        jl_amount.setFont(new Font("Arial", Font.PLAIN, 17));
        Dimension sizeJLAmount = jl_amount.getPreferredSize();
        jl_amount.setBounds(1100, 30, sizeJLAmount.width + 10, sizeJLAmount.height);
        add(jl_amount);

        setVisible(true);
    }

    //Achtergrondkleur en lijnen om panel heen zettenn (Sarah)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.white);
        g.drawLine(0, 0, 1720, 0);
        g.drawLine(0, 149, 1720, 149);
    }
}