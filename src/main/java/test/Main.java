package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Bahan;
import models.Users;

public class Main {

    public static void main(String[] args) {
        Users users = new Users();
        Bahan bahan = new Bahan();
        
         
        //System.out.println(users.usersSelectAllData());
        //System.out.println(bahan.getCategoryBahan());
        
        //System.out.println(bahan.getAllDataBahan().get(0));
        
        int size = bahan.getAllDataBahan().size();
        
        List<Map<String, String>> myList = new ArrayList<Map<String, String>>();
        myList = bahan.getAllDataBahan();
        
        for(int i=0;i<size;i++){
            List<String> list = new ArrayList<String>();
            Map<String, String> row  = new HashMap<>();
                row.putAll(myList.get(i));
                for(String key: row.keySet()){
                    if(!key.equalsIgnoreCase("id_bahan")){
                        list.add(row.get(key));
                    }
                }
             System.out.println(list);
        }
    }   
}
