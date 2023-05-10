package classes;
import java.util.ArrayList;
import java.util.Date;

public class Order{
    private int orderID;
    private Customer customer;
    private ArrayList<Product> products;
    private int productCount;
    private Date date;

    public Order(int orderID, Customer customer, ArrayList<Product> products, Date orderDate){
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.productCount = products.size();
        this.date = orderDate;
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
}
