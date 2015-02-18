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
import java.util.Scanner;

public class Utils {
    
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
    
    public static void flush(){
        tec.nextLine();
    }
    
}
