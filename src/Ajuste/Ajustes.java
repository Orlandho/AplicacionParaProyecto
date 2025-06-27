package Ajuste;

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
            // pnl1Inicio
            menu.getLblSubTitulo().setText("SUMMARY OF THE DAY");
            menu.getLblGanancias().setText("EARNINGS");
            menu.getLblProveedores().setText("  SUPPLIERS");
            menu.getLblCompraDelMes().setText("<html>PURCHASE<br>OF THE MONTH</html>");
            menu.getLblVentasDelDia().setText("SALES OF THE DAY");
            menu.getLblClientes().setText("CUSTOMERS");
            menu.getLblProductos().setText("PRODUCTS");
            // pnlRegUsuMostrar
            menu.getBtnCrearUsuario().setText("Create User");
            menu.getBtnEliminar().setText("Delete");
            menu.getBtnEditar().setText("Edit");
            // pnlRegUsuIngresar
            menu.getLblAgregaryCrearUsuario().setText("ADD AND CREATE USER");
            menu.getLblSubTitulo1().setText("• Create User");
            menu.getLblNombres().setText("Names:");
            menu.getLblTelefono().setText("Phone:");
            menu.getLblContraseña().setText("Password:");
            menu.getLblApellidos().setText("LastNames:");
            menu.getLblDNIUsuario().setText("ID/User:");
            menu.getLblTipoUsuario().setText("User Type:");
            menu.getBtnGuardaryAgregarDatos().setText("Save and Add Data");
            // pnl1Almacen
            menu.getLblSubtema2().setText("PRODUCT REGISTRATION:");
            menu.getLblProducto().setText("Product:");
            menu.getLblCantidaddeProducto().setText("Quantity of products:");
            menu.getLblPreciodeProducto().setText("Purchase Price:");
            menu.getBtnAgregarAlmacen().setText("Add");
            menu.getBtnEliminarAlmacen().setText("Delete");
            menu.getBtnEditarAlmacen().setText("Edit");
            menu.getBtnBuscarAlmacen().setText("Search");
            menu.getBtnFiltrarAlmacen().setText("Filter");
            // pnlRegComFactura
            menu.getLblsubtemaFactura().setText("INVOICE");
            menu.getLblSerieFactura().setText("Series:");
            menu.getLblProveedorFactura().setText("Supplier:");
            menu.getLblResponsableFactura().setText("Responsible:");
            menu.getblNumeroFactura().setText("Number:");
            menu.getLblMonedaFactura().setText("Currency:");
            menu.getLblFechaFactura().setText("Date:");
            menu.getBtnGuardarRegComFactura().setText("Save");
            menu.getBtnAgregarRegComFactura().setText("Add");
            menu.getBtnEliminarRegComFactura().setText("Delete");
            menu.getBtnEditarRegComFactura().setText("Edit");
            // pnlRegComBoleta
            menu.getLblsubtemaBoleta().setText("RECEIPT");
            menu.getLblSerieBoleta().setText("Series:");
            menu.getLblProveedorBoleta().setText("Supplier:");
            menu.getLblResponsableBoleta().setText("Responsible:");
            menu.getblNumeroBoleta().setText("Number:");
            menu.getLblMonedaBoleta().setText("Currency:");
            menu.getLblFechaBoleta().setText("Date:");
            menu.getBtnGuardarRegComBoleta().setText("Save");
            menu.getBtnAgregarRegComBoleta().setText("Add");
            menu.getBtnEliminarRegComBoleta().setText("Delete");
            menu.getBtnEditarRegComBoleta().setText("Edit");
            // pnlRegComProforma
            menu.getLblsubtemaProforma().setText("PROFORMA INVOICE");
            menu.getLblNombresProforma().setText("Names:");
            menu.getLblResponsableProforma().setText("Responsible:");
            menu.getLblMonedaProforma().setText("Currency:");
            menu.getLblFechaProforma().setText("Date:");
            menu.getBtnGuardarRegComProforma().setText("Save");
            menu.getBtnAgregarRegComProforma().setText("Add");
            menu.getBtnEliminarRegComProforma().setText("Delete");
            menu.getBtnEditarRegComProforma().setText("Edit");
            // pnlRegComCompromantesEmitidos
            menu.getLblSubtemaComprobantesEmitidos().setText("RECEIPTS ISSUED");
            menu.getBtnBuscarComprobantesEmitidos().setText("Search");
            menu.getBtnPDFComprobantesEmitidos().setText("PDF");
            menu.getBtnAnularComprobantesEmitidos().setText("Cancel");
            // pnlRegComAgregarProducto
            menu.getLblSubtemaAgregarProductoRegCom().setText("ADD PRODUCT");
            menu.getLblRegistrodeProductosRegCom().setText("• Product registrartion");
            menu.getLblProductoRegCom().setText("Product");
            menu.getLblCantidadRegCom().setText("Quantity");
            menu.getLblPreciioUnitarioRegCom().setText("UnitPrice:");
            menu.getBtnAgregarProductoRegCom().setText("Add Product");
            menu.getBtnCancelarRegCom().setText("Cancel");
            // pnlRegVenAgregarProducto
            menu.getLblSubtemaAgregarProductoRegVen().setText("ADD PRODUCT");
            menu.getLblAgregamosProductosRegVen().setText("• We add the products");
            menu.getLblProductoRegVen().setText("Product");
            menu.getLblCantidadRegVen().setText("Quantity");
            menu.getLblPreciioUnitarioRegVen().setText("UnitPrice:");
            menu.getBtnAgregarProductoRegVen().setText("Add Product");
            menu.getBtnCancelarRegVen().setText("Cancel");
            // pnlRegVenFactura
            menu.getLblsubtemaFacturaRegVen().setText("INVOICE");
            menu.getLblRucFacturaRegVen().setText("Ruc:");
            menu.getLblClienteFacturaRegVen().setText("Customer:");
            menu.getLblFechaFacturaRegVen().setText("Date:");
            menu.getLblSerieFacturaRegVen().setText("Series:");
            menu.getLblMonedaFacturaRegVen().setText("Currency:");
            menu.getLblNumeroFacturaRegVen().setText("Number:");
            menu.getBtnGuardarRegComFacturaRegVen().setText("Save");
            menu.getBtnAgregarRegComFacturaRegVen().setText("Add");
            menu.getBtnEliminarRegComFacturaRegVen().setText("Delete");
            menu.getBtnEditarRegComFacturaRegVen().setText("Edit");
            // pnlRegVenCompromantesEmitidos
            menu.getLblSubtemaComprobantesEmitidosRegVen().setText("RECEIPTS ISSUED");
            menu.getBtnBuscarComprobantesEmitidosRegVen().setText("Search");
            menu.getBtnPDFComprobantesEmitidosRegVen().setText("PDF");
            menu.getBtnAnularComprobantesEmitidosRegVen().setText("Cancel");
            // pnlCaja
            menu.getLblINGRESOScaja().setText("■   INCOME");
            menu.getLblEGRESOScaja().setText("■   EXPENSES");
            menu.getLblGananciascaja().setText("■   PROFITS");
            menu.getLblHistorialdeMovimientoscaja().setText("   Transaction History");
            menu.getBtnBusquedacaja().setText("Search");

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
            // pnl1Inicio
            menu.getLblSubTitulo().setText("RESUMEN DEL DÍA");
            menu.getLblGanancias().setText("GANANCIAS");
            menu.getLblProveedores().setText("PROVEEDORES");
            menu.getLblCompraDelMes().setText("COMPRA DEL MES");
            menu.getLblVentasDelDia().setText("VENTAS DEL DIA");
            menu.getLblClientes().setText("CLIENTES");
            menu.getLblProductos().setText("PRODUCTOS");
            // pnlRegUsuMostrar
            menu.getBtnCrearUsuario().setText("Crear Usuario");
            menu.getBtnEliminar().setText("Eliminar");
            menu.getBtnEditar().setText("Editar");
            // pnlRegUsuIngresar
            menu.getLblAgregaryCrearUsuario().setText("AGREGAR Y CREAR USUARIO");
            menu.getLblSubTitulo1().setText("• Creación de Usuario");
            menu.getLblNombres().setText("Nombres:");
            menu.getLblTelefono().setText("Telefono:");
            menu.getLblContraseña().setText("Contraseña:");
            menu.getLblApellidos().setText("Apellidos:");
            menu.getLblDNIUsuario().setText("DNI/Usuario:");
            menu.getLblTipoUsuario().setText("Tipo Usuario:");
            menu.getBtnGuardaryAgregarDatos().setText("Guardar y Agregar Datos");
            // pnl1Almacen
            menu.getLblSubtema2().setText("REGISTRO DE PRODUCTOS:");
            menu.getLblProducto().setText("Producto:");
            menu.getLblCantidaddeProducto().setText("Cantidad de productos:");
            menu.getLblPreciodeProducto().setText("Precio de compra:");
            menu.getBtnAgregarAlmacen().setText("Agregar");
            menu.getBtnEliminarAlmacen().setText("Eliminar");
            menu.getBtnEditarAlmacen().setText("Editar");
            menu.getBtnBuscarAlmacen().setText("Buscar");
            menu.getBtnFiltrarAlmacen().setText("Filtrar");
            // pnlRegComFactura
            menu.getLblsubtemaFactura().setText("FACTURA");
            menu.getLblSerieFactura().setText("Serie:");
            menu.getLblProveedorFactura().setText("Proveedor:");
            menu.getLblResponsableFactura().setText("Responsable:");
            menu.getblNumeroFactura().setText("Número:");
            menu.getLblMonedaFactura().setText("Moneda:");
            menu.getLblFechaFactura().setText("Fecha:");
            menu.getBtnGuardarRegComFactura().setText("Guardar");
            menu.getBtnAgregarRegComFactura().setText("Agregar");
            menu.getBtnEliminarRegComFactura().setText("Eliminar");
            menu.getBtnEditarRegComFactura().setText("Editar");
            // pnlRegComBoleta
            menu.getLblsubtemaBoleta().setText("FACTURA");
            menu.getLblSerieBoleta().setText("Serie:");
            menu.getLblProveedorBoleta().setText("Proveedor:");
            menu.getLblResponsableBoleta().setText("Responsable:");
            menu.getblNumeroBoleta().setText("Número:");
            menu.getLblMonedaBoleta().setText("Moneda:");
            menu.getLblFechaBoleta().setText("Fecha:");
            menu.getBtnGuardarRegComBoleta().setText("Guardar");
            menu.getBtnAgregarRegComBoleta().setText("Agregar");
            menu.getBtnEliminarRegComBoleta().setText("Eliminar");
            menu.getBtnEditarRegComBoleta().setText("Editar");
            // pnlRegComProforma
            menu.getLblsubtemaProforma().setText("PROFORMA");
            menu.getLblNombresProforma().setText("Nombres:");
            menu.getLblResponsableProforma().setText("Responsable:");
            menu.getLblMonedaProforma().setText("Moneda:");
            menu.getLblFechaProforma().setText("Fecha:");
            menu.getBtnGuardarRegComProforma().setText("Guardar");
            menu.getBtnAgregarRegComProforma().setText("Agregar");
            menu.getBtnEliminarRegComProforma().setText("Eliminar");
            menu.getBtnEditarRegComProforma().setText("Editar");
            // pnlRegComCompromantesEmitidos
            menu.getLblSubtemaComprobantesEmitidos().setText("COMPROBANTES EMITIDOS");
            menu.getBtnBuscarComprobantesEmitidos().setText("Buscar");
            menu.getBtnPDFComprobantesEmitidos().setText("PDF");
            menu.getBtnAnularComprobantesEmitidos().setText("Anular");
            // pnlRegComAgregarProducto
            menu.getLblSubtemaAgregarProductoRegCom().setText("AGREGAR PRODUCTO");
            menu.getLblRegistrodeProductosRegCom().setText("• Registro de los productos");
            menu.getLblProductoRegCom().setText("Producto");
            menu.getLblCantidadRegCom().setText("Cantidad");
            menu.getLblPreciioUnitarioRegCom().setText("Preciio Unitario:");
            menu.getBtnAgregarProductoRegCom().setText("Agregar producto");
            menu.getBtnCancelarRegCom().setText("Cancelar");
            // pnlRegVenAgregarProducto
            menu.getLblSubtemaAgregarProductoRegVen().setText("AGREGAR PRODUCTO");
            menu.getLblAgregamosProductosRegVen().setText("• Agregamos los productos");
            menu.getLblProductoRegVen().setText("Producto");
            menu.getLblCantidadRegVen().setText("Cantidad");
            menu.getLblPreciioUnitarioRegVen().setText("Preciio Unitario:");
            menu.getBtnAgregarProductoRegVen().setText("Agregar producto");
            menu.getBtnCancelarRegVen().setText("Cancelar");
            // pnlRegVenFactura
            menu.getLblsubtemaFacturaRegVen().setText("FACTURA");
            menu.getLblRucFacturaRegVen().setText("Ruc:");
            menu.getLblClienteFacturaRegVen().setText("Cliente:");
            menu.getLblFechaFacturaRegVen().setText("Fecha:");
            menu.getLblSerieFacturaRegVen().setText("Serie:");
            menu.getLblMonedaFacturaRegVen().setText("Moneda:");
            menu.getLblNumeroFacturaRegVen().setText("Numero:");
            menu.getBtnGuardarRegComFacturaRegVen().setText("Guardar");
            menu.getBtnAgregarRegComFacturaRegVen().setText("Agregar");
            menu.getBtnEliminarRegComFacturaRegVen().setText("Eliminar");
            menu.getBtnEditarRegComFacturaRegVen().setText("Editar");
            // pnlRegVenCompromantesEmitidos
            menu.getLblSubtemaComprobantesEmitidosRegVen().setText("COMPROBANTES EMITIDOS");
            menu.getBtnBuscarComprobantesEmitidosRegVen().setText("Buscar");
            menu.getBtnPDFComprobantesEmitidosRegVen().setText("PDF");
            menu.getBtnAnularComprobantesEmitidosRegVen().setText("Anular");
            // pnlCaja
            menu.getLblINGRESOScaja().setText("■   INGRESOS");
            menu.getLblEGRESOScaja().setText("■   EGRESOS");
            menu.getLblGananciascaja().setText("■   GANANCIAS");
            menu.getLblHistorialdeMovimientoscaja().setText("   Historial de movimientos");
            menu.getBtnBusquedacaja().setText("Busqueda");
            
        }
    }
}
