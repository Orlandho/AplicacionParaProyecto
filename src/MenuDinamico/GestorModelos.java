/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import DocumentoComercial.ComprobanteCompra;
import GestorDatosPermanentes.SQLiteManager;
import java.util.HashMap;
import javax.swing.JTable;
import MenuDinamico.ModeloProducto;
import MenuDinamico.ModeloUsuario;
import java.util.ArrayList;
import Producto.Producto;
import Usuario.Usuario;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ORLANDO
 */
public class GestorModelos {

    private static HashMap<JTable, EstructuraModeloEstandar> tablaModelo = new HashMap<>();

    public static void añadirTblUsuario(JTable tablaGUI) {
        ModeloUsuario mdl = new ModeloUsuario(new String[]{"Usuario ID", "Empleado", "Usuario", "Contraseña", "Tipo", "Telefono", "Estado"}, new int[]{0});
        tablaGUI.setModel(mdl.getModelo());
        tablaModelo.put(tablaGUI, mdl);
    }

    public static void añadirTblProducto(JTable tablaGUI) {
        ModeloProducto mdl = new ModeloProducto(new String[]{"ID", "Producto", "Cantidad", "Precio Unit.", "Sub. Total", "I.G.V.", "Total"}, new int[]{0, 4, 5, 6});
        tablaGUI.setModel(mdl.getModelo());
        tablaModelo.put(tablaGUI, mdl);
    }

    public static void añadirTblProducto(JTable tablaGUI, String[] columnas, int[] columnasProhibidas) {
        ModeloProducto mdl = new ModeloProducto(columnas, columnasProhibidas);
        tablaGUI.setModel(mdl.getModelo());
        tablaModelo.put(tablaGUI, mdl);
    }

    public static void actualizarTblAlmacen(JTable tblAlmacen, ArrayList<Producto> listaP) {
        ModeloProducto.actualizarRegsAlmacen(tablaModelo.get(tblAlmacen).getModelo(), listaP);
    }

    public static void actualizarRegsCompVenProd(JTable tblDocCom, ArrayList<Producto> listaP, JTextField total) {
        ModeloProducto.actualizarRegsCompVen(listaP, tablaModelo.get(tblDocCom).getModelo(), total);
    }

    public static void actualizarCompEmit(JTable tblDocCom, ArrayList<ComprobanteCompra> listaC) {
        ModeloProducto.actualizarCompEmit(tablaModelo.get(tblDocCom).getModelo(), listaC);
    }

    public static void actualizarUsuarios(JTable tblDocCom, ArrayList<Usuario> listaU) {
        ModeloUsuario.actualizarRegistros(listaU, tablaModelo.get(tblDocCom).getModelo());
    }

    public static void buscarProductoAlm(JTable tbl, String txtBuscar) {
        ModeloProducto.buscarProductoAlm(tablaModelo.get(tbl).getModelo(), txtBuscar);
    }

    public static void buscarCompEmit(JTable tbl, String txtBuscar, int col) {
        ModeloProducto.buscarCompEmit(tablaModelo.get(tbl).getModelo(), txtBuscar, col);
    }

    public static void buscarProductoAgotado(JTable tbl, SQLiteManager bd) {
        ModeloProducto.buscarProductoAgotado(tablaModelo.get(tbl).getModelo(), bd);
    }

    public static Integer getUsuarioID(JTable tbl, int filaSeleccionada) {
        return ModeloUsuario.getID(tablaModelo.get(tbl).getModelo(), filaSeleccionada);
    }

    public static Integer getProductoID(JTable tbl, int filaSeleccionada) {
        return ModeloProducto.getID(tablaModelo.get(tbl).getModelo(), filaSeleccionada);
    }

    public static boolean tieneCeldasVacias(JTable tbl, int fila) {
        for (int i = 0; i < tablaModelo.get(tbl).getModelo().getColumnCount(); i++) {
            if (tablaModelo.get(tbl).getModelo().getValueAt(fila, i).toString().isBlank()) {
                return true;
            }
        }
        return false;
    }

    public static Object getValueAt(JTable tbl, int filaSeleccion, int columnaSleccion) {
        return tablaModelo.get(tbl).getModelo().getValueAt(filaSeleccion, columnaSleccion);
    }

    public static void filtrarProductos(JTable tbl, JComboBox cb) {
        DefaultTableModel modelo = tablaModelo.get(tbl).getModelo();
        if (cb.getSelectedIndex() == 1) {
            //sin stock
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (modelo.getValueAt(i, 5).toString().equals("Disponible") || modelo.getValueAt(i, 5).toString().equals("Camino agotarse")) {
                    modelo.removeRow(i);
                    i--;
                }
            }
        } else if (cb.getSelectedIndex() == 2) {
            //con stock
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (modelo.getValueAt(i, 5).toString().equals("Agotado")) {
                    modelo.removeRow(i);
                    i--;
                }
            }
        }
    }
}
