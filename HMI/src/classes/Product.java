package classes;
// Bijgewerkt door Martijn tijdens orders uit DB

public class Product {
    private int productID;
    private String productName;

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                '}';
    }

    private String imgPath;
    private int amountOrdered; // New!
    private int stock;

    public Product(int productID, String productName, String imgPath, int amountOrdered) {
        this.productID = productID;
        this.productName = productName;
        this.imgPath = imgPath;
        this.amountOrdered = amountOrdered;
        this.stock = 0;
    }

    public Product(int productID, String productName, int stock, String imgPath){
        this(productID, productName, imgPath, 0);
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
}

