package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import comparator.*;
import database.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import classes.*;
import panels.*;

public class FrameOrders extends FrameHeader implements ActionListener {
    private JLabel jlOrderLabel = new JLabel("Orders"); // label voor titel Orders(Joëlle)
    private JLabel jlSortLabel = new JLabel("Sortering:"); // label voor titel sortering (Joëlle)
    private JLabel jlOrderText = new JLabel("Order"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)
    private JLabel jlCustomerText = new JLabel("Naam"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)
    private JLabel jlCustomerTextNumber = new JLabel("(klantnummer)"); // label voor in het titel panel van de scrollpanel: tekst producten
    private JLabel jlProductsText = new JLabel("Producten"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)
    private JLabel jlProductsQuantityText = new JLabel("(aantal)"); // label voor in het titel panel van de scrollpanel: tekst aantal (Joëlle)
    private JLabel jlStatusText = new JLabel("Status"); // label voor in het titel panel van de scrollpane: tekst status (Joëlle)
    private JLabel jlDateText = new JLabel("Datum");  // label voor in het titel panel van de scrollpanel: tekst datum (Joëlle)
    private JButton jbSearch = new JButton("Zoeken"); // butten voor het zoeken (Joëlle)
    private JTextField jtfCustomerNumber = new JTextField("Klantnummer", 10); // tekstveld voor het klantnummer (Joëlle)
    private JTextField jtfOrderNumber = new JTextField("Ordernummer", 10); // tekstveld voor het ordernummer (Joëlle)
    private JComboBox jcbSort; //combobox voor het sorteren (Joëlle)
    private Font arial22 = new Font("Arial", Font.PLAIN, 22);
    private Font arial17 = new Font("Arial", Font.PLAIN, 17);
    private Font arial15 = new Font("Arial", Font.PLAIN, 15);
    private ArrayList<JButton> buttons = new ArrayList<>(); // alle buttons uit het panel komen in deze lijst (Joëlle)
    private ArrayList<Order> orders = new ArrayList<>(); // arraylist waarin alle orders staan (Joëlle)

    public FrameOrders(){
        //standaard instellingen (Joëlle)
        setTitle("Java-application/Orders"); // moet nog een betere naam hebben (Joëlle)

        getOrderData(null,null);

        // Verplaats naar eigen methode om code overzichtelijker te maken
        ordersPanelHeader();
        ordersPanelTabelHeader();
        ordersPanelTabel();
    }

