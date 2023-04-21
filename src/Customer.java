
public class Customer {
    private int customerID;
    private static int amountOfCustomers = 0;
    private String customername;

    public Customer(String customername){
        this.customername = customername;
        this.customerID = amountOfCustomers;
        amountOfCustomers++;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomername() {
        return customername;
    }
}
