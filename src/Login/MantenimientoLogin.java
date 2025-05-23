package Login;

import java.time.LocalDate;
import java.util.ArrayList;

public class MantenimientoLogin {

    public static Boolean esDniORucValido(String usuario) {
        return usuario.matches("\\d{8}") || usuario.matches("\\d{11}");
    }

    public static boolean esContraseñaValida(String contraseña) {
        Boolean esLargo = contraseña.length() > 4;
        return contraseña.matches("[A-Za-z0-9]+") && esLargo;
    }
}
