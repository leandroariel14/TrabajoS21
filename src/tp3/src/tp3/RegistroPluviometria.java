
package tp3;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
/**
 *
 * @author gonzalez.leandro
 */
public class RegistroPluviometria {
    public static final String ANSI_GREEN = "\u001B[32m"; //Para dar un sensación de pantalla de fosforo
    public static final String ANSI_RESET = "\u001B[0m"; // Para resetear el color
    private LocalDate fecha;
    private LocalTime hora;
    private int tipo_pluviometro;
    private int volumen;
    private String observacion;

    public RegistroPluviometria() {
    }
//Constructor con los atributos correspondientes
    public RegistroPluviometria(LocalDate fecha, LocalTime hora, int tipo_pluviometro, int volumen, String observacion) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipo_pluviometro = tipo_pluviometro;
        this.volumen = volumen;
        this.observacion = observacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getTipo_pluviometro() {
        return tipo_pluviometro;
    }

    public void setTipo_pluviometro(int tipo_pluviometro) {
        this.tipo_pluviometro = tipo_pluviometro;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public static void ConsolaPluviometria(){
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        // While para manejar la consola del ingreso de datos
        while (!salir) {
        System.out.println(ANSI_GREEN + "===== PlUVIOMETRIA =====");        
        System.out.println(ANSI_GREEN + "1.Agregar Tipo de Pluviómetro");
        System.out.println(ANSI_GREEN + "2.Agregar Lectura");
        System.out.println(ANSI_GREEN + "3.Eliminar Lectura");
        System.out.println(ANSI_GREEN + "4.Modificar Lectura");
        System.out.println(ANSI_GREEN + "5.Consultar Lectura");
        System.out.println(ANSI_GREEN + "6.Listar Lecturas");
        System.out.println(ANSI_GREEN + "7.Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el carácter de nueva línea
        switch (opcion) {
                case 1:                    
                    break;
                case 2:
                    //Se solicita los datos para dar de alta
                    try {System.out.println(ANSI_GREEN + "Ingrese la fecha de la toma de datos, formato (yyyy-mm-dd)");
                    String fechaingresoAlta = scanner.nextLine();
                    LocalDate fechaAlta = LocalDate.parse(fechaingresoAlta);
                    System.out.println(ANSI_GREEN + "Ingrese la hora de la toma de datos, formato (HH:mm)");
                    String horaingresoAlta = scanner.nextLine();
                    LocalTime horaAlta = LocalTime.parse(horaingresoAlta);
                    System.out.println(ANSI_GREEN + "Ingrese tipo de Pluviómetro");
                    int tipo_pluviometroAlta = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese cantidad de centimetros cúbicos");
                    int gradosAlta = scanner.nextInt();
                    AgregarLectura(fechaAlta,horaAlta,tipo_pluviometroAlta,gradosAlta);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;

                case 3:
                    //Se solicita los datos para eliminar
                    try{System.out.println(ANSI_GREEN + "Ingrese la fecha de la toma de datos, formato (yyyy-mm-dd)");
                    String fechaingresoBaja = scanner.nextLine();
                    LocalDate fechaBaja = LocalDate.parse(fechaingresoBaja);
                    System.out.println(ANSI_GREEN + "Ingrese la hora de la toma de datos, formato (HH:mm)");
                    String horaingresoBaja = scanner.nextLine();
                    LocalTime horaBaja = LocalTime.parse(horaingresoBaja);
                    System.out.println(ANSI_GREEN + "Ingrese tipo de Pluviómetro");
                    int tipo_pluviometroBaja = scanner.nextInt();
                    EliminarLectura(fechaBaja,horaBaja,tipo_pluviometroBaja);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 4:
                    //Se solicita los datos para modificar
                    try{System.out.println(ANSI_GREEN + "Ingrese la fecha de la toma de datos, formato (yyyy-mm-dd)");
                    String fechaingresoModifica = scanner.nextLine();
                    LocalDate fechaModifica = LocalDate.parse(fechaingresoModifica);
                    System.out.println(ANSI_GREEN + "Ingrese la hora de la toma de datos, formato (HH:mm)");
                    String horaingresoModifica = scanner.nextLine();
                    LocalTime horaModifica = LocalTime.parse(horaingresoModifica);
                    System.out.println(ANSI_GREEN + "Ingrese tipo de Pluviómetro");
                    int tipo_pluviometroModifica = scanner.nextInt();
                    System.out.println(ANSI_GREEN + "Ingrese cantidad de centimetros cúbicos");
                    int gradosModifica = scanner.nextInt();
                    ModificarLectura(fechaModifica,horaModifica,tipo_pluviometroModifica,gradosModifica);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 5:
                    //Se solicita los datos para Consultar
                    try{System.out.println(ANSI_GREEN + "Ingrese la fecha de la toma de datos, formato (yyyy-mm-dd)");
                    String fechaingresoConsulta = scanner.nextLine();
                    LocalDate fechaConsulta = LocalDate.parse(fechaingresoConsulta);
                    System.out.println(ANSI_GREEN + "Ingrese la hora de la toma de datos, formato (HH:mm)");
                    String horaingresoConsulta = scanner.nextLine();
                    LocalTime horaConsulta = LocalTime.parse(horaingresoConsulta);
                    System.out.println(ANSI_GREEN + "Ingrese tipo de Pluviómetro");
                    int tipo_pluviometroConsulta = scanner.nextInt();
                    
                    ConsultaLectura(fechaConsulta,horaConsulta,tipo_pluviometroConsulta);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 6:
                  //Se solicita los datos para Listar
                    try{System.out.println(ANSI_GREEN + "Ingrese la fecha desde de la toma de datos, formato (yyyy-mm-dd)");
                    String fechaingresoListar1 = scanner.nextLine();
                    LocalDate fechaListar1 = LocalDate.parse(fechaingresoListar1);
                    System.out.println(ANSI_GREEN + "Ingrese la fecha hasta de la toma de datos, formato (yyyy-mm-dd)");
                    String fechaingresoListar2 = scanner.nextLine();
                    LocalDate fechaListar2 = LocalDate.parse(fechaingresoListar2);
                    System.out.println(ANSI_GREEN + "Ingrese tipo de Pluviómetro");
                    int tipo_pluviometroListar = scanner.nextInt();
                    
                    ListarLectura(fechaListar1,fechaListar2,tipo_pluviometroListar);
                    }catch(Exception e){
                    System.out.println(ANSI_GREEN + "Error al ingresar los datos. Por favor, intente de nuevo.");}
                    break;
                case 7:
                    System.out.println(ANSI_GREEN + "Saliendo del sistema...");
                    salir = true;
                    break;
                default:
                    System.out.println(ANSI_GREEN + "Opción no válida, elige nuevamente.");
            }
        } 
    }
    
    private static void AgregarLectura(LocalDate fecha,LocalTime hora,int tipo_pluviometro,int grados){
        //Combino la fecha y hora para una mejor lectura 
        LocalDateTime fechahora = LocalDateTime.of(fecha, hora);
        System.out.println(ANSI_GREEN + "Se agrego una lectura al pluviómetro " + tipo_pluviometro + " en grados " + grados + " En la Fecha " + fechahora);
    
    
    }
    private static void EliminarLectura(LocalDate fecha,LocalTime hora,int tipo_pluviometro){
        //Combino la fecha y hora para una mejor lectura 
        LocalDateTime fechahora = LocalDateTime.of(fecha, hora);
        System.out.println(ANSI_GREEN + "Se elimino una lectura al pluviómetro " + tipo_pluviometro + " En la Fecha " + fechahora);
     
    }
    private static void ModificarLectura(LocalDate fecha,LocalTime hora,int tipo_pluviometro,int grados){
        //Combino la fecha y hora para una mejor lectura 
        LocalDateTime fechahora = LocalDateTime.of(fecha, hora);
        System.out.println(ANSI_GREEN + "Se modifico una lectura al pluviómetro " + tipo_pluviometro + " en grados " + grados + " En la Fecha " + fechahora);
     
    }
    private static void ConsultaLectura(LocalDate fecha,LocalTime hora,int tipo_pluviometro){
        //Combino la fecha y hora para una mejor lectura 
        LocalDateTime fechahora = LocalDateTime.of(fecha, hora);
        System.out.println(ANSI_GREEN + "Se consulto una lectura al pluviómetro " + tipo_pluviometro  + " En la Fecha " + fechahora);
     
    }
    private static void ListarLectura(LocalDate fecha1,LocalDate fecha2,int tipo_pluviometro){
        
        System.out.println(ANSI_GREEN + "Se listo  lecturas del pluviómetro " + tipo_pluviometro  + " En la Fecha " + fecha1 +" Hasta "+ fecha2);
     
    }
    
    
//Hay codigo redundante, hay que pensarlo a futuro como un metodo private al cual pasarle parámetros
}
