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
    public void capturarDatosPluviometro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluviómetro: ");
        this.tipoPluviometro = scanner.nextInt();
        scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la descripción del pluviómetro: ");
        this.descripcion = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la capacidad del pluviómetro: ");
        this.capacidad = scanner.nextDouble();
        agregarPluviometro();
    }

    // Agregar un nuevo pluviómetro
    public void agregarPluviometro() {
        String query = "INSERT INTO pluviometria (tipo_pluviometro, capacidad, descripcion) VALUES (?, ?, ?)";
        Connection con = null;

        try {
            con = ConexionBD.obtenerConexion();  // Obtener la conexión

            // Usamos try-with-resources para manejar el PreparedStatement
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, this.tipoPluviometro);
                stmt.setDouble(2, this.capacidad);
                stmt.setString(3, this.descripcion);

                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? VariablesEstaticas.ANSI_GREEN + "Pluviómetro agregado exitosamente!" : "Error al agregar pluviómetro.");
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al agregar pluviómetro: " + e.getMessage());
        } finally {
            // Asegurarse de cerrar la conexión al final
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    // Listar todos los pluviómetros
    public void listarPluviometros() {
        String query = "SELECT * FROM pluviometria";
        Connection con = null;

        try {
            con = ConexionBD.obtenerConexion();  // Obtener la conexión

            try (PreparedStatement stmt = con.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                System.out.println(VariablesEstaticas.ANSI_GREEN + "Listado de pluviómetros:");
                while (rs.next()) {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo: " + rs.getInt("tipo_pluviometro") +
                                       ", Capacidad: " + rs.getDouble("capacidad") +
                                       ", Descripción: " + rs.getString("descripcion"));
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al listar pluviómetros: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    // Eliminar un pluviómetro
    public void eliminarPluviometro(int tipoPluviometro) {
        String query = "DELETE FROM pluviometria WHERE tipo_pluviometro = ?";
        Connection con = null;

        try {
            con = ConexionBD.obtenerConexion();  // Obtener la conexión

            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, tipoPluviometro);
                int rowsDeleted = stmt.executeUpdate();
                System.out.println(rowsDeleted > 0 ? VariablesEstaticas.ANSI_GREEN + "Pluviómetro eliminado exitosamente!" : "Pluviómetro no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al eliminar el pluviómetro: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    // Actualizar un pluviómetro
    public void actualizarPluviometro(int tipoPluviometro) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la nueva descripción: ");
        this.descripcion = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la nueva capacidad: ");
        this.capacidad = scanner.nextDouble();

        String query = "UPDATE pluviometria SET descripcion = ?, capacidad = ? WHERE tipo_pluviometro = ?";
        Connection con = null;

        try {
            con = ConexionBD.obtenerConexion();  // Obtener la conexión

            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, this.descripcion);
                stmt.setDouble(2, this.capacidad);
                stmt.setInt(3, tipoPluviometro);

                int rowsUpdated = stmt.executeUpdate();
                System.out.println(rowsUpdated > 0 ? VariablesEstaticas.ANSI_GREEN + "Pluviómetro actualizado exitosamente!" : "Pluviómetro no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al actualizar el pluviómetro: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    // Consultar un pluviómetro específico
    public void consultarPluviometro(int tipoPluviometro) {
        String query = "SELECT * FROM pluviometria WHERE tipo_pluviometro = ?";
        Connection con = null;

        try {
            con = ConexionBD.obtenerConexion();  // Obtener la conexión

            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, tipoPluviometro);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Pluviómetro encontrado:");
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Tipo: " + rs.getInt("tipo_pluviometro"));
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Capacidad: " + rs.getDouble("capacidad"));
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Descripción: " + rs.getString("descripcion"));
                    } else {
                        System.out.println(VariablesEstaticas.ANSI_GREEN + "Pluviómetro no encontrado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al consultar el pluviómetro: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    // Menú de opciones para la gestión de pluviómetros
    public void menuPluviometria() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "===== Gestión de Pluviómetros =====");
            System.out.println(VariablesEstaticas.ANSI_GREEN + "1. Agregar pluviómetro");
            System.out.println(VariablesEstaticas.ANSI_GREEN + "2. Eliminar pluviómetro");
            System.out.println(VariablesEstaticas.ANSI_GREEN + "3. Actualizar pluviómetro");
            System.out.println(VariablesEstaticas.ANSI_GREEN + "4. Consultar pluviómetro");
            System.out.println(VariablesEstaticas.ANSI_GREEN + "5. Listar pluviómetros");
            System.out.println(VariablesEstaticas.ANSI_GREEN + "6. Salir");
            System.out.print(VariablesEstaticas.ANSI_GREEN + "Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    capturarDatosPluviometro();
                    break;
                case 2:
                    System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluviómetro a eliminar: ");
                    int tipoEliminar = scanner.nextInt();
                    eliminarPluviometro(tipoEliminar);
                    break;
                case 3:
                    System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluviómetro a actualizar: ");
                    int tipoActualizar = scanner.nextInt();
                    actualizarPluviometro(tipoActualizar);
                    break;
                case 4:
                    System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de pluviómetro a consultar: ");
                    int tipoConsulta = scanner.nextInt();
                    consultarPluviometro(tipoConsulta);
                    break;
                case 5:
                    listarPluviometros();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Opción no válida.");
            }
        } while (opcion != 6);
    }
}
