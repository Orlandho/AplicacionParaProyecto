package database;

import Producto.Producto;
import user.User;
import DocumentoComercial.ComprobanteCompra;
import DocumentoComercial.ComprobanteVenta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DatabaseManager {

    private Connection dbConnection;
    private final String dbName = "baseDeDatos.db";
    public static final int INCORRECT_USER_OR_PASSWORD = 0, USER_BLOCKED = 1, MUST_CHANGE_PASSWORD = 2, CAN_LOG_IN = 3;

    public DatabaseManager() {
        try {
            dbConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database " + dbName + "\nError message: " + e.getMessage());
        }
    }

    public double getTotalPurchaseVouchers() {
        String sql = "SELECT COALESCE(SUM(total),0) FROM comprobantesEmitidosCompra";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public double getTotalSalesVouchers() {
        String sql = "SELECT COALESCE(SUM(total),0) FROM comprobantesEmitidosVentas";
        try {
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void closeConnection() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection.\nError message:" + e.getMessage());
        }
    }

    private boolean updateUserPassword(User user) {
        String sql = "UPDATE usuario SET contraseña = ?, fechaUltimoCambio = ?, IntentosFallidos = ?, cuentaBloqueada=? WHERE usuario_id = ?";
        try {
            PreparedStatement command = dbConnection.prepareStatement(sql);
            command.setString(1, user.getPassword());
            command.setString(2, user.getLastPasswordChange().toString());
            command.setInt(3, user.getFailedLoginAttempts());
            command.setInt(4, translateIsAccountLocked(user.isAccountLocked()));
            command.setInt(5, user.getUserId());
            int rowsAffected = command.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user password.\nError message:" + e.getMessage());
        }
    }

    private boolean translateIsAccountLocked(int isAccountLocked) {
        return isAccountLocked != 0;
    }

    private int translateIsAccountLocked(boolean isAccountLocked) {
        return isAccountLocked ? 1 : 0;
    }

    private User findUser(String username) {
        String sql = "SELECT * FROM Usuario WHERE usuarioDNIoRUC= ?";
        try {
            PreparedStatement command = dbConnection.prepareStatement(sql);
            command.setLong(1, Long.parseLong(username));
            ResultSet data = command.executeQuery();
            if (data.next()) {
                return new User(data.getInt("usuario_id"), data.getLong("usuarioDNIoRUC"),
                        data.getString("contraseña"),
                        data.getString("rol"),
                        LocalDate.parse(data.getString("fechaUltimoCambio")),
                        data.getInt("intentosFallidos"),
                        translateIsAccountLocked(data.getInt("cuentaBloqueada")),
                        data.getString("nombres"), data.getString("apellidos"),
                        data.getInt("telefono"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user.\nError message:" + e.getMessage());
        }
        return null;
    }

    private boolean userExists(String username) {
        return findUser(username) != null;
    }

    private boolean isCorrectPassword(User user, String enteredPassword) {
        if (!user.getPassword().equals(enteredPassword)) {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            if (user.getFailedLoginAttempts() >= 5) {
                user.setAccountLocked(true);
            }
            updateUserPassword(user);
            return false;
        }
        return true;
    }

    private boolean shouldChangePassword(User user) {
        return (user.getRole().equalsIgnoreCase("administrador") && user.shouldChangePassword());
    }

    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        if (userExists(username) && isCorrectPassword(findUser(username), oldPassword) && shouldChangePassword(findUser(username))) {
            User tempUser = findUser(username);
            tempUser.setPassword(newPassword);
            updateUserPassword(tempUser);
            return true;
        }
        return false;
    }

    public void createUserAccount(long idNumber, String names, String lastNames, int phone, String password, String role, boolean isAccountLocked) {
        String sql = "INSERT INTO Usuario ( usuarioDNIoRUC, contraseña, rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada, nombres, apellidos, telefono) "
                + "VALUES ( ?, ?, ?, date('now'), 0, ?, ?, ?, ?)";
        try {
            PreparedStatement command = dbConnection.prepareStatement(sql);
            command.setLong(1, idNumber);
            command.setString(2, password);
            command.setString(3, role);
            command.setInt(4, isAccountLocked ? 1 : 0);
            command.setString(5, names);
            command.setString(6, lastNames);
            command.setInt(7, phone);
            int rowsAffected = command.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Account created successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: could not create account.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating user.\nError message: " + e.getMessage());
        }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT usuario_id, usuarioDNIoRUC,contraseña,rol, fechaUltimoCambio, intentosFallidos, cuentaBloqueada ,nombres, apellidos ,telefono FROM usuario";
        try (Statement stmt = dbConnection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int userId = rs.getInt("usuario_id");
                long idNumber = rs.getLong("usuarioDNIoRUC");
                String password = rs.getString("contraseña");
                String role = rs.getString("rol");
                LocalDate lastPasswordChange = LocalDate.parse(rs.getString("fechaUltimoCambio"));
                int failedLoginAttempts = rs.getInt("intentosFallidos");
                boolean isAccountLocked = translateIsAccountLocked(rs.getInt("cuentaBloqueada"));
                String names = rs.getString("nombres");
                String lastNames = rs.getString("apellidos");
                int phone = rs.getInt("telefono");
                User userData = new User(userId, idNumber, password,
                        role, lastPasswordChange, failedLoginAttempts, isAccountLocked,
                        names, lastNames, phone);
                users.add(userData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error getting users: " + e.getMessage());
        }
        return users;
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM Usuario WHERE usuario_id = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No user found with the specified ID.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting user: " + e.getMessage());
        }
    }

    public void updateUser(long idNumber, String password, String role, LocalDate lastPasswordChange,
            int failedLoginAttempts, boolean isAccountLocked,
            String names, String lastNames, int phone, int userId) {
        String sql = "UPDATE Usuario SET usuarioDNIoRUC = ?, contraseña = ?, rol = ?, fechaUltimoCambio = ?, "
                + "intentosFallidos = ?, cuentaBloqueada = ?, nombres = ?, apellidos = ?, telefono = ? "
                + "WHERE usuario_id = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setLong(1, idNumber);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setString(4, lastPasswordChange.toString());
            pstmt.setInt(5, failedLoginAttempts);
            pstmt.setInt(6, translateIsAccountLocked(isAccountLocked));
            pstmt.setString(7, names);
            pstmt.setString(8, lastNames);
            pstmt.setInt(9, phone);
            pstmt.setInt(10, userId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No user found with the specified ID.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating user: " + e.getMessage());
        }
    }

    public Producto findProduct(String product) {
        String sql = "SELECT * FROM Productos WHERE producto= ?";
        try {
            PreparedStatement command = dbConnection.prepareStatement(sql);
            command.setString(1, product);
            ResultSet data = command.executeQuery();
            if (data.next()) {
                return new Producto(
                        data.getInt("producto_id"),
                        data.getString("tipoDocumento"),
                        data.getString("producto"),
                        data.getDouble("precioCompra"),
                        data.getInt("cantidad"),
                        data.getString("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding product: " + e);
        }
        return null;
    }

    public Producto findProduct(int ID) {
        String sql = "SELECT * FROM Productos WHERE producto_id= ?";
        try {
            PreparedStatement command = dbConnection.prepareStatement(sql);
            command.setInt(1, ID);
            ResultSet data = command.executeQuery();
            if (data.next()) {
                return new Producto(
                        data.getInt("producto_id"),
                        data.getString("tipoDocumento"),
                        data.getString("producto"),
                        data.getDouble("precioCompra"),
                        data.getInt("cantidad"),
                        data.getString("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding product: " + e);
        }
        return null;
    }

    public ArrayList<Producto> getProducts() {
        ArrayList<Producto> products = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Producto product = new Producto(
                        result.getInt("producto_id"),
                        result.getString("tipoDocumento"),
                        result.getString("producto"),
                        result.getDouble("precioCompra"),
                        result.getInt("cantidad"),
                        result.getString("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error getting products: " + e.getMessage());
        }
        return products;
    }

    public boolean createProduct(String docType, String product, double price, int quantity, String stock) {
        boolean success = false;
        String sql = "INSERT INTO productos (tipoDocumento, producto, precioCompra, cantidad, stock) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, docType);
            statement.setString(2, product);
            statement.setDouble(3, price);
            statement.setInt(4, quantity);
            statement.setString(5, stock);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating product: " + e.getMessage());
        }
        return success;
    }

    public boolean deleteProduct(int productId) {
        boolean success = false;
        String sql = "DELETE FROM productos WHERE producto_id = ?";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, productId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
        return success;
    }

    public boolean updateProduct(int productId, String newDocType, String newProduct,
            double newPurchasePrice, int newQuantity, String newStock) {
        boolean success = false;
        String sql = "UPDATE productos SET tipoDocumento = ?, producto = ?, precioCompra = ?, cantidad = ?, stock = ? WHERE producto_id = ?";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, newDocType);
            statement.setString(2, newProduct);
            statement.setDouble(3, newPurchasePrice);
            statement.setInt(4, newQuantity);
            statement.setString(5, newStock);
            statement.setInt(6, productId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
        return success;
    }

    public ComprobanteCompra findPurchaseVoucher(int id) {
        String sql = "SELECT * FROM comprobantesEmitidosCompra WHERE comprobante_id= ?";
        try {
            PreparedStatement command = dbConnection.prepareStatement(sql);
            command.setInt(1, id);
            ResultSet data = command.executeQuery();
            if (data.next()) {
                return new ComprobanteCompra(
                        data.getInt("comprobante_id"),
                        LocalDate.parse(data.getString("fechaRegistro")),
                        data.getString("tipoComprobante"),
                        data.getInt("serie"),
                        data.getInt("numero"),
                        data.getString("proveedor"),
                        data.getDouble("total")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding product: " + e);
        }
        return null;
    }

    public ArrayList<ComprobanteCompra> getPurchaseVouchers() {
        ArrayList<ComprobanteCompra> vouchers = new ArrayList<>();
        String sql = "SELECT * FROM comprobantesEmitidosCompra";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ComprobanteCompra voucher = new ComprobanteCompra(
                        result.getInt("comprobante_id"),
                        LocalDate.parse(result.getString("fechaRegistro")),
                        result.getString("tipoComprobante"),
                        result.getInt("serie"),
                        result.getInt("numero"),
                        result.getString("proveedor"),
                        result.getDouble("total")
                );
                vouchers.add(voucher);
            }
        } catch (SQLException e) {
            System.out.println("Error getting products: " + e.getMessage());
        }
        return vouchers;
    }

    public boolean createPurchaseVoucher(String registrationDate, String voucherType, long series, long number, String supplier, double total) {
        boolean success = false;
        String sql = "INSERT INTO comprobantesEmitidosCompra (fechaRegistro,tipoComprobante,serie,numero,proveedor,total) VALUES (date('now'), ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, voucherType);
            statement.setLong(2, series);
            statement.setLong(3, number);
            statement.setString(4, supplier);
            statement.setDouble(5, total);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating voucher: " + e.getMessage());
        }
        return success;
    }

    public boolean deletePurchaseVoucher(int voucherId) {
        boolean success = false;
        String sql = "DELETE FROM comprobantesEmitidosCompra WHERE comprobante_id = ?";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, voucherId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting voucher: " + e.getMessage());
        }
        return success;
    }

    public boolean updatePurchaseVoucher(int voucherId, LocalDate registrationDate, String voucherType,
            int series, int number, String supplier, double total) {
        boolean success = false;
        String sql = "UPDATE comprobantesEmitidosCompra SET fechaRegistro = ?, tipoComprobante = ?, serie = ?, numero = ?, proveedor = ?, total  = ? WHERE comprobante_id = ?";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, registrationDate.toString());
            statement.setString(2, voucherType);
            statement.setInt(3, series);
            statement.setInt(4, number);
            statement.setString(5, supplier);
            statement.setDouble(6, total);
            statement.setInt(7, voucherId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating voucher: " + e.getMessage());
        }
        return success;
    }

    public String[] getSettings() {
        String sql = "SELECT * FROM ajustes";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return new String[]{
                    result.getString("lenguaje"),
                    result.getString("modo")
                };
            }
        } catch (SQLException e) {
            System.out.println("Error getting settings: " + e.getMessage());
        }
        return null;
    }

    public boolean updateSettings(String language, String mode) {
        boolean success = false;
        String sql = "UPDATE ajustes SET lenguaje = ?, modo = ? WHERE ajuste_id = 1";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            String[] settings = getSettings();
            if (settings != null) {
                statement.setString(1, language);
                statement.setString(2, mode);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    success = true;
                }
            } else {
                System.out.println("Could not get current settings.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating settings: " + e.getMessage());
        }
        return success;
    }

    public ComprobanteVenta findSalesVoucher(int id) {
        String sql = "SELECT * FROM comprobantesEmitidosVentas WHERE comprobante_id= ?";
        try {
            PreparedStatement command = dbConnection.prepareStatement(sql);
            command.setInt(1, id);
            ResultSet data = command.executeQuery();
            if (data.next()) {
                return new ComprobanteVenta(
                        data.getInt("comprobante_id"),
                        LocalDate.parse(data.getString("fechaRegistro")),
                        data.getString("tipoComprobante"),
                        data.getInt("serie"),
                        data.getInt("numero"),
                        data.getString("cliente"),
                        data.getDouble("total")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding product: " + e);
        }
        return null;
    }

    public ArrayList<ComprobanteVenta> getSalesVouchers() {
        ArrayList<ComprobanteVenta> vouchers = new ArrayList<>();
        String sql = "SELECT * FROM comprobantesEmitidosVentas";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ComprobanteVenta voucher = new ComprobanteVenta(
                        result.getInt("comprobante_id"),
                        LocalDate.parse(result.getString("fechaRegistro")),
                        result.getString("tipoComprobante"),
                        result.getInt("serie"),
                        result.getInt("numero"),
                        result.getString("cliente"),
                        result.getDouble("total")
                );
                vouchers.add(voucher);
            }
        } catch (SQLException e) {
            System.out.println("Error getting products: " + e.getMessage());
        }
        return vouchers;
    }

    public boolean createSalesVoucher(String registrationDate, String voucherType, int series, int number, String client, double total) {
        boolean success = false;
        String sql = "INSERT INTO comprobantesEmitidosVentas (fechaRegistro,tipoComprobante,serie,numero,cliente,total) VALUES (date('now'), ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, voucherType);
            statement.setInt(2, series);
            statement.setInt(3, number);
            statement.setString(4, client);
            statement.setDouble(5, total);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating voucher: " + e.getMessage());
        }
        return success;
    }

    public boolean deleteSalesVoucher(int voucherId) {
        boolean success = false;
        String sql = "DELETE FROM comprobantesEmitidosVentas WHERE comprobante_id = ?";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(1, voucherId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting voucher: " + e.getMessage());
        }
        return success;
    }

    public boolean updateSalesVoucher(int voucherId, LocalDate registrationDate, String voucherType,
            int series, int number, String client, double total) {
        boolean success = false;
        String sql = "UPDATE comprobantesEmitidosVentas SET fechaRegistro = ?, tipoComprobante = ?, serie = ?, numero = ?, cliente = ?, total  = ? WHERE comprobante_id = ?";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, registrationDate.toString());
            statement.setString(2, voucherType);
            statement.setInt(3, series);
            statement.setInt(4, number);
            statement.setString(5, client);
            statement.setDouble(6, total);
            statement.setInt(7, voucherId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating voucher: " + e.getMessage());
        }
        return success;
    }

    public ArrayList<Object> tryLogin(String enteredUser, String enteredPassword) {
        ArrayList<Object> response = new ArrayList<>();
        if (!userExists(enteredUser)) {
            response.add(INCORRECT_USER_OR_PASSWORD);
            return response;
        }
        User user = findUser(enteredUser);
        if (user.isAccountLocked()) {
            response.add(USER_BLOCKED);
            return response;
        }
        if (!isCorrectPassword(user, enteredPassword)) {
            response.add(INCORRECT_USER_OR_PASSWORD);
            return response;
        } else {
            user = findUser(enteredUser);
            user.setFailedLoginAttempts(0);
            updateUserPassword(user);
        }
        if (shouldChangePassword(user)) {
            response.add(MUST_CHANGE_PASSWORD);
            return response;
        }
        response.add(CAN_LOG_IN);
        response.add(user);
        return response;
    }
}
