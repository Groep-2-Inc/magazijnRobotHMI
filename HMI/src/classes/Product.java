package classes;
// Bijgewerkt door Martijn tijdens orders uit DB

public class Product {
    private int productID;
    private String productName;
    private String imgPath;
    private int amountOrdered;
    private int stock;
    private String binLocation;

    public Product(int productID, String productName, String imgPath, int amountOrdered, String binLocation) {
        this.productID = productID;
        this.productName = productName;
        this.imgPath = imgPath;
        this.amountOrdered = amountOrdered;
        this.binLocation = binLocation;
        this.stock = 0;
    }

    public Product(int productID, String productName, String imgPath, String binLocation, int stock){
        this(productID, productName, imgPath, 0, binLocation);
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBinLocation() {
        return binLocation;
    }
}

