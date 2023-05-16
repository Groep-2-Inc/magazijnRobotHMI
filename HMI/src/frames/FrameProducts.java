package frames;
// Door Sarah

import database.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import panels.PanelProductRow;
import classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrameProducts extends FrameHeader implements ActionListener {
    private JButton jb_change, jb_save, jb_cancel; //Buttons die gebruikt worden in het scherm
    private ArrayList<PanelProductRow> productPanels = new ArrayList<>(); //Arraylist van de afzonderlijke productPanels
    private ArrayList<Product> products = new ArrayList<>(); //Arraylist waarin de producten worden opgeslagen
    private Font arial17 = new Font("Arial", Font.PLAIN, 17);

    public FrameProducts() {
        //Informatie voor het hele frame (Sarah)
        super.setTitle("JavaApplication/viewingProducts");

        setLayout(null);

        getProductData();
        // Verplaats naar eigen methode om code overzichtelijker te maken
        productPanel();

        closeProgram();
    }

    //Dynamisch opbouwen uit de database
    // Door Daan
    private void getProductData(){
        // Haalt alle products op en zet het in een JSONArray
        JSONArray allProducts = Database.getDbData("SELECT si.StockItemID, si.StockItemName, siHoldings.QuantityOnHand, siHoldings.BinLocation, siImg.ImagePath FROM stockitems si JOIN stockitemholdings siHoldings ON si.StockItemID = siHoldings.StockItemID JOIN stockitemimages siImg ON si.StockItemID = siImg.StockItemID", new String[]{});
        // Voor elk product
        for(Object singleProductData : allProducts){
            // Zet het Object om naar een JSON-object
            JSONObject productData = (JSONObject) singleProductData;

            // Maak een nieuw product object aan een voegt hem toe aan de products arraylist
            products.add(new Product(Integer.parseInt((String) productData.get("StockItemID")), (String) productData.get("StockItemName"), (String) productData.get("ImagePath"), String.valueOf(productData.get("BinLocation")), Integer.parseInt((String) productData.get("QuantityOnHand"))));
        }
    }

    private void productPanel(){
        //Titel aanmaken en stylen (Sarah)
        JLabel jl_products = new JLabel("Producten");
        jl_products.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeOrder = jl_products.getPreferredSize();
        jl_products.setBounds(getScreenWidth(1.1f), getScreenHeight(0.8f), sizeOrder.width, sizeOrder.height + 10);
        add(jl_products);

        //JPanel die lijst van producten laat zien (om doorheen te scrollen) aanmaken en stylen en scrollPane van maken (Sarah)
        JPanel jp_productListPanel = new JPanel();
        jp_productListPanel.setPreferredSize(new Dimension(getScreenWidth(getPercentage(1536, 1475)), getScreenHeight(getPercentage(864, 150)) * products.size()));
        jp_productListPanel.setLayout(null);
        Dimension sizeProductListPanel = jp_productListPanel.getPreferredSize();

        JScrollPane jsp_productList = new JScrollPane(jp_productListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp_productList.getVerticalScrollBar().setUnitIncrement(14);
        jsp_productList.setBounds(getScreenWidth(1f), getScreenHeight(getPercentage(864, 50)), sizeProductListPanel.width + getScreenWidth(getPercentage(1536, 30)), getScreenHeight(getPercentage(864, 645)));
        add(jsp_productList);

        //productListPanel voorzien van panels die de productinformatie weergeven (Sarah)
        for (int i = 0; i < products.size(); i++) {
            PanelProductRow jp_productsPanel = new PanelProductRow(products.get(i));
            jp_productListPanel.add(jp_productsPanel);
            Dimension sizeProductsPanel = jp_productsPanel.getPreferredSize();
            jp_productsPanel.setBounds(0, sizeProductsPanel.height * i, sizeProductsPanel.width + sizeProductListPanel.width + getScreenWidth(getPercentage(1536, 10)), sizeProductsPanel.height);
            productPanels.add(jp_productsPanel);
        }

        //Button om productlijst te bewerken aanmaken en stylen (zichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_change = new JButton("Bewerken");
        jb_change.setFont(arial17);
        Dimension sizeProductsEdit = jb_change.getPreferredSize();
        jb_change.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 700)), sizeProductsEdit.width +20, sizeProductsEdit.height);
        jb_change.addActionListener(this);
        add(jb_change);

        //Button om aanpassingen in productlijst op te slaan aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_save = new JButton("Opslaan");
        jb_save.setFont(arial17);
        Dimension sizeProductsEditSave = jb_save.getPreferredSize();
        jb_save.setBounds(getScreenWidth(getPercentage(1536, 20)), getScreenHeight(getPercentage(864, 700)), sizeProductsEditSave.width +20, sizeProductsEditSave.height);
        jb_save.addActionListener(this);
        jb_save.setVisible(false);
        add(jb_save);

        //Button om aanpassingen in productlijst te annuleren aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        jb_cancel = new JButton("Annuleren");
        jb_cancel.setFont(arial17);
        Dimension sizeProductsEditCancel = jb_cancel.getPreferredSize();
        jb_cancel.setBounds(getScreenWidth(getPercentage(1536, 150)), getScreenHeight(getPercentage(864, 700)), sizeProductsEditCancel.width +20, sizeProductsEditCancel.height);
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
            int numberOfChangingProduct = 0;
            for (int i = 0; i < products.size(); i++) {
                productPanels.get(i).removeAll();
                productPanels.get(i).editAmount(Color.white, null, false);

                //Aanpassingen aan aantal producten worden opgeslagen, errors worden afgevangen (Sarah), try en catch samengevoegd naar één (Joëlle)
                try {
                    products.get(i).setStock(Integer.parseInt(productPanels.get(i).getJtf_amount().getText()));
                    numberOfChangingProduct = i;
                } catch (NumberFormatException | NullPointerException ex) {
                    //Foutmelding als er geen nummer wordt ingevoerd (Sarah)
                    JLabel jl_invalid = new JLabel("Ongeldige waarde");
                    jl_invalid.setFont(new Font("Arial", Font.BOLD, 20));
                    jl_invalid.setForeground(Color.red);
                    jl_invalid.setBounds(getScreenWidth(getPercentage(1536, 300)), getScreenHeight(getPercentage(864, 870)), getScreenWidth(getPercentage(1536, 150)), getScreenHeight(getPercentage(864, 40)));
                    add(jl_invalid);
                    System.out.println(getClass() + ": " + ex);
                }
            }
            Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "1", "Hoeveelheid product met nummer " + products.get(numberOfChangingProduct).getProductID() + " is aangepast"}); //!! werkt nog niet, bij foute gegevens wordt er ook teogevoegd aan database, in het logboek wordt opgeslagen dat de order is bijgewerkt (Joëlle)
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