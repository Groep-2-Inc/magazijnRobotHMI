package panels;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import frames.FrameHeader;
import testClasses.*;

import static java.lang.String.valueOf;

public class PanelOrder extends JPanel {
    private Order order; // een order (Joëlle)

    public PanelOrder(Order order) {
        this.order = order; // het attribuut krijgt de meegegeven waarde
        //standaardinstelling: grootte, kleur en juiste layout(geen) meegegeven (Joëlle)
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(97f), FrameHeader.getScreenHeight(6f))); // procenten toegevoegd (Joëlle)
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
        jlOrderID.setBounds(FrameHeader.getScreenWidth(3.255208333f), FrameHeader.getScreenHeight(2f), sizeOrderID.width, sizeOrderID.height); // x = 50 pixels, y = 40 pixels

        //label aanmaken voor de  naam en juiste lettertype, grootte en plaats meegegeven (Joëlle)
        JLabel jlCustomerName = new JLabel(this.order.getCustomer().getCustomername());
        jlCustomerName.setFont(arial20);
        add(jlCustomerName);
        Dimension sizeCustomerName = jlCustomerName.getPreferredSize();
        jlCustomerName.setBounds(FrameHeader.getScreenWidth(26.04166667f), FrameHeader.getScreenHeight(2f), sizeCustomerName.width + 10, sizeCustomerName.height); // x = 400 pixels, y = 40 pixels

        //label aanmaken voor de custumerID, int waarde omgezet naar string en juiste lettertype, grootte en plaats meegegeven (Joëlle)
        JLabel jlCustomerID = new JLabel(", " + this.order.getCustomer().getCustomerID());
        jlCustomerID.setFont(arial20);
        add(jlCustomerID);
        Dimension sizeCustomerID = jlCustomerID.getPreferredSize();
        jlCustomerID.setBounds(FrameHeader.getScreenWidth(26.171875f) + sizeCustomerName.width , FrameHeader.getScreenHeight(2f), sizeCustomerID.width + 10, sizeCustomerID.height); // x = 402 pixels + size customerName.width, y = 40 pixels

        //label aanmaken voor de producten, int waarde omgezet naar string en juiste lettertype, grootte en plaats meegegeven (Joëlle)
        String stringProductAmount = valueOf(this.order.getProductAmount());
        JLabel jlProductAmount = new JLabel(stringProductAmount);
        jlProductAmount.setFont(arial20);
        add(jlProductAmount);
        Dimension sizeProductAmount = jlProductAmount.getPreferredSize();
        jlProductAmount.setBounds(FrameHeader.getScreenWidth(48.828125f), FrameHeader.getScreenHeight(2f), sizeProductAmount.width +15, sizeProductAmount.height); // x = 750 pixels, y = 40 pixels

        //label aanmaken voor de datum, datum converteren naar bepaald patroon en juiste lettertype, grootte en plaats meegeven (Joëlle)
        Date date = this.order.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String strDate = dateFormat.format(date);
        JLabel jlDate = new JLabel(strDate);
        jlDate.setFont(arial20);
        add(jlDate);
        Dimension sizeDate = jlDate.getPreferredSize();
        jlDate.setBounds(FrameHeader.getScreenWidth(71.61458333f), FrameHeader.getScreenHeight(2f), sizeDate.width +15, sizeDate.height); // x = 1100 pixels, y = 40 pixels
    }
}