/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;

/**
 *
 * @author walri
 */
public class Usuario extends Persona{

    private String usuario;
    private String contraseña;
    private String rol;
    private LocalDate fechaUltimoCambio;
    private int intentosFallidos = 0;
    private boolean cuentaBloqueada = false;
    private String[] preguntasSeguridad = new String[2], respuestasSeguridad = new String[2];

    public Usuario(String usuario, String contraseña, String rol, LocalDate fechaUltimoCambio, String nombre, String apellido, String genero, String direccion, String[] preguntas, String[] respuestas) {
        super(nombre, apellido, genero, direccion);
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.fechaUltimoCambio = fechaUltimoCambio;
        if (preguntasSeguridad.length == 2 && respuestasSeguridad.length == 2) {
            System.arraycopy(preguntas, 0, this.preguntasSeguridad, 0, 2);
            System.arraycopy(respuestas, 0, this.respuestasSeguridad, 0, 2);
        } else {
            throw new IllegalArgumentException("Class Usuario: Constructor: array preguntas o respuestas exceden el tamaño");
        }
    }
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
        this.fechaUltimoCambio = LocalDate.now();
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getFechaUltimoCambio() {
        return fechaUltimoCambio;
    }

    public void setFechaUltimoCambio(LocalDate fechaUltimoCambio) {
        this.fechaUltimoCambio = fechaUltimoCambio;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getApellido() {
        return apellido;
    }

    @Override
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String getGenero() {
        return genero;
    }

    @Override
    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }
    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String[] getPreguntasSeguridad() {
        return preguntasSeguridad;
    }

    public void setPreguntasSeguridad(String[] preguntasSeguridad) {
        this.preguntasSeguridad = preguntasSeguridad;
    }

    public String[] getRespuestasSeguridad() {
        return respuestasSeguridad;
    }

    public void setRespuestasSeguridad(String[] respuestasSeguridad) {
        this.respuestasSeguridad = respuestasSeguridad;
    }

    public boolean esCuentaBloqueada() {
        return cuentaBloqueada;
    }

    public void setCuentaBloqueada(boolean cuentaBloqueada) {
        this.cuentaBloqueada = cuentaBloqueada;
    }

    public LocalDate getFechaLimite() {
        return fechaUltimoCambio.plusDays(60);
    }

    // Validación para cambio de contraseña obligatorio
    public boolean debeCambiarContraseña() {
        return fechaUltimoCambio.plusDays(60).isBefore(LocalDate.now());
    }
}
