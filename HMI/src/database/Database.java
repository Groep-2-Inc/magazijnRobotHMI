package database;
// Door Daan
import env.GetEnv;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import panels.PanelStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// con - Slaat een statische verbinding met de database op.
// hasDbConnection - Slaat de status van de database verbinding op.
public class Database {
    private static Connection con;
    private static boolean hasDbConnection;

    public Database() {
        // Maakt automatisch verbinding met de database.
        connectToDatase();
    }

    // Methode die verbinding maakt met de database.
    public static void connectToDatase(){
        // Als de env.json file data bevat, wordt de try en catch uitgevoerd.
        if (GetEnv.getDb_host() != null && GetEnv.getDb_username() != null && GetEnv.getDb_password() != null){
            // Probeert verbinding te maken met de database.
            try{
                // Laadt de MySQL-driver klasse.
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Maakt verbinding met de database (doormiddel van, host, username en password).
                con = DriverManager.getConnection(GetEnv.getDb_host(), GetEnv.getDb_username(), GetEnv.getDb_password());
                hasDbConnection = true;
                // Werkt de status bij in de applicatie
                PanelStatus.updateStatus();
            // Als de try is mislukt worden twee foutmeldingen getoond.
            }catch (Exception e){
                System.out.println(Database.class + ": " + e); // Foutmelding met details.
            }
        // Foutmelding als env.json bestand niet kan worden uitgelezen.
        }else {
            System.out.println("env.json kan niet worden uitgelezen, er wordt geen verbinding gemaakt!");
        }
    }

    // Methode die data ophaalt uit de database.
    public static JSONArray getDbData(String query, String[] placeholders){
        JSONArray data = new JSONArray();
        // Als er een verbinding is
        if (hasDbConnection){
            int rowCount = 0; // Houdt de rijen bij van een tabel.
            // Probeert data op te halen.
            try{
                // Slaat de query op als preparedstatement.
                PreparedStatement pstmt = con.prepareStatement(query);
                // Voor elk vraagteken in de preparedstatement wordt een placeholder ingevuld.
                for (int i = 0; i < placeholders.length; i++){
                    pstmt.setObject(i + 1, placeholders[i]);
                }
                // Voert de query uit.
                ResultSet rs = pstmt.executeQuery(query);
                // Haalt data op over de kolommen van een tabel.
                ResultSetMetaData rsmd = rs.getMetaData();
                // Slaat het aantal kolommen op.
                int columnCount = rsmd.getColumnCount();
                // Wanneer er een nieuwe regel in de tabel is.
                while (rs.next()){
                    JSONObject row = new JSONObject();
                    // Voor elke kolom
                    for (int i = 1; i <= columnCount; i++){
                        // Stop de naam en de waatde in row.
                        row.put(rsmd.getColumnName(i), rs.getString(i));
                    }
                    // Voegt de data toe aan het json object data.
                    data.add(rowCount, row);
                    rowCount++;
                }
            // Data ophalen mislukt.
            }catch(SQLException e){
                System.out.println(Database.class + ": " + e); // Toont foutmelding met details.
            }
        // Anders foutmelding
        }else {
            System.out.println("Er is geen verbinding met de database, er kan geen data worden opgehaald!");
        }
        return data;
    }

    // Methode die de verbinding verbreekt met de database.
    public static boolean stopConnection(){
        // Als er een verbinding is
        if(hasDbConnection){
            try {
                // Stopt verbinding en zet de status op false.
                con.close();
                hasDbConnection = false;
                System.out.println("Database verbinding verbroken.");
                return true;
                // Try mislukt
            }catch(SQLException e){
                System.out.println(Database.class + ": " + e); // Toont foutmelding met details.
                return false;
            }
            // Anders geef foutmelding
        }else {
            System.out.println("Verbinding kan niet verboken worden, omdat er geen verbinding is!");
            return false;
        }
    }


    // Methode die een update (of een insert) kan uitvoeren op de database.
    public static boolean updateDatabase(String query, String[] placeholders){
        // Als er een verbinding is.
        if (hasDbConnection){
            // Probeert update uit te voeren.
            try {
                // Slaat de query op als preparedstatement.
                PreparedStatement pstmt = con.prepareStatement(query);
                // Voor elk vraagteken in de preparedstatement wordt een placeholder ingevuld.
                for (int i = 0; i < placeholders.length; i++){
                    pstmt.setObject(i + 1, placeholders[i]);
                }
                // Voert query uit.
                pstmt.executeUpdate();
                return true;
            // Update mislukt.
            }catch (SQLException e) {
                System.out.println(Database.class + ": " + e); // Toont foutmelding met details.
                return false;
            }
        // Anders foutmelding
        }else {
            System.out.println("Er is geen verbinding met de database, update mislukt!");
            return false;
        }
    }

    // Methode die de status van de verbinding teruggeeft.
    public static boolean hasDbConnection() {
        return hasDbConnection;
    }
}
