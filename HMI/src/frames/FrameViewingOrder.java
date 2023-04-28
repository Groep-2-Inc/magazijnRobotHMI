package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import testClasses.*;
import panels.*;
public class FrameViewingOrder extends FrameHeader implements ActionListener {
    private Order order; //Order waarvan het frame is
    private JButton jb_change, jb_cancel, jb_pick, jb_save, jb_back, jb_addProduct; //Buttons die in het scherm gebruikt worden
    private ArrayList<PanelProduct> productPanels = new ArrayList<>(); //Arraylist van de afzonderlijke productPanels

    public FrameViewingOrder(Order order) {
        this.order = order;

        //Informatie voor het hele frame (Sarah)
        super.setTitle("JavaApplication/FrameViewingOrder");
        FrameHeader.closeProgram();
        setLayout(null);

        //Pijltje terug (naar FrameOrders) aanmaken en stylen (Sarah)
        //TODO pijltje terug moet nog werkend gemaakt worden
        ImageIcon arrowBack = new ImageIcon(Objects.requireNonNull(getClass().getResource("../images/arrowLeft.png")));
        jb_back = new JButton(arrowBack);
        add(jb_back);
        jb_back.setBounds(40, 10, 40, 28);
        jb_back.setOpaque(false);
        jb_back.setContentAreaFilled(false);
        jb_back.setBorderPainted(false);

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
        jl_date.setBounds(300, 5, sizeDate.width + 10, sizeDate.height);

        //Naam en nummer van klant opvragen en stylen (Sarah)
        JLabel jl_customer = new JLabel("Klant: " + order.getCustomer().getCustomername() + ", " + order.getCustomer().getCustomerID());
        add(jl_customer);
        jl_customer.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeCustomer = jl_customer.getPreferredSize();
        jl_customer.setBounds(900, 5, sizeCustomer.width + 10, sizeCustomer.height);

        //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
        JPanel jp_productListPanel = new JPanel();
        jp_productListPanel.setPreferredSize(new Dimension(1320, 150 * order.getProducts().size()));
        jp_productListPanel.setLayout(null);
        Dimension sizeProductListPanel = jp_productListPanel.getPreferredSize();
        JScrollPane jsp_productList = new JScrollPane(jp_productListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsp_productList);
        jsp_productList.setBounds(100, 50, sizeProductListPanel.width + 10, 600);

        //productListPanel voorzien van panels die de productinformatie weergeven (Sarah)
        for (int i = 0; i < order.getProducts().size(); i++) {
            PanelProduct jp_productsPanel = new PanelProduct(order, i);
            jp_productListPanel.add(jp_productsPanel);
            Dimension sizeProductsPanel = jp_productsPanel.getPreferredSize();
            jp_productsPanel.setBounds(0, sizeProductsPanel.height * i, sizeProductsPanel.width + 10, sizeProductsPanel.height);
            productPanels.add(jp_productsPanel);
        }

        //Button om productlijst te bewerken aanmaken en stylen (zichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_change = new JButton("Bewerken");
        add(jb_change);
        jb_change.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_change.setBounds(100, 670, 150, 40);
        jb_change.addActionListener(this);

        //Button om order te picken aanmaken en stylen (zichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        //TODO knop om order te picken moet nog werkend gemaakt worden
        jb_pick = new JButton("Pick");
        add(jb_pick);
        jb_pick.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_pick.setBounds(300, 670, 150, 40);
        jb_pick.addActionListener(this);

        //Button om aanpassingen in productlijst op te slaan aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_save = new JButton("Opslaan");
        add(jb_save);
        jb_save.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_save.setBounds(100, 670, 150, 40);
        jb_save.addActionListener(this);
        jb_save.setVisible(false);

        //Button om aanpassingen in productlijst te annuleren aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_cancel = new JButton("Annuleren");
        add(jb_cancel);
        jb_cancel.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_cancel.setBounds(300, 670, 150, 40);
        jb_cancel.addActionListener(this);
        jb_cancel.setVisible(false);

        //Button om product toe te voegen aan productlijst aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        //TODO knop om producten toe te voegen aan productlijst moet nog werkend gemaakt worden
        jb_addProduct = new JButton("Product toevoegen");
        add(jb_addProduct);
        jb_addProduct.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_addProduct.setBounds(1200, 670, 200, 40);
        jb_addProduct.addActionListener(this);
        jb_addProduct.setVisible(false);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        //Als op "bewerken" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_change) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            jb_addProduct.setVisible(true);
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
            jb_addProduct.setVisible(false);

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
                        jl_invalid.setBounds(500, 670, 250, 40);
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
            jb_addProduct.setVisible(false);

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

    }
}
