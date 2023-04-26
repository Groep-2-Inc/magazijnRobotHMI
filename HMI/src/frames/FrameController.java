package frames;

import testClasses.Activity;
import testClasses.Customer;
import testClasses.Order;
import testClasses.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
//code door Jason Joshua

import comms.Communication;

public class FrameController {
    private static FrameHome jf_home = new FrameHome();
    private static FrameVerwerken jf_FrameVerwerken;
    private static FrameOrders jf_FrameOrders;
    private static FrameProducts jf_FrameProducts;
    private static FrameJournal jf_FrameJournal;
    private static FrameViewingOrder jf_FrameViewingOrder;
    private static FramePackingList jf_FramePackingList;
    private static FrameMakeOrder jf_FrameMakeOrder;

    public Communication comms;

    public FrameController(Communication comms){
        this.comms = comms;

        jf_home.setVisible(true);

        //dummydata voor de orders pagina
        Product product = new Product("Fiets", 4, "src/bicycle.jpg");
        Product product2 = new Product("Auto", 100);
        Product product3 = new Product("Boot", 11);
        Product product4 = new Product("Hamer", 11);
        Product product5 = new Product("Schrift", 11);
        Product product6 = new Product("Geodriehoek", 11);
        Product product7 = new Product("Microfoon", 11);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        ArrayList<Product> products2 = new ArrayList<>();
        products.add(product);
        products.add(product2);
        Customer customer = new Customer("Piet");
        Customer customer2 = new Customer("Klaas");
        Customer customer3 = new Customer("Annabel");
        Customer customer4 = new Customer("Erik");
        Order order = new Order(customer, products);
        Order order2 = new Order(customer2, products);
        Order order3 = new Order(customer3, products);
        Order order4 = new Order(customer4, products2);
        ArrayList<Order> orders= new ArrayList<>();
        orders.add(order);
        orders.add(order2);
        orders.add(order3);
        orders.add(order);
        orders.add(order2);
        orders.add(order3);
        orders.add(order);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        //initialiseer orders frame
        jf_FrameOrders = new FrameOrders(orders);

        //dummydata voor de FrameProducts
        product = new Product("Fiets", 4, "src/images/bicycle.jpg");
        product2 = new Product("Auto", 100);
        product3 = new Product("Boot", 11);
        product4 = new Product("Hamer", 11);
        product5 = new Product("Schrift", 11);
        product6 = new Product("Geodriehoek", 11);
        product7 = new Product("Microfoon", 11);
        Product product8 = new Product("Controller", 1456);
        products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        customer = new Customer("Piet");
        customer2 = new Customer("Klaas");
        order = new Order(customer, products);
        order2 = new Order(customer2, products);

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
        product = new Product("Fiets", 4);
        product2 = new Product("Auto", 100);
        product3 = new Product("Boot", 11);
        product4 = new Product("Hamer", 11);
        product5 = new Product("Schrift", 11);
        product6 = new Product("Geodriehoek", 11);
        product7 = new Product("Microfoon", 11);
        products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);

        ArrayList<Customer> customers = new ArrayList<>();
        customer = new Customer("Piet");
        customer2 = new Customer("Klaas");
        customer3 = new Customer("Annabel");
        customer4 = new Customer("Rick");
        customers.add(customer);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);

        order = new Order(customer, products);
        order2 = new Order(customer2, products);
        order3 = new Order(customer3, products);
        orders= new ArrayList<>();
        orders.add(order);
        orders.add(order2);
        orders.add(order3);
        orders.add(order);
        orders.add(order2);
        orders.add(order3);
        orders.add(order);
        orders.add(order2);
        orders.add(order3);

        //initialiseer framemakeorder
        jf_FrameMakeOrder = new FrameMakeOrder(products, customers);
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
