
package tp3;

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

    public Usuario(int tipo_usuario, int legajo, String nom_usuario, String pass, String Email) {
        this.tipo_usuario = tipo_usuario;
        this.legajo = legajo;
        this.nom_usuario = nom_usuario;
        this.pass = pass;
        this.Email = Email;
    }

    // Métodos Getters y Setters
    
    public void capturarDatosUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el legajo: ");
        this.legajo = scanner.nextInt();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el tipo de usuario: (1| Para Administrador; 2| Para Observador) ");
        this.tipo_usuario = scanner.nextInt();
        scanner.nextLine();  // Limpiamos el buffer
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el nombre de usuario: ");
        this.nom_usuario = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el email: ");
        this.Email = scanner.nextLine();
        System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese la contraseña: ");
        this.pass = scanner.nextLine();
    }

    public void agregarUsuario() {
        ConexionBD conexionBD = new ConexionBD();
        String query = "INSERT INTO usuarios (legajo, tipo_usuario, nom_usuario, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

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

    public void actualizarUsuario() {
        ConexionBD conexionBD = new ConexionBD();
        String query = "UPDATE usuarios SET tipo_usuario = ?, nom_usuario = ?, email = ?, password = ? WHERE legajo = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

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

    public void eliminarUsuario(int legajo) {
        ConexionBD conexionBD = new ConexionBD();
        String query = "DELETE FROM usuarios WHERE legajo = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

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

    public void listarUsuarios() {
        ConexionBD conexionBD = new ConexionBD();
        String query = "SELECT * FROM usuarios";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println(VariablesEstaticas.ANSI_GREEN + "Listado de usuarios:");
            while (rs.next()) {
                System.out.println(VariablesEstaticas.ANSI_GREEN + "Legajo: " + rs.getInt("legajo") + ", Tipo Usuario: " + rs.getInt("tipo_usuario") +
                        ", Nombre: " + rs.getString("nom_usuario") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al listar usuarios: " + e.getMessage());
        }
    }

    public void consultarUsuario(int legajo) {
        ConexionBD conexionBD = new ConexionBD();
        String query = "SELECT * FROM usuarios WHERE legajo = ?";

        try (Connection con = conexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, legajo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Usuario encontrado: ");
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Legajo: " + rs.getInt("legajo") + ", Tipo Usuario: " + rs.getInt("tipo_usuario") +
                            ", Nombre: " + rs.getString("nom_usuario") + ", Email: " + rs.getString("email"));
                } else {
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "No se encontró el usuario con el legajo especificado.");
                }
            }
        } catch (SQLException e) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "Error al consultar usuario: " + e.getMessage());
        }
    }

    public void menuUsuario() {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario();

        while (true) {
            System.out.println(VariablesEstaticas.ANSI_GREEN + "\n" + VariablesEstaticas.ANSI_GREEN + "===== Menú de Usuario =====");
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
                    usuario.capturarDatosUsuario();
                    usuario.agregarUsuario();
                    break;
                case 2:
                    System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el legajo del usuario a actualizar: ");
                    usuario.legajo = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer
                    usuario.capturarDatosUsuario();
                    usuario.actualizarUsuario();
                    break;
                case 3:
                    System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el legajo del usuario a eliminar: ");
                    int legajoEliminar = scanner.nextInt();
                    usuario.eliminarUsuario(legajoEliminar);
                    break;
                case 4:
                    usuario.listarUsuarios();
                    break;
                case 5:
                    System.out.print(VariablesEstaticas.ANSI_GREEN + "Ingrese el legajo del usuario a consultar: ");
                    int legajoConsultar = scanner.nextInt();
                    usuario.consultarUsuario(legajoConsultar);
                    break;
                case 6:
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Saliendo...");
                    return;
                default:
                    System.out.println(VariablesEstaticas.ANSI_GREEN + "Opción no válida. Intente nuevamente.");
            }
        }
    }
}
