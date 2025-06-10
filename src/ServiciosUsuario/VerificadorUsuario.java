/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiciosUsuario;

/**
 *
 * @author ORLANDO
 */
public class VerificadorUsuario {
    public static Boolean esDniORucValido(String usuario) {
        return usuario.matches("\\d{8}") || usuario.matches("\\d{11}");
    }

    public static boolean esContraseñaValida(String contraseña) {
        Boolean esLargo = contraseña.length() > 4;
        return contraseña.matches("[A-Za-z0-9]+") && esLargo;
    }
    public static boolean esTelefonoValido(int telefono){
        String telefonoMatch=Integer.toString(telefono);
        return telefonoMatch.matches("\\d{6}")||telefonoMatch.matches("\\d{9}");
    }
}
