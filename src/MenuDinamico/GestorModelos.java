/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import DocumentoComercial.ComprobanteEmitido;
import GestorDatosPermanentes.SQLiteManager;
import java.util.HashMap;
import javax.swing.JTable;
import MenuDinamico.ModeloProducto;
import MenuDinamico.ModeloUsuario;
import java.util.ArrayList;
import Producto.Producto;
import javax.swing.JTextField;

/**
 *
 * @author ORLANDO
 */
public class GestorModelos {
    private static HashMap<JTable, EstructuraModeloEstandar> tablaModelo;

    public GestorModelos() {
        this.tablaModelo = new HashMap<>();
    }

    public static void añadirTblUsuario(JTable tablaGUI){
        tablaModelo.put(tablaGUI, new ModeloUsuario(new String[]{"Usuario ID", "Empleado", "Usuario", "Contraseña", "Tipo", "Telefono", "Estado"}, new int[]{0}));
    }
    
    public static void añadirTblProducto(JTable tablaGUI){
        tablaModelo.put(tablaGUI,new ModeloProducto(new String[]{"ID", "Producto", "Cantidad", "Precio Unit.", "Sub. Total", "I.G.V.", "Total"}, new int[]{0,1,2,3,4,5,6}));
    }
    
    public static void añadirTblProducto(JTable tablaGUI,String[] columnas, int[] columnasProhibidas){
        tablaModelo.put(tablaGUI,new ModeloProducto(columnas,columnasProhibidas));
    }
    
    public static void actualizarTblAlmacen(JTable tblAlmacen, ArrayList<Producto> listaP){
        ModeloProducto.actualizarRegsAlmacen(tablaModelo.get(tblAlmacen).getModelo(),listaP);
    }
    
    public static void actualizarRegsCompVenProd(JTable tblDocCom,ArrayList<Producto> listaP,JTextField total){
        ModeloProducto.actualizarRegsCompVen(listaP,tablaModelo.get(tblDocCom).getModelo(),total);
    }
    
    public static void actualizarCompEmit(JTable tblDocCom,ArrayList<ComprobanteEmitido> listaP){
        ModeloProducto.actualizarCompEmit(tablaModelo.get(tblDocCom).getModelo(), listaP);
    }
    
    public static void buscarProductoAlm(JTable tbl, String txtBuscar){
        ModeloProducto.buscarProductoAlm(tablaModelo.get(tbl).getModelo(), txtBuscar);
    }
    
    
}
