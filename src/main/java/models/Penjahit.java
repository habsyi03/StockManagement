package models;

import java.sql.SQLException;
import java.util.ArrayList;
import static models.DbConnection.con;
import static models.DbConnection.preparedStatement;
import static models.DbConnection.rs;

public class Penjahit extends DbConnection{
    
    public ArrayList<String> getIdPenjahit(){
        ArrayList<String> idBahan = new ArrayList<String>();
        try{
            String query = "SELECT DISTINCT id_penjahit FROM m_penjahit order by id_penjahit asc";
            preparedStatement = con.prepareStatement(query);
	    rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                idBahan.add(rs.getString("id_penjahit"));
            }
            preparedStatement.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return idBahan;
    } 
    
}
