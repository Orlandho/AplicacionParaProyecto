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
public class ComprobanteVenta {

    private int id;
    private LocalDate fechaRegistro;
    private String tipoComprobante;
    private long serie;
    private int numero;
    private String cliente;
    private double total;

    public ComprobanteVenta(int id, LocalDate fechaRegistro, String tipoComprobante, long serie, int numero, String cliente, double total) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numero = numero;
        this.cliente = cliente;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
