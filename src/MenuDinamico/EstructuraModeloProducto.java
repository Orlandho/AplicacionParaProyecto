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
    private ArrayList<Producto> registros;

    
    
    public ArrayList<Producto> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Producto> registros) {
        this.registros = registros;
    }
    
    
}
