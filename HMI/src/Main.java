import TSP.TSP_main;
import comms.Communication;
import database.Database;
import env.GetEnv;
import frames.FrameController;

public class Main {
    public static void main(String[] args){
//        new GetEnv();
//        new Database();
//        new FrameController();
//        new Communication();
//
//
//        Communication.sendComms(434);

        String[] matrix = new String[5];
        matrix[0] = "A1";
        matrix[1] = "B5";
        matrix[2] = "A3";
        matrix[3] = "A4";
        matrix[4] = "A6";

        for (int i:TSP_main.main(matrix)) {
            System.out.println(i);
        }

//        System.out.println(Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "0", "Heeft op Go gedrukt!"}));

//        System.out.println(Database.getDbData("select * from orders where CustomerID <= ? limit 10", new String[]{"10"}));
    }
}
