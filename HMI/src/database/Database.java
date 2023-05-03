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

public class Database {
    private static Connection con;
    private static boolean hasDbConnection;

    public Database() {
        connectToDatase();
    }

    public static void connectToDatase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets", "root", "");
            hasDbConnection = true;
            PanelStatus.updateStatus();
        }catch (Exception e){
            System.out.println(Database.class + ": " + e);
        }
    }

    public static JSONArray getDbData(String query, String[] placeholders){
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

    public static boolean updateDatabase(String query, String[] placeholders){
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            for (int i = 0; i < placeholders.length; i++){
                pstmt.setObject(i + 1, placeholders[i]);
            }
            pstmt.executeUpdate();
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
