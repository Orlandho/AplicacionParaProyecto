package GestorDatosPermanentes;

import DocumentoComercial.ComprobanteVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ComprobanteVentaDAO {

    private Connection getConexion() {
        return ConexionDB.getConexion();
    }

    public ComprobanteVenta buscarComprobanteVenta(int id) {
        String comandoSQL = "SELECT * FROM comprobantesEmitidosVentas WHERE comprobante_id= ?";
        try (PreparedStatement ingresarComando = getConexion().prepareStatement(comandoSQL)) {
            ingresarComando.setInt(1, id);
            try (ResultSet datosObtenidos = ingresarComando.executeQuery()) {
                if (datosObtenidos.next()) {
                    return new ComprobanteVenta(
                            datosObtenidos.getInt("comprobante_id"),
                            LocalDate.parse(datosObtenidos.getString("fechaRegistro")),
                            datosObtenidos.getString("tipoComprobante"),
                            datosObtenidos.getInt("serie"),
                            datosObtenidos.getInt("numero"),
                            datosObtenidos.getString("cliente"),
                            datosObtenidos.getDouble("total")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar comprobante venta: " + e);
        }
        return null;
    }

    public ArrayList<ComprobanteVenta> obtenerComprobantesVenta() {
        ArrayList<ComprobanteVenta> comprobantes = new ArrayList<>();
        String sql = "SELECT * FROM comprobantesEmitidosVentas";
        try (PreparedStatement statement = getConexion().prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {
            while (resultado.next()) {
                ComprobanteVenta comprobante = new ComprobanteVenta(
                        resultado.getInt("comprobante_id"),
                        LocalDate.parse(resultado.getString("fechaRegistro")),
                        resultado.getString("tipoComprobante"),
                        resultado.getInt("serie"),
                        resultado.getInt("numero"),
                        resultado.getString("cliente"),
                        resultado.getDouble("total")
                );
                comprobantes.add(comprobante);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener comprobantes venta: " + e.getMessage());
        }
        return comprobantes;
    }

    public boolean crearComprobanteVenta(String fechaRegistro, String tipoComprobante, int serie, int numero, String cliente, double total) {
        boolean exito = false;
        String sql = "INSERT INTO comprobantesEmitidosVentas (fechaRegistro,tipoComprobante,serie,numero,cliente,total) VALUES (date('now'), ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setString(1, tipoComprobante);
            statement.setInt(2, serie);
            statement.setInt(3, numero);
            statement.setString(4, cliente);
            statement.setDouble(5, total);
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al crear comprobante venta: " + e.getMessage());
        }
        return exito;
    }

    public boolean eliminarComprobanteVenta(int comprobante_id) {
        boolean exito = false;
        String sql = "DELETE FROM comprobantesEmitidosVentas WHERE comprobante_id = ?";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setInt(1, comprobante_id);
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar comprobante venta: " + e.getMessage());
        }
        return exito;
    }

    public boolean actualizarComprobanteVenta(int comprobante_id, LocalDate fechaRegistro, String tipoComprobante,
            int serie, int numero, String cliente, double total) {
        boolean exito = false;
        String sql = "UPDATE comprobantesEmitidosVentas SET fechaRegistro = ?, tipoComprobante = ?, serie = ?, numero = ?, cliente = ?, total  = ? WHERE comprobante_id = ?";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setString(1, fechaRegistro.toString());
            statement.setString(2, tipoComprobante);
            statement.setInt(3, serie);
            statement.setInt(4, numero);
            statement.setString(5, cliente);
            statement.setDouble(6, total);
            statement.setInt(7, comprobante_id);
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar comprobante venta: " + e.getMessage());
        }
        return exito;
    }

    public double obtenerTotalCompEmitVenta() {
        String sql = "SELECT COALESCE(SUM(total),0) FROM comprobantesEmitidosVentas";
        try (PreparedStatement ps = getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
}
