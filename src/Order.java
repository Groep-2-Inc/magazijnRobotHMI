import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int orderID;
    private static int amountOfOrders = 0;
    private Customer customer;
    private ArrayList<Product> products;
    private int productAmount;
    private Date date;

    public Order(Customer customer, ArrayList<Product> products){
        this.products = products;
        this.customer = customer;
        this.orderID = amountOfOrders;
        this.date = new Date();
        this.productAmount = products.size();
        amountOfOrders++;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public void addProduct(String productname, int stock, String imgPath){
        products.add( new Product(productname, stock, imgPath));
    }
}
