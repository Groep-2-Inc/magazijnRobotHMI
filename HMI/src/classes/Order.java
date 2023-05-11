package classes;
// Bijgewerkt door Martijn voor order uit DB

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Order{
    private int orderID;
    private Customer customer;
    private ArrayList<Product> products;
    private int productCount;
    private Date date;
    private int orderCompleted;
    private String TextOrderCompleted;
    private Color ColorTextOrderCompleted;

    public Order(int orderID, Customer customer, ArrayList<Product> products, Date orderDate, int orderCompleted){
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.productCount = products.size();
        this.date = orderDate;
        this.orderCompleted = orderCompleted;
        if(orderCompleted == 1){
            TextOrderCompleted = "Voltooid";
            ColorTextOrderCompleted = Color.green;
        }else{
            TextOrderCompleted = "Niet voltooid";
            ColorTextOrderCompleted = Color.red;
        }
    }

    public int getOrderID() {
        return orderID;
    }
    public Customer getCustomer() {
        return customer;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public int getProductCount() {
        return productCount;
    }
    public Date getDate() {
        return date;
    }
    public int isOrderCompleted() {
        return orderCompleted;
    }

    public String getTextOrderCompleted() {return TextOrderCompleted;}

    public Color getColorTextOrderCompleted() {return ColorTextOrderCompleted;}
}
