package GestorDatosPermanentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static Connection conexion = null;
    private static final String NOMBRE_DB = "baseDeDatos.db";

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection("jdbc:sqlite:" + NOMBRE_DB);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
             System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
