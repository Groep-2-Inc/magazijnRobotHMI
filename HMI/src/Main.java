import frames.FrameController;
import classes.Communication;
import classes.Database;
import classes.GetEnv;

public class Main {
    public static void main(String[] args) {
        new GetEnv();
        new Database();
        new FrameController();
        new Communication();
































//        Thread.sleep(8000);
//
//        Communication.sendComms(434);
//
//        String[] matrix = new String[5];
//        matrix[0] = "A1";
//        matrix[1] = "B5";
//        matrix[2] = "A3";
//        matrix[3] = "A4";
//        matrix[4] = "A6";
//

//        for (int i : TSP_main.main(matrix)) {
//            System.out.println(i);
//        }
    }
}
