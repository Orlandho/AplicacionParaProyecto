package MenuDinamico;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.awt.Desktop;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class GeneradorPDF {

    Document documento = new Document();

    public void generarPDF(String comprobanteid) {

        Connection conn = null; // Inicializar a null para el bloque finally
        Document documento = new Document(); // Se crea por cada generació
        try {
            Class.forName("org.sqlite.JDBC");

            File dbFile = new File("baseDeDatos.db");
            if (!dbFile.exists()) {
                String errorMsg = "Error: No se encontró el archivo de la base de datos en: " + dbFile.getAbsolutePath()
                        + "\nAsegúrese de que 'baseDeDatos.db' esté en la misma carpeta que su archivo JAR.";
                System.err.println(errorMsg);
                JOptionPane.showMessageDialog(null, errorMsg, "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si la DB no se encuentra
            }

            String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
            conn = DriverManager.getConnection(url);

            File escritorio = new File(System.getProperty("user.home"), "Desktop");
            if (!escritorio.exists()) {
                escritorio = new File(System.getProperty("user.home"), "Escritorio");
            }

            String rutaPDF = escritorio + File.separator + "Comprobante_" + comprobanteid + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(rutaPDF));
            documento.open();

            // Añadir un título al PDF
            documento.add(new Paragraph("Detalles del Comprobante: " + comprobanteid));
            documento.add(new Paragraph(" ")); // Salto de línea

            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100); // La tabla ocupará todo el ancho de la página

            tabla.addCell("Comprobante ID");
            tabla.addCell("Fecha de Registro");
            tabla.addCell("Tipo de Comprobante");
            tabla.addCell("Serie");
            tabla.addCell("Número");
            tabla.addCell("Proveedor");
            tabla.addCell("Total");

            //Statement stmt = conn.createStatement();
            String sql = "SELECT comprobante_id, fechaRegistro, tipoComprobante, serie, numero, proveedor, total "
                    + "FROM comprobantesEmitidosCompra WHERE comprobante_id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, comprobanteid); // Asumiendo que comprobante_id es String/TEXT
               

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) { // Si se encontró un resultado
                    tabla.addCell(rs.getString("comprobante_id"));
                    tabla.addCell(rs.getString("fechaRegistro"));
                    tabla.addCell(rs.getString("tipoComprobante"));
                    tabla.addCell(rs.getString("serie"));
                    tabla.addCell(rs.getString("numero"));
                    tabla.addCell(rs.getString("proveedor"));
                    tabla.addCell(rs.getString("total"));
                } else {
                    documento.add(new Paragraph("No se encontraron datos para el comprobante ID: " + comprobanteid));
                    System.out.println("No se encontraron datos para el comprobante ID: " + comprobanteid);
                    JOptionPane.showMessageDialog(null, "No se encontraron datos para el comprobante ID: " + comprobanteid,
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            documento.add(tabla);

            System.out.println("PDF generado correctamente: " + rutaPDF);
            JOptionPane.showMessageDialog(null, "PDF generado correctamente en:\n" + rutaPDF, "PDF Generado", JOptionPane.INFORMATION_MESSAGE);

             // Opcional: Abrir el PDF automáticamente
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(rutaPDF);
                    if (myFile.exists()) {
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (IOException ex) {
                    System.err.println("Error al intentar abrir el PDF: " + ex.getMessage());
                }
            }

        } catch (ClassNotFoundException e) {
            String errorMsg = "Error: Driver JDBC de SQLite no encontrado. Asegúrese de que 'sqlite-jdbc-X.X.X.jar' esté en la ruta de clases.";
            System.err.println(errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error de Driver", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            String errorMsg = "Error SQL al generar el PDF: " + e.getMessage() + "\nPor favor, verifica la consulta SQL y los nombres de las columnas en tu base de datos.";
            System.err.println(errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            String errorMsg = "Error de E/S al generar el PDF: " + e.getMessage() + "\nVerifica los permisos de escritura en el escritorio o el espacio disponible.";
            System.err.println(errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            String errorMsg = "Ocurrió un error inesperado al generar el PDF: " + e.getMessage();
            System.err.println(errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error Inesperado", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Este bloque CRÍTICO asegura el cierre de recursos
            if (documento != null && documento.isOpen()) {
                documento.close();
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println("Error al cerrar la conexión a la base de datos: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void generarPDFVenta(String comprobanteid) {

        Connection conn = null; // Inicializar a null para el bloque finally
        Document documento = new Document(); // Se crea por cada generació
        try {
            Class.forName("org.sqlite.JDBC");

            File dbFile = new File("baseDeDatos.db");
            if (!dbFile.exists()) {
                String errorMsg = "Error: No se encontró el archivo de la base de datos en: " + dbFile.getAbsolutePath()
                        + "\nAsegúrese de que 'baseDeDatos.db' esté en la misma carpeta que su archivo JAR.";
                System.err.println(errorMsg);
                JOptionPane.showMessageDialog(null, errorMsg, "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si la DB no se encuentra
            }

            String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
            conn = DriverManager.getConnection(url);

            File escritorio = new File(System.getProperty("user.home"), "Desktop");
            if (!escritorio.exists()) {
                escritorio = new File(System.getProperty("user.home"), "Escritorio");
            }

            String rutaPDF = escritorio + File.separator + "Comprobante_" + comprobanteid + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(rutaPDF));
            documento.open();

            // Añadir un título al PDF
            documento.add(new Paragraph("Detalles del Comprobante: " + comprobanteid));
            documento.add(new Paragraph(" ")); // Salto de línea

            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100); // La tabla ocupará todo el ancho de la página

            tabla.addCell("Comprobante ID");
            tabla.addCell("Fecha de Registro");
            tabla.addCell("Tipo de Comprobante");
            tabla.addCell("Serie");
            tabla.addCell("Número");
            tabla.addCell("Cliente");
            tabla.addCell("Total");

            //Statement stmt = conn.createStatement();
            String sql = "SELECT comprobante_id, fechaRegistro, tipoComprobante, serie, numero, cliente, total "
                    + "FROM comprobantesEmitidosVentas WHERE comprobante_id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, comprobanteid); // Asumiendo que comprobante_id es String/TEXT
               

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) { // Si se encontró un resultado
                    tabla.addCell(rs.getString("comprobante_id"));
                    tabla.addCell(rs.getString("fechaRegistro"));
                    tabla.addCell(rs.getString("tipoComprobante"));
                    tabla.addCell(rs.getString("serie"));
                    tabla.addCell(rs.getString("numero"));
                    tabla.addCell(rs.getString("cliente"));
                    tabla.addCell(rs.getString("total"));
                } else {
                    documento.add(new Paragraph("No se encontraron datos para el comprobante ID: " + comprobanteid));
                    System.out.println("No se encontraron datos para el comprobante ID: " + comprobanteid);
                    JOptionPane.showMessageDialog(null, "No se encontraron datos para el comprobante ID: " + comprobanteid,
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            documento.add(tabla);

            System.out.println("PDF generado correctamente: " + rutaPDF);
            JOptionPane.showMessageDialog(null, "PDF generado correctamente en:\n" + rutaPDF, "PDF Generado", JOptionPane.INFORMATION_MESSAGE);

             // Opcional: Abrir el PDF automáticamente
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(rutaPDF);
                    if (myFile.exists()) {
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (IOException ex) {
                    System.err.println("Error al intentar abrir el PDF: " + ex.getMessage());
                }
            }

        } catch (ClassNotFoundException e) {
            String errorMsg = "Error: Driver JDBC de SQLite no encontrado. Asegúrese de que 'sqlite-jdbc-X.X.X.jar' esté en la ruta de clases.";
            System.err.println(errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error de Driver", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            String errorMsg = "Error SQL al generar el PDF: " + e.getMessage() + "\nPor favor, verifica la consulta SQL y los nombres de las columnas en tu base de datos.";
            System.err.println(errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            String errorMsg = "Error de E/S al generar el PDF: " + e.getMessage() + "\nVerifica los permisos de escritura en el escritorio o el espacio disponible.";
            System.err.println(errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            String errorMsg = "Ocurrió un error inesperado al generar el PDF: " + e.getMessage();
            System.err.println(errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error Inesperado", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Este bloque CRÍTICO asegura el cierre de recursos
            if (documento != null && documento.isOpen()) {
                documento.close();
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println("Error al cerrar la conexión a la base de datos: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
}
