import comms.Communication;
import database.Database;
import env.GetEnv;
import frames.FrameController;

public class Main {
    public static void main(String[] args) {
        new GetEnv();
        new FrameController();
        new Communication();
        new Database();

        System.out.println(Database.updateDatabase("INSERT INTO colors (ColorID, ColorName, LastEditedBy, ValidFrom, ValidTo) VALUES (?, ?, ?, ?, ?)", new String[]{"38", "Test", "2", "2023-05-01 14:37:02.000000", "2023-05-01 14:37:02.000000"}));

        System.out.println(Database.getDbData("select * from orders where CustomerID <= ? limit 10", new String[]{"10"}));
    }
}
