package panels;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import testClasses.*;

public class PanelProduct extends JPanel implements ActionListener{
    private Order order; //Order waarvan hij de producten laat zien
    private BufferedImage productImg; //Productafbeelding
    private JTextField jtf_amount; //Textfield dat aantal producten weergeeft en aanpassen mogelijk maakt
    private int index; //Houdt bij van welk product dit panel is (op basis van forloop uit ViewingOrder)
    private JButton jb_deleteProduct; //Knop met kruisje die producten uit productlijst kan verwijderen

    //Onderstaand: attributen die jb_deleteProduct en jtf_amount wel of niet zichtbaar/bewerkbaar kunnen maken
    private Border setBorder = null;
    private boolean isEditable, isDeleteVisible = false;
    private Color setBackground = Color.white;

    public PanelProduct(Order order, int index) {
        this.index = index;
        this.order = order;

        //Informatie voor het hele panel (Sarah)
        setPreferredSize(new Dimension(1320, 150));
        setLayout(null);

        //Productafbeelding wordt toegevoegd, error bij ontbrekende afbeelding wordt opgevangen (Sarah)
        try {
            productImg = ImageIO.read(new File(this.order.getProducts().get(index).getImgPath()));
        } catch (IOException ex) {
            System.out.println(getClass() + ": no image available");
        }
    }

    //Getter om bij jtf_amount te kunnen vanuit ViewingOrder (Sarah)
    public JTextField getJtf_amount() {
        return jtf_amount;
    }

    //Method om jtf_amount wel of niet bewerkbaar te maken vanuit ViewingOrder (Sarah)
    public void editAmount(Color background, Border border, boolean editable) {
        this.setBackground = background;
        this.setBorder = border;
        this.isEditable = editable;
        repaint();
    }

    //Method om jb_deleteProduct wel of niet zichtbaar te maken vanuit ViewingOrder (Sarah)
    public void editDelete(boolean deleteVisible) {
        this.isDeleteVisible = deleteVisible;
    }

    //Kleuren, omlijningen, labels, buttons, etc. worden toegevoegd (Sarah)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Achtergrondkleur geven en lijnen om panel heen zetten (Sarah)
        setBackground(Color.white);
        g.drawImage(productImg, 50, 25, null);
        g.drawRect(50, 25, 135, 100);
        g.drawLine(0, 0, 1720, 0);
        g.drawLine(0, 149, 1720, 149);

        //Productnaam opvragen en stylen (Sarah)
        JLabel jl_productname = new JLabel(this.order.getProducts().get(index).getProductName());
        jl_productname.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeProductname = jl_productname.getPreferredSize();
        jl_productname.setBounds(200, 30, sizeProductname.width + 10, sizeProductname.height);
        add(jl_productname);

        //ProductID opvragen en stylen (Sarah)
        JLabel jl_productID = new JLabel(String.valueOf(this.order.getProducts().get(index).getProductID()));
        jl_productID.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeProductID = jl_productID.getPreferredSize();
        jl_productID.setBounds(200, 60, sizeProductID.width + 10, sizeProductID.height);
        add(jl_productID);

        //Producthoeveelheid toevoegen en stylen (Sarah)
        JLabel jl_amount = new JLabel("Hoeveelheid besteld: ");
        jl_amount.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeJLAmount = jl_amount.getPreferredSize();
        jl_amount.setBounds(1000, 60, sizeJLAmount.width + 10, sizeJLAmount.height);
        add(jl_amount);

        jtf_amount = new JTextField(String.valueOf(order.getProducts().get(index).getAmountOrdered()));
        jtf_amount.setBackground(setBackground);
        jtf_amount.setBorder(setBorder);
        jtf_amount.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeJTFAmount = jtf_amount.getPreferredSize();
        jtf_amount.setBounds(1220, 60, 60, sizeJTFAmount.height);
        jtf_amount.setEditable(isEditable);
        add(jtf_amount);

        //Kruisje om product te verwijderen toevoegen en stylen (Sarah)
        ImageIcon deleteIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../images/deleteIcon.png")));
        jb_deleteProduct = new JButton(deleteIcon);
        jb_deleteProduct.setBounds(5, 15, 30, 30);
        jb_deleteProduct.setOpaque(false);
        jb_deleteProduct.setContentAreaFilled(false);
        jb_deleteProduct.setBorderPainted(false);
        jb_deleteProduct.setVisible(isDeleteVisible);
        jb_deleteProduct.addActionListener(this);
        add(jb_deleteProduct);
    }

    //TODO knop om producten uit productlijst te verwijderen moet nog werkend gemaakt worden
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_deleteProduct) {

        }
    }
}
