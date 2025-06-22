/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuDinamico;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Usuario.Usuario;
import javax.swing.JTextField;
import GestorDatosPermanentes.SQLiteManager;

/**
 *
 * @author ORLANDO
 */
public class ModeloUsuario extends EstructuraModeloEstandar<Usuario> {

    public ModeloUsuario(String[] columnas, int[] columnasProhibidas) {
        super(columnas, columnasProhibidas);
    }
    
    @Override
    public void actualizarRegistros(ArrayList<Usuario> lista) {
        super.getModelo().setRowCount(0);
        for (Usuario usu : lista) {
            Object[] datoFila = {usu.getUsuario_id(), usu.getNombres() + " " + usu.getApellidos(), usu.getUsuarioDNIoRUC(), usu.getContraseña(), usu.getRol(), usu.getTelefono(), Usuario.parseEsCuentaBloqueada(usu.esCuentaBloqueada())};
            super.getModelo().addRow(datoFila);
        }
    }
    
}
