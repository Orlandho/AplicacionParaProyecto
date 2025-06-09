package Usuario;

import java.time.LocalDate;

public class Usuario extends Persona {

    private int usuario_id;
    private int usuarioDNI;
    private String contraseña;
    private String rol;
    private LocalDate fechaUltimoCambio;
    private int intentosFallidos = 0;
    private boolean cuentaBloqueada = false;

    public Usuario(int usuario_id, int dni, String contraseña,
            String rol, LocalDate fechaUltimoCambio,int intentosFallidos,boolean cuentaBloqueada,
            String nombres, String apellidos,int telefono) {
        super(nombres, apellidos,dni,telefono);
        this.usuario_id = usuario_id;
        this.usuarioDNI = dni;
        this.contraseña = contraseña;
        this.rol = rol;
        this.fechaUltimoCambio = fechaUltimoCambio;
        this.intentosFallidos=intentosFallidos;
        this.cuentaBloqueada=cuentaBloqueada;
    }

    

    public Usuario(Usuario usuario) {
        super(usuario.nombres, usuario.apellidos, usuario.usuarioDNI,usuario.telefono);
        this.usuarioDNI = usuario.usuarioDNI;
        this.contraseña = usuario.contraseña;
        this.rol = usuario.rol;
        this.fechaUltimoCambio = usuario.fechaUltimoCambio;

    }

    public int getUsuarioDNI() {
        return usuarioDNI;
    }

    public void setUsuarioDNI(int usuarioDNI) {
        this.usuarioDNI = usuarioDNI;
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

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
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
