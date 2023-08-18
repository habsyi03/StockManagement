package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static models.DbConnection.con;
import static models.DbConnection.preparedStatement;
import static models.DbConnection.rs;

public class Produk extends DbConnection {
    
    public List<Map<String, String>> getAllDataProduk(){
        return selectAllData("m_produk");
    }
    
    public ArrayList<String> getIdProduk(){
        ArrayList<String> produk = new ArrayList<String>();
        try{
            String query = "SELECT DISTINCT id_produk FROM m_produk order by id_produk asc";
            preparedStatement = con.prepareStatement(query);
	    rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                produk.add(rs.getString("id_produk"));
            }
            preparedStatement.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return produk;
    } 
    
}
