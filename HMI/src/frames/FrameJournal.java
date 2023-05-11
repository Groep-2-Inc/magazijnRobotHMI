package frames;

import java.awt.*;
import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import database.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import classes.*;

public class FrameJournal extends FrameHeader {
    private ArrayList<Activity> activities; // lijst met activeiten (Joëlle)
    private JLabel jl_journal =new JLabel("Logboek");

    public FrameJournal() {
        getLogbookData();
//        this.activities = activities;
        closeProgram();
        setTitle("Java-application/Logboek"); // moet nog een betere titel komen lijkt mij (Joëlle)

        //Panel toevoegen voor de titel (Joëlle)
        JPanel panelJournalTitle = new JPanel();
        panelJournalTitle.setLayout(null);
        panelJournalTitle.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(5f))); //procenten toegevoegd (Joëlle)

        //Label toevoegen aan het panel en juiste plek en grootte toekennen (Joëlle)
        jl_journal.setFont(new Font("Arial", Font.BOLD, 30));
        panelJournalTitle.add(jl_journal);
        Dimension sizeJournalText = jl_journal.getPreferredSize();
        jl_journal.setBounds(getScreenWidth(0f), getScreenHeight(1.157407407f), sizeJournalText.width +10, sizeJournalText.height);

        super.add(panelJournalTitle);

        //Panel aanmaken voor waar de scrollpane inkomt (Joëlle)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(getScreenWidth(98f), 16 * activities.size())); //procenten toegevoegd, bij height mist nog, kan later evt. toegevoegd worden(Joëlle)

        //Een for-loop, doorloopt de activiteiten en print de datum en beschrijving (Joëlle)
        for (int i = 0; i < activities.size(); i++) {
            JLabel label = new JLabel();
            Date date = activities.get(i).getDate(); // converteren van de datum naar string
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            String strDate = dateFormat.format(date);

            label.setText(strDate + " ID:" + activities.get(i).getId() + "type: " + activities.get(i).getTypeText() + " - " + activities.get(i).getActivityDescription());
            panel.add(label);
        }

        //Aanmaken scrollpane, met vertical scrollbar plus juiste grootte meegegeven (Joëlle)
        JScrollPane scrollPane = new JScrollPane(panel); // aanmaken scrollPane
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.setPreferredSize(new Dimension(getScreenWidth(98f), getScreenHeight(70f))); //procenten toegevoegd (Joëlle)

        super.add(scrollPane); // scrollpane toevoegen aan de hoofdpagina (Joëlle)
    }

    private void getLogbookData() {
        JSONArray allActivities;
//        activities.clear();

        // Maakt de basis query, wat bij elke query hetzelfde is (Joëlle)
        String baseQuery = "SELECT logbook.id, logbook.type, logbook.text, logbook.date FROM logbook ORDER BY id desc \n";

        // Haalt alle data op en zet deze in de array (Joëlle)
        allActivities = Database.getDbData(baseQuery, new String[]{});

        for(Object singelLogbookData: allActivities){
            // Zet het Object om naar een JSON-object
            JSONObject logbookData = (JSONObject) singelLogbookData;

            // Maak een nieuwe default date aan
            Date logbookDate = new Date(2013, 1, 1);
            try{
                // Probeer een nieuwe date format aan te maken
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // Zet de String uit de order om naar een Date object
                logbookDate = sdf.parse(String.valueOf(logbookData.get("date")));
            }catch (ParseException pe){
                System.out.println(getClass() + "Framelogbook(): " + pe);
            }

            // Maak een nieuwe Activiteit aan met data uit het logbook
            Activity activity = new Activity(Integer.parseInt((String) logbookData.get("id")), Integer.parseInt((String) logbookData.get("type")), logbookDate, String.valueOf(logbookData.get("text")));


        //voeg de activiteit toe aan de activiteiten ArrayList
        activities.add(activity);
    }
}

}
