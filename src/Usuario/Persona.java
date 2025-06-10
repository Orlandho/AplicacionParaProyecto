package Usuario;

public abstract class Persona {

    protected String nombres, apellidos;
    protected int dni,telefono;

    public Persona(String nombres, String apellidos,int dni,int telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni=dni;
        this.telefono=telefono;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

}
