package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Produk;

public class ProdukController {
    public static final Produk produk = new Produk();
    
    public List<Map<String, String>> getProduk(){
       return produk.getAllDataProduk();
   }
    
    public ArrayList<String> getIdProduk(){
       return produk.getIdProduk();
   }
    
}
