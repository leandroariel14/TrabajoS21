
package tp3;

//import java.util.Scanner;
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
    protected int grados;
    protected String descripcion;
    //Constructor vac�o
    public Termometria() {
    }
    //Constructor con los atributos correspondientes
    public Termometria(int tipo_termometro, int grados, String descripcion) {
        this.tipo_termometro = tipo_termometro;
        this.grados = grados;
        this.descripcion = descripcion;
    }

    public int getTipo_termometro() {
        return tipo_termometro;
    }

    public void setTipo_termometro(int tipo_termometro) {
        this.tipo_termometro = tipo_termometro;
    }

    public int getGrados() {
        return grados;
    }

    public void setGrados(int grados) {
        this.grados = grados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
   public static void  agregarTermometro(int tipo_termometro,int total_grados, String descripcion){
   System.out.println(ANSI_GREEN + "Se agrego un term�metro "+ descripcion + " Con el total de grados "+ total_grados+" Tipo de Term�metro "+tipo_termometro);
                    
   }
   public static void  modificarTermometro(int tipo_termometro,int total_grados, String descripcion){
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
