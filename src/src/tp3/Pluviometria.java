package tp3;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 *
  @author gonzalez.leandro
 */
public class Pluviometria {
    //Se puede crear una clase que tenga las constantes que se van a usar en el sistema
    public static final String ANSI_GREEN = "\u001B[32m"; //Para dar un sensaci�n de pantalla de fosforo
    public static final String ANSI_RESET = "\u001B[0m"; // Para resetear el color
    protected int tipo_pluviometro;
    protected int capacidad;
    protected String descripcion;
    public Pluviometria() {
    }
    //Constructor con los atributos correspondientes
    public Pluviometria(int tipo_pluviometro, int capacidad, String descripcion) {
        this.tipo_pluviometro = tipo_pluviometro;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
    }
    public int getTipo_pluviometro() {
        return tipo_pluviometro;
    }
    public void setTipo_pluviometro(int tipo_pluviometro) {
        this.tipo_pluviometro = tipo_pluviometro;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   public static void consolaPluviometro(){
       Scanner scanner = new Scanner(System.in);
       boolean salir = false;
       while (!salir) {
        System.out.println(ANSI_GREEN + "===== PLUVIOMETRO =====");        
        System.out.println(ANSI_GREEN + "1.Agregar Pluvi�metro");
        System.out.println(ANSI_GREEN + "2.Eliminar Pluvi�metro");
        System.out.println(ANSI_GREEN + "3.Modificar Pluvi�metro");
        System.out.println(ANSI_GREEN + "4.Consultar Pluvi�metro");
        System.out.println(ANSI_GREEN + "5.Listar Pluvi�metro");
        System.out.println(ANSI_GREEN + "6.Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el car�cter de nueva l�nea
        switch (opcion) {
                case 1:  
                    //Se solicita los datos para dar de alta
                    try {System.out.println(ANSI_GREEN + "Ingrese tipo de pluvi�metro, se espera un entero");
                    int tipo_pluviometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del pluvi�metro, se espera un entero");
                    int capacidad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de pluvi�metro");
                    String descripcion = scanner.nextLine();
                    
                    agregarPluviometro(tipo_pluviometro, capacidad, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");
                    }break;
                case 2:
                    //Se solicita los datos para eliminar
                     try {System.out.println(ANSI_GREEN + "Ingrese tipo de pluvi�metro, se espera un entero");
                    int tipo_pluviometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del pluvi�metro, se espera un entero");
                    int capacidad = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de pluvi�metro");
                    String descripcion = scanner.nextLine();
                    
                    eliminarPluviometro(tipo_pluviometro, capacidad, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 3:
                    //Se solicita los datos para modificar
                      //Se solicita los datos para eliminar
                     try {System.out.println(ANSI_GREEN + "Ingrese tipo de pluvi�metro, se espera un entero");
                    int tipo_pluviometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del pluvi�metro, se espera un entero");
                    int capacidad = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de pluvi�metro");
                    String descripcion = scanner.nextLine();
                    
                    modificarPluviometro(tipo_pluviometro, capacidad, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 4:
                    //Se solicita los datos para Consultar
                      //Se solicita los datos para eliminar
                     try {System.out.println(ANSI_GREEN + "Ingrese tipo de pluvi�metro, se espera un entero");
                    int tipo_pluviometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del pluvi�metro, se espera un entero");
                    int capacidad = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de pluvi�metro");
                    String descripcion = scanner.nextLine();
                    
                    consultarPluviometro(tipo_pluviometro, capacidad, descripcion);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 5:
                    //Se solicita los datos para Listar
                     //Se solicita los datos para eliminar
                     try {System.out.println(ANSI_GREEN + "Ingrese tipo de pluvi�metro, se espera un entero");
                    int tipo_pluviometro = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese la capacidad total del pluvi�metro, se espera un entero");
                    int capacidad = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese descripci�n de pluvi�metro");
                    String descripcion = scanner.nextLine();
                    
                    listarPluviometro(tipo_pluviometro, capacidad, descripcion);
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
    public static void  agregarPluviometro(int tipo_pluviometro,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN + "Se agrego un pluvi�metro "+ descripcion + " Con el total de grados "+ capacidad +" Tipo de Term�metro "+tipo_pluviometro);
                    
   }
   public static void  modificarPluviometro(int tipo_pluviometro,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN +"Se modifico un pluvi�metro "+ descripcion + " Con el total de grados "+ capacidad +" Tipo de Term�metro "+tipo_pluviometro);
                    
   }
   public static void  eliminarPluviometro(int tipo_pluviometro,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN +"Se elimino un pluvi�metro "+ descripcion + " Con el total de grados "+ capacidad +" Tipo de Term�metro "+tipo_pluviometro);
                    
   }
   public static void  consultarPluviometro(int tipo_pluviometro,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN +"Se consulto un pluvi�metro "+ descripcion + " Con el total de grados "+ capacidad +" Tipo de Term�metro "+tipo_pluviometro);
                    
   }
   public static void  listarPluviometro(int tipo_pluviometro,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN +"Se listo  pluvi�metro "+ descripcion + " Con el total de grados "+ capacidad+" Tipo de Term�metro "+tipo_pluviometro);
                    
   }
}

    

