/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ORLANDO
 */
public class MantenimientoLogin {

    //Devuelve true si el usuario cumple con el formato
    public static Boolean esDniORucValido(String usuario){
        return usuario.matches("\\d{8}")||usuario.matches("\\d{11}");
    }
    
    public static boolean esContraseñaValida(String contraseña) {
        Boolean esLargo= contraseña.length()>4;
        return contraseña.matches("[A-Za-z0-9]+")&&esLargo;
    }
    
    
}
