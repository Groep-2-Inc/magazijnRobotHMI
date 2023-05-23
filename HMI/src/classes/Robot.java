package classes;

public class Robot {
    private static int robotStatus;

    public static int getRobotStatus() {
        return robotStatus;
    }

    public static void setRobotStatus(int robotStatus) {
        // Als de status van de robot 500 is
        if(robotStatus == 500){
            // Activeert de emergency
            Emergency.startEmergency();
        }else{
            // Anders zet de juiste status
            Robot.robotStatus = robotStatus;
        }
    }
}
