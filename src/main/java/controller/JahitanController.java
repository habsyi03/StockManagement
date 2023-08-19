package controller;

import java.util.List;
import java.util.Map;
import models.Jahitan;

public class JahitanController {
    
    public static final Jahitan jahitan = new Jahitan();
    
    public void addJahitan(Map<String, String> param){
        jahitan.insertTJahitan(param);
    }
    
    public List<Map<String, String>> getAllDataJahitan(){
        return jahitan.getAllDataJahitan();
    }
    
    public void updateTJahitan(Map<String, String> param){
        jahitan.updateTJahitan(param);
    }
    
}
