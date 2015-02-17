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
    private String nomNotas[];
    
    //Contador de objetos de tipo alumno crados 
    private static int cantAlu = 0;
    
    //Constructor por defecto, campo minimo requerido nombre de alumno
    public Alumno( String nom){
        this.nombre = nom;
        this.telefono = null;
        this.dirCalle = null;
        this.dirNum = null;
        this.dirCP = 0;
        
        //evitar evaluación 0
        this.notas = new float[MAX_ASIG + 1][MAX_EVA + 1];
        
        //Inicializa nombres de notas para cualquier alumno, se supone que cada alumno nuevo tiene las tres asignaturas
        this.nomNotas = new String[MAX_ASIG + 1];
        
        initNomNot(this.nomNotas);
        
        //Si se hace uso del constructor incrementa contador de objetos de tipo alumno
        
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

    public static void setCantAlu(int cantAlu) {
        Alumno.cantAlu = cantAlu;
    }
    
    private static void initNomNot(String nombres[]){
        nombres[1] = "PRG";
        nombres[2] = "BDA";
        nombres[3] = "EDD";
    }
    
    public void introNotas() {
        //Lectura usuario desde teclado
        Scanner tec = new Scanner(System.in);
        
        System.out.println("Introduzca notas:");

        for (int i = 1; i < this.notas.length; i++) {
            System.out.println(this.nomNotas[i]);
            for (int j = 1; j < this.notas[0].length; j++) {
                System.out.print("\t Eva " + j + ": ");
                this.notas[i][j] = tec.nextFloat();
            }
        }
    }

    private void mostrarNotas() {
        for (int i = 1; i < this.notas.length; i++) {
            System.out.print("\t" + this.nomNotas[i]);
            for (int j = 1; j < this.notas[0].length; j++) {
                System.out.print("\t EVA " + j + ": " + this.notas[i][j]);
            }
            System.out.println();
        }
    }
    
    public void mostrar(){
        infoUs("Información alumno:");
        System.out.println("Nombre: "+this.nombre);
        System.out.println("\t");
        verDirecc();
        mostrarNotas();
        
    }
    
    private void infoUs(String info){
        for (int i = 0; i < 15; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println(info);
    }
    
    private void verDirecc(){
        System.out.println("Calle: "+dirCalle);
        System.out.println("Num: "+dirNum);
        System.out.println("CP: "+dirCP);
    }
    
}
