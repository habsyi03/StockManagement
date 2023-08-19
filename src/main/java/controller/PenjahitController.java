package controller;

import java.util.ArrayList;
import models.Penjahit;

public class PenjahitController {
    
    public static final Penjahit penjahit = new Penjahit();
    
    public ArrayList<String> getIdPenjahit(){
       return penjahit.getIdPenjahit();
   }
}
