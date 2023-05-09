package panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.String.valueOf;

import testClasses.*;

public class PanelProduct2 extends JPanel{
    private BufferedImage productImg; // plaatje in de vorm van BufferedImage (Joëlle)
    private Image image; // plaatje in de vorm van een plaatje (Joëlle)
    private Product product; // product (Joëlle)
    private JLabel jl_productWantedQuantity = new JLabel("Aantal:");

    public PanelProduct2(Product product) {
        this.product = product; // het attribuut krijgt de de meegegeven waarde (Joëlle)

        //standaardinstelling: grootte, kleur en juiste layout(geen) meegegeven (Joëlle)
        setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(9.3f)));
        setBackground(Color.white);
        setLayout(null);

        //standaard fonts (Joëlle)
        Font arial15 = new Font("Arial", Font.PLAIN, 15);

        //proberen, het plaatje te laden en hem te verkleinen naar juiste grootte (Joëlle)
        try {
            productImg = ImageIO.read(new File(this.product.getImgPath()));
            image = productImg.getScaledInstance(100, 65, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
//            System.out.println(getClass() + ": no image available");
        }

        // label aanmaken plus waarde toekennen, toevoegen aan het panel en juiste grootte en locatie toevoegen (Joëlle)
        JLabel jl_productName = new JLabel(product.getProductName());
        jl_productName.setFont(arial15);
        add(jl_productName);
        Dimension sizeProductName = jl_productName.getPreferredSize();
        jl_productName.setBounds(300, 30, sizeProductName.width + 20, sizeProductName.height);

        // label aanmaken plus waarde toekennen, toevoegen aan het panel en juiste grootte en locatie toevoegen (Joëlle)
        String str_productID = valueOf(product.getProductID());
        JLabel jl_productID = new JLabel(str_productID);
        jl_productID.setFont(arial15);
        add(jl_productID);
        Dimension sizeProductID = jl_productName.getPreferredSize();
        jl_productID.setBounds(300 + sizeProductName.width + 10, 30, sizeProductID.width, sizeProductID.height);

        // label aanmaken plus waarde toekennen, toevoegen aan het panel en juiste grootte en locatie toevoegen (Joëlle)
        String str_productQuantity = valueOf(product.getStock()); // int omzetten naar string (Joëlle)
        JLabel jl_quantityTitle = new JLabel("Hoeveelheid op voorraad: " + str_productQuantity);
        jl_quantityTitle.setFont(arial15);
        add(jl_quantityTitle);
        Dimension sizeQuantityTitle = jl_quantityTitle.getPreferredSize();
        jl_quantityTitle.setBounds(1000, 30, sizeQuantityTitle.width + 10, sizeQuantityTitle.height);

        //label toevoegen aan scherm en juist grootte en locatie meegeven
        JLabel jl_productWantedQuantity = new JLabel("Aantal:");
        jl_productWantedQuantity.setFont(arial15);
        add(jl_productWantedQuantity);
        Dimension sizeProductWantedQuantity = jl_productWantedQuantity.getPreferredSize();
        jl_productWantedQuantity.setBounds(1300, 30, sizeProductWantedQuantity.width + 10, sizeProductWantedQuantity.height);

        JTextField jtf_productWantedQuantity = new JTextField("1", 6);
        jtf_productWantedQuantity.setFont(arial15);
        add(jtf_productWantedQuantity);
        Dimension sizeProductWantedQuantityTextfield = jtf_productWantedQuantity.getPreferredSize();
        jtf_productWantedQuantity.setBounds(1300 + sizeProductWantedQuantity.width, 30, sizeProductWantedQuantityTextfield.width + 10, sizeProductWantedQuantityTextfield.height);


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        setBackground(Color.white);
        //tekentt plaatje en lijnen daaromheen (Joëlle)
        g.drawImage(image, 50, 5, null);
        g.drawRect(50, 5, 100, 65);

    }
    //Methode die de grootte van het scherm bepaald en berekend met procenten naar de juiste waarde, moet nog anders (Joëlle)
    public int getScreenWidth(Float percentage){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() /100 * percentage);
//        System.out.println(width); // voor het debuggen
        return width;
    }
    //Methode die de grootte van het scherm bepaald en berekend met procenten naar de juiste waarde, moet nog anders (Joëlle)
    public int getScreenHeight(Float percentage){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) (screenSize.getHeight() /100 * percentage);
//        System.out.println(height); // voor het debuggen
        return height;
    }

}
