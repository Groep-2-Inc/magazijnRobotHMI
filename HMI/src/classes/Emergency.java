package classes;
// Handelt noodstop af
// Door Martijn

public class Emergency {
    private static boolean status;

    public static void startEmergency(){
        status = true;
        Communication.sendComms(500);
    }

    public static void stopEmergency(){
        status = false;
    }

    public static boolean isEmergency(){
        return status;
    }
}
