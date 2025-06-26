package ajustes;

import Login.FrmLogin;
import MenuDinamico.FrmMenuDinamico;

public class Ajustes {
        public static void cambiarAIngles(FrmLogin login, FrmMenuDinamico menu) {
        // Cambios en el Login
        if (login != null) {
            login.getBtnIngresar().setText("LOG IN");
        }

        // Cambios en el MenuDinamico
        if (menu != null) {
            menu.getLblInventario().setText("INVENTORY");
            menu.getBtnInicio().setText("HOME");
            menu.getBtnAlmacen().setText("WAREHOUSE");
            menu.getBtnRegistroDeCompras().setText("<html>PURCHASE<br>RECORD</html>");
            menu.getBtnRegistroDeVentas().setText("<html>SALES<br>RECORD</html>");
            menu.getBtnCaja().setText("CASHBOX");
            menu.getBtnRegistroDeUsuario().setText("<html>USER<br>RECORD</html>");
            menu.getBtnReportes().setText("REPORTS");
            menu.getBtnAjustes().setText("SETTINGS");
            menu.getBtnCerrarSesion().setText("<html>CLOSE<br>SESSION</html>");
        }
    }

    public static void cambiarAEspanol(FrmLogin login, FrmMenuDinamico menu) {
        // Login
        if (login != null) {
            login.getBtnIngresar().setText("INGRESAR");
        }

        // MenuDinamico
        if (menu != null) {
            menu.getLblInventario().setText("INVENTARIO");
            menu.getBtnInicio().setText("INICIO");
            menu.getBtnAlmacen().setText("ALMACÉN");
            menu.getBtnRegistroDeCompras().setText("<html>REGISTRO<br>DE COMPRAS</html>");
            menu.getBtnRegistroDeVentas().setText("<html>REGISTRO<br>DE VENTAS</html>");
            menu.getBtnCaja().setText("CAJA");
            menu.getBtnRegistroDeUsuario().setText("<html>REGISTRO<br>DE USUARIOS</html>");
            menu.getBtnReportes().setText("REPORTES");
            menu.getBtnAjustes().setText("AJUSTES");
            menu.getBtnCerrarSesion().setText("<html>CERRAR<br>SESION</html>");
        }
    }   
}
