package dao;

import conexion.ConexionDB;
import modelo.Prestamo;
import modelo.PrestamoDetalle;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public void agregarPrestamo (Prestamo prestamo){
        //consulta para agregar un prestamo
        String sql = "INSERT INTO prestamos (prestamoId, libroId, personaId, fechaInicio, fechaFin, fechaDevolucion) VALUES (?, ?, ?, ?, ?, ?)";

        //abre la conexión con la base de datos (conn) y prepara la consulta SQL (stmt)
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // se asignan los valores del objeto prestamo a los parámetros de la consulta
            stmt.setInt(1, prestamo.getPrestamoId());
            stmt.setInt(2, prestamo.getLibroId());
            stmt.setInt(3, prestamo.getPersonaId());
            stmt.setDate(4, Date.valueOf(prestamo.getFechaInicio()));
            stmt.setDate(5, Date.valueOf(prestamo.getFechaFin()));

            //verificás si hay fecha de devolución; si no, se pone NULL en la base de datos
            if (prestamo.getFechaDevolucion() != null) {
                stmt.setDate(6, Date.valueOf(prestamo.getFechaDevolucion()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            //manda el formulario a la base de datos para guardarlo
            stmt.executeUpdate();
            System.out.println("Prestamo agregado correctamente.");

            //si algo falla (error en conexión, consulta, etc.), atrapa el error y muestra el mensaje
        } catch (SQLException e) {
            System.out.println("Error al agregar prestamo: " + e.getMessage());
        }
    }

    public List <Prestamo> obtenerPrestamo (){
        //se crea la lista prestamo y se crea tambien la consulta
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // se obtiene las fechas desde la base de datos como java.sql.Date
                java.sql.Date fechaInicioSql = rs.getDate("fechaInicio");
                java.sql.Date fechaFinSql = rs.getDate("fechaFin");
                java.sql.Date fechaDevSql = rs.getDate("fechaDevolucion");

                //se convierte las fechas a LocalDate para trabajar con la clase de Java
                LocalDate fechaInicio = fechaInicioSql.toLocalDate();
                LocalDate fechaFin = fechaFinSql.toLocalDate();
                LocalDate fechaDevolucion = (fechaDevSql != null) ? fechaDevSql.toLocalDate() : null;

                //se crea un objeto Prestamo con todos los valores obtenidos
                Prestamo prestamo = new Prestamo(
                        rs.getInt("prestamoId"),
                        rs.getInt("libroId"),
                        rs.getInt("personaId"),
                        fechaInicio,
                        fechaFin,
                        fechaDevolucion
                );

                //se agrega el objeto Prestamo a la lista
                lista.add(prestamo);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener prestamos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarPrestamo (Prestamo prestamo){
        String sql = "UPDATE prestamos SET fechaInicio = ?, fechaFin = ?, fechaDevolucion = ? WHERE prestamoId = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // se asignan los valores del objeto prestamo a los parámetros de la consulta
            stmt.setDate(1, Date.valueOf(prestamo.getFechaInicio()));
            stmt.setDate(2, Date.valueOf(prestamo.getFechaFin()));
            // la fecha de devolución puede ser nula, se usa ternario para asignar valor o NULL
            if (prestamo.getFechaDevolucion() != null) {
                stmt.setDate(3, Date.valueOf(prestamo.getFechaDevolucion()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            stmt.setInt(4, prestamo.getPrestamoId());

            //ejecuta la consulta SQL y guarda cuántas filas fueron modificadas en la variable filasAfectadas
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) { // Comprueba si al menos una fila fue modificada
                System.out.println("Prestamo modificado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró una prestamo con ese ID.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar prestamo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPrestamo (int prestamoId){
        String sql = "DELETE FROM prestamos WHERE prestamoId = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,prestamoId);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Prestamo eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró un prestamo con ese ID");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar prestamo: " + e.getMessage());
            return false;
        }
    }

    public List <Prestamo> buscarPrestamoPorPersona (int personaId){
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE personaId = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personaId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                // se obtiene las fechas desde la base de datos como java.sql.Date
                java.sql.Date fechaInicioSql = rs.getDate("fechaInicio");
                java.sql.Date fechaFinSql = rs.getDate("fechaFin");
                java.sql.Date fechaDevSql = rs.getDate("fechaDevolucion");

                //se convierte las fechas a LocalDate para trabajar con la clase de Java
                LocalDate fechaInicio = fechaInicioSql.toLocalDate();
                LocalDate fechaFin = fechaFinSql.toLocalDate();
                LocalDate fechaDevolucion = (fechaDevSql != null) ? fechaDevSql.toLocalDate() : null;

                //se crea un objeto Prestamo con todos los valores obtenidos
                Prestamo prestamo = new Prestamo(
                        rs.getInt("prestamoId"),
                        rs.getInt("libroId"),
                        rs.getInt("personaId"),
                        fechaInicio,
                        fechaFin,
                        fechaDevolucion
                );

                if(lista.isEmpty()) {
                    System.out.println("No se encontraron préstamos para esa persona.");
                }
                lista.add(prestamo);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar prestamo por personaId: " + e.getMessage());
        }
        return lista;
    }

    public List <Prestamo> buscarPrestamoPorLibro (int libroId){
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE libroId = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, libroId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                // se obtiene las fechas desde la base de datos como java.sql.Date
                java.sql.Date fechaInicioSql = rs.getDate("fechaInicio");
                java.sql.Date fechaFinSql = rs.getDate("fechaFin");
                java.sql.Date fechaDevSql = rs.getDate("fechaDevolucion");

                //se convierte las fechas a LocalDate para trabajar con la clase de Java
                LocalDate fechaInicio = fechaInicioSql.toLocalDate();
                LocalDate fechaFin = fechaFinSql.toLocalDate();
                LocalDate fechaDevolucion = (fechaDevSql != null) ? fechaDevSql.toLocalDate() : null;

                //se crea un objeto Prestamo con todos los valores obtenidos
                Prestamo prestamo = new Prestamo(
                        rs.getInt("prestamoId"),
                        rs.getInt("libroId"),
                        rs.getInt("personaId"),
                        fechaInicio,
                        fechaFin,
                        fechaDevolucion
                );

                if(lista.isEmpty()) {
                    System.out.println("No se encontraron préstamos de ese libro.");
                }
                lista.add(prestamo);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar prestamo por libroId: " + e.getMessage());
        }
        return lista;
    }

    public List <PrestamoDetalle> buscarPrestamoLibroNoDevuelto (){
        List<PrestamoDetalle> lista = new ArrayList<>();
        // se aplica un JOIN a la consulta para pedir el nombre del libro y de la persona asi tengo todos los datos juntos en una sola consulta sin hacer varias
        String sql = "SELECT p.libroId, l.titulo, pe.nombre, pe.apellido " +
                "FROM prestamos p " +
                "JOIN libros l ON p.libroId = l.libroId "+
                "JOIN personas pe ON p.personaId = pe.personaId "+
                "WHERE fechaDevolucion IS NULL " +
                "ORDER BY fechaInicio ASC; ";
        /*p.libroId = l.libroId → relaciona el préstamo con el libro
        p.personaId = pe.personaId → relaciona el préstamo con la persona
        WHERE fechaDevolucion IS NULL → filtra solo los préstamos que no se devolvieron
        ORDER BY fechaInicio ASC → los ordena por fecha de inicio, de más antiguo a más reciente*/

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                // se obtiene las fechas desde la base de datos como java.sql.Date
                java.sql.Date fechaInicioSql = rs.getDate("fechaInicio");
                java.sql.Date fechaFinSql = rs.getDate("fechaFin");
                java.sql.Date fechaDevSql = rs.getDate("fechaDevolucion");

                int libroId = rs.getInt("libroId");          // de prestamos
                String titulo = rs.getString("titulo");      // de libros
                String nombre = rs.getString("nombre");      // de personas
                String apellido = rs.getString("apellido");

                //se convierte las fechas a LocalDate para trabajar con la clase de Java
                LocalDate fechaInicio = fechaInicioSql.toLocalDate();
                LocalDate fechaFin = fechaFinSql.toLocalDate();
                LocalDate fechaDevolucion = (fechaDevSql != null) ? fechaDevSql.toLocalDate() : null;

                /*se crea una nueva clase de PrestamoDetalle con todos los valores obtenidos modificado
                para que se agreguen los demas datos al agregar el join, eso es asi ya que al unir varias
                tablas y datos es necesario tener una nueva clase para representarlos en un solo objeto
                de manera mas simple y sencilla de organizar
                 */
                PrestamoDetalle detalle = new PrestamoDetalle(
                        libroId,
                        titulo,
                        nombre,
                        apellido,
                        fechaInicio,
                        fechaFin,
                        fechaDevolucion
                );
                lista.add(detalle);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar prestamos no devueltos : " + e.getMessage());
        }
        return lista;
    }
}
