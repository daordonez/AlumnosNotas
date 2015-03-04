/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumnosnotas;

import java.util.ArrayList;
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
//                      Descomentar las siguientes lineas para alumnos de demostración
//                      Alumno alusDemo[] = Utils.AlusIn();
//                      clase.addAll(Arrays.asList(alusDemo));

                        Utils.infoUs("Nuevo alumno <--");
                        nuevoAlumno(clase);

                        Utils.showAlert("Introducido(s)");
                        
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
                            
                            //Si existe el indice será mayor que 0
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
                            //Calculos integrales de todos los valores dependientes de los alumnos aprobados
                            int toPrint[][] = estadisticasInd(clase);
                            
                            //Impresión por pantalla de la matriz estadistica
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
                            System.out.print("Nombre que desea modificar: ");
                            int fAlu = buscaAlumno(clase, Utils.leerCad());
                            
                            if (fAlu >= 0) {
                                modificaNota(clase, fAlu);
                            }
                            
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
                            /*
                            Valor 1 final para limpiar todo el vector.Valor 1 intermedio para evitar error en la llamada 
                            y reutilización del código   
                            */
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
        
        
        //Finalización del programa
        System.out.print("Saliendo");
        Utils.pausedExit(1, '.', 3);
    }

    public static void nuevoAlumno(ArrayList<Alumno> vecAlus) {

        //ArrayList gestiona por si solo las posiciones disponibles del vector
        vecAlus.add(new Alumno());
    }

    public static void muestraObj(ArrayList<Alumno> vecAlus, int index) {

        //Indice proveniente de la función de búsqueda
        Alumno alu = vecAlus.get(index);
        //Método própio de la clase
        alu.mostrar();
    }

    public static void pideNotas(ArrayList<Alumno> vecAlus) {

        vecAlus.stream().forEach((Alumno aluInVec) -> {
            notaSimple(aluInVec);
        });
    }

    /**
     * Función recogedora de un valor simple de una nota.
     * Pregunta por Asignatura y evaluación y lleva control de errores, tanto de rango como de caracteres
     * no esperados.
     * @param alu Objeto de la clase alumno que se desee modificar.
     */
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
                    //Habiendose cumplido todos los parametros. Modifica las notas del Objeto Alumno
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

    /**
     * Recorre todo el vector y devuelve indice si existe una ocurrencia.
     * @param vecAlus vector que se desea recorrer
     * @param nombre Criterio de busqueda a encontrar 
     * @return Indice basado en el criterio de búsqueda pasado.
     */
    public static int buscaAlumno(ArrayList<Alumno> vecAlus, String nombre) {

        int idx = -1;
        boolean exist = false;
        
        String strBusq = Utils.capitalize(nombre);
        
        for (Alumno alu : vecAlus) {
            if (alu.getNombre().equals(strBusq)) {
                idx = vecAlus.indexOf(alu);
                exist = true;
                break;
            } 
        }
        if ( exist == false) {
            Utils.redInfo("No existen alumnos con el nombre:"+nombre);
        }

        return idx;
    }
    
    public static int [][] estadisticasInd(ArrayList<Alumno> vecAlus){
     
        final int MAX_FIL = 3;
        final int MAX_COL = 3;
        
        //Evitar posición 1
        int matrizResult[][] = new int[MAX_FIL + 2][MAX_COL + 2];
        
        //Recorrido de vector de alumnos para ir sumando valores individuales en cada asignatura y evaluación
        vecAlus.stream().forEach((a) -> {
            for (int i = 1; i < MAX_FIL; i++) {
                for (int j = 1; j < MAX_COL; j++) {

                    if (a.getNotas(i, j) >= 5) {
                        
                        matrizResult[i][j] ++;
                    }
                }
            }
        });
        //Porcentaje de aprobados por asignatura
        for (int i = 1; i < matrizResult.length; i++) {
            for (int j = 1; j < matrizResult[0].length; j++) {
                
                int tmp = matrizResult[i][j];
                if (tmp != 0) {
                    
                     matrizResult[i][j] = (tmp * 100) / vecAlus.size();
                }
            }   
        }
       
        //Porcentajes de aprobados por evaluación y asignatura
        for (int col = 1; col < matrizResult[0].length; col++) {
            for (int fil = 1; fil < matrizResult.length; fil++) {
                
                int tmp = matrizResult[col][fil];
                if (tmp != 0) {
                    matrizResult[4][col] += tmp / 3;
                    matrizResult[fil][4] += tmp / 3;
                }    
            }            
        }
                
        return matrizResult;
    }
    
    public static void imprimeEst(int stad[][]){
    
        
        String nombres[] = Alumno.getNomNotas();
        
        String ev[] = {"","EVA1","EVA2","EVA3","TOTAL"};
        
        for (String e : ev) {
            System.out.printf("%-4s ",e);
        }
        System.out.println();
        for (int i = 1; i < stad.length; i++) {
            System.out.print(nombres[i]);
            for (int j = 1; j < stad[1].length; j++) {
                System.out.printf("%4d |",stad[i][j]);
            }
            System.out.println();
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

    /**
     * Modificación de notas. Es capaz de modificar una nota simple haciendo llamada a la función notaSimple, o
     * de modificar todas las notas de un objetos que se encuentre en el vector dinámico pasado, haciendo uso
     * de la función propia de la clase.
     * @param vecAlus Vector contenedor de los Objetos "Alumno" que se desean modificar
     * @param index Indice donde se encuentra el objeto "Alumno" que se desea modificar.
     */
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
