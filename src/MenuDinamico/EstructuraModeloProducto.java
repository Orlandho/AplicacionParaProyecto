/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Producto.Producto;

/**
 *
 * @author ORLANDO
 */
public class EstructuraModeloProducto {
    private String[] columnas;
    private DefaultTableModel modelo;
    private ArrayList<Producto> registros;

    public EstructuraModeloProducto(String[] columnas, DefaultTableModel modelo, ArrayList<Producto> registros) {
        this.columnas = columnas;
        this.modelo = modelo;
        this.registros = registros;
    }

    public EstructuraModeloProducto(String[] columnas, ArrayList<Producto> registros,int[] columnasProhibidas) {
        this.columnas = columnas;
        this.registros = registros;
        this.modelo=new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int colum) {
                    return esColumnaEditable(colum,columnasProhibidas);
                }
            };
    }
    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public ArrayList<Producto> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Producto> registros) {
        this.registros = registros;
    }
    
    private boolean esColumnaEditable(int columna,int[] columnasProhibidas){
        for (int columnaProhibida : columnasProhibidas) {
            if(columna==columnaProhibida){
                return false;
            }
        }
        return true;
    }
    
}
