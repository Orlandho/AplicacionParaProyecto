/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Seguridad;

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
        Usuario usuario = new Usuario("73034581", "12345", "empleado", LocalDate.of(2025, 5, 16));
        //usuarios administradores de prueba
        Usuario usuario2 = new Usuario("20603299494", "12345", "administrador", LocalDate.of(2025, 5, 16));
        Usuario usuario3 = new Usuario("12345678", "12345", "administrador", LocalDate.of(2025, 5, 16));
        //debe aparecerle la ventana para cambiar contraseña OBLIGATORIAMENTE
        Usuario usuario4 = new Usuario("98765432", "12345", "administrador", LocalDate.of(2025, 1, 16));
        usuarios.put(usuario.getUsuario(), usuario);
        usuarios.put(usuario2.getUsuario(),usuario2);
        usuarios.put(usuario3.getUsuario(),usuario3);
        usuarios.put(usuario4.getUsuario(),usuario4);
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        return usuarios.get(nombreUsuario);
    }

    public boolean existeUsuario(String nombreUsuario) {
        return usuarios.containsKey(nombreUsuario);
    }

    public void actualizarContraseña(String nombreUsuario, String nuevaContraseña) {
        if (existeUsuario(nombreUsuario)) {
            usuarios.get(nombreUsuario).setContraseña(nuevaContraseña);
        }
    }
}
