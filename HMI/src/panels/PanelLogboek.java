package panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import classes.Activity;
import database.Database;
import frames.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//door Jason Joshua van der Kolk
public class PanelLogboek extends JPanel{
    private ArrayList<Activity> activities = new ArrayList<>(); // lijst met activeiten (Joëlle)
    private Font arial14B = new Font("Arial", Font.BOLD, 14);
    private void getLogbookData() {
        JSONArray allActivities;
        activities.clear();


        // Haalt alle data op en zet deze in de array (Joëlle)
        allActivities = Database.getDbData("SELECT id, type, text, date FROM logbook ORDER BY id DESC LIMIT 10", new String[]{});

        for (Object singelLogbookData : allActivities) {
            // Zet het Object om naar een JSON-object (Joëlle)
            JSONObject logbookData = (JSONObject) singelLogbookData;

            // Maak een nieuwe default date aan (Joëlle)
            Date logbookDate = new Date(2013, 1, 1);
            try {
                // Probeer een nieuwe date format aan te maken (Joëlle)
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // Zet de String uit de order om naar een Date object (Joëlle)
                logbookDate = sdf.parse(String.valueOf(logbookData.get("date")));
            } catch (ParseException pe) {
                System.out.println(getClass() + "Framelogbook(): " + pe);
            }

            // Maak een nieuwe Activiteit aan met data uit het logboek (Joëlle)
            Activity activity = new Activity(Integer.parseInt((String) logbookData.get("id")), Integer.parseInt((String) logbookData.get("type")), logbookDate, String.valueOf(logbookData.get("text")));

            //voeg de activiteit toe aan de activiteiten ArrayList (Joëlle)
            activities.add(activity);
        }
    }
    public PanelLogboek(){
        getLogbookData();
        //initializeer het hoofd paneel
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(FrameHeader.getScreenWidth(50f),FrameHeader.getScreenHeight(50f)));
        setBackground(new Color(236, 236, 236));
        setBorder(new LineBorder(Color.black, 1));

        //voeg nieuw paneel toe voor de titel
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(1080, 40))));

        p3.setBorder(new LineBorder(Color.BLACK));
        p3.add(new Label("Logboek"));
        p3.setBackground(Color.lightGray);
        p3.setFont(arial14B);
        add(p3);

        //nieuw paneel voor alle informatie
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 46 * activities.size())); //hoogte geschaald met hoeveelheid(Joëlle)

        //voegt panels toen, met de gegevens van de database daarin (Joëlle)
        for(int i = 0; i<10; i++){
            JPanel p2 = new JPanel();
            p2.setLayout(new GridLayout(1, 1));
            p2.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)), 40));
            p2.setBorder(new LineBorder(Color.BLACK));

            Date date = activities.get(i).getDate(); // converteren van de datum naar string
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            String strDate = dateFormat.format(date);

            p2.add(new JLabel(strDate + " ID:" + activities.get(i).getId() + " type: " + activities.get(i).getTypeText() + " - " + activities.get(i).getActivityDescription()));
            p.add(p2);
        }

        //maar het scrollpane en voeg uiteindelijk het scrollpane toe.
        JScrollPane s = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        s.getVerticalScrollBar().setUnitIncrement(14);
        s.setPreferredSize(new Dimension(FrameHeader.getScreenWidth(FrameHeader.getPercentage(1920, 896)),FrameHeader.getScreenHeight(FrameHeader.getPercentage(1080, 358))));
        add(s);
    }
}
