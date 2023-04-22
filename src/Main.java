

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
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
//
        FrameOrders frame = new FrameOrders(orders);
    }
}
