/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import java.util.HashMap;
import javax.swing.JTable;

/**
 *
 * @author ORLANDO
 */
public class GestorModelos {
    private HashMap<JTable, EstructuraModeloEstandar> tablaModelo;

    public GestorModelos() {
        this.tablaModelo = new HashMap<>();
    }

    public void añadirTblUsuario(JTable tablaGUI){
        tablaModelo.put(tablaGUI, new ModeloUsuario(new String[]{"Usuario ID", "Empleado", "Usuario", "Contraseña", "Tipo", "Telefono", "Estado"}, new int[]{0}));
    }
    
    public void actualizarTblUsuario(){
        
    }
    
    public void añadirTblProducto(JTable tablaGUI){
        tablaModelo.put(tablaGUI,new ModeloProducto(new String[]{"ID", "Producto", "Cantidad", "Precio Unit.", "Sub. Total", "I.G.V.", "Total"}, new int[]{0,1,2,3,4,5,6}));
    }
    
    public void añadirTblProducto(JTable tablaGUI,String[] columnas, int[] columnasProhibidas){
        tablaModelo.put(tablaGUI,new ModeloProducto(columnas,columnasProhibidas));
    }
    
}
