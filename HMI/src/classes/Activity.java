package classes;
// Door Joëlle
// Bijgewerkt door Martijn

import database.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import panels.PanelLogbook;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class Activity {
    private int id;
    private String typeText;
    private String date; // datum
    private String activityDescription; // activiteitBeschrijving
    private static ArrayList<Activity> activities = new ArrayList<>(); // arraylist met alle activities

    public Activity(int id, int typeNumber, String date, String activityDescription){
        this.id = id;
        this.date = date;
        this.activityDescription = activityDescription;
        if(typeNumber == 1){
            this.typeText = "HMI";
        }else{
            this.typeText = "Robot";
        }
    }

    public int getId() {
        return id;
    }
    public String getTypeText() {
        return typeText;
    }

    public String getDate() {
        return date;
    }
    public String getActivityDescription() {
        return activityDescription;
    }

    // Haalt data uit db
    // Eerste versie door Joëlle
    // Verplaats door Martijn
    public static void getLogbookData(int limit){
        activities.clear();

        // Haalt alle data op en zet deze in de array (Joëlle)
        JSONArray allActivities;
        allActivities = Database.getDbData("SELECT id, type, text, date FROM logbook ORDER BY id DESC LIMIT ?", new String[]{String.valueOf(limit)});

        for (Object singelLogbookData : allActivities) {
            // Zet het Object om naar een JSON-object (Joëlle)
            JSONObject logbookData = (JSONObject) singelLogbookData;

            // Maak een nieuwe default date aan (Joëlle)
            Date logbookDate;
            String formattedDate = null;
            try {
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logbookDate = sdf.parse(String.valueOf(logbookData.get("date")));

                // Format the date as "dd-MM-yyyy HH:mm"
                DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                formattedDate = outputFormat.format(logbookDate);
            } catch (ParseException pe) {
                System.out.println(PanelLogbook.class + "Framelogbook(): " + pe);
            }

            // Maak een nieuwe Activiteit aan met data uit het logboek (Joëlle)
            Activity activity = new Activity(Integer.parseInt((String) logbookData.get("id")), Integer.parseInt((String) logbookData.get("type")), String.valueOf(formattedDate), String.valueOf(logbookData.get("text")));

            //voeg de activiteit toe aan de activiteiten ArrayList (Joëlle)
            activities.add(activity);
        }
    }

    // Returnt alle activities
    public static ArrayList<Activity> getActivities() {
        return activities;
    }
}
