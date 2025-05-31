/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestorDatosPermanentes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {
    
    public static List<String> leerArchivo(String ruta) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }

    public static void escribirArchivo(String ruta, String linea, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, append))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
