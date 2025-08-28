package modelo;

import java.time.LocalDate;

/**
 * Clase Prestamo
 * Representa el registro de un préstamo de un libro dentro del sistema.
 * Cada préstamo contiene la información necesaria para saber:
 * - Qué libro fue prestado (libroId).
 * - A qué persona se le prestó (personaId).
 * - Cuándo comenzó el préstamo (fechaInicio).
 * - Cuál es la fecha límite de devolución (fechaFin).
 * - En qué fecha se devolvió realmente (fechaDevolucion).
 * Se utiliza LocalDate en las fechas para manejar únicamente la fecha sin hora,
 * lo que simplifica el control de vencimientos.
 * Esta clase actúa como modelo de datos, aislando la información que luego
 * se gestionará desde el DAO y el resto del sistema.*/

public class Prestamo {
    private int prestamoId;
    private int libroId;
    private int personaId;
    // se usa el tipo de dato LocalDate para tomar solo la fecha sin hora
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaDevolucion;

    public Prestamo(int prestamoId, int libroId, int personaId, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaDevolucion) {
        this.prestamoId = prestamoId;
        this.libroId = libroId;
        this.personaId = personaId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "\n" +
                "* PrestamoId: " + prestamoId +
                "* LibroId: " + libroId +
                "* PersonaId: " + personaId +
                "* Fecha de Inicio: " + fechaInicio +
                "* Fecha Limite: " + fechaFin +
                "* Fecha de Devolucion: " + fechaDevolucion +
                '\n';
    }
}

