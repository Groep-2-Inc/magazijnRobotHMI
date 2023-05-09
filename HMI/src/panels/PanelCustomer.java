package panels;
import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.valueOf;
import testClasses.*;

public class PanelCustomer extends JPanel {
    private Customer customer; // een klant (Joëlle)
    public PanelCustomer(Customer customer) {
        this.customer = customer; // het attribuut krijgt de de meegegeven waarde (Joëlle)
        Font arial12 = new Font("Arial", Font.PLAIN, 12);

        //standaardinstellingen: grootte, kleur en juiste layout(geen) meegegeven (Joëlle)
        setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(1.9f))); // procenten meegegeven (Joëlle)
        setBackground(Color.white);
        setLayout(null);

        //aanmaken en toevoegen label en de juiste grootte en locatie meegeven (Joëlle)
        JLabel jl_customerName = new JLabel(customer.getCustomerName());
        jl_customerName.setFont(arial12);
        add(jl_customerName);
        Dimension sizeCustomerName = jl_customerName.getPreferredSize();
        jl_customerName.setBounds(10, 0, sizeCustomerName.width, sizeCustomerName.height);

        //aanmaken en toevoegen label en de juiste grootte en locatie meegeven (Joëlle)
        JLabel jl_customerID = new JLabel(", " + customer.getCustomerID());
        jl_customerID.setFont(arial12);
        add(jl_customerID);
        Dimension sizeCustomerID = jl_customerID.getPreferredSize();
        jl_customerID.setBounds(10 + sizeCustomerName.width, 0, sizeCustomerID.width + 10, sizeCustomerID.height);
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
