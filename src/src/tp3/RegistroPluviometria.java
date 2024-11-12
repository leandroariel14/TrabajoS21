package tp3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RegistroPluviometria {
    private String fecha;
    private String hora;
    private double volumen;//Tuve que modificar la base de datos con alter table
    private String registroPluviometriaCol;
    private int usuariosLegajo;
    private int pluviometriaTipoPluviometro;

    // Constructor vacío, por defecto
    public RegistroPluviometria() {}

    // Método para capturar datos de un registro de pluviometría
    public void capturarDatosRegistroPluviometria() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la fecha (AAAA-MM-DD): ");
        this.fecha = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la hora (HH:MM): ");
        this.hora = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el volumen de agua colectado (en litros): ");
        this.volumen = scanner.nextDouble();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el legajo del usuario que realiza el registro: ");
        this.usuariosLegajo = scanner.nextInt();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluviómetro: ");
        this.pluviometriaTipoPluviometro = scanner.nextInt();
        scanner.nextLine(); // Limpio el buffer
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el observación de pluviometría: ");
        this.registroPluviometriaCol = scanner.nextLine();

        agregarRegistroPluviometria();
    }

    // Método para agregar un nuevo registro de pluviometría
    public void agregarRegistroPluviometria() {
        String query = "INSERT INTO registropluviometria (fecha, hora, volumen, RegistroPluviometriaCol, Usuarios_legajo, Pluviometria_tipo_pluviometro) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, this.fecha);
            stmt.setString(2, this.hora);
            stmt.setDouble(3, this.volumen);
            stmt.setString(4, this.registroPluviometriaCol);
            stmt.setInt(5, this.usuariosLegajo);
            stmt.setInt(6, this.pluviometriaTipoPluviometro);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? VariablesEstaticas.ANSI_GREEN + "Registro de pluviometría agregado exitosamente!" : "Error al agregar el registro.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al agregar el registro de pluviometría: " + e.getMessage());
        }
    }

    // Método para listar todos los registros de pluviometría
    public void listarRegistrosPluviometria() {
        String query = "SELECT * FROM registropluviometria";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println(VariablesEstaticas.ANSI_GREEN + "Listado de registros de pluviometría:");
            while (rs.next()) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Fecha: " + rs.getString("fecha") +
                                   ", Hora: " + rs.getString("hora") +
                                   ", Volumen: " + rs.getDouble("volumen") +
                                   ", Código de Registro: " + rs.getInt("RegistroPluviometriaCol") +
                                   ", Legajo de Usuario: " + rs.getInt("Usuarios_legajo") +
                                   ", Tipo de Pluviómetro: " + rs.getInt("Pluviometria_tipo_pluviometro"));
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al listar los registros de pluviometría: " + e.getMessage());
        }
    }

    // Método para eliminar un registro de pluviometría por su fecha y hora
    public void eliminarRegistroPluviometria(String fecha, String hora) {
        String query = "DELETE FROM registropluviometria WHERE fecha = ? AND hora = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, fecha);
            stmt.setString(2, hora);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? VariablesEstaticas.ANSI_GREEN + "Registro de pluviometría eliminado exitosamente!" : "Registro de pluviometría no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al eliminar el registro de pluviometría: " + e.getMessage());
        }
    }

    // Método para actualizar un registro de pluviometría
    public void actualizarRegistroPluviometria(String fecha, String hora) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el nuevo volumen de agua colectado: ");
        this.volumen = scanner.nextDouble();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el nuevo tipo de pluviómetro: ");
        this.pluviometriaTipoPluviometro = scanner.nextInt();
        scanner.nextLine(); // Limpio el buffer
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el nuevo código de registro: ");
        this.registroPluviometriaCol = scanner.nextLine();
        String query = "UPDATE registropluviometria SET volumen = ?, RegistroPluviometriaCol = ?, Pluviometria_tipo_pluviometro = ? WHERE fecha = ? AND hora = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setDouble(1, this.volumen);
            stmt.setString(2, this.registroPluviometriaCol);
            stmt.setInt(3, this.pluviometriaTipoPluviometro);
            stmt.setString(4, fecha);
            stmt.setString(5, hora);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? VariablesEstaticas.ANSI_GREEN + "Registro de pluviometría actualizado exitosamente!" : "Registro de pluviometría no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al actualizar el registro de pluviometría: " + e.getMessage());
        }
    }

    // Método para consultar un registro de pluviometría por fecha y hora
    public void consultarRegistroPluviometria(String fecha, String hora) {
        String query = "SELECT * FROM registropluviometria WHERE fecha = ? AND hora = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, fecha);
            stmt.setString(2, hora);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Registro encontrado:");
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Fecha: " + rs.getString("fecha"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Hora: " + rs.getString("hora"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Volumen: " + rs.getDouble("volumen"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Código de Registro: " + rs.getInt("RegistroPluviometriaCol"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Legajo de Usuario: " + rs.getInt("Usuarios_legajo"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo de Pluviómetro: " + rs.getInt("Pluviometria_tipo_pluviometro"));
                } else {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Registro de pluviometría no encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al consultar el registro de pluviometría: " + e.getMessage());
        }
    }

    // Menú de opciones para gestionar los registros de pluviometría
    public void menuRegistroPluviometria() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
    System.out.println(VariablesEstaticas.ANSI_GREEN + "===== Gestión de Registros de Pluviometría =====");
    System.out.println(VariablesEstaticas.ANSI_GREEN + "1. Agregar registro");
    System.out.println(VariablesEstaticas.ANSI_GREEN + "2. Eliminar registro");
    System.out.println(VariablesEstaticas.ANSI_GREEN + "3. Actualizar registro");
    System.out.println(VariablesEstaticas.ANSI_GREEN + "4. Consultar registro");
    System.out.println(VariablesEstaticas.ANSI_GREEN + "5. Listar todos los registros");
    System.out.println(VariablesEstaticas.ANSI_GREEN + "6. Gestionar Pluviómetro");
    System.out.println(VariablesEstaticas.ANSI_GREEN + "7. Salir");
    System.out.print(VariablesEstaticas.ANSI_GREEN + "Seleccione una opción: ");
    opcion = scanner.nextInt();

    switch (opcion) {
        case 1:
            capturarDatosRegistroPluviometria();
            break;
        case 2:
            scanner.nextLine(); // Consumir salto de línea
            System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la fecha del registro a eliminar (AAAA-MM-DD): ");
            String fechaEliminar = scanner.nextLine();
            System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la hora del registro a eliminar (HH:MM): ");
            String horaEliminar = scanner.nextLine();
            eliminarRegistroPluviometria(fechaEliminar, horaEliminar);
            break;
        case 3:
            scanner.nextLine(); // Consumir salto de línea
            System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la fecha del registro a actualizar (AAAA-MM-DD): ");
            String fechaActualizar = scanner.nextLine();
            System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la hora del registro a actualizar (HH:MM): ");
            String horaActualizar = scanner.nextLine();
            actualizarRegistroPluviometria(fechaActualizar, horaActualizar);
            break;
        case 4:
            scanner.nextLine(); // Consumir salto de línea
            System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la fecha del registro a consultar (AAAA-MM-DD): ");
            String fechaConsultar = scanner.nextLine();
            System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la hora del registro a consultar (HH:MM): ");
            String horaConsultar = scanner.nextLine();
            consultarRegistroPluviometria(fechaConsultar, horaConsultar);
            break;
        case 5:
            listarRegistrosPluviometria();
            break;
        case 6:
            Pluviometria plu = new Pluviometria();
            plu.menuPluviometria();
            break;
        case 7:
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Saliendo...");
            break;
        default:
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Opción no válida.");
            break;
    }
} while (opcion != 7);
    }
}
