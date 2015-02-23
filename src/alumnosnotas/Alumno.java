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


public class Alumno {

    //Limites de matriz de notas
    private static final int MAX_ASIG = 3;
    private static final int MAX_EVA = 3;

    //posibles asignaturas que pueden existir
    private String nombre;
    private String telefono;
    private String dirCalle;
    private String dirNum;
    private int dirCP;
    private float notas[][];
    private static String nomNotas[] = {"", "PRG", "BDA", "EDD"};

    //Contador de objetos de tipo alumno crados 
    private static int cantAlu = 0;

    /**
     * Constructor que va pidiendo al usuario progresivamente los parametros
     */
    public Alumno() {

        this.nombre = Utils.capitalize(Utils.imputString("Nombre: "));
        this.telefono = Utils.imputString("Telefono: ");
        this.dirCalle = Utils.capitalize(Utils.imputString("Calle:"));
        this.dirNum = Utils.imputString("Numero: ");
        this.dirCP = Utils.imputInt("CP: ");

        this.notas = new float[MAX_ASIG + 1][MAX_EVA + 1];
        cantAlu++;
    }

    /**
     * Constructor simple todos los atributos se inicializarán por defecto a
     * 'null, menos el nombre pasado parametro. Se incrementará la cantidad de
     * objetos creados por clase
     *
     * @param nom Nombre del nuevo alumno
     *
     */
    public Alumno(String nom) {
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
     * Constructor mas detallado. Rellena todos los campos descriptivos del
     * objeto , menos las notas. Incrementa el contador global de objetos
     * creador por la clase.
     *
     * @param nom
     * @param telf
     * @param dirCalle
     * @param dirNum
     * @param dirCP
     */
    public Alumno(String nom, String telf, String dirCalle, String dirNum, int dirCP) {
        this.nombre = nom;
        this.telefono = telf;
        this.dirCalle = dirCalle;
        this.dirNum = dirNum;
        this.dirCP = dirCP;

        //Crea matriz de notas pero no se reciben parametros ni se puede modificar mediante
        //este constructor
        this.notas = new float[MAX_ASIG + 1][MAX_EVA + 1];

        cantAlu++;
    }

    //modificadores de atributos SET & GET
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = Utils.capitalize(nombre);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    //Nombre de la calle
    public String getDirCalle() {
        return dirCalle;
    }

    public void setDirCalle(String dirCalle) {
        this.dirCalle = Utils.capitalize(dirCalle);
    }
    
    //Número de la dirección
    public String getDirNum() {
        return dirNum;
    }

    public void setDirNum(String dirNum) {
        this.dirNum = dirNum;
    }
    //CP - Dirección
    public int getDirCP() {
        return dirCP;
    }

    public void setDirCP(int dirCP) {
        this.dirCP = dirCP;
    }
    
    //Se evita setCantAlu con tal de no poder modificar valor desde ningún lugar
    public static int getCantAlu() {
        return cantAlu;
    }

    public float getNotas(int asig, int eva) {
        return notas[asig][eva];
    }

    /**
     * Metodo adaptado a tener que pedir asignatura de la que se quiere
     * introducir la nota y la evaluación. Es decir Introducir nota simple en
     * matriz de notas
     *
     * @param asig asignatura siendo --> [1]= "PRG" , [1] = "BDA", [2] = "EDD"
     * @param eva número de asignatura. MAX = 3
     * @param valueNot Nota a introducir
     */
    public void setNotaSimple(int asig, int eva, float valueNot) {
        this.notas[asig][eva] = valueNot;
    }
    
    //Métodos públicos
    public void introNotas() {
        //Lectura usuario desde teclado
        

        System.out.println("Introduzca notas:");

        for (int i = 1; i < this.notas.length; i++) {
            System.out.println(Alumno.nomNotas[i]);
            for (int j = 1; j < this.notas[0].length; j++) {
                System.out.print("\t Eva " + j + ": ");
                this.notas[i][j] = Utils.leeFloat();
            }
        }
    }

    public void mostrar() {
        Utils.infoUs("Información alumno:");
        System.out.println("Nombre: " + this.nombre);
        System.out.println("\t");
        verDirecc();
        mostrarNotas();
    }
    

    private void mostrarNotas() {
        System.out.print("\t");
        for (int i = 1; i < notas[0].length; i++) {
            System.out.print("\tEVA:" + i);
        }
        System.out.println();
        for (int i = 1; i < this.notas.length; i++) {
            System.out.print("\t" + Alumno.nomNotas[i] + "|");
            for (int j = 1; j < this.notas[0].length; j++) {
                System.out.print("\t" + this.notas[i][j]);
            }
            System.out.println();
        }
    }
    private void verDirecc() {
        System.out.println("Calle: " + this.dirCalle);
        System.out.println("Num: " + this.dirNum);
        System.out.println("CP: " + this.dirCP);
    }
    

}
