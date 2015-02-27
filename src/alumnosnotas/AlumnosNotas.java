/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumnosnotas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author diegordonez
 */
public class AlumnosNotas {

    public static void main(String[] args) {

        Utils.infoUs("--> Registro de notas <--");

        ArrayList<Alumno> clase = new ArrayList<>();

        boolean salir = false;
        int slc;

        do {
            //Evitar caracteres alfabéticos
            try {
                Utils.infoUs("");
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
                //Evitar dejar buffer ocupado con ENTER
                slc = Utils.leerInt();
                Utils.flush();

                switch (slc) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        
                        Alumno alusDemo[] = Utils.AlusIn();
                        
                        clase.addAll(Arrays.asList(alusDemo));
                        
                        Utils.showAlert("Introducidos");
                        
//                        Utils.infoUs("Nuevo alumno <--");
//                        nuevoAlumno(clase);

                        break;
                    case 2:

                        //No poner notas si no existen alumnos introducidos
                        if (clase.isEmpty()) {
                            Utils.showRedAlert("No existen alumnos dados de alta");
                        } else {
                            Utils.infoUs("Poner nota alumnos <--");
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
                            //Recoge el resultado de la busqueda
                            int idxAlu = buscaAlumno(clase, aluBus);
                            
                            //Si exist el indice será mayor que 0
                            if (idxAlu >= 0) {
                                muestraObj(clase, idxAlu);
                            }                            
                        }
                        break;

                    //Optativo
                    case 4:
                        
                        if (clase.isEmpty()) {
                            Utils.showAlert("No existen alumnos para mostrar estadisticas");
                        }else{
                            Utils.infoUs("Estadisticas");
                            int toPrint[][] = estadisticas(clase);
                            imprimeEst(toPrint);
                        }
                        
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
                            System.out.print("Introduzca nombre que desea módificar:");
                            int fAlu = buscaAlumno(clase, Utils.leerCad());
                            modificaNota(clase, fAlu);
                        }
                        break;

                    case 7:

                        if (clase.isEmpty()) {
                            Utils.showAlert("La lista de alumnos esta vacia");
                        } else {

                            Utils.infoUs("Borrar alumno X");
                            System.out.print("Introduza nombre de alumno:");
                            int index = buscaAlumno(clase, Utils.leerCad());

                            
                            if (index >= 0) {
                                //Valor 0 para borrado de objeto simple
                                borrarObjs(clase, index, 0);
                            }
                        }
                        break;
                    case 8:

                        if (clase.isEmpty()) {
                            Utils.showAlert("La lista de alumnos está vacia");
                        } else {
                            //Valor 1 final para limpiar todo el vector.Valor 1 intermedio para evitar error en la llamada 
                            //y reutilización del código
                            borrarObjs(clase, 1, 1);
                        }

                        break;
                    default:
                        Utils.redInfo("Opción incorrecta");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                //Caracteres alfabeticos no permitidos
                Utils.redInfo("Caracter no valido");
                Utils.flush();
            }
        } while (salir == false);

