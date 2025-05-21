/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author ORLANDO
 */
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BaseDeDatos {

    private Map<String, Usuario> usuarios;

    public BaseDeDatos() {
        usuarios = new HashMap<>();
        cargarUsuariosDePrueba();
    }

    private void cargarUsuariosDePrueba() {
        //usuario empleado de prueba
        //(String usuario, String contraseña, String rol, LocalDate fechaUltimoCambio, String nombre, String apellido, String genero, String direccion,String[] preguntas,String[] respuestas) {
        Usuario usuario = new Usuario("73034581", "12345", "empleado", LocalDate.of(2025, 5, 16), "Orlando", "Dorival", "El Macho", "jr.Los Olivos En USA", new String[]{"¿Cuantos Años tienes?", "¿Donde vives?"}, new String[]{"20", "en mi casa"});
        //usuarios administradores de prueba
        Usuario usuario2 = new Usuario("20603299494", "12345", "administrador", LocalDate.of(2025, 5, 16), "Alicia", "Nuñez", "femenino", "jr.Simpatica En Algun Lugar", new String[]{"¿Cuanto mides?", "¿Donde vives?"}, new String[]{"155", "cerca de casa"});
        Usuario usuario3 = new Usuario("12345678", "12345", "administrador", LocalDate.of(2025, 5, 16), "Danna", "Huaman", "femenino", "jr.Casita En Casa", new String[]{"¿Qué estudias?", "¿Donde vives?"}, new String[]{"Ingenieria de Sistemas", "dentro de mi casa"});
        //debe aparecerle la ventana para cambiar contraseña OBLIGATORIAMENTE
        Usuario usuario4 = new Usuario("98765432", "12345", "administrador", LocalDate.of(2025, 1, 16), "Luis", "Moreyra", "El Macho", "jr.Casita cerca de UPN", new String[]{"¿Qué juegas?", "¿Donde vives?"}, new String[]{"Pokemon TGCP", "cerca de UPN"});
        usuarios.put(usuario.getUsuario(), usuario);
        usuarios.put(usuario2.getUsuario(), usuario2);
        usuarios.put(usuario3.getUsuario(), usuario3);
        usuarios.put(usuario4.getUsuario(), usuario4);
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        return usuarios.get(nombreUsuario);
    }

    public boolean existeUsuario(String nombreUsuario) {
        return usuarios.containsKey(nombreUsuario);
    }

    public boolean esContraseñaCorrecta(Usuario usuarioEncontrado, String contraseña) {
        if (usuarioEncontrado.getContraseña().equals(contraseña)) {
            return true;
        }
        if (usuarioEncontrado.getIntentosFallidos() >= 5) {
            usuarioEncontrado.setCuentaBloqueada(true);
        }
        return false;

    }

    public boolean debeCambiarContraseña(Usuario usuario) {
        return (usuario.getRol().equalsIgnoreCase("administrador") && usuario.debeCambiarContraseña());
    }

    public boolean actualizarContraseña(String nombreUsuario, String antiguaContraseña, String nuevaContraseña) {
        if (existeUsuario(nombreUsuario)&&esContraseñaCorrecta(buscarUsuario(nombreUsuario), antiguaContraseña)) {
            usuarios.get(nombreUsuario).setContraseña(nuevaContraseña);
            //se actualizo la contraseña correctamente
            return true;
        }
        //no se pudo actualizar la contraseña
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
    public int intentarLogin(String usuarioIngresado, String contraseñaIngresada) {
        if (!existeUsuario(usuarioIngresado)) {
            return 0;
        }
        Usuario usuario = buscarUsuario(usuarioIngresado);
        if (usuario.esCuentaBloqueada()) {
            return 1;
        }
        if (!esContraseñaCorrecta(usuario, contraseñaIngresada)) {
            return 0;
        }
        if (debeCambiarContraseña(usuario)) {
            return 2;
        }
        return 3;
    }
    
    
    
}
