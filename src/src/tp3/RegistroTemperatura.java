
package tp3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RegistroTemperatura {
    private String fecha;
    private String hora;
    private String observacion;
    private double grados;
    private int usuariosLegajo;
    private int tipoTermometro;

    public RegistroTemperatura() {}

    // Captura de datos desde la consola
    public void capturarDatosRegistro(Connection con) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la fecha (YYYY-MM-DD): ");
        this.fecha = scanner.nextLine();

        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la hora (HH:MM:SS): ");
        this.hora = scanner.nextLine();

        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la observación: ");
        this.observacion = scanner.nextLine();

        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese los grados: ");
        this.grados = scanner.nextDouble();

        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el legajo del usuario: ");
        this.usuariosLegajo = scanner.nextInt();

        System.out.print(VariablesEstaticas.ANSI_GREEN +"Ingrese el tipo de termómetro: ");
        this.tipoTermometro = scanner.nextInt();

        agregarRegistro(con);
    }

    // Agregar un nuevo registro de temperatura
    public void agregarRegistro(Connection con) {
        String query = "INSERT INTO registrotemp (fecha, hora, observacion, grados, Usuarios_legajo, Termometria_tipo_termometro) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, this.fecha);
            stmt.setString(2, this.hora);
            stmt.setString(3, this.observacion);
            stmt.setDouble(4, this.grados);
            stmt.setInt(5, this.usuariosLegajo);
            stmt.setInt(6, this.tipoTermometro);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? VariablesEstaticas.ANSI_GREEN + "Registro de temperatura agregado exitosamente!" : "Error al agregar registro.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al agregar registro de temperatura: " + e.getMessage());
        }
    }

    // Listar todos los registros
    public void listarRegistros(Connection con) {
        String query = "SELECT * FROM registrotemp";

        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println(VariablesEstaticas.ANSI_GREEN + "Registros de temperatura:");
            while (rs.next()) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Fecha: " + rs.getString("fecha") +
                                   ", Hora: " + rs.getString("hora") +
                                   ", Observación: " + rs.getString("observacion") +
                                   ", Grados: " + rs.getDouble("grados") +
                                   ", Usuario Legajo: " + rs.getInt("Usuarios_legajo") +
                                   ", Tipo Termómetro: " + rs.getInt("Termometria_tipo_termometro"));
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al listar registros: " + e.getMessage());
        }
    }

    // Eliminar un registro por fecha y hora
    public void eliminarRegistro(Connection con, String fecha, String hora) {
        String query = "DELETE FROM registrotemp WHERE fecha = ? AND hora = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, fecha);
            stmt.setString(2, hora);
            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? VariablesEstaticas.ANSI_GREEN + "Registro eliminado exitosamente!" : "Registro no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al eliminar el registro: " + e.getMessage());
        }
    }

    // Actualizar un registro por fecha y hora
    public void actualizarRegistro(Connection con, String fecha, String hora) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la nueva observación: ");
        this.observacion = scanner.nextLine();

        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese los nuevos grados: ");
        this.grados = scanner.nextDouble();

        String query = "UPDATE registrotemp SET observacion = ?, grados = ? WHERE fecha = ? AND hora = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, this.observacion);
            stmt.setDouble(2, this.grados);
            stmt.setString(3, fecha);
            stmt.setString(4, hora);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? VariablesEstaticas.ANSI_GREEN + "Registro actualizado exitosamente!" : "Registro no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al actualizar el registro: " + e.getMessage());
        }
    }

    // Consultar un registro por fecha y hora
    public void consultarRegistro(Connection con, String fecha, String hora) {
        String query = "SELECT * FROM registrotemp WHERE fecha = ? AND hora = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, fecha);
            stmt.setString(2, hora);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Registro encontrado:");
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Fecha: " + rs.getString("fecha"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Hora: " + rs.getString("hora"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Observación: " + rs.getString("observacion"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Grados: " + rs.getDouble("grados"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Usuario Legajo: " + rs.getInt("Usuarios_legajo"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo Termómetro: " + rs.getInt("Termometria_tipo_termometro"));
                } else {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Registro no encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al consultar el registro: " + e.getMessage());
        }
    }

    // Menú de opciones para la gestión de registros
    public void menuRegistroTemperatura() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        try (Connection con = ConexionBD.obtenerConexion()) {
            do {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "===== Gestión de Registros de Temperatura =====");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "1. Agregar registro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "2. Listar registros");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "3. Eliminar registro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "4. Actualizar registro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "5. Consultar registro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "6. Gestionar termómetro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "7. Salir"); 
                System.out.print(VariablesEstaticas.ANSI_GREEN + "Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        capturarDatosRegistro(con);
                        break;
                    case 2:
                        listarRegistros(con);
                        break;
                    case 3:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la fecha del registro a eliminar: ");
                        String fechaEliminar = scanner.next();
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la hora del registro a eliminar: ");
                        String horaEliminar = scanner.next();
                        eliminarRegistro(con, fechaEliminar, horaEliminar);
                        break;
                    case 4:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la fecha del registro a actualizar: ");
                        String fechaActualizar = scanner.next();
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la hora del registro a actualizar: ");
                        String horaActualizar = scanner.next();
                        actualizarRegistro(con, fechaActualizar, horaActualizar);
                        break;
                    case 5:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la fecha del registro a consultar: ");
                        String fechaConsultar = scanner.next();
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la hora del registro a consultar: ");
                        String horaConsultar = scanner.next();
                        consultarRegistro(con, fechaConsultar, horaConsultar);
                        break;
                    case 6:
                        Termometria termometria = new Termometria();
                        termometria.menuTermometria();
                        break;
                    case 7:
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Saliendo del menú de registros de temperatura...");
                        break;
                    default:
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Opción no válida.");
                        break;
                }
            } while (opcion != 7);
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error en la conexión con la base de datos: " + e.getMessage());
        }
    }
}
