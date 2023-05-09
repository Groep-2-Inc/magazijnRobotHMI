package panels;

import classes.*;
import frames.FrameHeader;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class PanelProductOverview extends JPanel {
    private BufferedImage productImg; //Productafbeelding
    private JTextField jtf_amount; //Textfield dat aantal producten weergeeft en aanpassen mogelijk maakt
    private ArrayList<Product> products; //Arraylist waarin de producten worden opgeslagen
    private int index; //Houdt bij van welk product dit panel is (op basis van forloop uit FrameProducts)

    //Onderstaand: attributen die jtf_amount wel of niet bewerkbaar kunnen maken
    private Border setBorder = null;
    private boolean isEditable = false;
    private Color setBackground = Color.white;

    public PanelProductOverview(ArrayList<Product> products, int index) {
        this.products = products;
        this.index = index;

        //Informatie voor het hele panel (Sarah)
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1500)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 150))));
        setLayout(null);

        //Productafbeelding wordt toegevoegd, error bij ontbrekende afbeelding wordt opgevangen (Sarah)
        try {
            productImg = ImageIO.read(new File(products.get(index).getImgPath()));
        } catch (IOException ex) {
//            System.out.println(getClass() + ": no image available");
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

    //Kleuren, omlijningen, labels, buttons, etc. worden toegevoegd (Sarah)
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Achtergrondkleur geven en lijnen om panel heen zetten en productafbeelding toevoegen (Sarah)
        setBackground(Color.white);
        g.drawImage(productImg, FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 50)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 25)), null);
        g.drawRect(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 50)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 25)), FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 135)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 100)));
        g.drawLine(0, 0, FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1720)), 0);
        g.drawLine(0, FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 149)), FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1720)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 149)));

        //Productnaam opvragen en stylen (Sarah)
        JLabel jl_productname = new JLabel(products.get(index).getProductName());
        jl_productname.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeProductname = jl_productname.getPreferredSize();
        jl_productname.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 200)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 30)), sizeProductname.width + FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 10)), sizeProductname.height);
        add(jl_productname);

        //ProductID opvragen en stylen (Sarah)
        JLabel jl_productID = new JLabel(String.valueOf("Artikelnummer: " + products.get(index).getProductID()));
        jl_productID.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeProductID = jl_productID.getPreferredSize();
        jl_productID.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 200)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), sizeProductID.width + FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 10)), sizeProductID.height);
        add(jl_productID);

        //Producthoeveelheid toevoegen en stylen (Sarah)
        JLabel jl_amount = new JLabel("Hoeveelheid op voorraad: ");
        jl_amount.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeJLAmount = jl_amount.getPreferredSize();
        jl_amount.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 980)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), sizeJLAmount.width + FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 10)), sizeJLAmount.height);
        add(jl_amount);
        jtf_amount = new JTextField(String.valueOf(products.get(index).getStock()));
        jtf_amount.setBackground(setBackground);
        jtf_amount.setBorder(setBorder);
        jtf_amount.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeJTFAmount = jtf_amount.getPreferredSize();
        jtf_amount.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1220)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 60)), sizeJTFAmount.height);
        jtf_amount.setEditable(isEditable);
        add(jtf_amount);
    }
}
