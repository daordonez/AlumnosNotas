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
import java.io.*;
import java.util.*;

public class Utils {

//    private static Iterator it;
    private static final Scanner tec = new Scanner(System.in);

    //Salidas por pantalla
    public static void infoUs(String info) {
        for (int i = 0; i < 35; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println(info);
    }

    public static void redInfo(String info) {
        for (int i = 0; i < 35; i++) {
            System.err.print("*");
        }
        System.err.println();
        System.err.println(info);
    }

    /**
     * Función para pasusar salida. Va imprimiendo puntos con un retardo
     * especificado por parametro
     *
     * @param seg Delay --> Tiempos en segundos entre punto y punto
     * @param s Caracter que se desa mostrar en secuencia
     * @param cantSimb --> cantidad de simbolos que quiere imprimir
     */
    public static void pausedExit(int seg, char s, int cantSimb) {

        int miliSeg = seg * 1000;

        for (int i = 0; i < cantSimb; i++) {
            System.out.print(s);
            try {
                Thread.sleep(miliSeg);
            } catch (InterruptedException e) {
                redInfo("Delay erroneo");
                flush();
            }
        }
    }

    /**
     * Función que devuelve la cadena pasada con la primera letra en mayusculas.
     * 
     * @param toCapitalize Cadena que a la que se desea dar formato
     * @return Cadena formateada con la primera letra mayuscula.
     */
    public static String capitalize(String toCapitalize) {
        if (toCapitalize.length() == 0) {
            return toCapitalize;
        }
        String capitalized = toCapitalize.substring(0, 1).toUpperCase() + toCapitalize.substring(1);

        return capitalized;
    }

    public static void showAlert(String str) {

        infoUs("");
        infoUs(str);
    }

    public static void showRedAlert(String str) {

        redInfo(str);
        redInfo("");
    }

    //Lecturas desde teclado
    public static String leerCad() {
        String cad;
        cad = tec.nextLine();
        return cad;
    }

    public static int leerInt() {
        int num;
        num = tec.nextInt();
        return num;
    }

    public static float leeFloat() {
        float fl;
        fl = tec.nextFloat();
        return fl;
    }

    //Flush de buffer
    public static void flush() {
        tec.nextLine();
    }

    //Imput- errores
    /**
     * Se debe pasar cadena que se quiere mostrar para cuando el valor insertado
     * no sea correcto
     *
     * @param cadena
     * @return Devuelve valor correcto. En este caso Int
     */
    public static int imputInt(String cadena) {

        int numInt = 0;

        boolean isInt = false;

        do {
            System.out.print(cadena);
            try {
                numInt = leerInt();
                isInt = true;
            } catch (java.util.InputMismatchException e) {
                redInfo("Caracter no reconocido. Debe ser Entero (Int)");
                flush();
            }
        } while (isInt == false);

        return numInt;
    }

    public static float imputFloat(String cadena) {

        float numFloat = 0;

        boolean isFloat = false;

        do {
            System.out.print(cadena);
            try {
                numFloat = leeFloat();
                isFloat = true;
            } catch (java.util.InputMismatchException e) {
                redInfo("Caracter no reconocido. Debe ser decimal (Float)");
                flush();
            }
        } while (isFloat == false);

        return numFloat;
    }

    /**
     * Igual que inputInt. Pero trata cadenas.
     *
     * @param cadena
     * @return Devuelve valor correcto. En este caso String
     */
    public static String imputString(String cadena) {

        String cadString = null;

        boolean isString = false;

        do {
            System.out.print(cadena);
            try {
                cadString = leerCad();
                isString = true;

            } catch (java.util.InputMismatchException e) {
                redInfo("Caracter no reconocido. Debe ser Cadena (String)");
                flush();
            }
        } while (isString == false);

        return cadString;
    }

    //Escritura ficheros .TXT
    public static String creadorTXT(String dirArch) {

        String rutaAbs = "/Users/diegordonez/Desktop/".concat(dirArch + ".txt");

        File archivo = new File(rutaAbs);

        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado exitosamente!!");
            }
        } catch (IOException e) {
            System.err.println("Imposible crear archivo erorr: " + e);
        }

        return rutaAbs;
    }

    public static void escribir(String cad, String pathString) {

        try {
            try (FileWriter esc = new FileWriter(pathString, true); BufferedWriter bufW = new BufferedWriter(esc)) {
                bufW.write(cad);
                bufW.newLine();
            }
        } catch (Exception e) {
            System.err.println("No ha sido posible escribir en el fichero");
        }
    }

    
    /**
     * Función de introducción de alumnos de demostración. 
     * Esta función introduce cuatro alumnos de demostración a un vector de objetos de la clase "Alumno"
     * @return 
     */
    public static Alumno[] AlusIn() {
        
        Alumno aluDemo[] = new Alumno[4];
        Alumno alu1 = new Alumno("Fran", "611111114", "Calle Grande", "22", 46702);
        Alumno alu2 = new Alumno("Rafael", "600452338", "Calle Pequena", "72", 46702);
        Alumno alu3 = new Alumno("Maria", "658000008", "Calle Mediana", "22", 46702);
        Alumno alu4 = new Alumno("Ana", "600000004", "Calle larga", "22", 46702);
        
        
        
        aluDemo[0] = alu1;
        aluDemo[1] = alu2;
        aluDemo[2] = alu3;
        aluDemo[3] = alu4;
        
        
        return aluDemo;
    }
}
