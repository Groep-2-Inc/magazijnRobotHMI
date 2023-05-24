package frames;

import database.Database;
import panels.ProductsInBoxPanel;
import classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.text.*;
import java.util.*;

public class FramePackingList extends FrameHeader implements ActionListener {
    private Order order; //Order waarvan het frame is
    private JButton jb_export; //Button om te exporteren als PDF
    private JButton jb_back;
    private JPanel jp_productListPanel2 = new JPanel();
    private JPanel jp_productListPanel1 = new JPanel();
    private JLabel jl_box1 = new JLabel("Doosnummer: 1");
    private JLabel jl_box2 = new JLabel("Doosnummer: 2");
    private int sizeButton = 370;


    public FramePackingList(Order order) {
        this.order = order;

        //Pijltje terug (naar FrameVerwerken) aanmaken en stylen (Sarah)
        ImageIcon arrowBack = new ImageIcon(Objects.requireNonNull(getClass().getResource("../images/arrowLeft.png")));
        jb_back = new JButton(arrowBack);
        jb_back.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 3)), getScreenWidth(getPercentage(1536, 40)), getScreenHeight(getPercentage(864, 28)));
        jb_back.setOpaque(false);
        jb_back.setContentAreaFilled(false);
        jb_back.setBorderPainted(false);
        jb_back.addActionListener(this);
        add(jb_back);

        //Informatie voor het hele frame (Sarah)
        super.setTitle("JavaApplication/CreatePackingList");
        closeProgram();
        setLayout(null);

        //Ordernummer opvragen en stylen (Sarah)
        JLabel jl_order = new JLabel("Order " + order.getOrderID() + ":");
        add(jl_order);
        jl_order.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeOrder = jl_order.getPreferredSize();
        jl_order.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 30)), sizeOrder.width + getScreenWidth(getPercentage(1536, 10)), sizeOrder.height);

        //Datum opvragen, omzetten naar NL format en stylen (Sarah)
        Date date = order.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        JLabel jl_date = new JLabel(strDate);
        add(jl_date);
        jl_date.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeDate = jl_date.getPreferredSize();
        jl_date.setBounds(getScreenWidth(getPercentage(1536, 170)), getScreenHeight(getPercentage(864, 30)), sizeDate.width + getScreenWidth(getPercentage(1536, 10)), sizeDate.height);

        //Naam en nummer van klant opvragen en stylen (Sarah)
        JLabel jl_customer = new JLabel("Klant: " + order.getCustomer().getCustomerName() + ", " + order.getCustomer().getCustomerID());
        add(jl_customer);
        jl_customer.setFont(new Font("Arial", Font.PLAIN, 27));
        Dimension sizeCustomer = jl_customer.getPreferredSize();
        jl_customer.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 65)), sizeCustomer.width + getScreenWidth(getPercentage(1536, 10)), sizeCustomer.height);

        boolean variableSecondPart = false;
        ArrayList<Bin> copyListBins1 = Bin.getBins("First Fit");
        //for loop die de dozen langs loopt (Joëlle)
        for(int i = 0; i< copyListBins1.size(); i++) {
            //in het geval van de eerste doos (Joëlle)
            if (i == 0) {
                //Doosnummer 1aanmaken en stylen (Sarah)
                //TODO daadwerkelijk verschillende dozen met nummers aanmaken dmv BinPacking, ipv deze placeholder
                add(jl_box1);
                jl_box1.setFont(new Font("Arial", Font.PLAIN, 27));
                Dimension sizeBox1 = jl_box1.getPreferredSize();
                jl_box1.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 95)), sizeBox1.width + getScreenWidth(getPercentage(1536, 10)), sizeBox1.height);

                //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
                jp_productListPanel1.setPreferredSize(new Dimension(getScreenWidth(getPercentage(1536, 1496)), getScreenHeight(getPercentage(864, 100)) * copyListBins1.get(i).producten.size()));
                jp_productListPanel1.setLayout(null);
                Dimension sizeProductListPanel1 = jp_productListPanel1.getPreferredSize();
                JScrollPane jsp_productList1 = new JScrollPane(jp_productListPanel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jsp_productList1.getVerticalScrollBar().setUnitIncrement(14);
                add(jsp_productList1);
                jsp_productList1.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 125)), sizeProductListPanel1.width + getScreenWidth(getPercentage(1536, 10)), getScreenHeight(getPercentage(864, 230)));

                //for loop die de producten uit de doos één voor één toont (Joëlle)
                for (int j = 0; j < copyListBins1.get(i).producten.size(); j++) {
                    ProductsInBoxPanel jp_productsInBoxPanel1 = new ProductsInBoxPanel(copyListBins1.get(i).producten.get(j));
                    jp_productListPanel1.add(jp_productsInBoxPanel1);
                    Dimension sizeProductsPanel1 = jp_productsInBoxPanel1.getPreferredSize();
                    jp_productsInBoxPanel1.setBounds(0, sizeProductsPanel1.height * j, sizeProductsPanel1.width + getScreenWidth(getPercentage(1536, 10)), sizeProductsPanel1.height);
                }
                //in het geval van de tweede doos (Joëlle)
            } else {
                //Doosnummer 2 aanmaken en stylen (Joëlle)
                add(jl_box2);
                jl_box2.setFont(new Font("Arial", Font.PLAIN, 27));
                Dimension sizeBox2 = jl_box2.getPreferredSize();
                jl_box2.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 360)), sizeBox2.width + getScreenWidth(getPercentage(1536, 10)), sizeBox2.height);

                //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
                jp_productListPanel2.setPreferredSize(new Dimension(getScreenWidth(getPercentage(1536, 1496)), getScreenHeight(getPercentage(864, 100)) * copyListBins1.get(i).producten.size()));
                jp_productListPanel2.setLayout(null);
                Dimension sizeProductListPanel2 = jp_productListPanel2.getPreferredSize();
                JScrollPane jsp_productList2 = new JScrollPane(jp_productListPanel2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jsp_productList2.getVerticalScrollBar().setUnitIncrement(14);
                add(jsp_productList2);
                jsp_productList2.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 400)), sizeProductListPanel2.width + getScreenWidth(getPercentage(1536, 10)), getScreenHeight(getPercentage(864, 230)));

                //for loop die de producten uit de doos één voor één toont (Joëlle)
                for (int j = 0; j < copyListBins1.get(i).producten.size(); j++) {
                    ProductsInBoxPanel jp_productsInBoxPanel2 = new ProductsInBoxPanel(copyListBins1.get(i).producten.get(j));
                    jp_productListPanel2.add(jp_productsInBoxPanel2);
                    Dimension sizeProductsPanel2 = jp_productsInBoxPanel2.getPreferredSize();
                    jp_productsInBoxPanel2.setBounds(0, sizeProductsPanel2.height * j, sizeProductsPanel2.width + getScreenWidth(getPercentage(1536, 10)), sizeProductsPanel2.height);
                }
            }
        }

        if(copyListBins1.size() > 1){
            sizeButton = 645;
        }

        //Button om pakbon te exporteren als PDF-bestand (Sarah)
        jb_export = new JButton("Exporteer als PDF");
        add(jb_export);
        jb_export.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_export.setBounds(getScreenWidth(getPercentage(1536, 1360)), getScreenHeight(getPercentage(864, sizeButton)), getScreenWidth(10f), getScreenHeight(3f));
        jb_export.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        // Als er op "Exporteer als PDF" wordt gedrukt: (Sarah)
        if(e.getSource() == jb_export){
            Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "1", "De pakbon is ge-exporteerd als PDF!"}); // in het logboek wordt opgeslagen dat pakbon is ge-exporteerd als PDF(Joëlle)
            //Melding die laat zien dat de download geslaagd is (Sarah)
            //TODO daadwerkelijk PDF-bestand(en) aanmaken en downloaden
            JLabel jl_exported = new JLabel("Download is gelukt");
            jl_exported.setFont(new Font("Arial", Font.BOLD, 20));
            jl_exported.setForeground(Color.green);
            jl_exported.setBounds(getScreenWidth(getPercentage(1536, 1200)), getScreenHeight(getPercentage(864, sizeButton)), getScreenWidth(10f), getScreenHeight(3f));
            add(jl_exported);
            repaint();
        }

        // Terug naar het FrameVerwerken (Sarah)
        if (e.getSource() == jb_back) {
            FrameController.setActiveFrameVerwerken(this, order);
        }
    }
}
