package frames;

import database.Database;
import panels.PanelPackinglistSingleProduct;
import classes.*;

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
        super.setTitle("HMI-applicatie");
        closeProgram();
        setLayout(null);

        //Ordernummer opvragen en stylen (Sarah)
        JLabel jl_order = new JLabel("Order " + order.getOrderID() + ":");
        add(jl_order);
        jl_order.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeOrder = jl_order.getPreferredSize();
        jl_order.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 5)), sizeOrder.width + getScreenWidth(getPercentage(1536, 10)), sizeOrder.height);

        //Datum opvragen, omzetten naar NL format en stylen (Sarah)
        Date date = order.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        JLabel jl_date = new JLabel(strDate);
        add(jl_date);
        jl_date.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeDate = jl_date.getPreferredSize();
        jl_date.setBounds(getScreenWidth(getPercentage(1536, 170)), getScreenHeight(getPercentage(864, 5)), sizeDate.width + getScreenWidth(getPercentage(1536, 10)), sizeDate.height);

        //Naam en nummer van klant opvragen en stylen (Sarah)
        JLabel jl_customer = new JLabel("Klant: " + order.getCustomer().getCustomerName() + ", " + order.getCustomer().getCustomerID());
        add(jl_customer);
        jl_customer.setFont(new Font("Arial", Font.PLAIN, 27));
        Dimension sizeCustomer = jl_customer.getPreferredSize();
        jl_customer.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 40)), sizeCustomer.width + getScreenWidth(getPercentage(1536, 10)), sizeCustomer.height);

        //Doosnummer aanmaken en stylen (Sarah)
        //TODO daadwerkelijk verschillende dozen met nummers aanmaken dmv BinPacking, ipv deze placeholder
        JLabel jl_box = new JLabel("Doosnummer: 1");
        add(jl_box);
        jl_box.setFont(new Font("Arial", Font.PLAIN, 27));
        Dimension sizeBox = jl_box.getPreferredSize();
        jl_box.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 70)), sizeBox.width + getScreenWidth(getPercentage(1536, 10)), sizeBox.height);

        //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
        JPanel jp_productListPanel = new JPanel();
        jp_productListPanel.setPreferredSize(new Dimension(getScreenWidth(getPercentage(1536, 1496)), getScreenHeight(getPercentage(864, 75)) * order.getProducts().size()));
        jp_productListPanel.setLayout(null);
        Dimension sizeProductListPanel = jp_productListPanel.getPreferredSize();
        JScrollPane jsp_productList = new JScrollPane(jp_productListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp_productList.getVerticalScrollBar().setUnitIncrement(14);
        add(jsp_productList);
        jsp_productList.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 100)), sizeProductListPanel.width + getScreenWidth(getPercentage(1536, 10)), getScreenHeight(getPercentage(864, 600)));

        //productListPanel voorzien van panels die de productinformatie weergeven (Sarah)
        for (int i = 0; i < order.getProducts().size(); i++) {
            PanelPackinglistSingleProduct jp_productsInBoxPanel = new PanelPackinglistSingleProduct(order, i);
            jp_productListPanel.add(jp_productsInBoxPanel);
            Dimension sizeProductsPanel = jp_productsInBoxPanel.getPreferredSize();
            jp_productsInBoxPanel.setBounds(0, sizeProductsPanel.height * i, sizeProductsPanel.width + getScreenWidth(getPercentage(1536, 10)), sizeProductsPanel.height);
        }

        //Button om pakbon te exporteren als PDF-bestand (Sarah)
        jb_export = new JButton("Exporteer als PDF");
        add(jb_export);
        jb_export.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_export.setBounds(getScreenWidth(getPercentage(1536, 1360)), getScreenHeight(getPercentage(864, 720)), getScreenWidth(10f), getScreenHeight(3f));
        jb_export.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        //Als er op "Exporteer als PDF" wordt gedrukt: (Sarah)
        if(e.getSource() == jb_export){
            Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "1", "De pakbon is geëxporteerd als PDF!"}); // in het logboek wordt opgeslagen dat pakbon is ge-exporteerd als PDF(Joëlle)
            //Melding die laat zien dat de download geslaagd is (Sarah)
            //TODO daadwerkelijk PDF-bestand aanmaken en downloaden
            JLabel jl_exported = new JLabel("Download is gelukt");
            jl_exported.setFont(new Font("Arial", Font.BOLD, 20));
            jl_exported.setForeground(Color.green);
            jl_exported.setBounds(getScreenWidth(getPercentage(1536, 1000)), getScreenHeight(getPercentage(864, 720)), getScreenWidth(getPercentage(1536, 250)), getScreenHeight(getPercentage(864, 40)));
            add(jl_exported);
            repaint();

        }
    }
}