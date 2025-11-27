package GestorDatosPermanentes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjustesDAO {

    private Connection getConexion() {
        return ConexionDB.getConexion();
    }

    public String[] obtenerAjustes() {
        String sql = "SELECT * FROM ajustes";
        try (PreparedStatement statement = getConexion().prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {
            while (resultado.next()) {
                return new String[]{
                    resultado.getString("lenguaje"),
                    resultado.getString("modo")
                };
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ajustes: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarAjustes(String lenguaje, String modo) {
        boolean exito = false;
        String sql = "UPDATE ajustes SET lenguaje = ?, modo = ? WHERE ajuste_id = 1";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {

            // Obtener los valores actuales de ajustes para verificar existencia
            String[] ajustes = obtenerAjustes();

            if (ajustes != null) {
                statement.setString(1, lenguaje);
                statement.setString(2, modo);

                int filasActualizadas = statement.executeUpdate();
                if (filasActualizadas > 0) {
                    exito = true;
                }
            } else {
                System.out.println("No se pudieron obtener los ajustes actuales.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar ajustes: " + e.getMessage());
        }
        return exito;
    }
}
