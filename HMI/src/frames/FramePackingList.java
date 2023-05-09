package frames;

import panels.ProductsInBoxPanel;
import testClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class FramePackingList extends FrameHeader implements ActionListener {
    private Order order; //Order waarvan het frame is
    private JButton jb_export; //Button om te exporteren als PDF

    public FramePackingList(Order order) {
        this.order = order;

        //Informatie voor het hele frame (Sarah)
        super.setTitle("JavaApplication/CreatePackingList");
        closeProgram();
        setLayout(null);

        //Ordernummer opvragen en stylen (Sarah)
        JLabel jl_order = new JLabel("Order " + order.getOrderID() + ":");
        add(jl_order);
        jl_order.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeOrder = jl_order.getPreferredSize();
        jl_order.setBounds(100, 5, sizeOrder.width + 10, sizeOrder.height);

        //Datum opvragen, omzetten naar NL format en stylen (Sarah)
        Date date = order.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        JLabel jl_date = new JLabel(strDate);
        add(jl_date);
        jl_date.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeDate = jl_date.getPreferredSize();
        jl_date.setBounds(250, 5, sizeDate.width + 10, sizeDate.height);

        //Naam en nummer van klant opvragen en stylen (Sarah)
        JLabel jl_customer = new JLabel("Klant: " + order.getCustomer().getCustomerName() + ", " + order.getCustomer().getCustomerID());
        add(jl_customer);
        jl_customer.setFont(new Font("Arial", Font.PLAIN, 27));
        Dimension sizeCustomer = jl_customer.getPreferredSize();
        jl_customer.setBounds(100, 40, sizeCustomer.width + 10, sizeCustomer.height);

        //Doosnummer aanmaken en stylen (Sarah)
        //TODO daadwerkelijk verschillende dozen met nummers aanmaken dmv BinPacking, ipv deze placeholder
        JLabel jl_box = new JLabel("Doosnummer: 1");
        add(jl_box);
        jl_box.setFont(new Font("Arial", Font.PLAIN, 27));
        Dimension sizeBox = jl_box.getPreferredSize();
        jl_box.setBounds(100, 70, sizeBox.width + 10, sizeBox.height);

        //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
        JPanel jp_productListPanel = new JPanel();
        jp_productListPanel.setPreferredSize(new Dimension(1320, 75 * order.getProducts().size()));
        jp_productListPanel.setLayout(null);
        Dimension sizeProductListPanel = jp_productListPanel.getPreferredSize();
        JScrollPane jsp_productList = new JScrollPane(jp_productListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsp_productList);
        jsp_productList.setBounds(100, 100, sizeProductListPanel.width + 10, 600);

        //productListPanel voorzien van panels die de productinformatie weergeven (Sarah)
        for (int i = 0; i < order.getProducts().size(); i++) {
            ProductsInBoxPanel jp_productsInBoxPanel = new ProductsInBoxPanel(order, i);
            jp_productListPanel.add(jp_productsInBoxPanel);
            Dimension sizeProductsPanel = jp_productsInBoxPanel.getPreferredSize();
            jp_productsInBoxPanel.setBounds(0, sizeProductsPanel.height * i, sizeProductsPanel.width + 10, sizeProductsPanel.height);
        }

        //Button om pakbon te exporteren als PDF-bestand (Sarah)
        jb_export = new JButton("Exporteer als PDF");
        add(jb_export);
        jb_export.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_export.setBounds(1200, 720, 200, 40);
        jb_export.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        //Als er op "Exporteer als PDF" wordt gedrukt: (Sarah)
        if(e.getSource() == jb_export){
            //Melding die laat zien dat de download geslaagd is (Sarah)
            //TODO daadwerkelijk PDF-bestand aanmaken en downloaden
            JLabel jl_exported = new JLabel("Download is gelukt");
            jl_exported.setFont(new Font("Arial", Font.BOLD, 20));
            jl_exported.setForeground(Color.green);
            jl_exported.setBounds(1000, 720, 250, 40);
            add(jl_exported);
            repaint();
        }
    }
}