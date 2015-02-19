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
public class Alumno {
    
    private static final int MAX_ASIG = 3;
    private static final int MAX_EVA = 3;
    
    //posibles asignaturas que pueden existir
    
    
    
    private String nombre;
    private String telefono;
    private String dirCalle;
    private String dirNum;
    private int dirCP;
    private float notas[][];
    private static String nomNotas[] = {"","PRG","BDA","EDD"};
    
    
    //Contador de objetos de tipo alumno crados 
    private static int cantAlu = 0;
    
        
    /**
     * Constructor que va pidiendo al usuario progresivamente los parametros
     */
    public Alumno(){
        
        System.out.print("Nombre: ");
        this.nombre = Utils.leerCad();
        Utils.infoUs("Dirección:");
        System.out.print("\t Calle:");
        this.dirCalle = Utils.leerCad();
        System.out.print("\t Número:");
        this.dirNum = Utils.leerCad();
        System.out.print("\t CP:");
        this.dirCP = Utils.leerInt();
        
        this.notas = new float[MAX_ASIG + 1][MAX_EVA +1];
        cantAlu++;
    }
    /**
     * Constructor simple todos los atributos se inicializarán por defecto a 'null, menos el nombre pasado
     * parametro.
     * Se incrementará la cantidad de objetos creados por clase
     * @param nom Nombre del nuevo alumno
     * 
     */
    public Alumno(String nom){
        this.nombre = nom;
        this.telefono = null;
        this.dirCalle = null;
        this.dirNum = null;
        this.dirCP = 0;
        
        //evitar evaluación 0
        this.notas = new float[MAX_ASIG + 1][MAX_EVA + 1];
                
        //Si se hace uso del constructor incrementa contador de objetos de tipo alumno
        
        cantAlu++;
    }
    //Constructor Completo
    /**
     * Constructor mas detallado. Rellena todos los campos descriptivos del objeto , menos las notas.
     * Incrementa el contador global de objetos creador por la clase.
     * @param nom
     * @param telf
     * @param dirCalle
     * @param dirNum
     * @param dirCP 
     */
    public Alumno(String nom, String telf, String dirCalle, String dirNum, int dirCP){
        this.nombre = nom;
        this.telefono = telf;
        this.dirCalle = dirCalle;
        this.dirNum = dirNum;
        this.dirCP = dirCP;
        
        //Crea matriz de notas pero no se reciben parametros ni se puede modificar mediante
        //este constructor
        
        this.notas = new float[MAX_ASIG + 1][MAX_EVA +1];
        
        cantAlu++;
    }
    
    //modificadores de atributos SET & GET
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDirCalle() {
        return dirCalle;
    }

    public void setDirCalle(String dirCalle) {
        this.dirCalle = dirCalle;
    }

    public String getDirNum() {
        return dirNum;
    }

    public void setDirNum(String dirNum) {
        this.dirNum = dirNum;
    }

    public int getDirCP() {
        return dirCP;
    }

    public void setDirCP(int dirCP) {
        this.dirCP = dirCP;
    }

    public static int getCantAlu() {
        return cantAlu;
    }

    private static void setCantAlu(int cantAlu) {
        Alumno.cantAlu = cantAlu;
    }

    public float[][] getNotas() {
        return notas;
    }

    /**
     * Metodo adaptado a tener que pedir asignatura de la que se quiere introducir la nota y la evaluación. Es decir
     * Introducir nota simple en matriz de notas
     * @param asig asignatura siendo --> [1]= "PRG" , [1] = "BDA", [2] = "EDD"
     * @param eva número de asignatura. MAX = 3
     * @param valueNot Nota a introducir
     */
    public void setNotaSingle(int asig, int eva, float valueNot) {
        this.notas[asig][eva] = valueNot;
    }
    
    
    
    public void introNotas() {
        //Lectura usuario desde teclado
        Scanner tec = new Scanner(System.in);
        
        System.out.println("Introduzca notas:");

        for (int i = 1; i < this.notas.length; i++) {
            System.out.println(Alumno.nomNotas[i]);
            for (int j = 1; j < this.notas[0].length; j++) {
                System.out.print("\t Eva " + j + ": ");
                this.notas[i][j] = tec.nextFloat();
            }
        }
    }

    private void mostrarNotas() {
        System.out.print("\t");
        for (int i = 1; i < notas[0].length; i++) {
            System.out.print("\tEVA:"+i);
        }
        System.out.println();
        for (int i = 1; i < this.notas.length; i++) {
            System.out.print("\t" + Alumno.nomNotas[i]+"|");
            for (int j = 1; j < this.notas[0].length; j++) {
                System.out.print("\t"+ this.notas[i][j]);
            }
            System.out.println();
        }
    }
    
    public void mostrar(){
        Utils.infoUs("Información alumno:");
        System.out.println("Nombre: "+this.nombre);
        System.out.println("\t");
        verDirecc();
        mostrarNotas();
        
    }
    
    private void verDirecc(){
        System.out.println("Calle: "+dirCalle);
        System.out.println("Num: "+dirNum);
        System.out.println("CP: "+dirCP);
    }
    
}
