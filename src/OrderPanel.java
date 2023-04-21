import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import static java.lang.String.valueOf;

public class OrderPanel extends JPanel {
    private Order order; // een order

    public OrderPanel(Order order) {
        this.order = order; // het attribuut krijgt de de meegegeven waarde
        //standaardinstelling: grootte, kleur en juiste layout(geen) meegegeven
        setPreferredSize(new Dimension(1500, 100));
        setBackground(Color.white);
        setLayout(null);

        //label aanmaken voor de order, int waarde omgezet naar string en juiste lettertype, grootte en plaats meegegeven
        String stringOrderID = valueOf(this.order.getOrderID());
        JLabel jlOrderID = new JLabel(stringOrderID);
        jlOrderID.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jlOrderID);
        Dimension sizeOrderID = jlOrderID.getPreferredSize();
        jlOrderID.setBounds(50, 40, sizeOrderID.width, sizeOrderID.height);

        //label aanmaken voor de  naam en juiste lettertype, grootte en plaats meegegeven
        JLabel jlCustomerName = new JLabel(this.order.getCustomer().getCustomername());
        jlCustomerName.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jlCustomerName);
        Dimension sizeCustomerName = jlCustomerName.getPreferredSize();
        jlCustomerName.setBounds(400, 40, sizeCustomerName.width + 10, sizeCustomerName.height);

        //label aanmaken voor de custumerID, int waarde omgezet naar string en juiste lettertype, grootte en plaats meegegeven
        JLabel jlCustomerID = new JLabel(", " + this.order.getCustomer().getCustomerID());
        jlCustomerID.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jlCustomerID);
        Dimension sizeCustomerID = jlCustomerID.getPreferredSize();
        jlCustomerID.setBounds(400 + sizeCustomerName.width + 2, 40, sizeCustomerID.width + 10, sizeCustomerID.height);

        //label aanmaken voor de producten, int waarde omgezet naar string en juiste lettertype, grootte en plaats meegegeven
        String stringProductAmount = valueOf(this.order.getProductAmount());
        JLabel jlProductAmount = new JLabel(stringProductAmount);
        jlProductAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jlProductAmount);
        Dimension sizeProductAmount = jlProductAmount.getPreferredSize();
        jlProductAmount.setBounds(750, 40, sizeProductAmount.width, sizeProductAmount.height);

        //label aanmaken voor de datum, datum converteren naar bepaald patroon en juiste lettertype, grootte en plaats meegeven
        Date date = this.order.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String strDate = dateFormat.format(date);
        JLabel jlDate = new JLabel(strDate);
        jlDate.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jlDate);
        Dimension sizeDate = jlDate.getPreferredSize();
        jlDate.setBounds(1100, 40, sizeDate.width, sizeDate.height);
    }
}
