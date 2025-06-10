package GestorDatosPermanentes;

import Usuario.Persona;
import Usuario.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
//librerias para SQL
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SQLiteManager {
    //comentario de prueba
    private Connection conexionDB;
    private final String nombreDB = "baseDeDatos.db";
    public static final int USUARIO_CONTRA_INCORRECTOS = 0, USUARIO_BLOQUEADO = 1, DEBE_CAMBIAR_CONTRASEÑA = 2, PUEDE_INGRESAR = 3;

    public SQLiteManager() {
        try {
            conexionDB = DriverManager.getConnection("jdbc:sqlite:" + nombreDB);
        } catch (SQLException e) {
           throw new RuntimeException("Error al intentar conectar con la base de datos "+nombreDB+"\nMensaje de error: "+e.getMessage());
        }
    }
    public void cerrarConexion()
    {
        try{
        if (conexionDB != null && !conexionDB.isClosed()) {
            conexionDB.close();
        }
        }catch(SQLException e){
            throw new RuntimeException("Error al cerrar conexion.\nMensaje de error:"+e.getMessage());
        }
    }
    private boolean actualizarUsuario(Usuario usuario) {
        String comandoSQL = "UPDATE usuario SET contraseña = ?, fechaUltimoCambio = ?, IntentosFallidos = ? WHERE usuario_id = ?";
        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setString(1, usuario.getContraseña());
            ingresarComando.setString(2, usuario.getFechaUltimoCambio().toString());
            ingresarComando.setInt(3, usuario.getIntentosFallidos());
            ingresarComando.setInt(4, usuario.getUsuario_id());
            int filasAfectadas = ingresarComando.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario.\nMensaje de error:" + e.getMessage());
        }
    }

    private boolean traducirCuentaBloqueada(int cuentaBloqueada)
    {
        return cuentaBloqueada!=0;
    }
    private int traducirCuentaBloqueada(boolean cuentaBloqueada)
    {
        return cuentaBloqueada?1:0;
    }
    
    private Usuario buscarUsuario(String nombreUsuario) {
        String comandoSQL = "SELECT * FROM Usuario WHERE usuarioDNI= ?";
        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setString(1, nombreUsuario);
            ResultSet datosObtenidos = ingresarComando.executeQuery();
            if (datosObtenidos.next()) {
                //                                            
                return new Usuario(datosObtenidos.getInt("usuario_id"), datosObtenidos.getInt("usuarioDNI"),
                        datosObtenidos.getString("contraseña"),
                        datosObtenidos.getString("rol"),
                        LocalDate.parse(datosObtenidos.getString("fechaUltimoCambio")),
                        datosObtenidos.getInt("intentosFallidos"),
                        traducirCuentaBloqueada(datosObtenidos.getInt("cuentaBloqueada")) ,
                        datosObtenidos.getString("nombres"), datosObtenidos.getString("apellidos"),
                        datosObtenidos.getInt("telefono"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario.\nMensaje de error:"+e.getMessage());
        }
        return null;
    }

    private boolean existeUsuario(String nombreUsuario) {
        return buscarUsuario(nombreUsuario)!=null;
    }

    private boolean esSuContraseña(Usuario usuarioEncontrado, String contraseña) {
        if (!usuarioEncontrado.getContraseña().equals(contraseña)) {
            usuarioEncontrado.setIntentosFallidos(usuarioEncontrado.getIntentosFallidos() + 1);
            if (usuarioEncontrado.getIntentosFallidos() >= 5) {
                usuarioEncontrado.setCuentaBloqueada(true);
            }
            actualizarUsuario(usuarioEncontrado);
            return false;
        }
        return true;

    }

    private boolean debeCambiarContraseña(Usuario usuario) {
        return (usuario.getRol().equalsIgnoreCase("administrador") && usuario.debeCambiarContraseña());
    }

    public boolean actualizarContraseña(String nombreUsuario, String antiguaContraseña, String nuevaContraseña) {
        if (existeUsuario(nombreUsuario) && esSuContraseña(buscarUsuario(nombreUsuario), antiguaContraseña) && debeCambiarContraseña(buscarUsuario(nombreUsuario))) {
            Usuario usuarioTemp=buscarUsuario(nombreUsuario);
            usuarioTemp.setContraseña(nuevaContraseña);
            actualizarUsuario(usuarioTemp);
            return true;
        }
        return false;
    }
    
    //Sprint 2
    //Metodo para registrar empleados
    private ArrayList<Usuario> listaEmpleados = new ArrayList<>();

    // Método para llenar listaEmpleados (simulación de empleados registrados previamente)
    public void agregarEmpleado(int dni, int telefono, String nombres, String apellidos) {
        Usuario empleado = new Usuario(0, dni, "", "", null, 0, false, nombres, apellidos, telefono);
        listaEmpleados.add(empleado);
    }

    // Método para crear cuenta de usuario si el empleado existe en listaEmpleados
    public void crearCuentaUsuario(int usuario_id, int dni, String nombres, String apellidos, int telefono, String contraseña, String rol, boolean cuentaBloqueada) {
        boolean empleadoExiste = false;

        // Verificar si los datos existen en listaEmpleados
        for (Usuario empleado : listaEmpleados) {
            if (empleado.getDni() == dni &&
                empleado.getTelefono() == telefono &&
                empleado.getNombres().equalsIgnoreCase(nombres) &&
                empleado.getApellidos().equalsIgnoreCase(apellidos)) {
                empleadoExiste = true;
                break;
            }
        }

        if (empleadoExiste) {
            String comandoSQL = "INSERT INTO Usuario (usuario_id, usuarioDNI, contraseña, rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada, nombres, apellidos, telefono) " +
                                "VALUES (?, ?, ?, ?, date('now'), 0, ?, ?, ?, ?)";

            try {
                PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
                ingresarComando.setInt(1, usuario_id);
                ingresarComando.setInt(2, dni);
                ingresarComando.setString(3, contraseña);
                ingresarComando.setString(4, rol);
                ingresarComando.setInt(5, cuentaBloqueada ? 1 : 0); // SQLite: booleano como 1 o 0
                ingresarComando.setString(6, nombres);
                ingresarComando.setString(7, apellidos);
                ingresarComando.setInt(8, telefono);

                int filasAfectadas = ingresarComando.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se pudo crear la cuenta.");
                }

            } catch (SQLException e) {
                throw new RuntimeException("Error al crear usuario.\nMensaje de error: " + e.getMessage());
            }

        } else {
        JOptionPane.showMessageDialog(null, "Intento de creación de cuenta, Error: no se encuentran datos");
        }
    }

    
    //Metodos para obtener usuarios para la tabla
    public ArrayList<String[]> obtenerUsuarios() {
        ArrayList<String[]> usuarios = new ArrayList<>();

        String sql = "SELECT nombres, apellidos, usuarioDNI, contraseña, rol, cuentaBloqueada FROM usuario";

        try (Statement stmt = conexionDB.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String empleado = rs.getString("nombres") + " " + rs.getString("apellidos");
                int usuarioDNI = rs.getInt("usuarioDNI");
                String usuario = String.valueOf(usuarioDNI);
                String contraseña = rs.getString("contraseña");
                String rol = rs.getString("rol");
                int cuentaBloqueada = rs.getInt("cuentaBloqueada");

                // Determinar estado según cuentaBloqueada
                String estado = (cuentaBloqueada == 1) ? "Inactivo" : "Activo";

                String[] datosUsuario = { empleado, usuario, contraseña, rol, estado };
                usuarios.add(datosUsuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
        }

        return usuarios;
    }
    /*
        Dicionario de codigos:
        0 | Usuario o contraseña incorrectos
        1 | Usuario bloqueado
        2 | Usuario tiene que cambiar su contraseña
        3 | Usuario se logueo correctamente
        4 | Usuario no existe
     */
    public ArrayList<Object> intentarLogin(String usuarioIngresado, String contraseñaIngresada) {
        ArrayList<Object> respuesta = new ArrayList<>();
        if (!existeUsuario(usuarioIngresado)) {
            respuesta.add(USUARIO_CONTRA_INCORRECTOS);
            return respuesta;
        }
        Usuario usuario = buscarUsuario(usuarioIngresado);
        if (usuario.esCuentaBloqueada()) {
            respuesta.add(USUARIO_BLOQUEADO);
            return respuesta;
        }
        if (!esSuContraseña(usuario, contraseñaIngresada)) {
            respuesta.add(USUARIO_CONTRA_INCORRECTOS);
            return respuesta;
        }else{
            usuario=buscarUsuario(usuarioIngresado);
            usuario.setIntentosFallidos(0);
            actualizarUsuario(usuario);
        }
        if (debeCambiarContraseña(usuario)) {
            respuesta.add(DEBE_CAMBIAR_CONTRASEÑA);
            return respuesta;
        }
        respuesta.add(PUEDE_INGRESAR);
        respuesta.add(usuario);
        return respuesta;
    }

}
