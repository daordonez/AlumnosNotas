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

    //Control de alumnos insertados al Array "clase". Evita salida de rango en lectura del Array.
    static int uAlu = 0;

    public static void main(String[] args) {

        Utils.infoUs("--> Registro de notas <--");
        //Pregunta con cuantos alumnos contará la clase
        Utils.infoUs("Crear clase de alumnos");
        int cAlus = 0;
        do {

            System.out.print("Introduzca cantidad de alumnos por clase: ");
            
            //Evitar caracteres no alfabéticos
            try {
                cAlus = Utils.leerInt();
            } catch (java.util.InputMismatchException e) {
                Utils.redInfo("Caracter no valido");
                Utils.flush();
            }
        } while (cAlus <= 0);

        //Vector de alumnos que representa una clase de alumnos evita pos 0
        Alumno clase[] = new Alumno[cAlus + 1];

        
        boolean salir = false;
        int slc;

        do {
            //Evitar caracteres alfabéticos
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
                        
                        //No introducir mas alumnos de los que caben en el Array de alumnos
                        if (cAlus > 0) {
                            Utils.infoUs("Nuevo alumno <--");
                            uAlu = nuevoAlumno(clase);
                            cAlus--;
                        } else {
                            Utils.redInfo("Número máximo de alumnos alcanzado!");
                        }

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
                    default:
                        Utils.redInfo("Opción incorrecta");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                Utils.redInfo("Caracter no valido");
                Utils.flush();
            }
        } while (salir == false);
    }

    public static int nuevoAlumno(Alumno vecAlus[]) {
        
        //Preincremento para evitar posición 0 del Array de alumnos
        ++uAlu;

        vecAlus[uAlu] = new Alumno();

        return uAlu;
    }

    public static void muestraVectorObj(Alumno vecAlus[]) {

        for (int i = 1; i < vecAlus.length; i++) {
            //Evitar NullPointerExeption por no estar inicializado todo el vector de alumnos
            if (vecAlus[i] == null) {
                break;
            }
            vecAlus[i].mostrar();
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

            claseAlus[i].setNotaSingle(asig, eva, nota);
        }
    }

    public static void buscaAlumno(Alumno clasAlus[], String nombre) {

        for (int i = 1; i <= uAlu; i++) {

            String tmp = clasAlus[i].getNombre();

            System.out.println(tmp);
            if (tmp.equals(nombre)) {
                clasAlus[i].mostrar();
            }

        }
    }
}
