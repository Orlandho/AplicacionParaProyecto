/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ORLANDO
 */
public abstract class EstructuraModeloEstandar<T> {
    private String[] columnas;
    private DefaultTableModel modelo;


    private boolean esColumnaEditable(int columna,int[] columnasProhibidas){
            for (int columnaProhibida : columnasProhibidas) {
                if(columna==columnaProhibida){
                    return false;
                }
            }
            return true;
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
    
    
    
    protected abstract ArrayList<T> obtenerRegistros();
    protected abstract ArrayList<T> buscarCoincidencias(String clave);
}
