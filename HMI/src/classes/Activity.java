package classes;

import java.util.Date;
// (Joëlle)
public class Activity {
    private int id;
    private String typeText;
    private Date date; // datum
    private String activityDescription; // activiteitBeschrijving
    public Activity(int id, int typeNumber, Date date, String activityDescription){
        this.id = id;
        this.date = date;
        this.activityDescription = activityDescription;
        if(typeNumber  == 1){
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

    public Date getDate() {
        return date;
    }

    public String getActivityDescription() {
        return activityDescription;
    }
}
