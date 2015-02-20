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
public class AlumnosNotas {

    /**
     * @param args the command line arguments
     */
    
    static int uAlu = 0;
    
    public static void main(String[] args) {
        
        Utils.infoUs("--> Registro de notas <--");
        //Pregunta con cuantos alumnos contará la clase
        Utils.infoUs("Crear clase de alumnos");
        int cAlus = 0;
        do {
            
            System.out.print("Introduzca cantidad de alumnos por clase: ");

            try {
                cAlus = Utils.leerInt();
            } catch (java.util.InputMismatchException e) {
                Utils.redInfo("Caracter no valido");
                Utils.flush();
            }
        } while (cAlus <= 0);

        //Vector de alumnos que representa una clase de alumnos evita pos 0
      Alumno clase[] = new Alumno[cAlus + 1];
        
        initCl(clase);

        boolean salir = false;
        int slc;
        
        do {
                try {
                Utils.infoUs("--> Menú <--");
                System.out.println("1. Alta alumne");
                System.out.println("2. Poner nota alumno");
                System.out.println("3. Buscar alumno");
                System.out.println("0. Salir");
                Utils.infoUs("");
                System.out.print("Opción: ");
                slc = Utils.leerInt();
                switch (slc) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        
                       
                        
                        if (cAlus > 0) {
                            Utils.infoUs("Nuevo alumno <--");
                            uAlu = nuevoAlumno(clase);
                            cAlus--;
                        }else {
                            Utils.redInfo("Número máximo de alumnos alcanzado!");
                        }
                        
                        break;
                    case 2:
                        
//                        muestraVectorObj(clase);
                        Utils.infoUs("Poner nota alumno <--");
                        pideNotas(clase);

                        break;
                    case 3:

                        break;
                    default:
                        System.err.println("Opción incorrecta");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                    Utils.redInfo("Caracter no valido");
                    Utils.flush();
            }
        } while (salir == false);
    }
   
    public static int nuevoAlumno( Alumno vecAlus[]){
        
        ++uAlu;
        
        System.out.print("\t Introduzca nombre:");
        vecAlus[uAlu].setNombre(Utils.leerCad());
        
        return uAlu;
    }
    
    public static void muestraVectorObj( Alumno vecAlus[]){
        
        for (int i = 1; i < vecAlus.length; i++) {
            vecAlus[i].mostrar();
        }
    }
    
    public static void initCl(Alumno cl[]){
        for (int i = 1; i < cl.length; i++) {
            cl[i] = new Alumno();
            
        }
    }
    
    public static void pideNotas(Alumno claseAlus[]){
        for (int i = 1; claseAlus.length < 10; i++) {
            Utils.infoUs("Nombre alumno: "+ claseAlus[i].getNombre());
            Utils.infoUs("-> Asignaturas <-");
            System.out.println("1. PRG (Programación)");
            System.out.println("2. BDA (Bases de datos)");
            System.out.println("3. EDD (Entornos)");
            
            boolean isAsig = false;
            int asig = 0;
            int eva = 0;
            do {
                try {
                    System.out.print("Selecciona asignatura:");
                    asig = Utils.leerInt();
                    System.out.print("Selecciona evaluación:");
                    eva = Utils.leerInt();

                    if ((asig < 0 || asig > 3) || (eva < 0 || eva > 3)) {
                        Utils.redInfo("Valor(es) fuera de rango. Rango comprendido 0-3");
                        
                    } else {
                        if (asig == 1) {
                            System.out.println("Programación");
                        } else if (asig == 2) {
                            System.out.println("Bases de datos");
                        } else if (asig == 3) {
                            System.out.println("Entornos de desarrollo");
                        }

                        System.out.println("EVA:" + eva);
                        isAsig = true;
                    }

                } catch (java.util.InputMismatchException e) {
                    Utils.redInfo("Caracter no reconocido. Debe ser valor entero");
                    Utils.flush();
                }
            } while (isAsig == false);

            boolean isNota = false;
            float nota = 0;
            do {
                try {
                    System.out.print("Introduce nota de asignatura:");
                    nota = Utils.leeFloat();
                    if (nota < 0 || nota > 10) {
                        Utils.redInfo("Nota fuera de rango. Rango permitido 0-10");
                    } else {
                        Utils.infoUs("");
                        System.out.println("Alumno:"+claseAlus[i].getNombre());
                        System.out.println("\t Nota:"+nota);
                        Utils.infoUs("");
                        isNota = true;
                    }
                } catch (java.util.InputMismatchException e) {
                    Utils.redInfo("Caracter no reconocido.Debe ser valor decimal");
                    Utils.flush();
                }
            } while (isNota == false);
            
            claseAlus[i].setNotaSingle(asig, eva, nota);
        }
    }
}
