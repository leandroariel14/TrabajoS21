
package tp3;

//import java.util.Scanner;

import java.util.Scanner;


//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;

/**
 *
 * @author gonzalez.leandro
 */
public class Termometria {
    //Se puede crear una clase que tenga las constantes que se van a usar en el sistema
    public static final String ANSI_GREEN = "\u001B[32m"; //Para dar un sensaci�n de pantalla de fosforo
    public static final String ANSI_RESET = "\u001B[0m"; // Para resetear el color
    protected int tipo_termometro;
    protected int total_grados;
    protected String descripcion;
    //Constructor vac�o
    public Termometria() {
    }
    //Constructor con los atributos correspondientes
    public Termometria(int tipo_termometro, int total_grados, String descripcion) {
        this.tipo_termometro = tipo_termometro;
        this.total_grados = total_grados;
        this.descripcion = descripcion;
    }

    public int getTipo_termometro() {
        return tipo_termometro;
    }

    public void setTipo_termometro(int tipo_termometro) {
        this.tipo_termometro = tipo_termometro;
    }

    public int getGrados() {
        return total_grados;
    }

    public void setGrados(int grados) {
        this.total_grados = total_grados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public static void consolaTermometro(){
       Scanner scanner = new Scanner(System.in);
       boolean salir = false;
       while (!salir) {
        System.out.println(ANSI_GREEN + "===== Term�metro =====");        
        System.out.println(ANSI_GREEN + "1.Agregar Term�metro");
        System.out.println(ANSI_GREEN + "2.Eliminar Term�metro");
        System.out.println(ANSI_GREEN + "3.Modificar Term�metro");
        System.out.println(ANSI_GREEN + "4.Consultar Term�metro");
        System.out.println(ANSI_GREEN + "5.Listar Term�metro");
        System.out.println(ANSI_GREEN + "6.Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el car�cter de nueva l�nea
        switch (opcion) {
                case 1:  
                    //Se solicita los datos para dar de alta
                    try {System.out.println(ANSI_GREEN + "Ingrese tipo de Term�metro, se espera un entero");
                    int tipo_termometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del termometro, se espera un entero");
                    int total_grados = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de term�metro");
                    String descripcion = scanner.nextLine();
                    agregarTermometro(tipo_termometro, total_grados, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                     break;
                case 2:
                    //Se solicita los datos para eliminar
                     try {System.out.println(ANSI_GREEN + "Ingrese tipo de term�metro, se espera un entero");
                    int tipo_termometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del term�metro, se espera un entero");
                    int total_grados = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de term�metro");
                    String descripcion = scanner.nextLine();
                    
                    eliminarTermometro(tipo_termometro, total_grados, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;

                case 3:
                    //Se solicita los datos para modificar
                      //Se solicita los datos para eliminar
                     try {System.out.println(ANSI_GREEN + "Ingrese tipo de term�metro, se espera un entero");
                    int tipo_termometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del term�metro, se espera un entero");
                    int total_grados = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de term�metro");
                    String descripcion = scanner.nextLine();
                    
                    modificarTermometro(tipo_termometro, total_grados, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 4:
                    //Se solicita los datos para Consultar
                      //Se solicita los datos para eliminar
                     try {System.out.println(ANSI_GREEN + "Ingrese tipo de term�metro, se espera un entero");
                    int tipo_termometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del term�metro, se espera un entero");
                    int total_grados = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de term�metro");
                    String descripcion = scanner.nextLine();
                    
                    consultarTermometro(tipo_termometro, total_grados, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 5:
                    //Se solicita los datos para Listar
                     //Se solicita los datos para eliminar
                     try {System.out.println(ANSI_GREEN + "Ingrese tipo de term�metro, se espera un entero");
                    int tipo_termometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del term�metro, se espera un entero");
                    int total_grados = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de term�metro");
                    String descripcion = scanner.nextLine();
                    
                    listarTermometro(tipo_termometro, total_grados, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 6:
                    System.out.println(ANSI_GREEN + "Saliendo del sistema...");
                    salir = true;
                    break;
                                    
                default:
                    System.out.println(ANSI_GREEN + "Opci�n no v�lida, elige nuevamente.");
            }
        }
    }
   private static void  agregarTermometro(int tipo_termometro,int total_grados, String descripcion){
   System.out.println(ANSI_GREEN + "Se agrego un term�metro "+ descripcion + " Con el total de grados "+ total_grados+" Tipo de Term�metro "+tipo_termometro);
                    
   }
   private static void  modificarTermometro(int tipo_termometro,int total_grados, String descripcion){
   System.out.println(ANSI_GREEN +"Se modifico un term�metro "+ descripcion + " Con el total de grados "+ total_grados+" Tipo de Term�metro "+tipo_termometro);
                    
   }
   public static void  eliminarTermometro(int tipo_termometro,int total_grados, String descripcion){
   System.out.println(ANSI_GREEN +"Se elimino un term�metro "+ descripcion + " Con el total de grados "+ total_grados+" Tipo de Term�metro "+tipo_termometro);
                    
   }
   public static void  consultarTermometro(int tipo_termometro,int total_grados, String descripcion){
   System.out.println(ANSI_GREEN +"Se consulto un term�metro "+ descripcion + " Con el total de grados "+ total_grados+" Tipo de Term�metro "+tipo_termometro);
                    
   }
   public static void  listarTermometro(int tipo_termometro,int total_grados, String descripcion){
   System.out.println(ANSI_GREEN +"Se listo  term�metro "+ descripcion + " Con el total de grados "+ total_grados+" Tipo de Term�metro "+tipo_termometro);
                    
   }
}
