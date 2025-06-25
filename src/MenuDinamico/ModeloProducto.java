/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import DocumentoComercial.ComprobanteEmitido;
import GestorDatosPermanentes.SQLiteManager;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Producto.Producto;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
    
    public static void actualizarRegsCompVen(ArrayList<Producto> tempList,DefaultTableModel mdl,JTextField total){
        mdl.setRowCount(0);
        double acumulador = 0;
        for (int i = 0; i < tempList.size(); i++) {
            Producto pro = tempList.get(i);
            Object[] datoFila = {i, pro.getProducto(), pro.getCantidad(), pro.getPrecioUnitario(), pro.getSubTotal(), pro.getIGV(), pro.getTotal()};
            mdl.addRow(datoFila);
            acumulador += pro.getTotal();
        }
        total.setText(String.format("%.2f", acumulador));
    }
    
    public static void actualizarRegsAlmacen(DefaultTableModel mdl,ArrayList<Producto> tempList){
        mdl.setRowCount(0);

        for (Producto pro : tempList) {
            Object[] datoFila = {pro.getID(), pro.getTipoDocumento(), pro.getProducto(), pro.getPrecioCompra(), pro.getCantidad(), pro.getStock()};
            mdl.addRow(datoFila);
        }
    }
    
    private static boolean coincideAlgo(String clave, String palabra) {
        int numCoinc;
        for (int i = 0; i <= (palabra.length() - clave.length()); i++) {
            numCoinc = 0;
            for (int j = 0; j < clave.length(); j++) {
                if (palabra.toLowerCase().charAt(i + j) == clave.toLowerCase().charAt(j)) {
                    numCoinc++;
                    continue;
                }
                break;
            }
            if (numCoinc == clave.length()) {
                return true;
            }
        }
        return false;
    }
    
    public static void buscarProductoAlm(DefaultTableModel mdl,String txtBuscar){
        for (int i = 0; i < mdl.getRowCount(); i++) {
            if (!coincideAlgo(txtBuscar, mdl.getValueAt(i, 2).toString())) {
                mdl.removeRow(i);
                i--;
            }
        }
    }
    
    public static void actualizarCompEmit(DefaultTableModel mdl,ArrayList<ComprobanteEmitido> tempList){
        mdl.setRowCount(0);
        for (int i = 0; i < tempList.size(); i++) {
            ComprobanteEmitido com = tempList.get(i);
            Object[] datoFila = {i, com.getId(), com.getFechaRegistro(), com.getTipoComprobante(), com.getSerie(), com.getNumero(), com.getProveedor(), com.getTotal()};
            mdl.addRow(datoFila);
        }
    }
    
    public static void buscarProductoAgotado(DefaultTableModel mdl, SQLiteManager bd){
        for (int i = 0; i < mdl.getRowCount(); i++) {
            if (mdl.getValueAt(i, 5).toString().equals("Agotado")) {
                Producto agotado = bd.buscarProducto(Integer.parseInt(mdl.getValueAt(i, 0).toString()));
                JOptionPane.showMessageDialog(null, "Se encontro un producto agotado: \nNombre:" + agotado.getProducto() + "\nTipo de documento: " + agotado.getTipoDocumento() + "\nPrecio de Compra: " + agotado.getPrecioCompra());
                return;
            }
        }
    }
    
    public static Integer getID(DefaultTableModel mdl,int filaSeleccion){
        return Integer.parseInt(mdl.getValueAt(filaSeleccion, 0).toString());
    }
}
