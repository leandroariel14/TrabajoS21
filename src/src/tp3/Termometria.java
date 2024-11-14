
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
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de termómetro: ");
        this.tipoTermometro = scanner.nextInt();
        scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la descripción del termómetro: ");
        this.descripcion = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el total de grados: ");
        this.totalGrados = scanner.nextDouble();
        agregarTermometro(con);
    }

    // Agregar un nuevo termómetro
    public void agregarTermometro(Connection con) {
        String query = "INSERT INTO termometria (tipo_termometro, total_grados, descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, this.tipoTermometro);
            stmt.setDouble(2, this.totalGrados);
            stmt.setString(3, this.descripcion);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? VariablesEstaticas.ANSI_GREEN + "Termómetro agregado exitosamente!" : "Error al agregar termómetro.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al agregar termómetro: " + e.getMessage());
        }
    }

    // Listar todos los termómetros
    public void listarTermometros(Connection con) {
        String query = "SELECT * FROM termometria";

        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println(VariablesEstaticas.ANSI_GREEN + "Listado de termómetros:");
            while (rs.next()) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo: " + rs.getInt("tipo_termometro") +
                                   ", Total Grados: " + rs.getDouble("total_grados") +
                                   ", Descripción: " + rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al listar termómetros: " + e.getMessage());
        }
    }

    // Eliminar un termómetro
    public void eliminarTermometro(int tipoTermometro, Connection con) {
        String query = "DELETE FROM termometria WHERE tipo_termometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, tipoTermometro);
            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? VariablesEstaticas.ANSI_GREEN + "Termómetro eliminado exitosamente!" : "Termómetro no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al eliminar el termómetro: " + e.getMessage());
        }
    }

    // Actualizar un termómetro
    public void actualizarTermometro(int tipoTermometro, Connection con) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la nueva descripción: ");
        this.descripcion = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el nuevo total de grados: ");
        this.totalGrados = scanner.nextDouble();

        String query = "UPDATE termometria SET descripcion = ?, total_grados = ? WHERE tipo_termometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, this.descripcion);
            stmt.setDouble(2, this.totalGrados);
            stmt.setInt(3, tipoTermometro);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? VariablesEstaticas.ANSI_GREEN + "Termómetro actualizado exitosamente!" : "Termómetro no encontrado.");
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al actualizar el termómetro: " + e.getMessage());
        }
    }

    // Consultar Termómetro
    public void consultarTermometro(int tipoTermometro, Connection con) {
        String query = "SELECT * FROM termometria WHERE tipo_termometro = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, tipoTermometro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Termómetro encontrado:");
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo: " + rs.getInt("tipo_termometro"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Total Grados: " + rs.getDouble("total_grados"));
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Descripción: " + rs.getString("descripcion"));
                } else {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Termómetro no encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al consultar el termómetro: " + e.getMessage());
        }
    }

    // Menú de opciones para la gestión de termómetros
    public void menuTermometria() {
        try (Connection con = ConexionBD.obtenerConexion()) {  // Conexión establecida aquí, cerrada automáticamente al salir del bloque
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "===== Gestión de Termómetros =====");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "1. Agregar termómetro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "2. Eliminar termómetro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "3. Actualizar termómetro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "4. Consultar termómetro");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "5. Listar termómetros");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "6. Salir");
                System.out.print(VariablesEstaticas.ANSI_GREEN + "Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        capturarDatosTermometro(con);
                        break;                
                    case 2:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de termómetro a eliminar: ");
                        int tipoEliminar = scanner.nextInt();
                        eliminarTermometro(tipoEliminar, con);
                        break;
                    case 3:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de termómetro a actualizar: ");
                        int tipoActualizar = scanner.nextInt();
                        actualizarTermometro(tipoActualizar, con);
                        break;
                    case 4:
                        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de termómetro a consultar: ");
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
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Opción inválida. Intente de nuevo.");
                }
            } while (opcion != 6);

        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error en la conexión a la base de datos: " + e.getMessage());
        }
    }
}
