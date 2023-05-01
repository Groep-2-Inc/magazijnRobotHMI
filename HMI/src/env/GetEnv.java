package env;
// Door Martijn

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class GetEnv {
    private static String arduinoCommsPort;
    private static String db_host;
    private static String db_port;
    private static String db_username;
    private static String db_password;

    // Leest een env bestand uit
    public GetEnv() {
        // Probeert
        try{
            // Een json bestand uit te lezen dat in de root staat
            JSONObject json = (JSONObject) new JSONParser().parse(new FileReader("env.json"));
            // Haalt dingen voor de database uit het geneste json object
            JSONObject database = (JSONObject) json.get("database");

            // Leest JSON-waardes uit en zet hem om naar Strings
            arduinoCommsPort = (String) json.get("arduinoCommsPort");
            db_host = (String) database.get("host");
            db_port = (String) database.get("port");
            db_username = (String) database.get("username");
            db_password = (String) database.get("password");
        }catch (IOException | ParseException exp){
            System.out.println(getClass() + ": getEnv error " + exp);
        }
    }

    public static String getArduinoCommsPort() {
        return arduinoCommsPort;
    }

    public static String getDb_host() {
        return db_host;
    }

    public static String getDb_port() {
        return db_port;
    }

    public static String getDb_username() {
        return db_username;
    }

    public static String getDb_password() {
        return db_password;
    }
}
