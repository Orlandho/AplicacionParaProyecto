
package Producto;

public class Producto {
    
    // Atributos
    private int ID;
    private String tipoDocumento;
    private String producto;
    private double precioCompra;
    private int cantidad;
    private String stock;

    public Producto(int ID, String tipoDocumento, String producto, double precioCompra, int cantidad, String stock) {
        this.ID = ID;
        this.tipoDocumento = tipoDocumento;
        this.producto = producto;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.stock = stock;
    }

    
    
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public static Integer tryParseCantidad(String txtCantidad){
        int resultado;
        try {
            resultado = Integer.parseInt(txtCantidad);
        } catch (NumberFormatException e) {
            return null;
        }
        return resultado;
    }
    public static Double tryParsePrecioCompra(String txtPrecioCompra){
        double resultado;
        try {
            resultado = Double.parseDouble(txtPrecioCompra);
        } catch (NumberFormatException e) {
            return null;
        }
        return resultado;
    }
    
    public static String calcularStock(int cantidad){
        if(cantidad<1){
            return "Agotado";
        }else if(cantidad<11){
            return "Camino agotarse";
        }
        return "Disponible";
    }
    
    public String getStock(){
        return calcularStock(getCantidad());
    }
}
