package models;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static models.DbConnection.con;
import static models.DbConnection.preparedStatement;

public class Jahitan extends DbConnection {
    
    public List<Map<String, String>> getAllDataJahitan(){
        return selectAllData("t_jahitan");
    }
    
    public void insertTJahitan(Map<String, String> param){
        try{
            String query = "INSERT INTO t_jahitan VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, param.get("id_jahitan"));
            preparedStatement.setString(2, param.get("tanggal"));
            preparedStatement.setString(3, param.get("id_penjahit"));
            preparedStatement.setString(4, param.get("id_bahan"));
            preparedStatement.setString(5, param.get("id_produk"));           
            preparedStatement.setString(6, param.get("size"));
            preparedStatement.setString(7, param.get("jumlah_barang"));
            preparedStatement.setInt(8, Integer.parseInt(param.get("bahan_terpakai")));
            preparedStatement.setString(9, param.get("barang_masuk"));
            preparedStatement.setString(10, param.get("barang_pending"));
            preparedStatement.setString(11, param.get("status"));           
	    preparedStatement.executeUpdate();
            preparedStatement.close();
            rs.close();
            minusStockBahan(param.get("id_bahan"), Integer.parseInt(param.get("bahan_terpakai")));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void minusStockBahan(String id_bahan, int minus){
        try{
            String query = "UPDATE m_bahan SET stok_bahan=stok_bahan - ? where id_bahan=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, minus);
            preparedStatement.setString(2, id_bahan);
	    preparedStatement.executeUpdate();
            preparedStatement.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void plusStockBahan(String id_bahan, int plus){
        try{
            String query = "UPDATE m_bahan SET stok_bahan=stok_bahan + ? where id_bahan=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, plus);
            preparedStatement.setString(2, id_bahan);
	    preparedStatement.executeUpdate();
            preparedStatement.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private String generateIdJahitan(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return "TJ_"+dateFormat.format(new Date());
    }
    
    private int getBahanTerpakai(String primaryKey){
        int bahan_terpakai = 0;
        try{
            String query = "SELECT bahan_terpakai FROM t_jahitan where id_jahitan = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, primaryKey);
	    rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                bahan_terpakai = rs.getInt("bahan_terpakai");
            }
            preparedStatement.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return bahan_terpakai;
    }
    
    public void updateTJahitan(Map<String, String> param){
        int tempBahanTerpakai = getBahanTerpakai(param.get("id_jahitan"));
        int newBahanTerpakai = Integer.parseInt(param.get("bahan_terpakai"));
        
        try{
            String query = "UPDATE t_jahitan SET tanggal=?,id_penjahit=?,id_bahan=?,id_produk=?,size=?,jumlah_barang=?"
                    + ",bahan_terpakai=?,barang_masuk=?,barang_pending=?,status=? where id_jahitan=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, param.get("tanggal"));
            preparedStatement.setString(2, param.get("id_penjahit"));
            preparedStatement.setString(3, param.get("id_bahan"));
            preparedStatement.setString(4, param.get("id_produk"));           
            preparedStatement.setString(5, param.get("size"));
            preparedStatement.setString(6, param.get("jumlah_barang"));
            preparedStatement.setInt(7, Integer.parseInt(param.get("bahan_terpakai")));
            preparedStatement.setString(8, param.get("barang_masuk"));
            preparedStatement.setString(9, param.get("barang_pending"));
            preparedStatement.setString(10, param.get("status"));
            preparedStatement.setString(11, param.get("id_jahitan"));
	    preparedStatement.executeUpdate();
            preparedStatement.close();
            rs.close();
            
            if(tempBahanTerpakai != newBahanTerpakai){
                int hasil = tempBahanTerpakai - newBahanTerpakai;
                if(hasil < 0){
                    minusStockBahan(param.get("id_bahan"), Math.abs(hasil));
                }else{
                    plusStockBahan(param.get("id_bahan"), hasil);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
