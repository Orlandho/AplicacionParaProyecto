/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import GestorDatosPermanentes.SQLiteManager;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Producto.Producto;
import javax.swing.JTextField;

/**
 *
 * @author ORLANDO
 */
public class ModeloProducto extends EstructuraModeloEstandar<Producto> {

    public ModeloProducto( String[] columnas, int[] columnasProhibidas) {
        super(columnas, columnasProhibidas);
    }

    @Override
    protected void actualizarRegistros(ArrayList<Producto> tempList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void actualizarRegistros(ArrayList<Producto> tempList,JTextField total){
        super.getModelo().setRowCount(0);
        double acumulador = 0;
        for (int i = 0; i < tempList.size(); i++) {
            Producto pro = tempList.get(i);
            Object[] datoFila = {i, pro.getProducto(), pro.getCantidad(), pro.getPrecioUnitario(), pro.getSubTotal(), pro.getIGV(), pro.getTotal()};
            super.getModelo().addRow(datoFila);
            acumulador += pro.getTotal();
        }
        total.setText(String.format("%.2f", acumulador));
    }
    
    public void actualizarRegsAlmacen(SQLiteManager bd){
        super.getModelo().setRowCount(0);
        ArrayList<Producto> lista = bd.obtenerProductos();

        for (Producto pro : lista) {
            Object[] datoFila = {pro.getID(), pro.getTipoDocumento(), pro.getProducto(), pro.getPrecioCompra(), pro.getCantidad(), pro.getStock()};
            super.getModelo().addRow(datoFila);
        }
    }
}
