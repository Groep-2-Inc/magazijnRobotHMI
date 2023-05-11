package classes;
// Adres toegevoegd door Martijn

public class Customer {
    private int customerID;
    private String customerName;
    private String customerAdres;
    private String postalCode;
    private String city;

    public Customer(int customerID, String customerName, String customerAdres, String postalCode, String city) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAdres = customerAdres;
        this.postalCode = postalCode;
        this.city = city;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAdres() {
        return customerAdres;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }
}
