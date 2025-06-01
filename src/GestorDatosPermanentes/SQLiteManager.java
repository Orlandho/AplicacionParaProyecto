package GestorDatosPermanentes;

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

public class SQLiteManager {

    private Connection conexionDB;
    private final String nombreDB = "baseDeDatos.db";
    public static final int USUARIO_CONTRA_INCORRECTOS = 0, USUARIO_BLOQUEADO = 1, DEBE_CAMBIAR_CONTRASEÑA = 2, PUEDE_INGRESAR = 3;

    public SQLiteManager() {
        try {
            conexionDB = DriverManager.getConnection("jdbc:sqlite:" + nombreDB);
        } catch (SQLException e) {
           throw new RuntimeException("Error al intentar conectar con la base de datos "+nombreDB+"\nMensaje de error: "+e.getMessage());
        }
    }
    public void cerrarConexion()
    {
        try{
        conexionDB.close();
        }catch(SQLException e){
            throw new RuntimeException("Error al cerrar conexion.\nMensaje de error:"+e.getMessage());
        }
    }
    private boolean actualizarUsuario(Usuario usuario)
    {
        String comandoSQL = "UPDATE usuario SET usuario= ?,contraseña=?,rol= ?,fechaUltimoCambio= ?,nombre= ?,apellido= ?,genero= ?,direccion= ? WHERE usuario_id= ?";
        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setString(1, usuario.getUsuario());
            ingresarComando.setString(2, usuario.getContraseña());
            ingresarComando.setString(3, usuario.getRol());
            ingresarComando.setString(4, usuario.getFechaUltimoCambio().toString());
            ingresarComando.setString(5, usuario.getNombre());
            ingresarComando.setString(6, usuario.getApellido());
            ingresarComando.setString(7, usuario.getGenero());
            ingresarComando.setString(8, usuario.getDireccion());
            ingresarComando.setInt(9, usuario.getUsuario_id());
            int filasAfectadas = ingresarComando.executeUpdate();
            if (filasAfectadas>0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario.\nMensaje de error:"+e.getMessage());
        }
        //actualizo correctamente
        return false;
    }

    private Usuario buscarUsuario(String nombreUsuario) {
        String comandoSQL = "SELECT * FROM Usuario WHERE usuario= ?";
        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setString(1, nombreUsuario);
            ResultSet datosObtenidos = ingresarComando.executeQuery();
            if (datosObtenidos.next()) {
                return new Usuario(datosObtenidos.getInt("usuario_id"), datosObtenidos.getString("usuario"), datosObtenidos.getString("contraseña"), datosObtenidos.getString("rol"), LocalDate.parse(datosObtenidos.getString("fechaUltimoCambio")), datosObtenidos.getString("nombre"), datosObtenidos.getString("apellido"), datosObtenidos.getString("genero"), datosObtenidos.getString("direccion"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario.\nMensaje de error:"+e.getMessage());
        }
        return null;
    }

    private boolean existeUsuario(String nombreUsuario) {
        return buscarUsuario(nombreUsuario)!=null;
    }

    private boolean esSuContraseña(Usuario usuarioEncontrado, String contraseña) {
        if (!usuarioEncontrado.getContraseña().equals(contraseña)) {
            usuarioEncontrado.setIntentosFallidos(usuarioEncontrado.getIntentosFallidos() + 1);
            if (usuarioEncontrado.getIntentosFallidos() >= 5) {
                usuarioEncontrado.setCuentaBloqueada(true);
            }
            actualizarUsuario(usuarioEncontrado);
            return false;
        }
        return true;

    }

    private boolean debeCambiarContraseña(Usuario usuario) {
        return (usuario.getRol().equalsIgnoreCase("administrador") && usuario.debeCambiarContraseña());
    }

    public boolean actualizarContraseña(String nombreUsuario, String antiguaContraseña, String nuevaContraseña) {
        if (existeUsuario(nombreUsuario) && esSuContraseña(buscarUsuario(nombreUsuario), antiguaContraseña) && debeCambiarContraseña(buscarUsuario(nombreUsuario))) {
            Usuario usuarioTemp=buscarUsuario(nombreUsuario);
            usuarioTemp.setContraseña(nuevaContraseña);
            actualizarUsuario(usuarioTemp);
            return true;
        }
        return false;
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
