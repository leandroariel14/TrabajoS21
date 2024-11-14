
package tp3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/srda.mydb";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "Arielsep201422@";

    private static Connection conexion = null;

    public static Connection obtenerConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                // System.out.println("Conexión exitosa a la base de datos");
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos");
                e.printStackTrace();
            }
        }
        return conexion;
    }

    public static void cerrarConexion() { // Cambié 'close' por 'cerrarConexion'
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexion = null; 
                }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
        }
    }
}
