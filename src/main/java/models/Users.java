package models;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Users extends DbConnection {
    
    public List<Map<String, String>> usersSelectAllData(){
        return selectAllData("m_akun");
    }
    
    public void usersInsertData(String username, String password){
        try{
            String query = "INSERT INTO users (username,password) VALUES(?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
	    preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void usersUpdateData(String whereValue, String username, String password){
        try{
            String query = "UPDATE users SET username=?,password=? where username=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, whereValue);
	    preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
   public void usersDeleteData(String whereValue){
        try{
            String query = "DELETE FROM users WHERE username=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, whereValue);
	    preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
}
