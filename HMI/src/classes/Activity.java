package classes;

import java.util.Date;
// (Joëlle)
public class Activity {
    private Date date; // datum
    private String activityDescription; // activiteitBeschrijving
    public Activity(Date date, String activityDescription){
        this.date = date;
        this.activityDescription = activityDescription;
    }

    public Date getDate() {
        return date;
    }

    public String getActivityDescription() {
        return activityDescription;
    }
}
