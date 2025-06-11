package Usuario;

public abstract class Persona {

    protected String nombres, apellidos;
    protected int telefono;
    protected long DNIoRUC;

    public Persona(String nombres, String apellidos,long DNIoRUC,int telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.DNIoRUC=DNIoRUC;
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

    public long getDNIoRUC() {
        return DNIoRUC;
    }

    public void setDNIoRUC(long DNIoRUC) {
        this.DNIoRUC = DNIoRUC;
    }


}