    // Dynamisch opbouwen uit de database
    // Door Martijn
    private void getOrderData(String type, String searchValue){
        JSONArray allOrders;
        orders.clear();

        // Maakt de basis query, wat bij elke query hetzelfde is
        String baseQuery = "SELECT orders.OrderID, orders.CustomerID, orders.OrderDate, customers.CustomerName, customers.DeliveryAddressLine2, customers.DeliveryPostalCode, cities.CityName, orders.OrderCompleted FROM orders JOIN customers ON orders.CustomerID = customers.CustomerID JOIN cities ON cities.CityID = customers.PostalCityID";
        // Als hij moet zoeken naar customers
        if(type != null && type.equals("customers")){
            // Haalt alle orders op waarbij klantnummer lijkt op zoekwaarde
            allOrders = Database.getDbData(baseQuery + " WHERE orders.CustomerID LIKE ? ORDER BY orders.OrderID DESC", new String[]{searchValue});
        }else if(type != null && type.equals("orders")){
            // Haalt alle orders op en zet het in een JSONArray
            allOrders = Database.getDbData(baseQuery + " WHERE orders.OrderID LIKE ? ORDER BY orders.OrderID DESC", new String[]{searchValue});
        }else{
            // Haalt alle orders op en zet het in een JSONArray
            allOrders = Database.getDbData(baseQuery + " ORDER BY orders.OrderID DESC", new String[]{});
        }

        // Voor elke order
        for(Object singleOrderData: allOrders){
            // Zet het Object om naar een JSON-object
            JSONObject orderData = (JSONObject) singleOrderData;

            // Maak een nieuwe customer aan met data uit de order
            Customer customer = new Customer(Integer.parseInt((String) orderData.get("CustomerID")), String.valueOf(orderData.get("CustomerName")), (String) orderData.get("DeliveryAddressLine2"), (String) orderData.get("DeliveryPostalCode"), (String) orderData.get("CityName"));

            // Haalt de orderlines van deze order op
            JSONArray orderLinesData = Database.getDbData("SELECT orderlines.StockitemID, orderlines.Quantity, stockitems.StockItemName, stockitemimages.ImagePath, stockitems.Size, stockitemholdings.BinLocation FROM orderlines JOIN stockitems ON orderlines.StockitemID = stockitems.StockItemID JOIN stockitemimages ON orderlines.StockItemID = stockitemimages.StockItemID JOIN stockitemholdings ON orderlines.StockItemID = stockitemholdings.StockItemID WHERE orderlines.OrderID = ?", new String[]{(String) orderData.get("OrderID")});
            // Maak een lege products arrayList aan
            // Deze wordt later gevuld met products
            ArrayList<Product> products = new ArrayList<>();

            // VOor elke orderline
            for(Object singleOrderLine: orderLinesData){
                // Zet het Object om naar een JSON-object
                JSONObject orderLineData = (JSONObject) singleOrderLine;
                // Maak een nieuw product aan op basis van data uit de orderline
                // En zet deze in de products arraylist
                products.add(new Product(Integer.parseInt((String) orderLineData.get("StockItemID")), (String) orderLineData.get("StockItemName"), (String) orderLineData.get("ImagePath"), Integer.parseInt((String) orderLineData.get("Quantity")),Integer.parseInt((String) orderLineData.get("Size")), String.valueOf(orderLineData.get("BinLocation")) ));
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
            orders.add(new Order(Integer.parseInt((String) orderData.get("OrderID")), customer, products, orderDate, Integer.parseInt((String) orderData.get("OrderCompleted"))));
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
        jlOrderLabel.setFont(new Font("Arial", Font.BOLD, 30));
        Dimension sizeOrder = jlOrderLabel.getPreferredSize();
        jlOrderLabel.setBounds(0, getScreenHeight(0.8f), sizeOrder.width + 10, sizeOrder.height);
        headerPanel.add(jlOrderLabel);

        //label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlSortLabel.setFont(arial17);
        Dimension sizeSortLabel = jlSortLabel.getPreferredSize();
        jlSortLabel.setBounds(getScreenWidth(54f), getScreenHeight(1.4f), sizeSortLabel.width +10, sizeSortLabel.height);
        headerPanel.add(jlSortLabel);

        //Combobox aanmaken en waarde toekennen, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jcbSort = new JComboBox(new String[]{"Ordernummer aflopend", "Ordernummer oplopend", "Datum oplopend", "Datum aflopend", "Voltooid", "Onvoltooid"});
        jcbSort.addItemListener(this);
        jcbSort.setBackground(Color.white);
        jcbSort.setBounds(getScreenWidth(54.4f) + sizeSortLabel.width +10, getScreenHeight(1.1f), getScreenWidth(11.39322917f), getScreenHeight(3f));
        headerPanel.add(jcbSort);

        //Tekstveld toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jtfCustomerNumber.setBounds(getScreenWidth(71f), getScreenHeight(1.1f), getScreenWidth(7.8125f), getScreenHeight(3f));
        jtfCustomerNumber.setToolTipText("Klantnummer");
        headerPanel.add(jtfCustomerNumber);

        //Tekstveld toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jtfOrderNumber.setBounds(getScreenWidth(79.5f), getScreenHeight(1.1f), getScreenWidth(7.8125f), getScreenHeight(3f));
        jtfOrderNumber.setToolTipText("Ordernummer");
        headerPanel.add(jtfOrderNumber);

        // Buttun toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jbSearch.setFont(arial17);
        jbSearch.addActionListener(this); // actionlistener toevoegen aan button (Joëlle)
        jbSearch.setBounds(getScreenWidth(88f), getScreenHeight(1.1f), getScreenWidth(10f), getScreenHeight(3f));
        headerPanel.add(jbSearch);

        super.add(headerPanel); // PanelButtons toevoegen aan het hoofdscherm (Joëlle)
    }

    private void ordersPanelTabelHeader(){
        //Panel aanmaken voor de titels, juiste grootte en kleur meegeven (Joëlle)
        JPanel panelTitles = new JPanel();
        panelTitles.setLayout(null);
        panelTitles.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(4f))); // procenten toegevoegd( Joëlle)
        panelTitles.setBackground(Color.lightGray);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlOrderText.setFont(arial22);
        Dimension sizeOrderText = jlOrderText.getPreferredSize();
        jlOrderText.setBounds(getScreenWidth(3.255208333f), getScreenHeight(1.157407407f), sizeOrderText.width +10, sizeOrderText.height);
        panelTitles.add(jlOrderText);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlCustomerText.setFont(arial22);
        Dimension sizeCustomerText = jlCustomerText.getPreferredSize();
        jlCustomerText.setBounds(getScreenWidth(20f), getScreenHeight(1.157407407f), sizeCustomerText.width +10, sizeCustomerText.height);
        panelTitles.add(jlCustomerText);

        jlCustomerTextNumber.setFont(arial15);
        Dimension sizeCustomerTextNumber = jlCustomerTextNumber.getPreferredSize();
        jlCustomerTextNumber.setBounds(getScreenWidth(20.65f) +sizeCustomerText.width, getScreenHeight(1.736111111f), sizeCustomerTextNumber.width +10, sizeCustomerTextNumber.height);
        panelTitles.add(jlCustomerTextNumber);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlProductsText.setFont(arial22);
        Dimension sizeProductsText = jlProductsText.getPreferredSize();
        jlProductsText.setBounds(getScreenWidth(48.83f), getScreenHeight(1.157407407f), sizeProductsText.width +10, sizeProductsText.height);
        panelTitles.add(jlProductsText);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlProductsQuantityText.setFont(arial15);
        Dimension sizeProductsQuantityText = jlProductsQuantityText.getPreferredSize();
        jlProductsQuantityText.setBounds(getScreenWidth(49.48f) +sizeProductsText.width, getScreenHeight(1.736111111f), sizeProductsQuantityText.width +10, sizeProductsQuantityText.height);
        panelTitles.add(jlProductsQuantityText);

        //Label voor Datum aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlDateText.setFont(arial22);
        Dimension sizeDateText = jlDateText.getPreferredSize();
        jlDateText.setBounds(getScreenWidth(67f), getScreenHeight(1.16f), sizeDateText.width +10, sizeDateText.height);
        panelTitles.add(jlDateText);

        //label voor Status aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlStatusText.setFont(arial22);
        Dimension sizeStatusText = jlStatusText.getPreferredSize();
        jlStatusText.setBounds(getScreenWidth(84f), getScreenHeight(1.16f), sizeStatusText.width +10, sizeStatusText.height);
        panelTitles.add(jlStatusText);

        super.add(panelTitles); //Panel van de titels toevoegen aan het hoofdscherm (Joëlle)
    }

