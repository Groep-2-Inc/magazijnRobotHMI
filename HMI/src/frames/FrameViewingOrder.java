package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import classes.*;
import panels.*;
public class FrameViewingOrder extends FrameHeader implements ActionListener {
    private Order order; //Order waarvan het frame is
    private JButton jb_change, jb_cancel, jb_pick, jb_save, jb_back; //Buttons die in het scherm gebruikt worden
    private ArrayList<PanelProduct> productPanels = new ArrayList<>(); //Arraylist van de afzonderlijke productPanels
    private Font arial30B = new Font("Arial", Font.BOLD, 30);
    private Font arial17 = new Font("Arial", Font.PLAIN, 17);
    private Font arial24 = new Font("Arial", Font.PLAIN, 24);

    public FrameViewingOrder(Order order) {
        this.order = order;

        //Informatie voor het hele frame (Sarah)
        super.setTitle("JavaApplication/FrameViewingOrder");

        closeProgram();

        setLayout(null);

        //Pijltje terug (naar FrameOrders) aanmaken en stylen (Sarah)
        //TODO pijltje terug moet nog werkend gemaakt worden
        ImageIcon arrowBack = new ImageIcon(Objects.requireNonNull(getClass().getResource("../images/arrowLeft.png")));
        jb_back = new JButton(arrowBack);
        jb_back.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 10)), getScreenWidth(getPercentage(1536, 40)), getScreenHeight(getPercentage(864, 28)));
        jb_back.setOpaque(false);
        jb_back.setContentAreaFilled(false);
        jb_back.setBorderPainted(false);
        jb_back.addActionListener(this);
        add(jb_back);

        //Ordernummer opvragen en stylen (Sarah)
        JLabel jl_order = new JLabel("Order " + order.getOrderID());
        jl_order.setFont(arial30B);
        Dimension sizeOrder = jl_order.getPreferredSize();
        jl_order.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 50)), sizeOrder.width + getScreenWidth(getPercentage(1536, 10)), sizeOrder.height);
        add(jl_order);

        //Datum opvragen, omzetten naar NL format en stylen (Sarah)
        Date date = order.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        JLabel jl_date = new JLabel(strDate);
        jl_date.setFont(arial24);
        Dimension sizeDate = jl_date.getPreferredSize();
        jl_date.setBounds(getScreenWidth(getPercentage(1536, 210)), getScreenHeight(getPercentage(864, 55)), sizeDate.width + getScreenWidth(getPercentage(1536, 10)), sizeDate.height);
        add(jl_date);

        //Naam en nummer van klant opvragen en stylen (Sarah)
        // JLabel jl_customer = new JLabel("Klant: " + order.getCustomer().getCustomerName() + ", " + order.getCustomer().getCustomerID());
        // jl_customer.setFont(arial30B);
        JLabel jl_customer = new JLabel("Klant: " + order.getCustomer().getCustomerName() + ", " + order.getCustomer().getCustomerID());
        jl_customer.setFont(arial24);
        Dimension sizeCustomer = jl_customer.getPreferredSize();
        jl_customer.setBounds(getScreenWidth(getPercentage(1536, 810)), getScreenHeight(getPercentage(864, 55)), sizeCustomer.width + + getScreenWidth(getPercentage(1536, 10)), sizeCustomer.height);
        add(jl_customer);

        //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
        JPanel jp_productListPanel = new JPanel();
        jp_productListPanel.setPreferredSize(new Dimension(getScreenWidth(97f), getScreenHeight(getPercentage(864, 150)) * order.getProducts().size()));
        jp_productListPanel.setLayout(null);
        Dimension sizeProductListPanel = jp_productListPanel.getPreferredSize();
        JScrollPane jsp_productList = new JScrollPane(jp_productListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp_productList.getVerticalScrollBar().setUnitIncrement(14);
        jsp_productList.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 90)), sizeProductListPanel.width + getScreenWidth(getPercentage(1536, 10)), getScreenHeight(getPercentage(864, 600)));
        add(jsp_productList);

        //productListPanel voorzien van panels die de productinformatie weergeven (Sarah)
        for (int i = 0; i < order.getProducts().size(); i++) {
            PanelProduct jp_productsPanel = new PanelProduct(order, i);
            Dimension sizeProductsPanel = jp_productsPanel.getPreferredSize();
            jp_productsPanel.setBounds(0, sizeProductsPanel.height * i, sizeProductsPanel.width + getScreenWidth(getPercentage(1536, 200)), sizeProductsPanel.height);
            productPanels.add(jp_productsPanel);
            jp_productListPanel.add(jp_productsPanel);
        }

        //Button om productlijst te bewerken aanmaken en stylen (zichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_change = new JButton("Bewerken");
        jb_change.setFont(arial17);
        jb_change.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 700)), getScreenWidth(10f), getScreenHeight(3f));
        jb_change.addActionListener(this);
        add(jb_change);

        //Button om order te picken aanmaken en stylen (zichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        //TODO knop om order te picken moet nog werkend gemaakt worden
        jb_pick = new JButton("Pick");
        jb_pick.setFont(arial17);
        jb_pick.setBounds(getScreenWidth(getPercentage(1536, 210)), getScreenHeight(getPercentage(864, 700)), getScreenWidth(10f), getScreenHeight(3f));
        jb_pick.addActionListener(this);
        add(jb_pick);

        //Button om aanpassingen in productlijst op te slaan aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_save = new JButton("Opslaan");
        jb_save.setFont(arial17);
        jb_save.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 700)), getScreenWidth(10f), getScreenHeight(3f));
        jb_save.addActionListener(this);
        jb_save.setVisible(false);
        add(jb_save);

        //Button om aanpassingen in productlijst te annuleren aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_cancel = new JButton("Annuleren");
        jb_cancel.setFont(arial17);
        jb_cancel.setBounds(getScreenWidth(getPercentage(1536, 210)), getScreenHeight(getPercentage(864, 700)), getScreenWidth(10f), getScreenHeight(3f));
        jb_cancel.addActionListener(this);
        jb_cancel.setVisible(false);
        add(jb_cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        //Als op "bewerken" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_change) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            jb_cancel.setVisible(true);
            jb_save.setVisible(true);
            jb_pick.setVisible(false);
            jb_change.setVisible(false);

            //Kruisje om producten uit lijst te verwijderen wordt zichtbaar en aantal producten kan bewerkt worden (Sarah)
            for (int i = 0; i < order.getProducts().size(); i++) {
                productPanels.get(i).editDelete(true);
                productPanels.get(i).removeAll();
                productPanels.get(i).editAmount(Color.lightGray, BorderFactory.createLineBorder(Color.BLUE, 1), true);
            }
        }

        //Als op "Opslaan" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_save) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            jb_change.setVisible(true);
            jb_pick.setVisible(true);
            jb_save.setVisible(false);
            jb_cancel.setVisible(false);

            //Kruisje om producten uit lijst te verwijderen wordt onzichtbaar en aantal producten kan niet meer worden aangepast (wijzigingen worden opgeslagen) (Sarah)
            for (int i = 0; i < order.getProducts().size(); i++) {
                productPanels.get(i).editDelete(false);
                productPanels.get(i).removeAll();
                productPanels.get(i).editAmount(Color.white, null, false);

                //Aanpassingen aan aantal producten worden opgeslagen, errors worden afgevangen (Sarah)
                try {
                    try {
                        order.getProducts().get(i).setStock(Integer.parseInt(productPanels.get(i).getJtf_amount().getText()));
                    } catch (NumberFormatException NFE) {
                        //Foutmelding als er geen nummer wordt ingevoerd (Sarah)
                        JLabel jl_invalid = new JLabel("Ongeldige waarde");
                        jl_invalid.setFont(new Font("Arial", Font.BOLD, 20));
                        jl_invalid.setForeground(Color.red);
                        jl_invalid.setBounds(getScreenWidth(getPercentage(1536, 500)), getScreenHeight(getPercentage(864, 670)), getScreenWidth(getPercentage(1536, 250)), getScreenHeight(getPercentage(864, 40)));
                        add(jl_invalid);
                    }
                } catch (NullPointerException NPE) {}
            }
        }

        //Als op "Annuleren" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_cancel) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            jb_change.setVisible(true);
            jb_pick.setVisible(true);
            jb_save.setVisible(false);
            jb_cancel.setVisible(false);

            //Kruisje om producten uit lijst te verwijderen wordt onzichtbaar en aantal producten kan niet meer worden aangepast (wijzigingen worden niet opgeslagen)  (Sarah)
            for (int i = 0; i < order.getProducts().size(); i++) {
                productPanels.get(i).editDelete(false);
                productPanels.get(i).removeAll();
                productPanels.get(i).editAmount(Color.white, null, false);
            }
        }

        //naar pick scherm, door Jason Joshua van der Kolk
        if (e.getSource() == jb_pick){
            FrameController.setActiveFrameVerwerken(this, order);
        }

        if(e.getSource() == jb_back){
            System.out.println("er is op de terug knop gedrukt");
        }

    }
}