        System.out.print("Saliendo");
        Utils.pausedExit(1, '.', 3);
    }

    public static void nuevoAlumno(ArrayList<Alumno> vecAlus) {

        //ArrayList gestiona el solo las posiciones disponibles del vector
        vecAlus.add(new Alumno());
    }

    public static void muestraObj(ArrayList<Alumno> vecAlus, int index) {

        Alumno alu = vecAlus.get(index);

        alu.mostrar();
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

    public static int buscaAlumno(ArrayList<Alumno> vecAlus, String nombre) {

        int idx = -1;
        
        String strBusq = Utils.capitalize(nombre);

        for (Alumno alu : vecAlus) {
            if (alu.getNombre().equals(strBusq)) {
                idx = vecAlus.lastIndexOf(alu);
            } else {
                Utils.redInfo("No existen alumnos con el nombre: " + nombre);
            }
        }

        return idx;
    }
    
    public static int [][] estadisticas(ArrayList<Alumno> vecAlus){
     
        final int MAX_FIL = 4;
        final int MAX_COL = 4;
        
        //Evitar posición 1
        int matrizResult[][] = new int[MAX_FIL + 1][MAX_COL + 1];
        int acAp[][] = new int[MAX_FIL + 1][MAX_COL + 1];
        int cantAp [][] = new int[MAX_FIL + 1][MAX_COL + 1];
        
        
        vecAlus.stream().forEach((Alumno alu) -> {
            for (int i = 1; i < matrizResult.length; i++) {
                for (int j = 1; j < matrizResult[0].length; j++) {

                    if (alu.getNotas(i, j) >= 5) {
//                        float tmp = alu.getNotas(i, j);
//                        acAp[i][j] += (int) tmp;
                        cantAp[i][j]++;

                        //Calculo de aprobados
                        int aprs = cantAp[i][j] / vecAlus.size();
                        
                        matrizResult[i][j] = aprs;
                    }
                }
            }
        });
        
        return matrizResult;
        
    }
    
    public static void imprimeEst(int stad[][]){
    
        String nombres[] = Alumno.getNomNotas();
        
        for (int i = 1; i < stad.length; i++) {
            System.out.print(nombres[i]);
            for (int j = 1; j < stad[1].length; j++) {
                System.out.print("| "+stad[i][j]);
            }
        }
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
    }

    public static void modificaNota(ArrayList<Alumno> vecAlus, int index) {

        
        Alumno alu = vecAlus.get(index);
        
                boolean isModified = false;
                do {
                    try {
                        alu.mostrar();
                        Utils.infoUs("Actualizar notas:");
                        System.out.println("1. Actualizar nota simple");
                        System.out.println("2. Actualizar todas las notas");
                        Utils.infoUs("");
                        int opc = Utils.imputInt("Introduzca opción: ");
                        Utils.flush();

                        switch (opc) {
                            case 1:
                                notaSimple(alu);
                                Utils.showAlert("Nota modificada!");
                                isModified = true;
                                break;
                            case 2:
                                Utils.infoUs("Modificación notas para:" + alu.getNombre());
                                alu.introNotas();
                                Utils.showAlert("Notas modificadas correctamente!");
                                isModified = true;
                                break;
                            default:
                                Utils.redInfo("Opción incorrecta");
                        }
                    } catch (java.util.InputMismatchException e) {
                        Utils.redInfo("Caracter no reconocido");
                    }
                } while (isModified == false);
            
    }

    /**
     * Función empleada para el borrado parcial o total de la lista de objetos.
     * Mostrar un mensaje de alerta sobre la acción que se va a llevar a cabo y
     * en caso de ser afirmativa procederá con el borrado.Será necesario pasar
     * por para metro , indicando el tipo de borrado que se quiere ejecutar. Se
     * recomienda no recoger directamente la opción de borrado del usuario, si
     * no especificarla de manera manual, en la llamada a la función.
     *
     * @param vecAlus Lista de objetos que se va a borrar
     * @param index Indice del objeto que se desea eliminar en el caso de
     * borrado parcial de una lista
     * @param removOpt Valores permitidos 0 - 1 . 0 --> para borrar UN SOLO
     * objeto. 1 --> para borrar toda la lista
     */
    public static void borrarObjs(ArrayList<Alumno> vecAlus, int index, int removOpt) {

        boolean isClear = false;

        do {
            Utils.redInfo("");
            Utils.showRedAlert("Acción irreversible!!");
            Utils.redInfo("¿Desea continuar? [SI/NO] ");
            System.err.print("Opción: ");
            String opc = Utils.leerCad();

            if (!(opc.equals("SI") || opc.equals("si") || opc.equals("NO") || opc.equals("no"))) {
                Utils.redInfo("Cadena no permitida. Introduzca [SI/NO] [si/no]");
            } else {
                switch (opc) {
                    case "SI":
                    case "si":
                        System.out.print("Borrando [");
                        Utils.pausedExit(1, '*', 10);
                        System.out.println("]");
                        //Borrado completo/ parcial (Objeto simple) de la lista 
                        switch (removOpt) {
                            case 0:
                                vecAlus.remove(index);
                                isClear = true;
                                break;
                            case 1:
                                vecAlus.clear();
                                isClear = true;
                                break;
                            default:
                                Utils.redInfo("Opción borrado incorrecta");
                        }
                        break;
                    case "NO":
                    case "no":
                        Utils.infoUs("Volviendo al menú principal");

                        isClear = true;
                        break;
                }
            }
        } while (isClear == false);

    }

}
