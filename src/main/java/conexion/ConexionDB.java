package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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