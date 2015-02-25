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

    private static Iterator<Alumno> itAlus;

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
                        if (clase.isEmpty()) {
                            Utils.showRedAlert("No existen alumnos dados de alta");
                        } else {
                            muestraVectorObj(clase);
                            Utils.infoUs("Poner nota alumno <--");
                            pideNotas(clase);
                        }

                        break;
                    case 3:

                        //Evitar buscar alumnos si no existen alumnos dados de alta
                        if (clase.isEmpty()) {
                            Utils.showRedAlert("No existen alumnos dados de alta");
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

                        if (clase.isEmpty()) {
                            Utils.showRedAlert("No existen alumnos que módificar");
                        } else {
                            Utils.infoUs("Modificar datos alumno !¡");
                            Utils.infoUs("");
                            System.out.print("Introduzca nombre que desea módificar:");
                            modificaAlumno(clase, Utils.leerCad());
                        }
                        break;
                    case 6:

                        if (clase.isEmpty()) {
                            Utils.showRedAlert("No existen alumnos que modificar");
                        } else {
                            Utils.infoUs("Modificar nota alumno !¡");
                            Utils.infoUs("");
                            System.out.println("Introduzca nombre que desea módificar:");
                        }

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
        Utils.pausedExit(1, '.', 3);
    }

    public static int nuevoAlumno(ArrayList<Alumno> vecAlus) {

        //Preincremento para evitar posición 0 del Array de alumnos
        ++uAlu;

        vecAlus.add(new Alumno());

        return uAlu;
    }

    public static void muestraVectorObj(ArrayList<Alumno> vecAlus) {

        itAlus = vecAlus.iterator();

        while (itAlus.hasNext()) {
            Alumno next = itAlus.next();

            next.mostrar();

        }
    }

    public static void pideNotas(ArrayList<Alumno> vecAlus) {

        vecAlus.stream().forEach((Alumno aluInVec) -> {
            notaSimple(aluInVec);
        });
    }

    public static void notaSimple(Alumno alu) {

        Utils.infoUs("Nombre alumno: " + alu.getNombre());
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
        float nota;
        do {
            try {
                System.out.print("Introduce nota de asignatura:");
                nota = Utils.leeFloat();
                if (nota < 0 || nota > 10) {
                    Utils.redInfo("Nota fuera de rango. Rango permitido 0-10");
                } else {
                    Utils.infoUs("");
                    System.out.println("Alumno:" + alu.getNombre());
                    System.out.println("\t Nota:" + nota);
                    Utils.infoUs("");
                    alu.setNotaSimple(asig, eva, nota);
                    Utils.showAlert("Nota introducida correctamente!");
                    isNota = true;
                }
            } catch (java.util.InputMismatchException e) {
                Utils.redInfo("Caracter no reconocido.Debe ser valor decimal");
                Utils.flush();
            }
        } while (isNota == false);
    }

    public static void buscaAlumno(ArrayList<Alumno> clasAlus, String nombre) {

        clasAlus.stream().forEach((aluInVec) -> {
            if (aluInVec.getNombre().equals(nombre)) {
                aluInVec.mostrar();
            } else {
                Utils.redInfo("No existen alumno(s) con el nombre " + nombre);
            }
        });
    }

    public static void modificaAlumno(ArrayList<Alumno> vecAlus, String nombre) {

        Utils.infoUs("Alumno:" + nombre);

        vecAlus.stream().filter((aluInVec) -> (aluInVec.getNombre().equals(nombre))).map((aluInVec) -> {
            aluInVec.mostrar();
            return aluInVec;
        }).map((aluInVec) -> {
            //Nuevos datos
            Utils.infoUs("Nuevos datos para: " + aluInVec.getNombre());
            return aluInVec;
        }).map((aluInVec) -> {
            aluInVec.setNombre(Utils.capitalize(Utils.imputString("Nuevo nombre: ")));
            return aluInVec;
        }).map((aluInVec) -> {
            aluInVec.setTelefono(Utils.imputString("Nuevo telefono: "));
            return aluInVec;
        }).map((aluInVec) -> {
            aluInVec.setDirCalle(Utils.capitalize(Utils.imputString("Nueva Calle: ")));
            return aluInVec;
        }).map((aluInVec) -> {
            aluInVec.setDirNum(Utils.imputString("Nuevo número (Dirección): "));
            return aluInVec;
        }).forEach((aluInVec) -> {
            aluInVec.setDirCP(Utils.imputInt("Nuevo CP: "));
        });
        //  }
//}       
//        for (Alumno aluInVec : vecAlus) {
//            if (aluInVec.getNombre().equals(nombre)){
//                aluInVec.mostrar();
//                //Nuevos datos
//                Utils.infoUs("Nuevos datos para: "+aluInVec.getNombre());
//                aluInVec.setNombre(Utils.capitalize(Utils.imputString("Nuevo nombre: ")));
//                aluInVec.setTelefono(Utils.imputString("Nuevo telefono: "));
//                aluInVec.setDirCalle(Utils.capitalize(Utils.imputString("Nueva Calle: ")));
//                aluInVec.setDirNum(Utils.imputString("Nuevo número (Dirección): "));
//                aluInVec.setDirCP(Utils.imputInt("Nuevo CP: "));
//            }
//        }
    }

    public static void modificaNota(ArrayList<Alumno> vecAlus, String nombre) {

        vecAlus.stream().forEach((Alumno aluInVec) -> {

            if (vecAlus.contains(aluInVec.getNombre())) {

                boolean isModified = false;
                do {
                    try {
                        aluInVec.mostrar();
                        Utils.imputInt("Actualizar notas:");
                        System.out.println("1. Actualizar nota simple");
                        System.out.println("2. Actualizar todas las notas");
                        Utils.infoUs("");
                        int opc = Utils.imputInt("Introduzca opción: ");

                        switch (opc) {
                            case 1:
                                notaSimple(aluInVec);
                                Utils.showAlert("Nota modificada!");
                                isModified = true;
                                break;
                            case 2:
                                Utils.infoUs("Modificación notas para:" + nombre);
                                aluInVec.introNotas();
                                isModified = true;
                                break;
                            default:
                                Utils.redInfo("Opción incorrecta");
                        }
                    } catch (java.util.InputMismatchException e) {
                        Utils.redInfo("Caracter no reconocido");
                    }
                } while (isModified == false);
            } else {
                Utils.showRedAlert("No existe alumnos con el nombre" + nombre + ".");
            }
        });
    }

}
