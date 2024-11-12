
package tp3;
import java.util.Scanner;



public class Tp3 {
    
  public static void main(String[] args) {
        
        //Creo la clase scanner para poder leer lo ingresado por pantalla
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println(VariablesEstaticas.ANSI_GREEN + "===== BIENVENIDO AL SISTEMA REGISTRO DE DATOS AGROMETEOROLOGICOS =====");
        System.out.println(VariablesEstaticas.ANSI_GREEN+ "Desea gestionar usuarios? 1.Si | 2.No");
        //Intento capturar excepciones ante cualquier ingreso fuera de los valores permitidos
        try{ int gestionar = scanner.nextInt();
        if (gestionar == 1){
        Usuario usuario = new Usuario();
        usuario.menuUsuario(); 
          
        } else if (gestionar == 2) {
        //Inicio de un bucle para gestionar el menú 
        while (!salir) {
            
            System.out.println(VariablesEstaticas.ANSI_GREEN + "1.TERMOMETRIA | 2.EVAPORIMETRICA | 3.PLUVIOMETRIA | 4.ANEMOMETRIA | 5.NUBOSIDAD | 6.PSICROMETRIA | 7.FENOMENOS | 8.SALIR");
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Elige una opción: ");

            int opcion = scanner.nextInt();
            

            switch (opcion) {
                case 1:
                    RegistroTemperatura regtem = new RegistroTemperatura();
                    regtem.menuRegistroTemperatura();
                    break;
                case 2:
                    RegistroEvaporimetrica.ConsolaEvaporimetrica();
                    break;
                case 3:
                   RegistroPluviometria.ConsolaPluviometria();
                    break;
                case 4:
                    System.out.println("===== ANEMOMETRIA =====");
                    break;
                case 5:
                    System.out.println("===== NUBOSIDAD =====");
                    break;
                case 6:
                    System.out.println("===== PSICROMETRIA =====");
                    break;
                case 7:
                    System.out.println("===== FENOMENOS METEOROLOGICOS =====");
                    break;
                case 8:
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                    break;
                default:
                    System.out.println(VariablesEstaticas.ANSI_GREEN +"Opción no válida, elige nuevamente.");
            }
        }

        
    } else{System.out.println(VariablesEstaticas.ANSI_GREEN + "Ingrese una opción valida...Saliendo del Sistema");}
        }catch (Exception e) {
                System.out.println(VariablesEstaticas.ANSI_GREEN+ "Error inesperado: se espera el ingreso de un 1 o 2 " /* + e.getMessage()*/);
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        scanner.close();
  }
}