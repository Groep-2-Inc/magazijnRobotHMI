package classes;
// Bijgewerkt door Martijn tijdens orders uit DB

public class Product {
    private int productID;
    private String productName;
    private String imgPath;
    private int amountOrdered;
    private int stock;
    private int weight;
    private String binLocation;

    public Product(int productID, String productName, String imgPath, int amountOrdered, int weight, String binLocation) {
        this.productID = productID;
        this.productName = productName;
        this.imgPath = imgPath;
        this.amountOrdered = amountOrdered;
        this.binLocation = binLocation;
        this.weight = weight;
        this.stock = 0;
    }

    public Product(int productID, String productName, String imgPath, int stock, String binLocation, int weight){
        this(productID, productName, imgPath, 0, weight, binLocation);
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductID() {
        return productID;
    }

    public int getStock() {
        return stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }

    public void setAmountOrdered(int amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getWeight() {
        return weight;
    }

    public String getBinLocation() {
        return binLocation;
    }
}

