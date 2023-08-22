package models;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import static models.DbConnection.con;
import static models.DbConnection.preparedStatement;
import static models.DbConnection.rs;

public class TMasukBahan extends DbConnection {

    public void insertTMasukBahan(Map<String, String> param) {
        try {
            String query = "INSERT INTO t_masuk_bahan VALUES(?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, param.get("id_masuk_bahan"));
            preparedStatement.setString(2, param.get("tanggal"));
            preparedStatement.setString(3, param.get("id_bahan"));
            preparedStatement.setString(4, param.get("id_penjahit"));
            preparedStatement.setInt(5, Integer.parseInt(param.get("jumlah")));
            preparedStatement.setString(6, param.get("status"));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
   public List<Map<String, String>> getAllDataTransaksiMasukBahan(){
        return selectData("select * from t_masuk_bahan order by tanggal desc");
    }
}
