package tp3;
//Traigo las librerias que necesito
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Usuario {
    protected int tipo_usuario;
    protected int legajo;
    protected String nom_usuario;
    protected String pass;
    protected String Email;

    public Usuario() {}
//Constructores
    public Usuario(int tipo_usuario, int legajo, String nom_usuario, String pass, String Email) {
        this.tipo_usuario = tipo_usuario;
        this.legajo = legajo;
        this.nom_usuario = nom_usuario;
        this.pass = pass;
        this.Email = Email;
    }

    // Método para capturar ingreso por pantalla
    public void capturarDatosUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el legajo: ");
        this.legajo = scanner.nextInt();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de usuario (1| Para Administrador - 2| Para Observador): ");
        this.tipo_usuario = scanner.nextInt();
        scanner.nextLine(); // Limpiamos el buffer
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el nombre de usuario: ");
        this.nom_usuario = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el email: ");
        this.Email = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la contraseña: ");
        this.pass = scanner.nextLine();
    }

    // Método para agregar usuario
    public void agregarUsuario(Connection con) {
        String query = "INSERT INTO usuarios (legajo, tipo_usuario, nom_usuario, email, password) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, this.legajo);
            stmt.setInt(2, this.tipo_usuario);
            stmt.setString(3, this.nom_usuario);
            stmt.setString(4, this.Email);
            stmt.setString(5, this.pass);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Usuario agregado exitosamente!");
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al agregar usuario: " + e.getMessage());
        }
    }

    // Método para actualizar usuario
    public void actualizarUsuario(Connection con) {
        String query = "UPDATE usuarios SET tipo_usuario = ?, nom_usuario = ?, email = ?, password = ? WHERE legajo = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, this.tipo_usuario);
            stmt.setString(2, this.nom_usuario);
            stmt.setString(3, this.Email);
            stmt.setString(4, this.pass);
            stmt.setInt(5, this.legajo);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Usuario actualizado exitosamente!");
            } else {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "No se encontró el usuario con el legajo especificado.");
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al actualizar usuario: " + e.getMessage());
        }
    }

    // Método para eliminar usuario
    public void eliminarUsuario(Connection con, int legajo) {
        String query = "DELETE FROM usuarios WHERE legajo = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, legajo);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Usuario eliminado exitosamente!");
            } else {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "No se encontró el usuario con el legajo especificado.");
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al eliminar usuario: " + e.getMessage());
        }
    }

    // Método para listar usuarios
    public void listarUsuarios(Connection con) {
        String query = "SELECT * FROM usuarios";
        
        try (PreparedStatement stmt = con.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Listado de usuarios:");
            while (rs.next()) {
                System.out.println(VariablesEstaticas.ANSI_GREEN +
                    "Legajo: " + rs.getInt("legajo") +
                    ", Tipo Usuario: " + rs.getInt("tipo_usuario") +
                    ", Nombre: " + rs.getString("nom_usuario") +
                    ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al listar usuarios: " + e.getMessage());
        }
    }

    // Método para consultar usuario
    public void consultarUsuario(Connection con, int legajo) {
        String query = "SELECT * FROM usuarios WHERE legajo = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, legajo);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Usuario encontrado:");
                    System.out.println(VariablesEstaticas.ANSI_GREEN +
                        "Legajo: " + rs.getInt("legajo") +
                        ", Tipo Usuario: " + rs.getInt("tipo_usuario") +
                        ", Nombre: " + rs.getString("nom_usuario") +
                        ", Email: " + rs.getString("email"));
                } else {
                    System.out.println(VariablesEstaticas.ANSI_GREEN +
                        "No se encontró el usuario con el legajo especificado.");
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN +
                "Error al consultar usuario: " + e.getMessage());
        }
    }

    // Método para crear un menú
    public void menuUsuario() {
        Scanner scanner = new Scanner(System.in);
        
        // Abrir conexión una sola vez
        try (Connection con = ConexionBD.obtenerConexion()) { 
            while (true) {
                Usuario usuarioActual = new Usuario(); // Crear un nuevo objeto Usuario en cada iteración
                System.out.println("\n" + VariablesEstaticas.ANSI_GREEN + "===== Menú de Usuario =====");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "1. Agregar Usuario");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "2. Actualizar Usuario");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "3. Eliminar Usuario");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "4. Listar Usuarios");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "5. Consultar Usuario");
                System.out.println(VariablesEstaticas.ANSI_GREEN + "6. Salir");

                System.out.print(VariablesEstaticas.ANSI_GREEN + "Seleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        usuarioActual.capturarDatosUsuario();
                        usuarioActual.agregarUsuario(con); // Pasar conexión
                        break;
                    case 2:
                        System.out.print(VariablesEstaticas.ANSI_GREEN +
                            "Ingrese el legajo del usuario a actualizar: ");
                        usuarioActual.legajo = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer
                        usuarioActual.capturarDatosUsuario();
                        usuarioActual.actualizarUsuario(con); // Pasar conexión
                        break;
                    case 3:
                        System.out.print(VariablesEstaticas.ANSI_GREEN +
                            "Ingrese el legajo del usuario a eliminar: ");
                        int legajoEliminar = scanner.nextInt();
                        usuarioActual.eliminarUsuario(con, legajoEliminar); // Pasar conexión
                        break;
                    case 4:
                        usuarioActual.listarUsuarios(con); // Pasar conexión
                        break;
                    case 5:
                        System.out.print(VariablesEstaticas.ANSI_GREEN +
                            "Ingrese el legajo del usuario a consultar: ");
                        int legajoConsultar = scanner.nextInt();
                        usuarioActual.consultarUsuario(con, legajoConsultar); // Pasar conexión
                        break;
                    case 6:
                        System.out.println(VariablesEstaticas.ANSI_GREEN+ 
                            "¡Hasta luego!");
                        return; // Salir del menú y cerrar la conexión automáticamente aquí
                    default:
                        System.out.println(VariablesEstaticas.ANSI_GREEN +
                            "Opción inválida. Intente nuevamente.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos: " + e.getMessage());
        }
    }
}
