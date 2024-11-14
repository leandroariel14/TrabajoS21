//Importo librerias
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
        //Inicio de un bucle para gestionar el men� 
        while (!salir) {
            
            System.out.println(VariablesEstaticas.ANSI_GREEN + "1.TERMOMETRIA | 2.PLUVIOMETRIA | 3.EVAPORIMETRICA | 4.ANEMOMETRIA | 5.NUBOSIDAD | 6.PSICROMETRIA | 7.FENOMENOS | 8.SALIR");
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Elige una opci�n: ");

            int opcion = scanner.nextInt();
            
//hay m�dulos no codeados todavia, estos pongo un SOUT con el m�dulo
            switch (opcion) {
                case 1:
                    RegistroTemperatura regtem = new RegistroTemperatura();
                    regtem.menuRegistroTemperatura();
                    break;
                case 2:
                    RegistroPluviometria regplu = new RegistroPluviometria();
                    regplu.menuRegistroPluviometria();
                    break;
                case 3:
                   System.out.println("===== EVAPORIMETRICA =====");
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
                    System.out.println(VariablesEstaticas.ANSI_GREEN +"Opci�n no v�lida, elige nuevamente.");
            }
        }

        
    } else{System.out.println(VariablesEstaticas.ANSI_GREEN + "Ingrese una opci�n valida...Saliendo del Sistema");}
        }catch (Exception e) {
                System.out.println(VariablesEstaticas.ANSI_GREEN+ "Error inesperado: se espera el ingreso de un 1 o 2 " /* + e.getMessage()*/);
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        scanner.close();
  }
}