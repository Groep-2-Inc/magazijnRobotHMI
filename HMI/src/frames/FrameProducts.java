package frames;

import panels.PanelProductOverview;
import testClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrameProducts extends FrameHeader implements ActionListener {
    private JButton jb_change, jb_save, jb_cancel; //Buttons die gebruikt worden in het scherm
    private ArrayList<PanelProductOverview> productPanels = new ArrayList<>(); //Arraylist van de afzonderlijke productPanels
    private ArrayList<Product> products; //Arraylist waarin de producten worden opgeslagen

    public FrameProducts(ArrayList<Product> products) {
        this.products = products;

        //Informatie voor het hele frame (Sarah)
        super.setTitle("JavaApplication/viewingProducts");
        closeProgram();
        setLayout(null);

        //Titel aanmaken en stylen (Sarah)
        JLabel jl_products = new JLabel("Producten");
        add(jl_products);
        jl_products.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeOrder = jl_products.getPreferredSize();
        jl_products.setBounds(100, 5, sizeOrder.width + 10, sizeOrder.height);

        //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
        JPanel jp_productListPanel = new JPanel();
        jp_productListPanel.setPreferredSize(new Dimension(1320, 150 * products.size()));
        jp_productListPanel.setLayout(null);
        Dimension sizeProductListPanel = jp_productListPanel.getPreferredSize();
        JScrollPane jsp_productList = new JScrollPane(jp_productListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsp_productList);
        jsp_productList.setBounds(100, 50, sizeProductListPanel.width + 10, 800);

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
        add(jb_change);
        jb_change.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_change.setBounds(100, 870, 150, 40);
        jb_change.addActionListener(this);


        //Button om aanpassingen in productlijst op te slaan aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_save = new JButton("Opslaan");
        add(jb_save);
        jb_save.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_save.setBounds(100, 870, 150, 40);
        jb_save.addActionListener(this);
        jb_save.setVisible(false);

        //Button om aanpassingen in productlijst te annuleren aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_cancel = new JButton("Annuleren");
        add(jb_cancel);
        jb_cancel.setFont(new Font("Arial", Font.PLAIN, 17));
        jb_cancel.setBounds(300, 870, 150, 40);
        jb_cancel.addActionListener(this);
        jb_cancel.setVisible(false);
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