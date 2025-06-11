package GestorDatosPermanentes;

import Usuario.Producto;
import Usuario.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
//librerias para SQL
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SQLiteManager {

    //comentario de prueba
    private Connection conexionDB;
    private final String nombreDB = "baseDeDatos.db";
    public static final int USUARIO_CONTRA_INCORRECTOS = 0, USUARIO_BLOQUEADO = 1, DEBE_CAMBIAR_CONTRASEÑA = 2, PUEDE_INGRESAR = 3;

    public SQLiteManager() {
        try {
            conexionDB = DriverManager.getConnection("jdbc:sqlite:" + nombreDB);
        } catch (SQLException e) {
            throw new RuntimeException("Error al intentar conectar con la base de datos " + nombreDB + "\nMensaje de error: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (conexionDB != null && !conexionDB.isClosed()) {
                conexionDB.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar conexion.\nMensaje de error:" + e.getMessage());
        }
    }

    private boolean actualizarUsuarioContraseña(Usuario usuario) {
        String comandoSQL = "UPDATE usuario SET contraseña = ?, fechaUltimoCambio = ?, IntentosFallidos = ?, cuentaBloqueada=? WHERE usuario_id = ?";
        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setString(1, usuario.getContraseña());
            ingresarComando.setString(2, usuario.getFechaUltimoCambio().toString());
            ingresarComando.setInt(3, usuario.getIntentosFallidos());
            ingresarComando.setInt(4, traducirCuentaBloqueada(usuario.esCuentaBloqueada()));
            ingresarComando.setInt(5, usuario.getUsuario_id());
            int filasAfectadas = ingresarComando.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la contraseña del usuario.\nMensaje de error:" + e.getMessage());
        }
    }

    private boolean traducirCuentaBloqueada(int cuentaBloqueada) {
        return cuentaBloqueada != 0;
    }

    private int traducirCuentaBloqueada(boolean cuentaBloqueada) {
        return cuentaBloqueada ? 1 : 0;
    }

    private Usuario buscarUsuario(String nombreUsuario) {
        String comandoSQL = "SELECT * FROM Usuario WHERE usuarioDNIoRUC= ?";
        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setLong(1, Long.parseLong(nombreUsuario));
            ResultSet datosObtenidos = ingresarComando.executeQuery();
            if (datosObtenidos.next()) {
                //                                            
                return new Usuario(datosObtenidos.getInt("usuario_id"), datosObtenidos.getLong("usuarioDNIoRUC"),
                        datosObtenidos.getString("contraseña"),
                        datosObtenidos.getString("rol"),
                        LocalDate.parse(datosObtenidos.getString("fechaUltimoCambio")),
                        datosObtenidos.getInt("intentosFallidos"),
                        traducirCuentaBloqueada(datosObtenidos.getInt("cuentaBloqueada")),
                        datosObtenidos.getString("nombres"), datosObtenidos.getString("apellidos"),
                        datosObtenidos.getInt("telefono"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario.\nMensaje de error:" + e.getMessage());
        }
        return null;
    }

    private boolean existeUsuario(String nombreUsuario) {
        return buscarUsuario(nombreUsuario) != null;
    }

    private boolean esSuContraseña(Usuario usuarioEncontrado, String contraseñaIngresada) {
        if (!usuarioEncontrado.getContraseña().equals(contraseñaIngresada)) {
            usuarioEncontrado.setIntentosFallidos(usuarioEncontrado.getIntentosFallidos() + 1);
            if (usuarioEncontrado.getIntentosFallidos() >= 5) {
                usuarioEncontrado.setCuentaBloqueada(true);
            }
            actualizarUsuarioContraseña(usuarioEncontrado);
            return false;
        }
        return true;

    }

    private boolean debeCambiarContraseña(Usuario usuario) {
        return (usuario.getRol().equalsIgnoreCase("administrador") && usuario.debeCambiarContraseña());
    }

    public boolean actualizarContraseña(String nombreUsuario, String antiguaContraseña, String nuevaContraseña) {
        if (existeUsuario(nombreUsuario) && esSuContraseña(buscarUsuario(nombreUsuario), antiguaContraseña) && debeCambiarContraseña(buscarUsuario(nombreUsuario))) {
            Usuario usuarioTemp = buscarUsuario(nombreUsuario);
            usuarioTemp.setContraseña(nuevaContraseña);
            actualizarUsuarioContraseña(usuarioTemp);
            return true;
        }
        return false;
    }

    //Sprint 2
    // Método para crear cuenta de usuario si el empleado existe en listaEmpleados
    public void crearCuentaUsuario(long DNIoRUC, String nombres, String apellidos, int telefono, String contraseña, String rol, boolean cuentaBloqueada) {

        // Verificar si los datos existen en listaEmpleados
        String comandoSQL = "INSERT INTO Usuario ( usuarioDNIoRUC, contraseña, rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada, nombres, apellidos, telefono) "
                + "VALUES ( ?, ?, ?, date('now'), 0, ?, ?, ?, ?)";

        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setLong(1, DNIoRUC);
            ingresarComando.setString(2, contraseña);
            ingresarComando.setString(3, rol);
            ingresarComando.setInt(4, cuentaBloqueada ? 1 : 0);
            ingresarComando.setString(5, nombres);
            ingresarComando.setString(6, apellidos);
            ingresarComando.setInt(7, telefono);

            int filasAfectadas = ingresarComando.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: no se pudo crear la cuenta.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear usuario.\nMensaje de error: " + e.getMessage());
        }
    }

    //Metodos para obtener usuarios para la tabla
    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT usuario_id, usuarioDNIoRUC,contraseña,rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada ,nombres, apellidos ,telefono FROM usuario";

        try (Statement stmt = conexionDB.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int usuario_id = rs.getInt("usuario_id");
                long usuarioDNIoRUC = rs.getLong("usuarioDNIoRUC");
                String contraseña = rs.getString("contraseña");
                String rol = rs.getString("rol");
                LocalDate fechaUltimoCambio = LocalDate.parse(rs.getString("fechaUltimoCambio"));
                int intentosFallidos = rs.getInt("intentosFallidos");
                boolean cuentaBloqueada = traducirCuentaBloqueada(rs.getInt("cuentaBloqueada"));
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                int telefono = rs.getInt("telefono");

                Usuario datosUsuario = new Usuario(usuario_id, usuarioDNIoRUC, contraseña,
                        rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada,
                        nombres, apellidos, telefono);
                usuarios.add(datosUsuario);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los usuarios: " + e.getMessage());
        }

        return usuarios;
    }
    
    public void eliminarUsuario(int usuario_id) {
        String sql = "DELETE FROM Usuario WHERE usuario_id = ?";

        try (PreparedStatement pstmt = conexionDB.prepareStatement(sql)) {
            pstmt.setInt(1, usuario_id);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un usuario con el ID especificado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage());
        }
    }
    
    public void actualizarUsuario(int usuarioDNI, String contraseña, String rol, LocalDate fechaUltimoCambio,
                              int intentosFallidos, boolean cuentaBloqueada,
                              String nombres, String apellidos, int telefono, int usuario_id) {
        String sql = "UPDATE Usuario SET usuarioDNI = ?, contraseña = ?, rol = ?, fechaUltimoCambio = ?, " +
                     "intentosFallidos = ?, cuentaBloqueada = ?, nombres = ?, apellidos = ?, telefono = ? " +
                     "WHERE usuario_id = ?";

        try (PreparedStatement pstmt = conexionDB.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioDNI);
            pstmt.setString(2, contraseña);
            pstmt.setString(3, rol);
            pstmt.setString(4, fechaUltimoCambio.toString()); // Guardando LocalDate como String
            pstmt.setInt(5, intentosFallidos);
            pstmt.setBoolean(6, cuentaBloqueada);
            pstmt.setString(7, nombres);
            pstmt.setString(8, apellidos);
            pstmt.setInt(9, telefono);
            pstmt.setInt(10, usuario_id); // Este es el ID para filtrar

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un usuario con el ID especificado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.getMessage());
        }
    }
    
    //SPRINT 3
    
    public Producto buscarProducto(String producto) {
        String comandoSQL ="SELECT * FROM Productos WHERE producto= ?";
        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setString(1, producto);
            ResultSet datosObtenidos = ingresarComando.executeQuery();

            if (datosObtenidos.next()) {
                return new Producto(
                    datosObtenidos.getString("tipoDocumento"),
                    datosObtenidos.getString("producto"),
                    datosObtenidos.getDouble("precioCompra"),
                    datosObtenidos.getInt("cantidad"),
                    datosObtenidos.getString("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e);
        }
        return null;
    }
    
    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            PreparedStatement statement = conexionDB.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Producto producto = new Producto(
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
    /*
        Dicionario de codigos:
        0 | Usuario o contraseña incorrectos
        1 | Usuario bloqueado
        2 | Usuario tiene que cambiar su contraseña
        3 | Usuario se logueo correctamente
        4 | Usuario no existe
     */
    public ArrayList<Object> intentarLogin(String usuarioIngresado, String contraseñaIngresada) {
        ArrayList<Object> respuesta = new ArrayList<>();
        if (!existeUsuario(usuarioIngresado)) {
            respuesta.add(USUARIO_CONTRA_INCORRECTOS);
            return respuesta;
        }
        Usuario usuario = buscarUsuario(usuarioIngresado);
        if (usuario.esCuentaBloqueada()) {
            respuesta.add(USUARIO_BLOQUEADO);
            return respuesta;
        }
        if (!esSuContraseña(usuario, contraseñaIngresada)) {
            respuesta.add(USUARIO_CONTRA_INCORRECTOS);
            return respuesta;
        } else {
            usuario = buscarUsuario(usuarioIngresado);
            usuario.setIntentosFallidos(0);
            actualizarUsuarioContraseña(usuario);
        }
        if (debeCambiarContraseña(usuario)) {
            respuesta.add(DEBE_CAMBIAR_CONTRASEÑA);
            return respuesta;
        }
        respuesta.add(PUEDE_INGRESAR);
        respuesta.add(usuario);
        return respuesta;
    }

}
