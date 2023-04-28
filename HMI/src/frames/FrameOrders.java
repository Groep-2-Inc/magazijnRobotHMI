package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import testClasses.*;
import panels.*;


public class FrameOrders extends FrameHeader implements ActionListener {
    private JLabel jl_orderLabel = new JLabel("Orders"); // label voor titel Orders(Joëlle)

    private JLabel jl_sortLabel = new JLabel("Sortering:"); // label voor titel sortering (Joëlle)

    private JLabel jl_orderText = new JLabel("Order"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)
    private JLabel jl_customerText = new JLabel("Naam"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)

    private JLabel jl_productsText = new JLabel("Producten"); // label voor in het titel panel van de scrollpanel: tekst producten (Joëlle)

    private JLabel jl_productsQuantityText = new JLabel("(aantal)"); // label voor in het titel panel van de scrollpanel: tekst aantal (Joëlle)
    private JLabel jlDateText = new JLabel("Datum");  // label voor in het titel panel van de scrollpanel: tekst datum (Joëlle)
    private ArrayList<Order> orders;// arraylist waarin alle orders staan (Joëlle)
    private JButton jb_search = new JButton("Zoeken"); // butten voor het zoeken (Joëlle)
    private JButton jb_ordersAanmaken = new JButton("Order aanmaken"); //button voor orders aanmaken (Jason Joshua)
    private JTextField jtf_customerNumber = new JTextField("Klantnummer", 10); // tekstveld voor het klantnummer (Joëlle)
    private JTextField jtf_orderNumber = new JTextField("Ordernummer", 10); // tekstveld voor het ordernummer (Joëlle)
    private JComboBox jcb_sort; //combobox voor het sorteren (Joëlle)

    private ArrayList<JButton> buttons = new ArrayList<>(); // alle buttons uit het panel komen in deze lijst (Joëlle)

    public FrameOrders(ArrayList<Order> orders){
        //standaard instellingen (Joëlle)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Java-application/Orders"); // moet nog een betere naam hebben (Joëlle)
        // standaard fonts instellen
        Font arial20 = new Font("Arial", Font.PLAIN, 20);
        Font arial17 = new Font("Arial", Font.PLAIN, 17);
        Font arial15 = new Font("Arial", Font.PLAIN, 15);
        FrameHeader.closeProgram();

        this.orders = orders; // Het attribuut de meegegeven waarde geven (Joëlle)

        //panel voor de buttons aanmaken en de juiste grootte meegeven (Joëlle)
        JPanel panelButtons1 = new JPanel();
        panelButtons1.setLayout(null);
        panelButtons1.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(5.5f))); // procenten toegevoegd (Joëlle)
