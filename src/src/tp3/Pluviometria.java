package tp3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Pluviometria {
    private int tipoPluviometro;
    private double capacidad;
    private String descripcion;

    public Pluviometria() {}

    // Captura de datos desde la consola
    public void capturarDatosPluviometro(Connection con) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluvi�metro: ");
        this.tipoPluviometro = scanner.nextInt();
        scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la descripci�n del pluvi�metro: ");
        this.descripcion = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la capacidad del pluvi�metro: ");
        this.capacidad = scanner.nextDouble();
        agregarPluviometro(con);
    }

    // Agregar un nuevo pluvi�metro
    public void agregarPluviometro(Connection con) {
        String query = "INSERT INTO pluviometria (tipo_pluviometro, capacidad, descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, this.tipoPluviometro);
            stmt.setDouble(2, this.capacidad);
            stmt.setString(3, this.descripcion);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? VariablesEstaticas.ANSI_GREEN + "Pluvi�metro agregado exitosamente!" : "Error al agregar pluvi�metro.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al agregar pluvi�metro: " + e.getMessage());
        }
    }

    // Listar todos los pluvi�metros
    public void listarPluviometros(Connection con) {
        String query = "SELECT * FROM pluviometria";

        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println(VariablesEstaticas.ANSI_GREEN + "Listado de pluvi�metros:");
            while (rs.next()) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo: " + rs.getInt("tipo_pluviometro") +
                                   ", Capacidad: " + rs.getDouble("capacidad") +
                                   ", Descripci�n: " + rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al listar pluvi�metros: " + e.getMessage());
        }
    }

    // Eliminar un pluvi�metro
    public void eliminarPluviometro(int tipoPluviometro, Connection con) {
        String query = "DELETE FROM pluviometria WHERE tipo_pluviometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, tipoPluviometro);
            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? VariablesEstaticas.ANSI_GREEN + "Pluvi�metro eliminado exitosamente!" : "Pluvi�metro no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al eliminar el pluvi�metro: " + e.getMessage());
        }
    }

    // Actualizar un pluvi�metro
    public void actualizarPluviometro(int tipoPluviometro, Connection con) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la nueva descripci�n: ");
        this.descripcion = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la nueva capacidad: ");
        this.capacidad = scanner.nextDouble();

        String query = "UPDATE pluviometria SET descripcion = ?, capacidad = ? WHERE tipo_pluviometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, this.descripcion);
            stmt.setDouble(2, this.capacidad);
            stmt.setInt(3, tipoPluviometro);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? VariablesEstaticas.ANSI_GREEN + "Pluvi�metro actualizado exitosamente!" : "Pluvi�metro no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al actualizar el pluvi�metro: " + e.getMessage());
        }
    }

    // Consultar un pluvi�metro espec�fico
    public void consultarPluviometro(int tipoPluviometro, Connection con) {
        String query = "SELECT * FROM pluviometria WHERE tipo_pluviometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, tipoPluviometro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Pluvi�metro encontrado:");
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo: " + rs.getInt("tipo_pluviometro"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Capacidad: " + rs.getDouble("capacidad"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Descripci�n: " + rs.getString("descripcion"));
                } else {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Pluvi�metro no encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al consultar el pluvi�metro: " + e.getMessage());
        }
    }

    // Men� de opciones para la gesti�n de pluvi�metros
    public void menuPluviometria() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Establecer una sola conexi�n para todas las operaciones
        try (Connection con = ConexionBD.obtenerConexion()) {
            do {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "===== Gesti�n de Pluvi�metros =====");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "1. Agregar pluvi�metro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "2. Eliminar pluvi�metro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "3. Actualizar pluvi�metro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "4. Consultar pluvi�metro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "5. Listar pluvi�metros");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "6. Salir");
                System.out.print(VariablesEstaticas.ANSI_GREEN + "Seleccione una opci�n: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        capturarDatosPluviometro(con);
                        break;
                    case 2:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluvi�metro a eliminar: ");
                        int tipoEliminar = scanner.nextInt();
                        eliminarPluviometro(tipoEliminar, con);
                        break;
                    case 3:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluvi�metro a actualizar: ");
                        int tipoActualizar = scanner.nextInt();
                        actualizarPluviometro(tipoActualizar, con);
                        break;
                    case 4:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluvi�metro a consultar: ");
                        int tipoConsulta = scanner.nextInt();
                        consultarPluviometro(tipoConsulta, con);
                        break;
                    case 5:
                        listarPluviometros(con);
                        break;
                    case 6:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Opci�n no v�lida.");
                }
            } while (opcion != 6);
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error en la conexi�n con la base de datos: " + e.getMessage());
        }
    }
}
