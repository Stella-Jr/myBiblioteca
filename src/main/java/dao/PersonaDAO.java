package dao;

import conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;

public class PersonaDAO {

    public void agregarPersona(Persona persona) {
        //metodo para agregar personas a la base de datos
        //primero se verifica que no se repita un DNI
        String sqlVerificar = "SELECT COUNT(*) FROM personas WHERE dni = ?";
        String sql = "INSERT INTO personas (nombre, apellido, dni, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmtVerificar = conn.prepareStatement(sqlVerificar);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Verificar si el DNI ya existe
            stmtVerificar.setInt(1, persona.getDni());
            ResultSet rs = stmtVerificar.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                System.out.println("Error: Ya existe una persona con el DNI " + persona.getDni());
                return; // No insertar
            }

            // Insertar la persona porque el DNI es único
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setInt(3, persona.getDni());
            stmt.setString(4, persona.getEmail());
            stmt.executeUpdate();

            System.out.println("Persona agregada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar persona: " + e.getMessage());
        }
    }

    public List<Persona> obtenerPersonas() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM personas";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            //recorre cada fila de resultados que devolvió la consulta
            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("personaId"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("dni"),
                        rs.getString("email")
                );
                //agrega ese libro a la lista creada al inicio
                lista.add(persona);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener personas: " + e.getMessage());
        }
        return lista; // devuelve la lista completa con todos los libros encontrados
    }

    public boolean actualizarPersona(Persona persona) {
        String sql = "UPDATE personas SET nombre = ?, apellido = ?, email = ? WHERE dni = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setInt(4, persona.getDni());


            //ejecuta la consulta SQL y guarda cuántas filas fueron modificadas en la variable filasAfectadas
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) { // Comprueba si al menos una fila fue modificada
                System.out.println("Persona modificada correctamente.");
                return true;
            } else {
                System.out.println("No se encontró una persona con ese DNI.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar persona: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPersona(int dni) {
        String sql = "DELETE FROM personas WHERE dni = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dni);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Persona eliminada correctamente.");
                return true;
            } else {
                System.out.println("No se encontró una persona con ese DNI.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
            return false;
        }
    }
}