//        panelButtons1.setBackground(Color.blue); // voor het debuggen

        //label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_orderLabel.setFont(new Font(jl_orderLabel.getFont().getName(), jl_orderLabel.getFont().getStyle(), 20));
        panelButtons1.add(jl_orderLabel);
        Dimension sizeOrderLabel = jl_orderLabel.getPreferredSize();
        jl_orderLabel.setBounds(10, 10, sizeOrderLabel.width, sizeOrderLabel.height);

        //label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        panelButtons1.add(jl_sortLabel);
        Dimension sizeSortLabel = jl_sortLabel.getPreferredSize();
        jl_sortLabel.setBounds(800, 15, sizeSortLabel.width +10, sizeSortLabel.height);

        //Combobox aanmaken en waarde toekennen, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        String sortpossibilities[]={"Ordernummer oplopend", "Ordernummer aflopend", "Datum oplopend", "Datum aflopend", "Voltooid", "Onvoltooid"};
        jcb_sort = new JComboBox(sortpossibilities);
        panelButtons1.add(jcb_sort);
        jcb_sort.setBounds(800 + sizeSortLabel.width +10, 10, 175, 30);

        //Tekstveld toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        panelButtons1.add(jtf_customerNumber);
        jtf_customerNumber.setBounds(1070, 10, 120, 30);

        //Tekstveld toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        panelButtons1.add(jtf_orderNumber);
        jtf_orderNumber.setBounds(1200, 10, 120, 30);

        // Buttun toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jb_search.setFont(arial17);
        panelButtons1.add(jb_search);
        jb_search.addActionListener(this); // actionlistener toevoegen aan button (Joëlle)
        jb_search.setBounds(1350, 5, 120, 40);

        //button toevoegen voor order aanmaken, door Jason Joshua van der Kolk
        jb_ordersAanmaken.setFont(arial17);
        panelButtons1.add(jb_ordersAanmaken);
        jb_ordersAanmaken.addActionListener(this);
        jb_ordersAanmaken.setBounds(1550, 5, 220, 40);

        super.add(panelButtons1); // PanelButtons toevoegen aan het hoofdscherm (Joëlle)

        //Panel aanmaken voor de titels, juiste grootte en kleur meegeven (Joëlle)
        JPanel panelTitles = new JPanel();
        panelTitles.setLayout(null);
        panelTitles.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(5f))); // procenten toegevoegd( Joëlle)
        panelTitles.setBackground(Color.lightGray);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_orderText.setFont(arial20);
        panelTitles.add(jl_orderText);
        Dimension sizeOrderText = jl_orderText.getPreferredSize();
        jl_orderText.setBounds(50, 10, sizeOrderText.width, sizeOrderText.height);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_customerText.setFont(arial20);
        panelTitles.add(jl_customerText);
        Dimension sizeCustomerText = jl_customerText.getPreferredSize();
        jl_customerText.setBounds(400, 10, sizeCustomerText.width +10, sizeCustomerText.height);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_productsText.setFont(arial20);
        panelTitles.add(jl_productsText);
        Dimension sizeProductenText = jl_productsText.getPreferredSize();
        jl_productsText.setBounds(750, 10, sizeProductenText.width +10, sizeProductenText.height);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_productsQuantityText.setFont(arial15);
        panelTitles.add(jl_productsQuantityText);
        Dimension sizeProductsQuantityText = jl_productsQuantityText.getPreferredSize();
        jl_productsQuantityText.setBounds(750 +sizeProductenText.width +10, 15, sizeProductsQuantityText.width +10, sizeProductsQuantityText.height);

        //Label voor Datum aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlDateText.setFont(arial20);
        panelTitles.add(jlDateText);
        Dimension sizeDateText = jlDateText.getPreferredSize();
        jlDateText.setBounds(1100, 10, sizeDateText.width +10, sizeDateText.height);

        super.add(panelTitles); //Panel van de titels toevoegen aan het hoofdscherm (Joëlle)

        //Panel aanmaken, waar het scrollpanel inkomt (Joëlle)
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setPreferredSize(new Dimension(getScreenWidth(98f), 116 * orders.size())); // procenten toegevoegd( Joëlle)

        // For loop waar eerst een button toegevoegd wordt aan de arraylist, dan wordt in deze button een panel toegevoegd (Joëlle)
        // en wordt de juiste grootte meegegeven (Joëlle)
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

        //For loop die controleerd die elke knop in de arrayList langsgaat en checkt of die gedrukt is, bij het indrukken, wordt er een regel geprint, dat is alleen voor het debuggen (Joëlle)
        for (int i = 0; i < orders.size(); i++) {
            final int buttonNumber = i + 1;
            buttons.get(i).addActionListener(this); //aangepast door Jason Joshua van der Kolk
        }

        //Aanmaken scrollPane, juiste grootte meegeven en de vertical scrollbar en toevoegen aan het scherm (Joëlle)
        JScrollPane scrollPane = new JScrollPane(panel2);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        panelTitles.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(5f))); // procenten toegevoegd( Joëlle)
        scrollPane.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(67.5f))); // procenten toegevoegd( Joëlle)
        super.add(scrollPane);
    }

    //aangepast door Jason Joshua van der Kolk
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);

        for (int i = 0; i < orders.size(); i++) {
            if(e.getSource() ==buttons.get(i)){
                FrameController.setActiveViewingOrder(this, orders.get(i));
                System.out.println("Gedrukt op knop nummer " + i );
            }
        }

        if(e.getSource() == jb_ordersAanmaken){
            FrameController.setActiveFrameMakeOrder(this);
        }
    }
}