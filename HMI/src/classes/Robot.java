package classes;

public class Robot {
    private static int robotStatus;

    public static int getRobotStatus() {
        return robotStatus;
    }

    public static void setRobotStatus(int robotStatus) {
        Robot.robotStatus = robotStatus;
    }
}