    // Sorteren / zoeken door Martijn
    // Maakt de variabelen hier omdat ze gelijk hieronder en in meerdere methodes worden gebruikt.
    private JScrollPane scrollPane;
    private JPanel panelTabel = new JPanel();
    private JPanel panelOrderRow;

    // Maakt de orders tabel
    private void ordersPanelTabel() {
        // Maakt de JPanel aan met juiste params
        JPanel panelTabel = new JPanel();
        panelTabel.setLayout(new FlowLayout());
        panelTabel.setPreferredSize(new Dimension(getScreenWidth(98f), FrameHeader.getScreenHeight(7.45f) * orders.size())); // procenten toegevoegd( Joëlle)

        // For loop waar eerst een button toegevoegd wordt aan de arraylist, dan wordt in deze button een panel toegevoegd (Joëlle)
        // en wordt de juiste grootte meegegeven (Joëlle)
        for (int i = 0; i < orders.size(); i++) {
            buttons.add(new JButton());

            panelOrderRow = new PanelOrder(orders.get(i));
            buttons.get(i).add(panelOrderRow);
            buttons.get(i).setBackground(Color.white);
            Dimension sizeOrderPanel = panelOrderRow.getPreferredSize();
            panelOrderRow.setBounds(getScreenHeight(0f), sizeOrderPanel.height * i, sizeOrderPanel.width, sizeOrderPanel.height);
            panelTabel.add(buttons.get(i));

            Dimension sizeButtonOrder = buttons.get(i).getPreferredSize();
            buttons.get(i).setBounds(getScreenHeight(0f), sizeButtonOrder.height * i, sizeButtonOrder.width, sizeButtonOrder.height);

            buttons.get(i).addActionListener(this);
        }

        //Aanmaken scrollPane, juiste grootte meegeven en de vertical scrollbar en toevoegen aan het scherm (Joëlle)
        scrollPane = new JScrollPane(panelTabel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(70f)));
        scrollPane.setViewportView(panelTabel);
        super.add(scrollPane);

