/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumnosnotas;

/**
 *
 * @author diegordonez
 */
import java.util.*;

public class Utils {
    
    private static Iterator it ;
    private static Scanner tec = new Scanner(System.in);
    
    public static void infoUs(String info){
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println(info);
    }
    
    public static void redInfo(String info){
         for (int i = 0; i < 25; i++) {
            System.err.print("*");
        }
        System.err.println();
        System.err.println(info);
    }
    
    //Arrays
    public static void iterar(){}
   
    
    //Lecturas desde teclado
    public static String leerCad(){
        String cad ;
        cad = tec.next();
        return cad;
    }
    public static int leerInt(){
        int num;
        num = tec.nextInt();
        return num;
    }
    public static float leeFloat(){
        float fl;
        fl = tec.nextFloat();
        return fl;
    }
    //Flush de buffer
    public static void flush(){
        tec.nextLine();
    }
    
    //Imput- errores
    
    public static int imputInt(){
        int numInt = 0;
        
        boolean isInt = false;
        
        do {            
            try {
            tec.nextInt();
            isInt = true;
        } catch (java.util.InputMismatchException e) {
            redInfo("Caracter no reconocido. Debe ser Entero");
        }
        } while (isInt == false);
        
        return numInt;
    }
    
    public static String imputString(){
        String cadString = null;
        
        boolean isString = false;
        
        do {            
            try {
            tec.next();
            isString = true;
        } catch (java.util.InputMismatchException e) {
            redInfo("Caracter no reconocido. Debe ser Cadena (String)");
        }
        } while (isString == false);
        
        return cadString;
    }
    
}
