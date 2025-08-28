package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase ConexionDB
 * Se encarga de establecer la conexión con la base de datos MySQL
 * - Tiene un método estático `getConnection()` que devuelve una conexión activa.
 * - Centraliza la configuración (URL, usuario, contraseña).
 * - Si en algún momento queremos cambiar la base de datos (ej: de MySQL a otra),
 *   solo necesitamos modificar esta clase y no el resto del código.
 */

public class ConexionDB {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "admin";

    // Método estándar para obtener la conexión
    public static Connection getConnection() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a MySQL");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }

    // Método opcional para probar conexión manualmente
    public static void main(String[] args) {
        getConnection();
    }
}