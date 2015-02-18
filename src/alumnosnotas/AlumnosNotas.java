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
    public static void main(String[] args) {
        
        Utils.infoUs("--> Registro de notas <--");
        //Pregunta con cuantos alumnos contará la clase
        
        Utils.infoUs("Crear clase de alumnos");
        System.out.print("Introduzca cantidad de alumnos por clase: ");
        int cAlus = Utils.leerInt();
        
        //Vector de alumnos que representa una clase de alumnos
        Alumno clase[] = new Alumno[cAlus + 1];
        
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

                        Utils.infoUs("Nuevo alumno <--");
                        nuevoAlumno(clase);

                        break;
                    case 2:

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
    
    public static void nuevoAlumno( Alumno vecAlus[]){
        
        Alumno alumno = new Alumno();
        
        int uAlu = Alumno.getCantAlu();
        
        vecAlus[uAlu] = alumno;
    }
    
}
