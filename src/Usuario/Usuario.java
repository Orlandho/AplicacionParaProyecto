package Usuario;

import java.time.LocalDate;

public class Usuario extends Persona {

    private String usuario;
    private String contraseña;
    private String rol;
    private LocalDate fechaUltimoCambio;
    private int intentosFallidos = 0;
    private boolean cuentaBloqueada = false;

    public Usuario(String usuario, String contraseña, String rol, LocalDate fechaUltimoCambio, String nombre, String apellido, String genero, String direccion) {
        super(nombre, apellido, genero, direccion);
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.fechaUltimoCambio = fechaUltimoCambio;
    }

    public Usuario(Usuario usuario) {
        super(usuario.nombre, usuario.apellido, usuario.genero, usuario.direccion);
        this.usuario = usuario.usuario;
        this.contraseña = usuario.contraseña;
        this.rol = usuario.rol;
        this.fechaUltimoCambio = usuario.fechaUltimoCambio;

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

    public boolean esCuentaBloqueada() {
        return cuentaBloqueada;
    }

    public void setCuentaBloqueada(boolean cuentaBloqueada) {
        this.cuentaBloqueada = cuentaBloqueada;
    }

    public LocalDate getFechaLimite() {
        return fechaUltimoCambio.plusDays(60);
    }

    public boolean debeCambiarContraseña() {
        return fechaUltimoCambio.plusDays(60).isBefore(LocalDate.now());
    }
}
