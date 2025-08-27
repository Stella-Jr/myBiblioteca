package modelo;
import java.time.LocalDate;

public class PrestamoDetalle {

        private int libroId;
        private String titulo;
        private String nombre;
        private String apellido;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;
        private LocalDate fechaDevolucion;

        public PrestamoDetalle(int libroId, String titulo, String nombre, String apellido,
                               LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaDevolucion) {
            this.libroId = libroId;
            this.titulo = titulo;
            this.nombre = nombre;
            this.apellido = apellido;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.fechaDevolucion = fechaDevolucion;
        }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
                "* LibroId: " + libroId +
                "* Titulo: " + titulo +
                "* Nombre: " + nombre +
                "* Apellido: " + apellido +
                "* Fecha de Inicio: " + fechaInicio +
                "* Fecha Limite: " + fechaFin +
                "* Fecha de Devolucion: " + fechaDevolucion +
                '\n';
    }
}
