import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.text.html.HTML.Attribute.N;

public class FrameOrders extends FrameHeader implements ActionListener {
    private ArrayList<Order> orders;// arraylist waarin alle orders staan
    private JButton jb_search; // butten voor het zoeken
    private JButton jb_view; // button voor het orders inzien
    private JTextField jtf_customerNumber; // tekstveld voor het klantnummer

    private JTextField jtf_orderNumber; // tekstveld voor het ordernummer
    private JComboBox jcb_sort; //combobox voor het sorteren

    private ArrayList<JButton> buttons = new ArrayList<>(); // alle buttons uit het panel komen in deze lijst

    public FrameOrders(ArrayList<Order> orders){
        //standaard instellingen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Java-application/Orders"); // moet nog een betere naam hebben

        this.orders = orders; // Het attribuut de meegegeven waarde geven

        //panel  voor de buttons aanmaken en de juiste grootte meegeven
        JPanel panelButtons1 = new JPanel();
        panelButtons1.setLayout(null);
        panelButtons1.setPreferredSize(new Dimension(1500, 50));
//        panelButtons1.setBackground(Color.green); // voor het debuggen

        //label voor de tekst orders aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        JLabel jl_orderLabel = new JLabel("Orders");
        jl_orderLabel.setFont(new Font(jl_orderLabel.getFont().getName(), jl_orderLabel.getFont().getStyle(), 20));
        panelButtons1.add(jl_orderLabel);
        Dimension sizeOrderLabel = jl_orderLabel.getPreferredSize();
        jl_orderLabel.setBounds(10, 10, sizeOrderLabel.width, sizeOrderLabel.height);

        //label voor de tekst sortering aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        JLabel jl_sortLabel = new JLabel("Sortering:");
        panelButtons1.add(jl_sortLabel);
        Dimension sizeSortLabel = jl_sortLabel.getPreferredSize();
        jl_sortLabel.setBounds(800, 20, sizeSortLabel.width +10, sizeSortLabel.height);

        //Combobox aanmaken en waarde toekennen, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        String sortpossibilities[]={"Ordernummer oplopend", "Ordernummer aflopend", "Datum oplopend", "Datum aflopend", "Voltooid", "Onvoltooid"};
        jcb_sort = new JComboBox(sortpossibilities);
        panelButtons1.add(jcb_sort);
        Dimension sizeSortBox = jcb_sort.getPreferredSize();
        jcb_sort.setBounds(800 + sizeSortLabel.width +10, 15, 175, 30);

        //Tekstveld voor klantnummer aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        jtf_customerNumber = new JTextField("Klantnummer", 10);
        panelButtons1.add(jtf_customerNumber);
        Dimension sizeCustomerNumber = jtf_customerNumber.getPreferredSize();
        jtf_customerNumber.setBounds(1070, 15, 120, 30);

        //Tekstveld voor ordernummer aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        jtf_orderNumber = new JTextField("Ordernummer", 10);
        panelButtons1.add(jtf_orderNumber);
        Dimension sizeOrderNumber = jtf_orderNumber.getPreferredSize();
        jtf_orderNumber.setBounds(1200, 15, 120, 30);

        // Buttun voor zoeken aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        jb_search = new JButton("Zoeken");
        jb_search.setFont(new Font("Arial", Font.PLAIN, 17));
        panelButtons1.add(jb_search);
        jb_search.addActionListener(this); // actionlistener toevoegen aan button
        Dimension sizeSearchButton = jb_search.getPreferredSize();
        jb_search.setBounds(1350, 10, 120, 40);

        super.add(panelButtons1); // panelButtons toevoegen aan het hoofdscherm

        //Panel aanmaken voor de titels, juiste grootte en kleur meegeven
        JPanel panelTitles = new JPanel();
        panelTitles.setLayout(null);
        panelTitles.setPreferredSize(new Dimension(1500, 40));
        panelTitles.setBackground(Color.lightGray);

        //label voor order aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        JLabel jl_orderText = new JLabel("Order");
        jl_orderText.setFont(new Font("Arial", Font.PLAIN, 20));
        panelTitles.add(jl_orderText);
        Dimension sizeOrderText = jl_orderText.getPreferredSize();
        jl_orderText.setBounds(50, 10, sizeOrderText.width, sizeOrderText.height);

        //label voor naam aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        JLabel jl_customerText = new JLabel("Naam");
        jl_customerText.setFont(new Font("Arial", Font.PLAIN, 20));
        panelTitles.add(jl_customerText);
        Dimension sizeCustomerText = jl_customerText.getPreferredSize();
        jl_customerText.setBounds(400, 10, sizeCustomerText.width +10, sizeCustomerText.height);

        //label voor producten aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        JLabel jl_productsText = new JLabel("Producten");
        jl_productsText.setFont(new Font("Arial", Font.PLAIN, 20));
        panelTitles.add(jl_productsText);
        Dimension sizeProductenText = jl_productsText.getPreferredSize();
        jl_productsText.setBounds(750, 10, sizeProductenText.width +10, sizeProductenText.height);

        //label voor producten aantal aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        JLabel jl_productsQuantityText = new JLabel("(aantal)");
        jl_productsQuantityText.setFont(new Font("Arial", Font.PLAIN, 15));
        panelTitles.add(jl_productsQuantityText);
        Dimension sizeProductsQuantityText = jl_productsQuantityText.getPreferredSize();
        jl_productsQuantityText.setBounds(750 +sizeProductenText.width +10, 15, sizeProductsQuantityText.width +10, sizeProductsQuantityText.height);

        //label voor Datum aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        JLabel jlDateText = new JLabel("Datum");
        jlDateText.setFont(new Font("Arial", Font.PLAIN, 20));
        panelTitles.add(jlDateText);
        Dimension sizeDateText = jlDateText.getPreferredSize();
        jlDateText.setBounds(1100, 10, sizeDateText.width +10, sizeDateText.height);

        super.add(panelTitles); // panel van de titels toevoegen aan het hoofdscherm

        //panel aanmaken, waar het scrollpanel inkomt
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setPreferredSize(new Dimension(1500, 100* orders.size() + 50));

        // for loop waar eerst een buttun toegevoegd wordt aan de arraylist, dan wordt in deze button een panel toegevoegt
        // en wordt de juiste grootte meegegeven
        for (int i = 0; i < orders.size(); i++) {
            buttons.add(new JButton());
//            JButton jb_order = new JButton();
            JPanel panelOrders = new OrderPanel(orders.get(i));
            buttons.get(i).add(panelOrders);
            panel2.add(buttons.get(i));
            buttons.get(i).setBackground(Color.white);
            Dimension sizeOrderPanel = panelOrders.getPreferredSize();
            panelOrders.setBounds(0, sizeOrderPanel.height * i, sizeOrderPanel.width, sizeOrderPanel.height);

            Dimension sizeButtonOrder = buttons.get(i).getPreferredSize();
            buttons.get(i).setBounds(0, sizeButtonOrder.height * i, sizeButtonOrder.width, sizeButtonOrder.height);
        }

        //for loop die controleerd die elke knop in de arrayList langsgaat en checkt of die gedrukt is, bij het indrukken, wordt er een regel geprint, dat is alleen voor het debuggen
        for (int i = 0; i < orders.size(); i++) {
            final int buttonNumber = i + 1;
            buttons.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Gedrukt op knop nummer " + buttonNumber ); // voor het debuggen kijken of de knop werkt
                    //straks moet er iets komen van Panel = new Panel(orders.get(buttonNumber-1)) -> moet nog beter uitgewerkt worden
                }
            });
        }
