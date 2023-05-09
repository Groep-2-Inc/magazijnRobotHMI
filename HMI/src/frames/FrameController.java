package frames;
// Jason Joshua

import classes.Activity;
import classes.Order;
import classes.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;


public class FrameController {
    private static FrameHome jf_home = new FrameHome();
    private static FrameVerwerken jf_FrameVerwerken;
    private static FrameOrders jf_FrameOrders;
    private static FrameProducts jf_FrameProducts;
    private static FrameJournal jf_FrameJournal;
    private static FrameViewingOrder jf_FrameViewingOrder;
    private static FramePackingList jf_FramePackingList;
    private static FrameMakeOrder jf_FrameMakeOrder;

    public FrameController(){
        jf_home.setVisible(true);

        //dummydata voor de orders pagina
        Product product = new Product(5, "Fiets", "src/images/bicycle.jpg", 0);
        Product product2 = new Product(6, "Auto", "", 0);
        Product product3 = new Product(7, "Boot", "", 0);
        Product product4 = new Product(8, "Hamer", "", 0);
        Product product5 = new Product(9, "Schrift", "", 0);
        Product product6 = new Product(10, "Geodriehoek", "", 0);
        Product product7 = new Product(11, "Microfoon", "", 0);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        //initialiseer orders frame
        jf_FrameOrders = new FrameOrders();

        //dummydata voor de FrameProducts
        products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        //initialiseer products frame
        jf_FrameProducts = new FrameProducts(products);

        //dummydata journal frame
        ArrayList<Activity> activities = new ArrayList<>();
        Date date = new Date();
        Activity a1 = new Activity(date, "logboek openen");
        Activity a2 = new Activity(date, "statis bekijken");
        Activity a3 = new Activity(date, "darkmode ingesteld");
        Activity a4 = new Activity(date, "einde bereikt");
        for(int i = 0 ; i < 30; i++){
            activities.add(a1);
            activities.add(a2);
            activities.add(a3);
        }
        activities.add(a4);

        //initialiseer journal frame
        jf_FrameJournal = new FrameJournal(activities);

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

    public static void setActiveFrameHome(JFrame f){
        if(jf_home != f){
            jf_home.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFrameVerwerken(JFrame f, Order o){
        if(jf_FrameVerwerken != f){
            jf_FrameVerwerken = new FrameVerwerken(o);
            jf_FrameVerwerken.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFramePackingList(JFrame f, Order o){
        if(jf_FramePackingList != f){
            jf_FramePackingList = new FramePackingList(o);
            jf_FramePackingList.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFrameJournal(JFrame f){
        if(jf_FrameJournal != f){
            jf_FrameJournal.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFrameProducts(JFrame f){
        if(jf_FrameProducts != f){
            jf_FrameProducts.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveFrameOrders(JFrame f){
        if(jf_FrameOrders != f){
            jf_FrameOrders.setVisible(true);
            f.setVisible(false);
        }
    }

    public static void setActiveViewingOrder(JFrame f, Order o){
        if(jf_FrameViewingOrder != f){
            jf_FrameViewingOrder = new FrameViewingOrder(o);
            jf_FrameViewingOrder.setVisible(true);
            f.setVisible(false);
        }
    }
    public static void setActiveFrameMakeOrder(JFrame f){
        if(jf_FrameMakeOrder != f){
            jf_FrameMakeOrder.setVisible(true);
            f.setVisible(false);
        }
    }

}
