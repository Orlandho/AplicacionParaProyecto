/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;

/**
 *
 * @author ORLANDO
 */
public class Empleado extends Usuario {

    public Empleado(String usuario, String contraseña, String rol, LocalDate fechaUltimoCambio, String nombre, String apellido, String genero, String direccion, String[] preguntas, String[] respuestas) {
        super(usuario, contraseña, rol, fechaUltimoCambio, nombre, apellido, genero, direccion, preguntas, respuestas);
    }
    
}
