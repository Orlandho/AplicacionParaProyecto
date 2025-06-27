/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Usuario.Usuario;

/**
 *
 * @author ORLANDO
 */
public class ModeloUsuario extends EstructuraModeloEstandar<Usuario> {

    public ModeloUsuario(String[] columnas, int[] columnasProhibidas) {
        super(columnas, columnasProhibidas);
    }

    @Override
    protected void actualizarRegistros(ArrayList<Usuario> tempList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void actualizarRegistros(ArrayList<Usuario> lista, DefaultTableModel mdl) {
        mdl.setRowCount(0);
        for (Usuario usu : lista) {
            Object[] datoFila = {usu.getUsuario_id(), usu.getNombres() + " " + usu.getApellidos(), usu.getUsuarioDNIoRUC(), usu.getContraseña(), usu.getRol(), usu.getTelefono(), Usuario.parseEsCuentaBloqueada(usu.esCuentaBloqueada())};
            mdl.addRow(datoFila);
        }
    }

    public static Integer getID(DefaultTableModel mdl, int filaSeleccion) {
        return Integer.parseInt(mdl.getValueAt(filaSeleccion, 0).toString());
    }
}
