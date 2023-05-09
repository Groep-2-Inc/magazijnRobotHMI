package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import database.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import classes.*;
import panels.*;

public class FrameOrders extends FrameHeader implements ActionListener {
    private JLabel jl_orderLabel = new JLabel("Orders"); // label voor titel Orders(Joëlle)
    private JLabel jl_sortLabel = new JLabel("Sortering:"); // label voor titel sortering (Joëlle)
    private JLabel jl_orderText = new JLabel("Order"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)
    private JLabel jl_customerText = new JLabel("Naam, klantnummer"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)
    private JLabel jl_productsText = new JLabel("Producten"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)
    private JLabel jl_productsQuantityText = new JLabel("(aantal)"); // label voor in het titel panel van de scrollpanel: tekst aantal (Joëlle)
    private JLabel jlDateText = new JLabel("Datum");  // label voor in het titel panel van de scrollpanel: tekst datum (Joëlle)
    private JButton jb_search = new JButton("Zoeken"); // butten voor het zoeken (Joëlle)
    private JButton jb_ordersAanmaken = new JButton("Order aanmaken"); //button voor orders aanmaken (Jason Joshua)
    private JTextField jtf_customerNumber = new JTextField("Klantnummer", 10); // tekstveld voor het klantnummer (Joëlle)
    private JTextField jtf_orderNumber = new JTextField("Ordernummer", 10); // tekstveld voor het ordernummer (Joëlle)
    private JComboBox jcb_sort; //combobox voor het sorteren (Joëlle)
    private Font arial22 = new Font("Arial", Font.PLAIN, 22);
    private Font arial17 = new Font("Arial", Font.PLAIN, 17);
    private Font arial15 = new Font("Arial", Font.PLAIN, 15);
    private ArrayList<JButton> buttons = new ArrayList<>(); // alle buttons uit het panel komen in deze lijst (Joëlle)
    private ArrayList<Order> orders = new ArrayList<>(); // arraylist waarin alle orders staan (Joëlle)

    public FrameOrders(){
        //standaard instellingen (Joëlle)
        setTitle("Java-application/Orders"); // moet nog een betere naam hebben (Joëlle)

        getOrderData();

        // Verplaats naar eigen methode om code overzichtelijker te maken
        ordersPanelHeader();
        ordersPanelTabelHeader();
        ordersPanelTabel();
    }

    // Dynamisch opbouwen uit de database
    // Door Martijn
    private void getOrderData(){
        // Haalt alle orders op en zet het in een JSONArray
        JSONArray allOrders = Database.getDbData("SELECT orders.OrderID, orders.CustomerID, orders.OrderDate, customers.CustomerName FROM orders JOIN customers ON orders.CustomerID = customers.CustomerID", new String[]{});
        // Voor elke order
        for(Object singelOrderData: allOrders){
            // Zet het Object om naar een JSON-object
            JSONObject orderData = (JSONObject) singelOrderData;

            // Maak een nieuwe customer aan met data uit de order
            Customer customer = new Customer(Integer.parseInt((String) orderData.get("CustomerID")), String.valueOf(orderData.get("CustomerName")));

            // Haalt de orderlines van deze order op
            JSONArray orderLinesData = Database.getDbData("SELECT orderlines.StockitemID, orderlines.Quantity, stockitems.StockItemName, stockitemimages.ImagePath FROM orderlines JOIN stockitems ON orderlines.StockitemID = stockitems.StockItemID JOIN stockitemimages ON orderlines.StockItemID = stockitemimages.StockItemID WHERE orderlines.OrderID = ?", new String[]{(String) orderData.get("OrderID")});
            // Maak een lege products arrayList aan
            // Deze wordt later gevuld met products
            ArrayList<Product> products = new ArrayList<>();

            // VOor elke orderline
            for(Object singleOrderLine: orderLinesData){
                // Zet het Object om naar een JSON-object
                JSONObject orderLineData = (JSONObject) singleOrderLine;
                // Maak een nieuw product aan op basis van data uit de orderline
                // En zet deze in de products arraylist
                products.add(new Product(Integer.parseInt((String) orderLineData.get("StockItemID")), (String) orderLineData.get("StockItemName"), (String) orderLineData.get("ImagePath"), Integer.parseInt((String) orderLineData.get("Quantity"))));
            }

            // Maak een nieuwe default date aan
            Date orderDate = new Date(2013, 1, 1);
            try{
                // Probeer een nieuwe date format aan te maken
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // Zet de String uit de order om naar een Date object
                orderDate = sdf.parse(String.valueOf(orderData.get("OrderDate")));
            }catch (ParseException pe){
                System.out.println(getClass() + "FrameOrders(): " + pe);
            }

            //Maak een nieuwe order object aan en voeg hem toe aan de orders arrayList
            orders.add(new Order(Integer.parseInt((String) orderData.get("OrderID")), customer, products, orderDate));
        }
    }

