package Usuario;

public abstract class Persona {

    protected String nombres, apellidos;
    private int dni;

    public Persona(String nombres, String apellidos,int dni) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni=dni;
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
