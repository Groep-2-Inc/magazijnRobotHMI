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
        setPreferredSize(new Dimension(1500, 100)); // de grootte van het panel instellen
        setBackground(Color.white); // de kleur wit instellen
        setLayout(null); // geen layout instellen, voor het handmatig instellen voor de locaties


        String stringOrderID = valueOf(this.order.getOrderID()); // de int orderID omzetten naar string
        JLabel jlOrderID = new JLabel(stringOrderID); // label aanmaken met de net aangemaakte string
        jlOrderID.setFont(new Font("Arial", Font.PLAIN, 20)); // lettertype font 20 instellen
        add(jlOrderID); // toevoegen label aan panel
        Dimension sizeOrderID = jlOrderID.getPreferredSize(); // de grootte van het label bepalen
        jlOrderID.setBounds(50, 40, sizeOrderID.width, sizeOrderID.height); // de locatie van het label instellen, gebaseerd op de grootte van de tekst in het label

        JLabel jlCustomerName = new JLabel(this.order.getCustomer().getCustomername()); // label aanmaken met de klantnaam
        jlCustomerName.setFont(new Font("Arial", Font.PLAIN, 20)); // lettertype instellen op font 20
        add(jlCustomerName); // toevoegn label aan panel
        Dimension sizeCustomerName = jlCustomerName.getPreferredSize(); // de grootte van het label bepalen
        jlCustomerName.setBounds(400, 40, sizeCustomerName.width + 10, sizeCustomerName.height); // de locatie van het label instellen, gebaseerd op de grootte van de tekst in het label

        JLabel jlCustomerID = new JLabel(", " + this.order.getCustomer().getCustomerID()); // label toevoegn voor het klant ID
        jlCustomerID.setFont(new Font("Arial", Font.PLAIN, 20)); // instel0en van lettertype font 29
        add(jlCustomerID); // toevoegen label aan panel
        Dimension sizeCustomerID = jlCustomerID.getPreferredSize(); // de grootte van het label bepalen
        jlCustomerID.setBounds(400 + sizeCustomerName.width + 2, 40, sizeCustomerID.width + 10, sizeCustomerID.height); // de locatie van het label instellen, gebaseerd op de grootte van de tekst in het label


        String stringProductAmount = valueOf(this.order.getProductAmount()); // de int aantal producten omzetten naar string
        JLabel jlProductAmount = new JLabel(stringProductAmount); // label toevoegen met de net omgezette string
        jlProductAmount.setFont(new Font("Arial", Font.PLAIN, 20)); // lettertype op font 20 zetten
        add(jlProductAmount); // toevoegen label aan panel
        Dimension sizeProductAmount = jlProductAmount.getPreferredSize(); // de grootte van het label bepalen
        jlProductAmount.setBounds(750, 40, sizeProductAmount.width, sizeProductAmount.height); // de locatie van het label instellen, gebaseerd op de grootte van de tekst in het label

        Date date = this.order.getDate(); // variable datum omzetten
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm"); // datumformat instellen met patronen
        String strDate = dateFormat.format(date); // datum omvormen naar juist formaat
        JLabel jlDate = new JLabel(strDate); // label aanmaken met de string datum
        jlDate.setFont(new Font("Arial", Font.PLAIN, 20)); // lettertype op font 20 zetten
        add(jlDate); // toevoegen label aan panel
        Dimension sizeDate = jlDate.getPreferredSize(); // de grootte van het label bepalen
        jlDate.setBounds(1100, 40, sizeDate.width, sizeDate.height); // de locatie van het label instellen, gebaseerd op de grootte van de tekst in het label
    }
}
