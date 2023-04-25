package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.String.valueOf;

import panels.*;
import testClasses.*;

public class FrameMakeOrder extends FrameHeader {
    // labels
    private JLabel jl_makeOrder = new JLabel("Order aanmaken"); // tekst van de pagina (Joëlle)
    private JLabel jl_searchCustomer = new JLabel ("Klant zoeken"); //tekstveld met Titel voor boven het scrollpane (Joëlle)
    private JLabel jl_orderNumber; // tekstveld met daarin huidige ordernummer (Joëlle)
    private JLabel jl_orderDate; // tekstveld met huidige datum  (Joëlle)
    //tekstvelden
    private JTextField jtf_searchCustomer = new JTextField("Klantnaam", 10); //tekstveld voor invullen klantnaam voor boven het scrollpane (Joëlle)

    //buttons
    private JButton jb_searchCustomer= new JButton("Zoek klant"); //button om een klant te zoeken (Joëlle)
    private JButton jb_cancel = new JButton("Annuleren"); //button om order aanmaken te annuelern -> scherm terug? (Joëlle)
    private JButton jb_save = new JButton("Opslaan"); // button om order op te slaan (Joëlle)
    //arraylists
    private ArrayList<Product> products; // arraylist met producten (Joëlle)
    private ArrayList<Customer> customers; // arraylist met customers (Joëlle)
    private ArrayList<JButton> buttonsCustomers = new ArrayList<>(); // alle buttons uit het klantenpanel komen in deze lijst (Joëlle)
    private ArrayList<JButton> buttonsProducts = new ArrayList<>(); // alle buttons uit het productenpanel komen in deze lijst (Joëlle)
    private ArrayList<makeButtons> booleansButtonslistProducts = new ArrayList<>(); // lijst met alle booleans voor de buttons lijst 1 (Joëlle)
    private ArrayList<makeButtons> booleansButtonslistCustomers = new ArrayList<>(); // lijst met alle booleans voor de buttons lijst 2 (Joëlle)
    private ArrayList<Product> selectedProducts = new ArrayList<>(); // lijst met geselecteerde producten

    public FrameMakeOrder(ArrayList<Product> products,ArrayList<Customer> customers){
        //standaardinstellingen( Joëlle
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Java-application/MakeOrders"); // moet nog een betere naam hebben

        //standaard fonts (Joëlle)
        Font arial17 = new Font("Arial", Font.PLAIN, 17);

        //variable waarde toekennen (Joëlle)
        this.products = products;
        this.customers = customers;

        //panel aangemaakt met juiste grootte (Joëlle)
        JPanel panelTitle = new JPanel();
        panelTitle.setLayout(null);
        panelTitle.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(4.5f))); //procenten toevoegen (Joëlle)

        //label toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        jl_makeOrder.setFont(new Font(jl_makeOrder.getFont().getName(), jl_makeOrder.getFont().getStyle(), 20));
        panelTitle.add(jl_makeOrder);
        Dimension sizeMakeOrder = jl_makeOrder.getPreferredSize();
        jl_makeOrder.setBounds(0, 5, sizeMakeOrder.width, sizeMakeOrder.height);

        super.add(panelTitle); //panel toevoegen aan hoofdscherm (Joëlle)

        //panel aangemaakt met juiste grootte (Joëlle)
        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(null);
        panelLabels.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(4.5f))); //procenten toevoegen (Joëlle)

        //label aanmaken en toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        String str_Ordernummer = valueOf(Order.getAmountOfOrders()+1);  // huidige ordernummer meegeven
        jl_orderNumber = new JLabel("Ordernummer: " + str_Ordernummer);
        jl_orderNumber.setFont(arial17);
        panelLabels.add(jl_orderNumber);
        Dimension sizeOrderNumberLabel = jl_orderNumber.getPreferredSize();
        jl_orderNumber.setBounds(0, 10, sizeOrderNumberLabel.width + 10, sizeOrderNumberLabel.height);

        //label aanmaken en juiste waarde geven en toevoegen aan panel en de juiste plek, grootte en lettertype meegeven (Joëlle)
        Date date = new Date(); // converteren van de datum naar string
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        jl_orderDate = new JLabel("Orderdatum: " + strDate);
        jl_orderDate.setFont(arial17);
        panelLabels.add(jl_orderDate);
        Dimension sizeOrderDateLabel = jl_orderDate.getPreferredSize();
        jl_orderDate.setBounds(190, 10, sizeOrderDateLabel.width + 10, sizeOrderDateLabel.height);

        super.add(panelLabels); // panel toevoegen aan hoofdscherm (Joëlle)

        //panel aanmaken die boven het scrollpane komt  en juiste grootte en kleur meegevej(Joëlle)
        JPanel panelSearchCustomers = new JPanel();
        panelSearchCustomers.setLayout(null);
        panelSearchCustomers.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(4.5f))); //procenten toevoegen (Joëlle)
        panelSearchCustomers.setBackground(Color.lightGray);

        //label de de juiste lettertype en grootte meegeven en toevoegen aan het panel (Joëlle)
        jl_searchCustomer.setFont(arial17);
        panelSearchCustomers.add(jl_searchCustomer);
        Dimension sizeSearchCustomer = jl_searchCustomer.getPreferredSize();
        jl_searchCustomer.setBounds(0, 10, sizeSearchCustomer.width + 10, sizeSearchCustomer.height);

        //tekstveld toevoegen aan panel en juiste grootte meegeven (Joëlle)
        panelSearchCustomers.add(jtf_searchCustomer);
        jtf_searchCustomer.setBounds(1200, 5, 120, 30);

        //tekstveld toevoegen aan panel en juiste grootte meegeven (Joëlle)
        panelSearchCustomers.add(jb_searchCustomer);
        jb_searchCustomer.setBounds(1350, 5, 120, 30);
        jb_searchCustomer.addActionListener(this);


        super.add(panelSearchCustomers); // panel toevoegen aan hoofdscherm (Joëlle)

        //panel aanmaken, waar het scrollpanel inkomt en juiste grootte meegeven (Joëlle)
        JPanel panelCustomers = new JPanel();
