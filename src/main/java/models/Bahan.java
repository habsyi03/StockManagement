package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static models.DbConnection.con;
import static models.DbConnection.preparedStatement;
import static models.DbConnection.rs;
import static models.DbConnection.rsmd;

public class Bahan extends DbConnection{
    
    public List<Map<String, String>> getAllDataBahan(){
        return selectAllData("m_bahan");
    }
    
    public ArrayList<String> getCategoryBahan(){
        ArrayList<String> category = new ArrayList<String>();
        try{
            String query = "SELECT DISTINCT kategori_bahan FROM m_bahan";
            preparedStatement = con.prepareStatement(query);
	    rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                category.add(rs.getString("kategori_bahan"));
            }
            preparedStatement.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return category;
    }
    
     public List<Map<String, String>> getAllDataBahanPerKategori(String where){
        List<Map<String, String>> array = new ArrayList<Map<String, String>>();
        try{ 
            String sql = "select nama_bahan, stok_bahan, kategori_bahan from m_bahan where kategori_bahan=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, where);
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
