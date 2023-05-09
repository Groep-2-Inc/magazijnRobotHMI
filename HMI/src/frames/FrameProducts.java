package frames;
// Door Sarah

import panels.PanelProductOverview;
import classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrameProducts extends FrameHeader implements ActionListener {
    private JButton jb_change, jb_save, jb_cancel; //Buttons die gebruikt worden in het scherm
    private ArrayList<PanelProductOverview> productPanels = new ArrayList<>(); //Arraylist van de afzonderlijke productPanels
    private ArrayList<Product> products; //Arraylist waarin de producten worden opgeslagen
    private Font arial17 = new Font("Arial", Font.PLAIN, 17);

    public FrameProducts(ArrayList<Product> products) {
        this.products = products;

        //Informatie voor het hele frame (Sarah)
        super.setTitle("JavaApplication/viewingProducts");
        closeProgram();

        setLayout(null);

        //Titel aanmaken en stylen (Sarah)
        JLabel jl_products = new JLabel("Producten");
        jl_products.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeOrder = jl_products.getPreferredSize();
        jl_products.setBounds(25, 5, sizeOrder.width + 10, sizeOrder.height);
        add(jl_products);

        //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
        JPanel jp_productListPanel = new JPanel();
        jp_productListPanel.setPreferredSize(new Dimension(1320, 150 * products.size()));
        jp_productListPanel.setLayout(null);
        Dimension sizeProductListPanel = jp_productListPanel.getPreferredSize();
        JScrollPane jsp_productList = new JScrollPane(jp_productListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp_productList.setBounds(25, 50, sizeProductListPanel.width + 10, 800);
        add(jsp_productList);


        //productListPanel voorzien van panels die de productinformatie weergeven (Sarah)
        for (int i = 0; i < products.size(); i++) {
            PanelProductOverview jp_productsPanel = new PanelProductOverview(products, i);
            jp_productListPanel.add(jp_productsPanel);
            Dimension sizeProductsPanel = jp_productsPanel.getPreferredSize();
            jp_productsPanel.setBounds(0, sizeProductsPanel.height * i, sizeProductsPanel.width + 10, sizeProductsPanel.height);
            productPanels.add(jp_productsPanel);
        }

        //Button om productlijst te bewerken aanmaken en stylen (zichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_change = new JButton("Bewerken");
        jb_change.setFont(arial17);
        jb_change.setBounds(25, 870, 150, 40);
        jb_change.addActionListener(this);
        add(jb_change);

        //Button om aanpassingen in productlijst op te slaan aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_save = new JButton("Opslaan");
        jb_save.setFont(arial17);
        jb_save.setBounds(25, 870, 150, 40);
        jb_save.addActionListener(this);
        jb_save.setVisible(false);
        add(jb_save);

        //Button om aanpassingen in productlijst te annuleren aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_cancel = new JButton("Annuleren");
        jb_cancel.setFont(arial17);
        jb_cancel.setBounds(225, 870, 150, 40);
        jb_cancel.addActionListener(this);
        jb_cancel.setVisible(false);
        add(jb_cancel);
    }

    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        //Als op "bewerken" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_change) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            jb_cancel.setVisible(true);
            jb_save.setVisible(true);
            jb_change.setVisible(false);

            //Instellen dat aantal producten bewerkt kan worden (Sarah)
            for (int i = 0; i < products.size(); i++) {
                productPanels.get(i).removeAll();
                productPanels.get(i).editAmount(Color.lightGray, BorderFactory.createLineBorder(Color.BLUE, 1), true);
            }
        }

        //Als op "Opslaan" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_save) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            jb_change.setVisible(true);
            jb_save.setVisible(false);
            jb_cancel.setVisible(false);

            //Instellen dat aantal producten niet meer bewerkt kan worden (wijzigingen worden opgeslagen) (Sarah)
            for (int i = 0; i < products.size(); i++) {
                productPanels.get(i).removeAll();
                productPanels.get(i).editAmount(Color.white, null, false);

                //Aanpassingen aan aantal producten worden opgeslagen, errors worden afgevangen (Sarah)
                try {
                    try {
                        products.get(i).setStock(Integer.parseInt(productPanels.get(i).getJtf_amount().getText()));
                    } catch (NumberFormatException NFE) {
                        //Foutmelding als er geen nummer wordt ingevoerd (Sarah)
                        JLabel jl_invalid = new JLabel("Ongeldige waarde");
                        jl_invalid.setFont(new Font("Arial", Font.BOLD, 20));
                        jl_invalid.setForeground(Color.red);
                        jl_invalid.setBounds(300, 870, 250, 40);
                        add(jl_invalid);
                    }
                } catch (NullPointerException NPE) {
                }
            }
        }

        //Als op "Annuleren" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_cancel) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            jb_change.setVisible(true);
            jb_save.setVisible(false);
            jb_cancel.setVisible(false);

            //Instellen dat aantal producten niet meer bewerkt kan worden (wijzigingen worden niet opgeslagen) (Sarah)
            for (int i = 0; i < products.size(); i++) {
                productPanels.get(i).removeAll();
                productPanels.get(i).editAmount(Color.white, null, false);
            }
        }
    }
}