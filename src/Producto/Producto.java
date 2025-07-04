package Producto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Producto {

    // Atributos
    private int ID;
    private String tipoDocumento;
    private String producto;
    private double precioCompra;
    private int cantidad;
    private String stock;
    private double precioUnitario;

    public Producto(int ID, String tipoDocumento, String producto, double precioCompra, int cantidad, String stock) {
        this.ID = ID;
        this.tipoDocumento = tipoDocumento;
        this.producto = producto;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.stock = stock;
    }

    public Producto(String tipoDocumento, String producto, int cantidad, double precioUnitario) {
        this.tipoDocumento = tipoDocumento;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
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
        if (precioCompra == 0 && precioUnitario != 0) {
            return redondear2Decimales(precioUnitario * cantidad);
        }
        return redondear2Decimales(precioCompra);
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

    public double getPrecioUnitario() {
        if (precioUnitario == 0 && precioCompra != 0) {
            return redondear2Decimales(precioCompra / cantidad);
        }
        return redondear2Decimales(precioUnitario);
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = redondear2Decimales(precioUnitario);
    }

    public static Integer tryParseCantidad(String txtCantidad) {
        int resultado;
        try {
            resultado = Integer.parseInt(txtCantidad);
        } catch (NumberFormatException e) {
            return null;
        }
        return resultado;
    }

    public static Double tryParsePrecio(String txtPrecioCompra) {
        double resultado;
        try {
            resultado = Double.parseDouble(txtPrecioCompra);
        } catch (NumberFormatException e) {
            return null;
        }
        return resultado;
    }

    public static String calcularStock(int cantidad) {
        if (cantidad < 1) {
            return "Agotado";
        } else if (cantidad < 11) {
            return "Camino agotarse";
        }
        return "Disponible";
    }

    public String getStock() {
        return calcularStock(getCantidad());
    }

    public double getSubTotal() {
        return redondear2Decimales(getPrecioCompra() * 1.18);
    }

    public double getIGV() {
        return redondear2Decimales(getSubTotal() * 0.18);
    }

    public double getTotal() {
        return redondear2Decimales(getSubTotal() + getIGV());
    }

    public double redondear2Decimales(double precio) {
        
        if (Double.isNaN(precio) || Double.isInfinite(precio)) {
            return 0; 
        }
        return ((new BigDecimal(precio)).setScale(2, RoundingMode.HALF_UP)).doubleValue();
    }
}
