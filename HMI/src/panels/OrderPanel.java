package panels;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import testClasses.*;

import static java.lang.String.valueOf;

public class OrderPanel extends JPanel {
    private Order order; // een order (Joëlle)

    public OrderPanel(Order order) {
        this.order = order; // het attribuut krijgt de de meegegeven waarde
        //standaardinstelling: grootte, kleur en juiste layout(geen) meegegeven (Joëlle)
        setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(11.65f))); // procenten toegevoegd (Joëlle)
        setBackground(Color.white);
        setLayout(null);

        // standaard fonts instellen
        Font arial20 = new Font("Arial", Font.PLAIN, 20);

        //label aanmaken voor de order, int waarde omgezet naar string en juiste lettertype, grootte en plaats meegegeven (Joëlle)
        String stringOrderID = valueOf(this.order.getOrderID());
        JLabel jlOrderID = new JLabel(stringOrderID);
        jlOrderID.setFont(arial20);
        add(jlOrderID);
        Dimension sizeOrderID = jlOrderID.getPreferredSize();
        jlOrderID.setBounds(50, 40, sizeOrderID.width, sizeOrderID.height);

        //label aanmaken voor de  naam en juiste lettertype, grootte en plaats meegegeven (Joëlle)
        JLabel jlCustomerName = new JLabel(this.order.getCustomer().getCustomername());
        jlCustomerName.setFont(arial20);
        add(jlCustomerName);
        Dimension sizeCustomerName = jlCustomerName.getPreferredSize();
        jlCustomerName.setBounds(400, 40, sizeCustomerName.width + 10, sizeCustomerName.height);

        //label aanmaken voor de custumerID, int waarde omgezet naar string en juiste lettertype, grootte en plaats meegegeven (Joëlle)
        JLabel jlCustomerID = new JLabel(", " + this.order.getCustomer().getCustomerID());
        jlCustomerID.setFont(arial20);
        add(jlCustomerID);
        Dimension sizeCustomerID = jlCustomerID.getPreferredSize();
        jlCustomerID.setBounds(400 + sizeCustomerName.width + 2, 40, sizeCustomerID.width + 10, sizeCustomerID.height);

        //label aanmaken voor de producten, int waarde omgezet naar string en juiste lettertype, grootte en plaats meegegeven (Joëlle)
        String stringProductAmount = valueOf(this.order.getProductAmount());
        JLabel jlProductAmount = new JLabel(stringProductAmount);
        jlProductAmount.setFont(arial20);
        add(jlProductAmount);
        Dimension sizeProductAmount = jlProductAmount.getPreferredSize();
        jlProductAmount.setBounds(750, 40, sizeProductAmount.width, sizeProductAmount.height);

        //label aanmaken voor de datum, datum converteren naar bepaald patroon en juiste lettertype, grootte en plaats meegeven (Joëlle)
        Date date = this.order.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String strDate = dateFormat.format(date);
        JLabel jlDate = new JLabel(strDate);
        jlDate.setFont(arial20);
        add(jlDate);
        Dimension sizeDate = jlDate.getPreferredSize();
        jlDate.setBounds(1100, 40, sizeDate.width, sizeDate.height);
    }
    //Methode die de grootte van het scherm bepaald en berekend met procenten naar de juiste waarde, staan niet goed hoor, moet nog opgelost worden (Joëlle)
    public int getScreenWidth(Float percentage){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() /100 * percentage);
//        System.out.println(width); // voor het debuggen
        return width;
    }
    //Methode die de grootte van het scherm bepaald en berekend met procenten naar de juiste waarde, staan niet goed hier, moet nog opgelost worden (Joëlle)
    public int getScreenHeight(Float percentage){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) (screenSize.getHeight() /100 * percentage);
//        System.out.println(height); // voor het debuggen
        return height;
    }
}