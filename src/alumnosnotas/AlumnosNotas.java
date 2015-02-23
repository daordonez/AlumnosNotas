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
public class AlumnosNotas {

    //Control de alumnos insertados al Array "clase". Evita salida de rango en lectura del Array.
    static int uAlu = 0;

//    private static Iterator<Alumno> itAlu ;
    
    public static void main(String[] args) {

        
        Utils.infoUs("--> Registro de notas <--");
//        Pregunta con cuantos alumnos contará la clase
//        Utils.infoUs("Crear clase de alumnos");
//        int cAlus = 0;
//        do {
//
//            System.out.print("Introduzca cantidad de alumnos por clase: ");
//
//            //Evitar caracteres no alfabéticos
//            try {
//                cAlus = Utils.leerInt();
//            } catch (java.util.InputMismatchException e) {
//                Utils.redInfo("Caracter no valido");
//                Utils.flush();
//            }
//        } while (cAlus <= 0);
//
//        //Vector de alumnos que representa una clase de alumnos evita pos 0
//        Alumno clase[] = new Alumno[cAlus + 1];

        ArrayList<Alumno> clase = new ArrayList<>();
        
        int cAlus = clase.size();
        
        
        boolean salir = false;
        int slc;

        do {
            //Evitar caracteres alfabéticos
            try {
                Utils.infoUs("--> Menú <--");
                System.out.println("1. Alta alumne");
                System.out.println("2. Poner nota alumno");
                System.out.println("3. Buscar alumno");
                System.out.println("4. Estadisticas");
                System.out.println("5. Modificar datos de alumno");
                System.out.println("6. Modificar nota  de alumno");
                System.out.println("7. Borrar alumno");
                System.out.println("8. Borrar todos los alumnos");
                System.out.println("0. Salir");
                Utils.infoUs("");
                System.out.print("Opción: ");
                slc = Utils.leerInt();
                Utils.flush();
                
                switch (slc) {
                    case 0:
                        salir = true;
                        break;
                    case 1:

                        Utils.infoUs("Nuevo alumno <--");
                        uAlu = nuevoAlumno(clase);

                        break;
                    case 2:

                        //No poner notas si no existen alumnos introducidos
                        if (uAlu == 0) {
                            Utils.redInfo("No existen alumnos dados de alta");
                        } else {
                            muestraVectorObj(clase);
                            Utils.infoUs("Poner nota alumno <--");
                            pideNotas(clase);
                        }

                        break;
                    case 3:

                        //Evitar buscar alumnos si no existen alumnos dados de alta
                        if (uAlu == 0) {
                            Utils.redInfo("No existen alumnos dados de alta");
                        } else {

                            Utils.infoUs("Busca alumno ¿?");
                            System.out.print("Introduce nombre de alumno:");
                            String aluBus = Utils.leerCad();
                            buscaAlumno(clase, aluBus);
                        }

                        break;
                        //Optativo
                    case 4:
                        break;
                    case 5:
                        
                        if (uAlu == 0) {
                            Utils.redInfo("No existen alumnos que módificar");
                        } else {
                            Utils.infoUs("Modificar datos alumno !¡");
                            Utils.infoUs("");
                            System.out.print("Introduzca nombre que desea módificar:");
                            modificaAlumno(clase, Utils.leerCad());
                        }
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    default:
                        Utils.redInfo("Opción incorrecta");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                Utils.redInfo("Caracter no valido");
                Utils.flush();
            }
        } while (salir == false);
        
        System.out.print("Saliendo");
        Utils.pausedExit(1,'.', 3);
    }

    public static int nuevoAlumno(ArrayList<Alumno> vecAlus) {

        //Preincremento para evitar posición 0 del Array de alumnos
        ++uAlu;

        vecAlus.add(new Alumno());

        return uAlu;
    }

    public static void muestraVectorObj(ArrayList<Alumno> vecAlus) {

        Iterator<Alumno> itAlus = vecAlus.iterator();
        
        for (int i = 1; i < vecAlus.size(); i++) {
            if (itAlus.hasNext()) {
                vecAlus.get(i);
            }
            
        }
    }

    public static void pideNotas(Alumno claseAlus[]) {
        // "<=" como condición en el caso de que solo exista un alumno introducido
        for (int i = 1; i <= uAlu; i++) {
            Utils.infoUs("Nombre alumno: " + claseAlus[i].getNombre());
            Utils.infoUs("-> Asignaturas <-");
            System.out.println("1. PRG (Programación)");
            System.out.println("2. BDA (Bases de datos)");
            System.out.println("3. EDD (Entornos)");

            boolean isAsig = false;
            //Incializar variables para función getNotaSingle en clase Alumno
            int asig = 0;
            int eva = 0;
            do {
                try {
                    System.out.print("Selecciona asignatura:");
                    asig = Utils.leerInt();
                    System.out.print("Selecciona evaluación:");
                    eva = Utils.leerInt();

                    //No permitir salirse del rango de la matriz de notas
                    if ((asig <= 0 || asig > 3) || (eva <= 0 || eva > 3)) {
                        Utils.redInfo("Valor(es) fuera de rango. Rango comprendido 1-3");

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
                        System.out.println("Alumno:" + claseAlus[i].getNombre());
                        System.out.println("\t Nota:" + nota);
                        Utils.infoUs("");
                        isNota = true;
                    }
                } catch (java.util.InputMismatchException e) {
                    Utils.redInfo("Caracter no reconocido.Debe ser valor decimal");
                    Utils.flush();
                }
            } while (isNota == false);

            claseAlus[i].setNotaSimple(asig, eva, nota);
        }
    }

    public static void buscaAlumno(Alumno clasAlus[], String nombre) {

        for (int i = 1; i <= uAlu; i++) {

            String tmp = clasAlus[i].getNombre();

            System.out.println(tmp);
            if (tmp.equals(nombre)) {
                clasAlus[i].mostrar();
                break;
            }else{
                Utils.redInfo("No existen alumnos con el nombre:"+nombre+" en esta clase");
            }

        }
    }
    
    public static void modificaAlumno (Alumno claseAlus[], String nombre){
        Utils.infoUs("Alumno:"+nombre);
        
        for (int i = 1; i <= uAlu; i++) {
            String tmp = claseAlus[i].getNombre();
            
            if (nombre.equals(tmp)) {
                claseAlus[i].mostrar();
                claseAlus[i].setNombre(Utils.imputString("\t Nuevo nombre:"));
                claseAlus[i].setTelefono(Utils.imputString("\t Nuevo telefono:"));
                claseAlus[i].setDirCalle(Utils.imputString("\t Nuevo nombre calle:"));
                claseAlus[i].setDirNum(Utils.imputString("\t Nuevo número (Dirección):"));
                claseAlus[i].setDirCP(Utils.imputInt("\t Nuevo CP:"));
                break;
            }else{
                Utils.redInfo("No existen alumnos con el nombre: "+nombre+" en esta clase");
            }
        }
    }
    
}
