/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocumentoComercial;

import java.util.ArrayList;
import Producto.Producto;

/**
 *
 * @author ORLANDO
 */
public class BoletaVenta {

    private int id;
    private long serie;
    private long numero;
    private String proveedor;
    private String moneda;
    private String responsable;
    private ArrayList<Producto> listaProductos;
    private double total;

    public BoletaVenta(int ID, long serie, long numero, String proveedor, String moneda, String responsable, ArrayList<Producto> listaProductos, double total) {
        this.id = ID;
        this.serie = serie;
        this.numero = numero;
        this.proveedor = proveedor;
        this.moneda = moneda;
        this.responsable = responsable;
        this.listaProductos = listaProductos;
        this.total = total;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSerie() {
        return serie;
    }

    public void setSerie(long serie) {
        this.serie = serie;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
