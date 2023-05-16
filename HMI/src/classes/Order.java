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
    private boolean orderCompleted;
    private String textOrderCompleted;
    private Color colorTextOrderCompleted;

    public Order(int orderID, Customer customer, ArrayList<Product> products, Date orderDate, int orderCompleted){
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.productCount = products.size();
        this.date = orderDate;

        if(orderCompleted == 1){
            textOrderCompleted = "Voltooid";
            colorTextOrderCompleted = Color.green;
            this.orderCompleted = true;
        }else{
            textOrderCompleted = "Niet voltooid";
            colorTextOrderCompleted = Color.red;
            this.orderCompleted = false;
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
    public boolean isOrderCompleted() {
        return orderCompleted;
    }
    public String getTextOrderCompleted() {return textOrderCompleted;}

    public Color getColorTextOrderCompleted() {return colorTextOrderCompleted;}
}
