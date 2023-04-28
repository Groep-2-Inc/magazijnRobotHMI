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
    private Font arial22 = new Font("Arial", Font.PLAIN, 22);
    private Font arial20 = new Font("Arial", Font.PLAIN, 20);
    private Font arial17 = new Font("Arial", Font.PLAIN, 17);
    private Font arial15 = new Font("Arial", Font.PLAIN, 15);
    private ArrayList<JButton> buttons = new ArrayList<>(); // alle buttons uit het panel komen in deze lijst (Joëlle)

    public FrameOrders(ArrayList<Order> orders){
        //standaard instellingen (Joëlle)
        setTitle("Java-application/Orders"); // moet nog een betere naam hebben (Joëlle)

        this.orders = orders; // Het attribuut de meegegeven waarde geven (Joëlle)

        // Verplaats naar eigen methode om code overzichtelijker te maken
        // Door Martijn
        ordersPanelHeader();
        ordersPanelTabelHeader();
        ordersPanelTabel();
    }

    // Verplaats naar eigen methode om code overzichtelijker te maken
    // Door Martijn
    private void ordersPanelHeader(){
        //panel voor de buttons aanmaken en de juiste grootte meegeven (Joëlle)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(5.5f))); // procenten toegevoegd (Joëlle)
//        headerPanel.setBackground(Color.yellow); // voor het debuggen

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
        headerPanel.add(jtf_orderNumber);
        jtf_orderNumber.setBounds(getScreenWidth(69f), getScreenHeight(1.1f), getScreenWidth(7.8125f), getScreenHeight(3f));

        // Buttun toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jb_search.setFont(arial17);
        headerPanel.add(jb_search);
        jb_search.addActionListener(this); // actionlistener toevoegen aan button (Joëlle)
        jb_search.setBounds(getScreenWidth(77.5f), getScreenHeight(1.1f), getScreenWidth(10f), getScreenHeight(3f));

        //button toevoegen voor order aanmaken, door Jason Joshua van der Kolk
        jb_ordersAanmaken.setFont(arial17);
        headerPanel.add(jb_ordersAanmaken);
        jb_ordersAanmaken.addActionListener(this);
        jb_ordersAanmaken.setBounds(getScreenWidth(88f), getScreenHeight(1.1f), getScreenWidth(10f), getScreenHeight(3f));

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
        panelTitles.add(jl_orderText);
        Dimension sizeOrderText = jl_orderText.getPreferredSize();
        jl_orderText.setBounds(getScreenWidth(3.255208333f), getScreenHeight(1.157407407f), sizeOrderText.width +10, sizeOrderText.height);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_customerText.setFont(arial22);
        panelTitles.add(jl_customerText);
        Dimension sizeCustomerText = jl_customerText.getPreferredSize();
        jl_customerText.setBounds(getScreenWidth(26.04166667f), getScreenHeight(1.157407407f), sizeCustomerText.width +10, sizeCustomerText.height);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_productsText.setFont(arial22);
        panelTitles.add(jl_productsText);
        Dimension sizeProductenText = jl_productsText.getPreferredSize();
        jl_productsText.setBounds(getScreenWidth(48.828125f), getScreenHeight(1.157407407f), sizeProductenText.width +10, sizeProductenText.height);

        //Label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_productsQuantityText.setFont(arial15);
        panelTitles.add(jl_productsQuantityText);
        Dimension sizeProductsQuantityText = jl_productsQuantityText.getPreferredSize();
        jl_productsQuantityText.setBounds(getScreenWidth(49.47916667f) +sizeProductenText.width, getScreenHeight(1.736111111f), sizeProductsQuantityText.width +10, sizeProductsQuantityText.height);

        //Label voor Datum aanmaken, toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jlDateText.setFont(arial22);
        panelTitles.add(jlDateText);
        Dimension sizeDateText = jlDateText.getPreferredSize();
        jlDateText.setBounds(getScreenWidth(71.61458333f), getScreenHeight(1.157407407f), sizeDateText.width +10, sizeDateText.height);

        super.add(panelTitles); //Panel van de titels toevoegen aan het hoofdscherm (Joëlle)
    }

    private void ordersPanelTabel(){
        //Panel aanmaken, waar het scrollpanel inkomt (Joëlle)
        JPanel panelTabel = new JPanel();
        panelTabel.setLayout(new FlowLayout());
        panelTabel.setPreferredSize(new Dimension(getScreenWidth(98f), 116 * orders.size())); // procenten toegevoegd( Joëlle)

        // For loop waar eerst een button toegevoegd wordt aan de arraylist, dan wordt in deze button een panel toegevoegd (Joëlle)
        // en wordt de juiste grootte meegegeven (Joëlle)
        for (int i = 0; i < orders.size(); i++) {
            buttons.add(new JButton());

            JPanel panelOrderRow = new PanelOrder(orders.get(i));
            buttons.get(i).add(panelOrderRow);
            panelTabel.add(buttons.get(i));
            buttons.get(i).setBackground(Color.white);
            Dimension sizeOrderPanel = panelOrderRow.getPreferredSize();
            panelOrderRow.setBounds(getScreenHeight(0f), sizeOrderPanel.height * i, sizeOrderPanel.width, sizeOrderPanel.height);

            Dimension sizeButtonOrder = buttons.get(i).getPreferredSize();
            buttons.get(i).setBounds(getScreenHeight(0f), sizeButtonOrder.height * i, sizeButtonOrder.width, sizeButtonOrder.height);
        }

        //For loop die controleerd die elke knop in de arrayList langsgaat en checkt of die gedrukt is, bij het indrukken, wordt er een regel geprint, dat is alleen voor het debuggen (Joëlle)
        for (int i = 0; i < orders.size(); i++) {
            final int buttonNumber = i + 1;
            buttons.get(i).addActionListener(this); //aangepast door Jason Joshua van der Kolk
        }

        //Aanmaken scrollPane, juiste grootte meegeven en de vertical scrollbar en toevoegen aan het scherm (Joëlle)
        JScrollPane scrollPane = new JScrollPane(panelTabel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(70f))); // procenten toegevoegd( Joëlle)
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