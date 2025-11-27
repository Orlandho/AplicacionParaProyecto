package GestorDatosPermanentes;

import DocumentoComercial.ComprobanteCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ComprobanteCompraDAO {

    private Connection getConexion() {
        return ConexionDB.getConexion();
    }

    public ComprobanteCompra buscarComprobante(int id) {
        String comandoSQL = "SELECT * FROM comprobantesEmitidosCompra WHERE comprobante_id= ?";
        try (PreparedStatement ingresarComando = getConexion().prepareStatement(comandoSQL)) {
            ingresarComando.setInt(1, id);
            try (ResultSet datosObtenidos = ingresarComando.executeQuery()) {
                if (datosObtenidos.next()) {
                    return new ComprobanteCompra(
                            datosObtenidos.getInt("comprobante_id"),
                            LocalDate.parse(datosObtenidos.getString("fechaRegistro")),
                            datosObtenidos.getString("tipoComprobante"),
                            datosObtenidos.getInt("serie"),
                            datosObtenidos.getInt("numero"),
                            datosObtenidos.getString("proveedor"),
                            datosObtenidos.getDouble("total")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar comprobante compra: " + e);
        }
        return null;
    }

    public ArrayList<ComprobanteCompra> obtenerComprobantes() {
        ArrayList<ComprobanteCompra> comprobantes = new ArrayList<>();
        String sql = "SELECT * FROM comprobantesEmitidosCompra";
        try (PreparedStatement statement = getConexion().prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {
            while (resultado.next()) {
                ComprobanteCompra comprobante = new ComprobanteCompra(
                        resultado.getInt("comprobante_id"),
                        LocalDate.parse(resultado.getString("fechaRegistro")),
                        resultado.getString("tipoComprobante"),
                        resultado.getInt("serie"),
                        resultado.getInt("numero"),
                        resultado.getString("proveedor"),
                        resultado.getDouble("total")
                );
                comprobantes.add(comprobante);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener comprobantes compra: " + e.getMessage());
        }
        return comprobantes;
    }

    public boolean crearComprobante(String fechaRegistro, String tipoComprobante, long serie, long numero, String proveedor, double total) {
        boolean exito = false;
        String sql = "INSERT INTO comprobantesEmitidosCompra (fechaRegistro,tipoComprobante,serie,numero,proveedor,total) VALUES (date('now'), ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setString(1, tipoComprobante);
            statement.setLong(2, serie);
            statement.setLong(3, numero);
            statement.setString(4, proveedor);
            statement.setDouble(5, total);
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al crear comprobante compra: " + e.getMessage());
        }
        return exito;
    }

    public boolean eliminarComprobante(int comprobante_id) {
        boolean exito = false;
        String sql = "DELETE FROM comprobantesEmitidosCompra WHERE comprobante_id = ?";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setInt(1, comprobante_id);
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar comprobante compra: " + e.getMessage());
        }
        return exito;
    }

    public boolean actualizarComprobante(int comprobante_id, LocalDate fechaRegistro, String tipoComprobante,
            int serie, int numero, String proveedor, double total) {
        boolean exito = false;
        String sql = "UPDATE comprobantesEmitidosCompra SET fechaRegistro = ?, tipoComprobante = ?, serie = ?, numero = ?, proveedor = ?, total  = ? WHERE comprobante_id = ?";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setString(1, fechaRegistro.toString());
            statement.setString(2, tipoComprobante);
            statement.setInt(3, serie);
            statement.setInt(4, numero);
            statement.setString(5, proveedor);
            statement.setDouble(6, total);
            statement.setInt(7, comprobante_id);
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar comprobante compra: " + e.getMessage());
        }
        return exito;
    }

    public double obtenerTotalCompEmitCompra() {
        String sql = "SELECT COALESCE(SUM(total),0) FROM comprobantesEmitidosCompra";
        try (PreparedStatement ps = getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error BD ", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
}
