import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameOrders extends FrameHeader implements ActionListener {
    private ArrayList<Order> orders;// arraylist waarin alle orders staan
    private JButton jb_search; // butten voor het zoeken
    private JButton jb_view; // button voor het orders inzien
    private JTextField jtf_customerNumber; // tekstveld voor het klantnummer

    private JTextField jtf_orderNumber; // tekstveld voor het ordernummer
    private JComboBox jcb_sort; //combobox voor het sorteren

    public FrameOrders(ArrayList<Order> orders){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // programma sluit als op kruisje geklikt wordt
        setTitle("Java-application/Orders"); // moet nog een betere titel komen lijkt mij

        this.orders = orders; // Het attribuut de meegegeven waarde geven

        JPanel panelButtons1 = new JPanel(); // panel voor de buttons
        panelButtons1.setLayout(null); // panel zetten op geen layout, zodat de plaatsen van de buttons handmatig ingesteld kan worden
        panelButtons1.setPreferredSize(new Dimension(1500, 50));
//        panelButtons1.setBackground(Color.green); // voor het debuggen

        JLabel jl_orderLabel = new JLabel("Orders"); // label voor de orders
        jl_orderLabel.setFont(new Font(jl_orderLabel.getFont().getName(), jl_orderLabel.getFont().getStyle(), 20)); // lettertype van het label vergoten naar font 20, met dikgedrukte stijl
        panelButtons1.add(jl_orderLabel); // label toevoegen aan buttonspanel
        Dimension sizeOrderLabel = jl_orderLabel.getPreferredSize(); // maakt een dimensie aan met de grootte van het label
        jl_orderLabel.setBounds(10, 10, sizeOrderLabel.width, sizeOrderLabel.height); // zet de plaats van het label en de grootte van het label wordt gebaseerd op de tekst in het label

        JLabel jl_sortLabel = new JLabel("Sortering:"); // label voor de sortering
//        jl_sortLabel.setFont(new Font("Arial", Font.PLAIN, 10)); // grootte letters kan handmatig ingesteld worden
        panelButtons1.add(jl_sortLabel); // label toevoegen aan buttonspanel
        Dimension sizeSortLabel = jl_sortLabel.getPreferredSize(); // maakt een dimensie aan met de grootte van het label
        jl_sortLabel.setBounds(800, 20, sizeSortLabel.width +10, sizeSortLabel.height); // zet de plaats van het label en de grootte van het label wordt gebaseerd op de tekst in het label

        String sortpossibilities[]={"Ordernummer oplopend", "Ordernummer aflopend", "Datum oplopend", "Datum aflopend", "Voltooid", "Onvoltooid"}; // string aanmaken met de tekst voor in de combobox
        jcb_sort = new JComboBox(sortpossibilities); // combobox aanmaken met de net aangemaakte string
        panelButtons1.add(jcb_sort); // label toevoegen aan buttonspanel
        Dimension sizeSortBox = jcb_sort.getPreferredSize(); // maakt een dimensie aan met de grootte van de sortbox
        jcb_sort.setBounds(800 + sizeSortLabel.width +10 , 15, sizeSortBox.width, sizeSortBox.height); // zet de plaats van de sortbox en de grootte van de sortbox wordt gebaseerd op de tekst van de sortbox

        jtf_customerNumber = new JTextField("Klantnummer", 10); // tekstveld voor klantnummer
        panelButtons1.add(jtf_customerNumber); // tekstveld toevoegen aan buttonspanel
        Dimension sizeCustomerNumber = jtf_customerNumber.getPreferredSize(); // maakt een dimensie aan met de grootte van het tekstveld
        jtf_customerNumber.setBounds(1125 , 20, sizeCustomerNumber.width, sizeCustomerNumber.height); // zet de plaats van het tekstveld en de grootte van het tekstveld wordt gebaseerd op de tekst in het tekstveld

        jtf_orderNumber = new JTextField("Ordernummer", 10); // tekstveld voor ordernummer
        panelButtons1.add(jtf_orderNumber); // tekstveld toevoegen aan buttonspanel
        Dimension sizeOrderNumber = jtf_orderNumber.getPreferredSize(); // maakt een dimensie aan met de grootte van het tekstveld
        jtf_orderNumber.setBounds(1250 , 20, sizeOrderNumber.width, sizeOrderNumber.height);  // zet de plaats van het tekstveld en de grootte van het tekstveld wordt gebaseerd op de tekst in het tekstveld

        jb_search = new JButton("Zoeken"); // button voor zoeken
        panelButtons1.add(jb_search); // button toevoegen aan buttonspanel
        jb_search.addActionListener(this); // actionlistener toevoegen aan button
        Dimension sizeSearchButton = jb_search.getPreferredSize(); // maakt een dimensie aan met de grootte van de button
        jb_search.setBounds(1400 , 15, sizeSearchButton.width, sizeSearchButton.height); // zet de plaats van de button en de grootte van het button wordt gebaseerd op de tekst in de button

        super.add(panelButtons1); // panelButtons toevoegen aan het hoofdscherm


        JPanel panelTitles = new JPanel(); // panel aanmaken voor de titels van de scrollbar
        panelTitles.setLayout(null); // panel zetten op geen layout, zodat labels handmatig op juiste plek gezet kunnen worden
        panelTitles.setPreferredSize(new Dimension(1500, 40)); // grootte van het panel instellen
        panelTitles.setBackground(Color.lightGray); // voor het debuggen kleur toevoegen


        JLabel jl_orderText = new JLabel("src.Order"); // label voor de order
        jl_orderText.setFont(new Font("Arial", Font.PLAIN, 20)); // lettertype instellen op font 20
        panelTitles.add(jl_orderText); // label toevoegen aan het panel voor de titels
        Dimension sizeOrderText = jl_orderText.getPreferredSize(); // maakt een dimensie aan met de grootte van het label
        jl_orderText.setBounds(50, 10, sizeOrderText.width, sizeOrderText.height);  // zet de plaats van het label en de grootte van het label wordt gebaseerd op de tekst in het label

        JLabel jl_customerText = new JLabel("Naam"); // label voor de naam
        jl_customerText.setFont(new Font("Arial", Font.PLAIN, 20)); // lettertype instellen op font 20
        panelTitles.add(jl_customerText); // label toevoegen aan panel van de titels
        Dimension sizeCustomerText = jl_customerText.getPreferredSize(); // maakt een dimensie aan met de grootte van het label
        jl_customerText.setBounds(400, 10, sizeCustomerText.width +10, sizeCustomerText.height); // zet de plaats van het label en de grootte van het label wordt gebaseerd op de tekst in het label

        JLabel jl_productenText = new JLabel("Producten"); // label voor de producten
        jl_productenText.setFont(new Font("Arial", Font.PLAIN, 20)); // lettertype instellen op font 20
        panelTitles.add(jl_productenText); // label toevoegen aan panel van de titels
        Dimension sizeProductenText = jl_productenText.getPreferredSize(); // maakt een dimensie aan met de grootte van het label
        jl_productenText.setBounds(750, 10, sizeProductenText.width +10, sizeProductenText.height); // zet de plaats van het label en de grootte van het label wordt gebaseerd op de tekst in het label

        JLabel jlDateText = new JLabel("Datum"); // label voor de datum
        jlDateText.setFont(new Font("Arial", Font.PLAIN, 20));  // lettertype instellen op font 20
        panelTitles.add(jlDateText); // label toevoegen aan panel van de titels
        Dimension sizeDateText = jlDateText.getPreferredSize(); // maakt een dimensie aan met de grootte van het label
        jlDateText.setBounds(1100, 10, sizeDateText.width +10, sizeDateText.height); // zet de plaats van het label en de grootte van het label wordt gebaseerd op de tekst in het label


        super.add(panelTitles); // panel van de titels toevoegen aan het hoofdscherm

        JPanel panel2 = new JPanel(); // panel aanmaken
        panel2.setLayout(new FlowLayout()); // panel instllen op flowlayout
        panel2.setPreferredSize(new Dimension(1500, 100* orders.size() + 50)); // grootte van dit panel instellen

        int teller = 0; // variable teller
        for (Order order : orders) { // for loop, om de orders te laten doorlopen
            JPanel panelOrders = new OrderPanel(order); //panel aanmaken voor de orders
            panel2.add(panelOrders); //panel voor de orders toevoegen aan panel2
            Dimension sizeOrderPanel = panelOrders.getPreferredSize(); // grootte van het panel bepalen
            panelOrders.setBounds(0, sizeOrderPanel.height * teller, sizeOrderPanel.width, sizeOrderPanel.height); // de panels achterelkaar plaatsen
            teller ++; // de teller optellen
        }


        JScrollPane scrollPane = new JScrollPane(panel2); // aanmaken scrollPane en toevoegn aan penl 2
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // hierdoor wordt nooit een horizontale scrollbar toegevoegd
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // altijd standaard een scrollbar toevoegen
        scrollPane.setPreferredSize(new Dimension(1500, 535)); // de grootte van het scrollpane bepalen
        super.add(scrollPane); // scrollpane toevoegen aan de hoofdpagina

        JPanel panel3 = new JPanel(); // panel aanmaken
        panel3.setLayout(null); // panel zetten op geen layout voor het handmatig instellen van de locaties
        panel3.setPreferredSize(new Dimension(1500, 50)); // de grootte van het pannel instellen
//        panel3.setBackground(Color.magenta); // voor het debuggen

        jb_view = new JButton("Inzien"); // jbutton aanmaken voor inzien
        panel3.add(jb_view); // button toevoegen aan panel 3
        jb_view.setFont(new Font("Arial", Font.PLAIN, 17));
        Dimension sizeViewButton = jb_view.getPreferredSize(); // de grootte van het scrollpane bepalen
        jb_view.setBounds(1400, 10, 150, 40); // de grootte bepalen aan de hand van de grootte van de tekst in de button
        jb_view.addActionListener(this); // actionlistener toevoegen

        super.add(panel3); // panel3 toevoegen aan het hoofdscherm



    }

    public void actionPerformed(ActionEvent e) {
    }


}
