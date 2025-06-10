/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiciosUsuario;
import ServiciosUsuario.VerificadorUsuario;

/**
 *
 * @author ORLANDO
 */
public class ConvertidorUsuario {
    public static Integer tryParseTelefono(String telefono){
        int resultado;
        try{
        resultado=Integer.parseInt(telefono);
        }catch(NumberFormatException e){
            return null;
        }
        return VerificadorUsuario.esTelefonoValido(resultado)?resultado:null;
    }
    
    public static Integer tryParseUsuarioDNI(String usuarioDNI){
        int resultado;
        try{
        resultado=Integer.parseInt(usuarioDNI);
        }catch(NumberFormatException e){
            return null;
        }
        return VerificadorUsuario.esDniORucValido(usuarioDNI)?resultado:null;
    }
    
    public static String parseEsCuentaBloqueada(boolean esCuentaBloqueada){
        return esCuentaBloqueada?"Inactivo":"Activo";
    }
}
