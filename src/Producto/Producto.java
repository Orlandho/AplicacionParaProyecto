package Producto;

public class Producto {

    // Atributos
    private String tipoDocumento;
    private String producto;
    private double precioCompra;
    private int cantidad;
    private String stock;

    public Producto(String tipoDocumento, String producto, double precioCompra, int cantidad, String stock) {
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public double getIGV() {
        return 0;
    }

    public double subTotal() {
        return 0;
    }

    public double total() {
        return 0;
    }

}
