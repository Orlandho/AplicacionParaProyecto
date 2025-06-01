package GestorDatosPermanentes;

import Usuario.Usuario;
import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;
//borrar librerias sin usar como HashMap y Map
import java.util.HashMap;
import java.util.Map;
//librerias para SQL
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class SQLiteManager {

    private Map<String, Usuario> usuarios;
    public static final int USUARIO_CONTRA_INCORRECTOS = 0, USUARIO_BLOQUEADO = 1, DEBE_CAMBIAR_CONTRASEÑA = 2, PUEDE_INGRESAR = 3;

    public SQLiteManager() {
        usuarios = new HashMap<>();
        cargarUsuariosDePrueba();
    }

    private void cargarUsuariosDePrueba() {
        Usuario usuario = new Usuario("73034581", "12345", "empleado", LocalDate.of(2025, 5, 16), "Orlando", "Dorival", "El Macho", "jr.Los Olivos En USA");
        Usuario usuario2 = new Usuario("20603299494", "12345", "administrador", LocalDate.of(2025, 5, 16), "Alicia", "Nuñez", "femenino", "jr.Simpatica En Algun Lugar");
        Usuario usuario3 = new Usuario("12345678", "12345", "administrador", LocalDate.of(2025, 5, 16), "Danna", "Huaman", "femenino", "jr.Casita En Casa");
        Usuario usuario4 = new Usuario("98765432", "12345", "administrador", LocalDate.of(2025, 1, 16), "Luis", "Moreyra", "El Macho", "jr.Casita cerca de UPN");
        usuarios.put(usuario.getUsuario(), usuario);
        usuarios.put(usuario2.getUsuario(), usuario2);
        usuarios.put(usuario3.getUsuario(), usuario3);
        usuarios.put(usuario4.getUsuario(), usuario4);
    }

    private Usuario buscarUsuario(String nombreUsuario) {
        return usuarios.get(nombreUsuario);
    }

    private boolean existeUsuario(String nombreUsuario) {
        return usuarios.containsKey(nombreUsuario);
    }

    private boolean esSuContraseña(Usuario usuarioEncontrado, String contraseña) {
        if (!usuarioEncontrado.getContraseña().equals(contraseña)) {
            usuarioEncontrado.setIntentosFallidos(usuarioEncontrado.getIntentosFallidos() + 1);
            if (usuarioEncontrado.getIntentosFallidos() >= 5) {
                usuarioEncontrado.setCuentaBloqueada(true);
            }
            return false;
        }
        return true;

    }

    private boolean debeCambiarContraseña(Usuario usuario) {
        return (usuario.getRol().equalsIgnoreCase("administrador") && usuario.debeCambiarContraseña());
    }

    public boolean actualizarContraseña(String nombreUsuario, String antiguaContraseña, String nuevaContraseña) {
        if (existeUsuario(nombreUsuario) && esSuContraseña(buscarUsuario(nombreUsuario), antiguaContraseña) && debeCambiarContraseña(buscarUsuario(nombreUsuario))) {
            usuarios.get(nombreUsuario).setContraseña(nuevaContraseña);
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