//        //for loop waarbij voor elke order nieuw panel wordt aangemaakt, de teller wordt opgehoogt en de grootte en de plaats van elk panel wordt bedacht
//        int teller1 = 0;
//        for (Order order : orders) {
//            JButton jb_order = new JButton();
//            JPanel panelOrders = new OrderPanel(order);
//            jb_order.add(panelOrders);
//            panel2.add(jb_order);
//            Dimension sizeOrderPanel = panelOrders.getPreferredSize();
//            panelOrders.setBounds(0, sizeOrderPanel.height * teller, sizeOrderPanel.width, sizeOrderPanel.height);
//
//            Dimension sizeButtonOrder = jb_order.getPreferredSize();
//            jb_order.setBounds(0, sizeButtonOrder.height * teller, sizeButtonOrder.width, sizeButtonOrder.height);
//            teller ++;
//        }

        //aanmaken scrollpane, juiste grootte megeven en de vertical scrollbar en toevoegen aan het scherm
        JScrollPane scrollPane = new JScrollPane(panel2);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(1500, 535));
        super.add(scrollPane);

        //aanmaken derde panel voor inzien button, layout op null zetten
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setPreferredSize(new Dimension(1500, 50));
//        panel3.setBackground(Color.magenta); // voor het debuggen

        //Button voor inzien aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven
        jb_view = new JButton("Inzien");
        panel3.add(jb_view);
        jb_view.setFont(new Font("Arial", Font.PLAIN, 17));
        Dimension sizeViewButton = jb_view.getPreferredSize();
        jb_view.setBounds(1350, 10, 120, 40);
        jb_view.addActionListener(this); // actionlistener toevoegen

        super.add(panel3); // panel3 toevoegen aan het hoofdscherm



    }

    public void actionPerformed(ActionEvent e) {
    }


}