//        panelCustomers.setLayout(new BoxLayout(panelCustomers, BoxLayout.Y_AXIS)); // mogelijk nog nodig
        panelCustomers.setLayout(new FlowLayout());
        panelCustomers.setPreferredSize(new Dimension(getScreenWidth(98f), 31 * customers.size())); //procenten toegevoeged (Joëlle)

        //for loop die buttons toevoegd, daar panels inzet per klant, geeft juiste grootte, kleur en locatie mee aan de buttons en de locatie (Joëlle)
        for (int i = 0; i < this.customers.size(); i++) {
            buttonsCustomers.add(new JButton());
            booleansButtonslistCustomers.add(new makeButtons(false)); // variable buttons aanmaken, gelijk aan de buttons lijst (Joëlle)
            JPanel CustomerPanel = new PanelCustomer(this.customers.get(i));
            buttonsCustomers.get(i).add(CustomerPanel);
            panelCustomers.add(buttonsCustomers.get(i));
            buttonsCustomers.get(i).setBackground(Color.white);
            Dimension sizeCustomerPanel = panelCustomers.getPreferredSize();
            panelCustomers.setBounds(0, sizeCustomerPanel.height * i, sizeCustomerPanel.width, sizeCustomerPanel.height);

            Dimension sizeButtonCustomer = buttonsCustomers.get(i).getPreferredSize();
            buttonsCustomers.get(i).setBounds(0, sizeButtonCustomer.height * i, sizeButtonCustomer.width, sizeButtonCustomer.height);
        }

        //for loop die controleerd die elke knop in de arrayList langsgaat en checkt of die gedrukt is, bij het indrukken, wordt er een regel geprint, dat is alleen voor het debuggen (Joëlle)
        for (int i = 0; i < this.customers.size(); i++) {
            final int buttonNumber = i + 1;
            buttonsCustomers.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    !! uiteindelijk moet de zoekknop ook werken, maar dat doen we met de database, dus dat komt nog (Joëlle)
//                    Bij het indrukken van de button wordt gecheckt of die button al eerder ingedrukt is, als die nog niet ingedrukt wordt de button zwart, anders wordt hij(weer) wit (Joëlle)
                    if(booleansButtonslistCustomers.get(buttonNumber-1).getPressed() == false){
                        buttonsCustomers.get(buttonNumber-1).setBackground(Color.BLACK);
                        booleansButtonslistCustomers.get(buttonNumber-1).setPressed(true);
                        selectedProducts.add(products.get(buttonNumber-1));
                    }else{
                        buttonsCustomers.get(buttonNumber-1).setBackground(Color.white);
                        booleansButtonslistCustomers.get(buttonNumber-1).setPressed(false);
                        // Product wordt verwijderd als id klopt, dus bij 2x klikken staat het product niet meer in de lijst (Joëlle)
                    }


                    System.out.println("Gedrukt op knop nummer " + buttonNumber ); // voor het debuggen kijken of de knop werkt (Joëlle)
                    //straks moet er iets komen van Panel = new Panel(orders.get(buttonNumber-1)) -> moet nog beter uitgewerkt worden (Joëlle)
                }
            });
        }

        //scrolpanel aanmaken en toevoegen aan het panel van de klanten, juiste grootte meegeven (Joëlle)
        JScrollPane scrollPane1 = new JScrollPane(panelCustomers);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(11.5f))); // procenten toegevoegd (Joëlle)
        super.add(scrollPane1);

        //panel aanmaken en juiste layout en grootte meegeven (Joëlle)
        JPanel panelProducts = new JPanel();
        panelProducts.setLayout(new FlowLayout());
        panelProducts.setPreferredSize(new Dimension(getScreenWidth(98f), 95 * products.size() +5 )); //procenten toevoegen (Joëlle)

        //for loop die buttons toevoegd, daar panels inzet per klant, geeft juiste grootte, kleur en locatie mee aan de buttons en de locatie, voegt ook aan het lijste met booleans een extra toe (Joëlle)
        for (int i = 0; i < products.size(); i++) {
            buttonsProducts.add(new JButton()); // button veranderen in button (Joëlle)
            booleansButtonslistProducts.add(new makeButtons(false)); // variable buttons aanmaken, gelijk aan de buttons lijst (Joëlle)
            JPanel panelProduct = new PanelProduct2(products.get(i));
            buttonsProducts.get(i).add(panelProduct);
            panelProducts.add(buttonsProducts.get(i));
            buttonsProducts.get(i).setBackground(Color.white);
            Dimension sizePanelOrders = panelProducts.getPreferredSize();
            panelProducts.setBounds(0, sizePanelOrders.height * i, sizePanelOrders.width, sizePanelOrders.height);

            Dimension sizeButtonOrders = buttonsProducts.get(i).getPreferredSize();
            buttonsProducts.get(i).setBounds(0, sizeButtonOrders.height * i, sizeButtonOrders.width, sizeButtonOrders.height);

        }
