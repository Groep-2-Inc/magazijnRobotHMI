import classes.Communication;
import database.Database;
import env.GetEnv;
import frames.FrameController;

public class Main {
    public static void main(String[] args){
        new GetEnv();
        new Database();
        new FrameController();
        new Communication();
        

//        Communication.sendComms(434);

//        System.out.println(Database.updateDatabase("INSERT INTO logbook (type, text) VALUES (?, ?)", new String[]{ "0", "Heeft op Go gedrukt!"}));

//        System.out.println(Database.getDbData("select * from orders where CustomerID <= ? limit 10", new String[]{"10"}));
    }
}
