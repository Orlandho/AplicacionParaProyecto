
package Excel;

import GestorDatosPermanentes.SQLiteManager;
import DocumentoComercial.ComprobanteVenta;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ExportadorExcel {
    private SQLiteManager gestor;

    public ExportadorExcel() {
        gestor = new SQLiteManager();
    }

    public void exportarRegistroDeVentas(String tipoSucursal, String periodo, String rangoPeriodo) {
        ArrayList<ComprobanteVenta> comprobantes = gestor.obtenerComprobantesVenta();

        String nombreArchivo = "Registro_" + tipoSucursal + "_" + periodo + ".xlsx";
        String rutaEscritorio = Paths.get(System.getProperty("user.home"), "Desktop", nombreArchivo).toString();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Ventas");

            // Cabecera
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Fecha", "Tipo", "Serie", "Número", "Cliente", "Total"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Filas de datos
            int rowNum = 1;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (ComprobanteVenta cv : comprobantes) {
                // Si deseas puedes filtrar por tipoSucursal o fecha aquí
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(cv.getId());
                row.createCell(1).setCellValue(cv.getFechaRegistro().format(formatter));
                row.createCell(2).setCellValue(cv.getTipoComprobante());
                row.createCell(3).setCellValue(cv.getSerie());
                row.createCell(4).setCellValue(cv.getNumero());
                row.createCell(5).setCellValue(cv.getCliente());
                row.createCell(6).setCellValue(cv.getTotal());
            }

            FileOutputStream fileOut = new FileOutputStream(rutaEscritorio);
            workbook.write(fileOut);
            fileOut.close();

            gestor.cerrarConexion();

            javax.swing.JOptionPane.showMessageDialog(null, "Archivo Excel generado:\n" + rutaEscritorio);

        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al generar Excel: " + e.getMessage());
        }
    }
}
