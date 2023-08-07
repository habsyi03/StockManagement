package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbConnection {
    
    public static Connection con;
    public static PreparedStatement preparedStatement;
    public static ResultSet rs;
    public static ResultSetMetaData rsmd;
    
    public DbConnection(){
        String url = "jdbc:sqlite:src/main/java/db/stockmanagement";
        
        try{
            con = DriverManager.getConnection(url);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            con = null;
        }
    }
    
    public List<Map<String, String>> selectAllData(String tableName){
        List<Map<String, String>> array = new ArrayList<Map<String, String>>();
        try{ 
            String sql = "select * from "+tableName;
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            rsmd = rs.getMetaData();
            int clmCount = rsmd.getColumnCount();
            
            while (rs.next()) {
                Map<String, String> object = new HashMap<>();
                for(int i=1;i<=clmCount;i++){
                    String name = rsmd.getColumnName(i);
                    object.put(name, rs.getString(name));
                }
                array.add(object);          
            }
            preparedStatement.close();
            rs.close();
            //con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }      
        return array;
    }
}
