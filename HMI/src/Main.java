import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
//        Communication comms = new Communication();
//        Communication.sendComms(200);
//
//        comms.getComms();

        List<Object> placeholders = new ArrayList<>();
        placeholders.add(37);
        placeholders.add("Daan");
        placeholders.add(2);
        placeholders.add("2023-05-01 14:37:02.000000");
        placeholders.add("2023-05-01 14:37:02.000000");
        System.out.println(Database.insertInDatabase("INSERT INTO colors (ColorID, ColorName, LastEditedBy, ValidFrom, ValidTo) VALUES (?, ?, ?, ?, ?)", placeholders));
    }
}
