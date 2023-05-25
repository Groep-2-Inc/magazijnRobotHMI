package frames;
// Door Jason Joshua
// Demo data eruit door Martijn

import classes.Activity;
import classes.Order;
import javax.swing.*;

public class FrameController {
    private static FrameHome jf_home = new FrameHome();
    private static FrameVerwerken jf_FrameVerwerken;
    private static FrameOrders jf_FrameOrders;
    private static FrameProducts jf_FrameProducts;
    private static FrameLogbook jf_FrameLogbook;
    private static FrameViewingOrder jf_FrameViewingOrder;
    private static FramePackingList jf_FramePackingList;

    public FrameController(){
        Activity.getLogbookData(10);
        jf_home.setVisible(true);

        //initialiseer orders frame
        jf_FrameOrders = new FrameOrders();

        //initialiseer products frame
        jf_FrameProducts = new FrameProducts();

        //initialiseer journal frame
        jf_FrameLogbook = new FrameLogbook();

        //dummydate voor de FrameMakeOrder
//        products = new ArrayList<>();
//        products.add(product);
//        products.add(product2);
//        products.add(product3);
//        products.add(product4);
//        products.add(product5);
//        products.add(product6);
//        products.add(product7);
//
//        ArrayList<Customer> customers = new ArrayList<>();
//        Customer customer = new Customer(11, "Piet");
//        Customer customer2 = new Customer(12, "Klaas");
//        Customer customer3 = new Customer(13, "Annabel");
//        Customer customer4 = new Customer(14, "Rick");
//        customers.add(customer);
//        customers.add(customer2);
//        customers.add(customer3);
//        customers.add(customer);
//        customers.add(customer2);
//        customers.add(customer3);
//        customers.add(customer4);
//
//        order = new Order(customer, products);
//        order2 = new Order(customer2, products);
//        order3 = new Order(customer3, products);
//        orders= new ArrayList<>();
//        orders.add(order);
//        orders.add(order2);
//        orders.add(order3);
//        orders.add(order);
//        orders.add(order2);
//        orders.add(order3);
//        orders.add(order);
//        orders.add(order2);
//        orders.add(order3);

        //initialiseer framemakeorder
//        jf_FrameMakeOrder = new FrameMakeOrder(products, customers);
    }

    // Werkt de positie panel bij
    // Door Martijn
    public static void updatePositiePanel(){
        // Update panel op home pagina
        jf_home.updatePanelPositie();

        // Als de verwerken pagina een keer is geopend
        if(FrameVerwerken.isVerwerken()){
            // Update de verwerken pagina
            jf_FrameVerwerken.updatePanelPositie();
        }
    }

    public static void setActiveFrameHome(JFrame f){ //functie voor het aanzetten van het homeframe
        if(jf_home != f){
            jf_home.updateLogbookPanel();
            jf_home.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFrameVerwerken(JFrame f, Order o){ //functie van het aanzetten van het verwerken frame
        if(jf_FrameVerwerken != f){
            FrameVerwerken.setIsVerwerken(true);
            jf_FrameVerwerken = new FrameVerwerken(o);
            jf_FrameVerwerken.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFramePackingList(JFrame f, Order o){ //functie voor het aanzetten van de framepackinglist
        if(jf_FramePackingList != f){
            jf_FramePackingList = new FramePackingList(o);
            jf_FramePackingList.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFrameJournal(JFrame f){ //functie voor het aanzetten van het journal
        if(jf_FrameLogbook != f){
            Activity.getLogbookData(10);
            jf_FrameLogbook.dispose();
            jf_FrameLogbook = new FrameLogbook();
            jf_FrameLogbook.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFrameProducts(JFrame f){ //functie voor het aanzetten van de products frame
        if(jf_FrameProducts != f){
            jf_FrameProducts.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFrameOrders(JFrame f){ //functie voor het aanzetten van de ordersframe
        if(jf_FrameOrders != f){
            jf_FrameOrders.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveViewingOrder(JFrame f, Order o){ //functie voor het aanzetten van de viewingorder frame
        if(jf_FrameViewingOrder != f){
            jf_FrameViewingOrder = new FrameViewingOrder(o);
            jf_FrameViewingOrder.setVisible(true);
            f.setVisible(false);
        }
    }

}