    // Verplaats naar eigen methode om code overzichtelijker te maken
    // Door Martijn
    private void ordersPanelHeader(){
        //panel voor de buttons aanmaken en de juiste grootte meegeven (Joëlle)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(5.5f))); // procenten toegevoegd (Joëlle)

        //label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_orderLabel.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeOrder = jl_orderLabel.getPreferredSize();
        jl_orderLabel.setBounds(0, getScreenHeight(0.8f), sizeOrder.width + 10, sizeOrder.height);
        headerPanel.add(jl_orderLabel);

        //label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_sortLabel.setFont(arial17);
        Dimension sizeSortLabel = jl_sortLabel.getPreferredSize();
        jl_sortLabel.setBounds(getScreenWidth(42f), getScreenHeight(1.4f), sizeSortLabel.width +10, sizeSortLabel.height);
        headerPanel.add(jl_sortLabel);

        //Combobox aanmaken en waarde toekennen, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jcb_sort = new JComboBox(new String[]{"Ordernummer oplopend", "Ordernummer aflopend", "Datum oplopend", "Datum aflopend", "Voltooid", "Onvoltooid"});
        jcb_sort.setBackground(Color.white);
        jcb_sort.setBounds(getScreenWidth(42f) + sizeSortLabel.width +10, getScreenHeight(1.1f), getScreenWidth(11.39322917f), getScreenHeight(3f));
        headerPanel.add(jcb_sort);

        //Tekstveld toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jtf_customerNumber.setBounds(getScreenWidth(60f), getScreenHeight(1.1f), getScreenWidth(7.8125f), getScreenHeight(3f));
        headerPanel.add(jtf_customerNumber);

        //Tekstveld toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jtf_orderNumber.setBounds(getScreenWidth(69f), getScreenHeight(1.1f), getScreenWidth(7.8125f), getScreenHeight(3f));
        headerPanel.add(jtf_orderNumber);

        // Buttun toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jb_search.setFont(arial17);
        jb_search.addActionListener(this); // actionlistener toevoegen aan button (Joëlle)
        jb_search.setBounds(getScreenWidth(77.5f), getScreenHeight(1.1f), getScreenWidth(10f), getScreenHeight(3f));
        headerPanel.add(jb_search);

        //button toevoegen voor order aanmaken, door Jason Joshua van der Kolk
        jb_ordersAanmaken.setFont(arial17);
        jb_ordersAanmaken.addActionListener(this);
        jb_ordersAanmaken.setBounds(getScreenWidth(88f), getScreenHeight(1.1f), getScreenWidth(10f), getScreenHeight(3f));
        headerPanel.add(jb_ordersAanmaken);

        super.add(headerPanel); // PanelButtons toevoegen aan het hoofdscherm (Joëlle)
    }

