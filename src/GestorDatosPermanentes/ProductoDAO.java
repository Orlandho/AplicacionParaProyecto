package GestorDatosPermanentes;

import Producto.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {

    private Connection getConexion() {
        return ConexionDB.getConexion();
    }

    public Producto buscarProducto(String producto) {
        String comandoSQL = "SELECT * FROM Productos WHERE producto= ?";
        try (PreparedStatement ingresarComando = getConexion().prepareStatement(comandoSQL)) {
            ingresarComando.setString(1, producto);
            try (ResultSet datosObtenidos = ingresarComando.executeQuery()) {
                if (datosObtenidos.next()) {
                    return new Producto(
                            datosObtenidos.getInt("producto_id"),
                            datosObtenidos.getString("tipoDocumento"),
                            datosObtenidos.getString("producto"),
                            datosObtenidos.getDouble("precioCompra"),
                            datosObtenidos.getInt("cantidad"),
                            datosObtenidos.getString("stock")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e);
        }
        return null;
    }

    public Producto buscarProducto(int ID) {
        String comandoSQL = "SELECT * FROM Productos WHERE producto_id= ?";
        try (PreparedStatement ingresarComando = getConexion().prepareStatement(comandoSQL)) {
            ingresarComando.setInt(1, ID);
            try (ResultSet datosObtenidos = ingresarComando.executeQuery()) {
                if (datosObtenidos.next()) {
                    return new Producto(
                            datosObtenidos.getInt("producto_id"),
                            datosObtenidos.getString("tipoDocumento"),
                            datosObtenidos.getString("producto"),
                            datosObtenidos.getDouble("precioCompra"),
                            datosObtenidos.getInt("cantidad"),
                            datosObtenidos.getString("stock")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e);
        }
        return null;
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (PreparedStatement statement = getConexion().prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {
            while (resultado.next()) {
                Producto producto = new Producto(
                        resultado.getInt("producto_id"),
                        resultado.getString("tipoDocumento"),
                        resultado.getString("producto"),
                        resultado.getDouble("precioCompra"),
                        resultado.getInt("cantidad"),
                        resultado.getString("stock")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    public boolean crearProducto(String tipoDoc, String producto, double precio, int cantidad, String stock) {
        boolean exito = false;
        String sql = "INSERT INTO productos (tipoDocumento, producto, precioCompra, cantidad, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setString(1, tipoDoc);
            statement.setString(2, producto);
            statement.setDouble(3, precio);
            statement.setInt(4, cantidad);
            statement.setString(5, stock);
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
        return exito;
    }

    public boolean eliminarProducto(int producto_id) {
        boolean exito = false;
        String sql = "DELETE FROM productos WHERE producto_id = ?";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setInt(1, producto_id);
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
        return exito;
    }

    public boolean actualizarProducto(int producto_id, String nuevoTipoDocumento, String nuevoProducto,
            double nuevoPrecioCompra, int nuevaCantidad, String nuevoStock) {
        boolean exito = false;
        String sql = "UPDATE productos SET tipoDocumento = ?, producto = ?, precioCompra = ?, cantidad = ?, stock = ? WHERE producto_id = ?";
        try (PreparedStatement statement = getConexion().prepareStatement(sql)) {
            statement.setString(1, nuevoTipoDocumento);
            statement.setString(2, nuevoProducto);
            statement.setDouble(3, nuevoPrecioCompra);
            statement.setInt(4, nuevaCantidad);
            statement.setString(5, nuevoStock);
            statement.setInt(6, producto_id);
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
        return exito;
    }
}
