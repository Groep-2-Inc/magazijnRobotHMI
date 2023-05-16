package frames;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import database.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import classes.*;

public class FrameLogbook extends FrameHeader {
    private ArrayList<Activity> activities; // lijst met activeiten (Joëlle)
    private JLabel jlJournal =new JLabel("Logboek");
    private JScrollPane scrollPane;
    private JPanel panel;


    public FrameLogbook() {
        closeProgram();
        setTitle("HMI-applicatie"); // moet nog een betere titel komen lijkt mij (Joëlle)
        Activity.getLogbookData(1000);

        //Panel toevoegen voor de titel (Joëlle)
        JPanel panelJournalTitle = new JPanel();
        panelJournalTitle.setLayout(null);
        panelJournalTitle.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(5f))); //procenten toegevoegd (Joëlle)

        //Label toevoegen aan het panel en juiste plek en grootte toekennen (Joëlle)
        jlJournal.setFont(new Font("Arial", Font.BOLD, 30));
        panelJournalTitle.add(jlJournal);
        Dimension sizeJournalText = jlJournal.getPreferredSize();
        jlJournal.setBounds(getScreenWidth(0f), getScreenHeight(1.157407407f), sizeJournalText.width +10, sizeJournalText.height);

        super.add(panelJournalTitle);

        //Panel aanmaken voor waar de scrollpane inkomt (Joëlle)
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(getScreenWidth(98f), 46 * Activity.getActivities().size())); //procenten toegevoegd, bij height mist nog, kan later evt. toegevoegd worden(Joëlle)

        //Een for-loop, doorloopt de activiteiten en print de datum en beschrijving (Joëlle)
        for (int i = 0; i < Activity.getActivities().size(); i++) {
            JPanel panelLogging = new JPanel();
            panelLogging.setLayout(new GridLayout(1, 1));
            panelLogging.setPreferredSize(new Dimension(getScreenWidth(98f), 40));
            panelLogging.setBorder(new LineBorder(Color.BLACK));

            panelLogging.add(new JLabel(Activity.getActivities().get(i).getDate() + " #:" + Activity.getActivities().get(i).getId() + " type: " + Activity.getActivities().get(i).getTypeText() + " - " + Activity.getActivities().get(i).getActivityDescription()));
            panel.add(panelLogging);
        }

        //Aanmaken scrollpane, met vertical scrollbar plus juiste grootte meegegeven (Joëlle)
        scrollPane = new JScrollPane(panel); // aanmaken scrollPane
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(70f))); //procenten toegevoegd (Joëlle)

        super.add(scrollPane); // scrollpane toevoegen aan de hoofdpagina (Joëlle)
    }
}