    private void ordersPanelTabelHeader(){
        //Panel aanmaken voor de titels, juiste grootte en kleur meegeven (Joëlle)
        JPanel panelTitles = new JPanel();
        panelTitles.setLayout(null);
        panelTitles.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(4f))); // procenten toegevoegd( Joëlle)
        panelTitles.setBackground(Color.lightGray);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_orderText.setFont(arial22);
        Dimension sizeOrderText = jl_orderText.getPreferredSize();
        jl_orderText.setBounds(getScreenWidth(3.255208333f), getScreenHeight(1.157407407f), sizeOrderText.width +10, sizeOrderText.height);
        panelTitles.add(jl_orderText);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_customerText.setFont(arial22);
        Dimension sizeCustomerText = jl_customerText.getPreferredSize();
        jl_customerText.setBounds(getScreenWidth(26.04166667f), getScreenHeight(1.157407407f), sizeCustomerText.width +10, sizeCustomerText.height);
        panelTitles.add(jl_customerText);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_productsText.setFont(arial22);
        Dimension sizeProductenText = jl_productsText.getPreferredSize();
        jl_productsText.setBounds(getScreenWidth(48.828125f), getScreenHeight(1.157407407f), sizeProductenText.width +10, sizeProductenText.height);
        panelTitles.add(jl_productsText);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_productsQuantityText.setFont(arial15);
        Dimension sizeProductsQuantityText = jl_productsQuantityText.getPreferredSize();
        jl_productsQuantityText.setBounds(getScreenWidth(49.47916667f) +sizeProductenText.width, getScreenHeight(1.736111111f), sizeProductsQuantityText.width +10, sizeProductsQuantityText.height);
        panelTitles.add(jl_productsQuantityText);

        //Label voor Datum aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlDateText.setFont(arial22);
        Dimension sizeDateText = jlDateText.getPreferredSize();
        jlDateText.setBounds(getScreenWidth(71.61458333f), getScreenHeight(1.157407407f), sizeDateText.width +10, sizeDateText.height);
        panelTitles.add(jlDateText);

        super.add(panelTitles); //Panel van de titels toevoegen aan het hoofdscherm (Joëlle)
    }

    private void ordersPanelTabel(){
        //Panel aanmaken, waar het scrollpanel inkomt (Joëlle)
        JPanel panelTabel = new JPanel();
        panelTabel.setLayout(new FlowLayout());
        panelTabel.setPreferredSize(new Dimension(getScreenWidth(98f), FrameHeader.getScreenHeight(7.45f) * orders.size())); // procenten toegevoegd( Joëlle)

        // For loop waar eerst een button toegevoegd wordt aan de arraylist, dan wordt in deze button een panel toegevoegd (Joëlle)
        // en wordt de juiste grootte meegegeven (Joëlle)
        for (int i = 0; i < orders.size(); i++) {
            buttons.add(new JButton());

            JPanel panelOrderRow = new PanelOrder(orders.get(i));
            buttons.get(i).add(panelOrderRow);
            buttons.get(i).setBackground(Color.white);
            Dimension sizeOrderPanel = panelOrderRow.getPreferredSize();
            panelOrderRow.setBounds(getScreenHeight(0f), sizeOrderPanel.height * i, sizeOrderPanel.width, sizeOrderPanel.height);
            panelTabel.add(buttons.get(i));

            Dimension sizeButtonOrder = buttons.get(i).getPreferredSize();
            buttons.get(i).setBounds(getScreenHeight(0f), sizeButtonOrder.height * i, sizeButtonOrder.width, sizeButtonOrder.height);

            buttons.get(i).addActionListener(this); //aangepast door Jason Joshua van der Kolk
        }

        //Aanmaken scrollPane, juiste grootte meegeven en de vertical scrollbar en toevoegen aan het scherm (Joëlle)
        JScrollPane scrollPane = new JScrollPane(panelTabel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(70f))); // procenten toegevoegd( Joëlle)
        super.add(scrollPane);
    }

    //aangepast door Jason Joshua van der Kolk
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);

        //als op een knop wordt gedrukt
        for (int i = 0; i < orders.size(); i++) {
            if(e.getSource() == buttons.get(i)){
                FrameController.setActiveViewingOrder(this, orders.get(i));
            }
        }

        //naar het ordersaanmaken frame
        if(e.getSource() == jb_ordersAanmaken){
            FrameController.setActiveFrameMakeOrder(this);
        }

        //kijken of er op de search knop gedrukt is
        if(e.getSource() == jb_search){
            System.out.println("gedrukt op search knop in orders frame");
        }
    }
}