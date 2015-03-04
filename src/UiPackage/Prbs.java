/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiPackage;

import alumnosnotas.Utils;
import java.util.Scanner;

/**
 *
 * @author diegordonez
 */
public class Prbs {
    
    static Scanner tec = new Scanner(System.in);
    
    public class Datos{
        
        private String nombre;
        private String telf;
        private String dirNom;
        private String dirNum;
        private int cp;
        
        public Datos (String n,String t,String dN, String dNu, int cp){
        
            this.nombre = n;
            this.telf = t;
            this.dirNom = dN;
            this.dirNum = dNu;
            this.cp = cp;
        }
//           
    }
    
    public static void main(String[] args) {
        
        
        
        System.out.print("Nombre fichero: ");
        String path = Utils.creadorTXT(tec.next());
        
        Datos dt[] = new Datos[20];
        
        for (Datos dt1 : dt) {
        }
        
        
       
        
    }
    
    public static void calculoEst() {
        
        int notas[][] = {{6, 7, 8}, {0, 0, 0}, {10, 6, 3}};

        for (int[] nota : notas) {
            for (int j = 0; j < notas[0].length; j++) {
                System.out.print(nota[j] + " ");
            }
            System.out.println();
        }

        for (int[] nota : notas) {
            for (int j = 0; j < notas[0].length; j++) {

                int tmp = nota[j];
                if (tmp != 0) {
                    nota[j] = (nota[j] * 100) / nota.length;
                    System.out.print("Value: " + nota[j] + " ");
                }
            }
            System.out.println();
        }
    }
}
