package GestorDatosPermanentes;

import Usuario.Usuario;
import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseDeDatos {

    private Map<String, Usuario> usuarios;
    public static final int USUARIO_CONTRA_INCORRECTOS = 0, USUARIO_BLOQUEADO = 1, DEBE_CAMBIAR_CONTRASEÑA = 2, PUEDE_INGRESAR = 3;

    public BaseDeDatos() {
        usuarios = new HashMap<>();
        cargarUsuariosDeArchivo("Usuarios.txt");
    }

    private void cargarUsuariosDeArchivo(String archivo) {
        // Usar la clase ArchivoUtil para leer el archivo
        ArrayList<String> lineas = ArchivoUtil.leerArchivo(archivo);
        for (String linea : lineas) {
            // Asumiendo que cada línea tiene los datos separados por comas
            // "usuario,contraseña,rol,fechaUltimoCambio,nombre,apellido,genero,direccion,pregunta1,respuesta1,pregunta2,respuesta2"
            String[] datos = linea.split(",");
            if (datos.length >= 12) {
                String usuario = datos[0];
                String contraseña = datos[1];
                String rol = datos[2];
                LocalDate fechaUltimoCambio = LocalDate.parse(datos[3]);
                String nombre = datos[4];
                String apellido = datos[5];
                String genero = datos[6];
                String direccion = datos[7];
                String[] preguntas = {datos[8], datos[10]};
                String[] respuestas = {datos[9], datos[11]};

                // Crear objeto Usuario
                Usuario nuevoUsuario = new Usuario(usuario, contraseña, rol, fechaUltimoCambio, nombre, apellido, genero, direccion, preguntas, respuestas);
                usuarios.put(usuario, nuevoUsuario);
            }
        }
    }

    public int validarLogin(String usuario, String contraseña) {
        Usuario u = usuarios.get(usuario);
        if (u == null) {
            return USUARIO_CONTRA_INCORRECTOS; // Usuario no encontrado
        }
        if (u.esCuentaBloqueada()) {
            return USUARIO_BLOQUEADO; // Cuenta bloqueada
        }
        if (u.debeCambiarContraseña()) {
            return DEBE_CAMBIAR_CONTRASEÑA; // La contraseña debe ser cambiada
        }
        if (!u.getContraseña().equals(contraseña)) {
            u.setIntentosFallidos(u.getIntentosFallidos() + 1);
            if (u.getIntentosFallidos() >= 3) {
                u.setCuentaBloqueada(true); // Bloquear cuenta después de 3 intentos fallidos
            }
            return USUARIO_CONTRA_INCORRECTOS; // Contraseña incorrecta
        }
        // Resetear intentos fallidos si el login es exitoso
        u.setIntentosFallidos(0);
        return PUEDE_INGRESAR; // Login exitoso
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
        Diccionario de códigos:
        0 | Usuario o contraseña incorrectos
        1 | Usuario bloqueado
        2 | Usuario tiene que cambiar su contraseña
        3 | Usuario se logueó correctamente
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

    // Métodos para agregar y eliminar usuarios

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void agregarUsuario(Usuario nuevoUsuario) {
        usuarios.put(nuevoUsuario.getUsuario(), nuevoUsuario);
        // Aquí podrías agregar lógica para guardar los usuarios en el archivo de texto si es necesario
    }

    public void eliminarUsuario(String usuario) {
        usuarios.remove(usuario);
        // Aquí podrías agregar lógica para actualizar el archivo de texto si es necesario
    }

}
