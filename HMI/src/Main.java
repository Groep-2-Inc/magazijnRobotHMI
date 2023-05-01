import java.io.IOException;
import java.sql.SQLException;

import comms.Communication;
import database.Database;
import env.GetEnv;
import frames.FrameController;

import javax.xml.crypto.Data;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        new GetEnv();
        new Database();
//        new FrameController();
        
//         Start de Serial communication
        Communication comms = new Communication();
        Communication.sendComms(200);

        comms.getComms();

        System.out.println(Database.insertInDatabase("INSERT INTO colors (ColorID, ColorName, LastEditedBy, ValidFrom, ValidTo) VALUES ('37', 'Daan', '2', '2023-05-01 14:37:02.000000', '2023-05-01 14:37:02.000000')"));
    }
}
