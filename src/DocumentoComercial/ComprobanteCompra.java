/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocumentoComercial;

import java.time.LocalDate;

/**
 *
 * @author ORLANDO
 */
public class ComprobanteCompra {

    private int id;
    private LocalDate fechaRegistro;
    private String tipoComprobante;
    private long serie;
    private int numero;
    private String proveedor;
    private double total;

    public ComprobanteCompra(int id, LocalDate fechaRegistro, String tipoComprobante, long serie, int numero, String proveedor, double total) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numero = numero;
        this.proveedor = proveedor;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public long getSerie() {
        return serie;
    }

    public void setSerie(long serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
