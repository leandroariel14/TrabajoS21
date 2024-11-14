
package tp3;

// Importo las librerias a usar
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Termometria {
    private int tipoTermometro;
    private double totalGrados;
    private String descripcion;

    public Termometria() {}

    // Captura de datos desde la consola
    public void capturarDatosTermometro(Connection con) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de term�metro: ");
        this.tipoTermometro = scanner.nextInt();
        scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la descripci�n del term�metro: ");
        this.descripcion = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el total de grados: ");
        this.totalGrados = scanner.nextDouble();
        agregarTermometro(con);
    }

    // Agregar un nuevo term�metro
    public void agregarTermometro(Connection con) {
        String query = "INSERT INTO termometria (tipo_termometro, total_grados, descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, this.tipoTermometro);
            stmt.setDouble(2, this.totalGrados);
            stmt.setString(3, this.descripcion);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? VariablesEstaticas.ANSI_GREEN + "Term�metro agregado exitosamente!" : "Error al agregar term�metro.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al agregar term�metro: " + e.getMessage());
        }
    }

    // Listar todos los term�metros
    public void listarTermometros(Connection con) {
        String query = "SELECT * FROM termometria";

        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println(VariablesEstaticas.ANSI_GREEN + "Listado de term�metros:");
            while (rs.next()) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo: " + rs.getInt("tipo_termometro") +
                                   ", Total Grados: " + rs.getDouble("total_grados") +
                                   ", Descripci�n: " + rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al listar term�metros: " + e.getMessage());
        }
    }

    // Eliminar un term�metro
    public void eliminarTermometro(int tipoTermometro, Connection con) {
        String query = "DELETE FROM termometria WHERE tipo_termometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, tipoTermometro);
            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? VariablesEstaticas.ANSI_GREEN + "Term�metro eliminado exitosamente!" : "Term�metro no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al eliminar el term�metro: " + e.getMessage());
        }
    }

    // Actualizar un term�metro
    public void actualizarTermometro(int tipoTermometro, Connection con) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la nueva descripci�n: ");
        this.descripcion = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el nuevo total de grados: ");
        this.totalGrados = scanner.nextDouble();

        String query = "UPDATE termometria SET descripcion = ?, total_grados = ? WHERE tipo_termometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, this.descripcion);
            stmt.setDouble(2, this.totalGrados);
            stmt.setInt(3, tipoTermometro);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? VariablesEstaticas.ANSI_GREEN + "Term�metro actualizado exitosamente!" : "Term�metro no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al actualizar el term�metro: " + e.getMessage());
        }
    }

    // Consultar Term�metro
    public void consultarTermometro(int tipoTermometro, Connection con) {
        String query = "SELECT * FROM termometria WHERE tipo_termometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, tipoTermometro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Term�metro encontrado:");
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo: " + rs.getInt("tipo_termometro"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Total Grados: " + rs.getDouble("total_grados"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Descripci�n: " + rs.getString("descripcion"));
                } else {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Term�metro no encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al consultar el term�metro: " + e.getMessage());
        }
    }

    // Men� de opciones para la gesti�n de term�metros
    public void menuTermometria() {
        try (Connection con = ConexionBD.obtenerConexion()) {  // Conexi�n establecida aqu�, cerrada autom�ticamente al salir del bloque
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "===== Gesti�n de Term�metros =====");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "1. Agregar term�metro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "2. Eliminar term�metro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "3. Actualizar term�metro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "4. Consultar term�metro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "5. Listar term�metros");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "6. Salir");
                System.out.print(VariablesEstaticas.ANSI_GREEN + "Seleccione una opci�n: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        capturarDatosTermometro(con);
                        break;                
                    case 2:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de term�metro a eliminar: ");
                        int tipoEliminar = scanner.nextInt();
                        eliminarTermometro(tipoEliminar, con);
                        break;
                    case 3:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de term�metro a actualizar: ");
                        int tipoActualizar = scanner.nextInt();
                        actualizarTermometro(tipoActualizar, con);
                        break;
                    case 4:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de term�metro a consultar: ");
                        int tipoConsulta = scanner.nextInt();
                        consultarTermometro(tipoConsulta, con);
                        break;       
                    case 5:
                        listarTermometros(con);
                        break;   
                    case 6:
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Saliendo...");
                        break;
                    default:
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Opci�n inv�lida. Intente de nuevo.");
                }
            } while (opcion != 6);

        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error en la conexi�n a la base de datos: " + e.getMessage());
        }
    }
}
