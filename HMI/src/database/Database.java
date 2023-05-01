package database;
// Door Daan
import env.GetEnv;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import panels.PanelStatus;

import java.sql.*;

public class Database {
    private static Connection con;
    private static boolean hasDbConnection;
    public Database() {
        connectToDatase();
    }

    public static void connectToDatase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(GetEnv.getDb_host(), GetEnv.getDb_username(), GetEnv.getDb_password());
            hasDbConnection = true;
            PanelStatus.updateStatus();
        }catch (Exception e){
            System.out.println(Database.class + ": " + e);
        }
    }

    public static JSONArray getDbData(String query){
        JSONArray data = new JSONArray();
        if (hasDbConnection){
            int rowCount = 0;
            try{
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (rs.next()){
                    JSONObject row = new JSONObject();
                    for (int i = 1; i <= columnCount; i++){
                        row.put(rsmd.getColumnName(i), rs.getString(i));
                    }
                    data.add(rowCount, row);
                    rowCount++;
                }
            }catch(SQLException e){
                System.out.println(Database.class + ": " + e);
            }
        }
        return data;
    }

    public static void stopConnection(){
        if(hasDbConnection){
            try {
                con.close();
                hasDbConnection = false;
            }catch(SQLException e){
                System.out.println(Database.class + ": " + e);
            }
        }
    }

    public static boolean insertInDatabase(String query){
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return true;
        }catch (SQLException e) {
            System.out.println(Database.class + ": " + e);
            return false;
        }
    }

    public static boolean hasDbConnection() {
        return hasDbConnection;
    }
}