        // Update de frame met nieuwe data
        revalidate();
        repaint();
    }

    // Verwijderd de elementen uit de JFrame en tekent ze opnieuw
    // Door Martijn
    public void redraw(){
        // Verwijder de huidge panelTabel en scrollPane
        super.remove(panelTabel);
        super.remove(scrollPane);
        // Maakt de buttons array leeg zodat deze opnieuw kan worden gevuld
        buttons.clear();
        // Tekent het paneel opnieuw
        ordersPanelTabel();
    }

    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);

        //aangepast door Jason Joshua van der Kolk
        //als op een knop wordt gedrukt
        for (int i = 0; i < orders.size(); i++) {
            if(e.getSource() == buttons.get(i)){
                FrameController.setActiveViewingOrder(this, orders.get(i));
            }
        }



        // Als de zoekknop is ingedrukt
        // Door Martijn
        if(e.getSource() == jbSearch){
            // Haalt de waardes van de tekstvelden op
            String customerNumber = jtfCustomerNumber.getText();
            String orderNumber = jtfOrderNumber.getText();

            // Op basis van de waardes haal andere data uit de db
            if ((customerNumber.isEmpty() && orderNumber.isEmpty()) || (customerNumber.isEmpty() && orderNumber.equals("Ordernummer")) || (customerNumber.equals("Klantnummer") && orderNumber.isEmpty())) {
                getOrderData(null, null);
            } else if (customerNumber.isEmpty()) {
                getOrderData("orders", orderNumber);
            } else if (orderNumber.isEmpty()) {
                getOrderData("customers", customerNumber);
            } else if (!customerNumber.equals("Klantnummer")) {
                getOrderData("customers", customerNumber);
            } else if (!orderNumber.equals("Ordernummer")) {
                getOrderData("orders", orderNumber);
            }

            // Ververst het venster met de nieuwe data
            redraw();
        }
    }

    // Als de waarde van de combobox is veranderd
    // Door Martijn
    public void itemStateChanged(ItemEvent e1) {
        // Haalt de waarde van de combobox op en zet hem om naar een String
        String selectedSortOption = String.valueOf(jcbSort.getSelectedItem());
        // Maakt een lege comperator aan
        Comparator<Order> comparator;

        // Loopt door de verschillende opties heen
        // Als een conditie true is, maakt hij de comparator aan en slaat hem op in de var
        switch (selectedSortOption) {
            case "Ordernummer aflopend":
                comparator = new sortByOrderIDDesc();
                break;
            case "Ordernummer oplopend":
                comparator = new sortByOrderIDAsc();
                break;
            case "Datum aflopend":
                comparator = new sortByOrderDateDesc();
                break;
            case "Datum oplopend":
                comparator = new sortByOrderDateAsc();
                break;
            case "Voltooid":
                comparator = new sortByOrderCompletedTrue();
                break;
            case "Onvoltooid":
                comparator = new sortByOrderCompletedFalse();
                break;
            default:
                return;
        }

        // Sorteert de orders op basis van de comparator
        orders.sort(comparator);

        // Ververst het venster met de nieuwe data
        redraw();
    }
}