import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class FrameHeader extends JFrame implements ActionListener{
    private JMenuBar jmb_main = new JMenuBar(); //menubarr voor alle menu knoppen
    private JButton jb_home = new JButton("Home"); //button om naar het hoofdscherm te gaan
    private JButton jb_orders = new JButton("Orders"); //button om naar het order scherm te gaan
    private JButton jb_producten = new JButton("Producten"); //button om naar het scherm naar producten te gaan
    private JButton jb_settings; //button om de settings te openen
    private JButton jb_noodstop; //button voor de noodstop



    public FrameHeader(){

        //de uiterlijk van het paneel regelen
        setTitle("JavaAplication/Home");
        setSize(1920, 1080);
        setLayout(new FlowLayout());


        //voeg de knoppen toe met actionlisteners aan het menu
        jb_home.addActionListener(this);
        jmb_main.add(jb_home);
        jb_orders.addActionListener(this);
        jmb_main.add(jb_orders);
        jb_producten.addActionListener(this);
        jmb_main.add(jb_producten);

        //zorg ervoor dat in plaats van links nu alles rechts wordt toegevoegd
        jmb_main.add(Box.createHorizontalGlue());

        //haal de immages op, maak knoppen met deze immages en zet de marigin op 0;
        ImageIcon settings = new ImageIcon(getClass().getResource("settings2.png"));
        jb_settings = new JButton(settings);
        jb_settings.setMargin(new Insets(0, 0, 0, 0));

        ImageIcon ii_noodstop = new ImageIcon(getClass().getResource("noodstop2.png"));
        jb_noodstop = new JButton(ii_noodstop);
        jb_noodstop.setMargin(new Insets(0, 0, 0, 0));

        //voeg deze uiteindelijk toe aan de menubalk
        jmb_main.add(jb_settings);
        jmb_main.add(jb_noodstop);

        //voeg de menubalk toe aan het scherm
        setJMenuBar(jmb_main);

        //zet de default close operation en laat het scherm zien.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //hieronder de code voor als de knoppen ingedrukt worden.
        if(e.getSource() == jb_home){
            System.out.println("test");
        }
    }
}