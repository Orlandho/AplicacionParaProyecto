package Usuario;

import java.time.LocalDate;

public class Usuario extends Persona {

    private int usuario_id;
    private long usuarioDNIoRUC;
    private String contraseña;
    private String rol;
    private LocalDate fechaUltimoCambio;
    private int intentosFallidos = 0;
    private boolean cuentaBloqueada = false;

    public Usuario(int usuario_id, long DNIoRUC, String contraseña,
            String rol, LocalDate fechaUltimoCambio, int intentosFallidos, boolean cuentaBloqueada,
            String nombres, String apellidos, int telefono) {
        super(nombres, apellidos, DNIoRUC, telefono);
        this.usuario_id = usuario_id;
        this.usuarioDNIoRUC = DNIoRUC;
        this.contraseña = contraseña;
        this.rol = rol;
        this.fechaUltimoCambio = fechaUltimoCambio;
        this.intentosFallidos = intentosFallidos;
        this.cuentaBloqueada = cuentaBloqueada;
    }

    public Usuario(Usuario usuario) {
        super(usuario.nombres, usuario.apellidos, usuario.usuarioDNIoRUC, usuario.telefono);
        this.usuarioDNIoRUC = usuario.usuarioDNIoRUC;
        this.contraseña = usuario.contraseña;
        this.rol = usuario.rol;
        this.fechaUltimoCambio = usuario.fechaUltimoCambio;

    }

    public long getUsuarioDNIoRUC() {
        return usuarioDNIoRUC;
    }

    public void setUsuarioDNIoRUC(long usuarioDNIoRUC) {
        this.usuarioDNIoRUC = usuarioDNIoRUC;
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

    public static Integer tryParseTelefono(String telefono) {
        int resultado;
        try {
            resultado = Integer.parseInt(telefono);
        } catch (NumberFormatException e) {
            return null;
        }
        return esTelefonoValido(resultado) ? resultado : null;
    }

    public static Long tryParseUsuarioDNI(String usuarioDNI) {
        long resultado;
        try {
            resultado = Long.parseLong(usuarioDNI);
        } catch (NumberFormatException e) {
            return null;
        }
        return esDniValido(usuarioDNI) ? resultado : null;
    }

    public static String parseEsCuentaBloqueada(boolean esCuentaBloqueada) {
        return esCuentaBloqueada ? "Inactivo" : "Activo";
    }

    public static boolean parseEsCuentaBloqueada(String esCuentaBloqueadaString) {
        return !esCuentaBloqueadaString.equals("Inactivo");
    }

    public static boolean esDniORucValido(String usuario) {
        return usuario.matches("\\d{8}") || usuario.matches("\\d{11}");
    }

    public static boolean esDniValido(String dni) {
        return dni.matches("\\d{8}");
    }

    public static boolean esContraseñaValida(String contraseña) {
        Boolean esLargo = contraseña.length() > 4;
        return contraseña.matches("[A-Za-z0-9]+") && esLargo && contraseña.length() < 31;
    }

    public static boolean esTelefonoValido(int telefono) {
        String telefonoMatch = Integer.toString(telefono);
        return telefonoMatch.matches("\\d{6}") || telefonoMatch.matches("\\d{9}");
    }

    public static boolean muyLargo(String texto) {
        return texto.length() > 20;
    }

}
