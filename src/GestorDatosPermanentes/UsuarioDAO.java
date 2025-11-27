package GestorDatosPermanentes;

import Usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    public static final int USUARIO_CONTRA_INCORRECTOS = 0, USUARIO_BLOQUEADO = 1, DEBE_CAMBIAR_CONTRASEÑA = 2, PUEDE_INGRESAR = 3;

    private Connection getConexion() {
        return ConexionDB.getConexion();
    }

    public boolean actualizarUsuarioContraseña(Usuario usuario) {
        String comandoSQL = "UPDATE usuario SET contraseña = ?, fechaUltimoCambio = ?, IntentosFallidos = ?, cuentaBloqueada=? WHERE usuario_id = ?";
        try (PreparedStatement ingresarComando = getConexion().prepareStatement(comandoSQL)) {
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

    public Usuario buscarUsuario(String nombreUsuario) {
        String comandoSQL = "SELECT * FROM Usuario WHERE usuarioDNIoRUC= ?";
        try (PreparedStatement ingresarComando = getConexion().prepareStatement(comandoSQL)) {
            ingresarComando.setLong(1, Long.parseLong(nombreUsuario));
            try (ResultSet datosObtenidos = ingresarComando.executeQuery()) {
                if (datosObtenidos.next()) {
                    return new Usuario(datosObtenidos.getInt("usuario_id"), datosObtenidos.getLong("usuarioDNIoRUC"),
                            datosObtenidos.getString("contraseña"),
                            datosObtenidos.getString("rol"),
                            LocalDate.parse(datosObtenidos.getString("fechaUltimoCambio")),
                            datosObtenidos.getInt("intentosFallidos"),
                            traducirCuentaBloqueada(datosObtenidos.getInt("cuentaBloqueada")),
                            datosObtenidos.getString("nombres"), datosObtenidos.getString("apellidos"),
                            datosObtenidos.getInt("telefono"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario.\nMensaje de error:" + e.getMessage());
        }
        return null;
    }

    public boolean existeUsuario(String nombreUsuario) {
        return buscarUsuario(nombreUsuario) != null;
    }

    public boolean esSuContraseña(Usuario usuarioEncontrado, String contraseñaIngresada) {
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

    public boolean debeCambiarContraseña(Usuario usuario) {
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

    public void crearCuentaUsuario(long DNIoRUC, String nombres, String apellidos, int telefono, String contraseña, String rol, boolean cuentaBloqueada) {
        String comandoSQL = "INSERT INTO Usuario ( usuarioDNIoRUC, contraseña, rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada, nombres, apellidos, telefono) "
                + "VALUES ( ?, ?, ?, date('now'), 0, ?, ?, ?, ?)";

        try (PreparedStatement ingresarComando = getConexion().prepareStatement(comandoSQL)) {
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

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT usuario_id, usuarioDNIoRUC,contraseña,rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada ,nombres, apellidos ,telefono FROM usuario";

        try (Statement stmt = getConexion().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
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

        try (PreparedStatement pstmt = getConexion().prepareStatement(sql)) {
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

    public void actualizarUsuario(long usuarioDNI, String contraseña, String rol, LocalDate fechaUltimoCambio,
            int intentosFallidos, boolean cuentaBloqueada,
            String nombres, String apellidos, int telefono, int usuario_id) {
        String sql = "UPDATE Usuario SET usuarioDNIoRUC = ?, contraseña = ?, rol = ?, fechaUltimoCambio = ?, "
                + "intentosFallidos = ?, cuentaBloqueada = ?, nombres = ?, apellidos = ?, telefono = ? "
                + "WHERE usuario_id = ?";

        try (PreparedStatement pstmt = getConexion().prepareStatement(sql)) {
            pstmt.setLong(1, usuarioDNI);
            pstmt.setString(2, contraseña);
            pstmt.setString(3, rol);
            pstmt.setString(4, fechaUltimoCambio.toString());
            pstmt.setInt(5, intentosFallidos);
            pstmt.setInt(6, traducirCuentaBloqueada(cuentaBloqueada));
            pstmt.setString(7, nombres);
            pstmt.setString(8, apellidos);
            pstmt.setInt(9, telefono);
            pstmt.setInt(10, usuario_id);

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
