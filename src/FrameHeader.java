import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class FrameHeader extends JFrame implements ActionListener, ItemListener{
    private JMenuBar jmb_main = new JMenuBar(); //menubar voor alle menu knoppen
    private JButton jb_home = new JButton("Home"); //button om naar het hoofdscherm te gaan
    private JButton jb_orders = new JButton("Orders"); //button om naar het order scherm te gaan
    private JButton jb_producten = new JButton("Producten"); //button om naar het scherm naar producten te gaan
    private JButton jb_settings; //button om de settings te openen
    private JButton jb_noodstop; //button voor de noodstop
    private JButton jb_logboek = new JButton("Logboek"); // nieuwe logboek knop
    private Popup p_settings; //popup voor de settings
    private boolean b_isShowingSettings = false; //bool om te kijken of de settings aan het showen zijn
    private PopupFactory pf = new PopupFactory(); //popupfactory om de popup werkend te krijgen
    private JPanel p2 = new JPanel(); //paneel voor in de popup
    private JToggleButton jtb_darkMode; //button voor het toggelen van darmode en lightmode
    public boolean b_darkMode = false; //boolean om de darkmode bij te houden
    private ImageIcon ii_switchOff = new ImageIcon(getClass().getResource("switch off.png")); //imageicon van de switch uit
    private ImageIcon ii_switchOn = new ImageIcon(getClass().getResource("switch on.png")); //imageicon van de switch aan

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
        jb_settings.addActionListener(this);


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

        //initializeer de darkmode button
        jtb_darkMode = new JToggleButton(ii_switchOff);
        jtb_darkMode.setMargin(new Insets(0, 0, 0, 0));
        jtb_darkMode.setBackground(Color.WHITE);
        jtb_darkMode.setBorder(new LineBorder(Color.white));
        jtb_darkMode.addItemListener(this);

        //initializeer het paneel.
        p2.setPreferredSize(new Dimension(200, 200));
        p2.setBorder(new LineBorder(Color.BLACK));
        p2.add(new JLabel("          Instellingen          "));
        p2.add(new JLabel("                                "));
        p2.add(new JLabel("Light/Dark mode"));
        //voeg de buttons toe
        p2.add(jtb_darkMode);
        jb_logboek.setPreferredSize(new Dimension(150, 40));
        p2.add(jb_logboek);
        p2.setBackground(Color.white);
        //maak de popup
        p_settings = pf.getPopup(this, p2, 1800, 60);
    }

    public void actionPerformed(ActionEvent e) {

        //open de settings popup
        if(e.getSource() == jb_settings){
            if(!b_isShowingSettings){
                p_settings = pf.getPopup(this, p2, 1700, 80);
                p_settings.show();
                b_isShowingSettings = true;
            } else{
                p_settings.hide();
                b_isShowingSettings = false;
            }
        }
    }
    public void itemStateChanged(ItemEvent eve) {
        //switch van dark/light mode
        if (jtb_darkMode.isSelected()){
            jtb_darkMode.setIcon(ii_switchOn);
            b_darkMode = true;
        } else {
            b_darkMode = false;
            jtb_darkMode.setIcon(ii_switchOff);
        }
    }
}
