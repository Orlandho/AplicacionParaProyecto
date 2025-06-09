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
        String comandoSQL = "SELECT * FROM Usuario WHERE usuario= ?";
        try {
            PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
            ingresarComando.setString(1, nombreUsuario);
            ResultSet datosObtenidos = ingresarComando.executeQuery();
            if (datosObtenidos.next()) {
                //                                   int usuario_id, S                    tring usuario,                        String contraseña,             String rol, LocalDate                                          fechaUltimoCambio,                     int intentosFallidos,                                                boolean cuentaBloqueada,                       String nombre,                     String apellido,                      String genero,                       String direccion
                return new Usuario(datosObtenidos.getInt("usuario_id"), datosObtenidos.getInt("usuarioDNI"),
                        datosObtenidos.getString("contraseña"),
                        datosObtenidos.getString("rol"),
                        LocalDate.parse(datosObtenidos.getString("fechaUltimoCambio")),
                        datosObtenidos.getInt("intentosFallidos"),
                        traducirCuentaBloqueada(datosObtenidos.getInt("cuentaBloqueada")) ,
                        datosObtenidos.getString("nombre"), datosObtenidos.getString("apellido"),
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
    private ArrayList<Persona> listaEmpleados = new ArrayList<>();
    
    public void registrarEmpleado(int dni, int telefono,String nombres, String apellidos) {
        Usuario nuevoEmpleado = new Usuario(0, dni, "", "", null, 0, false, nombres, apellidos, telefono);
        listaEmpleados.add(nuevoEmpleado);
    }
    
    //Metodo para crearCuenta a un empleado y asignar su rol
    public boolean crearCuentaEmpleado(int dni, int telefono, String nombres, String apellidos, String contraseña, String rol) {
        // Verificar si el empleado ya fue registrado
        boolean empleadoRegistrado = false;
        for (Persona empleado : listaEmpleados) {
            if (empleado.getDni() == dni && empleado.getTelefono() == telefono && empleado.getNombres().equals(nombres) && empleado.getApellidos().equals(apellidos)) {
                empleadoRegistrado = true;
                break;
            }
        }

        // Si los datos coinciden, proceder a crear la cuenta en la base de datos
        if (empleadoRegistrado) {
            String comandoSQL = "INSERT INTO Usuario (usuarioDNI, contraseña, rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada, nombre, apellido, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ingresarComando = conexionDB.prepareStatement(comandoSQL);
                // Asignamos los valores en el orden correspondiente
                ingresarComando.setInt(1, dni);
                ingresarComando.setString(2, contraseña);
                ingresarComando.setString(3, rol);
                ingresarComando.setString(4, LocalDate.now().toString());  // fechaUltimoCambio es la fecha actual
                ingresarComando.setInt(5, 0);  // intentosFallidos por defecto en 0
                ingresarComando.setInt(6, 0);  // cuentaBloqueada por defecto en 0
                ingresarComando.setString(7, nombres);
                ingresarComando.setString(8, apellidos);
                ingresarComando.setInt(9, telefono);

                int filasAfectadas = ingresarComando.executeUpdate();
                return filasAfectadas > 0; // Si se insertó correctamente, retorna true
            } catch (SQLException e) {
                throw new RuntimeException("Error al intentar crear cuenta de usuario.\nMensaje de error: " + e.getMessage());
            }
        } else {
            // Si no se encuentran datos, mostrar el mensaje de error
            JOptionPane.showMessageDialog(null, "Intento de creación de cuenta, Error: no se encuentran datos");
            return false;
        }
    }
    
    //Metodos para crear la tabla
    public void mostrarUsuarios() {
        // Crear modelo de tabla con las columnas solicitadas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Empleado");
        model.addColumn("Usuario");
        model.addColumn("Contraseña");
        model.addColumn("Rol");
        model.addColumn("Estado");

        // Establecer la consulta SQL para obtener los usuarios
        String consultaSQL = "SELECT nombres, apellidos, usuarioDNI, contraseña, rol FROM Usuario";

        try {
            // Preparar la conexión a la base de datos
            Statement stmt = conexionDB.createStatement();
            ResultSet rs = stmt.executeQuery(consultaSQL);

            // Recorrer el ResultSet para añadir los datos al modelo
            while (rs.next()) {
                String nombreCompleto = rs.getString("nombres") + " " + rs.getString("apellidos");
                int dni = rs.getInt("usuarioDNI");
                String contraseña = rs.getString("contraseña");
                String rol = rs.getString("rol");

                // Verificar el estado (Activo o Inactivo)
                String estado = verificarEstado(dni, contraseña) ? "Activo" : "Inactivo";

                // Agregar la fila con los datos del usuario
                model.addRow(new Object[] {
                    nombreCompleto, 
                    dni, 
                    contraseña, 
                    rol, 
                    estado
                });
            }

            // Crear la JTable para mostrar los datos
            JTable table = new JTable(model);

            // Mostrar la tabla en un JScrollPane (esto permite scroll si hay muchos usuarios)
            JScrollPane scrollPane = new JScrollPane(table);

            // Establecer las propiedades de la tabla (por ejemplo, tamaño)
            table.setFillsViewportHeight(true);

            // Aquí debes integrar el JScrollPane en tu formulario o JFrame
            // ejemplo: formulario.add(scrollPane);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los datos: " + e.getMessage());
        }
    }

    private boolean verificarEstado(int dni, String contraseña) {
        // Verificar si el DNI y la contraseña coinciden con un usuario activo
        String consultaSQL = "SELECT * FROM Usuario WHERE usuarioDNI = ? AND contraseña = ?";

        try (PreparedStatement stmt = conexionDB.prepareStatement(consultaSQL)) {
            stmt.setInt(1, dni);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();

            // Si existe un resultado, significa que el usuario y la contraseña son correctos
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar el estado: " + e.getMessage());
            return false;
        }
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
