
package tp3;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 *
 * @author gonzalez.leandro
 */
public class Evaporimetrica {
    public static final String ANSI_GREEN = "\u001B[32m"; //Para dar un sensación de pantalla de fosforo
    public static final String ANSI_RESET = "\u001B[0m"; // Para resetear el color
    protected int tipo_contenedor;
    protected int capacidad;
    protected String descripcion;
 //Constructor 
    public Evaporimetrica() {
    }
    //Constructor con los atributos correspondientes
    public Evaporimetrica(int tipo_contenedor, int capacidad, String descripcion) {
        this.tipo_contenedor = tipo_contenedor;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
    }

    public int getTipo_contenedor() {
        return tipo_contenedor;
    }

    public void setTipo_contenedor(int tipo_contenedor) {
        this.tipo_contenedor = tipo_contenedor;
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
    public static void  agregarContenedor(int tipo_contenedor,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN + "Se agrego un contenedor "+ descripcion + " Con el total de centímetros cúbicos "+ capacidad +" Tipo de Termómetro "+tipo_contenedor);
                    
   }
   public static void  modificarContenedor(int tipo_contenedor,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN +"Se modifico un contenedor "+ descripcion + " Con el total de centímetros cúbicos  "+ capacidad +" Tipo de Termómetro "+tipo_contenedor);
                    
   }
   public static void  eliminarContenedor(int tipo_contenedor,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN +"Se elimino un contenedor "+ descripcion + " Con el total de centímetros cúbicos  "+ capacidad +" Tipo de Termómetro "+tipo_contenedor);
                    
   }
   public static void  consultarContenedor(int tipo_contenedor,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN +"Se consulto un contenedor "+ descripcion + " Con el total de centímetros cúbicos  "+ capacidad +" Tipo de Termómetro "+tipo_contenedor);
                    
   }
   public static void  listarContenedor(int tipo_contenedor,int capacidad, String descripcion){
   System.out.println(ANSI_GREEN +"Se listo  contenedor "+ descripcion + " Con el total de centímetros cúbicos  "+ capacidad+" Tipo de Termómetro "+tipo_contenedor);
                    
   }
}
