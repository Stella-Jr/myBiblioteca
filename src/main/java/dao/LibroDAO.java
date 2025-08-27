package dao;

import conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Libro;

public class LibroDAO {

    // metodo para agregar libros a la base de datos
    public void agregarLibro(Libro libro) {
        //consulta para agregar un libro
        String sql = "INSERT INTO libros (titulo, autor, anio_publicacion, isbn) VALUES (?, ?, ?, ?)";

        //abre la conexión con la base de datos (conn) y prepara la consulta SQL (stmt)
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // se asignan los valores del objeto Libro a los parámetros de la consulta
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setString(4, libro.getIsbn());

            //manda el formulario a la base de datos para guardarlo
            stmt.executeUpdate();
            System.out.println("Libro agregado correctamente.");

            //si algo falla (error en conexión, consulta, etc.), atrapa el error y muestra el mensaje
        } catch (SQLException e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
    }
    // metodo para obetener los libros de la base de datos
    public List<Libro> obtenerLibros() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("libroId"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("isbn")
                );
                lista.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener libros: " + e.getMessage());
        }
        return lista;
    }

    // método para actualizar los datos de un libro por su ID
    public boolean actualizarLibro(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, anio_publicacion = ?, isbn = ? WHERE libroId = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setString(4, libro.getIsbn());
            stmt.setInt(5, libro.getLibroId());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Libro actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró un libro con ese ID.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar libro: " + e.getMessage());
            return false;
        }
    }

    //método para eliminar un libro por su ID.
    public boolean eliminarLibroPorId(int libroId) {
        String sql = "DELETE FROM libros WHERE libroId = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, libroId);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Libro eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró un libro con ese ID.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }

    //método que devuelve una lista de libros desde la base de datos usando LIKE
    public List<Libro> buscarLibroPorTitulo(String titulo) {

        //Crea una lista vacía donde vamos a guardar los libros que encontremos.
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE titulo LIKE ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //abre la conexión con la base (conn), crea un objeto para ejecutar la consulta (stmt) y guarda el resultado en rs
            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("libroId"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("isbn")
                );
                lista.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro por título: " + e.getMessage());
        }
        return lista;
    }

    public List<Libro> buscarLibroPorAutor(String autor) {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros WHERE autor LIKE ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + autor + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("libroId"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("isbn")
                );
                lista.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro por autor: " + e.getMessage());
        }
        return lista;
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        String sql = "SELECT * FROM libros WHERE isbn = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Libro(
                        rs.getInt("libroId"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("isbn")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro por ISBN: " + e.getMessage());
        }
        return null;
    }
}