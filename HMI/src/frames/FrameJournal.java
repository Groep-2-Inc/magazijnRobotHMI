package frames;

import java.awt.*;
import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import classes.*;

public class FrameJournal extends FrameHeader {
    private ArrayList<Activity> activities; // lijst met activeiten (Joëlle)
    private JLabel jl_journal =new JLabel("Logboek");


    public FrameJournal(ArrayList<Activity> activities) {
        this.activities = activities;
        closeProgram();

        //Panel toevoegen voor de titel (Joëlle)
        JPanel panelJournalTitle = new JPanel();
        panelJournalTitle.setLayout(null);
        panelJournalTitle.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(5f))); //procenten toegevoegd (Joëlle)
//        panelJournalTitle.setBackground(Color.blue); // voor het debuggen

        //Label toevoegen aan het panel en juiste plek en grootte toekennen (Joëlle)
        jl_journal.setFont(new Font("Arial", Font.PLAIN, 20));
        panelJournalTitle.add(jl_journal);
        Dimension sizeJournalText = jl_journal.getPreferredSize();
        jl_journal.setBounds(getScreenWidth(0f), getScreenHeight(1.157407407f), sizeJournalText.width +10, sizeJournalText.height);

        super.add(panelJournalTitle);

        //Panel aanmaken voor waar de scrollpane inkomt (Joëlle)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(getScreenWidth(98f), 16 * activities.size())); //procenten toegevoegd, bij height mist nog, kan later evt. toegevoegd worden(Joëlle)


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Java-application/Journal"); // moet nog een betere titel komen lijkt mij (Joëlle)

        //Een for-loop, doorloopt de activiteiten en print de datum en beschrijving (Joëlle)
        for (int i = 0; i < activities.size(); i++) {
            JLabel label = new JLabel();
            Date date = activities.get(i).getDate(); // converteren van de datum naar string
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            String strDate = dateFormat.format(date);

            label.setText(strDate + " " + activities.get(i).getActivityDescription());
            panel.add(label);
        }


        //Aanmaken scrollpane, met vertical scrollbar plus juiste grootte meegegeven (Joëlle)
        JScrollPane scrollPane = new JScrollPane(panel); // aanmaken scrollPane
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        getScreenWidth(78.125f), getScreenHeight(4.6296296269f))
        scrollPane.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(70f))); //procenten toegevoegd (Joëlle)

        super.add(scrollPane); // scrollpane toevoegen aan de hoofdpagina (Joëlle)

    }
}
