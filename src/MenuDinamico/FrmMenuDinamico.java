package MenuDinamico;
//Autor: orlando el pro

import DocumentoComercial.ComprobanteCompra;
import Usuario.Usuario;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import GestorDatosPermanentes.SQLiteManager;
import java.util.ArrayList;
import Login.FrmLogin;
import java.time.LocalDate;
import javax.swing.JTable;
import Producto.Producto;
import javax.swing.JTextField;
import MenuDinamico.GestorModelos;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.File;
import java.sql.SQLException;
import java.sql.PreparedStatement;
//añadido
import java.util.List;
//hasta aca
import javax.swing.JButton;
import javax.swing.JToggleButton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class FrmMenuDinamico extends javax.swing.JFrame {

    private Usuario usuario;
    private SQLiteManager baseDeDatos;

    private final int pnlInicio = 0;
    private final int pnlRegUsu = 1;
    private final int pnlCrearUsu = 2;
    private final int pnlAlmac = 3;
    private final int pnlComFact = 4;
    private final int pnlComBole = 5;
    private final int pnlComProf = 6;
    private final int pnlCompEmit = 7;
    private final int pnlAgrComProd = 8;
    private final int pnlAgrVenProd = 9;
    private final int pnlVenFactura = 10;
    private final int pnlVenBoleta = 13;
    private final int pnlVenCompEmit = 11;
    private final int iPnlCaja = 12;
    private final int iPnlReportes = 14;
    private final int pnlAjuste = 15;
    private final int iPnlVenProforma = 16;

    private String[] colsRegProd = {"Producto ID", "Tipo de Documento", "Producto", "Precio Compra", "Cantidad", "Stock"};
    private String[] colsRegFactBoleProf = {"ID", "Producto", "Cantidad", "Precio Unit.", "Sub. Total", "I.G.V.", "Total"};
    private ArrayList<Producto> tempListFact;
    private ArrayList<Producto> tempListBole;
    private ArrayList<Producto> tempListProf;
    private ArrayList<Producto> tempListVenFact;
    private ArrayList<Producto> tempListVenBole;
    private ArrayList<Producto> tempListVenProf;
    private int pnlPadreComVenProd;
    private String[] colsRegCompComprEmitid = {"N°", "ID", "Fecha de registro", "Tipo de comprobante", "Serie", "Número", "Proveedor", "Total"};
    private int indiceRegCompComprEmitid = 5;
    private ArrayList<ComprobanteCompra> tempListComprEmitid;
    private JTable tblPadre;

    public FrmMenuDinamico() {
        initComponents();
        //esto coloque para el cmabio de idioma
        //Ajuste.Ajustes.cambiarAIngles(null, this);
        //hasta aca
        jpaneladmin.setVisible(false);
        baseDeDatos = new SQLiteManager();

        GestorModelos.añadirTblUsuario(tblRegistroUsuarios);
        //configuracion personalizada para tblRegistroProductos
        GestorModelos.añadirTblProducto(tblRegistroProductos, colsRegProd, new int[]{0, 1, 5});
        GestorModelos.añadirTblProducto(tblRegistroFactura);
        GestorModelos.añadirTblProducto(tblRegistroFactura);
        GestorModelos.añadirTblProducto(tblRegistroBoleta);
        GestorModelos.añadirTblProducto(tblRegistroProforma);
        //configuracion personalizada para tblRegistrodeComprobantesEmitidos
        GestorModelos.añadirTblProducto(tblRegistrodeComprobantesEmitidos, colsRegCompComprEmitid, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        GestorModelos.añadirTblProducto(tblRegistroFacturaRegVen);
        GestorModelos.añadirTblProducto(tblRegistroBoletaRegVen);
        GestorModelos.añadirTblProducto(tblRegistroProformaRegVen);
        GestorModelos.añadirTblProducto(tblRegistrodeComprobantesEmitidosRegVen, new String[]{"N°", "ID", "Fecha de registro", "Tipo de comprobante", "Serie", "Número", "Cliente", "Total"}, new int[]{0, 1, 2, 3, 4, 5, 6, 7});

        tempListFact = new ArrayList<>();
        tempListBole = new ArrayList<>();
        tempListProf = new ArrayList<>();
        tempListVenFact = new ArrayList<>();
        tempListVenBole = new ArrayList<>();
        tempListVenProf = new ArrayList<>();
        tempListComprEmitid = new ArrayList<>();
        tempListVenProf = new ArrayList<>();
        tblPadre = null;
        leerLenguaje();
    }
    
    private void leerLenguaje(){
        String[] configuracion=baseDeDatos.obtenerAjustes();
        if(configuracion[0].equals("ingles")){
            Ajuste.Ajustes.cambiarAIngles(null, this);
        }
    }

    public void modificarSegunRol(Usuario usuario) {
        this.usuario = new Usuario(usuario);
        if (this.usuario.getRol().equalsIgnoreCase("administrador")) {
            jpaneladmin.setVisible(true);
            jpaneladmin.setEnabled(true);
        }
        lblRol.setText(usuario.getRol());
    }

    /**
     * This method is called from somewhere so the constructor can initialize
     * the start of the form from the beginning. WARNING: Do NOT modify this
     * code unless you wanna this program to blow up your PC. The content of
     * this method is always blowable regenerated by Orlando the Joker ;v.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RegistroDeCompra = new javax.swing.JPopupMenu();
        jmFACTURASRegCom = new javax.swing.JMenuItem();
        jmBOLETASRegCom = new javax.swing.JMenuItem();
        jmOTROSRegCom = new javax.swing.JMenuItem();
        jmCOMPROBATESEMITIDOSRegCom = new javax.swing.JMenuItem();
        RegistroDeVenta = new javax.swing.JPopupMenu();
        jmFACTURASRegVen = new javax.swing.JMenuItem();
        jmBOLETASRegVen = new javax.swing.JMenuItem();
        jmOTROSRegVen = new javax.swing.JMenuItem();
        jmCOMPROBATESEMITIDOSRegVen = new javax.swing.JMenuItem();
        jpanelgeneral = new javax.swing.JPanel();
        jpaneladmin = new javax.swing.JPanel();
        btncaja = new javax.swing.JLabel();
        btnregistrodeusuario = new javax.swing.JLabel();
        btnreportes = new javax.swing.JLabel();
        btnajustes = new javax.swing.JLabel();
        btninicio = new javax.swing.JLabel();
        btnAlmacen = new javax.swing.JLabel();
        btnregistrodecompras = new javax.swing.JLabel();
        btnregistrodeventas = new javax.swing.JLabel();
        btncerrarsesion = new javax.swing.JLabel();
        lblRegCompras = new javax.swing.JLabel();
        lblRegCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/signoMas.png")));
        lblRegCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRegCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistroDeCompra.show(lblRegCompras, 0, lblRegCompras.getHeight());
            }
        });

        lblRegVentas = new javax.swing.JLabel();
        lblRegVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/signoMas.png")));
        lblRegVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRegVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistroDeVenta.show(lblRegVentas, 0, lblRegVentas.getHeight());
            }
        });

        jpanelsuperior = new javax.swing.JPanel();

        lblinventario = new javax.swing.JLabel();
        lblinventario.setFont(new java.awt.Font("Segoe UI", 1, 36));
        lblinventario.setForeground(new java.awt.Color(255, 255, 255));
        lblinventario.setText("SISTEMA DE INVENTARIO");

        lblimagen1 = new javax.swing.JLabel();
        lblimagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoEmpresa removebg.png")));

        lblimagen2 = new javax.swing.JLabel();
        lblimagen2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuariovl2.png")));

        lblRUC = new javax.swing.JLabel();
        lblRUC.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblRUC.setForeground(new java.awt.Color(255, 255, 255));
        lblRUC.setText("RUC: 20602598745");

        lblRol = new javax.swing.JLabel();
        lblRol.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblRol.setForeground(new java.awt.Color(255, 255, 255));
        tpnMostrar = new javax.swing.JTabbedPane();
        pnl1Inicio = new javax.swing.JPanel();
        lblSubTitulo = new javax.swing.JLabel();
        pnlProductos = new javax.swing.JPanel();
        lblProductos = new javax.swing.JLabel();
        txtProductos = new javax.swing.JTextField();
        pnlGancias = new javax.swing.JPanel();
        lblGanancias = new javax.swing.JLabel();
        txtGanancias = new javax.swing.JTextField();
        pnlProveedores = new javax.swing.JPanel();
        lblProveedores = new javax.swing.JLabel();
        txtProveedores = new javax.swing.JTextField();
        pnlCompraDelMes = new javax.swing.JPanel();
        lblCompraDelMes = new javax.swing.JLabel();
        txtComprasDelMes = new javax.swing.JTextField();
        pnlVentasDelDia = new javax.swing.JPanel();
        lblVentasDelDia = new javax.swing.JLabel();
        txtVentasDelDia = new javax.swing.JTextField();
        pnlCientes = new javax.swing.JPanel();
        lblClientes = new javax.swing.JLabel();
        txtClientes = new javax.swing.JTextField();
        pnlRegUsuMostrar = new javax.swing.JPanel();
        btnCrearUsuario = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistroUsuarios = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        pnlRegUsuIngresar = new javax.swing.JPanel();
        lblAgregaryCrearUsuario = new javax.swing.JLabel();
        lblSubTitulo1 = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblDNIUsuario = new javax.swing.JLabel();
        txtDNIUsuario = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        lblTipoUsuario = new javax.swing.JLabel();
        cbTipoUsuario = new javax.swing.JComboBox<>();
        btnGuardaryAgregarDatos = new javax.swing.JButton();
        rbtActivo = new javax.swing.JRadioButton();
        rbtInactivo = new javax.swing.JRadioButton();
        pnl1Almacen = new javax.swing.JPanel();
        lblSubtema2 = new javax.swing.JLabel();
        cbTipoStock = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistroProductos = new javax.swing.JTable();
        lblPreciodeProducto = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lblCantidaddeProducto = new javax.swing.JLabel();
        btnBuscarAlmacen = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnAgregarAlmacen = new javax.swing.JButton();
        btnEliminarAlmacen = new javax.swing.JButton();
        btnEditarAlmacen = new javax.swing.JButton();
        txtPreciodeCompra = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtCantidaddeProducto = new javax.swing.JTextField();
        btnFiltrarAlmacen = new javax.swing.JButton();
        lblImagen1 = new javax.swing.JLabel();
        pnlRegComFactura = new javax.swing.JPanel();
        lblsubtemaFactura = new javax.swing.JLabel();
        lblSerieFactura = new javax.swing.JLabel();
        txtSerieFactura = new javax.swing.JTextField();
        lblNumeroFactura = new javax.swing.JLabel();
        txtFechaFactura = new javax.swing.JTextField();
        lblFechaFactura = new javax.swing.JLabel();
        txtProveedorFactura = new javax.swing.JTextField();
        lblProveedorFactura = new javax.swing.JLabel();
        lblMonedaFactura = new javax.swing.JLabel();
        lblResponsableFactura = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        cbTipoDeLiderFactura = new javax.swing.JComboBox<>();
        cbTipoDeDineroFactura = new javax.swing.JComboBox<>();
        pnlInternRegComFactura = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRegistroFactura = new javax.swing.JTable();
        txtDecoracion1Factura = new javax.swing.JTextField();
        txtDecoracion2Factura = new javax.swing.JTextField();
        txtTotalFactura = new javax.swing.JTextField();
        btnEditarRegComFactura = new javax.swing.JButton();
        btnGuardarRegComFactura = new javax.swing.JButton();
        btnAgregarRegComFactura = new javax.swing.JButton();
        btnEliminarRegComFactura = new javax.swing.JButton();
        pnlRegComBoleta = new javax.swing.JPanel();
        lblsubtemaBoleta = new javax.swing.JLabel();
        lblSerieBoleta = new javax.swing.JLabel();
        txtSerieBoleta = new javax.swing.JTextField();
        lblNumeroBoleta = new javax.swing.JLabel();
        txtFechaBoleta = new javax.swing.JTextField();
        lblFechaBoleta = new javax.swing.JLabel();
        txtProveedorBoleta = new javax.swing.JTextField();
        lblProveedorBoleta = new javax.swing.JLabel();
        lblMonedaBoleta = new javax.swing.JLabel();
        lblResponsableBoleta = new javax.swing.JLabel();
        txtNumeroBoleta = new javax.swing.JTextField();
        cbTipoDeLiderBoleta = new javax.swing.JComboBox<>();
        cbTipoDeDineroBoleta = new javax.swing.JComboBox<>();
        pnlInternoRegComBoleta = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRegistroBoleta = new javax.swing.JTable();
        txtDecoracion1Boleta = new javax.swing.JTextField();
        txtTotalBoleta = new javax.swing.JTextField();
        txtDecoracion2Boleta = new javax.swing.JTextField();
        btnEditarRegComBoleta = new javax.swing.JButton();
        btnGuardarRegComBoleta = new javax.swing.JButton();
        btnAgregarRegComBoleta = new javax.swing.JButton();
        btnEliminarRegComBoleta = new javax.swing.JButton();
        pnlRegComProforma = new javax.swing.JPanel();
        lblsubtemaProforma = new javax.swing.JLabel();
        txtFechaProforma = new javax.swing.JTextField();
        lblFechaProforma = new javax.swing.JLabel();
        lblNombresProforma = new javax.swing.JLabel();
        lblMonedaProforma = new javax.swing.JLabel();
        lblResponsableProforma = new javax.swing.JLabel();
        cbTipoDeLiderProforma = new javax.swing.JComboBox<>();
        cbTipoDeDineroProforma = new javax.swing.JComboBox<>();
        pnlInternoRegComProforma = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRegistroProforma = new javax.swing.JTable();
        txtDecoracion1Proforma = new javax.swing.JTextField();
        txtTotalProforma = new javax.swing.JTextField();
        txtDecoracion2Proforma = new javax.swing.JTextField();
        btnEditarRegComProforma = new javax.swing.JButton();
        btnGuardarRegComProforma = new javax.swing.JButton();
        btnAgregarRegComProforma = new javax.swing.JButton();
        btnEliminarRegComProforma = new javax.swing.JButton();
        txtNombresProforma = new javax.swing.JTextField();
        pnlRegComCompromantesEmitidos = new javax.swing.JPanel();
        lblSubtemaComprobantesEmitidos = new javax.swing.JLabel();
        btnBuscarComprobantesEmitidos = new javax.swing.JButton();
        btnPDFComprobantesEmitidos = new javax.swing.JButton();
        btnAnularComprobantesEmitidos = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblRegistrodeComprobantesEmitidos = new javax.swing.JTable();
        lblimagen1ComprobantesEmitidos = new javax.swing.JLabel();
        cbTipodeBusquedaComprobantesEmitidos = new javax.swing.JComboBox<>();
        txtBuscarComprobantesEmitidos = new javax.swing.JTextField();
        pnlRegComAgregarProducto = new javax.swing.JPanel();
        lblSubtemaAgregarProductoRegCom = new javax.swing.JLabel();
        lblRegistrodeProductosRegCom = new javax.swing.JLabel();
        lblPreciioUnitarioRegCom = new javax.swing.JLabel();
        txtPreciioUnitarioRegCom = new javax.swing.JTextField();
        txtProductoRegCom = new javax.swing.JTextField();
        txtCantidadRegCom = new javax.swing.JTextField();
        btnCancelarRegCom = new javax.swing.JButton();
        btnAgregarProductoRegCom = new javax.swing.JButton();
        lblProductoRegCom = new javax.swing.JLabel();
        lblCantidadRegCom = new javax.swing.JLabel();
        pnlRegVenAgregarProducto = new javax.swing.JPanel();
        lblSubtemaAgregarProductoRegVen = new javax.swing.JLabel();
        lblAgregamosProductosRegVen = new javax.swing.JLabel();
        lblPreciioUnitarioRegVen = new javax.swing.JLabel();
        txtPreciioUnitarioRegVen = new javax.swing.JTextField();
        txtCantidadRegVen = new javax.swing.JTextField();
        btnCancelarRegVen = new javax.swing.JButton();
        btnAgregarProductoRegVen = new javax.swing.JButton();
        lblProductoRegVen = new javax.swing.JLabel();
        lblCantidadRegVen = new javax.swing.JLabel();
        cbTipoProductosRegVen = new javax.swing.JComboBox<>();
        pnlRegVenFactura = new javax.swing.JPanel();
        lblsubtemaFacturaRegVen = new javax.swing.JLabel();
        lblRucFacturaRegVen = new javax.swing.JLabel();
        txtRucFacturaRegVen = new javax.swing.JTextField();
        lblSerieFacturaRegVen = new javax.swing.JLabel();
        txtFechaFacturaRegVen = new javax.swing.JTextField();
        lblFechaFacturaRegVen = new javax.swing.JLabel();
        txtClienteFacturaRegVen = new javax.swing.JTextField();
        lblClienteFacturaRegVen = new javax.swing.JLabel();
        lblNumeroFacturaRegVen = new javax.swing.JLabel();
        lblMonedaFacturaRegVen = new javax.swing.JLabel();
        txtNumeroFacturaRegVen = new javax.swing.JTextField();
        cbTipoDeDineroFacturaRegVen = new javax.swing.JComboBox<>();
        pnlInternRegComFacturaRegVen = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblRegistroFacturaRegVen = new javax.swing.JTable();
        txtDecoracion1FacturaRegVen = new javax.swing.JTextField();
        txtDecoracion2FacturaRegVen = new javax.swing.JTextField();
        txtTotalFacturaRegVen = new javax.swing.JTextField();
        btnEditarRegComFacturaRegVen = new javax.swing.JButton();
        btnGuardarRegComFacturaRegVen = new javax.swing.JButton();
        btnAgregarRegComFacturaRegVen = new javax.swing.JButton();
        btnEliminarRegComFacturaRegVen = new javax.swing.JButton();
        txtSerieFacturaRegVen = new javax.swing.JTextField();
        lblImagen2FacturaRegVen = new javax.swing.JLabel();
        lblImagen1FacturaRegVen = new javax.swing.JLabel();
        lblimagen2factura = new javax.swing.JLabel();
        lblimagen1factura = new javax.swing.JLabel();
        pnlRegVenCompromantesEmitidos = new javax.swing.JPanel();
        lblSubtemaComprobantesEmitidosRegVen = new javax.swing.JLabel();
        btnBuscarComprobantesEmitidosRegVen = new javax.swing.JButton();
        btnPDFComprobantesEmitidosRegVen = new javax.swing.JButton();
        btnAnularComprobantesEmitidosRegVen = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblRegistrodeComprobantesEmitidosRegVen = new javax.swing.JTable();
        lblimagen1ComprobantesEmitidosRegVen = new javax.swing.JLabel();
        cbTipodeBusquedaComprobantesEmitidosRegVen = new javax.swing.JComboBox<>();
        txtBuscarComprobantesEmitidosRegVen = new javax.swing.JTextField();
        pnlCaja = new javax.swing.JPanel();
        lblGananciascaja = new javax.swing.JLabel();
        lblINGRESOScaja = new javax.swing.JLabel();
        lblEGRESOScaja = new javax.swing.JLabel();
        pnlInternodecaja = new javax.swing.JPanel();
        lblimagen1caja = new javax.swing.JLabel();
        txtBusquedacaja = new javax.swing.JTextField();
        lblEgresoscaja = new javax.swing.JTextField();
        btnBusquedacaja = new javax.swing.JToggleButton();
        lblHistorialdeMovimientoscaja = new javax.swing.JTextField();
        lblIngresoscaja = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblRegistrodeCaja = new javax.swing.JTable();
        txtGANACIAScaja = new javax.swing.JTextField();
        txtINGRESOScaja = new javax.swing.JTextField();
        txtEGRESOScaja = new javax.swing.JTextField();
        Grafico_Caja = new javax.swing.JPanel();
        btn_Grafico_Caja = new javax.swing.JButton();
        pnlRegVenBoleta = new javax.swing.JPanel();
        lblsubtemaBoletaRegVen = new javax.swing.JLabel();
        lblOPBoletaRegVen = new javax.swing.JLabel();
        lblSerieBoletaRegVen = new javax.swing.JLabel();
        txtFechaBoletaRegVen = new javax.swing.JTextField();
        lblFechaBoletaRegVen = new javax.swing.JLabel();
        txtClienteBoletaRegVen = new javax.swing.JTextField();
        lblClienteBoletaRegVen = new javax.swing.JLabel();
        lblNumeroBoletaRegVen = new javax.swing.JLabel();
        lblMonedaBoletaRegVen = new javax.swing.JLabel();
        txtNumeroBoletaRegVen = new javax.swing.JTextField();
        cbTipoDeDineroBoletaRegVen = new javax.swing.JComboBox<>();
        pnlInternoBoletaRegVen = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblRegistroBoletaRegVen = new javax.swing.JTable();
        txtDecoracion1BoletaRegVen = new javax.swing.JTextField();
        txtDecoracion2BoletaRegVen = new javax.swing.JTextField();
        txtTotalBoletaRegVen = new javax.swing.JTextField();
        btnEditarBoletaRegVen = new javax.swing.JButton();
        btnGuardarBoletaRegVen = new javax.swing.JButton();
        btnAgregarBoletaRegVen = new javax.swing.JButton();
        btnEliminarBoletaRegVen = new javax.swing.JButton();
        txtSerieBoletaRegVen = new javax.swing.JTextField();
        lblImagen2BoletaRegVen = new javax.swing.JLabel();
        lblImagen1BoletaRegVen = new javax.swing.JLabel();
        lblimagen2factura1 = new javax.swing.JLabel();
        lblimagen1factura1 = new javax.swing.JLabel();
        cbTipoOPBoletaRegVen = new javax.swing.JComboBox<>();
        pnlReportes = new javax.swing.JPanel();
        lblREPORTEREGISTRODEVENTASReportes = new javax.swing.JLabel();
        txtRangodeperiodoReportes = new javax.swing.JTextField();
        lblRangodeperiodoReportes = new javax.swing.JLabel();
        txtPeriodoReportes = new javax.swing.JTextField();
        lblSurcusalesReportes = new javax.swing.JLabel();
        cbTipoSurcusalesReportes = new javax.swing.JComboBox<>();
        btnDescargarRegistrodeVentasReportes = new javax.swing.JButton();
        lblPeriodoReportes = new javax.swing.JLabel();
        pnlAjustes = new javax.swing.JPanel();
        lblGestionarperfilAjustes = new javax.swing.JLabel();
        lblDatosAjustes = new javax.swing.JLabel();
        txtNombreAjustes = new javax.swing.JTextField();
        lblNombreAjustes = new javax.swing.JLabel();
        lblImagen1Ajustes = new javax.swing.JLabel();
        txtRUCAjustes = new javax.swing.JTextField();
        lblRUCAjustes = new javax.swing.JLabel();
        txtTelefonoAjustes = new javax.swing.JTextField();
        lblTelefonoAjustes = new javax.swing.JLabel();
        txtCorreodeEmpresaAjustes = new javax.swing.JTextField();
        lblCorreodeEmpresaAjustes = new javax.swing.JLabel();
        cbTipoAspectoAjustes = new javax.swing.JComboBox<>();
        lblIDIOMAAjustes = new javax.swing.JLabel();
        btnGuardacambiosAjustes = new javax.swing.JButton();
        lblApectoAjustes = new javax.swing.JLabel();
        lblLogoAjustes = new javax.swing.JLabel();
        cbLenguaje = new javax.swing.JComboBox<>();
        pnlRegVenProform = new javax.swing.JPanel();
        lblsubtemaProformaRegVen = new javax.swing.JLabel();
        txtFechaProformaRegVen = new javax.swing.JTextField();
        lblFechaProformaRegVen = new javax.swing.JLabel();
        lblNombresProformaRegVen = new javax.swing.JLabel();
        lblMonedaProformaRegVen = new javax.swing.JLabel();
        cbTipoDeDineroProformaRegVen = new javax.swing.JComboBox<>();
        pnlInternoRegVenProforma = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblRegistroProformaRegVen = new javax.swing.JTable();
        txtDetalledeVentasRegVen = new javax.swing.JTextField();
        txtTotalProformaRegVen = new javax.swing.JTextField();
        txtDecoracion1ProformaRegVen = new javax.swing.JTextField();
        btnEditarRegVenProforma = new javax.swing.JButton();
        btnGuardarRegVenProforma = new javax.swing.JButton();
        btnAgregarRegVenProforma = new javax.swing.JButton();
        btnEliminarRegVenProforma = new javax.swing.JButton();
        txtNombresProformaRegVen = new javax.swing.JTextField();

        jmFACTURASRegCom.setText("FACTURAS");
        jmFACTURASRegCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFACTURASRegComActionPerformed(evt);
            }
        });
        RegistroDeCompra.add(jmFACTURASRegCom);

        jmBOLETASRegCom.setText("BOLETAS");
        jmBOLETASRegCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmBOLETASRegComActionPerformed(evt);
            }
        });
        RegistroDeCompra.add(jmBOLETASRegCom);

        jmOTROSRegCom.setText("OTROS");
        jmOTROSRegCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmOTROSRegComActionPerformed(evt);
            }
        });
        RegistroDeCompra.add(jmOTROSRegCom);

        jmCOMPROBATESEMITIDOSRegCom.setText("COMPROBATES EMITIDOS");
        jmCOMPROBATESEMITIDOSRegCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCOMPROBATESEMITIDOSRegComActionPerformed(evt);
            }
        });
        RegistroDeCompra.add(jmCOMPROBATESEMITIDOSRegCom);

        jmFACTURASRegVen.setText("FACTURAS");
        jmFACTURASRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFACTURASRegVenActionPerformed(evt);
            }
        });
        RegistroDeVenta.add(jmFACTURASRegVen);

        jmBOLETASRegVen.setText("BOLETAS");
        jmBOLETASRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmBOLETASRegVenActionPerformed(evt);
            }
        });
        RegistroDeVenta.add(jmBOLETASRegVen);

        jmOTROSRegVen.setText("OTROS");
        jmOTROSRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmOTROSRegVenActionPerformed(evt);
            }
        });
        RegistroDeVenta.add(jmOTROSRegVen);

        jmCOMPROBATESEMITIDOSRegVen.setText("COMPROBATES EMITIDOS");
        jmCOMPROBATESEMITIDOSRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCOMPROBATESEMITIDOSRegVenActionPerformed(evt);
            }
        });
        RegistroDeVenta.add(jmCOMPROBATESEMITIDOSRegVen);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(true); // Cambiado a true para permitir redimensionar
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout());

        // jpanelgeneral (Menú Lateral)
        jpanelgeneral.setBackground(new java.awt.Color(0, 153, 153));
        jpanelgeneral.setPreferredSize(new Dimension(200, 0));
        jpanelgeneral.setLayout(new javax.swing.BoxLayout(jpanelgeneral, javax.swing.BoxLayout.Y_AXIS));

        // Botones del menú - Inicialización y Estilos
        btninicio.setFont(new java.awt.Font("DialogInput", 0, 18));
        btninicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/casa.png")));
        btninicio.setText("INICIO");
        btninicio.setAlignmentX(Component.CENTER_ALIGNMENT);
        btninicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btninicioMouseClicked(evt);
            }
        });

        btnAlmacen.setFont(new java.awt.Font("DialogInput", 0, 18));
        btnAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/caja.png")));
        btnAlmacen.setText("ALMACEN");
        btnAlmacen.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlmacenMouseClicked(evt);
            }
        });

        btnregistrodecompras.setFont(new java.awt.Font("DialogInput", 0, 18));
        btnregistrodecompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/camion.png")));
        btnregistrodecompras.setText("<html>REGISTRO<br>DE COMPRAS</html>");
        btnregistrodecompras.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnregistrodecompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrodecomprasMouseClicked(evt);
            }
        });

        btnregistrodeventas.setFont(new java.awt.Font("DialogInput", 0, 18));
        btnregistrodeventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tienda.png")));
        btnregistrodeventas.setText("<html>REGISTRO<br>DE VENTAS</html>");
        btnregistrodeventas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnregistrodeventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrodeventasMouseClicked(evt);
            }
        });

        btncerrarsesion.setFont(new java.awt.Font("DialogInput", 0, 18));
        btncerrarsesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/puerta.png")));
        btncerrarsesion.setText("<html>CERRAR<br>SESION</html>");
        btncerrarsesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btncerrarsesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncerrarsesionMouseClicked(evt);
            }
        });

        // Espaciado y Agregado al Panel
        jpanelgeneral.add(javax.swing.Box.createVerticalStrut(20));
        jpanelgeneral.add(btninicio);
        jpanelgeneral.add(javax.swing.Box.createVerticalStrut(10));
        jpanelgeneral.add(btnAlmacen);
        jpanelgeneral.add(javax.swing.Box.createVerticalStrut(10));

        // Panel contenedor para Compras
        javax.swing.JPanel pnlCompras = new javax.swing.JPanel();
        pnlCompras.setOpaque(false);
        pnlCompras.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        pnlCompras.add(btnregistrodecompras);
        pnlCompras.add(lblRegCompras);
        pnlCompras.setMaximumSize(new Dimension(200, 60));
        jpanelgeneral.add(pnlCompras);

        jpanelgeneral.add(javax.swing.Box.createVerticalStrut(10));

        // Panel contenedor para Ventas
        javax.swing.JPanel pnlVentas = new javax.swing.JPanel();
        pnlVentas.setOpaque(false);
        pnlVentas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        pnlVentas.add(btnregistrodeventas);
        pnlVentas.add(lblRegVentas);
        pnlVentas.setMaximumSize(new Dimension(200, 60));
        jpanelgeneral.add(pnlVentas);

        // jpaneladmin (Parte del menú lateral para admin)
        jpaneladmin.setBackground(new java.awt.Color(0, 153, 153));
        jpaneladmin.setLayout(new javax.swing.BoxLayout(jpaneladmin, javax.swing.BoxLayout.Y_AXIS));

        // Inicialización de botones de admin
        btncaja.setFont(new java.awt.Font("DialogInput", 0, 18));
        btncaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dinero.png")));
        btncaja.setText("CAJA");
        btncaja.setAlignmentX(Component.CENTER_ALIGNMENT);
        btncaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncajaMouseClicked(evt);
            }
        });

        btnregistrodeusuario.setFont(new java.awt.Font("DialogInput", 0, 18));
        btnregistrodeusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/personas.png")));
        btnregistrodeusuario.setText("<html>REGISTRO<br>DE USUARIOS</html>");
        btnregistrodeusuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnregistrodeusuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrodeusuarioMouseClicked(evt);
            }
        });

        btnreportes.setFont(new java.awt.Font("DialogInput", 0, 18));
        btnreportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/documento.png")));
        btnreportes.setText("REPORTES");
        btnreportes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnreportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnreportesMouseClicked(evt);
            }
        });

        btnajustes.setFont(new java.awt.Font("DialogInput", 0, 18));
        btnajustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tuercas.png")));
        btnajustes.setText("AJUSTES");
        btnajustes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnajustes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnajustesMouseClicked(evt);
            }
        });

        jpaneladmin.add(javax.swing.Box.createVerticalStrut(10));
        jpaneladmin.add(btncaja);
        jpaneladmin.add(javax.swing.Box.createVerticalStrut(10));
        jpaneladmin.add(btnregistrodeusuario);
        jpaneladmin.add(javax.swing.Box.createVerticalStrut(10));
        jpaneladmin.add(btnreportes);
        jpaneladmin.add(javax.swing.Box.createVerticalStrut(10));
        jpaneladmin.add(btnajustes);

        jpanelgeneral.add(jpaneladmin);
        jpanelgeneral.add(javax.swing.Box.createVerticalGlue()); // Empuja cerrar sesión abajo
        jpanelgeneral.add(btncerrarsesion);
        jpanelgeneral.add(javax.swing.Box.createVerticalStrut(20));

        // jpanelsuperior (Encabezado)
        jpanelsuperior.setBackground(new java.awt.Color(0, 153, 153));
        jpanelsuperior.setPreferredSize(new Dimension(0, 80));
        jpanelsuperior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));

        jpanelsuperior.add(lblinventario);
        jpanelsuperior.add(lblimagen1);
        // Separador flexible simulado con glue si fuera Box, pero FlowLayout es simple.
        // Usaremos un panel derecho para alinear a la derecha la info de usuario
        javax.swing.JPanel pnlInfoUsuario = new javax.swing.JPanel();
        pnlInfoUsuario.setOpaque(false);
        pnlInfoUsuario.setLayout(new java.awt.GridLayout(2, 1));
        pnlInfoUsuario.add(lblRUC);
        pnlInfoUsuario.add(lblRol);

        javax.swing.JPanel pnlHeaderRight = new javax.swing.JPanel();
        pnlHeaderRight.setOpaque(false);
        pnlHeaderRight.add(pnlInfoUsuario);
        pnlHeaderRight.add(lblimagen2);

        // Contenedor principal del header para usar BorderLayout y separar titulo de usuario
        javax.swing.JPanel pnlHeaderContainer = new javax.swing.JPanel(new BorderLayout());
        pnlHeaderContainer.setOpaque(false);

        javax.swing.JPanel pnlHeaderLeft = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        pnlHeaderLeft.setOpaque(false);
        pnlHeaderLeft.add(lblinventario);
        pnlHeaderLeft.add(lblimagen1);

        pnlHeaderContainer.add(pnlHeaderLeft, BorderLayout.WEST);
        pnlHeaderContainer.add(pnlHeaderRight, BorderLayout.EAST);

        // Reemplazamos el layout manager de jpanelsuperior por BorderLayout
        jpanelsuperior.setLayout(new BorderLayout());
        jpanelsuperior.add(pnlHeaderContainer, BorderLayout.CENTER);


        // Agregar paneles al frame
        getContentPane().add(jpanelsuperior, BorderLayout.NORTH);
        getContentPane().add(jpanelgeneral, BorderLayout.WEST);
        getContentPane().add(tpnMostrar, BorderLayout.CENTER);

        // Ocultar las pestañas pero mantener la funcionalidad
        tpnMostrar.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
                return 0;
            }
        });

        // --- pnl1Inicio ---
        // Usaremos GridBagLayout para centrar los paneles de resumen
        pnl1Inicio.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcInicio = new java.awt.GridBagConstraints();
        gbcInicio.insets = new java.awt.Insets(10, 10, 10, 10);

        // Subtítulo
        lblSubTitulo.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblSubTitulo.setText("RESUMEN DEL DÍA");
        gbcInicio.gridx = 0;
        gbcInicio.gridy = 0;
        gbcInicio.gridwidth = 2; // Ocupa dos columnas
        gbcInicio.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnl1Inicio.add(lblSubTitulo, gbcInicio);

        // Configuración de layout para los paneles pequeños
        java.awt.Dimension dimPanelResumen = new java.awt.Dimension(190, 90);

        // pnlGanancias
        pnlGancias.setBackground(new java.awt.Color(255, 212, 4));
        pnlGancias.setPreferredSize(dimPanelResumen);
        pnlGancias.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10)); // Simple FlowLayout
        lblGanancias.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        lblGanancias.setText("GANANCIAS");
        txtGanancias.setPreferredSize(new java.awt.Dimension(150, 30));
        pnlGancias.add(txtGanancias);
        pnlGancias.add(lblGanancias);

        gbcInicio.gridx = 0;
        gbcInicio.gridy = 1;
        gbcInicio.gridwidth = 1;
        gbcInicio.anchor = java.awt.GridBagConstraints.CENTER;
        pnl1Inicio.add(pnlGancias, gbcInicio);

        // pnlProveedores
        pnlProveedores.setBackground(new java.awt.Color(199, 180, 159));
        pnlProveedores.setPreferredSize(dimPanelResumen);
        pnlProveedores.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        lblProveedores.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        lblProveedores.setText("PROVEEDORES");
        txtProveedores.setPreferredSize(new java.awt.Dimension(150, 30));
        pnlProveedores.add(txtProveedores);
        pnlProveedores.add(lblProveedores);

        gbcInicio.gridx = 1;
        gbcInicio.gridy = 1;
        pnl1Inicio.add(pnlProveedores, gbcInicio);

        // pnlCompraDelMes
        pnlCompraDelMes.setBackground(new java.awt.Color(248, 197, 200));
        pnlCompraDelMes.setPreferredSize(dimPanelResumen);
        pnlCompraDelMes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        lblCompraDelMes.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        lblCompraDelMes.setText("COMPRA DEL MES");
        txtComprasDelMes.setPreferredSize(new java.awt.Dimension(150, 30));
        pnlCompraDelMes.add(txtComprasDelMes);
        pnlCompraDelMes.add(lblCompraDelMes);

        gbcInicio.gridx = 0;
        gbcInicio.gridy = 2;
        pnl1Inicio.add(pnlCompraDelMes, gbcInicio);

        // pnlVentasDelDia
        pnlVentasDelDia.setBackground(new java.awt.Color(160, 204, 92));
        pnlVentasDelDia.setPreferredSize(dimPanelResumen);
        pnlVentasDelDia.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        lblVentasDelDia.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        lblVentasDelDia.setText("VENTAS DEL DIA");
        txtVentasDelDia.setPreferredSize(new java.awt.Dimension(150, 30));
        pnlVentasDelDia.add(txtVentasDelDia);
        pnlVentasDelDia.add(lblVentasDelDia);

        gbcInicio.gridx = 1;
        gbcInicio.gridy = 2;
        pnl1Inicio.add(pnlVentasDelDia, gbcInicio);

        // pnlCientes (Typo original conservado en variable)
        pnlCientes.setBackground(new java.awt.Color(152, 180, 204));
        pnlCientes.setPreferredSize(dimPanelResumen);
        pnlCientes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        lblClientes.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        lblClientes.setText("CLIENTES");
        txtClientes.setPreferredSize(new java.awt.Dimension(150, 30));
        pnlCientes.add(txtClientes);
        pnlCientes.add(lblClientes);

        gbcInicio.gridx = 0;
        gbcInicio.gridy = 3;
        pnl1Inicio.add(pnlCientes, gbcInicio);

        // pnlProductos
        pnlProductos.setBackground(new java.awt.Color(216, 196, 244));
        pnlProductos.setPreferredSize(dimPanelResumen);
        pnlProductos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        lblProductos.setFont(new java.awt.Font("Comic Sans MS", 1, 12));
        lblProductos.setText("PRODUCTOS");
        txtProductos.setPreferredSize(new java.awt.Dimension(150, 30));
        pnlProductos.add(txtProductos);
        pnlProductos.add(lblProductos);

        gbcInicio.gridx = 1;
        gbcInicio.gridy = 3;
        pnl1Inicio.add(pnlProductos, gbcInicio);

        tpnMostrar.addTab("pnl1Inicio", pnl1Inicio);

        // --- pnlRegUsuMostrar ---
        pnlRegUsuMostrar.setLayout(new java.awt.BorderLayout());

        // Panel superior para botón Crear Usuario
        javax.swing.JPanel pnlRegUsuTop = new javax.swing.JPanel();
        pnlRegUsuTop.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 20));
        btnCrearUsuario.setBackground(new java.awt.Color(202, 244, 250));
        btnCrearUsuario.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnCrearUsuario.setText("Crear Usuario");
        btnCrearUsuario.setPreferredSize(new Dimension(210, 40));
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });
        pnlRegUsuTop.add(btnCrearUsuario);
        pnlRegUsuMostrar.add(pnlRegUsuTop, BorderLayout.NORTH);

        // Tabla central
        tblRegistroUsuarios.setBackground(new java.awt.Color(220, 235, 245));
        tblRegistroUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Usuario ID", "Empleado", "Usuario", "Contraseña", "Tipo", "Telefono", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistroUsuarios.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblRegistroUsuarios);
        // Margen alrededor de la tabla
        javax.swing.JPanel pnlTablaContainer = new javax.swing.JPanel(new BorderLayout());
        pnlTablaContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pnlTablaContainer.add(jScrollPane1, BorderLayout.CENTER);
        pnlRegUsuMostrar.add(pnlTablaContainer, BorderLayout.CENTER);

        // Panel inferior para botones Editar y Eliminar
        javax.swing.JPanel pnlRegUsuBottom = new javax.swing.JPanel();
        pnlRegUsuBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 20));

        btnEliminar.setBackground(new java.awt.Color(202, 244, 250));
        btnEliminar.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new Dimension(230, 40));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlRegUsuBottom.add(btnEliminar);

        btnEditar.setBackground(new java.awt.Color(226, 237, 241));
        btnEditar.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setPreferredSize(new Dimension(230, 40));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlRegUsuBottom.add(btnEditar);

        pnlRegUsuMostrar.add(pnlRegUsuBottom, BorderLayout.SOUTH);

        tpnMostrar.addTab("pnlRegUsuMostrar", pnlRegUsuMostrar);

        // --- pnlRegUsuIngresar ---
        pnlRegUsuIngresar.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcIngresar = new java.awt.GridBagConstraints();
        gbcIngresar.insets = new java.awt.Insets(10, 10, 10, 10);
        gbcIngresar.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblAgregaryCrearUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblAgregaryCrearUsuario.setText("AGREGAR Y CREAR USUARIO");
        gbcIngresar.gridx = 0;
        gbcIngresar.gridy = 0;
        gbcIngresar.gridwidth = 4;
        pnlRegUsuIngresar.add(lblAgregaryCrearUsuario, gbcIngresar);

        // Subtitulo
        lblSubTitulo1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSubTitulo1.setText("• Creación de Usuario");
        gbcIngresar.gridy = 1;
        pnlRegUsuIngresar.add(lblSubTitulo1, gbcIngresar);

        // Nombres
        lblNombres.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblNombres.setText("Nombres:");
        gbcIngresar.gridy = 2;
        gbcIngresar.gridwidth = 1;
        pnlRegUsuIngresar.add(lblNombres, gbcIngresar);

        txtNombres.setPreferredSize(new Dimension(190, 30));
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        gbcIngresar.gridx = 1;
        pnlRegUsuIngresar.add(txtNombres, gbcIngresar);

        // Apellidos
        lblApellidos.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblApellidos.setText("Apellidos:");
        gbcIngresar.gridx = 2;
        pnlRegUsuIngresar.add(lblApellidos, gbcIngresar);

        txtApellidos.setPreferredSize(new Dimension(190, 30));
        gbcIngresar.gridx = 3;
        pnlRegUsuIngresar.add(txtApellidos, gbcIngresar);

        // Telefono
        lblTelefono.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblTelefono.setText("Telefono:");
        gbcIngresar.gridx = 0;
        gbcIngresar.gridy = 3;
        pnlRegUsuIngresar.add(lblTelefono, gbcIngresar);

        txtTelefono.setPreferredSize(new Dimension(190, 30));
        gbcIngresar.gridx = 1;
        pnlRegUsuIngresar.add(txtTelefono, gbcIngresar);

        // DNI
        lblDNIUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblDNIUsuario.setText("DNI/Usuario:");
        gbcIngresar.gridx = 2;
        pnlRegUsuIngresar.add(lblDNIUsuario, gbcIngresar);

        txtDNIUsuario.setPreferredSize(new Dimension(190, 30));
        gbcIngresar.gridx = 3;
        pnlRegUsuIngresar.add(txtDNIUsuario, gbcIngresar);

        // Contraseña
        lblContraseña.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblContraseña.setText("Contraseña:");
        gbcIngresar.gridx = 0;
        gbcIngresar.gridy = 4;
        pnlRegUsuIngresar.add(lblContraseña, gbcIngresar);

        txtContraseña.setPreferredSize(new Dimension(190, 30));
        gbcIngresar.gridx = 1;
        pnlRegUsuIngresar.add(txtContraseña, gbcIngresar);

        // Tipo Usuario
        lblTipoUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblTipoUsuario.setText("Tipo Usuario:");
        gbcIngresar.gridx = 2;
        pnlRegUsuIngresar.add(lblTipoUsuario, gbcIngresar);

        cbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "administrador", "empleado" }));
        cbTipoUsuario.setPreferredSize(new Dimension(190, 30));
        gbcIngresar.gridx = 3;
        pnlRegUsuIngresar.add(cbTipoUsuario, gbcIngresar);

        // Radio Buttons (Estado)
        javax.swing.JPanel pnlEstado = new javax.swing.JPanel();
        pnlEstado.setLayout(new javax.swing.BoxLayout(pnlEstado, javax.swing.BoxLayout.Y_AXIS));

        rbtActivo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtActivo.setForeground(new java.awt.Color(102, 153, 0));
        rbtActivo.setSelected(true);
        rbtActivo.setText("ACTIVO");
        rbtActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtActivoActionPerformed(evt);
            }
        });
        pnlEstado.add(rbtActivo);

        rbtInactivo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtInactivo.setForeground(new java.awt.Color(204, 0, 0));
        rbtInactivo.setText("INACTIVO");
        rbtInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtInactivoActionPerformed(evt);
            }
        });
        pnlEstado.add(rbtInactivo);

        gbcIngresar.gridx = 1;
        gbcIngresar.gridy = 5;
        pnlRegUsuIngresar.add(pnlEstado, gbcIngresar);

        // Boton Guardar
        btnGuardaryAgregarDatos.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnGuardaryAgregarDatos.setText("Guardar y Agregar Datos");
        btnGuardaryAgregarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardaryAgregarDatosActionPerformed(evt);
            }
        });

        gbcIngresar.gridx = 2;
        gbcIngresar.gridwidth = 2;
        gbcIngresar.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlRegUsuIngresar.add(btnGuardaryAgregarDatos, gbcIngresar);

        tpnMostrar.addTab("pnlRegUsuIngresar", pnlRegUsuIngresar);

        // --- pnl1Almacen ---
        pnl1Almacen.setBackground(new java.awt.Color(216, 252, 156));
        pnl1Almacen.setLayout(new java.awt.BorderLayout());

        // Panel superior para Formulario y Botones
        javax.swing.JPanel pnlAlmacenTop = new javax.swing.JPanel();
        pnlAlmacenTop.setOpaque(false);
        pnlAlmacenTop.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcAlm = new java.awt.GridBagConstraints();
        gbcAlm.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcAlm.anchor = java.awt.GridBagConstraints.WEST;

        // Subtitulo
        lblSubtema2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lblSubtema2.setText("REGISTRO DE PRODUCTOS");
        gbcAlm.gridx = 0;
        gbcAlm.gridy = 0;
        gbcAlm.gridwidth = 2;
        pnlAlmacenTop.add(lblSubtema2, gbcAlm);

        // --- Columna Izquierda (Campos) ---
        // Producto
        lblProducto.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblProducto.setText("Producto: ");
        gbcAlm.gridx = 0;
        gbcAlm.gridy = 1;
        gbcAlm.gridwidth = 1;
        pnlAlmacenTop.add(lblProducto, gbcAlm);

        txtProducto.setPreferredSize(new Dimension(160, 30));
        gbcAlm.gridx = 1;
        pnlAlmacenTop.add(txtProducto, gbcAlm);

        // Cantidad
        lblCantidaddeProducto.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblCantidaddeProducto.setText("Cantidad de productos: ");
        gbcAlm.gridx = 0;
        gbcAlm.gridy = 2;
        pnlAlmacenTop.add(lblCantidaddeProducto, gbcAlm);

        txtCantidaddeProducto.setPreferredSize(new Dimension(160, 30));
        gbcAlm.gridx = 1;
        pnlAlmacenTop.add(txtCantidaddeProducto, gbcAlm);

        // Precio
        lblPreciodeProducto.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblPreciodeProducto.setText("Precio de compra:");
        gbcAlm.gridx = 0;
        gbcAlm.gridy = 3;
        pnlAlmacenTop.add(lblPreciodeProducto, gbcAlm);

        txtPreciodeCompra.setPreferredSize(new Dimension(160, 30));
        gbcAlm.gridx = 1;
        pnlAlmacenTop.add(txtPreciodeCompra, gbcAlm);

        // Busqueda
        javax.swing.JPanel pnlBusquedaAlm = new javax.swing.JPanel();
        pnlBusquedaAlm.setOpaque(false);
        pnlBusquedaAlm.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        lblImagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lupa.png")));
        pnlBusquedaAlm.add(lblImagen1);

        txtBuscar.setPreferredSize(new Dimension(160, 30));
        pnlBusquedaAlm.add(txtBuscar);

        btnBuscarAlmacen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnBuscarAlmacen.setText("Buscar");
        btnBuscarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlmacenActionPerformed(evt);
            }
        });
        pnlBusquedaAlm.add(btnBuscarAlmacen);

        gbcAlm.gridx = 0;
        gbcAlm.gridy = 4;
        gbcAlm.gridwidth = 2;
        pnlAlmacenTop.add(pnlBusquedaAlm, gbcAlm);

        // --- Columna Derecha (Botones Acciones) ---
        javax.swing.JPanel pnlAccionesAlm = new javax.swing.JPanel();
        pnlAccionesAlm.setOpaque(false);
        pnlAccionesAlm.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcAcc = new java.awt.GridBagConstraints();
        gbcAcc.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcAcc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbcAcc.gridx = 0;

        btnAgregarAlmacen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAgregarAlmacen.setText("Agregar");
        btnAgregarAlmacen.setPreferredSize(new Dimension(120, 40));
        btnAgregarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAlmacenActionPerformed(evt);
            }
        });
        gbcAcc.gridy = 0;
        pnlAccionesAlm.add(btnAgregarAlmacen, gbcAcc);

        btnEliminarAlmacen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEliminarAlmacen.setText("Eliminar");
        btnEliminarAlmacen.setPreferredSize(new Dimension(120, 40));
        btnEliminarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAlmacenActionPerformed(evt);
            }
        });
        gbcAcc.gridy = 1;
        pnlAccionesAlm.add(btnEliminarAlmacen, gbcAcc);

        btnEditarAlmacen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEditarAlmacen.setText("Editar");
        btnEditarAlmacen.setPreferredSize(new Dimension(120, 40));
        btnEditarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAlmacenActionPerformed(evt);
            }
        });
        gbcAcc.gridy = 2;
        pnlAccionesAlm.add(btnEditarAlmacen, gbcAcc);

        // Filtro y Combo
        javax.swing.JPanel pnlFiltro = new javax.swing.JPanel();
        pnlFiltro.setOpaque(false);
        cbTipoStock.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        cbTipoStock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "• Sin Filtro", "• Sin Stock", "• Con stock" }));
        cbTipoStock.setPreferredSize(new Dimension(150, 30));
        pnlFiltro.add(cbTipoStock);

        btnFiltrarAlmacen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnFiltrarAlmacen.setText("Filtrar");
        btnFiltrarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarAlmacenActionPerformed(evt);
            }
        });
        pnlFiltro.add(btnFiltrarAlmacen);

        gbcAcc.gridy = 3;
        pnlAccionesAlm.add(pnlFiltro, gbcAcc);

        gbcAlm.gridx = 2;
        gbcAlm.gridy = 0;
        gbcAlm.gridheight = 5;
        gbcAlm.anchor = java.awt.GridBagConstraints.NORTH;
        pnlAlmacenTop.add(pnlAccionesAlm, gbcAlm);

        pnl1Almacen.add(pnlAlmacenTop, BorderLayout.NORTH);

        // Tabla
        tblRegistroProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tipo de documento", "Producto", "Precio Compra", "Cantidad", "Stock"
            }
        ));
        jScrollPane2.setViewportView(tblRegistroProductos);

        javax.swing.JPanel pnlTablaAlm = new javax.swing.JPanel(new BorderLayout());
        pnlTablaAlm.setOpaque(false);
        pnlTablaAlm.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pnlTablaAlm.add(jScrollPane2, BorderLayout.CENTER);

        pnl1Almacen.add(pnlTablaAlm, BorderLayout.CENTER);

        tpnMostrar.addTab("pnl1Almacen", pnl1Almacen);

        // --- pnlRegComFactura ---
        pnlRegComFactura.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegComFactura.setLayout(new java.awt.BorderLayout());

        // Panel Superior (Formulario Factura)
        javax.swing.JPanel pnlFacturaTop = new javax.swing.JPanel();
        pnlFacturaTop.setOpaque(false);
        pnlFacturaTop.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcFact = new java.awt.GridBagConstraints();
        gbcFact.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcFact.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblsubtemaFactura.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblsubtemaFactura.setText("FACTURA");
        gbcFact.gridx = 0;
        gbcFact.gridy = 0;
        gbcFact.gridwidth = 4;
        pnlFacturaTop.add(lblsubtemaFactura, gbcFact);

        // Fila 1: Serie, Numero, Moneda
        lblSerieFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblSerieFactura.setText("Serie:");
        gbcFact.gridy = 1;
        gbcFact.gridwidth = 1;
        gbcFact.gridx = 0;
        pnlFacturaTop.add(lblSerieFactura, gbcFact);

        txtSerieFactura.setPreferredSize(new Dimension(80, 30));
        gbcFact.gridx = 1;
        pnlFacturaTop.add(txtSerieFactura, gbcFact);

        lblNumeroFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblNumeroFactura.setText("Número:");
        gbcFact.gridx = 2;
        pnlFacturaTop.add(lblNumeroFactura, gbcFact);

        txtNumeroFactura.setPreferredSize(new Dimension(80, 30));
        gbcFact.gridx = 3;
        pnlFacturaTop.add(txtNumeroFactura, gbcFact);

        // Fila 2: Proveedor, Moneda
        lblProveedorFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblProveedorFactura.setText("Proveedor:");
        gbcFact.gridy = 2;
        gbcFact.gridx = 0;
        pnlFacturaTop.add(lblProveedorFactura, gbcFact);

        txtProveedorFactura.setPreferredSize(new Dimension(170, 30));
        gbcFact.gridx = 1;
        pnlFacturaTop.add(txtProveedorFactura, gbcFact);

        lblMonedaFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblMonedaFactura.setText("Moneda:");
        gbcFact.gridx = 2;
        pnlFacturaTop.add(lblMonedaFactura, gbcFact);

        cbTipoDeDineroFactura.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeDineroFactura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soles", "Dólares" }));
        cbTipoDeDineroFactura.setPreferredSize(new Dimension(120, 30));
        gbcFact.gridx = 3;
        pnlFacturaTop.add(cbTipoDeDineroFactura, gbcFact);

        // Fila 3: Responsable, Fecha
        lblResponsableFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblResponsableFactura.setText("Responsable:");
        gbcFact.gridy = 3;
        gbcFact.gridx = 0;
        pnlFacturaTop.add(lblResponsableFactura, gbcFact);

        cbTipoDeLiderFactura.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeLiderFactura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flor de Maria Huaman Alvarez", "Wilmer Pincos Huamani" }));
        cbTipoDeLiderFactura.setPreferredSize(new Dimension(220, 30));
        gbcFact.gridx = 1;
        gbcFact.gridwidth = 1; // Ajuste
        pnlFacturaTop.add(cbTipoDeLiderFactura, gbcFact);

        lblFechaFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblFechaFactura.setText("Fecha:");
        gbcFact.gridx = 2;
        pnlFacturaTop.add(lblFechaFactura, gbcFact);

        txtFechaFactura.setPreferredSize(new Dimension(110, 30));
        gbcFact.gridx = 3;
        pnlFacturaTop.add(txtFechaFactura, gbcFact);

        pnlRegComFactura.add(pnlFacturaTop, BorderLayout.NORTH);

        // Panel Central (Tabla y Totales) - pnlInternRegComFactura
        pnlInternRegComFactura.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternRegComFactura.setLayout(new java.awt.BorderLayout());
        pnlInternRegComFactura.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Decoración superior (Titulo tabla)
        txtDecoracion1Factura.setEditable(false);
        txtDecoracion1Factura.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternRegComFactura.add(txtDecoracion1Factura, BorderLayout.NORTH);

        // Tabla
        tblRegistroFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Cantidad", "Precio Unit.", "Sub. Total", "I.G.V.", "Total"
            }
        ));
        jScrollPane3.setViewportView(tblRegistroFactura);
        pnlInternRegComFactura.add(jScrollPane3, BorderLayout.CENTER);

        // Panel Totales (Inferior de tabla)
        javax.swing.JPanel pnlTotalesFactura = new javax.swing.JPanel();
        pnlTotalesFactura.setOpaque(false);
        pnlTotalesFactura.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        txtDecoracion2Factura.setEditable(false);
        txtDecoracion2Factura.setBackground(new java.awt.Color(255, 255, 255));
        txtDecoracion2Factura.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtDecoracion2Factura.setForeground(new java.awt.Color(255, 51, 51));
        txtDecoracion2Factura.setText("TOTAL");
        txtDecoracion2Factura.setPreferredSize(new Dimension(80, 30));
        pnlTotalesFactura.add(txtDecoracion2Factura);

        txtTotalFactura.setEnabled(false);
        txtTotalFactura.setPreferredSize(new Dimension(100, 30));
        pnlTotalesFactura.add(txtTotalFactura);

        pnlInternRegComFactura.add(pnlTotalesFactura, BorderLayout.SOUTH);

        pnlRegComFactura.add(pnlInternRegComFactura, BorderLayout.CENTER);

        // Panel Inferior (Botones)
        javax.swing.JPanel pnlBotonesFactura = new javax.swing.JPanel();
        pnlBotonesFactura.setOpaque(false);
        pnlBotonesFactura.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        btnGuardarRegComFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnGuardarRegComFactura.setText("Guardar");
        btnGuardarRegComFactura.setPreferredSize(new Dimension(100, 30));
        btnGuardarRegComFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegComFacturaActionPerformed(evt);
            }
        });
        pnlBotonesFactura.add(btnGuardarRegComFactura);

        btnAgregarRegComFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAgregarRegComFactura.setText("Agregar");
        btnAgregarRegComFactura.setPreferredSize(new Dimension(100, 30));
        btnAgregarRegComFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRegComFacturaActionPerformed(evt);
            }
        });
        pnlBotonesFactura.add(btnAgregarRegComFactura);

        btnEliminarRegComFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEliminarRegComFactura.setText("Eliminar");
        btnEliminarRegComFactura.setPreferredSize(new Dimension(110, 30));
        btnEliminarRegComFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRegComFacturaActionPerformed(evt);
            }
        });
        pnlBotonesFactura.add(btnEliminarRegComFactura);

        btnEditarRegComFactura.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEditarRegComFactura.setText("Editar");
        btnEditarRegComFactura.setPreferredSize(new Dimension(100, 30));
        btnEditarRegComFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarRegComFacturaActionPerformed(evt);
            }
        });
        pnlBotonesFactura.add(btnEditarRegComFactura);

        pnlRegComFactura.add(pnlBotonesFactura, BorderLayout.SOUTH);

        tpnMostrar.addTab("pnlRegComFactura", pnlRegComFactura);

        // --- pnlRegComBoleta ---
        pnlRegComBoleta.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegComBoleta.setLayout(new java.awt.BorderLayout());

        // Panel Superior (Formulario Boleta)
        javax.swing.JPanel pnlBoletaTop = new javax.swing.JPanel();
        pnlBoletaTop.setOpaque(false);
        pnlBoletaTop.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcBol = new java.awt.GridBagConstraints();
        gbcBol.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcBol.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblsubtemaBoleta.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblsubtemaBoleta.setText("BOLETA");
        gbcBol.gridx = 0;
        gbcBol.gridy = 0;
        gbcBol.gridwidth = 4;
        pnlBoletaTop.add(lblsubtemaBoleta, gbcBol);

        // Fila 1: Serie, Numero
        lblSerieBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblSerieBoleta.setText("Serie:");
        gbcBol.gridy = 1;
        gbcBol.gridwidth = 1;
        gbcBol.gridx = 0;
        pnlBoletaTop.add(lblSerieBoleta, gbcBol);

        txtSerieBoleta.setPreferredSize(new Dimension(80, 30));
        gbcBol.gridx = 1;
        pnlBoletaTop.add(txtSerieBoleta, gbcBol);

        lblNumeroBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblNumeroBoleta.setText("Número:");
        gbcBol.gridx = 2;
        pnlBoletaTop.add(lblNumeroBoleta, gbcBol);

        txtNumeroBoleta.setPreferredSize(new Dimension(80, 30));
        gbcBol.gridx = 3;
        pnlBoletaTop.add(txtNumeroBoleta, gbcBol);

        // Fila 2: Proveedor, Moneda
        lblProveedorBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblProveedorBoleta.setText("Proveedor:");
        gbcBol.gridy = 2;
        gbcBol.gridx = 0;
        pnlBoletaTop.add(lblProveedorBoleta, gbcBol);

        txtProveedorBoleta.setPreferredSize(new Dimension(170, 30));
        gbcBol.gridx = 1;
        pnlBoletaTop.add(txtProveedorBoleta, gbcBol);

        lblMonedaBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblMonedaBoleta.setText("Moneda:");
        gbcBol.gridx = 2;
        pnlBoletaTop.add(lblMonedaBoleta, gbcBol);

        cbTipoDeDineroBoleta.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeDineroBoleta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soles", "Dólares" }));
        cbTipoDeDineroBoleta.setPreferredSize(new Dimension(120, 30));
        gbcBol.gridx = 3;
        pnlBoletaTop.add(cbTipoDeDineroBoleta, gbcBol);

        // Fila 3: Responsable, Fecha
        lblResponsableBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblResponsableBoleta.setText("Responsable:");
        gbcBol.gridy = 3;
        gbcBol.gridx = 0;
        pnlBoletaTop.add(lblResponsableBoleta, gbcBol);

        cbTipoDeLiderBoleta.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeLiderBoleta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flor de Maria Huaman Alvarez", "Wilmer Pincos Huamani" }));
        cbTipoDeLiderBoleta.setPreferredSize(new Dimension(220, 30));
        gbcBol.gridx = 1;
        pnlBoletaTop.add(cbTipoDeLiderBoleta, gbcBol);

        lblFechaBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblFechaBoleta.setText("Fecha:");
        gbcBol.gridx = 2;
        pnlBoletaTop.add(lblFechaBoleta, gbcBol);

        txtFechaBoleta.setPreferredSize(new Dimension(110, 30));
        gbcBol.gridx = 3;
        pnlBoletaTop.add(txtFechaBoleta, gbcBol);

        pnlRegComBoleta.add(pnlBoletaTop, BorderLayout.NORTH);

        // Panel Central (Tabla y Totales)
        pnlInternoRegComBoleta.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternoRegComBoleta.setLayout(new java.awt.BorderLayout());
        pnlInternoRegComBoleta.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Decoración superior (Titulo tabla)
        txtDecoracion1Boleta.setEditable(false);
        txtDecoracion1Boleta.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternoRegComBoleta.add(txtDecoracion1Boleta, BorderLayout.NORTH);

        // Tabla
        tblRegistroBoleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Cantidad", "Precio Unit.", "Sub. Total", "I.G.V.", "Total"
            }
        ));
        jScrollPane4.setViewportView(tblRegistroBoleta);
        pnlInternoRegComBoleta.add(jScrollPane4, BorderLayout.CENTER);

        // Panel Totales
        javax.swing.JPanel pnlTotalesBoleta = new javax.swing.JPanel();
        pnlTotalesBoleta.setOpaque(false);
        pnlTotalesBoleta.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        txtDecoracion2Boleta.setEditable(false);
        txtDecoracion2Boleta.setBackground(new java.awt.Color(255, 255, 255));
        txtDecoracion2Boleta.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtDecoracion2Boleta.setForeground(new java.awt.Color(255, 51, 51));
        txtDecoracion2Boleta.setText("TOTAL");
        txtDecoracion2Boleta.setPreferredSize(new Dimension(80, 30));
        pnlTotalesBoleta.add(txtDecoracion2Boleta);

        txtTotalBoleta.setEnabled(false);
        txtTotalBoleta.setPreferredSize(new Dimension(100, 30));
        pnlTotalesBoleta.add(txtTotalBoleta);

        pnlInternoRegComBoleta.add(pnlTotalesBoleta, BorderLayout.SOUTH);

        pnlRegComBoleta.add(pnlInternoRegComBoleta, BorderLayout.CENTER);

        // Panel Inferior (Botones)
        javax.swing.JPanel pnlBotonesBoleta = new javax.swing.JPanel();
        pnlBotonesBoleta.setOpaque(false);
        pnlBotonesBoleta.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        btnGuardarRegComBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnGuardarRegComBoleta.setText("Guardar");
        btnGuardarRegComBoleta.setPreferredSize(new Dimension(100, 30));
        btnGuardarRegComBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegComBoletaActionPerformed(evt);
            }
        });
        pnlBotonesBoleta.add(btnGuardarRegComBoleta);

        btnAgregarRegComBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAgregarRegComBoleta.setText("Agregar");
        btnAgregarRegComBoleta.setPreferredSize(new Dimension(100, 30));
        btnAgregarRegComBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRegComBoletaActionPerformed(evt);
            }
        });
        pnlBotonesBoleta.add(btnAgregarRegComBoleta);

        btnEliminarRegComBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEliminarRegComBoleta.setText("Eliminar");
        btnEliminarRegComBoleta.setPreferredSize(new Dimension(110, 30));
        btnEliminarRegComBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRegComBoletaActionPerformed(evt);
            }
        });
        pnlBotonesBoleta.add(btnEliminarRegComBoleta);

        btnEditarRegComBoleta.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEditarRegComBoleta.setText("Editar");
        btnEditarRegComBoleta.setPreferredSize(new Dimension(100, 30));
        btnEditarRegComBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarRegComBoletaActionPerformed(evt);
            }
        });
        pnlBotonesBoleta.add(btnEditarRegComBoleta);

        pnlRegComBoleta.add(pnlBotonesBoleta, BorderLayout.SOUTH);

        tpnMostrar.addTab("pnlRegComBoleta", pnlRegComBoleta);

        // --- pnlRegComProforma ---
        pnlRegComProforma.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegComProforma.setLayout(new java.awt.BorderLayout());

        // Panel Superior (Formulario Proforma)
        javax.swing.JPanel pnlProformaTop = new javax.swing.JPanel();
        pnlProformaTop.setOpaque(false);
        pnlProformaTop.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcProf = new java.awt.GridBagConstraints();
        gbcProf.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcProf.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblsubtemaProforma.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblsubtemaProforma.setText("PROFORMA");
        gbcProf.gridx = 0;
        gbcProf.gridy = 0;
        gbcProf.gridwidth = 4;
        pnlProformaTop.add(lblsubtemaProforma, gbcProf);

        // Fila 1: Nombres
        lblNombresProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblNombresProforma.setText("Nombres:");
        gbcProf.gridy = 1;
        gbcProf.gridwidth = 1;
        gbcProf.gridx = 0;
        pnlProformaTop.add(lblNombresProforma, gbcProf);

        txtNombresProforma.setPreferredSize(new Dimension(300, 30));
        gbcProf.gridx = 1;
        gbcProf.gridwidth = 3;
        pnlProformaTop.add(txtNombresProforma, gbcProf);

        // Fila 2: Responsable
        lblResponsableProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblResponsableProforma.setText("Responsable:");
        gbcProf.gridy = 2;
        gbcProf.gridx = 0;
        gbcProf.gridwidth = 1;
        pnlProformaTop.add(lblResponsableProforma, gbcProf);

        cbTipoDeLiderProforma.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeLiderProforma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flor de Maria Huaman Alvarez", "Wilmer Pincos Huamani" }));
        cbTipoDeLiderProforma.setPreferredSize(new Dimension(220, 30));
        gbcProf.gridx = 1;
        gbcProf.gridwidth = 3;
        pnlProformaTop.add(cbTipoDeLiderProforma, gbcProf);

        // Fila 3: Moneda, Fecha
        lblMonedaProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblMonedaProforma.setText("Moneda:");
        gbcProf.gridy = 3;
        gbcProf.gridx = 0;
        gbcProf.gridwidth = 1;
        pnlProformaTop.add(lblMonedaProforma, gbcProf);

        cbTipoDeDineroProforma.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeDineroProforma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soles", "Dólares" }));
        cbTipoDeDineroProforma.setPreferredSize(new Dimension(120, 30));
        gbcProf.gridx = 1;
        pnlProformaTop.add(cbTipoDeDineroProforma, gbcProf);

        lblFechaProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblFechaProforma.setText("Fecha:");
        gbcProf.gridx = 2;
        pnlProformaTop.add(lblFechaProforma, gbcProf);

        txtFechaProforma.setPreferredSize(new Dimension(110, 30));
        gbcProf.gridx = 3;
        pnlProformaTop.add(txtFechaProforma, gbcProf);

        pnlRegComProforma.add(pnlProformaTop, BorderLayout.NORTH);

        // Panel Central (Tabla y Totales)
        pnlInternoRegComProforma.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternoRegComProforma.setLayout(new java.awt.BorderLayout());
        pnlInternoRegComProforma.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Decoración superior (Titulo tabla)
        txtDecoracion1Proforma.setEditable(false);
        txtDecoracion1Proforma.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternoRegComProforma.add(txtDecoracion1Proforma, BorderLayout.NORTH);

        // Tabla
        tblRegistroProforma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Cantidad", "Precio Unit.", "Sub. Total", "I.G.V.", "Total"
            }
        ));
        jScrollPane5.setViewportView(tblRegistroProforma);
        pnlInternoRegComProforma.add(jScrollPane5, BorderLayout.CENTER);

        // Panel Totales
        javax.swing.JPanel pnlTotalesProforma = new javax.swing.JPanel();
        pnlTotalesProforma.setOpaque(false);
        pnlTotalesProforma.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        txtDecoracion2Proforma.setEditable(false);
        txtDecoracion2Proforma.setBackground(new java.awt.Color(255, 255, 255));
        txtDecoracion2Proforma.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtDecoracion2Proforma.setForeground(new java.awt.Color(255, 51, 51));
        txtDecoracion2Proforma.setText("TOTAL");
        txtDecoracion2Proforma.setPreferredSize(new Dimension(80, 30));
        pnlTotalesProforma.add(txtDecoracion2Proforma);

        txtTotalProforma.setEnabled(false);
        txtTotalProforma.setPreferredSize(new Dimension(100, 30));
        pnlTotalesProforma.add(txtTotalProforma);

        pnlInternoRegComProforma.add(pnlTotalesProforma, BorderLayout.SOUTH);

        pnlRegComProforma.add(pnlInternoRegComProforma, BorderLayout.CENTER);

        // Panel Inferior (Botones)
        javax.swing.JPanel pnlBotonesProforma = new javax.swing.JPanel();
        pnlBotonesProforma.setOpaque(false);
        pnlBotonesProforma.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        btnGuardarRegComProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnGuardarRegComProforma.setText("Guardar");
        btnGuardarRegComProforma.setPreferredSize(new Dimension(100, 30));
        btnGuardarRegComProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegComProformaActionPerformed(evt);
            }
        });
        pnlBotonesProforma.add(btnGuardarRegComProforma);

        btnAgregarRegComProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAgregarRegComProforma.setText("Agregar");
        btnAgregarRegComProforma.setPreferredSize(new Dimension(100, 30));
        btnAgregarRegComProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRegComProformaActionPerformed(evt);
            }
        });
        pnlBotonesProforma.add(btnAgregarRegComProforma);

        btnEliminarRegComProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEliminarRegComProforma.setText("Eliminar");
        btnEliminarRegComProforma.setPreferredSize(new Dimension(110, 30));
        btnEliminarRegComProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRegComProformaActionPerformed(evt);
            }
        });
        pnlBotonesProforma.add(btnEliminarRegComProforma);

        btnEditarRegComProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEditarRegComProforma.setText("Editar");
        btnEditarRegComProforma.setPreferredSize(new Dimension(100, 30));
        btnEditarRegComProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarRegComProformaActionPerformed(evt);
            }
        });
        pnlBotonesProforma.add(btnEditarRegComProforma);

        pnlRegComProforma.add(pnlBotonesProforma, BorderLayout.SOUTH);

        tpnMostrar.addTab("pnlRegComProforma", pnlRegComProforma);

        // --- pnlRegComCompromantesEmitidos ---
        pnlRegComCompromantesEmitidos.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegComCompromantesEmitidos.setLayout(new java.awt.BorderLayout());

        // Panel Superior (Titulo y Busqueda)
        javax.swing.JPanel pnlComprobantesTop = new javax.swing.JPanel();
        pnlComprobantesTop.setOpaque(false);
        pnlComprobantesTop.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcComp = new java.awt.GridBagConstraints();
        gbcComp.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcComp.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblSubtemaComprobantesEmitidos.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblSubtemaComprobantesEmitidos.setText("COMPROBANTES EMITIDOS");
        gbcComp.gridx = 0;
        gbcComp.gridy = 0;
        gbcComp.gridwidth = 2;
        pnlComprobantesTop.add(lblSubtemaComprobantesEmitidos, gbcComp);

        // Buscador Panel
        javax.swing.JPanel pnlBuscadorComp = new javax.swing.JPanel();
        pnlBuscadorComp.setOpaque(false);
        pnlBuscadorComp.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcBusq = new java.awt.GridBagConstraints();
        gbcBusq.insets = new java.awt.Insets(0, 5, 5, 0);
        gbcBusq.anchor = java.awt.GridBagConstraints.WEST;

        lblimagen1ComprobantesEmitidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lupa.png")));
        gbcBusq.gridx = 0;
        gbcBusq.gridy = 0;
        gbcBusq.gridheight = 2;
        pnlBuscadorComp.add(lblimagen1ComprobantesEmitidos, gbcBusq);

        txtBuscarComprobantesEmitidos.setPreferredSize(new Dimension(300, 30));
        gbcBusq.gridx = 1;
        gbcBusq.gridheight = 1;
        pnlBuscadorComp.add(txtBuscarComprobantesEmitidos, gbcBusq);

        cbTipodeBusquedaComprobantesEmitidos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "   Numero", "   Serie ", "   Proveedor" }));
        cbTipodeBusquedaComprobantesEmitidos.setPreferredSize(new Dimension(300, 30));
        gbcBusq.gridy = 1;
        pnlBuscadorComp.add(cbTipodeBusquedaComprobantesEmitidos, gbcBusq);

        gbcComp.gridx = 0;
        gbcComp.gridy = 1;
        gbcComp.gridwidth = 1;
        pnlComprobantesTop.add(pnlBuscadorComp, gbcComp);

        // Botones
        javax.swing.JPanel pnlBotonesComp = new javax.swing.JPanel();
        pnlBotonesComp.setOpaque(false);
        pnlBotonesComp.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        btnBuscarComprobantesEmitidos.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnBuscarComprobantesEmitidos.setText("Buscar");
        btnBuscarComprobantesEmitidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarComprobantesEmitidosActionPerformed(evt);
            }
        });
        pnlBotonesComp.add(btnBuscarComprobantesEmitidos);

        btnPDFComprobantesEmitidos.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnPDFComprobantesEmitidos.setText("PDF");
        btnPDFComprobantesEmitidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFComprobantesEmitidosActionPerformed(evt);
            }
        });
        pnlBotonesComp.add(btnPDFComprobantesEmitidos);

        btnAnularComprobantesEmitidos.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAnularComprobantesEmitidos.setText("Anular");
        btnAnularComprobantesEmitidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularComprobantesEmitidosActionPerformed(evt);
            }
        });
        pnlBotonesComp.add(btnAnularComprobantesEmitidos);

        gbcComp.gridx = 1;
        gbcComp.gridy = 1;
        gbcComp.anchor = java.awt.GridBagConstraints.EAST;
        pnlComprobantesTop.add(pnlBotonesComp, gbcComp);

        pnlRegComCompromantesEmitidos.add(pnlComprobantesTop, BorderLayout.NORTH);

        // Tabla
        tblRegistrodeComprobantesEmitidos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblRegistrodeComprobantesEmitidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha de registro", "Tipo de comprobantes", "Serie", "Numero", "Proveedor", "Total"
            }
        ));
        tblRegistrodeComprobantesEmitidos.setAutoscrolls(false);
        tblRegistrodeComprobantesEmitidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        // tblRegistrodeComprobantesEmitidos.setPreferredSize(new java.awt.Dimension(890, 140)); // Quitar setPreferredSize para que funcione el scroll
        jScrollPane6.setViewportView(tblRegistrodeComprobantesEmitidos);

        // Contenedor Tabla con margen
        javax.swing.JPanel pnlTablaComp = new javax.swing.JPanel(new BorderLayout());
        pnlTablaComp.setOpaque(false);
        pnlTablaComp.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pnlTablaComp.add(jScrollPane6, BorderLayout.CENTER);

        pnlRegComCompromantesEmitidos.add(pnlTablaComp, BorderLayout.CENTER);

        tpnMostrar.addTab("pnlRegComCompromantesEmitidos", pnlRegComCompromantesEmitidos);

        // --- pnlRegComAgregarProducto ---
        pnlRegComAgregarProducto.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegComAgregarProducto.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcAgrProd = new java.awt.GridBagConstraints();
        gbcAgrProd.insets = new java.awt.Insets(10, 10, 10, 10);
        gbcAgrProd.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblSubtemaAgregarProductoRegCom.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblSubtemaAgregarProductoRegCom.setText("AGREGAR PRODUCTO");
        gbcAgrProd.gridx = 0;
        gbcAgrProd.gridy = 0;
        gbcAgrProd.gridwidth = 2;
        pnlRegComAgregarProducto.add(lblSubtemaAgregarProductoRegCom, gbcAgrProd);

        // Subtitulo
        lblRegistrodeProductosRegCom.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblRegistrodeProductosRegCom.setText("• Registro de los productos");
        gbcAgrProd.gridy = 1;
        pnlRegComAgregarProducto.add(lblRegistrodeProductosRegCom, gbcAgrProd);

        // Producto
        lblProductoRegCom.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        lblProductoRegCom.setText("Producto");
        gbcAgrProd.gridy = 2;
        gbcAgrProd.gridwidth = 1;
        pnlRegComAgregarProducto.add(lblProductoRegCom, gbcAgrProd);

        txtProductoRegCom.setPreferredSize(new Dimension(170, 30));
        gbcAgrProd.gridx = 1;
        pnlRegComAgregarProducto.add(txtProductoRegCom, gbcAgrProd);

        // Cantidad
        lblCantidadRegCom.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        lblCantidadRegCom.setText("Cantidad");
        gbcAgrProd.gridx = 0;
        gbcAgrProd.gridy = 3;
        pnlRegComAgregarProducto.add(lblCantidadRegCom, gbcAgrProd);

        txtCantidadRegCom.setPreferredSize(new Dimension(170, 30));
        gbcAgrProd.gridx = 1;
        pnlRegComAgregarProducto.add(txtCantidadRegCom, gbcAgrProd);

        // Precio Unitario
        lblPreciioUnitarioRegCom.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        lblPreciioUnitarioRegCom.setText("Preciio Unitario:");
        gbcAgrProd.gridx = 0;
        gbcAgrProd.gridy = 4;
        pnlRegComAgregarProducto.add(lblPreciioUnitarioRegCom, gbcAgrProd);

        txtPreciioUnitarioRegCom.setPreferredSize(new Dimension(170, 30));
        gbcAgrProd.gridx = 1;
        pnlRegComAgregarProducto.add(txtPreciioUnitarioRegCom, gbcAgrProd);

        // Botones
        javax.swing.JPanel pnlBotonesAgr = new javax.swing.JPanel();
        pnlBotonesAgr.setOpaque(false);
        pnlBotonesAgr.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));

        btnAgregarProductoRegCom.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnAgregarProductoRegCom.setText("Agregar producto");
        btnAgregarProductoRegCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoRegComActionPerformed(evt);
            }
        });
        pnlBotonesAgr.add(btnAgregarProductoRegCom);

        btnCancelarRegCom.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnCancelarRegCom.setText("Cancelar");
        btnCancelarRegCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRegComActionPerformed(evt);
            }
        });
        pnlBotonesAgr.add(btnCancelarRegCom);

        gbcAgrProd.gridx = 0;
        gbcAgrProd.gridy = 5;
        gbcAgrProd.gridwidth = 2;
        gbcAgrProd.anchor = java.awt.GridBagConstraints.CENTER;
        pnlRegComAgregarProducto.add(pnlBotonesAgr, gbcAgrProd);

        tpnMostrar.addTab("pnlRegComAgregarProducto", pnlRegComAgregarProducto);

        // --- pnlRegVenAgregarProducto ---
        pnlRegVenAgregarProducto.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegVenAgregarProducto.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcAgrVen = new java.awt.GridBagConstraints();
        gbcAgrVen.insets = new java.awt.Insets(10, 10, 10, 10);
        gbcAgrVen.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblSubtemaAgregarProductoRegVen.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblSubtemaAgregarProductoRegVen.setText("AGREGAR PRODUCTO");
        gbcAgrVen.gridx = 0;
        gbcAgrVen.gridy = 0;
        gbcAgrVen.gridwidth = 2;
        pnlRegVenAgregarProducto.add(lblSubtemaAgregarProductoRegVen, gbcAgrVen);

        // Subtitulo
        lblAgregamosProductosRegVen.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblAgregamosProductosRegVen.setText("• Agregamos los productos");
        gbcAgrVen.gridy = 1;
        pnlRegVenAgregarProducto.add(lblAgregamosProductosRegVen, gbcAgrVen);

        // Producto (ComboBox)
        lblProductoRegVen.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        lblProductoRegVen.setText("Producto");
        gbcAgrVen.gridy = 2;
        gbcAgrVen.gridwidth = 1;
        pnlRegVenAgregarProducto.add(lblProductoRegVen, gbcAgrVen);

        cbTipoProductosRegVen.setPreferredSize(new Dimension(170, 30));
        cbTipoProductosRegVen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoProductosRegVenItemStateChanged(evt);
            }
        });
        gbcAgrVen.gridx = 1;
        pnlRegVenAgregarProducto.add(cbTipoProductosRegVen, gbcAgrVen);

        // Cantidad
        lblCantidadRegVen.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        lblCantidadRegVen.setText("Cantidad");
        gbcAgrVen.gridx = 0;
        gbcAgrVen.gridy = 3;
        pnlRegVenAgregarProducto.add(lblCantidadRegVen, gbcAgrVen);

        txtCantidadRegVen.setPreferredSize(new Dimension(170, 30));
        gbcAgrVen.gridx = 1;
        pnlRegVenAgregarProducto.add(txtCantidadRegVen, gbcAgrVen);

        // Precio Unitario
        lblPreciioUnitarioRegVen.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        lblPreciioUnitarioRegVen.setText("Preciio Unitario:");
        gbcAgrVen.gridx = 0;
        gbcAgrVen.gridy = 4;
        pnlRegVenAgregarProducto.add(lblPreciioUnitarioRegVen, gbcAgrVen);

        txtPreciioUnitarioRegVen.setPreferredSize(new Dimension(170, 30));
        gbcAgrVen.gridx = 1;
        pnlRegVenAgregarProducto.add(txtPreciioUnitarioRegVen, gbcAgrVen);

        // Botones
        javax.swing.JPanel pnlBotonesAgrVen = new javax.swing.JPanel();
        pnlBotonesAgrVen.setOpaque(false);
        pnlBotonesAgrVen.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));

        btnAgregarProductoRegVen.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnAgregarProductoRegVen.setText("Agregar producto");
        btnAgregarProductoRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoRegVenActionPerformed(evt);
            }
        });
        pnlBotonesAgrVen.add(btnAgregarProductoRegVen);

        btnCancelarRegVen.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnCancelarRegVen.setText("Cancelar");
        btnCancelarRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRegVenActionPerformed(evt);
            }
        });
        pnlBotonesAgrVen.add(btnCancelarRegVen);

        gbcAgrVen.gridx = 0;
        gbcAgrVen.gridy = 5;
        gbcAgrVen.gridwidth = 2;
        gbcAgrVen.anchor = java.awt.GridBagConstraints.CENTER;
        pnlRegVenAgregarProducto.add(pnlBotonesAgrVen, gbcAgrVen);

        tpnMostrar.addTab("pnlRegVenAgregarProducto", pnlRegVenAgregarProducto);

        // --- pnlRegVenFactura ---
        pnlRegVenFactura.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegVenFactura.setLayout(new java.awt.BorderLayout());

        // Panel Superior (Formulario Factura)
        javax.swing.JPanel pnlFacturaVenTop = new javax.swing.JPanel();
        pnlFacturaVenTop.setOpaque(false);
        pnlFacturaVenTop.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcFacVen = new java.awt.GridBagConstraints();
        gbcFacVen.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcFacVen.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblsubtemaFacturaRegVen.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblsubtemaFacturaRegVen.setText("FACTURA");
        gbcFacVen.gridx = 0;
        gbcFacVen.gridy = 0;
        gbcFacVen.gridwidth = 4;
        pnlFacturaVenTop.add(lblsubtemaFacturaRegVen, gbcFacVen);

        // Fila 1: Serie, Numero, Iconos
        lblSerieFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblSerieFacturaRegVen.setText("Serie:");
        gbcFacVen.gridy = 1;
        gbcFacVen.gridwidth = 1;
        gbcFacVen.gridx = 0;
        pnlFacturaVenTop.add(lblSerieFacturaRegVen, gbcFacVen);

        txtSerieFacturaRegVen.setPreferredSize(new Dimension(80, 30));
        gbcFacVen.gridx = 1;
        pnlFacturaVenTop.add(txtSerieFacturaRegVen, gbcFacVen);

        lblNumeroFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblNumeroFacturaRegVen.setText("Numero:");
        gbcFacVen.gridx = 2;
        pnlFacturaVenTop.add(lblNumeroFacturaRegVen, gbcFacVen);

        txtNumeroFacturaRegVen.setPreferredSize(new Dimension(80, 30));
        gbcFacVen.gridx = 3;
        pnlFacturaVenTop.add(txtNumeroFacturaRegVen, gbcFacVen);

        // Fila 2: Ruc, Moneda
        lblRucFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblRucFacturaRegVen.setText("Ruc:");
        gbcFacVen.gridy = 2;
        gbcFacVen.gridx = 0;
        pnlFacturaVenTop.add(lblRucFacturaRegVen, gbcFacVen);

        txtRucFacturaRegVen.setPreferredSize(new Dimension(180, 30));
        gbcFacVen.gridx = 1;
        pnlFacturaVenTop.add(txtRucFacturaRegVen, gbcFacVen);

        lblMonedaFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblMonedaFacturaRegVen.setText("Moneda:");
        gbcFacVen.gridx = 2;
        pnlFacturaVenTop.add(lblMonedaFacturaRegVen, gbcFacVen);

        cbTipoDeDineroFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeDineroFacturaRegVen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soles", "Dolares", " " }));
        cbTipoDeDineroFacturaRegVen.setPreferredSize(new Dimension(120, 30));
        gbcFacVen.gridx = 3;
        pnlFacturaVenTop.add(cbTipoDeDineroFacturaRegVen, gbcFacVen);

        // Fila 3: Cliente, Fecha
        lblClienteFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblClienteFacturaRegVen.setText("Cliente:");
        gbcFacVen.gridy = 3;
        gbcFacVen.gridx = 0;
        pnlFacturaVenTop.add(lblClienteFacturaRegVen, gbcFacVen);

        txtClienteFacturaRegVen.setPreferredSize(new Dimension(190, 30));
        gbcFacVen.gridx = 1;
        pnlFacturaVenTop.add(txtClienteFacturaRegVen, gbcFacVen);

        lblFechaFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblFechaFacturaRegVen.setText("Fecha:");
        gbcFacVen.gridx = 2;
        pnlFacturaVenTop.add(lblFechaFacturaRegVen, gbcFacVen);

        txtFechaFacturaRegVen.setPreferredSize(new Dimension(140, 30));
        gbcFacVen.gridx = 3;
        pnlFacturaVenTop.add(txtFechaFacturaRegVen, gbcFacVen);

        pnlRegVenFactura.add(pnlFacturaVenTop, BorderLayout.NORTH);

        // Panel Central (Tabla y Totales)
        pnlInternRegComFacturaRegVen.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternRegComFacturaRegVen.setLayout(new java.awt.BorderLayout());
        pnlInternRegComFacturaRegVen.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Decoracion superior
        txtDecoracion1FacturaRegVen.setEditable(false);
        txtDecoracion1FacturaRegVen.setBackground(new java.awt.Color(255, 255, 255));
        txtDecoracion1FacturaRegVen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDecoracion1FacturaRegVen.setText("DETALLE DE VENTA:");
        pnlInternRegComFacturaRegVen.add(txtDecoracion1FacturaRegVen, BorderLayout.NORTH);

        // Tabla
        tblRegistroFacturaRegVen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Cantidad", "Precio Unit.", "SubTotal", "I.G.V.", "Total"
            }
        ));
        jScrollPane7.setViewportView(tblRegistroFacturaRegVen);
        pnlInternRegComFacturaRegVen.add(jScrollPane7, BorderLayout.CENTER);

        // Totales
        javax.swing.JPanel pnlTotalesFactVen = new javax.swing.JPanel();
        pnlTotalesFactVen.setOpaque(false);
        pnlTotalesFactVen.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        txtDecoracion2FacturaRegVen.setEditable(false);
        txtDecoracion2FacturaRegVen.setBackground(new java.awt.Color(255, 255, 255));
        txtDecoracion2FacturaRegVen.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtDecoracion2FacturaRegVen.setForeground(new java.awt.Color(255, 51, 51));
        txtDecoracion2FacturaRegVen.setText("TOTAL");
        txtDecoracion2FacturaRegVen.setPreferredSize(new Dimension(80, 30));
        pnlTotalesFactVen.add(txtDecoracion2FacturaRegVen);

        txtTotalFacturaRegVen.setEnabled(false);
        txtTotalFacturaRegVen.setPreferredSize(new Dimension(80, 30));
        pnlTotalesFactVen.add(txtTotalFacturaRegVen);

        pnlInternRegComFacturaRegVen.add(pnlTotalesFactVen, BorderLayout.SOUTH);

        pnlRegVenFactura.add(pnlInternRegComFacturaRegVen, BorderLayout.CENTER);

        // Botones
        javax.swing.JPanel pnlBotonesFactVen = new javax.swing.JPanel();
        pnlBotonesFactVen.setOpaque(false);
        pnlBotonesFactVen.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        btnGuardarRegComFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnGuardarRegComFacturaRegVen.setText("Guardar");
        btnGuardarRegComFacturaRegVen.setPreferredSize(new Dimension(100, 30));
        btnGuardarRegComFacturaRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegComFacturaRegVenActionPerformed(evt);
            }
        });
        pnlBotonesFactVen.add(btnGuardarRegComFacturaRegVen);

        btnAgregarRegComFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAgregarRegComFacturaRegVen.setText("Agregar");
        btnAgregarRegComFacturaRegVen.setPreferredSize(new Dimension(100, 30));
        btnAgregarRegComFacturaRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRegComFacturaRegVenActionPerformed(evt);
            }
        });
        pnlBotonesFactVen.add(btnAgregarRegComFacturaRegVen);

        btnEliminarRegComFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEliminarRegComFacturaRegVen.setText("Eliminar");
        btnEliminarRegComFacturaRegVen.setPreferredSize(new Dimension(110, 30));
        btnEliminarRegComFacturaRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRegComFacturaRegVenActionPerformed(evt);
            }
        });
        pnlBotonesFactVen.add(btnEliminarRegComFacturaRegVen);

        btnEditarRegComFacturaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEditarRegComFacturaRegVen.setText("Editar");
        btnEditarRegComFacturaRegVen.setPreferredSize(new Dimension(100, 30));
        btnEditarRegComFacturaRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarRegComFacturaRegVenActionPerformed(evt);
            }
        });
        pnlBotonesFactVen.add(btnEditarRegComFacturaRegVen);

        pnlRegVenFactura.add(pnlBotonesFactVen, BorderLayout.SOUTH);

        tpnMostrar.addTab("pnlRegVenFactura", pnlRegVenFactura);

        pnlRegVenCompromantesEmitidos.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegVenCompromantesEmitidos.setLayout(null);

        lblSubtemaComprobantesEmitidosRegVen.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblSubtemaComprobantesEmitidosRegVen.setText("COMPROBANTES EMITIDOS");
        pnlRegVenCompromantesEmitidos.add(lblSubtemaComprobantesEmitidosRegVen);
        lblSubtemaComprobantesEmitidosRegVen.setBounds(40, 40, 310, 20);

        btnBuscarComprobantesEmitidosRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnBuscarComprobantesEmitidosRegVen.setText("Buscar");
        btnBuscarComprobantesEmitidosRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarComprobantesEmitidosRegVenActionPerformed(evt);
            }
        });
        pnlRegVenCompromantesEmitidos.add(btnBuscarComprobantesEmitidosRegVen);
        btnBuscarComprobantesEmitidosRegVen.setBounds(570, 60, 100, 30);

        btnPDFComprobantesEmitidosRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnPDFComprobantesEmitidosRegVen.setText("PDF");
        btnPDFComprobantesEmitidosRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFComprobantesEmitidosRegVenActionPerformed(evt);
            }
        });
        pnlRegVenCompromantesEmitidos.add(btnPDFComprobantesEmitidosRegVen);
        btnPDFComprobantesEmitidosRegVen.setBounds(570, 100, 100, 30);

        btnAnularComprobantesEmitidosRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAnularComprobantesEmitidosRegVen.setText("Anular");
        btnAnularComprobantesEmitidosRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularComprobantesEmitidosRegVenActionPerformed(evt);
            }
        });
        pnlRegVenCompromantesEmitidos.add(btnAnularComprobantesEmitidosRegVen);
        btnAnularComprobantesEmitidosRegVen.setBounds(570, 140, 100, 30);

        tblRegistrodeComprobantesEmitidosRegVen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblRegistrodeComprobantesEmitidosRegVen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha de registro", "Tipo de comprobantes", "Serie", "Numero", "Cliente", "Total"
            }
        ));
        tblRegistrodeComprobantesEmitidosRegVen.setAutoscrolls(false);
        tblRegistrodeComprobantesEmitidosRegVen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblRegistrodeComprobantesEmitidosRegVen.setPreferredSize(new java.awt.Dimension(890, 140));
        jScrollPane8.setViewportView(tblRegistrodeComprobantesEmitidosRegVen);

        pnlRegVenCompromantesEmitidos.add(jScrollPane8);
        jScrollPane8.setBounds(20, 180, 540, 300);

        lblimagen1ComprobantesEmitidosRegVen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lupa.png"))); // NOI18N
        pnlRegVenCompromantesEmitidos.add(lblimagen1ComprobantesEmitidosRegVen);
        lblimagen1ComprobantesEmitidosRegVen.setBounds(40, 90, 40, 30);

        cbTipodeBusquedaComprobantesEmitidosRegVen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Numero", " Serie", " Cliente" }));
        pnlRegVenCompromantesEmitidos.add(cbTipodeBusquedaComprobantesEmitidosRegVen);
        cbTipodeBusquedaComprobantesEmitidosRegVen.setBounds(70, 120, 470, 30);
        pnlRegVenCompromantesEmitidos.add(txtBuscarComprobantesEmitidosRegVen);
        txtBuscarComprobantesEmitidosRegVen.setBounds(70, 90, 470, 30);

        tpnMostrar.addTab("pnlRegVenCompromantesEmitidos", pnlRegVenCompromantesEmitidos);

        pnlCaja.setLayout(null);

        lblGananciascaja.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGananciascaja.setForeground(new java.awt.Color(176, 148, 0));
        lblGananciascaja.setText("■   GANANCIAS");
        pnlCaja.add(lblGananciascaja);
        lblGananciascaja.setBounds(30, 130, 180, 30);

        lblINGRESOScaja.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblINGRESOScaja.setForeground(new java.awt.Color(255, 0, 204));
        lblINGRESOScaja.setText("■   INGRESOS");
        pnlCaja.add(lblINGRESOScaja);
        lblINGRESOScaja.setBounds(30, 30, 170, 30);

        lblEGRESOScaja.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblEGRESOScaja.setForeground(new java.awt.Color(136, 216, 252));
        lblEGRESOScaja.setText("■   EGRESOS");
        pnlCaja.add(lblEGRESOScaja);
        lblEGRESOScaja.setBounds(30, 80, 170, 30);

        pnlInternodecaja.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternodecaja.setLayout(null);

        lblimagen1caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lupa.png"))); // NOI18N
        pnlInternodecaja.add(lblimagen1caja);
        lblimagen1caja.setBounds(20, 90, 40, 30);
        pnlInternodecaja.add(txtBusquedacaja);
        txtBusquedacaja.setBounds(40, 90, 230, 30);

        lblEgresoscaja.setText("               Egresos");
        pnlInternodecaja.add(lblEgresoscaja);
        lblEgresoscaja.setBounds(250, 40, 150, 30);

        btnBusquedacaja.setText("Busqueda");
        pnlInternodecaja.add(btnBusquedacaja);
        btnBusquedacaja.setBounds(280, 90, 100, 30);

        lblHistorialdeMovimientoscaja.setText("   Historial de movimientos");
        pnlInternodecaja.add(lblHistorialdeMovimientoscaja);
        lblHistorialdeMovimientoscaja.setBounds(20, 10, 230, 30);

        lblIngresoscaja.setText("                           Ingresos");
        pnlInternodecaja.add(lblIngresoscaja);
        lblIngresoscaja.setBounds(20, 40, 230, 30);

        tblRegistrodeCaja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Fecha", "Totales del dia"
            }
        ));
        jScrollPane9.setViewportView(tblRegistrodeCaja);

        pnlInternodecaja.add(jScrollPane9);
        jScrollPane9.setBounds(20, 130, 590, 120);

        pnlCaja.add(pnlInternodecaja);
        pnlInternodecaja.setBounds(20, 210, 630, 260);

        txtGANACIAScaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGANACIAScajaActionPerformed(evt);
            }
        });
        pnlCaja.add(txtGANACIAScaja);
        txtGANACIAScaja.setBounds(140, 130, 90, 30);

        txtINGRESOScaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtINGRESOScajaActionPerformed(evt);
            }
        });
        pnlCaja.add(txtINGRESOScaja);
        txtINGRESOScaja.setBounds(140, 30, 90, 30);

        txtEGRESOScaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEGRESOScajaActionPerformed(evt);
            }
        });
        pnlCaja.add(txtEGRESOScaja);
        txtEGRESOScaja.setBounds(140, 80, 90, 30);
        pnlCaja.add(Grafico_Caja);
        Grafico_Caja.setBounds(260, 40, 390, 160);

        btn_Grafico_Caja.setText("Graficar");
        btn_Grafico_Caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Grafico_CajaActionPerformed(evt);
            }
        });
        pnlCaja.add(btn_Grafico_Caja);
        btn_Grafico_Caja.setBounds(420, 10, 120, 23);

        tpnMostrar.addTab("pnlCaja", pnlCaja);

        // --- pnlRegVenBoleta ---
        pnlRegVenBoleta.setBackground(new java.awt.Color(216, 252, 156));
        pnlRegVenBoleta.setLayout(new java.awt.BorderLayout());

        // Panel Superior (Formulario Boleta)
        javax.swing.JPanel pnlBoletaVenTop = new javax.swing.JPanel();
        pnlBoletaVenTop.setOpaque(false);
        pnlBoletaVenTop.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcBolVen = new java.awt.GridBagConstraints();
        gbcBolVen.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcBolVen.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblsubtemaBoletaRegVen.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblsubtemaBoletaRegVen.setText("BOLETA");
        gbcBolVen.gridx = 0;
        gbcBolVen.gridy = 0;
        gbcBolVen.gridwidth = 4;
        pnlBoletaVenTop.add(lblsubtemaBoletaRegVen, gbcBolVen);

        // Fila 1: Serie, Numero
        lblSerieBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblSerieBoletaRegVen.setText("Serie:");
        gbcBolVen.gridy = 1;
        gbcBolVen.gridwidth = 1;
        gbcBolVen.gridx = 0;
        pnlBoletaVenTop.add(lblSerieBoletaRegVen, gbcBolVen);

        txtSerieBoletaRegVen.setPreferredSize(new Dimension(80, 30));
        gbcBolVen.gridx = 1;
        pnlBoletaVenTop.add(txtSerieBoletaRegVen, gbcBolVen);

        lblNumeroBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblNumeroBoletaRegVen.setText("Numero:");
        gbcBolVen.gridx = 2;
        pnlBoletaVenTop.add(lblNumeroBoletaRegVen, gbcBolVen);

        txtNumeroBoletaRegVen.setPreferredSize(new Dimension(80, 30));
        gbcBolVen.gridx = 3;
        pnlBoletaVenTop.add(txtNumeroBoletaRegVen, gbcBolVen);

        // Fila 2: OP (Tipo Documento), Moneda
        lblOPBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblOPBoletaRegVen.setText("OP:");
        gbcBolVen.gridy = 2;
        gbcBolVen.gridx = 0;
        pnlBoletaVenTop.add(lblOPBoletaRegVen, gbcBolVen);

        cbTipoOPBoletaRegVen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin documento", "DNI", "Ruc" }));
        cbTipoOPBoletaRegVen.setPreferredSize(new Dimension(190, 30));
        gbcBolVen.gridx = 1;
        pnlBoletaVenTop.add(cbTipoOPBoletaRegVen, gbcBolVen);

        lblMonedaBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblMonedaBoletaRegVen.setText("Moneda:");
        gbcBolVen.gridx = 2;
        pnlBoletaVenTop.add(lblMonedaBoletaRegVen, gbcBolVen);

        cbTipoDeDineroBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeDineroBoletaRegVen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soles", "Dolares", " " }));
        cbTipoDeDineroBoletaRegVen.setPreferredSize(new Dimension(120, 30));
        gbcBolVen.gridx = 3;
        pnlBoletaVenTop.add(cbTipoDeDineroBoletaRegVen, gbcBolVen);

        // Fila 3: Cliente, Fecha
        lblClienteBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblClienteBoletaRegVen.setText("Cliente:");
        gbcBolVen.gridy = 3;
        gbcBolVen.gridx = 0;
        pnlBoletaVenTop.add(lblClienteBoletaRegVen, gbcBolVen);

        txtClienteBoletaRegVen.setPreferredSize(new Dimension(190, 30));
        gbcBolVen.gridx = 1;
        pnlBoletaVenTop.add(txtClienteBoletaRegVen, gbcBolVen);

        lblFechaBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblFechaBoletaRegVen.setText("Fecha:");
        gbcBolVen.gridx = 2;
        pnlBoletaVenTop.add(lblFechaBoletaRegVen, gbcBolVen);

        txtFechaBoletaRegVen.setPreferredSize(new Dimension(140, 30));
        gbcBolVen.gridx = 3;
        pnlBoletaVenTop.add(txtFechaBoletaRegVen, gbcBolVen);

        pnlRegVenBoleta.add(pnlBoletaVenTop, BorderLayout.NORTH);

        // Panel Central (Tabla y Totales)
        pnlInternoBoletaRegVen.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternoBoletaRegVen.setLayout(new java.awt.BorderLayout());
        pnlInternoBoletaRegVen.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Decoracion Superior
        txtDecoracion1BoletaRegVen.setEditable(false);
        txtDecoracion1BoletaRegVen.setBackground(new java.awt.Color(255, 255, 255));
        txtDecoracion1BoletaRegVen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDecoracion1BoletaRegVen.setText("DETALLE DE VENTA:");
        pnlInternoBoletaRegVen.add(txtDecoracion1BoletaRegVen, BorderLayout.NORTH);

        // Tabla
        tblRegistroBoletaRegVen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Cantidad", "Precio Unit.", "SubTotal", "I.G.V.", "Total"
            }
        ));
        jScrollPane10.setViewportView(tblRegistroBoletaRegVen);
        pnlInternoBoletaRegVen.add(jScrollPane10, BorderLayout.CENTER);

        // Totales
        javax.swing.JPanel pnlTotalesBoletaVen = new javax.swing.JPanel();
        pnlTotalesBoletaVen.setOpaque(false);
        pnlTotalesBoletaVen.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        txtDecoracion2BoletaRegVen.setEditable(false);
        txtDecoracion2BoletaRegVen.setBackground(new java.awt.Color(255, 255, 255));
        txtDecoracion2BoletaRegVen.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtDecoracion2BoletaRegVen.setForeground(new java.awt.Color(255, 51, 51));
        txtDecoracion2BoletaRegVen.setText("TOTAL");
        txtDecoracion2BoletaRegVen.setPreferredSize(new Dimension(80, 30));
        pnlTotalesBoletaVen.add(txtDecoracion2BoletaRegVen);

        txtTotalBoletaRegVen.setEnabled(false);
        txtTotalBoletaRegVen.setPreferredSize(new Dimension(80, 30));
        pnlTotalesBoletaVen.add(txtTotalBoletaRegVen);

        pnlInternoBoletaRegVen.add(pnlTotalesBoletaVen, BorderLayout.SOUTH);

        pnlRegVenBoleta.add(pnlInternoBoletaRegVen, BorderLayout.CENTER);

        // Botones
        javax.swing.JPanel pnlBotonesBolVen = new javax.swing.JPanel();
        pnlBotonesBolVen.setOpaque(false);
        pnlBotonesBolVen.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        btnGuardarBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnGuardarBoletaRegVen.setText("Guardar");
        btnGuardarBoletaRegVen.setPreferredSize(new Dimension(100, 30));
        btnGuardarBoletaRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarBoletaRegVenActionPerformed(evt);
            }
        });
        pnlBotonesBolVen.add(btnGuardarBoletaRegVen);

        btnAgregarBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAgregarBoletaRegVen.setText("Agregar");
        btnAgregarBoletaRegVen.setPreferredSize(new Dimension(100, 30));
        btnAgregarBoletaRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBoletaRegVenActionPerformed(evt);
            }
        });
        pnlBotonesBolVen.add(btnAgregarBoletaRegVen);

        btnEliminarBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEliminarBoletaRegVen.setText("Eliminar");
        btnEliminarBoletaRegVen.setPreferredSize(new Dimension(110, 30));
        btnEliminarBoletaRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarBoletaRegVenActionPerformed(evt);
            }
        });
        pnlBotonesBolVen.add(btnEliminarBoletaRegVen);

        btnEditarBoletaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEditarBoletaRegVen.setText("Editar");
        btnEditarBoletaRegVen.setPreferredSize(new Dimension(100, 30));
        btnEditarBoletaRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarBoletaRegVenActionPerformed(evt);
            }
        });
        pnlBotonesBolVen.add(btnEditarBoletaRegVen);

        pnlRegVenBoleta.add(pnlBotonesBolVen, BorderLayout.SOUTH);

        tpnMostrar.addTab("pnlRegVenBoleta", pnlRegVenBoleta);

        pnlReportes.setLayout(null);

        lblREPORTEREGISTRODEVENTASReportes.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblREPORTEREGISTRODEVENTASReportes.setText("REPORTE-REGISTRO DE VENTAS");
        pnlReportes.add(lblREPORTEREGISTRODEVENTASReportes);
        lblREPORTEREGISTRODEVENTASReportes.setBounds(50, 40, 300, 30);
        pnlReportes.add(txtRangodeperiodoReportes);
        txtRangodeperiodoReportes.setBounds(450, 160, 190, 30);

        lblRangodeperiodoReportes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblRangodeperiodoReportes.setText("Rango de periodo:");
        pnlReportes.add(lblRangodeperiodoReportes);
        lblRangodeperiodoReportes.setBounds(320, 160, 130, 30);
        pnlReportes.add(txtPeriodoReportes);
        txtPeriodoReportes.setBounds(380, 110, 180, 30);

        lblSurcusalesReportes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblSurcusalesReportes.setText("Surcusales:");
        pnlReportes.add(lblSurcusalesReportes);
        lblSurcusalesReportes.setBounds(50, 110, 90, 30);

        cbTipoSurcusalesReportes.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        cbTipoSurcusalesReportes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "• Facturas", "• Boletas", "• Proformas" }));
        pnlReportes.add(cbTipoSurcusalesReportes);
        cbTipoSurcusalesReportes.setBounds(130, 110, 150, 30);

        btnDescargarRegistrodeVentasReportes.setBackground(new java.awt.Color(202, 244, 250));
        btnDescargarRegistrodeVentasReportes.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnDescargarRegistrodeVentasReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nube.png"))); // NOI18N
        btnDescargarRegistrodeVentasReportes.setText(" Descargar Registro de Ventas");
        btnDescargarRegistrodeVentasReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarRegistrodeVentasReportesActionPerformed(evt);
            }
        });
        pnlReportes.add(btnDescargarRegistrodeVentasReportes);
        btnDescargarRegistrodeVentasReportes.setBounds(100, 260, 460, 60);

        lblPeriodoReportes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblPeriodoReportes.setText("Período:");
        pnlReportes.add(lblPeriodoReportes);
        lblPeriodoReportes.setBounds(310, 110, 70, 30);

        tpnMostrar.addTab("pnlReportes", pnlReportes);

        pnlAjustes.setLayout(null);

        lblGestionarperfilAjustes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGestionarperfilAjustes.setForeground(new java.awt.Color(78, 154, 169));
        lblGestionarperfilAjustes.setText("•      Gestionar perfil");
        pnlAjustes.add(lblGestionarperfilAjustes);
        lblGestionarperfilAjustes.setBounds(40, 50, 150, 20);

        lblDatosAjustes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDatosAjustes.setForeground(new java.awt.Color(48, 174, 76));
        lblDatosAjustes.setText("•      Datos");
        pnlAjustes.add(lblDatosAjustes);
        lblDatosAjustes.setBounds(40, 160, 150, 20);
        pnlAjustes.add(txtNombreAjustes);
        txtNombreAjustes.setBounds(110, 100, 180, 30);

        lblNombreAjustes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblNombreAjustes.setText("Nombre:");
        pnlAjustes.add(lblNombreAjustes);
        lblNombreAjustes.setBounds(40, 100, 70, 30);

        lblImagen1Ajustes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblImagen1Ajustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo.png"))); // NOI18N
        pnlAjustes.add(lblImagen1Ajustes);
        lblImagen1Ajustes.setBounds(420, 70, 80, 90);
        pnlAjustes.add(txtRUCAjustes);
        txtRUCAjustes.setBounds(110, 220, 180, 30);

        lblRUCAjustes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblRUCAjustes.setText("RUC:");
        pnlAjustes.add(lblRUCAjustes);
        lblRUCAjustes.setBounds(40, 220, 70, 30);
        pnlAjustes.add(txtTelefonoAjustes);
        txtTelefonoAjustes.setBounds(400, 220, 180, 30);

        lblTelefonoAjustes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblTelefonoAjustes.setText("Telefono:");
        pnlAjustes.add(lblTelefonoAjustes);
        lblTelefonoAjustes.setBounds(330, 220, 70, 30);
        pnlAjustes.add(txtCorreodeEmpresaAjustes);
        txtCorreodeEmpresaAjustes.setBounds(180, 280, 180, 30);

        lblCorreodeEmpresaAjustes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblCorreodeEmpresaAjustes.setText("Correo de Empresa:");
        pnlAjustes.add(lblCorreodeEmpresaAjustes);
        lblCorreodeEmpresaAjustes.setBounds(40, 280, 140, 30);

        cbTipoAspectoAjustes.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        cbTipoAspectoAjustes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "• Claro", "• Oscuro" }));
        pnlAjustes.add(cbTipoAspectoAjustes);
        cbTipoAspectoAjustes.setBounds(440, 340, 150, 30);

        lblIDIOMAAjustes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblIDIOMAAjustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Planeta.png"))); // NOI18N
        lblIDIOMAAjustes.setText("IDIOMA");
        pnlAjustes.add(lblIDIOMAAjustes);
        lblIDIOMAAjustes.setBounds(110, 340, 120, 30);

        btnGuardacambiosAjustes.setBackground(new java.awt.Color(240, 236, 220));
        btnGuardacambiosAjustes.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        btnGuardacambiosAjustes.setText("Guardar cambios");
        btnGuardacambiosAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardacambiosAjustesActionPerformed(evt);
            }
        });
        pnlAjustes.add(btnGuardacambiosAjustes);
        btnGuardacambiosAjustes.setBounds(170, 410, 340, 30);

        lblApectoAjustes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblApectoAjustes.setText("Aspecto:");
        pnlAjustes.add(lblApectoAjustes);
        lblApectoAjustes.setBounds(350, 340, 90, 30);

        lblLogoAjustes.setFont(new java.awt.Font("Corbel Light", 1, 14)); // NOI18N
        lblLogoAjustes.setText("Logo:");
        pnlAjustes.add(lblLogoAjustes);
        lblLogoAjustes.setBounds(340, 100, 70, 30);

        cbLenguaje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Español", "Ingles" }));
        cbLenguaje.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLenguajeItemStateChanged(evt);
            }
        });
        pnlAjustes.add(cbLenguaje);
        cbLenguaje.setBounds(60, 370, 160, 30);

        tpnMostrar.addTab("pnlAjustes", pnlAjustes);

        // --- pnlRegVenProform ---
        pnlRegVenProform.setBackground(new java.awt.Color(211, 251, 155));
        pnlRegVenProform.setLayout(new java.awt.BorderLayout());

        // Panel Superior
        javax.swing.JPanel pnlProformaVenTop = new javax.swing.JPanel();
        pnlProformaVenTop.setOpaque(false);
        pnlProformaVenTop.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbcProfVen = new java.awt.GridBagConstraints();
        gbcProfVen.insets = new java.awt.Insets(5, 5, 5, 5);
        gbcProfVen.anchor = java.awt.GridBagConstraints.WEST;

        // Titulo
        lblsubtemaProformaRegVen.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblsubtemaProformaRegVen.setText("PROFORMA");
        gbcProfVen.gridx = 0;
        gbcProfVen.gridy = 0;
        gbcProfVen.gridwidth = 4;
        pnlProformaVenTop.add(lblsubtemaProformaRegVen, gbcProfVen);

        // Fila 1: Nombres
        lblNombresProformaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblNombresProformaRegVen.setText("Nombres:");
        gbcProfVen.gridy = 1;
        gbcProfVen.gridwidth = 1;
        gbcProfVen.gridx = 0;
        pnlProformaVenTop.add(lblNombresProformaRegVen, gbcProfVen);

        txtNombresProformaRegVen.setPreferredSize(new Dimension(300, 30));
        gbcProfVen.gridx = 1;
        gbcProfVen.gridwidth = 3;
        pnlProformaVenTop.add(txtNombresProformaRegVen, gbcProfVen);

        // Fila 2: Moneda, Fecha
        lblMonedaProformaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblMonedaProformaRegVen.setText("Moneda:");
        gbcProfVen.gridy = 2;
        gbcProfVen.gridx = 0;
        gbcProfVen.gridwidth = 1;
        pnlProformaVenTop.add(lblMonedaProformaRegVen, gbcProfVen);

        cbTipoDeDineroProformaRegVen.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        cbTipoDeDineroProformaRegVen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soles", "Dólares" }));
        cbTipoDeDineroProformaRegVen.setPreferredSize(new Dimension(120, 30));
        gbcProfVen.gridx = 1;
        pnlProformaVenTop.add(cbTipoDeDineroProformaRegVen, gbcProfVen);

        lblFechaProformaRegVen.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        lblFechaProformaRegVen.setText("Fecha:");
        gbcProfVen.gridx = 2;
        pnlProformaVenTop.add(lblFechaProformaRegVen, gbcProfVen);

        txtFechaProformaRegVen.setPreferredSize(new Dimension(110, 30));
        gbcProfVen.gridx = 3;
        pnlProformaVenTop.add(txtFechaProformaRegVen, gbcProfVen);

        pnlRegVenProform.add(pnlProformaVenTop, BorderLayout.NORTH);

        // Panel Central
        pnlInternoRegVenProforma.setBackground(new java.awt.Color(255, 255, 255));
        pnlInternoRegVenProforma.setLayout(new java.awt.BorderLayout());
        pnlInternoRegVenProforma.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Decoracion Superior
        txtDetalledeVentasRegVen.setEditable(false);
        txtDetalledeVentasRegVen.setBackground(new java.awt.Color(255, 255, 255));
        txtDetalledeVentasRegVen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDetalledeVentasRegVen.setText("DETALLE DE VENTA:");
        pnlInternoRegVenProforma.add(txtDetalledeVentasRegVen, BorderLayout.NORTH);

        // Tabla
        tblRegistroProformaRegVen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Cantidad", "Precio Unit.", "Subtotal", "I.G.V.", "Total"
            }
        ));
        jScrollPane11.setViewportView(tblRegistroProformaRegVen);
        pnlInternoRegVenProforma.add(jScrollPane11, BorderLayout.CENTER);

        // Totales
        javax.swing.JPanel pnlTotalesProfVen = new javax.swing.JPanel();
        pnlTotalesProfVen.setOpaque(false);
        pnlTotalesProfVen.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        txtDecoracion1ProformaRegVen.setEditable(false);
        txtDecoracion1ProformaRegVen.setBackground(new java.awt.Color(255, 255, 255));
        txtDecoracion1ProformaRegVen.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtDecoracion1ProformaRegVen.setForeground(new java.awt.Color(255, 51, 51));
        txtDecoracion1ProformaRegVen.setText("TOTAL");
        txtDecoracion1ProformaRegVen.setPreferredSize(new Dimension(80, 30));
        pnlTotalesProfVen.add(txtDecoracion1ProformaRegVen);

        txtTotalProformaRegVen.setEnabled(false);
        txtTotalProformaRegVen.setPreferredSize(new Dimension(80, 30));
        pnlTotalesProfVen.add(txtTotalProformaRegVen);

        pnlInternoRegVenProforma.add(pnlTotalesProfVen, BorderLayout.SOUTH);

        pnlRegVenProform.add(pnlInternoRegVenProforma, BorderLayout.CENTER);

        // Botones
        javax.swing.JPanel pnlBotonesProfVen = new javax.swing.JPanel();
        pnlBotonesProfVen.setOpaque(false);
        pnlBotonesProfVen.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        btnGuardarRegVenProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnGuardarRegVenProforma.setText("Guardar");
        btnGuardarRegVenProforma.setPreferredSize(new Dimension(100, 30));
        btnGuardarRegVenProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegVenProformaActionPerformed(evt);
            }
        });
        pnlBotonesProfVen.add(btnGuardarRegVenProforma);

        btnAgregarRegVenProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnAgregarRegVenProforma.setText("Agregar");
        btnAgregarRegVenProforma.setPreferredSize(new Dimension(100, 30));
        btnAgregarRegVenProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRegVenProformaActionPerformed(evt);
            }
        });
        pnlBotonesProfVen.add(btnAgregarRegVenProforma);

        btnEliminarRegVenProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEliminarRegVenProforma.setText("Eliminar");
        btnEliminarRegVenProforma.setPreferredSize(new Dimension(110, 30));
        btnEliminarRegVenProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRegVenProformaActionPerformed(evt);
            }
        });
        pnlBotonesProfVen.add(btnEliminarRegVenProforma);

        btnEditarRegVenProforma.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        btnEditarRegVenProforma.setText("Editar");
        btnEditarRegVenProforma.setPreferredSize(new Dimension(100, 30));
        btnEditarRegVenProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarRegVenProformaActionPerformed(evt);
            }
        });
        pnlBotonesProfVen.add(btnEditarRegVenProforma);

        pnlRegVenProform.add(pnlBotonesProfVen, BorderLayout.SOUTH);

        tpnMostrar.addTab("pnlRegVenProforma", pnlRegVenProform);

        getContentPane().add(tpnMostrar);
        tpnMostrar.setBounds(190, 80, 690, 610);

        setMinimumSize(new java.awt.Dimension(850, 600)); // Establecer tamaño minimo para evitar colapso
        pack(); // Ajustar tamaño de ventana al contenido
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //esto coloque para el cmabio de idioma
    public javax.swing.JLabel getLblInventario() {
        return lblinventario;
    }

    public JLabel getBtnInicio() {
        return btninicio;
    }

    public JLabel getBtnAlmacen() {
        return btnAlmacen;
    }

    public JLabel getBtnRegistroDeCompras() {
        return btnregistrodecompras;
    }

    public JLabel getBtnRegistroDeVentas() {
        return btnregistrodeventas;
    }

    public JLabel getBtnCaja() {
        return btncaja;
    }

    public JLabel getBtnRegistroDeUsuario() {
        return btnregistrodeusuario;
    }

    public JLabel getBtnReportes() {
        return btnreportes;
    }

    public JLabel getBtnAjustes() {
        return btnajustes;
    }

    public JLabel getBtnCerrarSesion() {
        return btncerrarsesion;
    }

    // pnl1Inicio
    public JLabel getLblSubTitulo() {
        return lblSubTitulo;
    }

    public JLabel getLblGanancias() {
        return lblGanancias;
    }

    public JLabel getLblProveedores() {
        return lblProveedores;
    }

    public JLabel getLblCompraDelMes() {
        return lblCompraDelMes;
    }

    public JLabel getLblVentasDelDia() {
        return lblVentasDelDia;
    }

    public JLabel getLblClientes() {
        return lblClientes;
    }

    public JLabel getLblProductos() {
        return lblProductos;
    }

    // pnlRegUsuMostrar
    public JButton getBtnCrearUsuario() {
        return btnCrearUsuario;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    // pnlRegUsuIngresar
    public JLabel getLblAgregaryCrearUsuario() {
        return lblAgregaryCrearUsuario;
    }

    public JLabel getLblSubTitulo1() {
        return lblSubTitulo1;
    }

    public JLabel getLblNombres() {
        return lblNombres;
    }

    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    public JLabel getLblContraseña() {
        return lblContraseña;
    }

    public JLabel getLblApellidos() {
        return lblApellidos;
    }

    public JLabel getLblDNIUsuario() {
        return lblDNIUsuario;
    }

    public JLabel getLblTipoUsuario() {
        return lblTipoUsuario;
    }

    public JButton getBtnGuardaryAgregarDatos() {
        return btnGuardaryAgregarDatos;
    }

    // pnl1Almacen
    public JLabel getLblSubtema2() {
        return lblSubtema2;
    }

    public JLabel getLblProducto() {
        return lblProducto;
    }

    public JLabel getLblCantidaddeProducto() {
        return lblCantidaddeProducto;
    }

    public JLabel getLblPreciodeProducto() {
        return lblPreciodeProducto;
    }

    public JButton getBtnAgregarAlmacen() {
        return btnAgregarAlmacen;
    }

    public JButton getBtnEliminarAlmacen() {
        return btnEliminarAlmacen;
    }

    public JButton getBtnEditarAlmacen() {
        return btnEditarAlmacen;
    }

    public JButton getBtnBuscarAlmacen() {
        return btnBuscarAlmacen;
    }

    public JButton getBtnFiltrarAlmacen() {
        return btnFiltrarAlmacen;
    }

    // pnlRegComFactura
    public JLabel getLblsubtemaFactura() {
        return lblsubtemaFactura;
    }

    public JLabel getLblSerieFactura() {
        return lblSerieFactura;
    }

    public JLabel getLblProveedorFactura() {
        return lblProveedorFactura;
    }

    public JLabel getLblResponsableFactura() {
        return lblResponsableFactura;
    }

    public JLabel getblNumeroFactura() {
        return lblNumeroFactura;
    }

    public JLabel getLblMonedaFactura() {
        return lblMonedaFactura;
    }

    public JLabel getLblFechaFactura() {
        return lblFechaFactura;
    }

    public JButton getBtnGuardarRegComFactura() {
        return btnGuardarRegComFactura;
    }

    public JButton getBtnAgregarRegComFactura() {
        return btnAgregarRegComFactura;
    }

    public JButton getBtnEliminarRegComFactura() {
        return btnEliminarRegComFactura;
    }

    public JButton getBtnEditarRegComFactura() {
        return btnEditarRegComFactura;
    }

    // pnlRegComBoleta
    public JLabel getLblsubtemaBoleta() {
        return lblsubtemaBoleta;
    }

    public JLabel getLblSerieBoleta() {
        return lblSerieBoleta;
    }

    public JLabel getLblProveedorBoleta() {
        return lblProveedorBoleta;
    }

    public JLabel getLblResponsableBoleta() {
        return lblResponsableBoleta;
    }

    public JLabel getblNumeroBoleta() {
        return lblNumeroBoleta;
    }

    public JLabel getLblMonedaBoleta() {
        return lblMonedaBoleta;
    }

    public JLabel getLblFechaBoleta() {
        return lblFechaBoleta;
    }

    public JButton getBtnGuardarRegComBoleta() {
        return btnGuardarRegComBoleta;
    }

    public JButton getBtnAgregarRegComBoleta() {
        return btnAgregarRegComBoleta;
    }

    public JButton getBtnEliminarRegComBoleta() {
        return btnEliminarRegComBoleta;
    }

    public JButton getBtnEditarRegComBoleta() {
        return btnEditarRegComBoleta;
    }

    // pnlRegComProforma
    public JLabel getLblsubtemaProforma() {
        return lblsubtemaProforma;
    }

    public JLabel getLblNombresProforma() {
        return lblNombresProforma;
    }

    public JLabel getLblResponsableProforma() {
        return lblResponsableProforma;
    }

    public JLabel getLblMonedaProforma() {
        return lblMonedaProforma;
    }

    public JLabel getLblFechaProforma() {
        return lblFechaProforma;
    }

    public JButton getBtnGuardarRegComProforma() {
        return btnGuardarRegComProforma;
    }

    public JButton getBtnAgregarRegComProforma() {
        return btnAgregarRegComProforma;
    }

    public JButton getBtnEliminarRegComProforma() {
        return btnEliminarRegComProforma;
    }

    public JButton getBtnEditarRegComProforma() {
        return btnEditarRegComProforma;
    }

    // pnlRegComCompromantesEmitidos
    public JLabel getLblSubtemaComprobantesEmitidos() {
        return lblSubtemaComprobantesEmitidos;
    }

    public JButton getBtnBuscarComprobantesEmitidos() {
        return btnBuscarComprobantesEmitidos;
    }

    public JButton getBtnPDFComprobantesEmitidos() {
        return btnPDFComprobantesEmitidos;
    }

    public JButton getBtnAnularComprobantesEmitidos() {
        return btnAnularComprobantesEmitidos;
    }

    // pnlRegComAgregarProducto
    public JLabel getLblSubtemaAgregarProductoRegCom() {
        return lblSubtemaAgregarProductoRegCom;
    }

    public JLabel getLblRegistrodeProductosRegCom() {
        return lblRegistrodeProductosRegCom;
    }

    public JLabel getLblProductoRegCom() {
        return lblProductoRegCom;
    }

    public JLabel getLblCantidadRegCom() {
        return lblCantidadRegCom;
    }

    public JLabel getLblPreciioUnitarioRegCom() {
        return lblPreciioUnitarioRegCom;
    }

    public JButton getBtnAgregarProductoRegCom() {
        return btnAgregarProductoRegCom;
    }

    public JButton getBtnCancelarRegCom() {
        return btnCancelarRegCom;
    }

    // pnlRegVenAgregarProducto
    public JLabel getLblSubtemaAgregarProductoRegVen() {
        return lblSubtemaAgregarProductoRegVen;
    }

    public JLabel getLblAgregamosProductosRegVen() {
        return lblAgregamosProductosRegVen;
    }

    public JLabel getLblProductoRegVen() {
        return lblProductoRegVen;
    }

    public JLabel getLblCantidadRegVen() {
        return lblCantidadRegVen;
    }

    public JLabel getLblPreciioUnitarioRegVen() {
        return lblPreciioUnitarioRegVen;
    }

    public JButton getBtnAgregarProductoRegVen() {
        return btnAgregarProductoRegVen;
    }

    public JButton getBtnCancelarRegVen() {
        return btnCancelarRegVen;
    }

    // pnlRegVenFactura
    public JLabel getLblsubtemaFacturaRegVen() {
        return lblsubtemaFacturaRegVen;
    }

    public JLabel getLblRucFacturaRegVen() {
        return lblRucFacturaRegVen;
    }

    public JLabel getLblClienteFacturaRegVen() {
        return lblClienteFacturaRegVen;
    }

    public JLabel getLblFechaFacturaRegVen() {
        return lblFechaFacturaRegVen;
    }

    public JLabel getLblSerieFacturaRegVen() {
        return lblSerieFacturaRegVen;
    }

    public JLabel getLblMonedaFacturaRegVen() {
        return lblMonedaFacturaRegVen;
    }

    public JLabel getLblNumeroFacturaRegVen() {
        return lblNumeroFacturaRegVen;
    }

    public JButton getBtnGuardarRegComFacturaRegVen() {
        return btnGuardarRegComFacturaRegVen;
    }

    public JButton getBtnAgregarRegComFacturaRegVen() {
        return btnAgregarRegComFacturaRegVen;
    }

    public JButton getBtnEliminarRegComFacturaRegVen() {
        return btnEliminarRegComFacturaRegVen;
    }

    public JButton getBtnEditarRegComFacturaRegVen() {
        return btnEditarRegComFacturaRegVen;
    }

    // pnlRegVenCompromantesEmitidos
    public JLabel getLblSubtemaComprobantesEmitidosRegVen() {
        return lblSubtemaComprobantesEmitidosRegVen;
    }

    public JButton getBtnBuscarComprobantesEmitidosRegVen() {
        return btnBuscarComprobantesEmitidosRegVen;
    }

    public JButton getBtnPDFComprobantesEmitidosRegVen() {
        return btnPDFComprobantesEmitidosRegVen;
    }

    public JButton getBtnAnularComprobantesEmitidosRegVen() {
        return btnAnularComprobantesEmitidosRegVen;
    }

    // pnlCaja
    public JLabel getLblINGRESOScaja() {
        return lblINGRESOScaja;
    }

    public JLabel getLblEGRESOScaja() {
        return lblEGRESOScaja;
    }

    public JLabel getLblGananciascaja() {
        return lblGananciascaja;
    }

    public JTextField getLblHistorialdeMovimientoscaja() {
        return lblHistorialdeMovimientoscaja;
    }

    public JToggleButton getBtnBusquedacaja() {
        return btnBusquedacaja;
    }

    // pnlRegVenBoleta
    public JLabel getLblsubtemaBoletaRegVen() {
        return lblsubtemaBoletaRegVen;
    }

    public JLabel getLblOPBoletaRegVen() {
        return lblOPBoletaRegVen;
    }

    public JLabel getLblClienteBoletaRegVen() {
        return lblClienteBoletaRegVen;
    }

    public JLabel getLblFechaBoletaRegVen() {
        return lblFechaBoletaRegVen;
    }

    public JLabel getLblSerieBoletaRegVen() {
        return lblSerieBoletaRegVen;
    }

    public JLabel getLblMonedaBoletaRegVen() {
        return lblMonedaBoletaRegVen;
    }

    public JLabel getLblNumeroBoletaRegVen() {
        return lblNumeroBoletaRegVen;
    }

    public JButton getBtnGuardarBoletaRegVen() {
        return btnGuardarBoletaRegVen;
    }

    public JButton getBtnAgregarBoletaRegVen() {
        return btnAgregarBoletaRegVen;
    }

    public JButton getBtnEliminarBoletaRegVen() {
        return btnEliminarBoletaRegVen;
    }

    public JButton getBtnEditarBoletaRegVen() {
        return btnEditarBoletaRegVen;
    }

    //pnlReportes
    public JLabel getLblREPORTEREGISTRODEVENTASReportes() {
        return lblREPORTEREGISTRODEVENTASReportes;
    }

    public JLabel getLblSurcusalesReportes() {
        return lblSurcusalesReportes;
    }

    public JLabel getLblPeriodoReportes() {
        return lblPeriodoReportes;
    }

    public JLabel getLblRangodeperiodoReportes() {
        return lblRangodeperiodoReportes;
    }

    public JButton getBtnDescargarRegistrodeVentasReportes() {
        return btnDescargarRegistrodeVentasReportes;
    }

    // pnlAjustes
    public JLabel getLblGestionarperfilAjustes() {
        return lblGestionarperfilAjustes;
    }

    public JLabel getLblNombreAjustes() {
        return lblNombreAjustes;
    }

    public JLabel getLblLogoAjustes() {
        return lblLogoAjustes;
    }

    public JLabel getLblDatosAjustes() {
        return lblDatosAjustes;
    }

    public JLabel getLblRUCAjustes() {
        return lblRUCAjustes;
    }

    public JLabel getLblTelefonoAjustes() {
        return lblTelefonoAjustes;
    }

    public JLabel getLblCorreodeEmpresaAjustes() {
        return lblCorreodeEmpresaAjustes;
    }

    public JLabel getLblIDIOMAAjustes() {
        return lblIDIOMAAjustes;
    }

    public JLabel getLblApectoAjustes() {
        return lblApectoAjustes;
    }

    public JButton getBtnGuardacambiosAjustes() {
        return btnGuardacambiosAjustes;
    }

    // pnlRegVenProform
    public JLabel getLblsubtemaProformaRegVen() {
        return lblsubtemaProformaRegVen;
    }

    public JLabel getLblNombresProformaRegVen() {
        return lblNombresProformaRegVen;
    }

    public JLabel getLblMonedaProformaRegVen() {
        return lblMonedaProformaRegVen;
    }

    public JLabel getLblFechaProformaRegVen() {
        return lblFechaProformaRegVen;
    }

    public JTextField getTxtDetalledeVentasRegVen() {
        return txtDetalledeVentasRegVen;
    }

    public JButton getBtnGuardarRegVenProforma() {
        return btnGuardarRegVenProforma;
    }

    public JButton getBtnAgregarRegVenProforma() {
        return btnAgregarRegVenProforma;
    }

    public JButton getBtnEliminarRegVenProforma() {
        return btnEliminarRegVenProforma;
    }

    public JButton getBtnEditarRegVenProforma() {
        return btnEditarRegVenProforma;
    }

    //hasta aca

    private void btninicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btninicioMouseClicked
        tpnMostrar.setSelectedIndex(pnlInicio);
    }//GEN-LAST:event_btninicioMouseClicked

    private void btnAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlmacenMouseClicked
        GestorModelos.actualizarTblAlmacen(tblRegistroProductos, baseDeDatos.obtenerProductos());
        tpnMostrar.setSelectedIndex(pnlAlmac);
        GestorModelos.buscarProductoAgotado(tblRegistroProductos, baseDeDatos);

    }//GEN-LAST:event_btnAlmacenMouseClicked

    private void btnregistrodecomprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrodecomprasMouseClicked

    }//GEN-LAST:event_btnregistrodecomprasMouseClicked

    private void btnregistrodeventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrodeventasMouseClicked

    }//GEN-LAST:event_btnregistrodeventasMouseClicked

    private void btncajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncajaMouseClicked
        tpnMostrar.setSelectedIndex(iPnlCaja);
    }//GEN-LAST:event_btncajaMouseClicked

    private void btnregistrodeusuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrodeusuarioMouseClicked
        GestorModelos.actualizarUsuarios(tblRegistroUsuarios, baseDeDatos.obtenerUsuarios());
        tpnMostrar.setSelectedIndex(pnlRegUsu);


    }//GEN-LAST:event_btnregistrodeusuarioMouseClicked

    private void btnreportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnreportesMouseClicked
        tpnMostrar.setSelectedIndex(iPnlReportes);
    }//GEN-LAST:event_btnreportesMouseClicked

    private void btnajustesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnajustesMouseClicked
        tpnMostrar.setSelectedIndex(pnlAjuste);
    }//GEN-LAST:event_btnajustesMouseClicked

    private void btncerrarsesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncerrarsesionMouseClicked
        if (JOptionPane.showConfirmDialog(this, "¿Esta seguro de cerrar sesion?", "Lo extrañaremos ;)", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
            baseDeDatos.cerrarConexion();
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
        }
    }//GEN-LAST:event_btncerrarsesionMouseClicked

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        // TONOTDO, DONT add your handling code here:
        tpnMostrar.setSelectedIndex(pnlCrearUsu);
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private boolean faltanDatosEnTxt(JTextField... verificame) {
        for (JTextField txt : verificame) {
            if (txt.getText().isBlank()) {
                return true;
            }
        }
        return false;
    }

    private boolean faltanDatosEnTxt(String[] verificame) {
        for (String txt : verificame) {
            if (txt.isBlank()) {
                return true;
            }
        }
        return false;
    }

    private void btnGuardaryAgregarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardaryAgregarDatosActionPerformed
        String errorMensaje = "";
        boolean noIngresar = false;
        if (faltanDatosEnTxt(txtNombres, txtApellidos, txtTelefono, txtDNIUsuario, txtContraseña)) {
            errorMensaje += "Error: Falta llenar datos";
            noIngresar = true;
        }

        if (Usuario.muyLargo(txtNombres.getText()) || Usuario.muyLargo(txtApellidos.getText()) || Usuario.muyLargo(txtContraseña.getText())) {
            errorMensaje += "Error: nombres o apellidos o contraseña muy largos.\n";
            noIngresar = true;
        }

        Integer telefono = Usuario.tryParseTelefono(txtTelefono.getText());
        Long usuarioDNI = Usuario.tryParseUsuarioDNI(txtDNIUsuario.getText());
        if (telefono == null) {
            errorMensaje += "Error: Formato de telefono no valido. Debe ser de 6 o 9 digitos\n";
            noIngresar = true;
        }
        if (usuarioDNI == null) {
            errorMensaje += "Error: Formato de DNI no valido.\n";
            noIngresar = true;
        }
        if (noIngresar) {
            JOptionPane.showMessageDialog(this, errorMensaje);
            return;
        }

        boolean esCuentaInactiva = rbtInactivo.isSelected();
        baseDeDatos.crearCuentaUsuario(usuarioDNI, txtNombres.getText(), txtApellidos.getText(), Integer.parseInt(txtTelefono.getText()), txtContraseña.getText(), cbTipoUsuario.getSelectedItem().toString(), esCuentaInactiva);
        GestorModelos.actualizarUsuarios(tblRegistroUsuarios, baseDeDatos.obtenerUsuarios());
    }//GEN-LAST:event_btnGuardaryAgregarDatosActionPerformed

    private void rbtActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtActivoActionPerformed
        rbtActivo.setSelected(true);
        rbtInactivo.setSelected(false);

    }//GEN-LAST:event_rbtActivoActionPerformed

    private void rbtInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtInactivoActionPerformed
        rbtInactivo.setSelected(true);
        rbtActivo.setSelected(false);

    }//GEN-LAST:event_rbtInactivoActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, "¿Esta seguro de salir? Su sesion se cerrará automaticamente.", "Lo extrañaremos ;)", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            baseDeDatos.cerrarConexion();
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private String[] separarNombresApellidos(String nombresApellidos) {
        int tamaño = nombresApellidos.length();
        String[] nombre_apellido = new String[]{"", ""};
        int j = 0;
        for (int i = 0; i < tamaño; i++) {
            if (nombresApellidos.charAt(i) == ' ' && j < nombre_apellido.length - 1) {
                j++;
                continue;
            }
            nombre_apellido[j] += nombresApellidos.charAt(i);
        }
        return nombre_apellido;
    }

    private Integer getSeleccionEliminar(JTable objetivo) {
        int filaSeleccion = objetivo.getSelectedRow();
        if (filaSeleccion < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar");
            return null;
        }
        return filaSeleccion;
    }

    private boolean cancelarEliminacion() {
        if (JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar la fila seleccionada?", "Ya fue, no habrá vuelta atras :,v", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            return false;
        }
        return true;
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (cancelarEliminacion()) {
            return;
        }

        Integer filaSeleccion = getSeleccionEliminar(tblRegistroUsuarios);
        if (filaSeleccion == null) {
            return;
        }
        int usuario_id = GestorModelos.getUsuarioID(tblRegistroUsuarios, filaSeleccion);
        baseDeDatos.eliminarUsuario(usuario_id);
        GestorModelos.actualizarUsuarios(tblRegistroUsuarios, baseDeDatos.obtenerUsuarios());
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAlmacenActionPerformed
        String errorMensaje = "";
        boolean noRegistrar = false;
        if (faltanDatosEnTxt(txtProducto, txtCantidaddeProducto, txtPreciodeCompra)) {
            JOptionPane.showMessageDialog(this, "Faltan ingresar datos de producto");
            return;
        }
        Integer cantidadProducto = Producto.tryParseCantidad(txtCantidaddeProducto.getText());
        Double precioCompra = Producto.tryParsePrecio(txtPreciodeCompra.getText());
        String producto = txtProducto.getText();
        if (cantidadProducto == null) {
            errorMensaje += "Formato de cantidad incorrecto. Debe ingresar un número natural.\n";
            noRegistrar = true;
        }
        if (precioCompra == null) {
            errorMensaje += "Formato de precio incorrecto. Debe ingresar un número real.\n";
            noRegistrar = true;
        }
        if (noRegistrar) {
            JOptionPane.showMessageDialog(this, errorMensaje);
            return;
        }
        //                  (String tipoDoc,String producto,double precio,int cantidad,String stock) {
        baseDeDatos.crearProducto("Sin documento", producto, precioCompra, cantidadProducto, Producto.calcularStock(cantidadProducto));
        GestorModelos.actualizarTblAlmacen(tblRegistroProductos, baseDeDatos.obtenerProductos());
    }//GEN-LAST:event_btnAgregarAlmacenActionPerformed

    private void btnEliminarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAlmacenActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistroProductos);
        if (filaSeleccion == null) {
            return;
        }
        int producto_id = GestorModelos.getProductoID(tblRegistroProductos, filaSeleccion);
        baseDeDatos.eliminarProducto(producto_id);
        GestorModelos.actualizarTblAlmacen(tblRegistroProductos, baseDeDatos.obtenerProductos());
    }//GEN-LAST:event_btnEliminarAlmacenActionPerformed

    private void btnEditarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAlmacenActionPerformed
        Integer filaSeleccion = getSeleccionEditar(tblRegistroProductos);
        if (filaSeleccion == null) {
            return;
        }

        //Revisar no espacios en blanco
        if (GestorModelos.tieneCeldasVacias(tblRegistroProductos, filaSeleccion)) {
            JOptionPane.showMessageDialog(this, "Faltan datos en la fila");
            return;
        }

        //formato correcto
        String errorMensaje = "";
        boolean noEntrar = false;
        Integer producto_id = Integer.parseInt(GestorModelos.getValueAt(tblRegistroProductos, filaSeleccion, 0).toString());
        String tipoDoc = GestorModelos.getValueAt(tblRegistroProductos, filaSeleccion, 1).toString();
        String producto = GestorModelos.getValueAt(tblRegistroProductos, filaSeleccion, 2).toString();
        Double precio = Double.parseDouble(GestorModelos.getValueAt(tblRegistroProductos, filaSeleccion, 3).toString());
        if (precio == null) {
            errorMensaje += "Formato de precio incorrecto. Ingrese un numero real.\n";
            noEntrar = true;
        }

        Integer cantidad = Integer.parseInt(GestorModelos.getValueAt(tblRegistroProductos, filaSeleccion, 4).toString());
        if (cantidad == null) {
            errorMensaje += "Formato de precio incorrecto. Ingrese un numero natural.\n";
            noEntrar = true;
        }

        if (noEntrar) {
            JOptionPane.showMessageDialog(this, errorMensaje);
            return;
        }

        String stock = GestorModelos.getValueAt(tblRegistroProductos, filaSeleccion, 5).toString();

        baseDeDatos.actualizarProducto(producto_id, tipoDoc, producto, precio, cantidad, stock);
        GestorModelos.actualizarTblAlmacen(tblRegistroProductos, baseDeDatos.obtenerProductos());
    }//GEN-LAST:event_btnEditarAlmacenActionPerformed

    private void btnFiltrarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarAlmacenActionPerformed
        GestorModelos.actualizarTblAlmacen(tblRegistroProductos, baseDeDatos.obtenerProductos());
        GestorModelos.filtrarProductos(tblRegistroProductos, cbTipoStock);
    }//GEN-LAST:event_btnFiltrarAlmacenActionPerformed

    private void btnBuscarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlmacenActionPerformed
        //revisar que no este en blanco
        if (faltanDatosEnTxt(txtBuscar) && !txtBuscar.getText().equals(" ") || btnBuscarAlmacen.getText().equals("Revertir")) {
            GestorModelos.actualizarTblAlmacen(tblRegistroProductos, baseDeDatos.obtenerProductos());
            btnBuscarAlmacen.setText("Buscar");
            txtBuscar.setEnabled(true);
            return;
        }
        GestorModelos.buscarProductoAlm(tblRegistroProductos, txtBuscar.getText());
        btnBuscarAlmacen.setText("Revertir");
        txtBuscar.setEnabled(false);


    }//GEN-LAST:event_btnBuscarAlmacenActionPerformed

    private void jmFACTURASRegComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFACTURASRegComActionPerformed
        //cambiar al panel
        tpnMostrar.setSelectedIndex(pnlComFact);

    }//GEN-LAST:event_jmFACTURASRegComActionPerformed

    private void jmBOLETASRegComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmBOLETASRegComActionPerformed
        tpnMostrar.setSelectedIndex(pnlComBole);
    }//GEN-LAST:event_jmBOLETASRegComActionPerformed

    private void jmOTROSRegComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmOTROSRegComActionPerformed
        tpnMostrar.setSelectedIndex(pnlComProf);
    }//GEN-LAST:event_jmOTROSRegComActionPerformed

    private void jmCOMPROBATESEMITIDOSRegComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCOMPROBATESEMITIDOSRegComActionPerformed
        //actualizar la tabla
        GestorModelos.actualizarCompEmit(tblRegistrodeComprobantesEmitidos, baseDeDatos.obtenerComprobantes());
        tpnMostrar.setSelectedIndex(pnlCompEmit);
    }//GEN-LAST:event_jmCOMPROBATESEMITIDOSRegComActionPerformed

    private void guardarProductosEnBD(ArrayList<Producto> listaProductos) {
        for (Producto prod : listaProductos) {
            baseDeDatos.crearProducto(prod.getTipoDocumento(), prod.getProducto(), prod.getPrecioCompra(), prod.getCantidad(), prod.getStock());
        }

    }

    private void btnGuardarRegComFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegComFacturaActionPerformed
        if (faltanDatosEnTxt(txtSerieFactura, txtNumeroFactura, txtProveedorFactura, txtFechaFactura) || tblRegistroFactura.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(this, "Faltan datos de factura y/o productos.");
            return;
        }
        Long serieFact = Long.parseLong(txtSerieFactura.getText());
        Long numFact = Long.parseLong(txtNumeroFactura.getText());
        String proveedor = txtProveedorFactura.getText();
        String responsable = cbTipoDeLiderFactura.getSelectedItem().toString();
        String moneda = cbTipoDeDineroFactura.getSelectedItem().toString();
        guardarProductosEnBD(tempListFact);
        baseDeDatos.crearComprobante(txtFechaFactura.getText(), "Factura", serieFact, numFact, proveedor, Double.parseDouble(txtTotalFactura.getText()));
        comprobanteRegistrado();
    }//GEN-LAST:event_btnGuardarRegComFacturaActionPerformed

    private void btnGuardarRegComBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegComBoletaActionPerformed
        if (faltanDatosEnTxt(txtSerieBoleta, txtNumeroBoleta, txtProveedorBoleta, txtFechaBoleta) || tblRegistroBoleta.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(this, "Faltan datos de boleta y/o productos.");
            return;
        }
        Long serieBoleta = Long.parseLong(txtSerieBoleta.getText());
        Long numBoleta = Long.parseLong(txtNumeroBoleta.getText());
        String proveedor = txtProveedorBoleta.getText();
        String responsable = cbTipoDeLiderBoleta.getSelectedItem().toString();
        String moneda = cbTipoDeDineroBoleta.getSelectedItem().toString();
        guardarProductosEnBD(tempListBole);
        //crearComprobante(String fechaRegistro, String tipoComprobante, int serie, int numero, String proveedor, double total) {
        baseDeDatos.crearComprobante(txtFechaBoleta.getText(), "Boleta", serieBoleta, numBoleta, proveedor, Double.parseDouble(txtTotalBoleta.getText()));
        comprobanteRegistrado();
    }//GEN-LAST:event_btnGuardarRegComBoletaActionPerformed

    private void comprobanteRegistrado() {
        JOptionPane.showMessageDialog(this, "Documento registrado correctamente.");
    }

    private void btnGuardarRegComProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegComProformaActionPerformed
        if (faltanDatosEnTxt(txtNombresProforma, txtFechaProforma) || tblRegistroProforma.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(this, "Faltan datos de proforma y/o productos.");
            return;
        }
        String proveedor = txtNombresProforma.getText();
        guardarProductosEnBD(tempListProf);
        //crearComprobante(String fechaRegistro, String tipoComprobante, int serie, int numero, String proveedor, double total) {
        baseDeDatos.crearComprobante(txtFechaProforma.getText(), "Proforma", 0, 0, proveedor, Double.parseDouble(txtTotalProforma.getText()));
        comprobanteRegistrado();
    }//GEN-LAST:event_btnGuardarRegComProformaActionPerformed

    private void btnAgregarRegComFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRegComFacturaActionPerformed
        tblPadre = tblRegistroFactura;
        pnlPadreComVenProd = pnlComFact;
        tpnMostrar.setSelectedIndex(pnlAgrComProd);
    }//GEN-LAST:event_btnAgregarRegComFacturaActionPerformed

    private void btnAgregarRegComBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRegComBoletaActionPerformed
        tblPadre = tblRegistroBoleta;
        pnlPadreComVenProd = pnlComBole;
        tpnMostrar.setSelectedIndex(pnlAgrComProd);
    }//GEN-LAST:event_btnAgregarRegComBoletaActionPerformed

    private void btnAgregarRegComProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRegComProformaActionPerformed
        tblPadre = tblRegistroProforma;
        pnlPadreComVenProd = pnlComProf;
        tpnMostrar.setSelectedIndex(pnlAgrComProd);
    }//GEN-LAST:event_btnAgregarRegComProformaActionPerformed

    private void btnElimAccion(JTable tbl, int filaSeleccion, ArrayList<Producto> list, JTextField txtf) {
        int prodId = GestorModelos.getProductoID(tbl, filaSeleccion);
        list.remove(prodId);
        GestorModelos.actualizarRegsCompVenProd(tbl, list, txtf);
    }

    private void btnEliminarRegComFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRegComFacturaActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistroFactura);
        if (filaSeleccion == null) {
            return;
        }
        btnElimAccion(tblRegistroFactura, filaSeleccion, tempListFact, txtTotalFactura);
    }//GEN-LAST:event_btnEliminarRegComFacturaActionPerformed

    private void btnEliminarRegComBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRegComBoletaActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistroBoleta);
        if (filaSeleccion == null) {
            return;
        }
        btnElimAccion(tblRegistroBoleta, filaSeleccion, tempListBole, txtTotalBoleta);
    }//GEN-LAST:event_btnEliminarRegComBoletaActionPerformed

    private void btnEliminarRegComProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRegComProformaActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistroProforma);
        if (filaSeleccion == null) {
            return;
        }
        btnElimAccion(tblRegistroProforma, filaSeleccion, tempListProf, txtTotalProforma);
    }//GEN-LAST:event_btnEliminarRegComProformaActionPerformed

    private boolean tryBtnEditarAccion(String tipoDoc, JTable tbl, int filaSeleccion, ArrayList<Producto> tempList) {
        Integer idLista = Integer.parseInt(GestorModelos.getValueAt(tbl, filaSeleccion, 0).toString());
        String producto = GestorModelos.getValueAt(tbl, filaSeleccion, 1).toString();
        Integer cantidad = Integer.parseInt(GestorModelos.getValueAt(tbl, filaSeleccion, 2).toString());
        Double precioUni = Double.parseDouble(GestorModelos.getValueAt(tbl, filaSeleccion, 3).toString());
        if (producto == null || cantidad == null || precioUni == null) {
            JOptionPane.showMessageDialog(null, "Tipo de dato incorrecto en producto o cantidad o precioUni");
            return false;
        }
        Producto editado = new Producto(tipoDoc, producto, cantidad, precioUni);
        tempList.set(idLista, editado);
        return true;
    }

    private void btnEditarRegComFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarRegComFacturaActionPerformed
        Integer filaSeleccion = getSeleccionEditar(tblRegistroFactura);
        if (filaSeleccion == null) {
            return;
        }
        if (GestorModelos.tieneCeldasVacias(tblRegistroFactura, filaSeleccion)) {
            JOptionPane.showMessageDialog(this, "Faltan datos en la fila");
            return;
        }
        if (!tryBtnEditarAccion("Factura", tblRegistroFactura, filaSeleccion, tempListFact)) {
            return;
        }
        GestorModelos.actualizarRegsCompVenProd(tblRegistroFactura, tempListFact, txtTotalFactura);
    }//GEN-LAST:event_btnEditarRegComFacturaActionPerformed

    private void btnEditarRegComBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarRegComBoletaActionPerformed
        Integer filaSeleccion = getSeleccionEditar(tblRegistroBoleta);
        if (filaSeleccion == null) {
            return;
        }
        if (GestorModelos.tieneCeldasVacias(tblRegistroBoleta, filaSeleccion)) {
            JOptionPane.showMessageDialog(this, "Faltan datos en la fila");
            return;
        }
        if (!tryBtnEditarAccion("Boleta", tblRegistroBoleta, filaSeleccion, tempListBole)) {
            return;
        }
        GestorModelos.actualizarRegsCompVenProd(tblRegistroBoleta, tempListBole, txtTotalBoleta);
    }//GEN-LAST:event_btnEditarRegComBoletaActionPerformed

    private void btnEditarRegComProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarRegComProformaActionPerformed
        Integer filaSeleccion = getSeleccionEditar(tblRegistroProforma);
        if (filaSeleccion == null) {
            return;
        }
        if (GestorModelos.tieneCeldasVacias(tblRegistroProforma, filaSeleccion)) {
            JOptionPane.showMessageDialog(this, "Faltan datos en la fila");
            return;
        }
        if (!tryBtnEditarAccion("Proforma", tblRegistroProforma, filaSeleccion, tempListProf)) {
            return;
        }
        GestorModelos.actualizarRegsCompVenProd(tblRegistroProforma, tempListProf, txtTotalProforma);
    }//GEN-LAST:event_btnEditarRegComProformaActionPerformed

    private void btnCancelarRegComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegComActionPerformed
        tpnMostrar.setSelectedIndex(pnlPadreComVenProd);
    }//GEN-LAST:event_btnCancelarRegComActionPerformed

    private String calcularTipoDoc(JTable padre) {
        String rpta = "";
        if (padre == tblRegistroFactura) {
            rpta = "Factura";
        } else if (padre == tblRegistroBoleta) {
            rpta = "Boleta";
        } else if (padre == tblRegistroProforma) {
            rpta = "Proforma";
        } else {
            rpta = "No definido";
        }
        return rpta;
    }

    private JTextField IdentificarTxt(int pnlPadre) {
        JTextField rpta = new JTextField();
        switch (pnlPadre) {
            case pnlComFact:
                rpta = txtTotalFactura;
                break;
            case pnlComBole:
                rpta = txtTotalBoleta;
                break;
            case pnlComProf:
                rpta = txtTotalProforma;
                break;
            case pnlVenFactura:
                rpta = txtTotalFacturaRegVen;
                break;
            case pnlVenBoleta:
                rpta = txtTotalBoletaRegVen;
                break;
            case iPnlVenProforma:
                rpta = txtTotalProformaRegVen;

        }
        return rpta;
    }

    private ArrayList<Producto> identificarLista(JTable tblPadre) {
        ArrayList<Producto> rpta = new ArrayList<>();

        if (tblPadre == tblRegistroFactura) {
            rpta = tempListFact;
        } else if (tblPadre == tblRegistroBoleta) {
            rpta = tempListBole;
        } else if (tblPadre == tblRegistroProforma) {
            rpta = tempListProf;
        } else if (tblPadre == tblRegistroFacturaRegVen) {
            rpta = tempListVenFact;
        } else if (tblPadre == tblRegistroBoletaRegVen) {
            rpta = tempListVenBole;
        } else if (tblPadre == tblRegistroProformaRegVen) {
            rpta = tempListVenProf;
        }

        return rpta;
    }

    private void btnAgregarProductoRegComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoRegComActionPerformed
        if (faltanDatosEnTxt(txtProductoRegCom, txtPreciioUnitarioRegCom, txtCantidadRegCom)) {
            JOptionPane.showMessageDialog(this, "Faltan datos de producto");
            return;
        }
        String error = "";
        boolean allahuAkbar = false;
        Double precio = Producto.tryParsePrecio(txtPreciioUnitarioRegCom.getText());
        if (precio == null) {
            error += "Precio no valido. Ingrese un número real.\n";
            allahuAkbar = true;
        }
        Integer cantidad = Producto.tryParseCantidad(txtCantidadRegCom.getText());
        if (cantidad == null) {
            error += "Cantidad no valida. Ingrese un número natural.\n";
            allahuAkbar = true;
        }
        //explotar el programa
        if (allahuAkbar) {
            //rm -rf / BOOM
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        String tipoDoc = calcularTipoDoc(tblPadre);
        JTextField txtTotal = IdentificarTxt(pnlPadreComVenProd);
        Producto prod = new Producto(tipoDoc, txtProductoRegCom.getText(), cantidad, precio);

        ArrayList<Producto> listaTemp = identificarLista(tblPadre);
        listaTemp.add(prod);
        GestorModelos.actualizarRegsCompVenProd(tblPadre, listaTemp, txtTotal);
        tpnMostrar.setSelectedIndex(pnlPadreComVenProd);
    }//GEN-LAST:event_btnAgregarProductoRegComActionPerformed

    private void btnCancelarRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegVenActionPerformed
        tpnMostrar.setSelectedIndex(pnlPadreComVenProd);
    }//GEN-LAST:event_btnCancelarRegVenActionPerformed

    private void btnAgregarProductoRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoRegVenActionPerformed
        if (faltanDatosEnTxt(txtPreciioUnitarioRegVen, txtPreciioUnitarioRegVen, txtCantidadRegVen)) {
            JOptionPane.showMessageDialog(this, "Faltan datos de producto");
            return;
        }

        Producto prod = baseDeDatos.buscarProducto(cbTipoProductosRegVen.getSelectedItem().toString());
        JTextField txtTotal = IdentificarTxt(pnlPadreComVenProd);
        ArrayList<Producto> listaTemp = identificarLista(tblPadre);
        listaTemp.add(prod);
        GestorModelos.actualizarRegsCompVenProd(tblPadre, listaTemp, txtTotal);
        tpnMostrar.setSelectedIndex(pnlPadreComVenProd);
    }//GEN-LAST:event_btnAgregarProductoRegVenActionPerformed

    private void btnAnularComprobantesEmitidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularComprobantesEmitidosActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistrodeComprobantesEmitidos);
        if (filaSeleccion == null) {
            return;
        }
        baseDeDatos.eliminarComprobante(Integer.parseInt(GestorModelos.getValueAt(tblRegistrodeComprobantesEmitidos, filaSeleccion, 1).toString()));
        GestorModelos.actualizarCompEmit(tblRegistrodeComprobantesEmitidos, baseDeDatos.obtenerComprobantes());

    }//GEN-LAST:event_btnAnularComprobantesEmitidosActionPerformed

    public Integer getSeleccionEditar(JTable editame) {
        int filaSeleccion = editame.getSelectedRow();
        if (filaSeleccion < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para editar");
            return null;
        }
        return filaSeleccion;
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Integer filaSeleccion = getSeleccionEditar(tblRegistroUsuarios);
        if (filaSeleccion == null) {
            return;
        }

        if (GestorModelos.tieneCeldasVacias(tblRegistroUsuarios, filaSeleccion)) {
            JOptionPane.showMessageDialog(this, "Faltan datos en la fila");
            return;
        }

        int usuID = Integer.parseInt(GestorModelos.getValueAt(tblRegistroUsuarios, filaSeleccion, 0).toString());
        //Empleado nombre String
        String[] nombres_apellidos = separarNombresApellidos(GestorModelos.getValueAt(tblRegistroUsuarios, filaSeleccion, 1).toString());
        String nombre = nombres_apellidos[0];
        String apellido = nombres_apellidos[1];
        //UsuarioDNIoRUC Long
        long usuarioDNI = Long.parseLong(GestorModelos.getValueAt(tblRegistroUsuarios, filaSeleccion, 2).toString());
        //Contraseña String
        String contra = GestorModelos.getValueAt(tblRegistroUsuarios, filaSeleccion, 3).toString();
        //Tipo String
        String rol = GestorModelos.getValueAt(tblRegistroUsuarios, filaSeleccion, 4).toString();
        //Telefono int
        int fono = Integer.parseInt(GestorModelos.getValueAt(tblRegistroUsuarios, filaSeleccion, 5).toString());

        //Estado boolean
        boolean esCuentaBloqueada = Usuario.parseEsCuentaBloqueada(GestorModelos.getValueAt(tblRegistroUsuarios, filaSeleccion, 6).toString());
        //long usuarioDNI, String contraseña, String rol, LocalDate fechaUltimoCambio,int intentosFallidos, boolean cuentaBloqueada, String nombres, String apellidos, int telefono, int usuario_id
        baseDeDatos.actualizarUsuario(usuarioDNI, contra, rol, LocalDate.now(), 0, esCuentaBloqueada, nombre, apellido, fono, usuID);
        GestorModelos.actualizarUsuarios(tblRegistroUsuarios, baseDeDatos.obtenerUsuarios());

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBuscarComprobantesEmitidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarComprobantesEmitidosActionPerformed
        //revisar que no este en blanco
        if (faltanDatosEnTxt(txtBuscarComprobantesEmitidos) && !txtBuscarComprobantesEmitidos.getText().equals(" ") || btnBuscarComprobantesEmitidos.getText().equals("Revertir")) {
            GestorModelos.actualizarCompEmit(tblRegistrodeComprobantesEmitidos, baseDeDatos.obtenerComprobantes());
            btnBuscarComprobantesEmitidos.setText("Buscar");
            txtBuscarComprobantesEmitidos.setEnabled(true);
            return;
        }
        int indice = 0;
        String seleccion = cbTipodeBusquedaComprobantesEmitidos.getSelectedItem().toString();
        switch (cbTipodeBusquedaComprobantesEmitidos.getSelectedIndex()) {
            case 0:
                indice = 5;
                break;
            case 1:
                indice = 4;
                break;
            case 2:
                indice = 6;
                break;
            default:
                throw new AssertionError();
        }
        GestorModelos.buscarCompEmit(tblRegistrodeComprobantesEmitidos, txtBuscarComprobantesEmitidos.getText(), indice);
        btnBuscarComprobantesEmitidos.setText("Revertir");
        txtBuscarComprobantesEmitidos.setEnabled(false);
    }//GEN-LAST:event_btnBuscarComprobantesEmitidosActionPerformed

    private void btnAnularComprobantesEmitidosRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularComprobantesEmitidosRegVenActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistrodeComprobantesEmitidosRegVen);
        if (filaSeleccion == null) {
            return;
        }
        baseDeDatos.eliminarComprobanteVenta(Integer.parseInt(GestorModelos.getValueAt(tblRegistrodeComprobantesEmitidosRegVen, filaSeleccion, 1).toString()));
        GestorModelos.actualizarCompVenEmit(tblRegistrodeComprobantesEmitidosRegVen, baseDeDatos.obtenerComprobantesVenta());

    }//GEN-LAST:event_btnAnularComprobantesEmitidosRegVenActionPerformed

    private void btnEditarRegComFacturaRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarRegComFacturaRegVenActionPerformed
        Integer filaSeleccion = getSeleccionEditar(tblRegistroFacturaRegVen);
        if (filaSeleccion == null) {
            return;
        }
        if (GestorModelos.tieneCeldasVacias(tblRegistroFacturaRegVen, filaSeleccion)) {
            JOptionPane.showMessageDialog(this, "Faltan datos en la fila");
            return;
        }
        if (!tryBtnEditarAccion("Factura", tblRegistroFacturaRegVen, filaSeleccion, tempListVenFact)) {
            return;
        }
        GestorModelos.actualizarRegsCompVenProd(tblRegistroFacturaRegVen, tempListVenFact, txtTotalFacturaRegVen);
    }//GEN-LAST:event_btnEditarRegComFacturaRegVenActionPerformed

    private void removerProdsVendidos(ArrayList<Producto> listaProductos) {
        for (Producto prod : listaProductos) {
            System.out.println(prod.getID());
            baseDeDatos.eliminarProducto(prod.getID());
        }
    }

    private void btnGuardarRegComFacturaRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegComFacturaRegVenActionPerformed

        int serie = Integer.parseInt(txtSerieFacturaRegVen.getText());
        int numero = Integer.parseInt(txtNumeroFacturaRegVen.getText());
        String cliente = txtClienteFacturaRegVen.getText();
        double total = Double.parseDouble(txtTotalFacturaRegVen.getText());
        String fechaRegistro = txtFechaFacturaRegVen.getText();
        //crearComprobanteVenta(String fechaRegistro, String tipoComprobante, int serie, int numero, String cliente, double total)
        baseDeDatos.crearComprobanteVenta(fechaRegistro, "Factura", serie, numero, cliente, total);
        removerProdsVendidos(tempListVenFact);
        comprobanteRegistrado();
    }//GEN-LAST:event_btnGuardarRegComFacturaRegVenActionPerformed

    private void actualizarComboBoxAgregVen() {
        cbTipoProductosRegVen.removeAllItems();
        ArrayList<Producto> lista = baseDeDatos.obtenerProductos();
        for (Producto prod : lista) {
            cbTipoProductosRegVen.addItem(prod.getProducto());
        }

    }

    private void btnAgregarRegComFacturaRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRegComFacturaRegVenActionPerformed
        tblPadre = tblRegistroFacturaRegVen;
        pnlPadreComVenProd = pnlVenFactura;
        actualizarComboBoxAgregVen();
        tpnMostrar.setSelectedIndex(pnlAgrVenProd);
    }//GEN-LAST:event_btnAgregarRegComFacturaRegVenActionPerformed

    private void btnEliminarRegComFacturaRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRegComFacturaRegVenActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistroFacturaRegVen);
        if (filaSeleccion == null) {
            return;
        }
        btnElimAccion(tblRegistroFacturaRegVen, filaSeleccion, tempListVenFact, txtTotalFacturaRegVen);
    }//GEN-LAST:event_btnEliminarRegComFacturaRegVenActionPerformed

    private void btnPDFComprobantesEmitidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFComprobantesEmitidosActionPerformed
        int filaSeleccionada = tblRegistrodeComprobantesEmitidos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor selecciona un comprobante");
            return;
        }

        String comprobanteid = tblRegistrodeComprobantesEmitidos.getValueAt(filaSeleccionada, 1).toString();

        GeneradorPDF pdf = new GeneradorPDF();
        pdf.generarPDF(comprobanteid);
    }//GEN-LAST:event_btnPDFComprobantesEmitidosActionPerformed

    private void btnEditarBoletaRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarBoletaRegVenActionPerformed
        Integer filaSeleccion = getSeleccionEditar(tblRegistroBoletaRegVen);
        if (filaSeleccion == null) {
            return;
        }
        if (GestorModelos.tieneCeldasVacias(tblRegistroBoletaRegVen, filaSeleccion)) {
            JOptionPane.showMessageDialog(this, "Faltan datos en la fila");
            return;
        }
        if (!tryBtnEditarAccion("Boleta", tblRegistroBoletaRegVen, filaSeleccion, tempListVenBole)) {
            return;
        }
        GestorModelos.actualizarRegsCompVenProd(tblRegistroBoletaRegVen, tempListVenBole, txtTotalBoletaRegVen);
    }//GEN-LAST:event_btnEditarBoletaRegVenActionPerformed

    private void btnGuardarBoletaRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarBoletaRegVenActionPerformed

        int serie = Integer.parseInt(txtSerieBoletaRegVen.getText());
        int numero = Integer.parseInt(txtNumeroBoletaRegVen.getText());
        String cliente = txtClienteBoletaRegVen.getText();
        double total = Double.parseDouble(txtTotalBoletaRegVen.getText());
        String fechaRegistro = txtFechaBoletaRegVen.getText();
        baseDeDatos.crearComprobanteVenta(fechaRegistro, "Factura", serie, numero, cliente, total);
        removerProdsVendidos(tempListVenBole);
        comprobanteRegistrado();
    }//GEN-LAST:event_btnGuardarBoletaRegVenActionPerformed

    private void btnAgregarBoletaRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarBoletaRegVenActionPerformed
        tblPadre = tblRegistroBoletaRegVen;
        pnlPadreComVenProd = pnlVenBoleta;
        actualizarComboBoxAgregVen();
        tpnMostrar.setSelectedIndex(pnlAgrVenProd);
    }//GEN-LAST:event_btnAgregarBoletaRegVenActionPerformed

    private void btnEliminarBoletaRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarBoletaRegVenActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistroBoletaRegVen);
        if (filaSeleccion == null) {
            return;
        }
        btnElimAccion(tblRegistroBoletaRegVen, filaSeleccion, tempListVenBole, txtTotalBoletaRegVen);
    }//GEN-LAST:event_btnEliminarBoletaRegVenActionPerformed

    private void btnDescargarRegistrodeVentasReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarRegistrodeVentasReportesActionPerformed

    String tipoSucursal = cbTipoSurcusalesReportes.getSelectedItem().toString();
    String periodo = txtPeriodoReportes.getText().trim();
    String rangoPeriodo = txtRangodeperiodoReportes.getText().trim();

    Excel.ExportadorExcel exportador = new Excel.ExportadorExcel();
    exportador.exportarRegistroDeVentas(tipoSucursal, periodo, rangoPeriodo);
    }//GEN-LAST:event_btnDescargarRegistrodeVentasReportesActionPerformed

    private void btnGuardacambiosAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardacambiosAjustesActionPerformed

    }//GEN-LAST:event_btnGuardacambiosAjustesActionPerformed

    private void jmFACTURASRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFACTURASRegVenActionPerformed
        tpnMostrar.setSelectedIndex(pnlVenFactura);
    }//GEN-LAST:event_jmFACTURASRegVenActionPerformed

    private void jmBOLETASRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmBOLETASRegVenActionPerformed
        tpnMostrar.setSelectedIndex(pnlVenBoleta);
    }//GEN-LAST:event_jmBOLETASRegVenActionPerformed

    private void jmCOMPROBATESEMITIDOSRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCOMPROBATESEMITIDOSRegVenActionPerformed
        GestorModelos.actualizarCompVenEmit(tblRegistrodeComprobantesEmitidosRegVen, baseDeDatos.obtenerComprobantesVenta());
        tpnMostrar.setSelectedIndex(pnlVenCompEmit);
    }//GEN-LAST:event_jmCOMPROBATESEMITIDOSRegVenActionPerformed

    private void cbTipoProductosRegVenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoProductosRegVenItemStateChanged
        int seleccionado = cbTipoProductosRegVen.getSelectedIndex();
        if (seleccionado == -1) {
            return;
        }
        Producto prodSeleccionado = baseDeDatos.buscarProducto(cbTipoProductosRegVen.getItemAt(seleccionado));
        txtCantidadRegVen.setText(Integer.toString(prodSeleccionado.getCantidad()));
        txtPreciioUnitarioRegVen.setText(Double.toString(prodSeleccionado.getPrecioUnitario()));

    }//GEN-LAST:event_cbTipoProductosRegVenItemStateChanged

    private void btnBuscarComprobantesEmitidosRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarComprobantesEmitidosRegVenActionPerformed
        //revisar que no este en blanco
        if (faltanDatosEnTxt(txtBuscarComprobantesEmitidosRegVen) && !txtBuscarComprobantesEmitidosRegVen.getText().equals(" ") || btnBuscarComprobantesEmitidosRegVen.getText().equals("Revertir")) {
            GestorModelos.actualizarCompVenEmit(tblRegistrodeComprobantesEmitidosRegVen, baseDeDatos.obtenerComprobantesVenta());
            btnBuscarComprobantesEmitidosRegVen.setText("Buscar");
            txtBuscarComprobantesEmitidosRegVen.setEnabled(true);
            return;
        }
        int indice = 0;
        String seleccion = cbTipodeBusquedaComprobantesEmitidosRegVen.getSelectedItem().toString();
        switch (cbTipodeBusquedaComprobantesEmitidosRegVen.getSelectedIndex()) {
            case 0:
                indice = 5;
                break;
            case 1:
                indice = 4;
                break;
            case 2:
                indice = 6;
                break;
            default:
                throw new AssertionError();
        }
        GestorModelos.buscarCompEmit(tblRegistrodeComprobantesEmitidosRegVen, txtBuscarComprobantesEmitidosRegVen.getText(), indice);
        btnBuscarComprobantesEmitidosRegVen.setText("Revertir");
        txtBuscarComprobantesEmitidosRegVen.setEnabled(false);
    }//GEN-LAST:event_btnBuscarComprobantesEmitidosRegVenActionPerformed

    private void btnEditarRegVenProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarRegVenProformaActionPerformed
        Integer filaSeleccion = getSeleccionEditar(tblRegistroProformaRegVen);
        if (filaSeleccion == null) {
            return;
        }
        if (GestorModelos.tieneCeldasVacias(tblRegistroProformaRegVen, filaSeleccion)) {
            JOptionPane.showMessageDialog(this, "Faltan datos en la fila");
            return;
        }
        if (!tryBtnEditarAccion("Proforma", tblRegistroProformaRegVen, filaSeleccion, tempListVenProf)) {
            return;
        }
        GestorModelos.actualizarRegsCompVenProd(tblRegistroProformaRegVen, tempListVenProf, txtTotalProformaRegVen);
    }//GEN-LAST:event_btnEditarRegVenProformaActionPerformed

    private void btnGuardarRegVenProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegVenProformaActionPerformed
        String cliente = txtNombresProformaRegVen.getText();
        double total = Double.parseDouble(txtTotalProformaRegVen.getText());
        String fechaRegistro = txtFechaProformaRegVen.getText();
        //crearComprobanteVenta(String fechaRegistro, String tipoComprobante, int serie, int numero, String cliente, double total)
        baseDeDatos.crearComprobanteVenta(fechaRegistro, "Proforma", 0, 0, cliente, total);
        removerProdsVendidos(tempListVenProf);
        comprobanteRegistrado();
    }//GEN-LAST:event_btnGuardarRegVenProformaActionPerformed

    private void btnAgregarRegVenProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRegVenProformaActionPerformed
        tblPadre = tblRegistroProformaRegVen;
        pnlPadreComVenProd = iPnlVenProforma;
        actualizarComboBoxAgregVen();
        tpnMostrar.setSelectedIndex(pnlAgrVenProd);
    }//GEN-LAST:event_btnAgregarRegVenProformaActionPerformed

    private void btnEliminarRegVenProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRegVenProformaActionPerformed
        if (cancelarEliminacion()) {
            return;
        }
        Integer filaSeleccion = getSeleccionEliminar(tblRegistroProformaRegVen);
        if (filaSeleccion == null) {
            return;
        }
        btnElimAccion(tblRegistroProformaRegVen, filaSeleccion, tempListVenProf, txtTotalProformaRegVen);
    }//GEN-LAST:event_btnEliminarRegVenProformaActionPerformed

    private void jmOTROSRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmOTROSRegVenActionPerformed
        tpnMostrar.setSelectedIndex(iPnlVenProforma);
    }//GEN-LAST:event_jmOTROSRegVenActionPerformed

    private void cargarResumenCaja() {
        try{
        double ingresos = baseDeDatos.obtenerTotalCompEmitCompra();
        double egresos = baseDeDatos.obtenerTotalCompEmitVenta();
        double ganancia = ingresos - egresos;

        txtINGRESOScaja.setText(String.format("%.2f", ingresos));
        txtEGRESOScaja.setText(String.format("%.2f", egresos));
        txtGANACIAScaja.setText(String.format("%.2f", ganancia));
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo obtener los datos de la base de datos.");
        }
    }

    private void txtINGRESOScajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtINGRESOScajaActionPerformed

        cargarResumenCaja();
    }//GEN-LAST:event_txtINGRESOScajaActionPerformed

    private void txtEGRESOScajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEGRESOScajaActionPerformed

        cargarResumenCaja();
    }//GEN-LAST:event_txtEGRESOScajaActionPerformed

    private void txtGANACIAScajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGANACIAScajaActionPerformed

        cargarResumenCaja();
    }//GEN-LAST:event_txtGANACIAScajaActionPerformed

    private void btn_Grafico_CajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Grafico_CajaActionPerformed

        cargarResumenCaja();
        double ingresos = Double.parseDouble(txtINGRESOScaja.getText());
        double egresos = Double.parseDouble(txtEGRESOScaja.getText());
        double ganancias = Double.parseDouble(txtGANACIAScaja.getText());

        DefaultCategoryDataset datasetCaja = new DefaultCategoryDataset();

        datasetCaja.setValue(ingresos, "Ingresos", "Total");
        datasetCaja.setValue(egresos, "Egresos", "Total");
        datasetCaja.setValue(ganancias, "Ganancias", "Total");

        JFreeChart chart = ChartFactory.createBarChart3D(
                "Resumen de Caja",
                "Concepto",
                "Monto (S/.)",
                datasetCaja,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(450, 250));

        Grafico_Caja.removeAll();
        Grafico_Caja.setLayout(new BorderLayout());
        Grafico_Caja.add(panel, BorderLayout.CENTER);
        Grafico_Caja.revalidate();
        Grafico_Caja.repaint();
        
        //pack() resetea el tamaño del form y debido a que el form tiene null layout me parece que por eso se-
        //esconde todo
        //pack();

    }//GEN-LAST:event_btn_Grafico_CajaActionPerformed
    private void cbLenguajeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLenguajeItemStateChanged
        int selec=cbLenguaje.getSelectedIndex();
        if(selec==-1){
            return;
        }
        switch(selec){
            case 0:
                baseDeDatos.actualizarAjustes("español","claro");
                Ajuste.Ajustes.cambiarAEspanol(null, this);
                break;
            case 1:
                baseDeDatos.actualizarAjustes("ingles","claro");
                Ajuste.Ajustes.cambiarAIngles(null, this);
                break;
        }
        
    }//GEN-LAST:event_cbLenguajeItemStateChanged

    private void btnPDFComprobantesEmitidosRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFComprobantesEmitidosRegVenActionPerformed
        int filaSeleccionada = tblRegistrodeComprobantesEmitidosRegVen.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor selecciona un comprobante");
            return;
        }

        String comprobanteid = tblRegistrodeComprobantesEmitidosRegVen.getValueAt(filaSeleccionada, 1).toString();

        GeneradorPDF pdf = new GeneradorPDF();
        pdf.generarPDFVenta(comprobanteid);
    }//GEN-LAST:event_btnPDFComprobantesEmitidosRegVenActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMenuDinamico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenuDinamico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenuDinamico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenuDinamico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenuDinamico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Grafico_Caja;
    private javax.swing.JPopupMenu RegistroDeCompra;
    private javax.swing.JPopupMenu RegistroDeVenta;
    private javax.swing.JButton btnAgregarAlmacen;
    private javax.swing.JButton btnAgregarBoletaRegVen;
    private javax.swing.JButton btnAgregarProductoRegCom;
    private javax.swing.JButton btnAgregarProductoRegVen;
    private javax.swing.JButton btnAgregarRegComBoleta;
    private javax.swing.JButton btnAgregarRegComFactura;
    private javax.swing.JButton btnAgregarRegComFacturaRegVen;
    private javax.swing.JButton btnAgregarRegComProforma;
    private javax.swing.JButton btnAgregarRegVenProforma;
    private javax.swing.JLabel btnAlmacen;
    private javax.swing.JButton btnAnularComprobantesEmitidos;
    private javax.swing.JButton btnAnularComprobantesEmitidosRegVen;
    private javax.swing.JButton btnBuscarAlmacen;
    private javax.swing.JButton btnBuscarComprobantesEmitidos;
    private javax.swing.JButton btnBuscarComprobantesEmitidosRegVen;
    private javax.swing.JToggleButton btnBusquedacaja;
    private javax.swing.JButton btnCancelarRegCom;
    private javax.swing.JButton btnCancelarRegVen;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnDescargarRegistrodeVentasReportes;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarAlmacen;
    private javax.swing.JButton btnEditarBoletaRegVen;
    private javax.swing.JButton btnEditarRegComBoleta;
    private javax.swing.JButton btnEditarRegComFactura;
    private javax.swing.JButton btnEditarRegComFacturaRegVen;
    private javax.swing.JButton btnEditarRegComProforma;
    private javax.swing.JButton btnEditarRegVenProforma;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarAlmacen;
    private javax.swing.JButton btnEliminarBoletaRegVen;
    private javax.swing.JButton btnEliminarRegComBoleta;
    private javax.swing.JButton btnEliminarRegComFactura;
    private javax.swing.JButton btnEliminarRegComFacturaRegVen;
    private javax.swing.JButton btnEliminarRegComProforma;
    private javax.swing.JButton btnEliminarRegVenProforma;
    private javax.swing.JButton btnFiltrarAlmacen;
    private javax.swing.JButton btnGuardacambiosAjustes;
    private javax.swing.JButton btnGuardarBoletaRegVen;
    private javax.swing.JButton btnGuardarRegComBoleta;
    private javax.swing.JButton btnGuardarRegComFactura;
    private javax.swing.JButton btnGuardarRegComFacturaRegVen;
    private javax.swing.JButton btnGuardarRegComProforma;
    private javax.swing.JButton btnGuardarRegVenProforma;
    private javax.swing.JButton btnGuardaryAgregarDatos;
    private javax.swing.JButton btnPDFComprobantesEmitidos;
    private javax.swing.JButton btnPDFComprobantesEmitidosRegVen;
    private javax.swing.JButton btn_Grafico_Caja;
    private javax.swing.JLabel btnajustes;
    private javax.swing.JLabel btncaja;
    private javax.swing.JLabel btncerrarsesion;
    private javax.swing.JLabel btninicio;
    private javax.swing.JLabel btnregistrodecompras;
    private javax.swing.JLabel btnregistrodeusuario;
    private javax.swing.JLabel btnregistrodeventas;
    private javax.swing.JLabel btnreportes;
    private javax.swing.JComboBox<String> cbLenguaje;
    private javax.swing.JComboBox<String> cbTipoAspectoAjustes;
    private javax.swing.JComboBox<String> cbTipoDeDineroBoleta;
    private javax.swing.JComboBox<String> cbTipoDeDineroBoletaRegVen;
    private javax.swing.JComboBox<String> cbTipoDeDineroFactura;
    private javax.swing.JComboBox<String> cbTipoDeDineroFacturaRegVen;
    private javax.swing.JComboBox<String> cbTipoDeDineroProforma;
    private javax.swing.JComboBox<String> cbTipoDeDineroProformaRegVen;
    private javax.swing.JComboBox<String> cbTipoDeLiderBoleta;
    private javax.swing.JComboBox<String> cbTipoDeLiderFactura;
    private javax.swing.JComboBox<String> cbTipoDeLiderProforma;
    private javax.swing.JComboBox<String> cbTipoOPBoletaRegVen;
    private javax.swing.JComboBox<String> cbTipoProductosRegVen;
    private javax.swing.JComboBox<String> cbTipoStock;
    private javax.swing.JComboBox<String> cbTipoSurcusalesReportes;
    private javax.swing.JComboBox<String> cbTipoUsuario;
    private javax.swing.JComboBox<String> cbTipodeBusquedaComprobantesEmitidos;
    private javax.swing.JComboBox<String> cbTipodeBusquedaComprobantesEmitidosRegVen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JMenuItem jmBOLETASRegCom;
    private javax.swing.JMenuItem jmBOLETASRegVen;
    private javax.swing.JMenuItem jmCOMPROBATESEMITIDOSRegCom;
    private javax.swing.JMenuItem jmCOMPROBATESEMITIDOSRegVen;
    private javax.swing.JMenuItem jmFACTURASRegCom;
    private javax.swing.JMenuItem jmFACTURASRegVen;
    private javax.swing.JMenuItem jmOTROSRegCom;
    private javax.swing.JMenuItem jmOTROSRegVen;
    private javax.swing.JPanel jpaneladmin;
    private javax.swing.JPanel jpanelgeneral;
    private javax.swing.JPanel jpanelsuperior;
    private javax.swing.JLabel lblAgregamosProductosRegVen;
    private javax.swing.JLabel lblAgregaryCrearUsuario;
    private javax.swing.JLabel lblApectoAjustes;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblCantidadRegCom;
    private javax.swing.JLabel lblCantidadRegVen;
    private javax.swing.JLabel lblCantidaddeProducto;
    private javax.swing.JLabel lblClienteBoletaRegVen;
    private javax.swing.JLabel lblClienteFacturaRegVen;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblCompraDelMes;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblCorreodeEmpresaAjustes;
    private javax.swing.JLabel lblDNIUsuario;
    private javax.swing.JLabel lblDatosAjustes;
    private javax.swing.JLabel lblEGRESOScaja;
    private javax.swing.JTextField lblEgresoscaja;
    private javax.swing.JLabel lblFechaBoleta;
    private javax.swing.JLabel lblFechaBoletaRegVen;
    private javax.swing.JLabel lblFechaFactura;
    private javax.swing.JLabel lblFechaFacturaRegVen;
    private javax.swing.JLabel lblFechaProforma;
    private javax.swing.JLabel lblFechaProformaRegVen;
    private javax.swing.JLabel lblGanancias;
    private javax.swing.JLabel lblGananciascaja;
    private javax.swing.JLabel lblGestionarperfilAjustes;
    private javax.swing.JTextField lblHistorialdeMovimientoscaja;
    private javax.swing.JLabel lblIDIOMAAjustes;
    private javax.swing.JLabel lblINGRESOScaja;
    private javax.swing.JLabel lblImagen1;
    private javax.swing.JLabel lblImagen1Ajustes;
    private javax.swing.JLabel lblImagen1BoletaRegVen;
    private javax.swing.JLabel lblImagen1FacturaRegVen;
    private javax.swing.JLabel lblImagen2BoletaRegVen;
    private javax.swing.JLabel lblImagen2FacturaRegVen;
    private javax.swing.JTextField lblIngresoscaja;
    private javax.swing.JLabel lblLogoAjustes;
    private javax.swing.JLabel lblMonedaBoleta;
    private javax.swing.JLabel lblMonedaBoletaRegVen;
    private javax.swing.JLabel lblMonedaFactura;
    private javax.swing.JLabel lblMonedaFacturaRegVen;
    private javax.swing.JLabel lblMonedaProforma;
    private javax.swing.JLabel lblMonedaProformaRegVen;
    private javax.swing.JLabel lblNombreAjustes;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNombresProforma;
    private javax.swing.JLabel lblNombresProformaRegVen;
    private javax.swing.JLabel lblNumeroBoleta;
    private javax.swing.JLabel lblNumeroBoletaRegVen;
    private javax.swing.JLabel lblNumeroFactura;
    private javax.swing.JLabel lblNumeroFacturaRegVen;
    private javax.swing.JLabel lblOPBoletaRegVen;
    private javax.swing.JLabel lblPeriodoReportes;
    private javax.swing.JLabel lblPreciioUnitarioRegCom;
    private javax.swing.JLabel lblPreciioUnitarioRegVen;
    private javax.swing.JLabel lblPreciodeProducto;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProductoRegCom;
    private javax.swing.JLabel lblProductoRegVen;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblProveedorBoleta;
    private javax.swing.JLabel lblProveedorFactura;
    private javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblREPORTEREGISTRODEVENTASReportes;
    private javax.swing.JLabel lblRUC;
    private javax.swing.JLabel lblRUCAjustes;
    private javax.swing.JLabel lblRangodeperiodoReportes;
    private javax.swing.JLabel lblRegCompras;
    private javax.swing.JLabel lblRegVentas;
    private javax.swing.JLabel lblRegistrodeProductosRegCom;
    private javax.swing.JLabel lblResponsableBoleta;
    private javax.swing.JLabel lblResponsableFactura;
    private javax.swing.JLabel lblResponsableProforma;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblRucFacturaRegVen;
    private javax.swing.JLabel lblSerieBoleta;
    private javax.swing.JLabel lblSerieBoletaRegVen;
    private javax.swing.JLabel lblSerieFactura;
    private javax.swing.JLabel lblSerieFacturaRegVen;
    private javax.swing.JLabel lblSubTitulo;
    private javax.swing.JLabel lblSubTitulo1;
    private javax.swing.JLabel lblSubtema2;
    private javax.swing.JLabel lblSubtemaAgregarProductoRegCom;
    private javax.swing.JLabel lblSubtemaAgregarProductoRegVen;
    private javax.swing.JLabel lblSubtemaComprobantesEmitidos;
    private javax.swing.JLabel lblSubtemaComprobantesEmitidosRegVen;
    private javax.swing.JLabel lblSurcusalesReportes;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefonoAjustes;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblVentasDelDia;
    private javax.swing.JLabel lblimagen1;
    private javax.swing.JLabel lblimagen1ComprobantesEmitidos;
    private javax.swing.JLabel lblimagen1ComprobantesEmitidosRegVen;
    private javax.swing.JLabel lblimagen1caja;
    private javax.swing.JLabel lblimagen1factura;
    private javax.swing.JLabel lblimagen1factura1;
    private javax.swing.JLabel lblimagen2;
    private javax.swing.JLabel lblimagen2factura;
    private javax.swing.JLabel lblimagen2factura1;
    private javax.swing.JLabel lblinventario;
    private javax.swing.JLabel lblsubtemaBoleta;
    private javax.swing.JLabel lblsubtemaBoletaRegVen;
    private javax.swing.JLabel lblsubtemaFactura;
    private javax.swing.JLabel lblsubtemaFacturaRegVen;
    private javax.swing.JLabel lblsubtemaProforma;
    private javax.swing.JLabel lblsubtemaProformaRegVen;
    private javax.swing.JPanel pnl1Almacen;
    private javax.swing.JPanel pnl1Inicio;
    private javax.swing.JPanel pnlAjustes;
    private javax.swing.JPanel pnlCaja;
    private javax.swing.JPanel pnlCientes;
    private javax.swing.JPanel pnlCompraDelMes;
    private javax.swing.JPanel pnlGancias;
    private javax.swing.JPanel pnlInternRegComFactura;
    private javax.swing.JPanel pnlInternRegComFacturaRegVen;
    private javax.swing.JPanel pnlInternoBoletaRegVen;
    private javax.swing.JPanel pnlInternoRegComBoleta;
    private javax.swing.JPanel pnlInternoRegComProforma;
    private javax.swing.JPanel pnlInternoRegVenProforma;
    private javax.swing.JPanel pnlInternodecaja;
    private javax.swing.JPanel pnlProductos;
    private javax.swing.JPanel pnlProveedores;
    private javax.swing.JPanel pnlRegComAgregarProducto;
    private javax.swing.JPanel pnlRegComBoleta;
    private javax.swing.JPanel pnlRegComCompromantesEmitidos;
    private javax.swing.JPanel pnlRegComFactura;
    private javax.swing.JPanel pnlRegComProforma;
    private javax.swing.JPanel pnlRegUsuIngresar;
    private javax.swing.JPanel pnlRegUsuMostrar;
    private javax.swing.JPanel pnlRegVenAgregarProducto;
    private javax.swing.JPanel pnlRegVenBoleta;
    private javax.swing.JPanel pnlRegVenCompromantesEmitidos;
    private javax.swing.JPanel pnlRegVenFactura;
    private javax.swing.JPanel pnlRegVenProform;
    private javax.swing.JPanel pnlReportes;
    private javax.swing.JPanel pnlVentasDelDia;
    private javax.swing.JRadioButton rbtActivo;
    private javax.swing.JRadioButton rbtInactivo;
    private javax.swing.JTable tblRegistroBoleta;
    private javax.swing.JTable tblRegistroBoletaRegVen;
    private javax.swing.JTable tblRegistroFactura;
    private javax.swing.JTable tblRegistroFacturaRegVen;
    private javax.swing.JTable tblRegistroProductos;
    private javax.swing.JTable tblRegistroProforma;
    private javax.swing.JTable tblRegistroProformaRegVen;
    private javax.swing.JTable tblRegistroUsuarios;
    private javax.swing.JTable tblRegistrodeCaja;
    private javax.swing.JTable tblRegistrodeComprobantesEmitidos;
    private javax.swing.JTable tblRegistrodeComprobantesEmitidosRegVen;
    private javax.swing.JTabbedPane tpnMostrar;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarComprobantesEmitidos;
    private javax.swing.JTextField txtBuscarComprobantesEmitidosRegVen;
    private javax.swing.JTextField txtBusquedacaja;
    private javax.swing.JTextField txtCantidadRegCom;
    private javax.swing.JTextField txtCantidadRegVen;
    private javax.swing.JTextField txtCantidaddeProducto;
    private javax.swing.JTextField txtClienteBoletaRegVen;
    private javax.swing.JTextField txtClienteFacturaRegVen;
    private javax.swing.JTextField txtClientes;
    private javax.swing.JTextField txtComprasDelMes;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtCorreodeEmpresaAjustes;
    private javax.swing.JTextField txtDNIUsuario;
    private javax.swing.JTextField txtDecoracion1Boleta;
    private javax.swing.JTextField txtDecoracion1BoletaRegVen;
    private javax.swing.JTextField txtDecoracion1Factura;
    private javax.swing.JTextField txtDecoracion1FacturaRegVen;
    private javax.swing.JTextField txtDecoracion1Proforma;
    private javax.swing.JTextField txtDecoracion1ProformaRegVen;
    private javax.swing.JTextField txtDecoracion2Boleta;
    private javax.swing.JTextField txtDecoracion2BoletaRegVen;
    private javax.swing.JTextField txtDecoracion2Factura;
    private javax.swing.JTextField txtDecoracion2FacturaRegVen;
    private javax.swing.JTextField txtDecoracion2Proforma;
    private javax.swing.JTextField txtDetalledeVentasRegVen;
    private javax.swing.JTextField txtEGRESOScaja;
    private javax.swing.JTextField txtFechaBoleta;
    private javax.swing.JTextField txtFechaBoletaRegVen;
    private javax.swing.JTextField txtFechaFactura;
    private javax.swing.JTextField txtFechaFacturaRegVen;
    private javax.swing.JTextField txtFechaProforma;
    private javax.swing.JTextField txtFechaProformaRegVen;
    private javax.swing.JTextField txtGANACIAScaja;
    private javax.swing.JTextField txtGanancias;
    private javax.swing.JTextField txtINGRESOScaja;
    private javax.swing.JTextField txtNombreAjustes;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombresProforma;
    private javax.swing.JTextField txtNombresProformaRegVen;
    private javax.swing.JTextField txtNumeroBoleta;
    private javax.swing.JTextField txtNumeroBoletaRegVen;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtNumeroFacturaRegVen;
    private javax.swing.JTextField txtPeriodoReportes;
    private javax.swing.JTextField txtPreciioUnitarioRegCom;
    private javax.swing.JTextField txtPreciioUnitarioRegVen;
    private javax.swing.JTextField txtPreciodeCompra;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtProductoRegCom;
    private javax.swing.JTextField txtProductos;
    private javax.swing.JTextField txtProveedorBoleta;
    private javax.swing.JTextField txtProveedorFactura;
    private javax.swing.JTextField txtProveedores;
    private javax.swing.JTextField txtRUCAjustes;
    private javax.swing.JTextField txtRangodeperiodoReportes;
    private javax.swing.JTextField txtRucFacturaRegVen;
    private javax.swing.JTextField txtSerieBoleta;
    private javax.swing.JTextField txtSerieBoletaRegVen;
    private javax.swing.JTextField txtSerieFactura;
    private javax.swing.JTextField txtSerieFacturaRegVen;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoAjustes;
    private javax.swing.JTextField txtTotalBoleta;
    private javax.swing.JTextField txtTotalBoletaRegVen;
    private javax.swing.JTextField txtTotalFactura;
    private javax.swing.JTextField txtTotalFacturaRegVen;
    private javax.swing.JTextField txtTotalProforma;
    private javax.swing.JTextField txtTotalProformaRegVen;
    private javax.swing.JTextField txtVentasDelDia;
    // End of variables declaration//GEN-END:variables
}
