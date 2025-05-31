/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestorDatosPermanentes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class ArchivoUtil {
    
    // Método estático para leer el archivo y retornar las líneas como una lista
    public static ArrayList<String> leerArchivo(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try {
            lineas = new ArrayList<>(Files.readAllLines(Paths.get(nombreArchivo)));
        } catch (IOException e) {
            // Puedes manejar el error aquí si es necesario
        }
        return lineas;
    }

    public static void escribirArchivo(String ruta, String linea, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, append))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            // Ignorar errores para evitar mensajes emergentes
        }
    }
}
