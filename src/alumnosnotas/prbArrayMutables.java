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
        
        //Declaración lista de objetos de la clase Alumno
        ArrayList<Alumno> clase2 = new ArrayList<>();
        
        //Añade un objeto a la lista
        addObjct(clase2);
        
        showList(clase2);
    }
    
    private static void addObjct(ArrayList<Alumno> clase){
    
        clase.add(new Alumno());
    }
    
    private static void showList(ArrayList<Alumno> clase){
        
        //Iterador para la lista de Alumnos
        Iterator<Alumno> italus = clase.iterator();
        
        //Mientras existan objetos a continuación sigue iterando
        while (italus.hasNext()) {
            
            /*
                Apuntar siguiente objeto de la lista a un objeto de la clase alumno
                para posteriormente poder usar métodos de la propia clase.
            */
            Alumno alu = italus.next();
            
            //Método propio de la clase Alumno
            alu.mostrar();
            
        }
    }
    
    
}
