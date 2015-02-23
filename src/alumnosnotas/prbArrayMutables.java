/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumnosnotas;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author diegordonez
 */
public class prbArrayMutables {
    
    public static void main(String[] args) {
        ArrayList<Alumno> clase2 = new ArrayList<>();
        
        addObjct(clase2);
        
        showList(clase2);
    }
    
    private static void addObjct(ArrayList<Alumno> clase){
    
        clase.add(new Alumno());
    }
    
    private static void showList(ArrayList<Alumno> clase){
        Iterator<Alumno> italus = clase.iterator();
        
        while (italus.hasNext()) {
            
            Alumno alu = italus.next();
            
            alu.mostrar();
            
        }
    }
    
    
}
