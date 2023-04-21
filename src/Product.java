public class Product {
    private String productname;
    private int productID;
    private static int amountOfProducts = 0;
    private int stock;
    private String imgPath;


    public Product(String productname, int stock){
        this.productname = productname;
        this.stock = stock;
        this.productID = amountOfProducts +1;
        this.imgPath = "unknown";
        amountOfProducts++;
    }
    public Product(String productname, int stock, String imgPath){
        this(productname, stock);
        this.imgPath = imgPath;
    }

    public String getProductname() {
        return productname;
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

    public void setStock(int stock) {
        this.stock = stock;
    }
}

