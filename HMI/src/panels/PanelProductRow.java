package panels;

import classes.*;
import database.Database;
import frames.FrameHeader;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class PanelProductRow extends JPanel implements ActionListener {
    public JButton jb_change, jb_save, jb_cancel; //Buttons die gebruikt worden in het scherm
    private BufferedImage productImg; //Productafbeelding
    private JTextField jtf_amount; //Textfield dat aantal producten weergeeft en aanpassen mogelijk maakt
    private Product product; //Arraylist waarin de producten worden opgeslagen

    //Onderstaand: attributen die jtf_amount wel of niet bewerkbaar kunnen maken
    private Border setBorder = null;
    private boolean isEditable = false;
    private boolean changeVisible = true;
    private boolean cancelSaveVisible = false;
    private boolean invalidVisible = false;
    private Color setBackground = Color.white;
    private JLabel jl_invalid = new JLabel("Ongeldige waarde");

    public PanelProductRow(Product product) {
        this.product = product;

        //Informatie voor het hele panel (Sarah)
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1500)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 150))));
        setLayout(null);

        //Productafbeelding wordt toegevoegd, error bij ontbrekende afbeelding wordt opgevangen (Sarah)
        try {
            String imgPath = this.product.getImgPath();
            productImg = ImageIO.read(new File("src/images/products/" + imgPath));
        } catch (IOException ex) {
            System.out.println(getClass() + " " + product.getImgPath() + ": no image available");
        }

        jb_change = new JButton("Bewerken");
        jb_save = new JButton("Opslaan");
        jb_cancel = new JButton("Annuleren");
        jb_change.addActionListener(this);
        jb_save.addActionListener(this);
        jb_cancel.addActionListener(this);

        setVisible(true);
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Achtergrondkleur geven en lijnen om panel heen zetten en productafbeelding toevoegen (Sarah)
        setBackground(Color.white);
        g.drawImage(productImg, FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 50)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 25)), FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 135)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 100)), null);
        g.drawRect(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 50)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 25)), FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 135)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 100)));
        g.drawLine(0, 0, FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1720)), 0);
        g.drawLine(0, FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 149)), FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1720)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 149)));

        //Productnaam opvragen en stylen (Sarah)
        JLabel jl_productname = new JLabel(product.getProductName());
        jl_productname.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeProductname = jl_productname.getPreferredSize();
        jl_productname.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 200)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 30)), sizeProductname.width + FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 10)), sizeProductname.height);
        add(jl_productname);

        //ProductID opvragen en stylen (Sarah)
        JLabel jl_productID = new JLabel("Artikelnummer: " + product.getProductID());
        jl_productID.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeProductID = jl_productID.getPreferredSize();
        jl_productID.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 200)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), sizeProductID.width + FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 10)), sizeProductID.height);
        add(jl_productID);

        //Producthoeveelheid toevoegen en stylen (Sarah)
        JLabel jl_amount = new JLabel("Voorraad: ");
        jl_amount.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeJLAmount = jl_amount.getPreferredSize();
        jl_amount.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1121)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), sizeJLAmount.width + FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 10)), sizeJLAmount.height);
        add(jl_amount);
        jtf_amount = new JTextField(String.valueOf(product.getStock()));
        jtf_amount.setBackground(setBackground);
        jtf_amount.setBorder(setBorder);
        jtf_amount.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension sizeJTFAmount = jtf_amount.getPreferredSize();
        jtf_amount.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1210)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 60)), sizeJTFAmount.height);
        jtf_amount.setEditable(isEditable);
        add(jtf_amount);

        //Button om productlijst te bewerken aanmaken en stylen (zichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        Dimension sizeProductsEdit = jb_change.getPreferredSize();
        jb_change.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1280)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), sizeProductsEdit.width +10, sizeProductsEdit.height);
        jb_change.setVisible(changeVisible);
        add(jb_change);

        //Button om aanpassingen in productlijst op te slaan aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        Dimension sizeProductsEditSave = jb_save.getPreferredSize();
        jb_save.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1280)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), sizeProductsEditSave.width +10, sizeProductsEditSave.height);
        jb_save.setVisible(cancelSaveVisible);
        add(jb_save);

        //Button om aanpassingen in productlijst te annuleren aanmaken en stylen (onzichtbaar totdat op "bewerken" wordt gedrukt) (Sarah)
        Dimension sizeProductsEditCancel = jb_cancel.getPreferredSize();
        jb_cancel.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1360)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 60)), sizeProductsEditCancel.width +10, sizeProductsEditCancel.height);
        jb_cancel.setVisible(cancelSaveVisible);
        add(jb_cancel);

        jl_invalid.setFont(new Font("Arial", Font.BOLD, 10));
        jl_invalid.setForeground(Color.red);
        Dimension sizeInvalid = jl_invalid.getPreferredSize();
        jl_invalid.setBounds(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1536, 1370)), FrameHeader.getScreenHeight(FrameHeader.getPercentage(864, 65)), sizeInvalid.width +20, sizeInvalid.height);
        jl_invalid.setVisible(invalidVisible);
        add(jl_invalid);
    }

    public void actionPerformed(ActionEvent e) {
        //Als op "bewerken" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_change) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            changeVisible = false;
            cancelSaveVisible = true;
            invalidVisible = false;

            removeAll();
            editAmount(Color.lightGray, BorderFactory.createLineBorder(Color.BLUE, 1), true);
        }

        //Als op "Opslaan" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_save) {
            // Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            changeVisible = true;
            cancelSaveVisible = false;

            // Instellen dat aantal producten niet meer bewerkt kan worden (wijzigingen worden opgeslagen) (Sarah)
            // Aanpassingen aan aantal producten worden opgeslagen, errors worden afgevangen (Sarah), try en catch samengevoegd naar één (Joëlle)
            try {
                if(Integer.parseInt(getJtf_amount().getText()) >= 0){
                    Database.updateDatabase("UPDATE stockitemholdings SET QuantityOnHand = ? WHERE StockItemID = ?", new String[]{getJtf_amount().getText(), String.valueOf(product.getProductID())});
                    product.setStock(Integer.parseInt(getJtf_amount().getText()));
                } else{
                    invalidVisible = true;
                }
            } catch (NumberFormatException | NullPointerException ex) {
                //Foutmelding als er geen nummer wordt ingevoerd (Sarah)
                 if(getJtf_amount() != null){
                     invalidVisible = true;
                 }
            }

            Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "1", "Hoeveelheid product met nummer " + product.getProductID() + " is aangepast"});
            removeAll();
            editAmount(Color.white, null, false);
        }

        //Als op "Annuleren" wordt gedrukt: (Sarah)
        if (e.getSource() == jb_cancel) {
            //Andere knoppen worden op zichtbaar of onzichtbaar gezet (Sarah)
            changeVisible = true;
            cancelSaveVisible = false;

            //Instellen dat aantal producten niet meer bewerkt kan worden (wijzigingen worden niet opgeslagen) (Sarah)
            removeAll();
            editAmount(Color.white, null, false);
        }
    }
}
