package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Bahan;

public class BahanController {

   public static final Bahan bahan = new Bahan();
   
   public ArrayList<String> getCategory(){
       //BahanEntity bahanEntity = BahanEntity.builder().categoryBahan(bahan.getCategoryBahan()).build();
       //return bahanEntity.getCategoryBahan();
       return bahan.getCategoryBahan();
   }
   
    public ArrayList<String> getIdBahan(){
       return bahan.getIdBahan();
   }
   
   public List<Map<String, String>> getAllDataBahan(){
        return bahan.getAllDataBahan();
    }
   
   public List<Map<String, String>> getAllDataBahanPerKategori(String where){
       return bahan.getAllDataBahanPerKategori(where);
   }
   
   public void addStockBahan(String id_bahan, int jumlah){
       bahan.tambahStockBahan(id_bahan, jumlah);
   }
    
}
