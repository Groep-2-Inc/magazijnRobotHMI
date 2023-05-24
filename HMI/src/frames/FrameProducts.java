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
    private ArrayList<PanelProductRow> productPanels = new ArrayList<>(); //Arraylist van de afzonderlijke productPanels
    private ArrayList<Product> products = new ArrayList<>(); //Arraylist waarin de producten worden opgeslagen
    private Font arial17 = new Font("Arial", Font.PLAIN, 17);
    JPanel jp_productListPanel = new JPanel();
    private JScrollPane jsp_productList = new JScrollPane(jp_productListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public FrameProducts() {
        //Informatie voor het hele frame (Sarah)
        super.setTitle("HMI-applicatie");

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
        JSONArray allProducts = Database.getDbData("SELECT si.StockItemID, si.StockItemName, siHoldings.QuantityOnHand, siHoldings.BinLocation, siImg.ImagePath, si.Size FROM stockitems si JOIN stockitemholdings siHoldings ON si.StockItemID = siHoldings.StockItemID JOIN stockitemimages siImg ON si.StockItemID = siImg.StockItemID", new String[]{});
        // Voor elk product
        for(Object singleProductData : allProducts){
            // Zet het Object om naar een JSON-object
            JSONObject productData = (JSONObject) singleProductData;

            // Maak een nieuw product object aan een voegt hem toe aan de products arraylist
            products.add(new Product(Integer.parseInt((String) productData.get("StockItemID")), (String) productData.get("StockItemName"), (String) productData.get("ImagePath"), Integer.parseInt((String) productData.get("QuantityOnHand")), String.valueOf(productData.get("BinLocation")), Integer.parseInt((String) productData.get("Size"))));
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
        jp_productListPanel.setPreferredSize(new Dimension(getScreenWidth(getPercentage(1536, 1475)), getScreenHeight(getPercentage(864, 150)) * products.size()));
        jp_productListPanel.setLayout(null);
        Dimension sizeProductListPanel = jp_productListPanel.getPreferredSize();

        //productListPanel voorzien van panels die de productinformatie weergeven (Sarah)
        for (int i = 0; i < products.size(); i++) {
            PanelProductRow jp_productsPanel = new PanelProductRow(products.get(i));
            jp_productListPanel.add(jp_productsPanel);
            Dimension sizeProductsPanel = jp_productsPanel.getPreferredSize();
            jp_productsPanel.setBounds(0, sizeProductsPanel.height * i, sizeProductsPanel.width + sizeProductListPanel.width + getScreenWidth(getPercentage(1536, 10)), sizeProductsPanel.height);
            productPanels.add(jp_productsPanel);
        }

        jsp_productList.getVerticalScrollBar().setUnitIncrement(14);
        jsp_productList.setBounds(getScreenWidth(1f), getScreenHeight(getPercentage(864, 50)), sizeProductListPanel.width + getScreenWidth(getPercentage(1536, 30)), getScreenHeight(getPercentage(864, 645)));
        add(jsp_productList);
    }
}