/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocumentoComercial;

import Producto.Producto;
import java.util.ArrayList;

/**
 *
 * @author ORLANDO
 */
public class Proforma {

    private String nombres;
    private String responsable;
    private String moneda;
    String fecha;
    private ArrayList<Producto> listaProductos;
    private double total;

    public Proforma(String nombres, String responsable, String moneda, String fecha, ArrayList<Producto> listaProductos,double total) {
        this.nombres = nombres;
        this.responsable = responsable;
        this.moneda = moneda;
        this.fecha = fecha;
        this.listaProductos = listaProductos;
        this.total=total;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
