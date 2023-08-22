
package controller;

import java.util.List;
import java.util.Map;
import models.TMasukBahan;

public class TMasukBahanController {
    
    public static final TMasukBahan tMasukBahan = new TMasukBahan();
    
    public void addTMasukBahan(Map<String, String> param){
        tMasukBahan.insertTMasukBahan(param);
    }
    
    public List<Map<String, String>> getAllDataTMasukBahan(){
        return tMasukBahan.getAllDataTransaksiMasukBahan();
    }
    
}
