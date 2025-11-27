package GestorDatosPermanentes;

import Producto.Producto;
import Usuario.Usuario;
import DocumentoComercial.ComprobanteCompra;
import DocumentoComercial.ComprobanteVenta;
import java.time.LocalDate;
import java.util.ArrayList;

public class SQLiteManager {

    private UsuarioDAO usuarioDAO;
    private ProductoDAO productoDAO;
    private ComprobanteCompraDAO comprobanteCompraDAO;
    private ComprobanteVentaDAO comprobanteVentaDAO;
    private AjustesDAO ajustesDAO;

    public static final int USUARIO_CONTRA_INCORRECTOS = UsuarioDAO.USUARIO_CONTRA_INCORRECTOS;
    public static final int USUARIO_BLOQUEADO = UsuarioDAO.USUARIO_BLOQUEADO;
    public static final int DEBE_CAMBIAR_CONTRASEÑA = UsuarioDAO.DEBE_CAMBIAR_CONTRASEÑA;
    public static final int PUEDE_INGRESAR = UsuarioDAO.PUEDE_INGRESAR;

    public SQLiteManager() {
        // Inicializar la conexión (opcional, ya que los DAOs llaman a ConexionDB.getConexion())
        ConexionDB.getConexion();

        // Inicializar DAOs
        this.usuarioDAO = new UsuarioDAO();
        this.productoDAO = new ProductoDAO();
        this.comprobanteCompraDAO = new ComprobanteCompraDAO();
        this.comprobanteVentaDAO = new ComprobanteVentaDAO();
        this.ajustesDAO = new AjustesDAO();
    }
    
    public double obtenerTotalCompEmitCompra() {
        return comprobanteCompraDAO.obtenerTotalCompEmitCompra();
    }
    
    public double obtenerTotalCompEmitVenta() {
        return comprobanteVentaDAO.obtenerTotalCompEmitVenta();
    }

    public void cerrarConexion() {
        ConexionDB.cerrarConexion();
    }

    public boolean actualizarContraseña(String nombreUsuario, String antiguaContraseña, String nuevaContraseña) {
        return usuarioDAO.actualizarContraseña(nombreUsuario, antiguaContraseña, nuevaContraseña);
    }

    //Sprint 2
    public void crearCuentaUsuario(long DNIoRUC, String nombres, String apellidos, int telefono, String contraseña, String rol, boolean cuentaBloqueada) {
        usuarioDAO.crearCuentaUsuario(DNIoRUC, nombres, apellidos, telefono, contraseña, rol, cuentaBloqueada);
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }

    public void eliminarUsuario(int usuario_id) {
        usuarioDAO.eliminarUsuario(usuario_id);
    }

    public void actualizarUsuario(long usuarioDNI, String contraseña, String rol, LocalDate fechaUltimoCambio,
            int intentosFallidos, boolean cuentaBloqueada,
            String nombres, String apellidos, int telefono, int usuario_id) {
        usuarioDAO.actualizarUsuario(usuarioDNI, contraseña, rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada, nombres, apellidos, telefono, usuario_id);
    }

    //SPRINT 3

    public Producto buscarProducto(String producto) {
        return productoDAO.buscarProducto(producto);
    }

    public Producto buscarProducto(int ID) {
        return productoDAO.buscarProducto(ID);
    }

    public ArrayList<Producto> obtenerProductos() {
        return productoDAO.obtenerProductos();
    }

    public boolean crearProducto(String tipoDoc, String producto, double precio, int cantidad, String stock) {
        return productoDAO.crearProducto(tipoDoc, producto, precio, cantidad, stock);
    }

    public boolean eliminarProducto(int producto_id) {
        return productoDAO.eliminarProducto(producto_id);
    }

    public boolean actualizarProducto(int producto_id, String nuevoTipoDocumento, String nuevoProducto,
            double nuevoPrecioCompra, int nuevaCantidad, String nuevoStock) {
        return productoDAO.actualizarProducto(producto_id, nuevoTipoDocumento, nuevoProducto, nuevoPrecioCompra, nuevaCantidad, nuevoStock);
    }

    //SPRINT 4: Comprobantes y ajustes
    public ComprobanteCompra buscarComprobante(int id) {
        return comprobanteCompraDAO.buscarComprobante(id);
    }

    public ArrayList<ComprobanteCompra> obtenerComprobantes() {
        return comprobanteCompraDAO.obtenerComprobantes();
    }

    public boolean crearComprobante(String fechaRegistro, String tipoComprobante, long serie, long numero, String proveedor, double total) {
        return comprobanteCompraDAO.crearComprobante(fechaRegistro, tipoComprobante, serie, numero, proveedor, total);
    }

    public boolean eliminarComprobante(int comprobante_id) {
        return comprobanteCompraDAO.eliminarComprobante(comprobante_id);
    }

    public boolean actualizarComprobante(int comprobante_id, LocalDate fechaRegistro, String tipoComprobante,
            int serie, int numero, String proveedor, double total) {
        return comprobanteCompraDAO.actualizarComprobante(comprobante_id, fechaRegistro, tipoComprobante, serie, numero, proveedor, total);
    }

    //AJUSTES
    public String[] obtenerAjustes() {
        return ajustesDAO.obtenerAjustes();
    }

    public boolean actualizarAjustes(String lenguaje, String modo) {
        return ajustesDAO.actualizarAjustes(lenguaje, modo);
    }
    
    //Sprint 5
    public ComprobanteVenta buscarComprobanteVenta(int id) {
        return comprobanteVentaDAO.buscarComprobanteVenta(id);
    }

    public ArrayList<ComprobanteVenta> obtenerComprobantesVenta() {
        return comprobanteVentaDAO.obtenerComprobantesVenta();
    }

    public boolean crearComprobanteVenta(String fechaRegistro, String tipoComprobante, int serie, int numero, String cliente, double total) {
        return comprobanteVentaDAO.crearComprobanteVenta(fechaRegistro, tipoComprobante, serie, numero, cliente, total);
    }

    public boolean eliminarComprobanteVenta(int comprobante_id) {
        return comprobanteVentaDAO.eliminarComprobanteVenta(comprobante_id);
    }

    public boolean actualizarComprobanteVenta(int comprobante_id, LocalDate fechaRegistro, String tipoComprobante,
            int serie, int numero, String cliente, double total) {
        return comprobanteVentaDAO.actualizarComprobanteVenta(comprobante_id, fechaRegistro, tipoComprobante, serie, numero, cliente, total);
    }

    public ArrayList<Object> intentarLogin(String usuarioIngresado, String contraseñaIngresada) {
        return usuarioDAO.intentarLogin(usuarioIngresado, contraseñaIngresada);
    }

}