//  for loop die controleerd die elke knop in de arrayList langsgaat en checkt of die gedrukt is (Joëlle)

        for (int i = 0; i < this.products.size(); i++) {
            final int buttonNumber1 = i + 1;
            buttonsProducts.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                      Bij het indrukken van de button wordt gecheckt of die button al eerder ingedrukt is, als die nog niet ingedrukt wordt de button zwart, anders wordt hij(weer) wit (Joëlle)
//                      Ook wordt het product wat op die button staat toegevoegd aan het huidige lijstje met geselecteerde producten (Joëlle)
                    if(booleansButtonslistProducts.get(buttonNumber1-1).getPressed() == false){
                        buttonsProducts.get(buttonNumber1-1).setBackground(Color.BLACK);
                        booleansButtonslistProducts.get(buttonNumber1-1).setPressed(true);
                        selectedProducts.add(products.get(buttonNumber1-1));
                    }else{
                        buttonsProducts.get(buttonNumber1-1).setBackground(Color.white);
                        booleansButtonslistProducts.get(buttonNumber1-1).setPressed(false);
                        //!for loop werkt niet, want product wordt niet verwijderd als id klopt, dus bij 2x klikken staat het product nog steeds in de lijst (Joëlle)
                        for(int i = 0; i < selectedProducts.size(); i++){
                            if(selectedProducts.get(i).getProductID() == products.get(buttonNumber1-1).getProductID()){
                                selectedProducts.remove(i); //! verwijdert nohg niet uit lijst (Joëlle)
                            }

                        }
                    }
                    System.out.println("Gedrukt op knop nummer " + buttonNumber1 ); // voor het debuggen kijken of de knop werkt
                    //straks moet er iets komen van Panel = new Panel(orders.get(buttonNumber-1)) -> moet nog beter uitgewerkt worden
                }
            });
        }

        //scrollpanel aanmaken en toevoegen aan het panel voor de producten en verticale scrollbar toevoegen (Joëlle)
        JScrollPane scrollPane2 = new JScrollPane(panelProducts);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(45f))); //procenten toevoegen (Joëlle)
        super.add(scrollPane2);

        //nieuw panel toevoegen voor de buttons (Joëlle)
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(null);
        panelButtons.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(4.5f))); //procenten toevoegen (Joëlle)

        //button verwijderen toevoegen aan panel en juiste grootte en locatie geven (Joëlle)
        panelButtons.add(jb_cancel);
        jb_cancel.setBounds(1200, 5, 120, 30);

        //button opslaan aan panel en juiste grootte en locatie geven (Joëlle)
        panelButtons.add(jb_save);
        jb_save.setBounds(1350 , 5, 120, 30);

        jb_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //komende regels voor testen en debuggen, uiteindelijk moet er iets opgeslagen worden (Joëlle)
                //Er wordt een lijste met geselecteerde producten getoond en hierbij wordt de lijst met geslecteerde producten doorlopen (Joëlle)
                System.out.println("Lijstje met geselecteerde producten:");
                for(int i = 0; i< selectedProducts.size(); i++){
                    System.out.println(selectedProducts.get(i).getProductname() + " " + selectedProducts.get(i).getProductID());
                }
            }
        }); // actionlistener toevoegen (Joëlle)

        super.add(panelButtons); // panel buttons toevoegen aan hoofdscherm (Joëlle)

    }

}